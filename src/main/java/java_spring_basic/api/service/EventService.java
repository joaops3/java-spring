package java_spring_basic.api.service;


import java_spring_basic.api.domain.event.Event;
import java_spring_basic.api.domain.event.EventRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    private S3Client s3Client;

    public Event createEvent(EventRequestDto data){
        String imgUrl = null;

        if(data.image() != null) {
          imgUrl = this.uploadImg(data.image());
        }


        Event newEvent = new Event();

        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());


        return newEvent;
    }


    public String uploadImg(MultipartFile file){
        return "";
    }
}
