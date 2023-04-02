package comp3350.quickkitchen.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class FakePersistenceDBTest {

    /**
     * Unit testing that test our FakePersistenceDB
     * This FakePersistenceDB is used for double test of features
     */

    // Asserting the correctness of the db
    @Test
    public void testRecipeDatabase(){

        FakePersistenceDB db = new FakePersistenceDB();
        assertNotNull(db);

        // Asserting that the Fake DB created the recipes as desired -------------------------------

        ArrayList<Recipe> recipeList = db.getDatabase();
        assertNotNull(recipeList);
        assertTrue( 4 == recipeList.size() );   // Should have 4 recipes

        // The first recipe in the list
        Recipe recipe1 = recipeList.get(0);
        assertTrue( "French fries".equals(recipe1.getName()) );
        assertTrue( "2".equals(recipe1.getDifficulty()) );
        assertTrue( "1".equals(recipe1.getPortion()) );
        assertTrue( "300".equals(recipe1.getCalories()) );
        assertTrue( recipe1.isVegeterian() );
        assertTrue( !recipe1.isGultenfree() );
        assertTrue( recipe1.isDairyFree() );

        // The second recipe in the list
        Recipe recipe2 = recipeList.get(1);
        assertTrue( "Poutine".equals(recipe2.getName()) );
        assertTrue( "3".equals(recipe2.getDifficulty()) );
        assertTrue( "2".equals(recipe2.getPortion()) );
        assertTrue( "900".equals(recipe2.getCalories()) );
        assertTrue( !recipe2.isVegeterian() );
        assertTrue( !recipe2.isGultenfree() );
        assertTrue( !recipe2.isDairyFree() );

        // The third recipe in the list
        Recipe recipe3 = recipeList.get(2);
        assertTrue( "Onion Ring".equals(recipe3.getName()) );
        assertTrue( "3".equals(recipe3.getDifficulty()) );
        assertTrue( "2".equals(recipe3.getPortion()) );
        assertTrue( "500".equals(recipe3.getCalories()) );
        assertTrue( !recipe2.isVegeterian() );
        assertTrue( !recipe2.isGultenfree() );
        assertTrue( !recipe2.isDairyFree() );

        // The fourth recipe in the list
        Recipe recipe4 = recipeList.get(3);
        assertTrue( "Pizza".equals(recipe4.getName()) );
        assertTrue( "3".equals(recipe4.getDifficulty()) );
        assertTrue( "2".equals(recipe4.getPortion()) );
        assertTrue( "1000".equals(recipe4.getCalories()) );
        assertTrue( !recipe4.isVegeterian() );
        assertTrue( !recipe4.isGultenfree() );
        assertTrue( !recipe4.isDairyFree() );

        // -----------------------------------------------------------------------------------------

        // Test getRecipeByName()
        Recipe frenchFries = db.getRecipeByName("French fries");
        Recipe pizza = db.getRecipeByName("Pizza");
        Recipe poutine = db.getRecipeByName("Poutine");
        Recipe onionRing = db.getRecipeByName("Onion Ring");

        assertNotNull(frenchFries);
        assertNotNull(pizza);
        assertNotNull(pizza);
        assertNotNull(pizza);

        assertTrue(frenchFries.getName().equalsIgnoreCase("French fries"));
        assertTrue(pizza.getName().equalsIgnoreCase("Pizza"));
        assertTrue(poutine.getName().equalsIgnoreCase("Poutine"));
        assertTrue(onionRing.getName().equalsIgnoreCase("Onion Ring"));
        //------------------------------------------------------------------------------------------

        // Test getRecipeByIngredient()
        ArrayList searchList1 = new ArrayList<String>(Arrays.asList("Potato"));
        ArrayList searchList2 = new ArrayList<String>(Arrays.asList("Cheese"));
        ArrayList searchList3 = new ArrayList<String>(Arrays.asList("Potato", "Cheese"));
        ArrayList searchList4 = new ArrayList<String>(Arrays.asList("Potato", "Cheese", "Oil"));

        List<Recipe> searchResult1 = db.getRecipeByIngredient(searchList1);
        List<Recipe> searchResult2 = db.getRecipeByIngredient(searchList2);
        List<Recipe> searchResult3 = db.getRecipeByIngredient(searchList3);
        List<Recipe> searchResult4 = db.getRecipeByIngredient(searchList4);

        // Should return both Poutine and French Fries ---------------------------------------------
        assertNotNull(searchResult1);
        assertTrue(2 == searchResult1.size());
        assertTrue("French fries".equals(searchResult1.get(0).getName()));
        assertTrue("Poutine".equals(searchResult1.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return both Poutine and Pizza ----------------------------------------------------
        assertNotNull(searchResult2);
        assertTrue(2 == searchResult2.size());
        assertTrue("Poutine".equals(searchResult2.get(0).getName()));
        assertTrue("Pizza".equals(searchResult2.get(1).getName()));

        //------------------------------------------------------------------------------------------

        // Should return French Fries, Poutine, and Pizza ------------------------------------------
        assertNotNull(searchResult3);
        assertTrue(3 == searchResult3.size());
        assertTrue("French fries".equals(searchResult3.get(0).getName()));
        assertTrue("Poutine".equals(searchResult3.get(1).getName()));
        assertTrue("Pizza".equals(searchResult3.get(2).getName()));

        //------------------------------------------------------------------------------------------

        // Should return French Fries, Poutine, Onion Ring, and Pizza ------------------------------
        assertNotNull(searchResult4);
        assertTrue(4 == searchResult4.size());
        assertTrue("French fries".equals(searchResult4.get(0).getName()));
        assertTrue("Poutine".equals(searchResult4.get(1).getName()));
        assertTrue("Onion Ring".equals(searchResult4.get(2).getName()));
        assertTrue("Pizza".equals(searchResult4.get(3).getName()));

        System.out.println("End of FakePersistenceDB dependency test.");
    }
}