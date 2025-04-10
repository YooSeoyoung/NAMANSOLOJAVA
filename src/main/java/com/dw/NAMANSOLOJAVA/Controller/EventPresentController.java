package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.EventPresentDTO;
import com.dw.NAMANSOLOJAVA.Service.EventPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event_present")
public class EventPresentController {
    @Autowired
    EventPresentService eventPresentService;

    @GetMapping("/all")
    public ResponseEntity<List<EventPresentDTO>> getAllEventPresent() {
        return new ResponseEntity<>(eventPresentService.getAllEventPresent(), HttpStatus.OK);
    }

    @GetMapping("/female")
    public ResponseEntity<List<EventPresentDTO>> getFemaleEventPresent() {
        return new ResponseEntity<>(eventPresentService.getFemaleEventPresent(), HttpStatus.OK);
    }

    @GetMapping("/male")
    public ResponseEntity<List<EventPresentDTO>> getMaleEventPresent() {
        return new ResponseEntity<>(eventPresentService.getMaleEventPresent(), HttpStatus.OK);
    }
}
