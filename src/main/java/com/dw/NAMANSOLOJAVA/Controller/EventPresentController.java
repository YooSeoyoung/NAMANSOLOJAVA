package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.EventPresentDTO;
import com.dw.NAMANSOLOJAVA.Service.EventPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/event")
public class EventPresentController {
    @Autowired
    EventPresentService eventPresentService;

    @GetMapping("/all")
    public ResponseEntity<EventPresentDTO> getAllEventPresent() {
        return new ResponseEntity<>(eventPresentService.getAllEventPresent(), HttpStatus.OK);
    }

    @GetMapping("/female")
    public ResponseEntity<EventPresentDTO> getFemaleEventPresent() {
        return new ResponseEntity<>(eventPresentService.getFemaleEventPresent(), HttpStatus.OK);
    }

    @GetMapping("/male")
    public ResponseEntity<EventPresentDTO> getMaleEventPresent() {
        return new ResponseEntity<>(eventPresentService.getMaleEventPresent(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EventPresentDTO> updateEventWord() {
        return new ResponseEntity<>(eventPresentService.updateEventWord(), HttpStatus.OK);
    }
}
