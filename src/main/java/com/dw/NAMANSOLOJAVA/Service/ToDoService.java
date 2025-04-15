package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import com.dw.NAMANSOLOJAVA.Repository.OfficialEventRepository;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    AlarmService alarmService;

    public ToDoAllDTO getAllTodo() {
        User user = userService.getCurrentUser();
        List<ToDo> anniversaryList = toDoRepository.findAllByUsernameAndType(user.getUsername(), "ANNIVERSARY");
        List<ToDo> toTravelList = toDoRepository.findAllByUsernameAndType(user.getUsername(), "TRAVEL");
        List<AnniversaryDTO> anverDTOs = anniversaryList.stream().map(ToDo::toAnniDTO).toList();
        List<ToDoTravelDTO> travelDTOs = toTravelList.stream().map(ToDo::toTravelDTO).toList();
        return new ToDoAllDTO(travelDTOs, anverDTOs);
    }

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
        return toTravelList.stream().map(ToDo::toTravelDTO).toList();
    }

    public AnniversaryDTO saveAnniversary(AnniversaryDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();

        ToDo todo = new ToDo();

        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getEndDate()); // 기념일은 시작일 = 종료일
        todo.setFinalEditDate(LocalDate.now());
        todo.setType(dto.getType());
        todo.setUser(user);
        todo.setEditable(true);
        todo.setMedia(new ArrayList<>());
        todo.setColor(dto.getColor());

        ToDo saved = toDoRepository.save(todo);

        // 남은 일수 계산해서 알림 전송
        long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), saved.getStartDate());
        if (daysUntil >= 0 && daysUntil <= 7) {
            alarmService.sendTodoAlarm(user.getUsername(), saved.getTitle());
        }

        return saved.toAnniDTO();
    }

    public ToDoTravelDTO saveTravel(ToDoTravelDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();

        List<Media> mediaList = dto.getMediaUrl().stream().map(
                mediaDTO -> new Media(
                        null, mediaDTO.getMediaUrl(),
                        MediaType.valueOf(mediaDTO.getMediaType()))
        ).toList();
        mediaRepository.saveAll(mediaList);

        ToDo todo = new ToDo();
        todo.setId(null);
        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getEndDate());
        todo.setFinalEditDate(LocalDate.now());
        todo.setType(dto.getType());
        todo.setUser(user);
        todo.setEditable(true);
        todo.setColor(dto.getColor());
        todo.setMedia(mediaList);

        return toDoRepository.save(todo).toTravelDTO();
    }

    public AnniversaryDTO getAnniversaryById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 기념일은 등록되어 있지 않습니다."));
        if (!todo.getType().equalsIgnoreCase("ANNIVERSARY")) {
            throw new ResourceNotFoundException("해당 기념일 항목이 없습니다.");
        }
        return todo.toAnniDTO();
    }

    public ToDoTravelDTO getToDoTravelById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정은 등록되어 있지 않습니다."));
        if (!todo.getType().equalsIgnoreCase("TRAVEL")) {
            throw new ResourceNotFoundException("해당 여행 일정이 없습니다.");
        }
        return todo.toTravelDTO();
    }

    @Transactional
    public ToDoTravelDTO updateToDoTravelById(Long id, ToDoTravelDTO dto) {
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정을 찾을 수 없습니다."));

        // 기존 미디어 유지 / 삭제
        List<Media> oldMedia = todo.getMedia();

        List<Long> newMedia = dto.getMediaUrl().stream()
                .map(MediaDTO::getId)
                .filter(Objects::nonNull)
                .toList();

        // 삭제 대상 미디어
        List<Media> deleteMedia = oldMedia.stream()
                .filter(media -> !newMedia.contains(media.getId()))
                .toList();
        for (Media media : deleteMedia) {
            String relativePath = media.getMediaUrl().replace("/api/todo/download/" + user.getUsername() + "/", "");
            Path filePath = Paths.get("./var/uploads").resolve(user.getUsername()).resolve(relativePath);

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mediaRepository.deleteAll(deleteMedia);

        List<Media> newMediaToSave = dto.getMediaUrl().stream()
                .map(mediaDTO -> {
                    if (mediaDTO.getId() != null) {
                        return mediaRepository.findById(mediaDTO.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 미디어 ID: " + mediaDTO.getId()));
                    } else {
                        if (mediaDTO.getMediaType() == null) {
                            throw new InvalidRequestException("해당 미디어의 타입을 알 수 없습니다.");
                        }
                        Media mediaSave = new Media();
                        mediaSave.setMediaUrl(mediaDTO.getMediaUrl());
                        mediaSave.setMediaType(MediaType.valueOf(mediaDTO.getMediaType()));
                        return mediaRepository.save(mediaSave);
                    }
                }).collect(Collectors.toList());
        mediaRepository.saveAll(newMediaToSave);
        // 나머지 속성 업데이트
        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getEndDate());
        todo.setColor(dto.getColor());
        todo.setFinalEditDate(LocalDate.now());
        todo.setType(dto.getType());
        todo.setMedia(newMediaToSave);

        ToDo updated = toDoRepository.save(todo);
        return updated.toTravelDTO();
    }

    @Transactional
    public AnniversaryDTO updateAnniversaryById(Long id, AnniversaryDTO dto) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 기념일은 등록되어 있지 않습니다."));

        if (!"ANNIVERSARY".equalsIgnoreCase(todo.getType())) {
            throw new InvalidRequestException("해당 항목은 기념일이 아닙니다.");
        }
        dto.setId(id);
        todo.setTitle(dto.getTitle());
        todo.setStartDate(dto.getStartDate());
        todo.setLastDate(dto.getEndDate()); // 기념일은 시작일이 곧 일정의 끝
        todo.setFinalEditDate(LocalDate.now()); // 미디어는 이미 생성 시점에 new ArrayList() 했음.
        todo.setColor(dto.getColor());
        todo.setType(dto.getType());

        ToDo updated = toDoRepository.save(todo);
        return updated.toAnniDTO();
    }

    @Transactional
    public String deleteAnniversaryById(Long id) {
//        String username = getCurrentUsername();
        User user = userService.getCurrentUser();
        ToDo todo = toDoRepository.findByIdAndUsername(id, user.getUsername())
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
        ToDo todo = toDoRepository.findByIdAndUsername(id, user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("해당 여행 일정은 등록되어 있지 않습니다."));

        if (!"TRAVEL".equals(todo.getType())) {
            throw new InvalidRequestException("해당 여행 일정은 등록되어 있지 않습니다.");
        }

        List<Media> mediaList = todo.getMedia();
        for (Media media : mediaList) {
            String relativePath = media.getMediaUrl().replace("/api/todo/download/" + user.getUsername() + "/", "");
            Path filePath = Paths.get("./var/uploads").resolve(user.getUsername()).resolve(relativePath);

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // DB에서 삭제
            mediaRepository.delete(media);
        }

        toDoRepository.delete(todo);

        return "여행 일정이 성공적으로 삭제되었습니다.";
    }
}
