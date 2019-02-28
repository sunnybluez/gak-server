package com.springboot.demo;

import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.enums.UserIdentity;
import com.springboot.service.ManagerService;
import com.springboot.service.RegisterUserService;
import com.springboot.service.StudentService;
import com.springboot.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	ManagerService managerService;

	@Autowired
	RegisterUserService registerUserService;



	@Test
	public void contextLoads() {


		addStudent("1@qq.com");

//			studentService.addStudent(new Student(
//					"student@qq.com", "e10adc3949ba59abbe56e057f20f883e", SexType.MALE
//			));


//		for(int i = 0;i<5;i++) {
//
//			Student student = new Student(
//					"student"+i+"@qq.com", "e10adc3949ba59abbe56e057f20f883e"
//			);
//			studentService.addStudent(student);
//
//			Student student1 = studentService.findByEmail("student" + i + "@qq.com");
//			student1.setAge(15);
//			studentService.modifyStudent(student1);
//
//		}





//		teacherService.addTeacher(new Teacher(
//				"teacher@qq.com", "e10adc3949ba59abbe56e057f20f883e"
//		));

	}

	public void addStudent(String email) {
		registerUserService.addRegisterUser(new RegisterUser(
				email,"e10adc3949ba59abbe56e057f20f883e", UserIdentity.STUDENT
		));

		RegisterUser registerUser = registerUserService.findByEmail("1@qq.com");
		registerUser.setAuthenticated(true);
		registerUserService.modifyRegisterUser(registerUser);

		studentService.addStudent(new Student(
				email,"e10adc3949ba59abbe56e057f20f883e"
		));
	}

}
