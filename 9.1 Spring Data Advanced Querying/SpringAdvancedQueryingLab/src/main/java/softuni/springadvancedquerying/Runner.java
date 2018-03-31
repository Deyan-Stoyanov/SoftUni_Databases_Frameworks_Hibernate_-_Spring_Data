package softuni.springadvancedquerying;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.springadvancedquerying.model.enums.Size;
import softuni.springadvancedquerying.model.ingredients.AmoniumChloride;
import softuni.springadvancedquerying.model.ingredients.BasicIngredient;
import softuni.springadvancedquerying.model.ingredients.Mint;
import softuni.springadvancedquerying.model.ingredients.Nettle;
import softuni.springadvancedquerying.model.labels.ClassicLabel;
import softuni.springadvancedquerying.model.shampoos.BasicShampoo;
import softuni.springadvancedquerying.repositories.BasicIngredientRepository;
import softuni.springadvancedquerying.repositories.LabelRepository;
import softuni.springadvancedquerying.repositories.ShampooRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Application.class);

    private LabelRepository labelRepository;
    private ShampooRepository shampooRepository;
    private BasicIngredientRepository ingredientRepository;

    @Autowired
    public Runner(LabelRepository labelRepository, ShampooRepository shampooRepository, BasicIngredientRepository ingredientRepository) {
        this.labelRepository = labelRepository;
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init(labelRepository, shampooRepository, ingredientRepository);
    }

    public static void init(LabelRepository labelRepository, ShampooRepository shampooRepository, BasicIngredientRepository basicIngredientRepository) {
        ClassicLabel labelFresh = new ClassicLabel("Fresh start", "Excited");
        ClassicLabel labelAutumn = new ClassicLabel("Autumn", "Relaxed");
        ClassicLabel labelSummer = new ClassicLabel("Summer", "Mint");
        ClassicLabel labelWinter = new ClassicLabel("Winter", "Nettle and Mint");
        labelRepository.saveAndFlush(labelFresh);
        labelRepository.saveAndFlush(labelAutumn);
        labelRepository.saveAndFlush(labelSummer);
        labelRepository.saveAndFlush(labelWinter);
        BasicIngredient firstIngredient = new Mint("Mint", BigDecimal.valueOf(3.56));
        BasicIngredient secondIngredient = new Nettle("Nettle", BigDecimal.valueOf(5.12));
        BasicIngredient thirdIngredient = new AmoniumChloride();
        Set<BasicIngredient> ingredients = new HashSet<>();
        ingredients.add(firstIngredient);
        ingredients.add(secondIngredient);
        ingredients.add(thirdIngredient);
        basicIngredientRepository.saveAll(ingredients);
        BasicShampoo springShampoo = new BasicShampoo("Spring", labelFresh, ingredients, BigDecimal.valueOf(3.12), Size.MEDIUM);
        shampooRepository.saveAndFlush(springShampoo);
        System.out.println("Shampoos by size: ");
        shampooRepository.findAllBySize(Size.MEDIUM).forEach(x -> System.out.println(x.toString()));
        System.out.println("Shampoo by size or label: ");
        shampooRepository.findAllBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, labelFresh).forEach(x -> System.out.println(x.toString()));
        Set<String> ingredientsList = Arrays.stream(new String[]{"Mint", "Nettle"}).collect(Collectors.toSet());
        shampooRepository.findAllByIngredientsIn(ingredientsList).forEach(x -> System.out.println(x.getBrand()));
        BigDecimal sum = shampooRepository.findBasicShampooByBrand("Spring");
        System.out.println(sum);
        //basicIngredientRepository.removeBasicIngredientByName("Nettle");
        basicIngredientRepository.updatePriceByName(ingredientsList);
    }
}