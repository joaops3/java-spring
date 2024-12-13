package java_spring_basic.api.service;


import java_spring_basic.api.domain.event.Event;
import java_spring_basic.api.domain.event.EventRequestDto;
import java_spring_basic.api.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private S3Client s3Client;


    @Autowired
    private EventMapper eventMapper;

    public Event createEvent(EventRequestDto data){
        String imgUrl = null;

        if(data.image() != null) {
          imgUrl = this.uploadImg(data.image());
        }


        // Event newEvent = new Event();

        // newEvent.setTitle(data.title());
        // newEvent.setDescription(data.description());
        // newEvent.setEventUrl(data.eventUrl());
        // newEvent.setDate(new Date(data.date()));
        // newEvent.setImgUrl(imgUrl);
        // newEvent.setRemote(data.remote());

        var newEvent = eventMapper.ToEntity(data, imgUrl);


        return newEvent;
    }


    public String uploadImg(MultipartFile file){
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();


        try{
            PutObjectRequest putOb = PutObjectRequest.builder()
                .bucket("event-images")
                .key(filename)
                .build();
            
            s3Client.putObject(putOb, RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes())));
            GetUrlRequest urlRequest = GetUrlRequest.builder()
                .bucket("event-images")
                .key(filename)
                .build();
            return s3Client.utilities().getUrl(urlRequest).toString();
        }catch(Exception e){
            log.error("erro ao subir arquivo: {}", e.getMessage());
            return "";
        }
    }
}
