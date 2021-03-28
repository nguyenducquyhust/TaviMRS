package com.tavi.tavi_mrs.service.khac;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.MessagingException;
import java.util.List;

public interface MailService {

    boolean sendMail(String[] userMail, String header, String content);

    boolean sendMailWithAttachment(String[] listMail, String header, String content,  List<String> attachFile) throws MessagingException;

    boolean sendHtmlMail(String[] userMail, String header, String content);
}
