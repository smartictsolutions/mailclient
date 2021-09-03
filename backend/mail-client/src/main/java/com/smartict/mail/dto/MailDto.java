/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Gönderilecek olan Mail sınıfıdır
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MailDto {
    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String body;

    private byte[] attachment = null;

}
