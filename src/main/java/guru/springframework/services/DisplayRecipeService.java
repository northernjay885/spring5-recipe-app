package guru.springframework.services;


import guru.springframework.domain.Recipe;
import java.util.Set;

public interface DisplayRecipeService {

    Set<Recipe> getRecipes();

}
