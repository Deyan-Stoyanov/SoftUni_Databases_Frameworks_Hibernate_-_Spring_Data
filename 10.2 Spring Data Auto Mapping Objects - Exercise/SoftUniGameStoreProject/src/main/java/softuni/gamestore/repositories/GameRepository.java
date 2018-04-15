package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.entities.Game;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public interface GameRepository extends JpaRepository<Game, Long> {
    Long findGameById(Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.title=:title WHERE g.id=:id")
    void updateGameTitle(@Param("title")String title, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.price=:price WHERE g.id=:id")
    void updateGamePrice(@Param("price")BigDecimal price, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.size=:size WHERE g.id=:id")
    void updateGameSize(@Param("size")Double size, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.youtubeId=:youtubeId WHERE g.id=:id")
    void updateGameYoutubeId(@Param("youtubeId")String youtubeId, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.imageUrl=:imageUrl WHERE g.id=:id")
    void updateGameImageUrl(@Param("imageUrl")String imageUrl, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.description=:description WHERE g.id=:id")
    void updateGameDescription(@Param("description")String description, @Param("id")Long id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Game AS g SET g.releaseDate=:releaseDate WHERE g.id=:id")
    void updateGameReleaseDate(@Param("releaseDate")Date releaseDate, @Param("id")Long id);
    @Transactional
    @Modifying
    void deleteGameById(Long id);
    @Query(value = "SELECT new softuni.gamestore.models.dto.binding.GameBindingModel(g.title, g.price, g.size, g.youtubeId, g.imageUrl, g.description, g.releaseDate) FROM Game AS g")
    Set<GameBindingModel> findAllBy();
    @Query(value = "SELECT new softuni.gamestore.models.dto.binding.GameBindingModel(g.title, g.price, g.size, g.youtubeId, g.imageUrl, g.description, g.releaseDate) FROM Game AS g WHERE g.title=:title")
    GameBindingModel findGameByTitle(@Param("title")String title);
}
