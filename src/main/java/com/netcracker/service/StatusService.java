package com.netcracker.service;

import com.netcracker.jpa.Status;

public interface StatusService {
    Iterable<Status> getAllStatuses();
    Status createStatus(Status status);
}
