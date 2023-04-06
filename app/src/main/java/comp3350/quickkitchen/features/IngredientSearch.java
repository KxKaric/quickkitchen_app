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
    private rank rankObj;


    public IngredientSearch(){

        /**
         * Constructor that use Service to connect to db
         */

        /*
         * the constructor of ingredient search
         * */
        recipeList = new ArrayList<>();
        recipeNameList = new ArrayList<>();
        rankObj=new rank();

        recipePersistence = Services.getRecipePersistence();
    }

    public IngredientSearch(RecipePersistence recipePersistence){
        /**
         * Overload constructor that inject the dependency
         */
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

//    public List<String> searchRecipeNameByIngredient(List<String> ingredients){
//
//        /**
//         * Search db and return a list of Recipe name that use the given ingredients
//         */
//
//        searchRecipeByIngredient(ingredients);
//
//        for(int i = 0; i < this.recipeList.size(); i ++){
//            Recipe temp = recipeList.get(i);
//            this.recipeNameList.add( temp.getName() );
//        }
//
//        return this.recipeNameList;
//    }

}
