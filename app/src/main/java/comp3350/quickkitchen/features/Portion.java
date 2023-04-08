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
         *  Overload constructor that inject the dependency
         */
        this.recipePersistence = recipePersistence;
    }

    public List<String> ingredientsWithPortion(Recipe recipe, int portion){
        /**
         * The portion for shop list and ingredient list.
         * Given recipeName and portion as parameter,
         * scale the portion of ingredients to the equivalence
         */
        ingredients = recipe.getIngredients();
        int tem = portion;

        for( int i=0; i< ingredients.size(); i++) {
            if (Character.isDigit(ingredients.get(i).charAt(0))) {
                int newPortion = Character.getNumericValue(ingredients.get(i).charAt(0)) * portion;
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
         * The portion for shop list and ingredient list.
         * Given recipeName and portion as parameter,
         * scale the portion of ingredients to the equivalence
         */
        recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        return ingredientsWithPortion(recipe, portion);
    }

}
