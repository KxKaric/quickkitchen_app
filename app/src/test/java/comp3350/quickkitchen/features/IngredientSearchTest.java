package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.StudPersistenceDB;

public class IngredientSearchTest {

    // Test if search return correct list
    @Test
    public void testIngredientSearch1() {
        ArrayList searchList1 = new ArrayList<>();
        ArrayList searchList2 = new ArrayList<>();
        ArrayList searchList3 = new ArrayList<>();
        ArrayList searchList4 = new ArrayList<>();
        ArrayList searchList5 = new ArrayList<>();

        // Should return both Poutine and French Fries ---------
        searchList1.add("Potato");
        IngredientSearch ingriSearch1 = new IngredientSearch(new StudPersistenceDB());
        assertNotNull(ingriSearch1);

        List<Recipe> searchResult1 = ingriSearch1.searchRecipeByIngredient(searchList1);
        assertNotNull(searchResult1);

        assertTrue(2 == searchResult1.size());
        assertTrue("French fries".equals(searchResult1.get(0).getName()));
        assertTrue("Poutine".equals(searchResult1.get(1).getName()));

        //------------------------------------------------------------------------------------
    }
}
