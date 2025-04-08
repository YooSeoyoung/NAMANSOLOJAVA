package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import com.dw.NAMANSOLOJAVA.Repository.OfficialEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OfficialEventService {
    @Autowired
    OfficialEventRepository officialEventRepository;

    public OfficialEventDTO getAllOfficialEvent() {
        return null;
//        return officialEventRepository.getAllOfficialEvent();
    }

    public OfficialEventDTO getSingleOfficialEvent() {
        return null;
//        return officialEventService.getSingleOfficialEvent();
    }

    public OfficialEventDTO getAllOfficialEventByDate(LocalDate date) {
        return null;
//        return officialEventService.getAllOfficialEventByDate(date);
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
