package com.yubo;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;


public class TextToExcel {
    public static void main(String argv[]){
        //读取的txt文件路径
        String txtFilePath = "C:\\Users\\wmy\\Downloads\\aaa.txt";
        //生成的excel文件路径
        String excelFilePath = "C:\\Users\\wmy\\Desktop\\aaa.xls";
        //编码格式
        String encoding = "utf-8";
        readAndWrite(txtFilePath,excelFilePath,encoding);
    }

    public static void readAndWrite(String filePath,String excelFilePath,String encoding){
        try{
            File file = new File(filePath);
            File tempFile = new File(excelFilePath);
            //判断文件是否存在
            if (!file.isFile() || !file.exists()){
                System.out.println("找不到指定的文件");
            }
            InputStreamReader read = new InputStreamReader(Files.newInputStream(file.toPath()), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
            //设置字体为宋体，11号
            WritableFont headerFont = new WritableFont(WritableFont.createFont("宋体"), 11,
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat headerFormat = new WritableCellFormat (headerFont);

            //一些临时变量，用于写到excel中
            String lineTxt = null;
            int i = 0;
            int sheetNum = 1;
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            while ((lineTxt = bufferedReader.readLine()) != null){
                try {
                    String[] list =  lineTxt.split("\t");
                    if(i == 65536){
                        i = 0;
                        sheetNum ++;
                        sheet = workbook.createSheet("Sheet"+sheetNum, 0);
                    }
                    for (int f=0;f<list.length;f++){
                        sheet.addCell(new Label(f, i, list[f], headerFormat));
                    }
                    i++;
                    //判断内容是否为空行，如果是，则转行
                    if("\\r".equals(lineTxt)){
                        continue;
                    }
                } catch (WriteException e) {
                    System.out.println("超出sheet范围数据:"+lineTxt);
                    e.printStackTrace();
                }
            }
            //写入文件
            workbook.write();
            //关闭文件
            workbook.close();
            read.close();
        }catch (Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

}
