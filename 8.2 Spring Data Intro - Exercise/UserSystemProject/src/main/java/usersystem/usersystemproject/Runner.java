package usersystem.usersystemproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import usersystem.usersystemproject.models.entites.Album;
import usersystem.usersystemproject.models.entites.Picture;
import usersystem.usersystemproject.models.entites.Town;
import usersystem.usersystemproject.models.entites.User;
import usersystem.usersystemproject.services.AlbumService;
import usersystem.usersystemproject.services.PictureService;
import usersystem.usersystemproject.services.TownService;
import usersystem.usersystemproject.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Runner implements CommandLineRunner {

    private final AlbumService albumService;
    private final PictureService pictureService;
    private final TownService townService;
    private final UserService userService;

    @Autowired
    public Runner(AlbumService albumService, PictureService pictureService, TownService townService, UserService userService) {
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Picture firstPicture = new Picture("pic1.jpg", "first photo", "user/doc", new HashSet<>());
        Picture secondPicture = new Picture("pic2.jpg", "second photo", "user/doc", new HashSet<>());
        Picture thirdPicture = new Picture("pic3.jpg", "third photo", "user/doc", new HashSet<>());
        Set<Picture> pictures = new HashSet<>();
        pictures.add(firstPicture);
        pictures.add(secondPicture);
        pictures.add(thirdPicture);
        Album album = new Album("this album", "red", true, pictures);
        this.pictureService.savePictureToDb(firstPicture);
        this.pictureService.savePictureToDb(secondPicture);
        this.pictureService.savePictureToDb(thirdPicture);
        this.albumService.saveAlbumToDb(album);
        Town town = new Town("Sofia", "Bulgaria", new HashSet<>(), new HashSet<>());
        Town secondTown = new Town("Belgrade", "Serbia", new HashSet<>(), new HashSet<>());
        this.townService.saveTownToDb(town);
        this.townService.saveTownToDb(secondTown);
        User user = new User();
        user.setUsername("pesho1");
        user.setPassword("pEsho123456*");
        user.setBornTown(town);
        user.setCurrentTown(secondTown);
        user.setEmail("pesho@abv.bg");
        user.setAge(25);
        user.setFirstName("Pesho");
        user.setLastName("Petrov");
        user.setDeleted(false);
        User user1 = new User();
        user1.setUsername("Gosho");
        user1.setPassword("Gosho12345/*");
        user1.setEmail("gosho@gmail.com");
        user1.setLastTimeLoggedIn(new SimpleDateFormat("d MMM yyyy", Locale.UK).parse("11 Oct 2012"));
        this.userService.saveUserToDb(user);
        this.userService.saveUserToDb(user1);

        String provider = reader.readLine();
        printUsersWithProvider(provider);

        markUsersAsDeleted(reader.readLine());

        deleteUsers();
    }

    private void printUsersWithProvider(String provider) {
        List<User> users = this.userService.getAllByEmailEndsWith(provider);
        if(users.isEmpty()){
            System.out.printf("No users found with email domain %s%n", provider);
        } else{
            users.forEach(x -> System.out.println(x.getUsername() + " " + x.getEmail()));
        }
    }

    private void markUsersAsDeleted(String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("dd MMM yyyy", Locale.UK).parse(dateStr);
        this.userService.setUserIsDeleted(date);
        Integer affectedRows = this.userService.setUserDeleted(date);
        if(affectedRows == 0){
            System.out.println("No users have been deleted");
        } else if(affectedRows==1){
            System.out.println("1 user have been deleted");
        } else {
            System.out.printf("%d users have been deleted%n", affectedRows);
        }
    }

    private void deleteUsers(){
        this.userService.deleteAllByIsDeletedEquals(true);
    }
}
