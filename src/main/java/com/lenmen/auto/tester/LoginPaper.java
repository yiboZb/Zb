package com.lenmen.auto.tester;

import com.lenmen.auto.base.BasicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginPaper extends BasicTest {

    @Test
    public  void login()  {
        logger.info("打开浏览器");
        webDriver.navigate().to("http://www.baidu.com");
        //webDriver.get("www.baidu.com");

        logger.info("得到文本框位置");
        WebElement element = getElement(3000,By.id("kw"));
        logger.info("在文本框中输入内容");
        element.sendKeys("中华人民共和国");
        logger.info("点击百度一下");
        getElement(3000,By.id("su")).click();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
