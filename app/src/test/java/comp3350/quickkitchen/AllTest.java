package comp3350.quickkitchen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.quickkitchen.features.IngredientSearchTest;
import comp3350.quickkitchen.persistence.StudPersistenceDBTest;
import comp3350.quickkitchen.objects.RecipeTest;
import comp3350.quickkitchen.features.SearchCalTest;
import comp3350.quickkitchen.features.ShowStepsTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        IngredientSearchTest.class,
        StudPersistenceDBTest.class,
        RecipeTest.class,
        ShowStepsTest.class,
        SearchCalTest.class,

})

public class AllTest {

}