package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.TravelDTO;
import com.dw.NAMANSOLOJAVA.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @GetMapping("/anniversary/all")
    public ResponseEntity<List<AnniversaryDTO>> getAllAnniversary() {
        return new ResponseEntity<>(toDoService.getAllAnniversary(), HttpStatus.OK);
    }

    @GetMapping("/travel/all")
    public ResponseEntity<List<TravelDTO>> getAllTravel() {
        return new ResponseEntity<>(toDoService.getAllTravel(), HttpStatus.OK);
    }

    @PostMapping("/travel/save")
    public ResponseEntity<TravelDTO> saveTravel(@RequestBody TravelDTO travelDTO) {
        return new ResponseEntity<>(toDoService.saveTravel(travelDTO), HttpStatus.OK);
    }

    @PostMapping("/anniversary/save")
    public ResponseEntity<AnniversaryDTO> saveAnniversary(@RequestBody AnniversaryDTO anniversaryDTO) {
        return new ResponseEntity<>(toDoService.saveAnniversary(anniversaryDTO), HttpStatus.OK);
    }

    @GetMapping("/anniversary/{id}")
    public ResponseEntity<AnniversaryDTO> getAnniversaryById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getAnniversaryById(id), HttpStatus.OK);
    }

    @GetMapping("/travel/{id}")
    public ResponseEntity<TravelDTO> getToDoTravelById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoTravelById(id), HttpStatus.OK);
    }

    @PutMapping("/travel/update/{id}")
    public ResponseEntity<TravelDTO> updateToDoTravelById(@PathVariable Long id, @RequestBody TravelDTO travelDTO) {
        return new ResponseEntity<>(toDoService.updateToDoTravelById(id, travelDTO), HttpStatus.OK);
    }

    @PutMapping("/anniversary/update/{id}")
    public ResponseEntity<AnniversaryDTO> updateAnniversaryById(@PathVariable Long id, @RequestBody AnniversaryDTO anniversaryDTO) {
        return new ResponseEntity<>(toDoService.updateAnniversaryById(id, anniversaryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/anniversary/delete/{id}")
    public ResponseEntity<String> deleteAnniversaryById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.deleteAnniversaryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/travel/delete/{id}")
    public ResponseEntity<String> deleteTravelById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.deleteTravelById(id), HttpStatus.OK);
    }
}
