package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Bootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LoadData();
    }

    private void LoadData() {

        System.out.println("find all categories and unitOfMeasures in the database");
        Optional<Category> americanCategoryOpt = categoryRepository.findByDescription("American");
        Optional<Category> italianCategoryOpt = categoryRepository.findByDescription("Italian");
        Optional<Category> mexicanCategoryOpt = categoryRepository.findByDescription("Mexican");
        Optional<Category> fastFoodCategoryOpt = categoryRepository.findByDescription("Fast Food");

        Optional<UnitOfMeasure> UOMTeaspoonOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> UOMTablespoonOpt = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> UOMDashOpt = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> UOMCupOpt = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> UOMPinchOpt = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> UOMOunceOpt = unitOfMeasureRepository.findByDescription("Ounce");

        //get category]
        Category americanCategory;
        if (americanCategoryOpt.isPresent()) {
            americanCategory = americanCategoryOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }
        Category italianCategory;
        if (italianCategoryOpt.isPresent()) {
            italianCategory = italianCategoryOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        Category mexicanCategory;
        if (mexicanCategoryOpt.isPresent()) {
            mexicanCategory = mexicanCategoryOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        Category fastFoodCategory;
        if (fastFoodCategoryOpt.isPresent()) {
            fastFoodCategory = fastFoodCategoryOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        //get unitOfMeasure
        UnitOfMeasure UOMTeaspoon;
        if (UOMTeaspoonOpt.isPresent()) {
            UOMTeaspoon = UOMTeaspoonOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        UnitOfMeasure UOMTablespoon;
        if (UOMTablespoonOpt.isPresent()) {
            UOMTablespoon = UOMTablespoonOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        UnitOfMeasure UOMDash;
        if (UOMDashOpt.isPresent()) {
            UOMDash = UOMDashOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        if (UOMCupOpt.isPresent()) {
            UnitOfMeasure UOMCup = UOMCupOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        if (UOMPinchOpt.isPresent()) {
            UnitOfMeasure UOMPinch = UOMPinchOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        if (UOMOunceOpt.isPresent()) {
            UnitOfMeasure UOMOunce = UOMOunceOpt.get();
        } else {
            throw new RuntimeException("no category exists");
        }

        log.debug("categories and unit of measures loaded!");

        System.out.println("create perfect guacamole recipe");

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(3);
        perfectGuacamole.setSource("Simple Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. " +
                "Score the inside of the avocado with a blunt knife and scoop out " +
                "the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
                + "\n"
                + "2 Mash with a fork: Using a fork, roughly mash the avocado."
                + "(Don't overdo it! The guacamole should be a little chunky.)"
                + "\n"
                + "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."
                + "\n"
                + "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to " +
                "cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        perfectGuacamole.setDifficulty(Difficulty.MODERATE);
        perfectGuacamole.getCategories().add(americanCategory);
        perfectGuacamole.getCategories().add(mexicanCategory);
        Notes notePG = new Notes();
        notePG.setRecipeNotes("This a note for perfect Guacamole");
        perfectGuacamole.setNotes(notePG);

        log.debug("Everything for perfect guacamole loaded except ingredients for it!");

        System.out.println("add ingredients of perfect guacamole");

        Ingredient avocadoPG = new Ingredient();
        avocadoPG.setDescription("avocado");
        avocadoPG.setAmount(BigDecimal.valueOf(2));
        avocadoPG.setUom(UOMDash);
        avocadoPG.setRecipe(perfectGuacamole);

        Ingredient saltPG = new Ingredient();
        saltPG.setDescription("salt");
        saltPG.setAmount(BigDecimal.valueOf(1 / 4));
        saltPG.setUom(UOMTeaspoon);
        saltPG.setRecipe(perfectGuacamole);

        Ingredient lemonJuicePG = new Ingredient();
        lemonJuicePG.setDescription("lemon juice or fresh lime juice");
        lemonJuicePG.setAmount(BigDecimal.valueOf(1));
        lemonJuicePG.setUom(UOMTablespoon);
        lemonJuicePG.setRecipe(perfectGuacamole);

        Ingredient redOnionPG = new Ingredient();
        redOnionPG.setDescription("red onion");
        redOnionPG.setAmount(BigDecimal.valueOf(2));
        redOnionPG.setUom(UOMTablespoon);
        redOnionPG.setRecipe(perfectGuacamole);

        Ingredient serranoChilePG = new Ingredient();
        serranoChilePG.setDescription("serrano chile");
        serranoChilePG.setAmount(BigDecimal.valueOf(2));
        serranoChilePG.setUom(UOMDash);
        serranoChilePG.setRecipe(perfectGuacamole);

        Ingredient cilantroPG = new Ingredient();
        cilantroPG.setDescription("cilantro");
        cilantroPG.setAmount(BigDecimal.valueOf(2));
        cilantroPG.setUom(UOMTablespoon);
        cilantroPG.setRecipe(perfectGuacamole);

        Ingredient blackPepperPG = new Ingredient();
        blackPepperPG.setDescription("black pepper");
        blackPepperPG.setAmount(BigDecimal.valueOf(1));
        blackPepperPG.setUom(UOMDash);
        blackPepperPG.setRecipe(perfectGuacamole);

        Ingredient tomatoPG = new Ingredient();
        tomatoPG.setDescription("tomato");
        tomatoPG.setAmount(BigDecimal.valueOf(1/2));
        tomatoPG.setUom(UOMDash);
        tomatoPG.setRecipe(perfectGuacamole);

        Ingredient radishPG = new Ingredient();
        radishPG.setDescription("red radish or jicama");
        radishPG.setAmount(BigDecimal.valueOf(1/2));
        radishPG.setUom(UOMDash);
        radishPG.setRecipe(perfectGuacamole);

        Ingredient chipsPG = new Ingredient();
        chipsPG.setDescription("Tortilla chips");
        chipsPG.setAmount(BigDecimal.valueOf(1/2));
        chipsPG.setUom(UOMDash);
        chipsPG.setRecipe(perfectGuacamole);

        System.out.println("add ingredients to perfect guacamole");
        Set<Ingredient> ingredientsPG = perfectGuacamole.getIngredients();
        ingredientsPG.add(avocadoPG);
        ingredientsPG.add(saltPG);
        ingredientsPG.add(lemonJuicePG);
        ingredientsPG.add(redOnionPG);
        ingredientsPG.add(serranoChilePG);
        ingredientsPG.add(cilantroPG);
        ingredientsPG.add(blackPepperPG);
        ingredientsPG.add(tomatoPG);
        ingredientsPG.add(radishPG);
        ingredientsPG.add(chipsPG);

        log.debug("Ingredients for perfect guacamole loaded!");

        recipeRepository.save(perfectGuacamole);

        log.debug("recipe for perfect guacamole saved!");

        System.out.println("create spicy grilled chicken recipe");

        Recipe spicyGrilledChicken = new Recipe();
        spicyGrilledChicken.setDescription("Spicy Grilled Chicken Taco");
        spicyGrilledChicken.setPrepTime(20);
        spicyGrilledChicken.setCookTime(30);
        spicyGrilledChicken.setServings(5);
        spicyGrilledChicken.setSource("Simple Recipes");
        spicyGrilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        spicyGrilledChicken.setDifficulty(Difficulty.HARD);
        spicyGrilledChicken.getCategories().add(americanCategory);
        spicyGrilledChicken.getCategories().add(fastFoodCategory);
        Notes noteSGC = new Notes();
        noteSGC.setRecipeNotes("This is a note for spicy grilled chicken");
        spicyGrilledChicken.setNotes(noteSGC);

        log.debug("Everything for spicy grilled chicken loaded except ingredients for it!");

        Ingredient anchoChiliPowderSGC = new Ingredient();
        anchoChiliPowderSGC.setDescription("ancho chili powder");
        anchoChiliPowderSGC.setAmount(BigDecimal.valueOf(2));
        anchoChiliPowderSGC.setUom(UOMTablespoon);
        anchoChiliPowderSGC.setRecipe(spicyGrilledChicken);

        Ingredient driedOreganoSGC = new Ingredient();
        driedOreganoSGC.setDescription("dried oregano");
        driedOreganoSGC.setAmount(BigDecimal.valueOf(1));
        driedOreganoSGC.setUom(UOMTeaspoon);
        driedOreganoSGC.setRecipe(spicyGrilledChicken);

        Ingredient driedCuminSGC = new Ingredient();
        driedCuminSGC.setDescription("dried cumin");
        driedCuminSGC.setAmount(BigDecimal.valueOf(1));
        driedCuminSGC.setUom(UOMTeaspoon);
        driedCuminSGC.setRecipe(spicyGrilledChicken);

        Ingredient sugarSGC = new Ingredient();
        sugarSGC.setDescription("sugar");
        sugarSGC.setAmount(BigDecimal.valueOf(1));
        sugarSGC.setUom(UOMTeaspoon);
        sugarSGC.setRecipe(spicyGrilledChicken);

        Ingredient saltSGC = new Ingredient();
        saltSGC.setDescription("salt");
        saltSGC.setAmount(BigDecimal.valueOf(1/2));
        saltSGC.setUom(UOMTeaspoon);
        saltSGC.setRecipe(spicyGrilledChicken);

        Ingredient cloveGarlicSGC = new Ingredient();
        cloveGarlicSGC.setDescription("clover garlic");
        cloveGarlicSGC.setAmount(BigDecimal.valueOf(1));
        cloveGarlicSGC.setUom(UOMDash);
        cloveGarlicSGC.setRecipe(spicyGrilledChicken);

        Ingredient orangeZestSGC = new Ingredient();
        orangeZestSGC.setDescription("orange zest");
        orangeZestSGC.setAmount(BigDecimal.valueOf(1));
        orangeZestSGC.setUom(UOMTablespoon);
        orangeZestSGC.setRecipe(spicyGrilledChicken);

        Ingredient orangeJuiceSGC = new Ingredient();
        orangeJuiceSGC.setDescription("orange juice");
        orangeJuiceSGC.setAmount(BigDecimal.valueOf(3));
        orangeJuiceSGC.setUom(UOMTablespoon);
        orangeJuiceSGC.setRecipe(spicyGrilledChicken);

        Ingredient oliveOilSGC = new Ingredient();
        oliveOilSGC.setDescription("olive oil");
        oliveOilSGC.setAmount(BigDecimal.valueOf(2));
        oliveOilSGC.setUom(UOMTablespoon);
        oliveOilSGC.setRecipe(spicyGrilledChicken);

        Ingredient chickenThighSGC = new Ingredient();
        chickenThighSGC.setDescription("boneless chicken thigh");
        chickenThighSGC.setAmount(BigDecimal.valueOf(5));
        chickenThighSGC.setUom(UOMDash);
        chickenThighSGC.setRecipe(spicyGrilledChicken);

        System.out.println("add ingredients to spicy grilled chicken");
        Set<Ingredient> ingredientsSGC = spicyGrilledChicken.getIngredients();
        ingredientsSGC.add(anchoChiliPowderSGC);
        ingredientsSGC.add(driedOreganoSGC);
        ingredientsSGC.add(sugarSGC);
        ingredientsSGC.add(saltSGC);
        ingredientsSGC.add(cloveGarlicSGC);
        ingredientsSGC.add(orangeZestSGC);
        ingredientsSGC.add(orangeJuiceSGC);
        ingredientsSGC.add(oliveOilSGC);
        ingredientsSGC.add(chickenThighSGC);

        log.debug("Ingredients for perfect guacamole loaded!");

        recipeRepository.save(spicyGrilledChicken);

        log.debug("recipe for spicy grilled chicken saved!");


    }


}
