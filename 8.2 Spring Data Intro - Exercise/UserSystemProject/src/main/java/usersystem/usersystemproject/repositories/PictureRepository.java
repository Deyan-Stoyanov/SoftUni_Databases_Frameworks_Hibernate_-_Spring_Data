package usersystem.usersystemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.usersystemproject.models.entites.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
