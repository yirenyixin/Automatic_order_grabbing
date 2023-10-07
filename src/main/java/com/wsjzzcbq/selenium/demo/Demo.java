//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;


package com.wsjzzcbq.selenium.demo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;

public class Demo extends JFrame {


    public static void main(String[] args) throws InterruptedException, ParseException {

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse("2022-07-19 10:19:00 000000000");

        //驱动地址
        String chromedriver = "C:\\Users\\yirenyixin\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriver);
        //初始化一个chrome浏览器实例
        WebDriver webDriver = new ChromeDriver();
        //最大化窗口
        webDriver.manage().window().maximize();
        //设置隐性等待时间
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //打开百度
        webDriver.get("https://login.taobao.com/");
        //扫码登录
        webDriver.findElement(By.className("iconfont icon-qrcode")).click();
        Thread.sleep(10000);



        // 输入账号 密码并登陆系统
//        webDriver.findElement(By.name("username")).sendKeys("18378110579");
//        webDriver.findElement(By.name("password")).sendKeys("000000");
//
//        // 滑动,把滑块从左端移到右端
//        // 新建Actions类
//        Actions action = new Actions(webDriver);
//
//        // 查找可拖拽元素
//        WebElement background = webDriver.findElement(By
//                .cssSelector("#verify-wrap > span.fix-tips.fixTips"));
//        //获取滑块的长度
//        org.openqa.selenium.Dimension span_background_size = background.getSize();
//
//        //获取滑块的位置
//        WebElement button = webDriver.findElement(By
//                .cssSelector("#verify-wrap > span.drag-btn.dragBtn"));
//        Point button_location = button.getLocation();
//        System.out.println("滑块的位置：" + button_location);
//
//        //计算滑动移动的目标位置,把滑块从左端移到右端
//        int x_location = button_location.x + span_background_size.width;
//        int y_location = button_location.y;
//        action.dragAndDropBy(button, x_location, y_location).perform();
//
//        // 获取登录按钮
//        WebElement loginButton = webDriver.findElement(By
//                .xpath("//*[@id=\"loginForm\"]/div[5]/button"));
//        // 点击登录
//        loginButton.click();
//        System.out.println("登录结束");



        webDriver.get("https://cart.taobao.com/cart.htm");
        while (true) {
            Date now = new Date();
            System.out.println(now);
            if(now.after(date)) {
                if (webDriver.findElement(By.id("J_SelectAll1")).isEnabled()) {
                    webDriver.findElement(By.id("J_SelectAll1")).click();
                    Thread.sleep(500);
                    webDriver.findElement(By.id("J_Go")).click();
                    Thread.sleep(800);
                    webDriver.findElement(By.linkText("提交订单")).click();
                    Thread.sleep(2000);
                    //如果支付界面不是中国大陆版
//                    webDriver.findElement(By.id("J-to-fastpay")).click();
//                    Thread.sleep(100);
                    webDriver.findElement(By.xpath("//*[@id=\"payPassword_container\"]/div")).click();
//                    Thread.sleep(500);
                    Robot robot = null;
                    try {
                        robot = new Robot();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }

                    //判断是否null
                    if(robot == null) {
                        return;//如果是空结束程序
                    }else {
                        //6位密码
                        robot.keyPress(KeyEvent.VK_1);//模拟按下
                        robot.keyRelease(KeyEvent.VK_1);//释放

                        robot.keyPress(KeyEvent.VK_1);
                        robot.keyRelease(KeyEvent.VK_1);

                        robot.keyPress(KeyEvent.VK_1);
                        robot.keyRelease(KeyEvent.VK_1);

                        robot.keyPress(KeyEvent.VK_1);
                        robot.keyRelease(KeyEvent.VK_1);

                        robot.keyPress(KeyEvent.VK_1);
                        robot.keyRelease(KeyEvent.VK_1);

                        robot.keyPress(KeyEvent.VK_1);
                        robot.keyRelease(KeyEvent.VK_1);
                    }
                    //支付
//                    webDriver.findElement(By.id("J_authSubmit")).click();
                }
            }
        }
    }

}
