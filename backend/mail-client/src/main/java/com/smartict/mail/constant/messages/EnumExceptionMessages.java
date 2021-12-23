/* SmartICT Bilisim A.S. (C) 2020 */
package com.smartict.mail.constant.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EnumExceptionMessages {
    NO_SUCH_USER("ExceptionMessages.NO_SUCH_USER", "Kullanıcı Bulunamdı!"),
    HAS_NO_AUTH_FOR_USER_OPERATION("ExceptionMessages.HAS_NO_AUTH_FOR_USER_OPERATION", "Bu kullanıcı için işlem yapma yetkiniz yoktur!"),
    USER_NOT_ACTIVE("ExceptionMessages.USER_NOT_ACTIVE", "Aktif kullanıcı bulunamadı!"),
    USER_ID_NOT_BE_EMPTY("ExceptionMessages.USER_ID_NOT_BE_EMPTY", "Kullanıcı ID boş olamaz!"),
    USER_NAME_PASSWORD_NOT_MATCH("ExceptionMessages.USER_NAME_PASSWORD_NOT_MATCH", "Hatalı kullanıcı adı ya da parola!"),
    USER_NOT_FOUND("ExceptionMessages.USER_NOT_FOUND", "Kullanıcı bulunamadı!"),
    USER_ROLE_INFO_NOT_READ("ExceptionMessages.USER_ROLE_INFO_NOT_READ", "Rol bilgisi okunamadı!"),
    USERNAME_NOT_INCLUDE_SPACE("ExceptionMessages.USERNAME_NOT_INCLUDE_SPACE", "Kullanıcı adı boşluk içeremez!"),

    USERCATEGORY_NOT_FOUND("ExceptionMessages.USERCATEGORY_NOT_FOUND", "Kullanıcı sınıfı bulunamadı!"),

    USER_EXCEL_IMPORT_FILE_NOT_CREATE("ExceptionMessages.USER_EXCEL_IMPORT_FILE_NOT_CREATE", "Sonuç excel dosyası yaratılamadı!"),
    USER_EXCEL_IMPORT_FILE_WRITE_ERROR("ExceptionMessages.USER_EXCEL_IMPORT_FILE_WRITE_ERROR", "Sonuçlar excel dosyasına yazılırken hata oluştu!"),
    USER_EXCEL_IMPORT_FILE_NOT_READ_USERS("ExceptionMessages.USER_EXCEL_IMPORT_FILE_NOT_READ_USERS", "Excel tablosundan kullanıcılar okunamadı!"),
    USER_EXCEL_IMPORT_FILE_UNSUPPORTED_FORMAT("ExceptionMessages.USER_EXCEL_IMPORT_FILE_UNSUPPORTED_FORMAT", "Desteklenmeyen dosya uzantısı!"),
    USER_EXCEL_TEMPLATE_NOT_FOUND("ExceptionMessages.USER_EXCEL_TEMPLATE_NOT_FOUND", "Şablon excel dosyası bulunamadı!"),
    USER_EXCEL_TEMPLATE_NOT_CREATE("ExceptionMessages.USER_EXCEL_TEMPLATE_NOT_CREATE", "Şablon excel dosyası oluşturulurken hata oluştu!"),

    OLD_PASSWORDS_NOT_MATCH("ExceptionMessages.OLD_PASSWORDS_NOT_MATCH", "Güncel parola hatalı!"),
    OLD_AND_NEW_PASSWORDS_MATCH("ExceptionMessages.OLD_AND_NEW_PASSWORDS_MATCH", "Güncel ve yeni şifre aynı olamaz!"),

    QUESTIONS_GROUP_NOT_FOUND("ExceptionMessages.QUESTIONS_GROUP_NOT_FOUND", "Soru grubu bulunamadı!"),
    QUESTION_GROUP_HAS_QUESTION("ExceptionMessages.QUESTION_GROUP_HAS_QUESTION", "Bu soru grubuna ait sorular bulunduğundan dolayı silme işlemi yapılamaz!"),

    CONTENT_GROUP_NOT_EMPTY(
            "ExceptionMessages.CONTENT_GROUP_NOT_EMPTY", "Bu içerik grubuna ait içerik veya içerik grubu bulunduğundan dolayı silme işlemi yapılamaz!"
    ),
    CONTENT_PLACE_TYPE_EMPTY("ExceptionMessages.CONTENT_PLACE_TYPE_EMPTY", "İçerik eğitim alanı tipi boş olamaz!"),
    CONTENT_NOT_FOUND("ExceptionMessages.CONTENT_NOT_FOUND", "İçerik bulunamadı!"),

    EDUCATION_NOT_FOUND("ExceptionMessages.EDUCATION_NOT_FOUND", "Eğitim bulunamadı!"),
    EDUCATION_GROUP_HAS_EDUCATION(
            "ExceptionMessages.EDUCATION_GROUP_HAS_EDUCATION", "Bu ders grubuna ait dersler bulunduğundan dolayı silme işlemi yapılamaz!"
    ),

    SUBJECT_NOT_DELETE_HAS_RELATION("ExceptionMessages.SUBJECT_NOT_EMPTY", "Bu konuya ait içerik veya konu bulunduğundan dolayı silme işlemi yapılamaz!"),
    SUBJECT_NOT_FOUND("ExceptionMessages.SUBJECT_NOT_FOUND", "Konu bulunamadı!"),

    COURSE_NOT_FOUND("ExceptionMessages.COURSE_NOT_FOUND", "Kurs bulunamadı!"),
    COURSE_NAME_MUST_UNIQUE("ExceptionMessages.COURSE_NAME_MUST_UNIQUE", "Kurs ismi daha önceden girildiği için kayıt yapılamadı!"),
    COURSE_PLANNED_START_DATE_AFTER_END_DATE(
            "ExceptionMessages.COURSE_PLANNED_START_DATE_AFTER_END_DATE", "Kursun planlanan başlangıç tarihi bitiş tarihinden büyük olamaz!"
    ),
    COURSE_PLANNED_END_DATE_BEFORE_START_DATE(
            "ExceptionMessages.COURSE_PLANNED_END_DATE_BEFORE_START_DATE", "Kursun planlanan bitiş tarihi başlangıç tarihinden küçük olamaz!"
    ),
    COURSE_ACTUAL_START_DATE_AFTER_END_DATE(
            "ExceptionMessages.COURSE_ACTUAL_START_DATE_AFTER_END_DATE", "Kursun gerçekleşen başlangıç tarihi bitiş tarihinden büyük olamaz!"
    ),
    COURSE_ACTUAL_END_DATE_BEFORE_START_DATE(
            "ExceptionMessages.COURSE_ACTUAL_END_DATE_BEFORE_START_DATE", "Kursun gerçekleşen bitiş tarihi başlangıç tarihinden küçük olamaz!"
    ),
    COURSE_NOT_FINISH_NOT_STARTED("ExceptionMessages.COURSE_NOT_FINISH_NOT_STARTED", "Kurs henüz başlamadığı için bitirilemez!"),
    COURSE_ALREADY_STARTED("ExceptionMessages.COURSE_ALREADY_STARTED", "Kurs daha önceden başlatıldığından tekrar başlatılamadı!"),
    COURSE_ALREADY_FINISHED("ExceptionMessages.COURSE_ALREADY_FINISHED", "Kurs daha önceden bitirildiğinden tekrar bitirilemedi!"),
    COURSE_NOT_DELETE_NOT_FINISHED("ExceptionMessages.COURSE_NOT_DELETE_NOT_FINISHED", "Silinmek istenen kurs sonlanmadığı için silinemez!"),
    COURSE_NOT_UPDATE_START_DATE_ALREADY_STARTED(
            "ExceptionMessages.COURSE_NOT_UPDATE_START_DATE_ALREADY_STARTED", "Kurs daha önceden başlatıldığından planlanan tarihi değiştirilemez!"
    ),

    CLASS_NOT_FOUND("ExceptionMessages.CLASS_NOT_FOUND", "Sınıf bulunamadı!"),
    CLASS_USER_NOT_MATCH("ExceptionMessages.CLASS_USER_NOT_MATCH", "Sınıfa kursiyer yetkisine sahip olmayan kullanıcı eklenemez!"),
    CLASS_USER_CLASS_NOT_FOUND("ExceptionMessages.CLASS_USER_CLASS_NOT_FOUND", "Verilen id ye karşılık sınıf bulunamadı!"),

    MAIL_NOT_SENT("ExceptionMessages.MAIL_NOT_SENT", "Mail gönderilemedi!"),
    MAIL_ATTACHMENT_NOT_SUPPORT("ExceptionMessages.MAIL_ATTACHMENT_NOT_SUPPORT", "Mail eki desteklenmeyen bir dosya türünde"),

    UNEXPECTED_ERROR_OCCURRED("ExceptionMessages.UNEXPECTED_ERROR_OCCURRED", "Beklenmeyen hata oluştu!");

    private final String languageValue;
    private final String languageKey;

    EnumExceptionMessages(String languageKey, String languageValue) {
        this.languageKey = languageKey;
        this.languageValue = languageValue;
    }

    @JsonCreator
    public static EnumExceptionMessages valueOfLanguageValue(String languageValue) {
        for (EnumExceptionMessages deger : values()) {
            if (deger.languageValue.equalsIgnoreCase(languageValue)) {
                return deger;
            }
        }
        return null;
    }

    @JsonCreator
    public static EnumExceptionMessages valueOfLanguageKey(String languageKey) {
        for (EnumExceptionMessages deger : values()) {
            if (deger.languageKey.equalsIgnoreCase(languageKey)) {
                return deger;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "EnumExceptionMessages {languageValue=" + languageValue + ", languageKey=" + languageKey + "}";
    }

}
