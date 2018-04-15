package softuni.gamestore;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.gamestore.models.dto.binding.DTOConvertUtil;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;
import softuni.gamestore.services.GameService;
import softuni.gamestore.services.RoleService;
import softuni.gamestore.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {
    private final GameService gameService;
    private final UserService userService;
    private final RoleService roleService;
    private final Set<Long> usersInSystem;
    private Long loggedInUser;
    private HashSet<Long> gamesInSystem;
    private final DTOConvertUtil dtoConvertUtil;
    private HashSet<GameBindingModel> shoppingCart;
    private final ModelMapper modelMapper;


    @Autowired
    public Runner(GameService gameService, UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.dtoConvertUtil = new DTOConvertUtil();
        this.usersInSystem = new HashSet<>();
        this.gamesInSystem = new HashSet<>();
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] input = reader.readLine().split("\\|");
            if (input[0].equalsIgnoreCase("End")) {
                break;
            }
            switch (input[0]) {
                case "RegisterUser":
                    registerUser(input);
                    break;
                case "LoginUser":
                    loginUser(input);
                    break;
                case "LogoutUser":
                    logoutUser();
                    break;
                case "AddGame":
                    addGame(input);
                    break;
                case "EditGame":
                    editGame(input);
                    break;
                case "DeleteGame":
                    deleteGame(input);
                    break;
                case "AllGame":
                    listAll();
                    break;
                case "DetailGame":
                    getDetailsOfGame(input[1]);
                    break;
                case "OwnedGame":
                    getOwnedGames(loggedInUser);
                    break;
                case "AddItem":
                    addItemToCart(input[1]);
                    break;
                case "RemoveItem":
                    removeItemFromCart(input[1]);
                    break;
                case "BuyItem":
                    buyItemsInCart();
                    break;
            }
        }

    }

    private void buyItemsInCart() {
        if(this.loggedInUser < 1){
            System.out.println("No user logged in!");
        } else {
            if(!shoppingCart.isEmpty()){
                System.out.println("Successfully bought games: ");
                for (GameBindingModel g : shoppingCart) {
                    this.modelMapper
                            .map(this.userService.findUserById(loggedInUser), User.class)
                            .getGames().add(this.modelMapper.map(g, Game.class));
                    System.out.println("- " + g.getTitle());
                }
                this.shoppingCart = new HashSet<>();
            } else {
                System.out.println("Shopping cart is empty!");
            }
        }
    }

    private void removeItemFromCart(String title) {
        for (GameBindingModel g : shoppingCart) {
            if (g.getTitle().equals(title)) {
                System.out.println(g.getTitle() + " removed from cart.");
                shoppingCart.remove(g);
            }
        }
    }

    private void addItemToCart(String title) {
        GameBindingModel addedGame = this.gameService.findGameByTitle(title);
        shoppingCart.add(addedGame);
        System.out.println(addedGame.getTitle() + " added to cart.");
    }

    private void getOwnedGames(Long id) {
        if(this.loggedInUser < 1){
            System.out.println("No user logged in!");
        } else {
            Set<GameBindingModel> ownedGames = this.userService.findOwnedGames(id);
            for (GameBindingModel g : ownedGames) {
                System.out.println(g.getTitle());
            }
        }
    }

    private void getDetailsOfGame(String title) {
        GameBindingModel g = this.gameService.findGameByTitle(title);
        System.out.println("Title: " + g.getTitle());
        System.out.println("Price: " + g.getPrice());
        System.out.println("Description: " + g.getDescription());
        System.out.println("Release date: " + g.getReleaseDate().toString());
    }

    private void listAll() {
        this.gameService.findAllBy().forEach(x -> System.out.println(x.getTitle() + " " + x.getPrice()));
    }

    private void deleteGame(String[] input) {
        if (!this.userService
                .findUserById(loggedInUser)
                .getRoles()
                .contains(this.roleService
                        .getRoleByName(RoleService.Roles.ADMIN)) || loggedInUser < 1) {
            System.out.println("Only admins can delete games!");
        } else {
            if (this.gameService.findGameById(Long.parseLong(input[1])) > 0) {
                System.out.println(this.gameService.findGameDtoById(Long.parseLong(input[1])).getTitle() + " deleted");
                this.gameService.deleteGameById(Long.parseLong(input[1]));
            }
        }
    }

    private void editGame(String[] input) throws ParseException {
        if (loggedInUser < 1) {
            System.out.println("Only admins can edit games!");
        } else {
            Long id = Long.parseLong(input[1]);
            for (int i = 2; i < input.length; i++) {
                String[] command = input[i].split("\\=");
                switch (command[0]) {
                    case "title":
                        if (command[1].matches("[A-Z]([a-z]{2,99})")) {
                            this.gameService.updateGameTitle(command[1], id);
                        }
                        break;
                    case "price":
                        if (Double.parseDouble(command[1]) > 0) {
                            this.gameService.updateGamePrice(BigDecimal.valueOf(Double.parseDouble(command[1])), id);
                        }
                        break;
                    case "size":
                        if (Double.parseDouble(command[1]) > 0) {
                            this.gameService.updateGameSize(Double.parseDouble(command[1]), id);
                        }
                        break;
                    case "trailer":
                        if (command[1].length() == 11 && command[1].matches("[a-zA-Z0-9]{11}")) {
                            this.gameService.updateGameYoutubeId(command[1], id);
                        }
                        break;
                    case "url":
                        if ((command[1].startsWith("http://") || command[1].startsWith("https://")) && command[1].length() >= 20) {
                            this.gameService.updateGameImageUrl(command[1], id);
                        }
                        break;
                    case "description":
                        if (command[1].length() >= 20) {
                            this.gameService.updateGameDescription(command[1], id);
                        }
                        break;
                    case "releaseDate":
                        this.gameService.updateGameReleaseDate(new SimpleDateFormat("dd-MM-yyyy").parse(command[1]), id);
                        break;
                }
            }
            System.out.println(this.gameService.findGameDtoById(Long.parseLong(input[1])).getTitle() + " edited");
        }
    }

    private void addGame(String[] input) {
        if (loggedInUser < 1L) {
            System.out.println("Only admins can add games!");
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                GameBindingModel model = new GameBindingModel(input[1], BigDecimal.valueOf(Double.parseDouble(input[2])), Double.parseDouble(input[3]), input[4], input[5], input[6], sdf.parse(input[7]));
                this.gameService.registerNewGameInSystem(model);
                System.out.println("Added " + model.getTitle());
            } catch (Exception e) {
                System.out.println("Error! Game could not be added");
            }
        }
    }

    private void logoutUser() {
        if (loggedInUser != 0L) {
            String name = this.userService.findUserById(loggedInUser).getFullName();
            System.out.printf("User %s successfully logged out%n", name);
            loggedInUser = 0L;
        } else {
            System.out.println("Cannot log out. No user was logged in.");
        }
    }


    private void loginUser(String[] input) {
        String email = input[1];
        String password = input[2];
        boolean userExists = false;
        for (Long id : usersInSystem) {
            if (this.userService.findUserById(id).getEmail().equals(email)) {
                if (this.userService.findUserById(id).getPassword().equals(password)) {
                    loggedInUser = id;
                    System.out.println("Successfully logged in " + this.userService.findUserById(id).getFullName());
                    userExists = true;
                    break;
                }
            }
        }
        if (!userExists) {
            System.out.println("Incorrect username / password");
        }
    }

    private void registerUser(String[] input) {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        try {
            userRegisterBindingModel.setEmail(input[1]);
            userRegisterBindingModel.setPassword(input[2]);
            userRegisterBindingModel.setConfirmPassword(input[3]);
            userRegisterBindingModel.setFullName(input[4]);
            Long id = this.userService.register(userRegisterBindingModel);
            if (id != null) {
                this.usersInSystem.add(id);
                System.out.println(userRegisterBindingModel.getFullName() + " was registered");
            }
        } catch (Exception e) {
            System.out.println("Incorrect email.");
        }
    }
}
