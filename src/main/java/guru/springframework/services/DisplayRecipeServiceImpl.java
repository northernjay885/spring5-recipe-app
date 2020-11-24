package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
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

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe getRecipeByID(Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if (!recipeOpt.isPresent()) {
           throw new RuntimeException("no recipe is found!");
        }
        return recipeOpt.get();
    }

}
