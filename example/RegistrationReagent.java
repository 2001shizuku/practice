package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.time.Duration;
import java.util.Scanner;

public class RegistrationReagent {
    public static void main(String[] args) {
        Scanner scannerReagent = new Scanner(System.in);
        System.out.print("登録する試薬のCASや製品番号を入力してください> ");
        String userInputReagent;
        // 製品番号の長さ判定
        while (true) {
            userInputReagent = scannerReagent.nextLine();
            if (userInputReagent.length() <= 3) {
                System.out.print("4文字以上で入力してください> ");
                continue;
            }
            break;
        }

//        System.out.print("保冷品ならc,冷凍品ならf,劇毒物ならp,グロボならg,溶媒庫ならs,何もなかったら0を押してください> ");
//        String userInputInfo;
//        // 毒劇物の判定入れる
//        while (true) {
//            userInputInfo = scannerReagent.nextLine();
//            // 条件が満たされない場合に次の繰り返しに進む
//            if (!userInputInfo.matches("[cfpgs0]")) {
//                System.out.print("無効な入力です。再入力してください> ");
//                continue;
//            }
//            break;
//        }

//        System.out.print("IASOバーコードの下5桁を入力してください> ");
//        String strUserInputBarcode;
//        // 入力された値の長さ判定
//        while (true) {
//            strUserInputBarcode = scannerReagent.nextLine();
//            if (!strUserInputBarcode.matches("^\\d{5}$")) {
//                System.out.print("5文字で入力してください> ");
//                continue;
//            }
//            break;
//        }


        // ChromeDriverのパスを設定
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // WebDriverのインスタンスを作成
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // 開きたいサイトのURLを引数に指定
        driver.get("https://iaso.general.hokudai.ac.jp/iasor7/fw/FW0000/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='login-button iaso-button iaso-gradient iaso-transition']")));
        driver.findElement(By.name("userLoginId")).sendKeys("ENG1507"); // ユーザーIDを指定
        driver.findElement(By.name("password")).sendKeys("ENG1507"); // パスワードを指定
        driver.findElement(By.xpath("//*[@class='login-button iaso-button iaso-gradient iaso-transition']")).click();

        WebElement registrationReagentElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/iasor7/cm/CM0100/']")));
        registrationReagentElement.click();

        // 現在のウィンドウハンドルを取得
        String mainWindowHandle = driver.getWindowHandle();

        WebElement searchReagentElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[contains(.,'薬品検索')]")));
        searchReagentElement.click();

        // 新しいウィンドウハンドルを取得
        Set<String> allWindowHandles = driver.getWindowHandles();
        allWindowHandles.remove(mainWindowHandle);  // 現在のウィンドウハンドルを除外
        String newWindowHandle1 = allWindowHandles.iterator().next();  // 新しいウィンドウハンドルを取得

        // 新しいウィンドウに切り替え
        driver.switchTo().window(newWindowHandle1);

        // <span> 要素が表示されているか、クリックできる状態になるまで待機
        WebElement spanElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '在庫のみ')]")));
        spanElement.click();
        WebElement inputReagentElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[data-js-view='TextField::fullTextWord']")));
        inputReagentElement.sendKeys(userInputReagent);
        WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-js-view='Button::btnSearch1']")));
        searchElement.click();
        System.out.print("試薬を選んだら、> ");

        Scanner scannerWaite = new Scanner(System.in);
        System.out.print("IASOバーコードの下5桁を入力してください> ");
        String strUserInputBarcode;
        // 入力された値の長さ判定
        while (true) {
            strUserInputBarcode = scannerReagent.nextLine();
            if (!strUserInputBarcode.matches("^\\d{5}$")) {
                System.out.print("5文字で入力してください> ");
                continue;
            }
            break;
        }

//        String strNum;
//
//        // 入力された数値の大小を判定
//        while (true) {
//            strNum = scannerWaite.nextLine();
//            if (!strNum.matches("([1-9]|1[0-9]|20)")) {
//                System.out.print("20以下の自然数を入力してください> ");
//                continue;
//            }
//            break;
//        }

        driver.switchTo().window(mainWindowHandle); // mainウィンドウに切り替え

        WebElement placeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-js-view='Button::btnPlace']")));
        placeElement.click();

        allWindowHandles = driver.getWindowHandles(); // 再度すべてのウィンドウハンドルを取得
        allWindowHandles.remove(mainWindowHandle);
        String newWindowHandle2 = allWindowHandles.iterator().next(); // 新しいウィンドウハンドルを取得
        driver.switchTo().window(newWindowHandle2); // 新しいウィンドウに切り替え

//        switch (userInputInfo) {
//            case "c":
//                WebElement coldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("13865")));
//                coldElement.click();
//                break;
//            case "f":
//                WebElement freezerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("14039")));
//                freezerElement.click();
//                break;
//            case "p":
//                WebElement poisonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("12286")));
//                poisonElement.click();
//                break;
//            case "g":
                WebElement gloveBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("11922")));
                gloveBoxElement.click();
//                break;
//            case "s":
//                WebElement solventElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("13719")));
//                solventElement.click();
//                break;
//            case "0":
//                WebElement shelfElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("13425")));
//                shelfElement.click();
//                break;
//            default:
//                System.out.println("予期しないエラーが発生しました。");
//                break;
//        }

        driver.switchTo().window(mainWindowHandle); // mainウィンドウに切り替え
        WebElement enterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[contains(.,'ENTER')]")));
        enterElement.click();

        wait.until(webdriver -> {
            Set<String> newAllWindowHandles = driver.getWindowHandles();
            return newAllWindowHandles.size() > 1; // 新しいウィンドウが開かれるのを待つ
        });

        allWindowHandles = driver.getWindowHandles(); // 再度すべてのウィンドウハンドルを取得
        allWindowHandles.remove(mainWindowHandle);
        String newWindowHandle3 = allWindowHandles.iterator().next(); // 新しいウィンドウハンドルを取得
        driver.switchTo().window(newWindowHandle3); // 新しいウィンドウに切り替え

//        int num = Integer.parseInt(strNum);

//        for (int i = 0; i < num; i++) {
            int userInputBarcode = Integer.parseInt(strUserInputBarcode);
            WebElement inputBarcodeElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[data-js-view='TextField']")));
            String formatted = String.format("%05d", userInputBarcode);
            inputBarcodeElement.sendKeys("AIL000" + formatted);
            WebElement decisionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '決定')]")));
            decisionElement.click();
            WebElement OKElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-name='OK']")));
            OKElement.click();
//            if (i < num - 1) {
//                WebElement OK2Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'OK')]")));
//                OK2Element.click();
//                System.out.println("IASOバーコード：AIL000" + formatted + " 登録済 " + (i + 1) + "/" + num);
//            } else {
                WebElement cancelElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'キャンセル')]")));
                cancelElement.click();
                System.out.println("IASOバーコード：AIL000" + formatted + " 登録済 " );
//            }
//        }

        System.out.println("試薬登録が完了しました。");
        driver.quit();
    }
}