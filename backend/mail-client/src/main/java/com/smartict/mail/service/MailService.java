/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.service;

import com.smartict.mail.dto.MailDto;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {

    MailDto sendMail(MailDto mailDto, MultipartFile multipartFile);
}