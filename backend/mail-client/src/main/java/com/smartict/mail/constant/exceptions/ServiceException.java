/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.constant.exceptions;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private Boolean warning;

    private final String message;
    private final String messageLanguageKey;

    public ServiceException(String messageLanguageKey, String message) {
        super(message);
        this.warning = Boolean.FALSE;
        this.messageLanguageKey = messageLanguageKey;
        this.message = message;
    }

    public ServiceException(String messageLanguageKey, String message, boolean warning) {
        super(message);
        this.warning = warning;
        this.messageLanguageKey = messageLanguageKey;
        this.message = message;
    }

}