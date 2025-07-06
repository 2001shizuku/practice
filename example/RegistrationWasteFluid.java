package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationWasteFluid {

    public static void main(String[] args) {
        WebDriverExample webDriverExample = new WebDriverExample();
        Scanner scanner = new Scanner(System.in);

        System.out.println("通常の有機廃液の番号を入力してください(次に進むにはEnterキーのみを入力してください)> ");
        List<String> listBurnable = buildList(scanner);
        System.out.println("ハロゲンの有機廃液の番号を入力してください(次に進むにはEnterキーのみ入力してください)> ");
        List<String> listHalogen = buildList(scanner);

        System.out.println("通常の有機廃液:");
        displayList(listBurnable);
        System.out.println("ハロゲンの有機廃液:");
        displayList(listHalogen);
        int sumWasteFluid = listBurnable.size() + listHalogen.size();
        System.out.println("合計：" + sumWasteFluid + "個");

        System.out.print("以上の内容で良ければ0を押してください> ");
        while (true) {
            String line = scanner.nextLine();
            if (sumWasteFluid == 0) {
                System.out.println("廃液登録するタンクがないためプログラムを終了します。");
                break;
            } else if (!line.equals("0")) {
            System.out.print("問題がなければ0を押してください> ");
            continue;
            }
            webDriverExample.openIASO();
            webDriverExample.Registration(listBurnable, "Burnable");
            webDriverExample.Registration(listHalogen, "Halogen");
            break;
        }

    }

    public static List<String> buildList(Scanner scanner) {
        List<String> list = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {  // 空行で終了
                break;
            } else if (!line.matches("^\\d{4}$")) {
                System.out.println("数字4桁になっていません> ");
                continue;
            } else if (list.contains(line)) {
                System.out.println(line + "はすでに入力されています> ");
                continue;
            }
            list.add(line);  // 各行をリストに追加
        }
        return list;
    }

    public static void displayList(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("なし");
        } else {
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
