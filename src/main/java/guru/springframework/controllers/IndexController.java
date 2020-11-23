package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.DisplayRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

   private final DisplayRecipeService displayRecipeService;

    public IndexController(DisplayRecipeService displayRecipeService) {
        this.displayRecipeService = displayRecipeService;
    }

    @RequestMapping({"/", "/index", "/index.html", ""})
    public String getIndex(Model model) {
        log.debug("I'm be directed to index page!");
        model.addAttribute("recipes", displayRecipeService.getRecipes());
        return "index";
    }
}
