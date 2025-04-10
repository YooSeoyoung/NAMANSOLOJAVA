package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import com.dw.NAMANSOLOJAVA.Repository.OfficialEventRepository;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.OfficialEvent;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    OfficialEventRepository eventRepository;

    @Autowired
    UserService userService;

    @Autowired
    MediaRepository mediaRepository;

//    private String getCurrentUsername() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }

    public List<AnniversaryDTO> getAllAnniversary() {
//            String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        List<ToDo> anniversaryList = toDoRepository.findAllByUsernameAndType(user.getUsername(), "ANNIVERSARY");
        if (anniversaryList.isEmpty()) {
            throw new ResourceNotFoundException("작성된 기념일이 없습니다.");
        }
        return anniversaryList.stream().map(ToDo::toAnniDTO).toList();
    }

    public List<ToDoTravelDTO> getAllTravel() {
//            String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        List<ToDo> toTravelList = toDoRepository.findAllByUsernameAndType(user.getUsername(), "TRAVEL");
        if (toTravelList.isEmpty()) {
            throw new ResourceNotFoundException("작성된 여행 일정이 없습니다.");
        }
        return toTravelList.stream().map(ToDo::toTravelDTO).toList();
    }

    public AnniversaryDTO saveAnniversary(AnniversaryDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();

        ToDo todo = new ToDo();

        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getStartDate()); // 기념일은 시작일 = 종료일
        todo.setFinalEditDate(LocalDate.now());
        todo.setType("ANNIVERSARY");
        todo.setUser(user);
        todo.setEditable(true);
        todo.setMedia(new ArrayList<>());

        ToDo saved = toDoRepository.save(todo);
        return saved.toAnniDTO();
    }

    public ToDoTravelDTO saveTravel(ToDoTravelDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();

        List<Media> mediaList = dto.getMediaUrl().stream()
                .map(m -> {
                    Media media = new Media();
                    media.setMediaUrl(m.getMediaUrl());
                    media.setMediaType(MediaType.valueOf(m.getMediaType()));
                    mediaRepository.save(media); // 직접 저장 (Cascade 없이)
                    return media;
                })
                .toList();

        ToDo todo = new ToDo();

        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getLastDate());
        todo.setFinalEditDate(LocalDate.now());
        todo.setType("TRAVEL");
        todo.setUser(user);
        todo.setEditable(true);
        todo.setMedia(mediaList);

        ToDo saved = toDoRepository.save(todo);

        return saved.toTravelDTO();
    }

    public AnniversaryDTO getAnniversaryById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 기념일은 등록되어 있지 않습니다."));
        if (!todo.getType().equalsIgnoreCase("ANNIVERSARY")) {
            throw new ResourceNotFoundException("해당 기념일 항목이 없습니다.");
        }
        return todo.toAnniDTO();
    }

    public ToDoTravelDTO getToDoTravelById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정은 등록되어 있지 않습니다."));
        if (!todo.getType().equalsIgnoreCase("TRAVEL")) {
            throw new ResourceNotFoundException("해당 여행 일정이 없습니다.");
        }
        return todo.toTravelDTO();
    }

    @Transactional
    public ToDoTravelDTO updateToDoTravelById(Long id, ToDoTravelDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정은 등록되어 있지 않습니다."));

        if (!todo.getType().equals("TRAVEL")) {
            throw new InvalidRequestException("해당 항목은 여행 일정이 아닙니다.");
        }

        todo.setUser(user);
        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getLastDate());
        todo.setFinalEditDate(LocalDate.now());

        List<Media> originMedia = todo.getMedia();
        List<Long> incomingMediaIds = dto.getMediaUrl().stream()
                .map(MediaDTO::getId)
                .filter(Objects::nonNull) // mediaId -> mediaId != null 으로 했더니 자동추천, 이 표현의 람다식이라고 함
                .toList();

        List<Media> deleteMedia = originMedia.stream()
                .filter(m -> !incomingMediaIds.contains(m.getId()))
                .toList();

        originMedia.removeAll(deleteMedia);

        List<Media> newMedias = dto.getMediaUrl().stream()
                .filter(m -> m.getId() == null)
                .map(m -> {
                    Media media = new Media();
                    media.setMediaUrl(m.getMediaUrl());
                    media.setMediaType(MediaType.valueOf(m.getMediaType()));
                    return mediaRepository.save(media);
                })
                .toList();

        originMedia.addAll(newMedias);

        todo.setMedia(originMedia);

        ToDo updated = toDoRepository.save(todo);
        return updated.toTravelDTO();
    }

    @Transactional
    public AnniversaryDTO updateAnniversaryById(Long id, AnniversaryDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 기념일은 등록되어 있지 않습니다."));

        if (!"ANNIVERSARY".equalsIgnoreCase(todo.getType())) {
            throw new InvalidRequestException("해당 항목은 기념일이 아닙니다.");
        }

        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getStartDate()); // 기념일은 시작일이 곧 일정의 끝
        todo.setFinalEditDate(LocalDate.now()); // 미디어는 이미 생성 시점에 new ArrayList() 했음.

        ToDo updated = toDoRepository.save(todo);
        return updated.toAnniDTO();
    }

    @Transactional
    public String deleteAnniversaryById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 기념일은 등록되어 있지 않습니다."));

        if (!todo.getEditable()) {
            throw new InvalidRequestException("비정상적인 접근입니다.");
        }

        if (!"ANNIVERSARY".equals(todo.getType())) {
            throw new InvalidRequestException("해당 항목은 기념일이 아닙니다.");
        }

        toDoRepository.delete(todo);

        return "기념일이 성공적으로 삭제되었습니다.";
    }

    @Transactional
    public String deleteTravelById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUserUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정은 등록되어 있지 않습니다."));

        if (!"TRAVEL".equals(todo.getType())) {
            throw new InvalidRequestException("해당 여행 일정은 등록되어 있지 않습니다.");
        }

        toDoRepository.delete(todo);

        return "기념일이 성공적으로 삭제되었습니다.";
    }
}
