package softuni.gamestore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import softuni.gamestore.config.ModelMapperConfig;

@SpringBootApplication
public class GamestoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamestoreApplication.class, args);
    }

    @Bean
    public ModelMapper configureMapper(){
        ModelMapper modelMapper = new ModelMapper();
        ModelMapperConfig mapperConfig = new ModelMapperConfig(modelMapper);
        return modelMapper;
    }
}