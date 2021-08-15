package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DBRepository extends JpaRepository<Message2, Integer> {

    @Query(value = "select id, sender, send_time, content from messages where sender = ?;", nativeQuery = true)
    List<Message2> getMessagesBySender(String name);
    @Query(value = "select id, sender, send_time, content from messages WHERE DATE(send_time) = ?", nativeQuery = true)
    List<Message2> getMessagesByDate(Date sendTime);
}
