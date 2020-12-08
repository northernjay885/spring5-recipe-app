package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private IngredientToIngredientCommand converter;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());

        ingredientService = new IngredientServiceImpl(recipeRepository, converter);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        IngredientCommand command = new IngredientCommand();

    }

    @Test
    public void findByRecipeAndRecipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setRecipe(recipe);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setRecipe(recipe);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setRecipe(recipe);

        Set<Ingredient> ingredients = recipe.getIngredients();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(2)).findById(anyLong());

    }
}