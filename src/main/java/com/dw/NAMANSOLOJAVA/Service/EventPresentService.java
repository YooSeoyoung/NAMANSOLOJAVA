package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.EventPresentDTO;
import com.dw.NAMANSOLOJAVA.Repository.EventPresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPresentService {
    @Autowired
    EventPresentRepository eventPlaceRepository;

    public EventPresentDTO getAllEventPresent() {
        return null;
//        return eventPlaceRepository.getAllEventPresent();
    }

    public EventPresentDTO getFemaleEventPresent() {
        return null;
//        return eventPlaceRepository.getFemaleEventPresent();
    }

    public EventPresentDTO getMaleEventPresent() {
        return null;
//        return eventPlaceRepository.getMaleEventPresent();
    }

    public EventPresentDTO updateEventWord() {
        return null;
//        return eventPlaceRepository.updateEventWord();
    }
}
