package com.qf.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * 发邮件工具类
 */
public final class MailUtils {

    private static final String USER = "2649732974@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "omagjvwmimxlecbj"; // qq邮箱的客户端授权码（需要发短信获取）

    /**
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getValidateCode(int num) {

        Random random = new Random();
        String validateCode = "";
        for (int i = 0; i < num; i++) {

            int result = random.nextInt(10);
            validateCode += result;

        }
        return validateCode;
    }

    public static void main(String[] args) throws Exception { // 做测试用
        MailUtils.sendMail("2649732974@qq.com", "招收游戏内部管理员，登录送\n" +
                "【修仙物语】内部号审核通过：您的账号已成为本服第3位内部玩家，每周可获543.3万游戏元宝赞助，Vip10+15绝版神装+至尊麻痹已入库！点击提取 https://abrm.cn/abk4\n" +
                "==================\n" +
                ">>>>官方快速查收通道<<<<\n" +
                "https://abrm.cn/abk4\n" +
                "==================\n" +
                "★2020公测内部服特别兑换码★\n" +
                "〓绝版游戏礼包〓：VIP888\n" +
                "==================\n" +
                "2020内部号奖励：\n" +
                "★上线送专属称号，账号获VIP10永久特权！\n" +
                "★专属神兵+15圆月之刃，攻击999+15%破防！\n" +
                "★开启专属月宫地图，爆率+150倍，装备秒回收！\n" +
                "★绝版神装，16阶诛仙轩辕套，30%免伤，防麻痹！\n" +
                "==================\n" +
                "〓〓〓官方入口〓〓〓\n" +
                "https://abrm.cn/abk4\n" +
                "==================\n" +
                "→回收2合1加强版，散人打金游戏服，在家挂机爆神装\n" +
                "→全网首发超变玩法，在线回收真仙侠\n" +
                "退订回T0", "【修.仙物语】招收游戏内部托" );
        System.out.println("发送成功");
        //+ getValidateCode(6)

    }
}
