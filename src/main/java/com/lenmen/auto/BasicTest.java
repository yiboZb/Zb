package com.lenmen.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Logger;

public class BasicTest {
    //日志
    public  static Logger logger = Logger.getLogger(String.valueOf(BasicTest.class));
    public static WebDriver webDriver = null;

   public static void befroeSuite(String browserType,String driverPath,String seleniumVersion){
       logger.info("开始判断浏览器类型");
       String browserTypeLowerCase = browserType.toLowerCase();
       if("ie".equals(browserTypeLowerCase)){
           System.setProperty("webdriver.ie.driver",driverPath);
           DesiredCapabilities capabilities = new DesiredCapabilities();//创建一个设置对象，用来设置创建ie驱动时的各种设置
           //忽略浏览器设置为true。取消IE安全设置
           capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
           //忽略浏览器的缩放设置
           //
       }

   }



}
