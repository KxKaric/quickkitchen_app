package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class FilterTest {

    /**
     * Unit testing
     * No need for dependency (this feature never interact with dependency). Just the unit testing itself
     */
    @Test
    public void filterTesting(){


        ArrayList<String> helperIngredient1 = new ArrayList<>();
        ArrayList<String> helperSteps1 = new ArrayList<>();
        ArrayList<String> helperIngredient2 = new ArrayList<>();
        ArrayList<String> helperSteps2 = new ArrayList<>();
        Recipe testRecipe1;
        Recipe testRecipe2;
        Recipe testRecipe3;

        // Create dummy Recipe instances
        helperIngredient1.add("no ingredient 1");
        helperIngredient1.add("no ingredient 2");

        helperSteps2.add("no step 1");
        helperSteps2.add("no step 2");

        testRecipe1 = new Recipe("0","FindThisName", "3", helperIngredient1,
                "2", "1000", helperSteps1, "0",
                "0", "0", "1", "1");
        testRecipe2 = new Recipe("1", "NotIsHere" , "3", helperIngredient2,
                "2", "300", helperSteps2, "0",
                "0", "0", "1", "2");
        testRecipe3 = new Recipe("1", "wEird" , "3", helperIngredient2,
                "2", "5000", helperSteps2, "0",
                "0", "0", "1", "2");

        List<Recipe> recipeList = new ArrayList<>(Arrays.asList(testRecipe1, testRecipe2, testRecipe3));

        Filter filter = new Filter(recipeList);
        assertNotNull(filter);

        List<Recipe> result1 = new Filter(recipeList).filterName("This");
        assertTrue(1 == result1.size());
        assertTrue( "FindThisName".equals(result1.get(0).getName()) );

        List<Recipe> result2 = new Filter(recipeList).filterName("is");

        assertTrue(2 == result2.size());

        assertTrue( "FindThisName".equals(result2.get(0).getName()) );
        assertTrue( "NotIsHere".equals(result2.get(1).getName()) );

        List<Recipe> result3 = new Filter(recipeList).filterName("lolo");
        assertTrue(0 == result3.size());

        List<Recipe> result4 = new Filter(recipeList).filterCalories("200");
        assertTrue(0 == result4.size());

        List<Recipe> result5 = new Filter(recipeList).filterCalories("300");
        assertTrue(1 == result5.size());
        assertTrue( "NotIsHere".equals(result5.get(0).getName()) );

        List<Recipe> result6 = new Filter(recipeList).filterCalories("1000");
        assertTrue(2 == result6.size());
        assertTrue( "FindThisName".equals(result6.get(0).getName()) );
        assertTrue( "NotIsHere".equals(result6.get(1).getName()) );
    }
}