package com.wsjzzcbq.selenium.demo;

import facebook4j.internal.http.BASE64Encoder;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.opencv.core.Core.minMaxLoc;
import static org.opencv.core.Core.normalize;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.*;


public class text extends JFrame implements ActionListener {
    JButton start;
    JTextField text1, text2, text3;
    JSpinner year;

    text() {
        JFrame f1 = new JFrame();
        f1.setTitle("抢购");
        f1.setBounds(400, 200, 400, 270);
        f1.setResizable(false);
        f1.invalidate(); // 保证组件是有效的布局
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 实例化各组件
        Container con = f1.getContentPane(); //生成一个容器
        con.setLayout(new GridLayout(7, 1));  //设置容器布局
//        JLabel welcome = new JLabel("欢迎进入本系统");
        JLabel usname = new JLabel();
        JLabel paswd = new JLabel();
        JLabel pay_paswd = new JLabel();
        text1 = new JTextField(12);
        text2 = new JPasswordField(12);
        text3 = new JPasswordField(12);
        start = new JButton("开始抢购");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println(text1.getText()+"1");
                if (e.getSource() == start) {
                    if (!text1.getText().equals("") && !text2.getText().equals("") && !text3.getText().equals("")) {
                        try {
                            Rush_purchase();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请检查是否输入");
                    }
                }
            }
        });

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        //选择时间
        SpinnerDateModel model = new SpinnerDateModel();
        //获得JSPinner对象
        year = new JSpinner(model);
        year.setValue(new Date());
        //设置时间格式
        JSpinner.DateEditor editor = new JSpinner.DateEditor(year,
                "yyyy-MM-dd HH:mm:ss");
        year.setEditor(editor);
        year.setBounds(34, 67, 50, 22);

        con.add(p1);
        text1.setText("17877981572");
        text2.setText("1342579711a");
        text3.setText("1");
        usname.setText("账号：");
        paswd.setText("密码：");
        pay_paswd.setText("支付密码：");
        p2.add(usname);
        p2.add(text1);
        con.add(p2);
        p3.add(paswd);
        p3.add(text2);
        con.add(p3);
        p4.add(pay_paswd);
        p4.add(text3);
        con.add(p4);
        con.add(year);
        p5.add(start);
        con.add(p5);
        f1.setVisible(true);

    }

    public void Rush_purchase() throws Exception {
        System.out.println(year.getValue());
//        System.out.println(text1.getText());
        System.out.println(text2.getText());
//    System.out.println(text3.getText());
        SimpleDateFormat sim1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s1 = String.valueOf(year.getValue());
        String s2 = null;
        try {
            Date date = sim1.parse(s1);
            s2 = sim2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s2);

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
        webDriver.get("https://h5.zycn.vip/pages/login/login");
        //登录
        webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[2]/uni-view[1]/uni-view/uni-view[1]/uni-view/uni-view/uni-view/uni-view/uni-view/uni-view/uni-view[2]/uni-input/div/input")).sendKeys(text1.getText());
        webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[2]/uni-view[1]/uni-view/uni-view[2]/uni-view/uni-view/uni-view/uni-view/uni-view/uni-view/uni-view[1]/uni-input/div/input")).sendKeys(text2.getText());
        webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[4]/uni-view[1]/uni-view/uni-view/uni-view/uni-text/span")).click();
        webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[2]/uni-view[2]")).click();
        //滑动验证码
        WebElement img1=webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/img[1]"));
        WebElement img2=webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/img[2]"));
        Thread.sleep(2500);
        String url1= img1.getAttribute("src");
        String url2= img2.getAttribute("src");
        System.out.println(url1);
        downloadA(url1);
        downloadB(url2);
        move(webDriver);


//    JOptionPane.showMessageDialog(null, "登录结束");
        System.out.println("登录结束");
        while (true) {
            Date now = new Date();
            if (now.after(date)) {
                webDriver.navigate().refresh();
                //购买
                Thread.sleep(10000);
//                webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[6]/uni-view/uni-view[2]/uni-view[2]/uni-view/uni-view[1]")).click();
//                Thread.sleep(500);
//                webDriver.findElement(By.xpath("/html/body/uni-app/uni-page/uni-page-wrapper/uni-page-body/uni-view/uni-view[2]/uni-view[3]/uni-view")).click();
//                Thread.sleep(500);
//                webDriver.findElement(By.linkText("确认支付")).click();
//                Thread.sleep(500);
                break;
            }
        }
        //
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (text1.getText() != null && text2.getText() != null && text3 != null) {
                JOptionPane.showMessageDialog(null, "登录成功");
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误");
            }
        }
    }

    public static void main(String[] args) {
        text ha = new text();
    }
    public static void downloadA(String urlString) throws Exception {

        // 构造URL

        URL url = new URL(urlString);

        // 打开连接

        URLConnection con = url.openConnection();

        // 输入流

        InputStream is = con.getInputStream();

        // 1K的数据缓冲

        byte[] bs = new byte[1024];

        // 读取到的数据长度

        int len;

        // 输出的文件流

        String filename = "D:\\1.jpg";  //下载路径及下载图片名称

        File file = new File(filename);
        //判断文件存不存在
        if(!file.exists()){
            System.out.println("删除文件失败："+filename+"不存在！");
        }else{
            //判断这是不是一个文件，ps：有可能是文件夹
            if(file.isFile()){
                file.delete();
            }
        }
        FileOutputStream os = new FileOutputStream(file, true);

        // 开始读取

        while ((len = is.read(bs)) != -1) {

            os.write(bs, 0, len);

        }


        // 完毕，关闭所有链接

        os.close();

        is.close();

    }
    public static void downloadB(String urlString) throws Exception {

        // 构造URL

        URL url = new URL(urlString);

        // 打开连接

        URLConnection con = url.openConnection();

        // 输入流

        InputStream is = con.getInputStream();

        // 1K的数据缓冲

        byte[] bs = new byte[1024];

        // 读取到的数据长度

        int len;

        // 输出的文件流

        String filename = "D:\\2.jpg";  //下载路径及下载图片名称

        File file = new File(filename);
        //判断文件存不存在
        if(!file.exists()){
            System.out.println("删除文件失败："+filename+"不存在！");
        }else{
            //判断这是不是一个文件，ps：有可能是文件夹
            if(file.isFile()){
                file.delete();
            }
        }
        FileOutputStream os = new FileOutputStream(file, true);

        // 开始读取

        while ((len = is.read(bs)) != -1) {

            os.write(bs, 0, len);

        }


        // 完毕，关闭所有链接

        os.close();

        is.close();

    }
