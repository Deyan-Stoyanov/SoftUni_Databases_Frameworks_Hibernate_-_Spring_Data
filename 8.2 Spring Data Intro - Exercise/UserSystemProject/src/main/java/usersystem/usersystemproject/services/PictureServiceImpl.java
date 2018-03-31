package usersystem.usersystemproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.usersystemproject.models.entites.Picture;
import usersystem.usersystemproject.repositories.PictureRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void savePictureToDb(Picture picture) {
        this.pictureRepository.saveAndFlush(picture);
    }

    @Override
    public List<Picture> getAllPictures() {
        return this.pictureRepository.findAll();
    }
}
