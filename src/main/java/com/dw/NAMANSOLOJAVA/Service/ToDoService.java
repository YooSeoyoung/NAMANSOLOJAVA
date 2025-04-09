package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.TravelDTO;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public List<AnniversaryDTO> getAllAnniversary() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ToDo> todos = toDoRepository.findAllTodoByUsernameAndType("couple001", "ANNIVERSARY").get();

        return todos.stream().map(ToDo::toAnniversaryDTO).toList();
    }

    public List<TravelDTO> getAllTravel() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ToDo> todos = toDoRepository.findAllTodoByUsernameAndType("couple001", "TRAVEL").get();

        return todos.stream().map(ToDo::toTravelDTO).toList();
    }

    public AnniversaryDTO saveAnniversary(AnniversaryDTO anniversaryDTO) {
        return null;
    }

    public TravelDTO saveTravel(TravelDTO travelDTO) {
        return null;
    }

    public AnniversaryDTO getAnniversaryById(Long id) {
        return null;
//        return toDoRepository.getAnniversaryById(id);
    }

    public TravelDTO getToDoTravelById(Long id) {
        return null;
//        return toDoRepository.getToDoTravelById();
    }

    public TravelDTO updateToDoTravelById(Long id, TravelDTO travelDTO) {
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
