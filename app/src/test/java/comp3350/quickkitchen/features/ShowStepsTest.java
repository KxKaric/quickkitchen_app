package comp3350.quickkitchen.features;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class ShowStepsTest {

    @Test
    public void testShopSteps(){

        // Create an instance of the ShowSteps class
        ShowSteps stepList = new ShowSteps();
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

        // Let's check something that's not in the db
        // Expecting a list of size 0 (no step)
        listOfSteps = stepList.showSteps("Mosquito fries");
        assertNotNull(listOfSteps);
        assertTrue( 0 == listOfSteps.size() );
    }

}