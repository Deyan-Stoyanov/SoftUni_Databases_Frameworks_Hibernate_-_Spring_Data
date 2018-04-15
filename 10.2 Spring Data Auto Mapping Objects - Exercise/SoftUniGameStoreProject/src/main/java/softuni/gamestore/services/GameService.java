package softuni.gamestore.services;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.entities.Game;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public interface GameService {
    GameBindingModel findGameDtoById(Long id);
    Long findGameById(Long id);
    void registerNewGameInSystem(GameBindingModel model);
    void updateGameTitle(@Param("title")String title, @Param("id")Long id);
    void updateGamePrice(@Param("price")BigDecimal price, @Param("id")Long id);
    void updateGameSize(@Param("size")Double size, @Param("id")Long id);
    void updateGameYoutubeId(@Param("youtubeId")String youtubeId, @Param("id")Long id);
    void updateGameImageUrl(@Param("imageUrl")String imageUrl, @Param("id")Long id);
    void updateGameDescription(@Param("description")String description, @Param("id")Long id);
    void updateGameReleaseDate(@Param("releaseDate")Date releaseDate, @Param("id")Long id);
    void deleteGameById(Long id);
    Set<GameBindingModel> findAllBy();
    GameBindingModel findGameByTitle(@Param("title")String title);

}
