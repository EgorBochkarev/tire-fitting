package com.netcracker.service;

import com.netcracker.repository.StatusesHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {

    @Autowired
    private StatusesHistoryRepository statusesHistoryRepository;
}
