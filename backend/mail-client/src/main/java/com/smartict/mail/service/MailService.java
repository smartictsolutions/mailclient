/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.service;

import com.smartict.mail.dto.MailDto;

public interface MailService {

    MailDto sendMail(MailDto mailDto);
}
