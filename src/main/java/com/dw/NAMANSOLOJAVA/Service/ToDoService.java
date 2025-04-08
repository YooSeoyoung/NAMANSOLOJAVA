package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public List<AnniversaryDTO> getAllAnniversary() {
        return null;
//        return toDoRepository.getAllAnniversary();
    }

    @GetMapping("/travel/all")
    public List<ToDoTravelDTO> getAllTravel() {
        return null;
//        return toDoRepository.getAllTravel();
    }

    @GetMapping("/anniversary/{id}")
    public AnniversaryDTO getAnniversaryById(Long id) {
        return null;
//        return toDoRepository.getAnniversaryById(id);
    }

    @GetMapping("/travel/{id}")
    public ToDoTravelDTO getToDoTravelById(Long id) {
        return null;
//        return toDoRepository.getToDoTravelById();
    }

    @PutMapping("/travel/update/{id}")
    public ToDoTravelDTO updateToDoTravelById(@PathVariable Long id, @RequestBody ToDoTravelDTO toDoTravelDTO) {
        return null;
//        toDoRepository.updateToDoTravelById(id);
//        toDoTravelDTO.getLastDate();
    }

    @PutMapping("/anniversary/update/{id}")
    public AnniversaryDTO updateAnniversaryById(@PathVariable Long id, @RequestBody AnniversaryDTO anniversaryDTO) {
        return null;
//         toDoRepository.updateAnniversaryById(id);
    }

    @DeleteMapping("/anniversary/delete/{id}")
    public String deleteAnniversaryById(@PathVariable Long id) {
        return null;
//        toDoRepository.deleteAnniversaryById(id);
    }

    @DeleteMapping("/travel/delete/{id}")
    public String deleteTravelById(@PathVariable Long id) {
        return null;
//        toDoRepository.deleteTravelById(id);
    }
}
