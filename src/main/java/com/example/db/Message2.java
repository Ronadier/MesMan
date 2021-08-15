package com.example.db;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSchemaType;
import java.util.Date;


// Это всё потому что нельзя в сгенеренных файлах добавить аннотации для работы с ДБ
@Entity
@Table(name = "messages")
public class Message2 {
    @Column
    String content;
    @Id
    @Column
    Integer id;
    @XmlSchemaType(name = "dateTime")
    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date sendTime;
    @Column
    String sender;

    public Message2() {
    }

    public Message2(String content, Integer id, Date sendTime, String sender) {
        this.content = content;
        this.id = id;
        this.sendTime = sendTime;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message2{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", sendTime=" + sendTime +
                ", sender='" + sender + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
