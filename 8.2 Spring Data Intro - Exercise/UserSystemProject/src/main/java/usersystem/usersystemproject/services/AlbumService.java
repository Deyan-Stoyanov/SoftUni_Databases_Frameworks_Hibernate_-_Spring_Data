package usersystem.usersystemproject.services;

import usersystem.usersystemproject.models.entites.Album;

import java.util.List;

public interface AlbumService {
    void saveAlbumToDb(Album album);
    List<Album> getAllAlbums();
}
