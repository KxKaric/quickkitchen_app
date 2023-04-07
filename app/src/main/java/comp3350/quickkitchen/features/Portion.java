package comp3350.quickkitchen.features;

import java.util.List;

import comp3350.quickkitchen.application.Services;
import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class Portion {
    private List<String> ingredients;
    private Recipe recipe;
    private RecipePersistence recipePersistence;

    public Portion(){
        /**
         * Constructor that use Service to connect to db
         */
        this.recipePersistence = Services.getRecipePersistence();
    }

    public Portion(RecipePersistence recipePersistence){
        /**
         * Overload constructor that take a persistence db as  its parameter
         */
        this.recipePersistence = recipePersistence;
    }

    public List<String> ingredientsWithPortion(Recipe recipe, int portion){ // String recipeName instead of Recipe recipe
        /**
         * the portion for shop list and ingredient list.
         */
        //recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        ingredients = recipe.getIngredients();
        int tem = portion;

        for( int i=0; i< ingredients.size(); i++) {
            if (Character.isDigit(ingredients.get(i).charAt(0))) {
                System.out.println("In if 1, value is " +  Character.getNumericValue(ingredients.get(i).charAt(0)));
                int newPortion = Character.getNumericValue(ingredients.get(i).charAt(0)) * portion;
                System.out.println("In if 1, value after scale is " + newPortion);
                String originalString = ingredients.get(i);
                String numStr = Integer.toString(newPortion);
                String newString = numStr + originalString.substring(1);
                ingredients.set(i, newString);
            }
            else if(Character.isDigit(ingredients.get(i).charAt(1))){
                tem = Character.getNumericValue(ingredients.get(i).charAt(1)) *tem;
                String originalString = ingredients.get(i);
                String numStr = Integer.toString(tem);
                String newString = numStr + originalString.substring(2);
                ingredients.set(i, newString);
            }
        }
        return ingredients;
    }

    public List<String> ingredientsWithPortion(String recipeName, int portion){
        /**
         * the portion for shop list and ingredient list.
         */
        recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        return ingredientsWithPortion(recipe, portion);
        /*
        ingredients = recipe.getIngredients();
        int tem = portion;

        for( int i=0;i< ingredients.size();i++) {
            if (Character.isDigit(ingredients.get(i).charAt(0))) {
                portion = Character.getNumericValue(ingredients.get(i).charAt(0)) * portion;
                String originalString = ingredients.get(i);
                String numStr = Integer.toString(portion);
                String newString = numStr + originalString.substring(1);
                ingredients.set(i, newString);
            }
            else if(Character.isDigit(ingredients.get(i).charAt(1))){
                tem = Character.getNumericValue(ingredients.get(i).charAt(1)) *tem;
                String originalString = ingredients.get(i);
                String numStr = Integer.toString(tem);
                String newString = numStr + originalString.substring(2);
                ingredients.set(i, newString);
            }
        }
        return ingredients;*/
    }

}
