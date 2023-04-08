package comp3350.quickkitchen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookMarkSystemTest.class,
        IngredientSearchSystemTest.class,
        RecipeListSystemTest.class,
        ShoppingListSystemTest.class,
        ShowStepsSystemTest.class,
})

public class AllSystemTests {

}
