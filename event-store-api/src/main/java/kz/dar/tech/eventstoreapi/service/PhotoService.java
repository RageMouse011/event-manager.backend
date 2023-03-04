package kz.dar.tech.eventstoreapi.service;

import kz.dar.tech.eventstoreapi.entity.Photo;
import kz.dar.tech.eventstoreapi.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo uploadPhoto(
            MultipartFile file,
            String eventId
    ) throws IOException {
        Photo photo = new Photo();
        photo.setData(file.getBytes());
        photo.setEventId(eventId);
        return photoRepository.save(photo);
    }
    public byte[] downloadPhoto(
            Long id
    ) {
        Photo photo = photoRepository.findById(id).get();
        return photo.getData();
    }
}
