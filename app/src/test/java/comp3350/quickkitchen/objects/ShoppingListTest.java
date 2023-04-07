package comp3350.quickkitchen.objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.FakePersistenceDB;
import comp3350.quickkitchen.persistence.hsqldb.RecipePersistenceHSQLDB;
import comp3350.quickkitchen.utils.TestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ShoppingListTest {

    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing
    private File tempDB;
    private ShoppingList officialShoppingList;

    @Before
    public void setUp() throws IOException{
        this.mockPersistenceDB = mock(RecipePersistence.class);
        this.fakePersistenceDB = new FakePersistenceDB();
        this.tempDB = TestUtils.copyDB();
        final RecipePersistence realPersistanceDB = new RecipePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.officialShoppingList = new ShoppingList(realPersistanceDB);
    }

    /**
     * Unit testing
     * Test with a Fake dependency (double test)
     */
    @Test
    public void unitTestingWithFake(){

        // Dummy shopping lists for testing
        List<String> pizzaIngre = new ArrayList<String>(Arrays.asList("flour", "Cheese", "tomato",
                                                                      "onion"));
        List<String> poutineIngre = new ArrayList<String>(Arrays.asList("Potato", "Cheese", "Oil",
                                                                        "Gravy"));
        List<String> onionRingIngre = new ArrayList<String>(Arrays.asList("Onion", "Oil"));
        List<String> frenchFryIngre = new ArrayList<String>(Arrays.asList("Potato", "Oil"));

        ShoppingList shoppingList = new ShoppingList(fakePersistenceDB);

        // Testing----------------------------------------------------------------------------------
        assertNotNull(shoppingList);

        List<String> result1 = shoppingList.getShoppingList("Pizza");
        List<String> result2 = shoppingList.getShoppingList("Poutine");
        List<String> result3 = shoppingList.getShoppingList("Onion Ring");
        List<String> result4 = shoppingList.getShoppingList("French fries");

        assertTrue( pizzaIngre.equals(result1) );
        assertTrue( poutineIngre.equals(result2) );
        assertTrue( onionRingIngre.equals(result3) );
        assertTrue( frenchFryIngre.equals(result4) );
        //------------------------------------------------------------------------------------------
    }

    /**
     * Integrated testing using mock dependency
     * We are not modifying the DB, so no need to make a copy of the real dependency
     */
    @Test
    public void integratedTestingWithMock(){

        // Using mock DB
        ShoppingList shoppingList = new ShoppingList(mockPersistenceDB);
        assertNotNull(shoppingList);
        String name = "no name";

        // Create a dummy test Recipe
        ArrayList<String> helperIngredient = new ArrayList<>();
        helperIngredient.add("no ingredient 1");
        helperIngredient.add("no ingredient 2");

        ArrayList<String> helperSteps = new ArrayList<>();

        Recipe testRecipe = new Recipe("4","Pizza", "3", helperIngredient,
                                        "2", "1000", helperSteps, "0",
                                        "0", "0", "1", "1");

        // Making Mock to return the test recipe if getRecipeByName() is called
        when(mockPersistenceDB.getRecipeByName(name)).thenReturn(testRecipe);

        List<String> listToShop = shoppingList.getShoppingList(name);

        assertTrue(helperIngredient.equals(listToShop));

        verify(mockPersistenceDB).getRecipeByName(name);
    }

    @Test
    public void integratedTesting1(){
        List<String> pizzaIngre = new ArrayList<String>(Arrays.asList("5lb flour", " 1lb tomato",
                                                                " 1onion", " cheese"));
        List<String> result = officialShoppingList.getShoppingList("Pizza");

        // Testing
        assertNotNull(result);
        assertTrue(result.equals(pizzaIngre));

    }

    @Test
    public void integratedTesting2(){
        List<String> poutineIngre = new ArrayList<String>(Arrays.asList("1lb potato", " cheese", " oil",
                " gravy"));
        List<String> result = officialShoppingList.getShoppingList("Poutine");

        // Testing
        assertNotNull(result);
        assertTrue(result.equals(poutineIngre));

        System.out.println("End of ShoppingList feature test.");
    }
}