//滑动验证码
public void move(WebDriver webDriver){
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    Mat g_tem = imread("D:\\2.jpg");
    Mat g_src = imread("D:\\1.jpg");

    int result_rows = g_src.rows() - g_tem.rows() + 1;
    int result_cols = g_src.cols() - g_tem.cols() + 1;
    Mat g_result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
    matchTemplate(g_src, g_tem, g_result, TM_CCORR_NORMED); // 归一化平方差匹配法
    // Imgproc.matchTemplate(g_src, g_tem, g_result,
    // Imgproc.TM_CCOEFF_NORMED); // 归一化相关系数匹配法

    // Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCOEFF);
    // //
    // 相关系数匹配法：1表示完美的匹配；-1表示最差的匹配。

    // Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCORR); //
    // 相关匹配法

    // Imgproc.matchTemplate(g_src, g_tem, g_result,Imgproc.TM_SQDIFF); //
//         平方差匹配法：该方法采用平方差来进行匹配；最好的匹配值为0；匹配越差，匹配值越大。

//         Imgproc.matchTemplate(g_src, g_tem,g_result,Imgproc.TM_CCORR_NORMED);
    // // 归一化相关匹配法
    Core.normalize(g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
//        Point matchLocation = new Point();
//        Core.MinMaxLocResult mmlr = Core.minMaxLoc(g_result);

    Point matchLocation=Core.minMaxLoc(g_result).maxLoc;
//        matchLocation = mmlr.maxLoc; // 此处使用maxLoc还是minLoc取决于使用的匹配算法
    Imgproc.rectangle(g_src, matchLocation,
            new Point( matchLocation.x + g_tem.cols(),  matchLocation.y + g_tem.rows()),
            new Scalar(0, 0, 0, 0));
    System.out.println(matchLocation.x);
    int x= (int) matchLocation.x;
    WebElement slider=webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div/div[2]/div[2]"));
    Actions action = new Actions(webDriver);
    action.clickAndHold(slider).perform();
    action.dragAndDropBy(slider, x+11, 0).perform();
}
}