package com.stackroute.emailservice.Model;

public class EmailRequest {
    private String to;
    private String subject;
    private String body; // Corrected variable name

    // Getters and setters

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() { // Corrected method name
        return body; // Corrected variable name
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) { // Corrected parameter name
        this.body = body; // Corrected variable name
    }

    public EmailRequest(String to, String subject, String body) { // Corrected parameter name
        this.to = to;
        this.subject = subject;
        this.body = body; // Corrected variable name
    }

    public EmailRequest() {
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' + // Corrected variable name
                '}';
    }
}


