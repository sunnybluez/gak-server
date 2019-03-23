package com.springboot.demo;

import com.springboot.dao.*;
import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.domain.Teacher;
import com.springboot.enums.UserIdentity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

	@Autowired
	StudentDao studentDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	ManagerDao managerDao;

	@Autowired
	RegisterUserDao registerUserDao;

    @Autowired
    private ReplyDao replyDao;



	@Test
	public void contextLoads() {


		addStudent("3101998985@qq.com");
		addTeacher("1@qq.com");


	}

	public void addStudent(String email) {
		registerUserDao.addRegisterUser(new RegisterUser(
				email,"e10adc3949ba59abbe56e057f20f883e", UserIdentity.STUDENT
		));

		RegisterUser registerUser = registerUserDao.findByEmail(email);
		registerUser.setAuthenticated(true);
		registerUserDao.modifyRegisterUser(registerUser);

		studentDao.addStudent(new Student(
				email,"e10adc3949ba59abbe56e057f20f883e"
		));
	}

	public void addTeacher(String email) {
		registerUserDao.addRegisterUser(new RegisterUser(
				email,"e10adc3949ba59abbe56e057f20f883e", UserIdentity.TEACHER
		));

		RegisterUser registerUser = registerUserDao.findByEmail(email);
		registerUser.setAuthenticated(true);
		registerUserDao.modifyRegisterUser(registerUser);

		teacherDao.addTeacher(new Teacher(
				email,"e10adc3949ba59abbe56e057f20f883e"
		));
	}

	public void  addRegisterReply(){
        registerUserDao.addRegisterUser(new RegisterUser(
                "1", "1111", UserIdentity.STUDENT
        ));
        registerUserDao.addRegisterUser(new RegisterUser(
                "2", "2222", UserIdentity.TEACHER
        ));
        registerUserDao.addRegisterUser(new RegisterUser(
                "3", "3333", UserIdentity.MANAGER
        ));
        registerUserDao.addRegisterUser(new RegisterUser(
                "4", "4444", UserIdentity.MANAGER
        ));


        RegisterUser registerUser = registerUserDao.findByEmail("1");
        RegisterUser registerUser2 = registerUserDao.findByEmail("2");
//        replyDao.addReply(new Reply(registerUser));
//        replyDao.addReply(new Reply(registerUser2));
//        replyDao.addReply(new Reply(registerUser2));

//        List<Reply> replies = registerUserDao.findByEmail("2").getReplyList();
//        for (Reply reply : replies) {
//            System.out.println(reply.getId());
//        }
//        RegisterUser user = replyDao.findById(1).getRegisterUser();
////        log.error("213"+user.getPassword());
//        System.out.println(user.getPassword());

//        registerUserDao.removeGister(1);


    }

}
