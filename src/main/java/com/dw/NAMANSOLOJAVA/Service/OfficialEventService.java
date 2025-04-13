package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
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
        if (dto.getOffsetDays() == null || dto.getOffsetDays() < 0) {
            throw new InvalidRequestException("offsetDays는 0 이상이어야 합니다.");
        }

        if (dto.getOffsetDays() == 0L && dto.getEventDate() == null) {
            throw new InvalidRequestException("고정 이벤트는 날짜를 입력해야 합니다.");
        }

        // 1. 이벤트 저장
        OfficialEvent event = new OfficialEvent();
        event.setEventTitle(dto.getEventTitle());
        event.setEventDate(dto.getEventDate() != null ? dto.getEventDate() : LocalDate.now());
        event.setOffsetDays(dto.getOffsetDays());
        event.setEditable(false);

        officialEventRepository.save(event);

        // 2. 전체 유저 조회
        List<User> users = userRepository.findAll();

        // 3. 유저별 ToDo 생성
        List<ToDo> todos = new ArrayList<>();

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase("admin")) {
                continue;
            }
            ToDo todo = new ToDo();
            todo.setUser(user);
            todo.setTitle(event.getEventTitle());
            todo.setEditable(false);
            todo.setFinalEditDate(LocalDate.now());
            todo.setType("ANNIVERSARY"); // 공식 이벤트 => 유저 개인의 기념일
            todo.setColor("#b22222");

            if (event.getOffsetDays() == 0L) {
                todo.setStartDate(event.getEventDate());
                todo.setLastDate(event.getEventDate());
            } else {
                LocalDate dday = user.getDDay();
                LocalDate offsetDate = dday.plusDays(event.getOffsetDays());
                todo.setStartDate(offsetDate);
                todo.setLastDate(offsetDate);
            }

            todos.add(todo);

            }


        todoRepository.saveAll(todos);

        return event.offEventDTO();
    }

    @Transactional
    public OfficialEventDTO updateOfficialEvent(Long id, OfficialEventDTO dto) {
        OfficialEvent event = officialEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("이벤트가 없습니다."));

        String oldTitle = event.getEventTitle();

        event.setEventTitle(dto.getEventTitle());
        event.setEventDate(dto.getEventDate());
        event.setOffsetDays(dto.getOffsetDays());
        Long offsetDays = event.getOffsetDays();

        event.setEditable(false);

        List<ToDo> todos = todoRepository.findAllByTitleAndEditable(oldTitle, false);

        for (ToDo todo : todos) {
            if (todo.getUser().getUsername().equalsIgnoreCase("admin")) {
                continue;
            }
            if (!oldTitle.equals(dto.getEventTitle())) {
                todo.setTitle(dto.getEventTitle());
            }

            if (offsetDays == 0L) {
                todo.setStartDate(event.getEventDate());
                todo.setLastDate(event.getEventDate());
                todo.setMedia(new ArrayList<>());
                todo.setFinalEditDate(LocalDate.now());
                todo.setColor("#b22222");
                todo.setType("ANNIVERSARY");
                todo.setEditable(false);
            } else {
                LocalDate dday = todo.getUser().getDDay();
                LocalDate calcDate = dday.plusDays(event.getOffsetDays());
                todo.setStartDate(calcDate);
                todo.setMedia(new ArrayList<>());
                todo.setLastDate(calcDate);
                todo.setFinalEditDate(LocalDate.now());
                todo.setColor("#b22222");
                todo.setEditable(false);
                todo.setType("ANNIVERSARY");
            }
        }

        todoRepository.saveAll(todos);
        OfficialEventDTO offEventDTO = officialEventRepository.save(event).offEventDTO();
        return offEventDTO;
    }

    @Transactional
    public String deleteOfficialEvent(Long id) {
        OfficialEvent event = officialEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 이벤트를 찾을 수 없습니다."));

        List<ToDo> relatedTodos = todoRepository.findAllByTitleAndEditable(event.getEventTitle(), false);

        todoRepository.deleteAll(relatedTodos);

        officialEventRepository.delete(event);

        return "이벤트 및 관련 유저 일정이 삭제되었습니다.";
    }

    @Transactional
    public void applyOfficialEventsToUser(User user) {
        List<OfficialEvent> events = officialEventRepository.findAll();

        List<ToDo> todos = new ArrayList<>();

        for (OfficialEvent event : events) {
            if (user.getUsername().equalsIgnoreCase("admin")) {
                continue;
            }
            ToDo todo = new ToDo();

            todo.setUser(user);
            todo.setTitle(event.getEventTitle());
            todo.setEditable(false);
            todo.setType("ANNIVERSARY");
            todo.setFinalEditDate(LocalDate.now());
            todo.setColor("#b22222");

            if (event.getOffsetDays() == 0L) {
                todo.setStartDate(event.getEventDate());
                todo.setLastDate(event.getEventDate());
            } else {
                LocalDate dday = user.getDDay();
                LocalDate date = dday.plusDays(event.getOffsetDays());
                todo.setStartDate(date);
                todo.setLastDate(date);
            }

            todos.add(todo);
        }

        todoRepository.saveAll(todos);
    }
}
