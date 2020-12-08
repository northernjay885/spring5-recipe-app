package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.DisplayRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final DisplayRecipeService displayRecipeService;

    public RecipeController(DisplayRecipeService displayRecipeService) {
        this.displayRecipeService = displayRecipeService;
    }

    @RequestMapping("recipe/{id}/show")
    public String index(@PathVariable String id, Model model) {
        model.addAttribute("recipe", displayRecipeService.getRecipeByID(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String savedOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = displayRecipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", displayRecipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {

        log.debug("Deleting id" + id);
        displayRecipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }



}
