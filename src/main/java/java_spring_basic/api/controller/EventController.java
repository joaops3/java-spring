package java_spring_basic.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java_spring_basic.api.domain.event.Event;
import java_spring_basic.api.domain.event.EventRequestDto;
import java_spring_basic.api.service.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

   

    @PostMapping(path = "/", consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@Valid @ModelAttribute EventRequestDto eventDto){

        var newEvent = eventService.createEvent(eventDto);

        return ResponseEntity.ok(newEvent);
    }
    
}
