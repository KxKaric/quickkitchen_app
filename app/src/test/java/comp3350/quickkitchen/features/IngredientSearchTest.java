package comp3350.quickkitchen.features;
import comp3350.quickkitchen.application.Services;
import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class IngredientSearchTest {

    // Test if search return correct list
    @Test
    public void testIngredientSearch1(){
        ArrayList searchList1 = new ArrayList<>();
        ArrayList searchList2 = new ArrayList<>();
        ArrayList searchList3 = new ArrayList<>();
        ArrayList searchList4 = new ArrayList<>();
        ArrayList searchList5 = new ArrayList<>();

        // Should return both Poutine and French Fries ---------
        searchList1.add("Potato");
        IngredientSearch ingriSearch1 = new IngredientSearch();
        assertNotNull(ingriSearch1);

        List <Recipe> searchResult1 = ingriSearch1.searchRecipeByIngredient(searchList1);
        assertNotNull(searchResult1);

        assertTrue( 2 == searchResult1.size() );
        assertTrue( "French fries".equals(searchResult1.get(0).getName()) );
        assertTrue( "Poutine".equals(searchResult1.get(1).getName()) );

        // Should only return poutine ---------
        searchList2.add("Cheese");
        searchList2.add("Potato");

        IngredientSearch ingriSearch2 = new IngredientSearch();
        assertNotNull(ingriSearch2);

        List <Recipe> searchResult2 = ingriSearch2.searchRecipeByIngredient(searchList2);
        assertNotNull(searchResult2);

        assertTrue( 1 == searchResult2.size() );
        assertTrue( "Poutine".equals(searchResult2.get(0).getName()) );


        // Multiple ingredient search. Should return both Poutine and French Fries ---------
        searchList3.add("Potato");
        searchList3.add("Oil");
        IngredientSearch ingriSearch3 = new IngredientSearch();
        assertNotNull(ingriSearch3);

        List <Recipe> searchResult3 = ingriSearch3.searchRecipeByIngredient(searchList3);
        assertNotNull(searchResult3);

        assertTrue( 2 == searchResult3.size() );
        assertTrue( "French fries".equals(searchResult3.get(0).getName()) );
        assertTrue( "Poutine".equals(searchResult3.get(1).getName()) );

        // Should return french fires, poutine and onion ring ---------
        searchList4.add("Oil");

        IngredientSearch ingriSearch4 = new IngredientSearch();
        assertNotNull(ingriSearch4);

        List <Recipe> searchResult4 = ingriSearch4.searchRecipeByIngredient(searchList4);
        assertNotNull(searchResult4);

        assertTrue( 3 == searchResult4.size() );
        assertTrue( "French fries".equals(searchResult4.get(0).getName()) );
        assertTrue( "Poutine".equals(searchResult4.get(1).getName()) );
        assertTrue( "Onion Ring".equals(searchResult4.get(2).getName()) );


        // Should return an empty list ---------
        searchList5.add("Oil");
        searchList5.add("flour");

        IngredientSearch ingriSearch5 = new IngredientSearch();
        assertNotNull(ingriSearch5);

        List <Recipe> searchResult5 = ingriSearch5.searchRecipeByIngredient(searchList5);

        assertNotNull(searchResult5);
        assertTrue( 0 == searchResult5.size() );

    }

    // Test if search return correct list, using ingredient that's not even in the db
    @Test
    public void testIngredientSearch2(){
        ArrayList<String> searchList1 = new ArrayList<>();
        ArrayList<String> searchList2 = new ArrayList<>();

        searchList1.add("Dragon Heart");
        searchList2.add("Truffle");

        // Should not return any result ---------
        IngredientSearch ingriSearch1 = new IngredientSearch();
        assertNotNull(ingriSearch1);

        List <Recipe> searchResult1 = ingriSearch1.searchRecipeByIngredient(searchList1);
        assertNotNull(searchResult1);
        assertTrue( 0 == searchResult1.size() );

        // Should not return any results ---------
        IngredientSearch ingriSearch2 = new IngredientSearch();
        assertNotNull(ingriSearch2);

        List <Recipe> searchResult2 = ingriSearch2.searchRecipeByIngredient(searchList2);

        assertNotNull(searchResult2);
        assertTrue( 0 == searchResult2.size() );


    }

}