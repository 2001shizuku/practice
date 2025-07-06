package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.List;

public class WebDriverExample {
    private WebDriver driver;  // WebDriverをクラスフィールドとして保持
    private WebDriverWait wait;

    public void openIASO() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();  // 1回だけインスタンス化
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://iaso.general.hokudai.ac.jp/iasor7/fw/FW0000/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='login-button iaso-button iaso-gradient iaso-transition']")));
        driver.findElement(By.name("userLoginId")).sendKeys("ENG1507"); // ユーザーIDを指定
        driver.findElement(By.name("password")).sendKeys("ENG1507"); // パスワードを指定
        driver.findElement(By.xpath("//*[@class='login-button iaso-button iaso-gradient iaso-transition']")).click();
        WebElement requestWaste = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '廃液処理')]")));
        requestWaste.click();
    }

    public void Registration(List<String> list, String type) {
        for (String line : list) {
            WebElement wasteInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/iasor7/cm/CM0700/']")));
            wasteInput.click();

            String mainWindowHandle = driver.getWindowHandle(); // 現在のウィンドハンドルを取得
            Set<String> allWindowHandles = driver.getWindowHandles();
            allWindowHandles.remove(mainWindowHandle);  // 現在のウィンドウハンドルを除外
            String selectKindWindowHandle = allWindowHandles.iterator().next();  // 新しいウィンドウハンドルを取得

            if ("Burnable".equals(type)) {
                processBurnable(selectKindWindowHandle, mainWindowHandle, line);
            } else if ("Halogen".equals(type)) {
                processHalogen(selectKindWindowHandle, mainWindowHandle, line);
            }

            WebElement OKElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'OK')]")));
            OKElement.click();
            WebElement OK2Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'OK')]")));
            OK2Element.click();

            allWindowHandles = driver.getWindowHandles(); // 再度すべてのウィンドウハンドルを取得
            allWindowHandles.remove(mainWindowHandle);
            String manifestWindowHandle = allWindowHandles.iterator().next(); // 新しいウィンドウハンドルを取得
            driver.switchTo().window(manifestWindowHandle); // 新しいウィンドウに切り替え

            WebElement requestElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '依頼')]")));
            requestElement.click();
            WebElement OK3Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'OK')]")));
            OK3Element.click();
            WebElement OK4Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'OK')]")));
            OK4Element.click();
            driver.close();
            driver.switchTo().window(mainWindowHandle);

            if ("Burnable".equals(type)) {
                System.out.println("登録済み　通常廃液：" + line);
            } else if ("Halogen".equals(type)) {
                System.out.println("登録済み　ハロゲン廃液：" + line);
            }
        }
    }

    public void processBurnable(String selectKindWindowHandle, String mainWindowHandle, String line) {
        driver.switchTo().window(selectKindWindowHandle);
        WebElement burnable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("k3_anchor")));
        burnable.click();

        driver.switchTo().window(mainWindowHandle);
        WebElement Enter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'ENTER')]")));
        driver.findElement(By.id("61")).sendKeys("3"); // 水：3
        driver.findElement(By.id("47")).sendKeys("3"); // アセトン：3
        driver.findElement(By.id("49")).sendKeys("2"); // ヘキサン：2
        driver.findElement(By.id("50")).sendKeys("2"); // 酢エチ：2
        driver.findElement(By.name("ph")).sendKeys("7.0"); // pH：7
        driver.findElement(By.name("remarks")).sendKeys(line); // 備考：廃液タンク
        Enter.click();
    }

    public void processHalogen(String selectKindWindowHandle, String mainWindowHandle, String line) {
        driver.switchTo().window(selectKindWindowHandle);
        WebElement halogen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("k2_anchor")));
        halogen.click();

        driver.switchTo().window(mainWindowHandle);
        WebElement Enter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'ENTER')]")));
        driver.findElement(By.id("33")).sendKeys("2"); // 水：2
        driver.findElement(By.id("22")).sendKeys("2"); // アセトン：2
        driver.findElement(By.id("24")).sendKeys("1"); // ヘキサン：1
        driver.findElement(By.id("25")).sendKeys("1"); // 酢エチ：1
        driver.findElement(By.id("17")).sendKeys("2"); // クロホ：2
        driver.findElement(By.id("21")).sendKeys("2"); // ジクロロ：2
        driver.findElement(By.name("ph")).sendKeys("7.0"); // pH：7
        driver.findElement(By.name("remarks")).sendKeys(line); // 備考：廃液タンク
        Enter.click();
    }



}
