package com.netcracker.controller;

import com.netcracker.jpa.Status;
import com.netcracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.POST)
    public Status createStatus(@RequestBody Status status) {
        return statusService.createStatus(status);
    }
}
