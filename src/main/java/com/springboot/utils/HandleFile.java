package com.springboot.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class HandleFile{


    /**
     * 解析Excel文件
     *
     * @param in
     * @param fileName
     */
    public static List<List<Object>> parseExcel(InputStream in, String fileName) throws Exception {
        List list = null;
        Workbook work = null;
        list = new ArrayList<>();
        //创建Excel工作薄
        work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    String value = "";
                    cell = row.getCell(y);
                    switch (cell.getCellType()) {
                        // 数字
                        case HSSFCell.CELL_TYPE_NUMERIC:

                            //如果为时间格式的内容
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                value=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                li.add(value);
                                break;
                            }else {
                                value= String.valueOf(cell.getNumericCellValue());
                                String[] split = value.split("\\.");
                                //整型不保留小数部分
                                if (split[1].length()==1 && split[1].equals("0")){
                                    value = split[0];
                                    li.add(value);
                                    break;
                                }
                                li.add(value);
                                break;
                            }
                            // 字符串
                        case HSSFCell.CELL_TYPE_STRING:

                            value = cell.getStringCellValue();
                            li.add(value);
                            break;
                        // Boolean
                        case HSSFCell.CELL_TYPE_BOOLEAN:

                            value = cell.getBooleanCellValue() + "";
                            li.add(value);
                            break;
                        // 公式
                        case HSSFCell.CELL_TYPE_FORMULA:
                            value = cell.getCellFormula() + "";
                            li.add(value);
                            break;
                        // 空值
                        case HSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            li.add(value);
                            break;
                        // 故障
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "非法字符";
                            li.add(value);
                            break;
                        default:
                            value = "未知类型";
                            li.add(value);
                            break;
                    }
                }
                list.add(li);
            }
        }
        return list;

    }


    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }


    /**
     * 生成压缩文件
     * @param os
     * @param files
     * @throws Exception
     */
    public static void generateZip(OutputStream os, List<File> files,List<String> nameList) throws Exception {
        ZipOutputStream out = null;
        try {
            byte[] buffer = new byte[1024];
            //生成的ZIP文件名为Demo.zip
            out = new ZipOutputStream(os);
            //需要同时下载的两个文件result.txt ，source.txt
            for(int i = 0;i<files.size();i++) {
                File file = files.get(i);
                FileInputStream fis = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(nameList.get(i)));
                int len;
                //读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
                out.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}