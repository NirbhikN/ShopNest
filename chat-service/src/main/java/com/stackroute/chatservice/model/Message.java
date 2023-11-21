package com.stackroute.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String senderEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time = new Date(System.currentTimeMillis());
    private String replymessage;
}
