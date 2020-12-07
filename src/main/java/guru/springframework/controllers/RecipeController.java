package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.DisplayRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final DisplayRecipeService displayRecipeService;

    public RecipeController(DisplayRecipeService displayRecipeService) {
        this.displayRecipeService = displayRecipeService;
    }

    @RequestMapping("recipe/show/{id}")
    public String index(@PathVariable String id, Model model) {
        model.addAttribute("recipe", displayRecipeService.getRecipeByID(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe/")
    public String savedOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = displayRecipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }

}
