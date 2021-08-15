package com.example.db;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class InsertRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Message2 message) {
        entityManager.createNativeQuery("INSERT INTO messages (id, sender, send_time, content) VALUES(?, ?, ?, ?);")
                .setParameter(1, message.getId())
                .setParameter(2, message.getSender())
                .setParameter(3, message.getSendTime())
                .setParameter(4, message.getContent())
                .executeUpdate();
    }
}
