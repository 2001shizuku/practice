package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class AutoVoting {
    public static void main(String[] args) {
        // ChromeDriverのパスを設定
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // WebDriverのインスタンスを作成
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // 開きたいサイトのURLを引数に指定
        driver.get("https://www.sbisec.co.jp/ETGate/?_ControlID=WPLETmgR001Control&_PageID=WPLETmgR001Mdtl20&_DataStoreID=DSWPLETmgR001Control&_ActionID=DefaultAID&burl=iris_indexDetail&cat1=market&cat2=index&dir=tl1-idxdtl%7Ctl2-JNIc1%7Ctl5-jpn&file=index.html&getFlg=on");
        WebElement currentValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idxdtlPrice\"]/em")));
        // 日経平均先物の現在値を取得
        String strCurrentValue = currentValue.getText();
        System.out.println("日経平均先物　現在値：" + strCurrentValue);
        String noCommaCurrentValue = strCurrentValue.substring(0,2) + strCurrentValue.substring(3,6);

        Random rand = new Random();
        int randomNum = rand.nextInt(200) - 100;
        int intCurrentValue = Integer.parseInt(noCommaCurrentValue);
        int votingValue = randomNum + intCurrentValue;
        String strVotingValue = Integer.toString(votingValue);
        int randomValue = rand.nextInt(100);
        String strRandomValue = String.format("%02d", randomValue);

        ((JavascriptExecutor) driver).executeScript("window.open()");
        // 現在のウィンドウハンドルを保存
        String originalWindow = driver.getWindowHandle();

        // 全てのウィンドウハンドルを取得
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);  // 新しいウィンドウに切り替え
                break;
            }
        }

        // 新しいタブでURLを開く
        driver.get("https://shi2025.market-price-forecast.com/login.php");

        WebElement id = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"accountid\"]")));
        id.sendKeys(""); // enter your mail adress
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]")));
        password.sendKeys(""); //enter your password
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login\"]")));
        loginButton.click();

        // ログイン後画面切り替え
        WebElement topButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"frame\"]/div[2]/div[1]/div/a[1]")));
        topButton.click();
        WebElement votingValueYen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"forecast\"]/p[2]/input[1]")));
        votingValueYen.sendKeys(strVotingValue);
        WebElement votingValueSen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"forecast\"]/p[2]/input[2]")));
        votingValueSen.sendKeys(strRandomValue);
        WebElement vote = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"forecast\"]/p[2]/input[3]")));
        vote.click();

        System.out.println("投票値:" + strVotingValue + "円" + strRandomValue +"銭　乱数:" + randomNum);
    }
}