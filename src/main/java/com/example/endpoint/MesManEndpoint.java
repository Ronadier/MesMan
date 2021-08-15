package com.example.endpoint;

import com.example.db.DBRepository;
import com.example.db.MesManServiceImpl;
import org.springframework.stereotype.Service;
import service.*;

@Service
public class MesManEndpoint implements MessageManagerService {

    private final DBRepository repository;

    public MesManEndpoint(DBRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetMessagesBySenderResponse getMessagesBySender(GetMessagesBySender parameters) {
        return new MesManServiceImpl(repository).getMessagesBySender(parameters);
    }

    @Override
    public GetMessagesByDateResponse getMessagesByDate(GetMessagesByDate parameters) {
        return new MesManServiceImpl(repository).getMessagesByDate(parameters);
    }

}
