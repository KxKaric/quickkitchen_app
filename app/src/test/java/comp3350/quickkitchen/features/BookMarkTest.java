package comp3350.quickkitchen.features;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.FakePersistenceDB;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class BookMarkTest {

    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing
    private ArrayList<String> helperIngredient1 = new ArrayList<>();
    private ArrayList<String> helperSteps1 = new ArrayList<>();
    private ArrayList<String> helperIngredient2 = new ArrayList<>();
    private ArrayList<String> helperSteps2 = new ArrayList<>();
    private Recipe testRecipe1;
    private Recipe testRecipe2;

    @Before
    public void setUp(){
        this.mockPersistenceDB = mock(RecipePersistence.class);
        this.fakePersistenceDB = new FakePersistenceDB();

        // Create dummy Recipe instances
        this.helperIngredient1.add("no ingredient 1");
        this.helperIngredient1.add("no ingredient 2");

        this.helperSteps2.add("no step 1");
        this.helperSteps2.add("no step 2");

        this.testRecipe1 = new Recipe("0","dummy1", "3", helperIngredient1,
                "2", "1000", helperSteps1, "0",
                "0", "0", "1", "1");
        this.testRecipe2 = new Recipe("1", "dummy2" , "3", helperIngredient2,
                "2", "1000", helperSteps2, "0",
                "0", "0", "1", "2");
    }

    /**
     * Unit testing
     * Test with a Fake dependency (double test)
     */

    @Test
    public void testBookMark(){

        BookMark bookmark = new BookMark(fakePersistenceDB);

        assertNotNull(bookmark);

        // Testing addToBookMark() method ----------------------------------------------------------
        List<Recipe> listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe1,1);
        assertTrue( 1 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));

        listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe2, 2);
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

    /**
     * Integrated testing using mock dependency
     * We are not modifying the DB, so no need to make a copy of the real dependency
     */

    public void integratedTestingWithMock(){
        // Using mock DB

        ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList("flour", "Cheese", "tomato",
                "onion"));

        ArrayList<String> steps = new ArrayList<String>(Arrays.asList("step 1", "step2", "step3",
                "step4"));

        Recipe recipe1 = new Recipe("2","Recipe1", "3", ingredients,
                "2", "900", steps, "0",
                "0", "0", "1", "1");

        Recipe recipe2 = new Recipe("1","Recipe2", "2", ingredients,
                "1", "300", steps, "1",
                "0", "1", "0.5", "2");

        List<Recipe> result = new ArrayList<>(Arrays.asList(recipe1, recipe2));

        List<String> searchList = new ArrayList<String>();

        BookMark bookmark = new BookMark(mockPersistenceDB);

        assertNotNull(bookmark);


        // Making Mock to return the searchList if getRecipeByIngredient() is called
        when(mockPersistenceDB.getRecipeByIngredient(searchList)).thenReturn(result);

        // Testing addToBookMark() method ----------------------------------------------------------
        List<Recipe> listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe1,1);
        assertTrue( 1 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));

        listOfBookmarkedRecipes = bookmark.addToBookMark(testRecipe2, 2);
        assertTrue( 2 == listOfBookmarkedRecipes.size() );
        assertTrue(listOfBookmarkedRecipes.get(0).getName().equals("dummy1"));
        assertTrue(listOfBookmarkedRecipes.get(1).getName().equals("dummy2"));

        //-----------------------------------------------------------------------------------------

        verify(mockPersistenceDB).getRecipeByIngredient(searchList);

        System.out.println("End of Bookmark feature test.");
    }
}