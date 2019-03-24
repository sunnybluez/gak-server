package com.springboot.service.impl;

import com.springboot.dao.RegisterUserDao;
import com.springboot.dao.StudentDao;
import com.springboot.dao.TeacherDao;
import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.domain.Teacher;
import com.springboot.enums.UserIdentity;
import com.springboot.exception.DuplicateAccountErr;
import com.springboot.exception.NotAuthenticateException;
import com.springboot.exception.WrongInfoException;
import com.springboot.service.AccountService;
import com.springboot.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RegisterUserDao registerUserDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;


    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public HashMap<String, Object> login(String email, String password, UserIdentity identity) {

        String token = "";
        HashMap<String, Object> result = new HashMap<>();
        RegisterUser registerUser = registerUserDao.findByEmail(email);
        if (registerUser == null) {
            throw new WrongInfoException();
        } else if (!registerUser.isAuthenticated()) {
            token = JwtHelper.getToken(email, identity.toString());
            sendSimpleMail(email, token);

            throw new NotAuthenticateException();
        } else if (registerUser.getUserIdentity() != identity) {
            throw new WrongInfoException();
        } else if (!registerUser.getPassword().equals(password)) {
            throw new WrongInfoException();
        } else {
            token = JwtHelper.getToken(email, identity.toString());
            result.put("token", token);
//            result.put("id")
            if (registerUser.getUserIdentity().equals(UserIdentity.STUDENT)) {
                int studentId = studentDao.findByEmail(email).getId();
                result.put("id", studentId);
            } else if (registerUser.getUserIdentity().equals(UserIdentity.TEACHER)) {
                int teacherId = teacherDao.findByEmail(email).getId();
                result.put("id",teacherId);
            }

        }


        return result;
    }

    @Override
    public String register(String email, String password, UserIdentity identity) {
        RegisterUser registerUser = registerUserDao.findByEmail(email);
        if (registerUser == null) {                                 //可注册

            RegisterUser newUser = new RegisterUser(email, password, identity);
            registerUserDao.addRegisterUser(newUser);

            String token = JwtHelper.getToken(email, identity.toString());
            sendSimpleMail(email,token);

            return "请前往邮箱验证使账户生效";
        }else if(!registerUser.isAuthenticated()){                 //未激活 注销状态

            String token = JwtHelper.getToken(email, identity.toString());
            sendSimpleMail(email,token);

            throw new NotAuthenticateException();

        }else {                                                     //账户已存在

            throw new DuplicateAccountErr();

        }
    }

    @Override
    public String authenticate(String token) {
        String email = JwtHelper.parseToken(token).getSubject();

        RegisterUser unAuth =  registerUserDao.findByEmail(email);
        if (unAuth != null) {
            unAuth.setAuthenticated(true);
            registerUserDao.modifyRegisterUser(unAuth);
        }

        UserIdentity userIdentity = unAuth.getUserIdentity();
        switch (userIdentity) {
            case TEACHER:
                teacherDao.addTeacher(new Teacher(email, unAuth.getPassword()));
                break;
            case STUDENT:
                Student student = studentDao.findByEmail(email);
                if (student == null) {
                    studentDao.addStudent(new Student(email, unAuth.getPassword()));
                }else {                                                              //注册过后注销的用户
                    student.setCancelled(false);
                    studentDao.modifyStudent(student);
                    return "取消注销";
                }
                break;
            default:
                break;
        }
        return "验证成功";
    }

    @Override
    public String cancelStudentAccount(int id) {
        Student studentCancel = studentDao.findById(id);
        studentCancel.setCancelled(true);
        studentDao.modifyStudent(studentCancel);

        String email = studentCancel.getEmail();
        RegisterUser registerUser = registerUserDao.findByEmail(email);
        registerUser.setAuthenticated(false);
        registerUserDao.modifyRegisterUser(registerUser);

        return "注销成功";
    }

    public void sendSimpleMail(String userEmail, String token) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("1332901986@qq.com");
            helper.setTo(userEmail);
            helper.setSubject("Gak TSS 注册验证");

            StringBuffer sb = new StringBuffer();
            sb.append("<p>您的邮箱刚刚在Gak TSS教学支持系统中进行了注册</p>")
                    .append("请及时<a href='").append("http://localhost:8081/#/authentication?token="+token).append("'>点击链接</a>验证注册。");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
