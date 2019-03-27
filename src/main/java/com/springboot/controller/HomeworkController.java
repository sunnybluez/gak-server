package com.springboot.controller;


import com.springboot.annotation.TeacherAuth;
import com.springboot.domain.Homework;
import com.springboot.domain.StudentWork;
import com.springboot.service.TaskService;
import com.springboot.utils.HandleFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@Slf4j
@RequestMapping(value = "homework")
public class HomeworkController {

    @Autowired
    TaskService taskService;

    @PostMapping(value = "addHomework")
    @TeacherAuth
    @ResponseBody
    public String addHomeWork(@RequestBody Map map) {
        int courseReleaseId = (int) map.get("courseReleaseId");
        String title = (String) map.get("title");
        String description = (String) map.get("description");
        int finishTime = (int) map.get("finishTime");

        return taskService.addHomework(courseReleaseId, title, description, finishTime);
    }


    @GetMapping(value = "getAllHomework")
    public List<HashMap<String, Object>> getAllHomework(int courseReleaseId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<Homework> homeworkList = taskService.getAllHomework(courseReleaseId);
        for (Homework homework : homeworkList) {
            HashMap<String, Object> homeworkJson = new HashMap<>();
            homeworkJson.put("id", homework.getId());
            homeworkJson.put("title", homework.getTitle());
            homeworkJson.put("description", homework.getDescription());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            homeworkJson.put("deadline", sdf.format(homework.getDeadline()));
            list.add(homeworkJson);
        }
        return list;
    }



    @PostMapping(value = "uploadStudentWork")
    @ResponseBody
    public String uploadStudentWork(@RequestParam("file") MultipartFile file,  int homeworkId, int courseReleaseId, int studentId) {
        if (!file.isEmpty()) {


            String dirName = "target/classes/static/StudentWork/" + homeworkId;
            File courseCreatePath = new File(dirName);
            if (!courseCreatePath.exists()) {
                courseCreatePath.mkdirs();
            }
            String filePath = dirName + "/" + file.getOriginalFilename();

            taskService.addStudentWork(filePath, homeworkId, courseReleaseId, studentId, file.getOriginalFilename());
            try {
                BufferedOutputStream outStream = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                filePath)));

                outStream.write(file.getBytes());
                outStream.flush();
                outStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }


    }

    @GetMapping(value = "getSingleStudentWork")
    public HashMap<String, Object> getAllHomework(int studentId,int courseReleaseId,int homeworkId) {
        HashMap<String, Object> map = new HashMap<>();
        StudentWork studentWork = taskService.getSingleStudentWork(studentId, courseReleaseId, homeworkId);
        if(studentWork==null){
            return map;
        }else {
            map.put("id", studentWork.getId());
            map.put("name", studentWork.getName());
            map.put("path", studentWork.getPath().substring(15));
            map.put("homeworkId", studentWork.getHomework().getId());
            return map;

        }


    }


    @RequestMapping(value = "/downloadWorkZip", method = RequestMethod.GET)
    public void Download(HttpServletResponse res, String homeworkId) {
        int homeworkId1 = Integer.parseInt(homeworkId);
        List<StudentWork> studentWorkList = taskService.getAllStudentWork(homeworkId1);
        List<String> nameList = new ArrayList<>();

        File source = new File("target/classes/static/StudentWork/"+homeworkId);
        List<File> files = Arrays.asList(source.listFiles());
        if (files.size() != 0) {

            for(int i = 0;i<files.size();i++) {
                for (StudentWork studentWork : studentWorkList) {
                    if (files.get(i).getName().equals(studentWork.getName())) {
                        nameList.add(studentWork.getCourseSelect().getStudent().getId() +"."+ studentWork.getName().split("\\.")[1]);
                        break;
                    }
                }

            }

            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
            //获取String类型的时间
            String createDate = sdf.format(date);

            String fileName = createDate+".zip";
            try {
                HandleFile.generateZip(new FileOutputStream(new File("target/classes/static/"+fileName)), files,nameList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File("target/classes/static/"
                        + fileName)));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("success");
        }
    }

}
