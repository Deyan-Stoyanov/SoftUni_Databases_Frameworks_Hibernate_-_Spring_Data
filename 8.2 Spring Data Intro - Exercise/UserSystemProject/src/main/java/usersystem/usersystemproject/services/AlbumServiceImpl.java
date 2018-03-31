package usersystem.usersystemproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.usersystemproject.models.entites.Album;
import usersystem.usersystemproject.repositories.AlbumRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void saveAlbumToDb(Album album) {
        this.albumRepository.saveAndFlush(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        return this.albumRepository.findAll();
    }
}
