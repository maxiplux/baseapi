package io.api.base.service;

public interface EmailServices {

    void sendEmail(String subject, String context);

    void executeSendEmailRuleById(String subject);


}
