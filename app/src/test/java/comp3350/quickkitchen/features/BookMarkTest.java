package comp3350.quickkitchen.features;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class BookMarkTest {

    /**
     * Unit testing
     * No need for dependency. Just the unit testing itself
     */

    @Test
    public void testBookMark(){

        BookMark bookmark = new BookMark();

        assertNotNull(bookmark);

        // Create a dummy test Recipe
        ArrayList<String> helperIngredient1 = new ArrayList<>();
        helperIngredient1.add("no ingredient 1");
        helperIngredient1.add("no ingredient 2");

        ArrayList<String> helperSteps1 = new ArrayList<>();

        // Create dummy Recipe instances
        Recipe testRecipe1 = new Recipe("0","dummy1", "3", helperIngredient1,
                "2", "1000", helperSteps1, "0",
                "0", "0", "1");

        ArrayList<String> helperIngredient2 = new ArrayList<>();
        ArrayList<String> helperSteps2 = new ArrayList<>();
        helperSteps2.add("no step 1");
        helperSteps2.add("no step 2");

        // Create a dummy test Recipe
        Recipe testRecipe2 = new Recipe("1", "dummy2" , "3", helperIngredient2,
                "2", "1000", helperSteps2, "0",
                "0", "0", "1");

        // Testing addToBookMark() method ----------------------------------------------------------
        List<Recipe> listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe1);
        assertTrue( 1 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));

        listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe2);
        assertTrue( 2 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));
        assertTrue(listOfBookmarkedRecipes.get(1).getName().equals("dummy2"));

        //------------------------------------------------------------------------------------------

        // Test delRecipeFromBookMark() and getBookMarkList() methods ------------------------------
        bookmark.delRecipeFromBookMark(testRecipe2);

        listOfBookmarkedRecipes = bookmark.getBookMarkList();
        assertNotNull(listOfBookmarkedRecipes);

        assertTrue( 1 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));

        bookmark.delRecipeFromBookMark(testRecipe1);
        assertNotNull(listOfBookmarkedRecipes);

        listOfBookmarkedRecipes = bookmark.getBookMarkList();
        assertTrue( 0 == listOfBookmarkedRecipes.size() );
        //------------------------------------------------------------------------------------------

        System.out.println("End of BookMark feature test.");
    }
}