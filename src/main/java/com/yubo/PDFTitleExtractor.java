package com.yubo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFTitleExtractor {
    public static void main(String[] args) throws IOException {
        double pageHeight = 100;
        try (PDDocument document = PDDocument.load(new File("example.pdf"))) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea() {
                @Override
                protected void writeString(String str, List<TextPosition> textPositions) {
                    if (isTitle(textPositions)) {
                        System.out.println(str);
                    }
                }


                private boolean isTitle(List<TextPosition> textPositions) {
                    // 假设标题位于页面顶部10%的位置
                    double topThreshold = 0.1;

                    for (TextPosition text : textPositions) {
                        if (text.getY() < pageHeight * topThreshold) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            double pageWidth = 50;


            stripper.addRegion("title", new Rectangle2D.Double(0, 0, pageWidth, pageHeight));
//            stripper.extractRegions(document);
        }
    }

}
