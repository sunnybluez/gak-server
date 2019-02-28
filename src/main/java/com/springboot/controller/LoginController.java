package com.springboot.controller;

import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.domain.Teacher;
import com.springboot.enums.UserIdentity;
import com.springboot.exception.DuplicateAccountErr;
import com.springboot.exception.NotAuthenticateException;
import com.springboot.exception.WrongInfoException;
import com.springboot.service.RegisterUserService;
import com.springboot.service.StudentService;
import com.springboot.service.TeacherService;
import com.springboot.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
//@CrossOrigin
@Slf4j
public class LoginController {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;



    @RequestMapping(value = "login")
    public String login(String email, String password, String identity) {

        log.info("访问login");
        UserIdentity userIdentity = UserIdentity.valueOf(identity.toUpperCase());
        String token = "";

        RegisterUser registerUser = registerUserService.findByEmail(email);
        if (registerUser == null) {
            throw new WrongInfoException();
        } else if (!registerUser.isAuthenticated()) {
            token = JwtHelper.getToken(email, userIdentity.toString());
            sendSimpleMail(email,token);

            throw new NotAuthenticateException();
        } else if (registerUser.getUserIdentity() != userIdentity) {
            throw new WrongInfoException();
        } else if (!registerUser.getPassword().equals(password)) {
            throw new WrongInfoException();
        } else {
            token = JwtHelper.getToken(email, userIdentity.toString());
        }


        return token;
    }


    @RequestMapping(value = "register")
    public String register(String email, String password, String identity) {
        log.info("访问register");
        UserIdentity userIdentity = UserIdentity.valueOf(identity.toUpperCase());

        RegisterUser registerUser = registerUserService.findByEmail(email);
        if (registerUser == null) {                                 //可注册

            RegisterUser newUser = new RegisterUser(email, password, userIdentity);
            registerUserService.addRegisterUser(newUser);

            String token = JwtHelper.getToken(email, userIdentity.toString());
            sendSimpleMail(email,token);

            return "请前往邮箱验证使账户生效";
        }else if(!registerUser.isAuthenticated()){                 //未激活 注销状态

            String token = JwtHelper.getToken(email, userIdentity.toString());
            sendSimpleMail(email,token);

            throw new NotAuthenticateException();

        }else {                                                     //账户已存在

            throw new DuplicateAccountErr();

        }

//        return "";
    }

    @RequestMapping(value = "authenticate")
    public String authenticate(String token) {
        String email = JwtHelper.parseToken(token).getSubject();

        RegisterUser unAuth =  registerUserService.findByEmail(email);
        if (unAuth != null) {
            unAuth.setAuthenticated(true);
            registerUserService.modifyRegisterUser(unAuth);
        }

        UserIdentity userIdentity = unAuth.getUserIdentity();
        switch (userIdentity) {
            case TEACHER:
                teacherService.addTeacher(new Teacher(email, unAuth.getPassword()));
                break;
            case STUDENT:
                Student student = studentService.findByEmail(email);
                if (student == null) {
                    studentService.addStudent(new Student(email, unAuth.getPassword()));
                }else {                                                              //注册过后注销的用户
                    student.setCancelled(false);
                    studentService.modifyStudent(student);
                    return "取消注销";
                }
                break;
            default:
                break;
        }
        return "验证成功";

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
