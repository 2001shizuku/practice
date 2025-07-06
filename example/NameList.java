package org.example;

import java.util.Map;

public class NameList {
    public static void main(String[] args) {
        PDFExtraction pdfExtraction = new PDFExtraction();
        NameMapping nameMapping = new NameMapping();

        Map<String, String> newMap = nameMapping.arrayToMap(pdfExtraction.PDFToArray("Mar7"));
        Map<String, String> oldMap = nameMapping.arrayToMap(pdfExtraction.PDFToArray("oldest"));

        for (String key : oldMap.keySet()) {
            if (!newMap.containsKey(key)) {
                System.out.println(key + ":" + oldMap.get(key));
                // entrySet()の方が処理早いらしい
            }
        }
    }
}
