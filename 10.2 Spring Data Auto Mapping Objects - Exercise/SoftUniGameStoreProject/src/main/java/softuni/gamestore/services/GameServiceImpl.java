package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.dto.binding.DTOConvertUtil;
import softuni.gamestore.config.ModelMapperConfig;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.repositories.GameRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapperConfig config;
    private final ModelMapper modelMapper;
    private final DTOConvertUtil dtoConvertUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapperConfig config, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.config = config;
        this.modelMapper = modelMapper;
        this.dtoConvertUtil = new DTOConvertUtil();
    }

    @Override
    public GameBindingModel findGameDtoById(Long id) {
        GameBindingModel gameBindingModel = this.modelMapper.map(this.gameRepository.findById(id), GameBindingModel.class);
        return gameBindingModel;
    }

    @Override
    public Long findGameById(Long id) {
        if(this.gameRepository.findById(id).get().getId() != 0){
            return this.gameRepository.findById(id).get().getId();
        }
        return 0L;
    }

    public void registerNewGameInSystem(GameBindingModel model) {
        this.modelMapper.addMappings(new PropertyMap<GameBindingModel, Game>() {
            @Override
            protected void configure() {
                map().setTitle(source.getTitle());
                map().setDescription(source.getDescription());
                map().setImageUrl(source.getImageUrl());
                map().setPrice(source.getPrice());
                map().setSize(source.getSize());
                map().setReleaseDate(source.getReleaseDate());
                map().setYoutubeId(source.getYoutubeId());
                map().setUsers(new HashSet<>());
            }
        });
        Game game = this.modelMapper.map(model, Game.class);
        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public void updateGameTitle(String title, Long id) {
        this.gameRepository.updateGameTitle(title, id);
    }

    @Override
    public void updateGamePrice(BigDecimal price, Long id) {
        this.gameRepository.updateGamePrice(price, id);
    }

    @Override
    public void updateGameSize(Double size, Long id) {
        this.gameRepository.updateGameSize(size, id);
    }

    @Override
    public void updateGameYoutubeId(String youtubeId, Long id) {
        this.gameRepository.updateGameYoutubeId(youtubeId, id);
    }

    @Override
    public void updateGameImageUrl(String imageUrl, Long id) {
        this.gameRepository.updateGameImageUrl(imageUrl, id);
    }

    @Override
    public void updateGameDescription(String description, Long id) {
        this.gameRepository.updateGameDescription(description, id);
    }

    @Override
    public void updateGameReleaseDate(Date releaseDate, Long id) {
        this.gameRepository.updateGameReleaseDate(releaseDate, id);
    }

    @Override
    public void deleteGameById(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public Set<GameBindingModel> findAllBy() {
        Set<GameBindingModel> allGames = new HashSet<>(this.gameRepository.findAllBy());
        return allGames;
    }

    @Override
    public GameBindingModel findGameByTitle(String title) {
        return this.gameRepository.findGameByTitle(title);
    }

}
