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
import static java.util.List.of;

public class ShoppingListTest {

    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing

    @Before
    public void setUp(){
        mockPersistenceDB = mock(RecipePersistence.class);
        fakePersistenceDB = new FakePersistenceDB();
    }

    /**
     * Unit testing
     * Test with a Fake dependency (double test)
     */
    @Test
    public void unitTestingWithFake(){
        List<String> pizzaIngre = new ArrayList<String>(Arrays.asList("flour", "Cheese", "tomato",
                                                                      "onion"));
        List<String> poutineIngre = new ArrayList<String>(Arrays.asList("Potato", "Cheese", "Oil",
                                                                        "Gravy"));

        ShoppingList shoppingList = new ShoppingList(fakePersistenceDB);

        assertNotNull(shoppingList);

        List<String> result1 = shoppingList.getShoppingList("Pizza");
        List<String> result2 = shoppingList.getShoppingList("Poutine");

        assertTrue( pizzaIngre.equals(result1) );
        assertTrue( poutineIngre.equals(result2) );

    }

    /**
     Integrated testing using mock dependency
     */
    @Test
    public void integratedTestingWithMock(){

        ShoppingList shoppingList = new ShoppingList(mockPersistenceDB);
        assertNotNull(shoppingList);
        String name = "no name";

        ArrayList<String> helperIngredient = new ArrayList<>();
        helperIngredient.add("no step 1");
        helperIngredient.add("no step 2");

        ArrayList<String> helperSteps = new ArrayList<>();

        Recipe testRecipe = new Recipe("4","Pizza", "3", helperIngredient,
                                        "2", "1000", helperSteps, "0",
                                        "0", "0", "1");

        when(mockPersistenceDB.getRecipeByName(name)).thenReturn(testRecipe);

        List<String> listToShop = shoppingList.getShoppingList(name);

        assertTrue(helperIngredient.equals(listToShop));

        verify(mockPersistenceDB).getRecipeByName(name);

    }
}