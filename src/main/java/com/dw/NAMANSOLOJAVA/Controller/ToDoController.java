package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<ToDoTravelDTO>> getAllTodo() {
        return new ResponseEntity<>(toDoService.getAllTodo(), HttpStatus.OK);
    }

    @GetMapping("/anniversary/all")
    public ResponseEntity<List<AnniversaryDTO>> getAllAnniversary() {
        return new ResponseEntity<>(toDoService.getAllAnniversary(), HttpStatus.OK);
    }

    @GetMapping("/travel/all")
    public ResponseEntity<List<ToDoTravelDTO>> getAllTravel() {
        return new ResponseEntity<>(toDoService.getAllTravel(), HttpStatus.OK);
    }

    @PostMapping("/travel/save")
    public ResponseEntity<ToDoTravelDTO> saveTravel(@RequestBody ToDoTravelDTO toDoTravelDTO) {
        return new ResponseEntity<>(toDoService.saveTravel(toDoTravelDTO), HttpStatus.OK);
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
    public ResponseEntity<ToDoTravelDTO> getToDoTravelById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoTravelById(id), HttpStatus.OK);
    }

    @PutMapping("/travel/update/{id}")
    public ResponseEntity<ToDoTravelDTO> updateToDoTravelById(@PathVariable Long id, @RequestBody ToDoTravelDTO toDoTravelDTO) {
        return new ResponseEntity<>(toDoService.updateToDoTravelById(id, toDoTravelDTO), HttpStatus.OK);
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
