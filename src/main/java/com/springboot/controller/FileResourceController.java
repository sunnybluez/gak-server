package com.springboot.controller;

import com.springboot.domain.CourseCreate;
import com.springboot.domain.CourseWare;
import com.springboot.domain.StudentWork;
import com.springboot.service.CourseStatisticsService;
import com.springboot.service.CourseWareService;
import com.springboot.service.TaskService;
import com.springboot.utils.HandleFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "fileStream")
public class FileResourceController {

    @Autowired
    CourseWareService courseWareService;

    @Autowired
    CourseStatisticsService courseStatisticsService;

    @Autowired
    TaskService taskService;

    @PostMapping(value = "uploadCourseWare")
    @ResponseBody
    public String handleUploadCourseWare(@RequestParam("file") MultipartFile file, int courseReleaseId) {
        if (!file.isEmpty()) {
            CourseCreate courseCreate = courseStatisticsService.getCourseReleaseById(courseReleaseId).getCourseCreate();
            int courseCreateId = courseCreate.getId();

            String dirName = "target/classes/static/courseWare/" + courseCreateId;
            File courseCreatePath = new File(dirName);
            if (!courseCreatePath.exists()) {
                courseCreatePath.mkdirs();
            }
            String filePath = dirName + "/" + file.getOriginalFilename();

            CourseWare courseWare = new CourseWare(file.getOriginalFilename(), filePath, courseCreate);
            courseWareService.addCourseWare(courseWare);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                filePath)));
//                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }


    }


    @GetMapping(value = "getCourseWarePathList")
    public List<HashMap<String, Object>> getCourseWarePathList(int courseReleaseId) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        int courseCreateId = courseStatisticsService.getCourseReleaseById(courseReleaseId).getCourseCreate().getId();
        List<CourseWare> courseWareList = courseWareService.getAllCourseWare(courseCreateId);
        for (CourseWare courseWare : courseWareList) {
            HashMap<String, Object> courseWares = new HashMap<>();
            courseWares.put("name", courseWare.getName());
            courseWares.put("id", courseWare.getId());
            courseWares.put("path", courseWare.getPath().substring(15));
            result.add(courseWares);
        }
        return result;

    }

    @PostMapping(value = "uploadCourseScoreExcel")
    @ResponseBody
    public String HandleUploadHomeworkScore(@RequestParam("file") MultipartFile file, int homeworkId) {
        if (!file.isEmpty()) {

            try {

                InputStream inputStream = file.getInputStream();
                List<List<Object>> list = HandleFile.parseExcel(inputStream,file.getOriginalFilename());
                List<StudentWork> studentWorkList = taskService.getAllStudentWork(homeworkId);

                for (int i = 0; i < list.size(); i++) {
                    List<Object> lo = list.get(i);
                    int studentId = Integer.parseInt((String) lo.get(0));
                    double score = Double.parseDouble((String) lo.get(1));
                    for (StudentWork studentWork : studentWorkList) {
                        if (studentWork.getId() == studentId) {
                            taskService.setWorkScore(studentWork,score);
                        }
                    }

                }

                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return "上传成功";
        }else {
            return "文件为空";
        }




    }


}
