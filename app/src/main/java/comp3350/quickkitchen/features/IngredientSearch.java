package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.application.Services;
import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class IngredientSearch {
    // search the recipe by ingredient
    private RecipePersistence recipePersistence;
    private List<Recipe> recipeList;
    private List<String> recipeNameList ;
    private Rank rankObj;

    public IngredientSearch(){

        /**
         * Constructor that use Service to connect to db
         */

        /*
         * the constructor of ingredient search
         * */
        recipeList = new ArrayList<>();
        recipeNameList = new ArrayList<>();
        rankObj=new Rank();

        recipePersistence = Services.getRecipePersistence();
    }

    public IngredientSearch(RecipePersistence recipePersistence){
        /**
         * Overload constructor that inject the dependency
         */

        recipeList = new ArrayList<>();
        recipeNameList = new ArrayList<>();
        rankObj=new Rank();

        this.recipePersistence = recipePersistence;
    }

    public List<Recipe> searchRecipeByIngredient(List<String> ingredients){

        /**
         * Search db and return a list of Recipe that use the given ingredients
         */
        //sorted list based on rank
        this.recipeList = rankObj.sortByRank(recipePersistence.getRecipeByIngredient(ingredients));
        // rank method here
        return this.recipeList;
    }

}
