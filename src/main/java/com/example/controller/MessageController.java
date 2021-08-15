package com.example.controller;


import com.example.db.DBRepository;
import com.example.db.InsertRepository;
import com.example.db.Message2;
import com.example.jms.Reciever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import service.Message;

@RestController
public class MessageController {

    @Autowired
    JmsTemplate jmsTemplate;

    private final DBRepository repository;
    //второй репозиторий нужен для инсёртов
    private final InsertRepository insertRepository;

    public MessageController(DBRepository repository, InsertRepository insertRepository) {
        this.repository = repository;
        this.insertRepository = insertRepository;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String addMessage (@RequestBody Message message) {
        jmsTemplate.convertAndSend("messages", message);
        Message m = new Reciever().receiveMessage(message);
        Message2 message2 = new Message2(m.getContent(), m.getId(), m.getSendTime().toGregorianCalendar().getTime(), m.getSender());
        try {
            insertRepository.insertWithQuery(message2);
            System.out.println(message2);
            return "SUCCESS";
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }

    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public String deleteMessage (@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return "SUCCESS";
        } catch (Exception e) {
            throw e;
        }
    }
}
