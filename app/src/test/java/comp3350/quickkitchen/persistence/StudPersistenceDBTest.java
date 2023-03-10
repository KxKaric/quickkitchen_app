package comp3350.quickkitchen.persistence;
import comp3350.quickkitchen.objects.Recipe;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class StudPersistenceDBTest {

    // Asserting the correctness of the db
    @Test
    public void testRecipeDatabase(){

        StudPersistenceDB db = new StudPersistenceDB();
        assertNotNull(db);

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

    }
}