package comp3350.quickkitchen.features;
import comp3350.quickkitchen.objects.Recipe;

import static org.junit.Assert.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class SearchCalTest {

    @Test
    public void testSearchCal(){

        // Create an instance of the SearchCal class
        SearchCal searchCal = new SearchCal();
        assertNotNull(searchCal);

        // Test search() method of SearchCal
        // search() accept an integer (the amount of calories) and return a list of recipe
        // that has calories index <= the parameter

        // Expect nothing, as no recipe in the db has calories <= 5
        ArrayList<Recipe> listOfRecipe1 = searchCal.search(5);
        assertNotNull(listOfRecipe1);
        assertTrue( 0 == listOfRecipe1.size() );

        // Expect French fries recipe
        ArrayList<Recipe> listOfRecipe2 = searchCal.search(350);
        assertNotNull(listOfRecipe2);
        assertTrue( 1 == listOfRecipe2.size() );
        assertTrue( "French fries".equals( listOfRecipe2.get(0).getName() ) );

        // Expect French fries and poutine recipe
        ArrayList<Recipe> listOfRecipe3 = searchCal.search(1000);
        assertNotNull(listOfRecipe3);

        // There are actually 3 recipes in the return result instead of 3
        System.out.println(listOfRecipe3.size());
        System.out.println(listOfRecipe3.get(0).getName());
        System.out.println(listOfRecipe3.get(1).getName());
        System.out.println(listOfRecipe3.get(2).getName());

        assertTrue( 2 == listOfRecipe3.size() );
        assertTrue( "French fries".equals( listOfRecipe3.get(0).getName() ) );
        assertTrue( "Poutine".equals( listOfRecipe3.get(1).getName() ) );
    }
}