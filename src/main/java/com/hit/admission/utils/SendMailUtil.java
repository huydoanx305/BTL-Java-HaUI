package com.hit.admission.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author Huy Doan
 */
public class SendMailUtil {

    public static void send(String toEmail, String tenNganh) {
        final String username = "bloomi.hit@gmail.com";
        final String password = "mmxibmamsyfrbood";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "false");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS

        // Tạo đối tượng Authenticator để xác thực tài khoản email
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Tạo đối tượng Session
        Session session = Session.getInstance(properties, authenticator);
        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);
            // Thiết lập thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            // Thiết lập tiêu đề email
            message.setSubject("Thông báo chúng tuyển", "UTF-8");
            // Thiết lập nội dung email dạng HTML
            String htmlContent = htmlThongBaoChungTuyen(tenNganh);
            message.setContent(htmlContent, "text/html; charset=UTF-8");
            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    private static String htmlThongBaoChungTuyen(String tenNganh) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Thông báo chúng tuyển</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f2f2f2;\n"
                + "            padding: 20px;\n"
                + "        }\n"
                + "\n"
                + "        h1 {\n"
                + "            color: #333333;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "\n"
                + "        .notification {\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 5px;\n"
                + "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n"
                + "            padding: 20px;\n"
                + "            margin: 0 auto;\n"
                + "            max-width: 600px;\n"
                + "        }\n"
                + "\n"
                + "        p {\n"
                + "            color: #666666;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "\n"
                + "        .cta-button {\n"
                + "            display: inline-block;\n"
                + "            background-color: #4caf50;\n"
                + "            color: #ffffff;\n"
                + "            text-decoration: none;\n"
                + "            padding: 10px 20px;\n"
                + "            border-radius: 4px;\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .cta-button:hover {\n"
                + "            background-color: #45a049;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>");
        html.append("<body>\n"
                + "    <div class=\"notification\">\n"
                + "        <h1>Chúc mừng bạn đã được tuyển vào trường đại học</h1>\n"
                + "        <p>\n"
                + "            Chúng tôi xin chân thành chúc mừng bạn đã vượt qua quá trình tuyển sinh và trở thành một thành viên của trường đại học chúng tôi.\n"
                + "            Chúng tôi rất vui mừng được chào đón bạn và hy vọng rằng bạn sẽ có những trải nghiệm học tập thú vị và thành công tại trường.\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            Ngành chúng tuyển: "
                + tenNganh + "\n</br>"
                + "            Thời gian nhập học: 05/09/2023\n"
                + "        </p>\n"
                + "        <p>\n"
                + "            Hãy chuẩn bị tinh thần để bắt đầu cuộc hành trình học tập và khám phá tại trường đại học của chúng tôi.\n"
                + "            Chúng tôi tin rằng bạn sẽ tận dụng tốt cơ hội này và đạt được những thành tựu xuất sắc trong tương lai.\n"
                + "        </p>\n"
                + "        <a href=\"https://tuyensinh.haui.edu.vn/\" class=\"cta-button\">Truy cập để biết thêm thông tin chi tiết nhập học</a>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>");
        return html.toString();
    }

}
