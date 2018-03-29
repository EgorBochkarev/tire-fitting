package com.netcracker.controller;

import com.netcracker.jpa.Status;
import com.netcracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Status createStatus(@RequestBody Status status) {
        return statusService.createStatus(status);
    }
}
