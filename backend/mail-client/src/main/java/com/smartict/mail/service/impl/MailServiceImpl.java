/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.service.impl;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

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
            // mailSender.setHost(System.getenv("MAIL_" + "host".toUpperCase()));
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

    public MailDto sendMail(MailDto mailDto) {
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

            if (!Objects.isNull(mailDto.getAttachment())) {
                ByteArrayDataSource dSource = new ByteArrayDataSource(mailDto.getAttachment(), "image/*");
                message.addAttachment("AttachedFile.png", dSource);
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
