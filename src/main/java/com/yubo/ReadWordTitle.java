package com.yubo;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.util.List;

public class ReadWordTitle {
    public static void main(String[] args) {
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream("D:\\document/testTitle.docx"));
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                System.out.println(paragraph.getStyle());
                if (paragraph.getStyle() != null && paragraph.getStyle().equals("1")) {
                    String title = paragraph.getText();
                    while ("".equals(title)){
                        continue;
                    }
                    System.out.println("Title: " + title);
                }
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
