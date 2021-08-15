package com.example.db;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import service.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class MesManServiceImpl implements MessageManagerService {

    private final DBRepository repository;

    public MesManServiceImpl(DBRepository repository) {
        this.repository = repository;
    }


    @Override
    public GetMessagesBySenderResponse getMessagesBySender(GetMessagesBySender parameters) {
        GetMessagesBySenderResponse response = new GetMessagesBySenderResponse();
        List<Message2> m2 = repository.getMessagesBySender(parameters.getSender());
        List<Message> m1 = new ArrayList<>();
        //превращаем ответ из БД в ответ для веб-сервиса
        for (Message2 mn2 : m2) {
            m1.add(convertMessage(mn2));
        }
        response.getMessage().addAll(m1);
        return response;
    }

    @Override
    public GetMessagesByDateResponse getMessagesByDate(GetMessagesByDate parameters) {
        GetMessagesByDateResponse response = new GetMessagesByDateResponse();
        System.out.println(parameters.getSendTime().toGregorianCalendar().getTime());
        java.sql.Date date = new java.sql.Date(parameters.getSendTime().toGregorianCalendar().getTimeInMillis());
        //превращаем ответ из БД в ответ для веб-сервиса
        List<Message2> m2 = repository.getMessagesByDate(date);
        List<Message> m1 = new ArrayList<>();
        for (Message2 mn2 : m2) {
            m1.add(convertMessage(mn2));
        }
        response.getMessage().addAll(m1);
        return response;
    }

    private Message convertMessage(Message2 m2) {
            Message message = new Message();
            message.setId(m2.getId());
            message.setSender(m2.getSender());
            message.setSendTime(changeTime(m2.getSendTime()));
            message.setContent(m2.getContent());
            return message;
    }

    @SneakyThrows
    private XMLGregorianCalendar changeTime (Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }
}
