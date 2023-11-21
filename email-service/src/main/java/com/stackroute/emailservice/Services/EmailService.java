package com.stackroute.emailservice.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String to, String subject, String text) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(fromEmail);
            String htmlContent = "<!DOCTYPE html>\n" +
                    "\n" +
                    "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                    "<head>\n" +
                    "<title></title>\n" +
                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                    "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                    "<link href=\"https://fonts.googleapis.com/css?family=Oswald\" rel=\"stylesheet\" type=\"text/css\"/><!--<![endif]-->\n" +
                    "<style>\n" +
                    "\t\t* {\n" +
                    "\t\t\tbox-sizing: border-box;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tbody {\n" +
                    "\t\t\tmargin: 0;\n" +
                    "\t\t\tpadding: 0;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\ta[x-apple-data-detectors] {\n" +
                    "\t\t\tcolor: inherit !important;\n" +
                    "\t\t\ttext-decoration: inherit !important;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t#MessageViewBody a {\n" +
                    "\t\t\tcolor: inherit;\n" +
                    "\t\t\ttext-decoration: none;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tp {\n" +
                    "\t\t\tline-height: inherit\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.desktop_hide,\n" +
                    "\t\t.desktop_hide table {\n" +
                    "\t\t\tmso-hide: all;\n" +
                    "\t\t\tdisplay: none;\n" +
                    "\t\t\tmax-height: 0px;\n" +
                    "\t\t\toverflow: hidden;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.image_block img+div {\n" +
                    "\t\t\tdisplay: none;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t@media (max-width:670px) {\n" +
                    "\n" +
                    "\t\t\t.desktop_hide table.icons-inner,\n" +
                    "\t\t\t.social_block.desktop_hide .social-table {\n" +
                    "\t\t\t\tdisplay: inline-block !important;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.icons-inner {\n" +
                    "\t\t\t\ttext-align: center;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.icons-inner td {\n" +
                    "\t\t\t\tmargin: 0 auto;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.mobile_hide {\n" +
                    "\t\t\t\tdisplay: none;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.row-content {\n" +
                    "\t\t\t\twidth: 100% !important;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.stack .column {\n" +
                    "\t\t\t\twidth: 100%;\n" +
                    "\t\t\t\tdisplay: block;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.mobile_hide {\n" +
                    "\t\t\t\tmin-height: 0;\n" +
                    "\t\t\t\tmax-height: 0;\n" +
                    "\t\t\t\tmax-width: 0;\n" +
                    "\t\t\t\toverflow: hidden;\n" +
                    "\t\t\t\tfont-size: 0px;\n" +
                    "\t\t\t}\n" +
                    "\n" +
                    "\t\t\t.desktop_hide,\n" +
                    "\t\t\t.desktop_hide table {\n" +
                    "\t\t\t\tdisplay: table !important;\n" +
                    "\t\t\t\tmax-height: none !important;\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t</style>\n" +
                    "</head>\n" +
                    "<body style=\"background-color: #fff; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #07071f;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #060e21; color: #000; background-image: url('https://drive.google.com/uc?export=view&id=1FovEmyyUNZ5frC7ArR99su1IwTOp1DJ5'); background-repeat: no-repeat; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-top:10px;\">\n" +
                    "<div style=\"color:#393d47;font-family:'Oswald',Arial,'Helvetica Neue',Helvetica,sans-serif;font-size:11px;letter-spacing:9px;line-height:120%;text-align:center;mso-line-height-alt:13.2px;\">\n" +
                    "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color:#ffffff;\"><strong> WWW.ARKABOUTIQUE.COM<br/></strong></span></p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<div class=\"spacer_block block-2\" style=\"height:75px;line-height:75px;font-size:1px;\"> </div>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                    "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><a href=\"http://example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"><img alt=\"Arka Boutique Logo\" src=\"https://drive.google.com/uc?export=view&id=1MBJNDVKbEnZYsaIkyS8KY2bPweJtSa6T\" style=\"display: block; height: auto; border: 0; max-width: 130px; width: 100%;\" title=\"Arka Boutique Logo\" width=\"130\"/></a></div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<div class=\"spacer_block block-4\" style=\"height:155px;line-height:155px;font-size:1px;\"> </div>\n" +
                    "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"divider_block block-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\">\n" +
                    "<div align=\"center\" class=\"alignment\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"15%\">\n" +
                    "<tr>\n" +
                    "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px dashed #7DE5E5;\"><span> </span></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"text-align:center;width:100%;\">\n" +
                    "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Oswald', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 86px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0;\"><strong>THANK YOU FOR YOUR ORDER </strong></h1>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-bottom:20px;padding-left:35px;padding-right:35px;padding-top:10px;\">\n" +
                    "<div style=\"color:#393d47;font-family:'Helvetica Neue',Helvetica,Arial,sans-serif;font-size:17px;letter-spacing:0px;line-height:150%;text-align:center;mso-line-height-alt:25.5px;\">\n" +
                    "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color:#ffffff;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut fermentum libero purus, quis luctus ex vestibulum vel. Integer velit dolor.</span></p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<div class=\"spacer_block block-8 mobile_hide\" style=\"height:70px;line-height:70px;font-size:1px;\"> </div>\n" +
                    "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"divider_block block-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\">\n" +
                    "<div align=\"center\" class=\"alignment\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"5%\">\n" +
                    "<tr>\n" +
                    "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px dashed #7DE5E5;\"><span> </span></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #07071f;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #060e21; color: #000; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<div class=\"spacer_block block-1\" style=\"height:20px;line-height:20px;font-size:1px;\"> </div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #07071f;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #060e21; color: #000; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<div class=\"spacer_block block-1\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #07071f;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #060e21; color: #000; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"divider_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\">\n" +
                    "<div align=\"center\" class=\"alignment\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"90%\">\n" +
                    "<tr>\n" +
                    "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px dashed #B23AB6;\"><span> </span></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:35px;padding-right:40px;padding-top:10px;\">\n" +
                    "<div style=\"color:#393d47;font-family:'Helvetica Neue',Helvetica,Arial,sans-serif;font-size:17px;letter-spacing:0px;line-height:150%;text-align:center;mso-line-height-alt:25.5px;\">\n" +
                    "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color:#ffffff;\">Please note that the payment is non-refundable in the case of cancellation. Lorem ipsum dolor sit amet, consectetur adipisicing elit.</span></p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"button_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\">\n" +
                    "<div align=\"center\" class=\"alignment\"><!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://example.com\" style=\"height:53px;width:139px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"0.75pt\" strokecolor=\"#b23ab6\" fillcolor=\"#b23ab6\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]--><a href=\"http://example.com\" style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#b23ab6;border-radius:0px;width:auto;border-top:1px solid transparent;font-weight:undefined;border-right:1px solid transparent;border-bottom:1px solid transparent;border-left:1px solid transparent;padding-top:10px;padding-bottom:10px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:30px;padding-right:30px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"word-break: break-word; line-height: 32px;\">View order</span></span></a><!--[if mso]></center></v:textbox></v:roundrect><![endif]--></div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<div class=\"spacer_block block-4\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #07071f;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #060e21; color: #000; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 15px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"width:100%;\">\n" +
                    "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><a href=\"http://example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"><img alt=\"Arka Boutique Logo\" src=\"https://drive.google.com/uc?export=view&id=1N-SA-X3k92ED70G4qiiFn_N5Bm-O3zJq\" style=\"display: block; height: auto; border: 0; max-width: 89px; width: 100%;\" title=\"Arka Boutique Logo\" width=\"89\"/></a></div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;padding-top:15px;text-align:center;\">\n" +
                    "<div align=\"center\" class=\"alignment\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social-table\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;\" width=\"144px\">\n" +
                    "<tr>\n" +
                    "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.facebook.com\" target=\"_blank\"><img alt=\"Facebook\" height=\"32\" src=\"https://drive.google.com/uc?export=view&id=1V22AoIJOkT8rwtxj029-JQDU6dCMUGWG\" style=\"display: block; height: auto; border: 0;\" title=\"facebook\" width=\"32\"/></a></td>\n" +
                    "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.twitter.com\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"https://drive.google.com/uc?export=view&id=1G7_RaGG-iKq2HaqfgplyD_XHXDaKmzcs\" style=\"display: block; height: auto; border: 0;\" title=\"twitter\" width=\"32\"/></a></td>\n" +
                    "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.linkedin.com\" target=\"_blank\"><img alt=\"Linkedin\" height=\"32\" src=\"https://drive.google.com/uc?export=view&id=1JnFd7ZXnt0ikdYvPVjKdhPyL33p_RUcE\" style=\"display: block; height: auto; border: 0;\" title=\"linkedin\" width=\"32\"/></a></td>\n" +
                    "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.instagram.com\" target=\"_blank\"><img alt=\"Instagram\" height=\"32\" src=\"https://drive.google.com/uc?export=view&id=1FehbHlin83XgQeVgbIa7jeY5nOfaUqe3\" style=\"display: block; height: auto; border: 0;\" title=\"instagram\" width=\"32\"/></a></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;\">\n" +
                    "<div style=\"color:#393d47;font-family:'Oswald',Arial,'Helvetica Neue',Helvetica,sans-serif;font-size:14px;line-height:120%;text-align:center;mso-line-height-alt:16.8px;\">\n" +
                    "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color:#ffffff;\">Changed your mind? <a href=\"http://www.example.com\" rel=\"noopener\" style=\"text-decoration: underline; color: #ffffff;\" target=\"_blank\">Unsubscribe</a></span></p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:5px;\">\n" +
                    "<div style=\"color:#393d47;font-family:'Oswald',Arial,'Helvetica Neue',Helvetica,sans-serif;font-size:14px;line-height:120%;text-align:center;mso-line-height-alt:16.8px;\">\n" +
                    "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color:#7de5e5;\">Copyright ©. All Rights Reserved.</span></p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000; width: 650px; margin: 0 auto;\" width=\"650\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                    "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                    "<tr>\n" +
                    "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                    "<!--[if !vml]><!-->\n" +
                    "<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\"><!--<![endif]-->\n" +
                    "<tr>\n" +
                    "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"http://designedwithbeefree.com/\" style=\"text-decoration: none;\" target=\"_blank\"><img align=\"center\" alt=\"Beefree Logo\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=view&id=1_KWbpSufSUWT0FzrqHcxVERxWYG4OkM2\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\" width=\"34\"/></a></td>\n" +
                    "<td style=\"font-family: 'Inter', sans-serif; font-size: 15px; color: #1e0e4b; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"http://designedwithbeefree.com/\" style=\"color: #1e0e4b; text-decoration: none;\" target=\"_blank\">Designed with Beefree</a></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table><!-- End -->\n" +
                    "</body>\n" +
                    "</html>";

            message.setContent(htmlContent, "text/html");
            mailSender.send(message);

            return "Mail Sent...";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


