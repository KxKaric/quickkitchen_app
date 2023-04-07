package comp3350.quickkitchen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.quickkitchen.features.BookMarkTest;
import comp3350.quickkitchen.features.FilterTest;
import comp3350.quickkitchen.features.IngredientSearchTest;
import comp3350.quickkitchen.features.PortionTest;
import comp3350.quickkitchen.features.RankTest;
import comp3350.quickkitchen.features.ShowStepsTest;
import comp3350.quickkitchen.objects.RecipeTest;
import comp3350.quickkitchen.objects.ShoppingListTest;
import comp3350.quickkitchen.persistence.FakePersistenceDBTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookMarkTest.class,
        FilterTest.class,
        IngredientSearchTest.class,
        PortionTest.class,
        RankTest.class,
        ShoppingListTest.class,
        ShowStepsTest.class,
        RecipeTest.class,
        FakePersistenceDBTest.class,
})

public class AllTest {

}
