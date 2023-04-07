package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.FakePersistenceDB;
import comp3350.quickkitchen.persistence.hsqldb.RecipePersistenceHSQLDB;
import comp3350.quickkitchen.utils.TestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class PortionTest {
    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing
    private File tempDB;
    private Portion officialPortion;

    @Before
    public void setUp() throws IOException{
        this.mockPersistenceDB = mock(RecipePersistence.class);
        this.fakePersistenceDB = new FakePersistenceDB();
        this.tempDB = TestUtils.copyDB();
        final RecipePersistence realPersistanceDB = new RecipePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.officialPortion = new Portion(realPersistanceDB);
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

    }

    /**
     * Integrated testing using mock dependency
     * We are not modifying the DB, so no need to make a copy of the real dependency
     */
    @Test
    public void integratedTestingWithMock(){
        // Making instance of Portion with mock db
        Portion portion = new Portion(mockPersistenceDB);
        assertNotNull(portion);

        // Making dummy recipe for test
        ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList("flour", "Cheese", "tomato",
                "onion"));

        ArrayList<String> steps = new ArrayList<String>(Arrays.asList("step 1", "step2", "step3",
                "step4"));

        Recipe recipe = new Recipe("2","Recipe1", "3", ingredients,
                "2", "900", steps, "0",
                "0", "0", "1", "1");

        String name = "";
        List<String> result = new ArrayList<>();

        // Testing results (correct methods called)
        when(mockPersistenceDB.getRecipeByName(name)).thenReturn(recipe);
        List<String> ingredientList = portion.ingredientsWithPortion(name, 2);

        assertNotNull(ingredientList);
        assertTrue(ingredientList.equals(ingredients));

        verify(mockPersistenceDB).getRecipeByName(name);
    }

    // Integrated testing with the real dependency (real SQL db)
    @Test
    public void integratedTesting1(){
        List<String> ingredientList = officialPortion.ingredientsWithPortion("Onion Ring", 3);
        assertNotNull(ingredientList);
        assertTrue( 2 == ingredientList.size());
        assertTrue( 6 == Character.getNumericValue(ingredientList.get(0).charAt(0)) );
    }

    @Test
    public void integratedTesting2(){
        List<String> ingredientList = officialPortion.ingredientsWithPortion("Poutine", 4);
        assertNotNull(ingredientList);
        assertTrue( 4 == ingredientList.size());
        assertTrue( 4 == Character.getNumericValue(ingredientList.get(0).charAt(0)) );

        System.out.println("End of Portion feature test.");
    }
}