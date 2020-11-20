package guru.springframework.services;

import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class DisplayRecipeServiceImpl implements DisplayRecipeService {
    private RecipeRepository recipeRepository;

    public DisplayRecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeRepository getRecipeRepository() {
        return recipeRepository;
    }

    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
}
