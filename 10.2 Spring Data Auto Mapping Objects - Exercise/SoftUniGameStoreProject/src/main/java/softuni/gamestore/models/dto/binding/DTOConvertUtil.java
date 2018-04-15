package softuni.gamestore.models.dto.binding;

import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DTOConvertUtil {

    public DTOConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, destinationClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        List<D> resultList = new ArrayList<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    public static <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }

    public static UserRegisterBindingModel convertUserToUserDTO(UserRegisterBindingModel user) {
        Converter<String, String> toUpperConverter = ctx -> ctx.getSource() == null ? "" : ctx.getSource().toUpperCase();
        ExpressionMap<User, UserRegisterBindingModel> mappingWithConverter =
                m -> m.map(User::getFullName, UserRegisterBindingModel::setFullName);
        return convertCustom(user, UserRegisterBindingModel.class, new ExpressionMap[]{mappingWithConverter});
    }

    public static User convertUserDTOToUser(UserRegisterBindingModel userDTO) {
        Converter<String, String> toLowerConverter = ctx -> ctx.getSource() == null ? "" : ctx.getSource().toLowerCase();

        ExpressionMap<UserRegisterBindingModel, User> mappingWithConverter =
                m -> m.using(toLowerConverter)
                        .<String> map(UserRegisterBindingModel::getFullName, (dest, v) -> dest.setFullName(v));
        return convertCustom(userDTO, User.class, mappingWithConverter);
    }

    private  static <S, D> D convertCustom(S source, Class<D> destClass, ExpressionMap<S, D>... exprMaps) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<S, D> typeMap = mapper.createTypeMap((Class<S>) source.getClass(), destClass);
        for (ExpressionMap<S, D> exprMap : exprMaps) {
            typeMap.addMappings(exprMap);
        }
        return typeMap.map(source);
    }

    public static Set<GameBindingModel> convertGamesToGameDtos(Iterable<Game> games) {
        ModelMapper mapper = new ModelMapper();
        Set<GameBindingModel> resultList = new HashSet<>();

        for (Game game : games) {
            GameBindingModel employeeDto = convertGameToGameDto(game);
            resultList.add(employeeDto);
        }

        return resultList;
    }

    private static GameBindingModel convertGameToGameDto(Game game) {
        ExpressionMap<Game, GameBindingModel> mappingWithConverter =
                m -> {
                    m.map(Game::getTitle, GameBindingModel::setTitle);
                    m.map(Game::getDescription, GameBindingModel::setDescription);
                    m.map(Game::getImageUrl, GameBindingModel::setImageUrl);
                    m.map(Game::getPrice, GameBindingModel::setPrice);
                    m.map(Game::getReleaseDate, GameBindingModel::setReleaseDate);
                    m.map(Game::getSize, GameBindingModel::setSize);
                    m.map(Game::getYoutubeId, GameBindingModel::setYoutubeId);
                };
        return convertCustom(game, GameBindingModel.class, new ExpressionMap[]{mappingWithConverter});
    }
}
