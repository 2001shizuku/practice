package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFExtraction {
    public String[] PDFToArray(String string) {
        String[] lines = {};
        try {
            File file = new File(string + ".pdf");
            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // 改行で分割して配列に格納
            lines = text.split("\n");
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    return lines;
    }

}
