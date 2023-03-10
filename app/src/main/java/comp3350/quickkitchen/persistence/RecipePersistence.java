package comp3350.quickkitchen.persistence;

import comp3350.quickkitchen.objects.Recipe;
import java.util.ArrayList;
import java.util.List;

public interface RecipePersistence {
    // the interface of Recipe persistence
    Recipe getRecipeByName(String name);

    List<Recipe> getRecipeByIngredient(List<String> ingredients);
}
