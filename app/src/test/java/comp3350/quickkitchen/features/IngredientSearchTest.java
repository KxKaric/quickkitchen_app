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
        this.mockPersistenceDB = mock(RecipePersistence.class);
        this.fakePersistenceDB = new FakePersistenceDB();
    }

    /**
     * Unit testing
     * Test with a Fake dependency (double test)
     */
    @Test
    public void unitTestingWithFake() {
        ArrayList searchList1 = new ArrayList<>();
        ArrayList searchList2 = new ArrayList<>();
        ArrayList searchList3 = new ArrayList<>();
        ArrayList searchList4 = new ArrayList<>();

        // Should return both Poutine and French Fries ---------------------------------------------
        searchList1.add("Potato");
        IngredientSearch ingreSearch1 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingreSearch1);

        List<Recipe> searchResult1 = ingreSearch1.searchRecipeByIngredient(searchList1);
        assertNotNull(searchResult1);
        assertTrue(2 == searchResult1.size());
        assertTrue("French fries".equals(searchResult1.get(0).getName()));
        assertTrue("Poutine".equals(searchResult1.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return both Poutine and Pizza ----------------------------------------------------
        searchList2.add("Cheese");
        IngredientSearch ingreSearch2 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingreSearch2);

        List<Recipe> searchResult2= ingreSearch2.searchRecipeByIngredient(searchList2);
        assertNotNull(searchResult2);
        assertTrue(2 == searchResult2.size());
        assertTrue("Poutine".equals(searchResult2.get(0).getName()));
        assertTrue("Pizza".equals(searchResult2.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return French Fries, Poutine, and Pizza ------------------------------------------
        searchList3.add("Potato");
        searchList3.add("Cheese");
        IngredientSearch ingreSearch3 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingreSearch3);

        List<Recipe> searchResult3= ingreSearch3.searchRecipeByIngredient(searchList3);
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
        IngredientSearch ingreSearch4 = new IngredientSearch(new FakePersistenceDB());
        assertNotNull(ingreSearch4);

        List<Recipe> searchResult4= ingreSearch3.searchRecipeByIngredient(searchList4);
        assertNotNull(searchResult4);
        assertTrue(4 == searchResult4.size());
        assertTrue("French fries".equals(searchResult4.get(0).getName()));
        assertTrue("Poutine".equals(searchResult4.get(1).getName()));
        assertTrue("Onion Ring".equals(searchResult4.get(2).getName()));
        assertTrue("Pizza".equals(searchResult4.get(3).getName()));

        //------------------------------------------------------------------------------------------
    }

    /**
     * Integrated testing using mock dependency
     * We are not modifying the DB, so no need to make a copy of the real dependency
     */
    @Test
    public void integratedTestingWithMock(){

        // Using mock DB
        ArrayList searchList = new ArrayList<>();

        IngredientSearch ingreSearch = new IngredientSearch(mockPersistenceDB);

        assertNotNull(ingreSearch);

        searchList.add("Empty");

        // Making Mock to return the searchList if getRecipeByIngredient() is called
        when(mockPersistenceDB.getRecipeByIngredient(searchList)).thenReturn(searchList);

        List<String> searchResult = ingreSearch.searchRecipeByIngredient(searchList);

        assertTrue("Empty".equals(searchResult.get(0)));

        verify(mockPersistenceDB).getRecipeByIngredient(searchList);

        System.out.println("End of IngredientSearch feature test.");
    }

    @Test
    public void integratedTesting(){

    }
}
