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

    public List<ToDoTravelDTO> getAllTravel() {
        return null;
//        return toDoRepository.getAllTravel();
    }

    public AnniversaryDTO saveAnniversary(AnniversaryDTO anniversaryDTO) {
        return null;
    }

    public ToDoTravelDTO saveTravel(ToDoTravelDTO toDoTravelDTO) {
        return null;
    }

    public AnniversaryDTO getAnniversaryById(Long id) {
        return null;
//        return toDoRepository.getAnniversaryById(id);
    }

    public ToDoTravelDTO getToDoTravelById(Long id) {
        return null;
//        return toDoRepository.getToDoTravelById();
    }

    public ToDoTravelDTO updateToDoTravelById(Long id, ToDoTravelDTO toDoTravelDTO) {
        return null;
//        toDoRepository.updateToDoTravelById(id);
//        toDoTravelDTO.getLastDate();
    }

    public AnniversaryDTO updateAnniversaryById(Long id, AnniversaryDTO anniversaryDTO) {
        return null;
//         toDoRepository.updateAnniversaryById(id);
    }

    public String deleteAnniversaryById(Long id) {
        return null;
//        toDoRepository.deleteAnniversaryById(id);
    }

    public String deleteTravelById(Long id) {
        return null;
//        toDoRepository.deleteTravelById(id);
    }
}
