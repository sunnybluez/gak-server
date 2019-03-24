package com.springboot.demo;

import com.springboot.dao.*;
import com.springboot.domain.CourseSelect;
import com.springboot.domain.RegisterUser;
import com.springboot.domain.Student;
import com.springboot.domain.Teacher;
import com.springboot.enums.GradeType;
import com.springboot.enums.SelectState;
import com.springboot.enums.Term;
import com.springboot.enums.UserIdentity;
import com.springboot.service.ManagerService;
import com.springboot.service.StudentCourseService;
import com.springboot.service.TeacherCourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

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

	@Autowired
	TeacherCourseService teacherCourseService;

	@Autowired
	ManagerService managerService;

	@Autowired
	StudentCourseService studentCourseService;

	@Autowired
	CourseSelectDao courseSelectDao;





	@Test
	public void contextLoads() {
		selectCourseTest();

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


	//创建一个已经验证过的老师账号
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

	//创建一门课程
    public void addCourseCreate(String teacherEmail,String name,String description){

		int teacherId = teacherDao.findByEmail(teacherEmail).getId();
		teacherCourseService.createCourse(teacherId, name, description);
	}

	//审批一门课程
	public void approveCourseCreate(int courseCreateId,boolean isApprove){
		managerService.approveCourseCreate(courseCreateId, isApprove);
	}

	//发布一门课程
	public void releaseCourse(int courseCreateId, GradeType gradeType, int limitNum, Term term) {
		teacherCourseService.releaseCourse(courseCreateId, gradeType, limitNum, term);
	}

	public void approveCourseRelease(int courseReleaseId, boolean isApprove){
		managerService.approveCourseRelease(courseReleaseId, isApprove);
	}

	//初选
	public void selectCourse(String studentEmail, int courseReleaseId){
		int studentId = studentDao.findByEmail(studentEmail).getId();
		String result = studentCourseService.selectCourse(studentId, courseReleaseId);
		System.out.println(result);

	}

	//补选
	public void reelectCourse(String studentEmail, int courseReleaseId){
		int studentId = studentDao.findByEmail(studentEmail).getId();
		String result = studentCourseService.reelectCourse(studentId, courseReleaseId);
		System.out.println(result);

	}


	//查看某门课的选课状况
	public void showSelectCondition( int courseReleaseId){


		String logResult= "";

		List<CourseSelect> ongoing = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId,SelectState.ONGOING);
		List<CourseSelect> drop = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId,SelectState.RETURNED);
		List<CourseSelect> fail = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId,SelectState.FAILED);
		List<CourseSelect> select = courseSelectDao.findAllCSByCRIdAndState(courseReleaseId,SelectState.SELECTED);

		for (CourseSelect courseSelect : ongoing) {
			logResult+=courseSelect.getStudent().getEmail() + " 正在上 " + courseSelect.getCourseRelease().getCourseCreate().getName()+"\n";
		}
		for (CourseSelect courseSelect : drop) {
			logResult+=courseSelect.getStudent().getEmail() + " 退了 " + courseSelect.getCourseRelease().getCourseCreate().getName()+"\n";
		}
		for (CourseSelect courseSelect : fail) {
			logResult+=courseSelect.getStudent().getEmail() + " 没选上 " + courseSelect.getCourseRelease().getCourseCreate().getName()+"\n";
		}
		for (CourseSelect courseSelect : select) {
			logResult+=courseSelect.getStudent().getEmail() + " 选了 " + courseSelect.getCourseRelease().getCourseCreate().getName()+"\n";
		}

		logResult += "\n";
		try {
			FileWriter fw = new FileWriter("src/test/java/com/springboot/demo/选课日志信息.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(logResult);
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	public void selectCourseTest() {

		addStudent("1@qq.com");
		addStudent("2@qq.com");
		addStudent("3@qq.com");
		addStudent("4@qq.com");
		addStudent("6@qq.com");

		addTeacher("5@qq.com");

		addCourseCreate("5@qq.com", "数据库", "第一堂数据库");
		approveCourseCreate(1, true);
		addCourseCreate("5@qq.com", "数据库2", "第二堂数据库");
		approveCourseCreate(2, true);

		releaseCourse(1, GradeType.FRESHMAN, 3, Term.SPRING2018);
		approveCourseRelease(1, true);
		releaseCourse(2, GradeType.FRESHMAN, 4, Term.SPRING2018);
		approveCourseRelease(2, true);

		selectCourse("1@qq.com", 1);
		selectCourse("1@qq.com",1);
		studentCourseService.dropCourse(1);
		selectCourse("1@qq.com",1);
		selectCourse("2@qq.com",1);
		studentCourseService.dropCourse(2);
		selectCourse("3@qq.com",1);
		selectCourse("4@qq.com",1);
		showSelectCondition(1);
		teacherCourseService.beginClass(1);
		showSelectCondition(1);
//		studentCourseService.dropCourse(2);
//		studentCourseService.dropCourse(1);
		selectCourse("6@qq.com",1);
		showSelectCondition(1);
		studentCourseService.dropCourse(1);
		showSelectCondition(1);
		reelectCourse("6@qq.com",1);
		showSelectCondition(1);
		reelectCourse("1@qq.com",1);
		showSelectCondition(1);

		teacherCourseService.officialBeginClass(1);
		System.out.println(studentCourseService.dropCourse(3));
		System.out.println(studentCourseService.dropCourse(1));
//		studentCourseService.
	}

}
