package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class NameMapping {
    public Map<String, String> arrayToMap (String[] strings) {
        Map<String, String> map = new LinkedHashMap<>();

        for (String string : strings) {
            String[] words = string.split(" ");

            if (words[0].matches("^\\d{6}$")) {
                map.put(words[0], words[1]);
            }
            // strings[0]が数字6文字(正規表現で区別)なら、string[0]をkey、string[1]をvalueにしてマッピング
        }
    return map;
    }
}
