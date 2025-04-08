package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
import com.dw.NAMANSOLOJAVA.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @GetMapping("/anniversary/all")
    public List<AnniversaryDTO> getAllAnniversary() {
        return toDoService.getAllAnniversary();
    }

    @GetMapping("/travel/all")
    public List<ToDoTravelDTO> getAllTravel() {
        return toDoService.getAllTravel();
    }

    @GetMapping("/anniversary/{id}")
    public AnniversaryDTO getAnniversaryById(@PathVariable Long id) {
        return toDoService.getAnniversaryById(id);
    }

    @GetMapping("/travel/{id}")
    public ToDoTravelDTO getToDoTravelById(@PathVariable Long id) {
        return toDoService.getToDoTravelById(id);
    }

    @PutMapping("/travel/update/{id}")
    public ToDoTravelDTO updateToDoTravelById(@PathVariable Long id, @RequestBody ToDoTravelDTO toDoTravelDTO) {
        return toDoService.updateToDoTravelById(id, toDoTravelDTO);
    }

    @PutMapping("/anniversary/update/{id}")
    public AnniversaryDTO updateAnniversaryById(@PathVariable Long id, @RequestBody AnniversaryDTO anniversaryDTO) {
        return toDoService.updateAnniversaryById(id, anniversaryDTO);
    }

    @DeleteMapping("/anniversary/delete/{id}")
    public String deleteAnniversaryById(@PathVariable Long id) {
        return toDoService.deleteAnniversaryById(id);
    }

    @DeleteMapping("/travel/delete/{id}")
    public String deleteTravelById(@PathVariable Long id) {
        return toDoService.deleteTravelById(id);
    }
}
