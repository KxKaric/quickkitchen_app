package comp3350.quickkitchen.features;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class RankTest {

    /**
     * Unit testing
     * No need for dependency. Just the unit testing itself
     */
    @Test
    public void testRank(){

        // Create a rank instance
        Rank rank = new Rank();

        // Create test data
        ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList("flour", "Cheese", "tomato",
                "onion"));

        ArrayList<String> steps = new ArrayList<String>(Arrays.asList("step 1", "step2", "step3",
                "step4"));

        Recipe recipe1 = new Recipe("2","Recipe1", "3", ingredients,
                "2", "900", steps, "0",
                "0", "0", "1", "0");

        Recipe recipe2 = new Recipe("1","Recipe2", "2", ingredients,
                "1", "300", steps, "1",
                "0", "1", "0.5", "1");

        Recipe recipe3 = new Recipe("1","Recipe2", "2", ingredients,
                "1", "300", steps, "1",
                "0", "1", "0.5", "2");

        List<Recipe> listOf1Recipe = new ArrayList<Recipe>(Arrays.asList(recipe1));
        List<Recipe> listOf2Recipe = new ArrayList<Recipe>(Arrays.asList(recipe3, recipe2));
        List<Recipe> listOf3Recipe = new ArrayList<Recipe>(Arrays.asList(recipe2, recipe3, recipe1));

        // Testing
        List<Recipe> result1 = rank.sortByRank(listOf1Recipe);
        assertNotNull(result1);
        assertTrue(1 == result1.size());
        assertTrue(0 == result1.get(0).getRanking() );

        List<Recipe> result2 = rank.sortByRank(listOf2Recipe);
        assertNotNull(result2);
        assertTrue(2 == result2.size());
        assertTrue(1 == result2.get(0).getRanking() );
        assertTrue(2 == result2.get(1).getRanking() );

        List<Recipe> result3 = rank.sortByRank(listOf3Recipe);
        assertNotNull(result3);
        assertTrue(3 == result3.size());
        assertTrue(0 == result3.get(0).getRanking() );
        assertTrue(1 == result3.get(1).getRanking() );
        assertTrue(2 == result3.get(2).getRanking() );

        System.out.println("End of Rank feature test.");

    }
}