package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.FakePersistenceDB;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


public class IngredientSearchTest {
    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing

    @Before
    public void setUp(){
        mockPersistenceDB = mock(RecipePersistence.class);
        fakePersistenceDB = new FakePersistenceDB();
    }

    // Test if search return correct list
    // A double test with a fake DB
    @Test
    public void unitTestingWithFake() {
        ArrayList searchList1 = new ArrayList<>();
        ArrayList searchList2 = new ArrayList<>();
        ArrayList searchList3 = new ArrayList<>();
        ArrayList searchList4 = new ArrayList<>();
        ArrayList searchList5 = new ArrayList<>();

        // Should return both Poutine and French Fries ---------------------------------------
        searchList1.add("Potato");
        IngredientSearch ingriSearch1 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingriSearch1);

        List<Recipe> searchResult1 = ingriSearch1.searchRecipeByIngredient(searchList1);
        assertNotNull(searchResult1);
        assertTrue(2 == searchResult1.size());
        assertTrue("French fries".equals(searchResult1.get(0).getName()));
        assertTrue("Poutine".equals(searchResult1.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return both Poutine and Pizza ----------------------------------------------------
        searchList2.add("Cheese");
        IngredientSearch ingriSearch2 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingriSearch2);

        List<Recipe> searchResult2= ingriSearch2.searchRecipeByIngredient(searchList2);
        assertNotNull(searchResult2);
        assertTrue(2 == searchResult2.size());
        assertTrue("Poutine".equals(searchResult2.get(0).getName()));
        assertTrue("Pizza".equals(searchResult2.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return French Fries, Poutine, and Pizza ------------------------------------------
        searchList3.add("Potato");
        searchList3.add("Cheese");
        IngredientSearch ingriSearch3 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingriSearch3);

        List<Recipe> searchResult3= ingriSearch3.searchRecipeByIngredient(searchList3);
        assertNotNull(searchResult3);
        assertTrue(3 == searchResult3.size());
        assertTrue("French fries".equals(searchResult3.get(0).getName()));
        assertTrue("Poutine".equals(searchResult3.get(1).getName()));
        assertTrue("Pizza".equals(searchResult3.get(2).getName()));

        //------------------------------------------------------------------------------------------

        // Should return French Fries, Poutine, Onion Ring, and Pizza ------------------------------
        searchList4.add("Potato");
        searchList4.add("Cheese");
        searchList4.add("Oil");
        IngredientSearch ingriSearch4 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingriSearch4);

        List<Recipe> searchResult4= ingriSearch3.searchRecipeByIngredient(searchList4);
        assertNotNull(searchResult3);
        assertTrue(4 == searchResult4.size());
        assertTrue("French fries".equals(searchResult4.get(0).getName()));
        assertTrue("Poutine".equals(searchResult4.get(1).getName()));
        assertTrue("Onion Ring".equals(searchResult4.get(2).getName()));
        assertTrue("Pizza".equals(searchResult4.get(3).getName()));

        //-----------------------------------------------------------------------------------------
    }

    public void integratedTestingWithMock() {
        ArrayList searchList1 = new ArrayList<>();
        ArrayList searchList2 = new ArrayList<>();

        IngredientSearch ingriSearch = new IngredientSearch(mockPersistenceDB);

        searchList1.add("Empty");

        when(mockPersistenceDB.getRecipeByIngredient(searchList1)).thenReturn(searchList1);

        List<String> searchResult= new ArrayList<String>();
        searchResult = ingriSearch.searchRecipeByIngredient(searchList1);

        assertTrue("Empty".equals(searchResult.get(0)));

        verify(mockPersistenceDB).getRecipeByName("Name");
    }
}
