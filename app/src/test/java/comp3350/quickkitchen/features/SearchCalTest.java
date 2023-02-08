package comp3350.quickkitchen.features;
import comp3350.quickkitchen.objects.Recipe;

import static org.junit.Assert.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class SearchCalTest {

    // Test search() method and searchString() method in SearchCal class
    // This test will only use 1 instance of SearchCal class for all search
    @Test
    public void testSearchCal(){

        // Create an instance of the SearchCal class
        SearchCal searchCal = new SearchCal();
        assertNotNull(searchCal);

        // Test search() method of SearchCal
        // search() accept an integer (the amount of calories) and return a list of recipe
        // that has calories index <= the parameter

        // Expect nothing, as no recipe in the db has calories <= 5 ---------
        ArrayList<Recipe> listOfRecipe1 = searchCal.search(5);
        ArrayList<String> listOfRecipeString1 = searchCal.searchString(5);
        assertNotNull(listOfRecipe1);
        assertNotNull(listOfRecipeString1);

        assertTrue( 0 == listOfRecipe1.size() );
        assertTrue( 0 == listOfRecipeString1.size() );

        // Expect French fries recipe ---------
        ArrayList<Recipe> listOfRecipe2 = searchCal.search(350);
        ArrayList<String> listOfRecipeString2 = searchCal.searchString(350);
        assertNotNull(listOfRecipe2);
        assertNotNull(listOfRecipeString2);

        assertTrue( 1 == listOfRecipe2.size() );
        assertTrue( "French fries".equals( listOfRecipe2.get(0).getName() ) );

        assertTrue( 1 == listOfRecipeString2.size() );
        assertTrue( "French fries".equals( listOfRecipeString2.get(0) ) );

        // Expect French fries and Onion Ring recipes ---------
        ArrayList<Recipe> listOfRecipe3 = searchCal.search(600);
        ArrayList<String> listOfRecipeString3 = searchCal.searchString(600);
        assertNotNull(listOfRecipe3);
        assertNotNull(listOfRecipeString3);

        assertTrue( 2 == listOfRecipe3.size() );
        assertTrue( "French fries".equals( listOfRecipe3.get(0).getName() ) );
        assertTrue( "Onion Ring".equals( listOfRecipe3.get(1).getName() ) );

        assertTrue( 2 == listOfRecipeString3.size() );
        assertTrue( "French fries".equals( listOfRecipe3.get(0) ) );
        assertTrue( "Onion Ring".equals( listOfRecipe3.get(1) ) );

        // Expect French fries, poutine and onion ring recipes ---------
        ArrayList<Recipe> listOfRecipe4 = searchCal.search(900);
        ArrayList<String> listOfRecipeString4 = searchCal.searchString(900);
        assertNotNull(listOfRecipe4);
        assertNotNull(listOfRecipeString4);

        assertTrue( 3 == listOfRecipe4.size() );
        assertTrue( "French fries".equals( listOfRecipe4.get(0).getName() ) );
        assertTrue( "Poutine".equals( listOfRecipe4.get(1).getName() ) );
        assertTrue( "Onion Ring".equals( listOfRecipe4.get(2).getName() ) );

        assertTrue( 3 == listOfRecipeString4.size() );
        assertTrue( "French fries".equals( listOfRecipeString4.get(0) ) );
        assertTrue( "Poutine".equals( listOfRecipeString4.get(1) ) );
        assertTrue( "Onion Ring".equals( listOfRecipeString4.get(2) ) );

        // Expect alll recipes in the db ---------
        ArrayList<Recipe> listOfRecipe5 = searchCal.search(1200);
        ArrayList<String> listOfRecipeString5 = searchCal.searchString(1200);
        assertNotNull(listOfRecipe5);
        assertNotNull(listOfRecipeString5);

        assertTrue( 4 == listOfRecipe5.size() );
        assertTrue( "French fries".equals( listOfRecipe5.get(0).getName() ) );
        assertTrue( "Poutine".equals( listOfRecipe5.get(1).getName() ) );
        assertTrue( "Onion Ring".equals( listOfRecipe5.get(2).getName() ) );
        assertTrue( "Pizza".equals( listOfRecipe5.get(3).getName() ) );

        assertTrue( 4 == listOfRecipeString5.size() );
        assertTrue( "French fries".equals( listOfRecipeString5.get(0) ) );
        assertTrue( "Poutine".equals( listOfRecipeString5.get(1) ) );
        assertTrue( "Onion Ring".equals( listOfRecipeString5.get(2) ) );
        assertTrue( "Pizza".equals( listOfRecipeString5.get(3) ) );
    }
}