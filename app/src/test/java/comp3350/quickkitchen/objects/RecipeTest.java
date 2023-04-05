package comp3350.quickkitchen.objects;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class RecipeTest {

    /**
     * Unit testing for Recipe class.
     */
    @Test
    public void testRecipe(){

        // Create and test the first recipe

        // Array list of steps
        ArrayList<String> ingredient1 = new ArrayList<>();
        ingredient1.add("Potato");
        ingredient1.add("Cheese");
        ingredient1.add("Oil");
        ingredient1.add("Gravy");

        // Array list of ingredients
        ArrayList<String> stepList1 = new ArrayList<>();
        stepList1.add( "Wash potates.");
        stepList1.add( "Cut the potatoes into thin slices.");
        stepList1.add( "Fry.");
        stepList1.add( "Put gravy.");
        stepList1.add( "Put cheese.");

        Recipe recipe1 = new Recipe("2","Poutine", "3", ingredient1,
                                    "2", "900", stepList1, "0",
                                    "0", "0", "1");

        // Asserting the basic fields
        assertNotNull(recipe1);
        assertTrue( "Poutine".equals(recipe1.getName()) );
        assertTrue( "3".equals(recipe1.getDifficulty()) );
        assertTrue( "2".equals(recipe1.getPortion()) );
        assertTrue( "900".equalsIgnoreCase(recipe1.getCalories()) );
        assertTrue( !recipe1.isVegeterian() );
        assertTrue( !recipe1.isGultenfree() );
        assertTrue( !recipe1.isDairyFree() );

        // Asserting if the ingredients are correctly passed into the class
        // Also a test for getIngredients() method
        assertTrue( recipe1.getIngredients().equals(ingredient1) );

        // Asserting if the steps are correctly passed into the class
        // Also a test for getSteps() method
        assertTrue( recipe1.getSteps().equals(stepList1) );


        // Create and test the second recipe

        // Array list of ingredients
        ArrayList<String> ingredient2 = new ArrayList<>();
        ingredient2.add("Potato");
        ingredient2.add("Oil");

        // Array list of steps
        ArrayList<String> stepList2 = new ArrayList<>();
        stepList2.add( "Wash potates.");
        stepList2.add( "Cut the potatoes into thin slices.");
        stepList2.add( "Fry.");

        Recipe recipe2 = new Recipe("1","French fries", "2", ingredient2,
                                    "1", "300", stepList2, "1",
                                    "0", "1", "0.5");

        // Asserting the basic fields
        assertNotNull(recipe2);
        assertTrue( "French fries".equals(recipe2.getName()) );
        assertTrue( "2".equals(recipe2.getDifficulty()) );
        assertTrue( "1".equals(recipe2.getPortion()) );
        assertTrue( "300".equals(recipe2.getCalories()) );
        assertTrue( recipe2.isVegeterian() );
        assertTrue( !recipe2.isGultenfree() );
        assertTrue( recipe2.isDairyFree() );

        // Asserting if the ingredients are correctly passed into the class
        // Also a test for getIngredients() method
        assertTrue( recipe2.getIngredients().equals(ingredient2) );

        // Asserting if the steps are correctly passed into the class
        // Also a test for getSteps() method
        assertTrue( recipe2.getSteps().equals(stepList2) );


        // Create and test the third recipe

        // Array list of ingredients
        ArrayList<String> ingredient3 = new ArrayList<>();
        ingredient3.add("ONION");
        ingredient3.add("Oil");

        // Array list of steps
        ArrayList<String> stepList3 = new ArrayList<>();
        stepList3.add( "Wash onions." );
        stepList3.add( "Cut the onions into thick slices." );
        stepList3.add( "Fry." );

        Recipe recipe3 = new Recipe("3","Onion Ring", "3", ingredient3,
                                    "2", "500", stepList3, "0",
                                    "0", "0", "1");

        // Asserting the basic fields
        assertNotNull(recipe3);
        assertTrue( "Onion Ring".equals(recipe3.getName()) );
        assertTrue( "3".equals(recipe3.getDifficulty()) );
        assertTrue( "2".equals(recipe3.getPortion()) );
        assertTrue( "500".equals(recipe3.getCalories()) );
        assertTrue( !recipe3.isVegeterian() );
        assertTrue( !recipe3.isGultenfree() );
        assertTrue( !recipe3.isDairyFree() );

        // Asserting if the ingredients are correctly passed into the class
        // Also a test for getIngredients() method
        assertTrue( recipe3.getIngredients().equals(ingredient3) );

        // Asserting if the steps are correctly passed into the class
        // Also a test for getSteps() method
        assertTrue( recipe3.getSteps().equals(stepList3) );


        // Create and test the fourth recipe

        // Array list of ingredients
        ArrayList<String> ingredient4 = new ArrayList<>();
        ingredient4.add("flour");
        ingredient4.add("Cheese");
        ingredient4.add("tomato");
        ingredient4.add("onion");

        // Array list of steps
        ArrayList<String> stepList4 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            stepList4.add( "test." );
        }

        Recipe recipe4 = new Recipe("4","Pizza", "3", ingredient4, "2",
                                    "1000", stepList4, "0", "0",
                                    "0", "1");

        // Asserting the basic fields
        assertNotNull(recipe4);
        assertTrue( "Pizza".equals(recipe4.getName()) );
        assertTrue( "3" ==  recipe4.getDifficulty() );
        assertTrue( "2".equals(recipe4.getPortion()) );
        assertTrue( "1000".equals(recipe4.getCalories()) );
        assertTrue( !recipe4.isVegeterian() );
        assertTrue( !recipe4.isGultenfree() );
        assertTrue( !recipe4.isDairyFree() );

        // Asserting if the ingredients are correctly passed into the class
        // Also a test for getIngredients() method
        assertTrue( recipe4.getIngredients().equals(ingredient4) );

        // Asserting if the steps are correctly passed into the class
        // Also a test for getSteps() method
        assertTrue( recipe4.getSteps().equals(stepList4) );

        System.out.println("End of Recipe object test.");
    }
}