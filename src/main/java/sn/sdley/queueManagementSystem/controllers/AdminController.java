package sn.sdley.queueManagementSystem.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.sdley.queueManagementSystem.dto.QueueInfo;
import sn.sdley.queueManagementSystem.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TicketService ticketService;

    // REST endpoint pour le dashboard admin
    @GetMapping("/dashboard")
    public ResponseEntity<List<QueueInfo>> getDashboardOverview() {
        List<QueueInfo> queues = ticketService.getQueuesOverview();
        return ResponseEntity.ok(queues);
    }

    // We'll add the remaining endpoints later
}

