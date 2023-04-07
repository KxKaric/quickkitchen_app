package comp3350.quickkitchen.features;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.FakePersistenceDB;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.hsqldb.RecipePersistenceHSQLDB;
import comp3350.quickkitchen.utils.TestUtils;

public class BookMarkTest {

    private RecipePersistence mockPersistenceDB;    // For integrated testing
    private RecipePersistence fakePersistenceDB;    // For unit testing
    private ArrayList<String> helperIngredient1 = new ArrayList<>();
    private ArrayList<String> helperSteps1 = new ArrayList<>();
    private ArrayList<String> helperIngredient2 = new ArrayList<>();
    private ArrayList<String> helperSteps2 = new ArrayList<>();
    private Recipe testRecipe1;
    private Recipe testRecipe2;
    private File tempDB;
    private BookMark officialBookMark;

    @Before
    public void setUp() throws IOException{
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

        this.tempDB = TestUtils.copyDB();
        final RecipePersistence realPersistanceDB = new RecipePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.officialBookMark = new BookMark(realPersistanceDB);
    }

    /**
     * Unit testing
     * No need for integrated testing (This feature never interact with dependency). This class never interact with the dependency
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

}