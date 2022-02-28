package ua.com.alevel.helper;

public class HTML {
    public static String htmlEmailTemplate(String token, Integer code){
        //Verify Account URL:
        String url = "http://127.0.0.1:8080/verify?token=" + token + "&code=" + code;
        String emailTemplate = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <!-- <link rel=\"stylesheet\" href=\"css/email.css\"> -->\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        *{\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: 'Times New Roman', Times, serif;\n" +
                "        }\n" +
                "\n" +
                "        /*Main Body Styling*/\n" +
                "        body{\n" +
                "\n" +
                "            height: 100vh;\n" +
                "            background-color:aliceblue;\n" +
                "            display:flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        /*Wrapper*/\n" +
                "        .wrapper{\n" +
                "            width: 550px;\n" +
                "            height: auto;\n" +
                "            padding: 15px;\n" +
                "            background-color: rgb(5, 1, 19);\n" +
                "            border-radius: 10px;\n" +
                "\n" +
                "        }\n" +
                "        /*Email MSG Header*/\n" +
                "        .email-msg-header\n" +
                "        {\n" +
                "\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .company-name{\n" +
                "            text-align: center;\n" +
                "            width: 100%;\n" +
                "            font-size: 32px;\n" +
                "            color: rgb(255, 255, 255);\n" +
                "            font-family:'Times New Roman', Times, serif;\n" +
                "        }\n" +
                "\n" +
                "        .welcome-text{\n" +
                "            text-align: center;\n" +
                "            width: 100%;\n" +
                "            font-size: 32px;\n" +
                "            color: white;\n" +
                "            font-family:'Times New Roman', Times, serif;\n" +
                "        }\n" +
                "\n" +
                "        /*verify-account-btn*/\n" +
                "        .verify-account-btn{\n" +
                "            padding: 12px;\n" +
                "            background-color: white;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "            color: black;\n" +
                "        }\n" +
                "        .copy-right{\n" +
                "            padding: 10px;\n" +
                "            color: white;\n" +
                "            font-size: 14px;\n" +
                "            margin: 10px 0;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<!--Wrapper Start-->\n" +
                "<div class='wrapper'>\n" +
                "    <!--Email MSG Header Start-->\n" +
                "    <h2 class='email-msg-header'>\n" +
                "\n" +
                "    </h2>\n" +
                "    <!--Email MSG Header End-->\n" +
                "\n" +
                "    <!--Company Name-->\n" +
                "    <div class='company-name'>You are welcome! <br>Iron Bank of Braavos</div>\n" +
                "    <!--Company Name-->\n" +
                "    <hr>\n" +
                "    <!--Welcome Text-->\n" +
                "    <p class='welcome-text'>\n" +
                "        Your account has been activated successfully.\n" +
                "    </p>\n" +
                "    <!--Welcome Text-->\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "\n" +
                "    <!--Verify ButtonStart-->\n" +
                "    <center><a href='"+url+"' class='verify-account-btn'role='button'>Verify account</a></center>\n" +
                "    <!--CopyRightWrapperStart-->\n" +
                "    <div class='copy-right'>\n" +
                "        &copy; Copy Right 2022. All Right Resevred.\n" +
                "    </div>\n" +
                "\n" +
                "    <!--CopyRightWrapperEnd-->\n" +
                "    <!--Verify ButtonEnd-->\n" +
                "</div>\n" +
                "<!--Wrapper End-->\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return emailTemplate;
    }
}
