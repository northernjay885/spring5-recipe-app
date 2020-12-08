package guru.springframework.controllers;


import guru.springframework.services.DisplayRecipeService;
import guru.springframework.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final DisplayRecipeService displayRecipeService;

    public IngredientController(IngredientService ingredientService, DisplayRecipeService displayRecipeService) {
        this.ingredientService = ingredientService;
        this.displayRecipeService = displayRecipeService;
    }

    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredient(@PathVariable String recipeId, Model model) {

        model.addAttribute("recipe", displayRecipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
}
