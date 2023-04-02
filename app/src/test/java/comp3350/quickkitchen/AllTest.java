package comp3350.quickkitchen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.quickkitchen.features.IngredientSearchTest;
import comp3350.quickkitchen.features.ShoppingListTest;
import comp3350.quickkitchen.persistence.FakePersistenceDBTest;
import comp3350.quickkitchen.objects.RecipeTest;
import comp3350.quickkitchen.features.ShowStepsTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        IngredientSearchTest.class,
        ShoppingListTest.class,
        FakePersistenceDBTest.class,
        RecipeTest.class,
        ShowStepsTest.class,

})

public class AllTest {

}
