package comp3350.quickkitchen.features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.FakePersistenceDB;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class ShowStepsTest {

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
    public void testShopSteps(){

        // Create an instance of the ShowSteps class
        ShowSteps stepList = new ShowSteps(fakePersistenceDB);
        assertNotNull(stepList);

        // Show the steps to make french fries
        List<String> listOfSteps = stepList.showSteps("French fries");
        assertNotNull(listOfSteps);

        // The standard step list that we use to check
        ArrayList<String> stepForFrenchFries = new ArrayList<>();
        stepForFrenchFries.add( "Wash potates.");
        stepForFrenchFries.add( "Cut the potatoes into thin slices.");
        stepForFrenchFries.add( "Fry.");

        assertTrue( listOfSteps.equals(stepForFrenchFries) );

        // Show the steps to make poutine
        listOfSteps = stepList.showSteps("Poutine");
        assertNotNull(listOfSteps);

        // The standard step list that we use to check
        ArrayList<String> stepForPoutine = new ArrayList<>();
        stepForPoutine.add( "Wash potates.");
        stepForPoutine.add( "Cut the potatoes into thin slices.");
        stepForPoutine.add( "Fry.");
        stepForPoutine.add( "Put gravy.");
        stepForPoutine.add( "Put cheese.");

        assertTrue( listOfSteps.equals(stepForPoutine) );

        // Show the steps to make onion ring
        listOfSteps = stepList.showSteps("Onion Ring");
        assertNotNull(listOfSteps);

        // The standard step list that we use to check
        ArrayList<String> stepForOnionRing = new ArrayList<>();
        stepForOnionRing.add( "Wash onions.");
        stepForOnionRing.add( "Cut the onions into thick slices.");
        stepForOnionRing.add( "Fry.");

        assertTrue( listOfSteps.equals(stepForOnionRing) );

        // Show the steps to make pizza
        listOfSteps = stepList.showSteps("Pizza");
        assertNotNull(listOfSteps);

        // The standard step list that we use to check
        ArrayList<String> stepForPizza = new ArrayList<>();
        stepForPizza.add("Mixing flour.");
        stepForPizza.add("Add tomato and onion.");
        stepForPizza.add("Bake for 5 minutes.");
        stepForPizza.add("Sprinkle cheese on top");
        stepForPizza.add("Add sauce.");

        assertTrue( listOfSteps.equals(stepForPizza) );
    }

    /**
     * Integrated testing using mock dependency
     * We are not modifying the DB, so no need to make a copy of the real dependency
     */
    @Test
    public void integratedTestingWithMock(){

        // Using mock DB
        ShowSteps showStep = new ShowSteps(mockPersistenceDB);
        assertNotNull(showStep);
        String name = "no name";

        // Create a dummy test Recipe
        ArrayList<String> helperIngredient = new ArrayList<>();
        ArrayList<String> helperSteps = new ArrayList<>();
        helperSteps.add("no step 1");
        helperSteps.add("no step 2");

        Recipe testRecipe = new Recipe("4", name, "3", helperIngredient,
                "2", "1000", helperSteps, "0",
                "0", "0", "1", "1");

        // Making Mock to return the test recipe if getRecipeByName() is called
        when(mockPersistenceDB.getRecipeByName(name)).thenReturn(testRecipe);

        List<String> stepToShow = showStep.showSteps(name);

        assertTrue(helperSteps.equals(stepToShow));

        verify(mockPersistenceDB).getRecipeByName(name);

        System.out.println("End of ShowSteps feature test.");
    }

}
