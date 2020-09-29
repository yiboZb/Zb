package com.lenmen.auto.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.logging.Logger;

public class BasicTest {
    //日志
    public  static Logger logger = Logger.getLogger(String.valueOf(BasicTest.class));
    public static WebDriver webDriver = null;


    @BeforeSuite
    @Parameters({"browserType","driverPath","seleniumVersion"})
   public static void befroeSuite(String browserType,String driverPath,String seleniumVersion){
       logger.info("开始判断浏览器类型");
       String browserTypeLowerCase = browserType.toLowerCase();
       if("ie".equals(browserTypeLowerCase)){
           System.setProperty("webdriver.ie.driver",driverPath);
           DesiredCapabilities capabilities = new DesiredCapabilities();//创建一个设置对象，用来设置创建ie驱动时的各种设置
           //忽略浏览器设置为true。取消IE安全设置
           capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
           //忽略浏览器的缩放设置
           capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
           //设置一个初始化页面，防止window对象丢失
           capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"http://www.baidu.com");
           webDriver = new InternetExplorerDriver(capabilities);
       } else if ("chrome".equals(browserTypeLowerCase)){
           System.setProperty("webdriver.chrome.driver",driverPath);
           logger.info("创建谷歌浏览器的driver驱动");
           webDriver = new ChromeDriver();
       } else if ("firefox".equals(browserTypeLowerCase)){
           System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
           if ("3.x".equals(browserTypeLowerCase)){
               System.setProperty("webdriver.gecko.driver",driverPath);
           }
           webDriver = new FirefoxDriver();
       }

   }

   @AfterSuite
   public static void afterSuite(){
        webDriver.quit();
   }

    /**
     * 智能等待，等到页面包含目标元素
     * @param timeOutInSeconds 超时时间
     * @param by 定位By对象
     * @return
     */
    public static WebElement getElement(long timeOutInSeconds, final By by){
        WebDriverWait wait = new WebDriverWait(webDriver,timeOutInSeconds);
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(by);
            }
        });
        return element;
    }

    /**
     * 等待到元素可点击
     * @param timeOutInSeconds 超时时间
     * @param by 定位By对象
     * @return
     */
    public static WebElement getElementToBeClickable(long timeOutInSeconds,By by){
        WebDriverWait wait = new WebDriverWait(webDriver,timeOutInSeconds);
        return  wait.until(ExpectedConditions.elementToBeClickable(by));
    }



}
