package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import com.dw.NAMANSOLOJAVA.Exception.PermissionDeniedException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.OfficialEventRepository;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.OfficialEvent;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfficialEventService {
    @Autowired
    OfficialEventRepository officialEventRepository;

    @Autowired
    UserService userService;

    @Autowired
    ToDoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    public List<OfficialEventDTO> getAllOfficialEvent() {
        List<OfficialEventDTO> officialEventDTOs = officialEventRepository.findAll().stream().map(OfficialEvent::offEventDTO).toList();
        if (officialEventDTOs.isEmpty()) {
            throw new ResourceNotFoundException("시스템이 기념일을 찾지 못했습니다.");
        }

        return officialEventDTOs;
    }

    public OfficialEventDTO getSingleOfficialEvent(Long id) {
        OfficialEvent officialEvent = officialEventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("해당 일정을 찾지 못했습니다."));

        return officialEvent.offEventDTO();
    }

    @Transactional
    public OfficialEventDTO saveOfficialEvent(OfficialEventDTO dto) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            // 중복 방지: 같은 날짜 + 제목이 있으면 생략
            boolean exists = todoRepository.existsByUsernameAndStartDateAndTitle(
                    user.getUsername(), dto.getEventDate(), dto.getEventTitle());

            if (!exists) {
                ToDo todo = new ToDo();
                todo.setTitle(dto.getEventTitle());
                todo.setStartDate(dto.getEventDate());
                todo.setLastDate(dto.getEventDate()); // 기념일 = 당일 일정
                todo.setFinalEditDate(LocalDate.now());
                todo.setType("ANNIVERSARY");
                todo.setUser(user);
                todo.setEditable(false); // 공식 이벤트니까 수정 불가

                todoRepository.save(todo);
            }
        }

        OfficialEvent officialEvent = new OfficialEvent();
        officialEvent.setEventTitle(dto.getEventTitle());
        officialEvent.setEventDate(dto.getEventDate());
        officialEvent.setEditable(false);

        return dto;
    }

    public OfficialEventDTO updateOfficialEvent(OfficialEventDTO officialEventDTO) {
        return null;
//        return officialEventService.updateOfficialEvent();
    }

    public String deleteOfficialEvent(Long id) {
        return null;
//       officialEventRepository.deleteById(id);
    }
}
