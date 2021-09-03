/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail.controller;

import com.smartict.mail.constant.exceptions.ServiceException;
import com.smartict.mail.constant.messages.EnumCrudMessages;
import com.smartict.mail.dto.MailDto;
import com.smartict.mail.dto.response.ResponseTypeEnum;
import com.smartict.mail.dto.response.RestResponse;
import com.smartict.mail.service.MailService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/sendMail")
    public ResponseEntity<RestResponse<MailDto>> sendMail(@RequestBody MailDto mail) {
        try {
            return new ResponseEntity<>(
                new RestResponse<>(
                    mailService.sendMail(mail),
                    EnumCrudMessages.READ_TITLE.getLanguageKey(),
                    EnumCrudMessages.READ_TITLE.getLanguageValue(),
                    EnumCrudMessages.READ_SUCCESS_MESSAGE.getLanguageKey(),
                    EnumCrudMessages.READ_SUCCESS_MESSAGE.getLanguageValue(),
                    ResponseTypeEnum.Success
                ),
                HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                new RestResponse<>(
                    EnumCrudMessages.READ_TITLE.getLanguageKey(),
                    EnumCrudMessages.READ_TITLE.getLanguageValue(),
                    e.getMessageLanguageKey(),
                    e.getMessage(),
                    ResponseTypeEnum.Error
                ),
                HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    @GetMapping(value = "/test")
    public ResponseEntity<RestResponse<String>> test() {
        try {
            return new ResponseEntity<>(
                new RestResponse<>(
                    "Service Is Running",
                    EnumCrudMessages.CREATE_TITLE.getLanguageKey(),
                    EnumCrudMessages.CREATE_TITLE.getLanguageValue(),
                    EnumCrudMessages.CREATE_SUCCESS_MESSAGE.getLanguageKey(),
                    EnumCrudMessages.CREATE_SUCCESS_MESSAGE.getLanguageValue(),
                    ResponseTypeEnum.Success
                ),
                HttpStatus.OK
            );
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                new RestResponse<>(
                    "Error",
                    EnumCrudMessages.CREATE_TITLE.getLanguageKey(),
                    EnumCrudMessages.CREATE_TITLE.getLanguageValue(),
                    e.getMessageLanguageKey(),
                    e.getMessage(),
                    ResponseTypeEnum.Error
                ),
                HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

}
