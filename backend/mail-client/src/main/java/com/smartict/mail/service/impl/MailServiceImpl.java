/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.service.impl;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.smartict.mail.constant.enums.EnumAttachmentType;
import com.smartict.mail.constant.exceptions.ServiceException;
import com.smartict.mail.constant.messages.EnumExceptionMessages;
import com.smartict.mail.dto.MailDto;
import com.smartict.mail.service.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailServiceImpl implements MailService {
    /**
     * Loglayıcı
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final String UTF_8 = "UTF-8";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = getMailSender(javaMailSender);
    }

    private JavaMailSender getMailSender(JavaMailSender javaMailSender) {
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
        Boolean isSourceFromEnvVar = true;

        if (!Objects.isNull(System.getenv("MAIL_" + "host".toUpperCase())))
            mailSender.setHost(System.getenv("MAIL_" + "host".toUpperCase()));
        if (!Objects.isNull(System.getenv("MAIL_" + "username".toUpperCase())))
            mailSender.setUsername(System.getenv("MAIL_" + "username".toUpperCase()));
        if (!Objects.isNull(System.getenv("MAIL_" + "password".toUpperCase())))
            mailSender.setPassword(System.getenv("MAIL_" + "password".toUpperCase()));
        if (!Objects.isNull(System.getenv("MAIL_" + "port".toUpperCase())))
            mailSender.setPort(Integer.parseInt(System.getenv("MAIL_" + "port".toUpperCase())));
        if (!Objects.isNull(System.getenv("MAIL_" + "protocol".toUpperCase())))
            mailSender.setProtocol(System.getenv("MAIL_" + "protocol".toUpperCase()));

        mailSender.setJavaMailProperties(configureJavaMailProp(mailSender));

        return mailSender;
    }

    private Properties configureJavaMailProp(JavaMailSenderImpl mailSender) {
        Properties mailProperties = mailSender.getJavaMailProperties();

        readMailConfigValue(mailProperties, "mail.smtp.auth", "MAIL_AUTH");
        readMailConfigValue(mailProperties, "mail.smtp.starttls.enable", "MAIL_START_TLS_ENABLE");
        readMailConfigValue(mailProperties, "mail.smtp.ssl.trust", "MAIL_SSL_TRUST");

        return mailProperties;
    }

    private void readMailConfigValue(Properties mailProperties, String keyProp, String keyConfig) {
        String value = System.getenv("MAIL_" + keyConfig.toUpperCase());
        if (!Objects.isNull(value)) {
            mailProperties.setProperty(keyProp, value);
        }
    }

    public MailDto sendMail(MailDto mailDto, MultipartFile multipartFile) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, UTF_8);
            message.setTo(mailDto.getTo().split(","));
            message.setText(mailDto.getBody());
            message.setSubject(mailDto.getSubject());

            if (mailDto.getCc() != null && mailDto.getCc().length() > 0) {
                message.setCc(mailDto.getCc().split(","));
            }

            if (mailDto.getBcc() != null && mailDto.getBcc().length() > 0) {
                message.setBcc(mailDto.getBcc().split(","));
            }

            if (Objects.nonNull(multipartFile)) {
                String fileName = multipartFile.getOriginalFilename();
                EnumAttachmentType attachmentType = EnumAttachmentType.valueOfFileNameExtension(fileName);

                switch (attachmentType) {
                    case PDF_ATTACHMENT: {
                        ByteArrayDataSource dSource = new ByteArrayDataSource(multipartFile.getBytes(), "application/pdf");
                        message.addAttachment(fileName, dSource);
                        break;
                    }
                    case IMAGE_ATTACHMENT: {
                        ByteArrayDataSource dSource = new ByteArrayDataSource(multipartFile.getBytes(), "image/*");
                        message.addAttachment(fileName, dSource);
                        break;
                    }
                    case EXCEL_ATTACHMENT: {
                        ByteArrayDataSource dSource = new ByteArrayDataSource(multipartFile.getBytes(), "application/vnd.ms-excel");
                        message.addAttachment(fileName, dSource);
                        break;
                    }
                    case TXT_ATTACHMENT: {
                        ByteArrayDataSource dSource = new ByteArrayDataSource(multipartFile.getBytes(), "text/plain;charset=UTF-8");
                        message.addAttachment(fileName, dSource);
                        break;
                    }
                    default:
                        throw new ServiceException(
                            EnumExceptionMessages.MAIL_ATTACHMENT_NOT_SUPPORT.getLanguageKey(),
                            EnumExceptionMessages.MAIL_ATTACHMENT_NOT_SUPPORT.getLanguageValue()
                        );
                }

            }

            javaMailSender.send(mimeMessage);
            return mailDto;

        } catch (Exception e) {
            throw new ServiceException(
                EnumExceptionMessages.MAIL_NOT_SENT.getLanguageKey(),
                EnumExceptionMessages.MAIL_NOT_SENT.getLanguageValue() + "\nDetails : " + e.getCause() + "\n" + e.getCause().getMessage()
            );
        }
    }

}