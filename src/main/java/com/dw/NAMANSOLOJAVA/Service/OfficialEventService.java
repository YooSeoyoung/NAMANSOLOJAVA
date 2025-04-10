package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.OfficialEventRepository;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.model.OfficialEvent;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfficialEventService {
    @Autowired
    OfficialEventRepository officialEventRepository;

    @Autowired
    ToDoRepository todoRepository;

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
        if (
                (dto.getOffsetDays() == null) &&
                (dto.getOffsetDays() <= 0L && dto.getEventDate() == null)) {
            throw new InvalidRequestException("기본 날짜 차이는 0 이상이어야 하며, 0인 경우에는 이벤트일을 지정해야 합니다.");
        }
        if (dto.getEventTitle().isEmpty()){
            throw new InvalidRequestException("이벤트명은 필수입니다.");
        }

        OfficialEvent event = new OfficialEvent();
        event.setEventTitle(dto.getEventTitle());
        event.setOffsetDays(dto.getOffsetDays());
        event.setEditable(false);

        // 날짜는 항상 채워주자
        event.setEventDate(dto.getEventDate() != null ? dto.getEventDate() : LocalDate.now());

        OfficialEvent saved = officialEventRepository.save(event);

        return new OfficialEventDTO(
                saved.getEventDate(),
                saved.getEventTitle(),
                saved.getOffsetDays()
        );
    }

    @Transactional
    public OfficialEventDTO updateOfficialEvent(Long id, OfficialEventDTO dto) {
        OfficialEvent event = officialEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 이벤트를 찾을 수 없습니다."));

        List<ToDo> todos = todoRepository.findAllByTitleAndEditable(event.getEventTitle(), false);

        event.setEventTitle(dto.getEventTitle());
        if (event.getOffsetDays()!=null) {
            event.setOffsetDays(dto.getOffsetDays());
        }

        event.setEventDate(dto.getEventDate());

        OfficialEvent updated = officialEventRepository.save(event);

//        todos.stream().map(ToDo:)

        return updated.offEventDTO();
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
}
