package com.yubo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class ReadPDFTitle {
    public static void main(String[] args) {
        try {
            // 加载PDF文件
            File file = new File("D:\\document/testTitle1.pdf");
            //PDDocument document = PDDocument.load(multipartFile.getInputStream());
            PDDocument document = PDDocument.load(file);
            // 获取PDF文档信息
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setSortByPosition(true);
            textStripper.setStartPage(1);
            textStripper.setEndPage(1);
            String text = textStripper.getText(document);
            if(text.indexOf("（")>-1){
                text=text.replace("（","(");
            }
            if(text.indexOf("）")>-1){
                text=text.replace("）",")");
            }
            String testTitle = "测试标题(1)";
            if(text.indexOf(testTitle)>-1){
                System.out.println("测试成功");
            }
            // 关闭PDF文档
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
