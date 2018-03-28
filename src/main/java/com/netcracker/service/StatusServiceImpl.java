package com.netcracker.service;

import com.netcracker.jpa.Status;
import com.netcracker.repository.StatusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusesRepository statusesRepository;
    @Override
    public Iterable<Status> getAllStatuses() {
        return statusesRepository.findAll();
    }

    @Override
    public Status createStatus(Status status) {
        if (statusesRepository.findOne(status.getStatusId())!=null) {
            return status;
        }
        else
        return statusesRepository.save(status);
    }
}
