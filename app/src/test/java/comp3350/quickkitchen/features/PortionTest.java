package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.FakePersistenceDB;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class PortionTest {
    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing

    @Before
    public void setUp(){
        this.mockPersistenceDB = mock(RecipePersistence.class);
        this.fakePersistenceDB = new FakePersistenceDB();
    }

    /**
     * Unit testing
     * Test with a Fake dependency (double test)
     */

    @Test
    public void unitTestingWithFake(){
        Portion portion = new Portion(fakePersistenceDB);

        ArrayList<String> helperIngredient = new ArrayList<>();
        ArrayList<String> helperSteps = new ArrayList<>();

        // Create dummy Recipe instances
        helperIngredient.add("1lb Potato");
        helperIngredient.add("2lb Cheese");
        helperIngredient.add("Oil");
        helperIngredient.add("3lb Gravy");

        helperSteps.add("no step 1");
        helperSteps.add("no step 2");

        Recipe testRecipe = new Recipe("0","dummy1", "3", helperIngredient,
                "2", "1000", helperSteps, "0",
                "0", "0", "1", "1");

        // Start testing ---------------------------------------------------------------------------
        List<String> ingredientList1 = portion.ingredientsWithPortion("Onion Ring", 3);
        assertNotNull(ingredientList1);
        assertTrue( 2 == ingredientList1.size());

        List<String> ingredientList2 = portion.ingredientsWithPortion(testRecipe, 3);
        assertNotNull(ingredientList2);
        assertTrue(4 == ingredientList2.size());

        // Test if scaling work correctly
        assertTrue( 3 == Character.getNumericValue(ingredientList2.get(0).charAt(0)) );
        assertTrue( 6 == Character.getNumericValue(ingredientList2.get(1).charAt(0)) );
        assertTrue( 9 == Character.getNumericValue(ingredientList2.get(3).charAt(0)) );
        //------------------------------------------------------------------------------------------

        System.out.println("End of Portion feature test.");
    }
}