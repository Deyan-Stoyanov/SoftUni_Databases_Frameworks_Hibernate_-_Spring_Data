package usersystem.usersystemproject.services;

import usersystem.usersystemproject.models.entites.Picture;

import java.util.List;

public interface PictureService {
    void savePictureToDb(Picture picture);
    List<Picture> getAllPictures();
}
