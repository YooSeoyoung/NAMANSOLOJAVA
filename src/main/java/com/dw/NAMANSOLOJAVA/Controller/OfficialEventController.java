package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import com.dw.NAMANSOLOJAVA.Service.OfficialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/officialevent")
public class OfficialEventController {
    @Autowired
    OfficialEventService officialEventService;

    @GetMapping("/all")
    public ResponseEntity<OfficialEventDTO> getAllOfficialEvent() {
        return new ResponseEntity<>(officialEventService.getAllOfficialEvent(), HttpStatus.OK);
    }

    @GetMapping("/single")
    public ResponseEntity<OfficialEventDTO> getSingleOfficialEvent() {
        return new ResponseEntity<>(officialEventService.getSingleOfficialEvent(), HttpStatus.OK);
    }

    @GetMapping("/all/{date}")
    public ResponseEntity<OfficialEventDTO> getAllOfficialEventByDate(@PathVariable LocalDate date) {
        return new ResponseEntity<>(officialEventService.getAllOfficialEventByDate(date), HttpStatus.OK);
    }

    @PutMapping("/single/update")
    public ResponseEntity<OfficialEventDTO> updateOfficialEvent(@RequestBody OfficialEventDTO officialEventDTO) {
        return new ResponseEntity<>(officialEventService.updateOfficialEvent(officialEventDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOfficialEvent(@PathVariable Long id) {
        return new ResponseEntity<>(officialEventService.deleteOfficialEvent(id), HttpStatus.OK);
    }
}
