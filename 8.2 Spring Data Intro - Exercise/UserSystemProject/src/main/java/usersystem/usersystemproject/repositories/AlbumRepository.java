package usersystem.usersystemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.usersystemproject.models.entites.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
