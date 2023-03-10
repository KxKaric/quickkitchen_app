package comp3350.quickkitchen.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Recipe {
//    private int id; //id of the recipe

    private String id;//recipe id
    private String name; //name of the recipe
    private String difficulty; //difficulty level of the recipe
    private ArrayList<String>ingredients; //elements for the recipe
    private String portion; //How many people
    private String calories; //Calories on the recipe
    private ArrayList<String>steps; //Steps to prepare for the recipe;
    private String vegeterian;//if its veg
    private String gultenfree; //if its gultenfree or not
    private String dairyFree; //if it has egg/milk false otherwise true

    private String duration; //time to prepare the meal in hours
    public Recipe(String recipeId, String rName, String rDifficulty, ArrayList<String>rIngredient, String rPortion,
                  String rCalories, ArrayList<String>rSteps, String rVegeterian, String rGulenfree,
                  String rdairyFree, String rDuration)
    {
        id=recipeId;
        name=rName;
        difficulty=rDifficulty;
        //assigning value to ingredients
        ingredients=new ArrayList<String>();
        for(int i=0; i<rIngredient.size(); i++){
            ingredients.add(i,rIngredient.get(i));
        }

        portion=rPortion;
        calories=rCalories;
        steps=new ArrayList<String>();
        for(int i=0; i<rSteps.size(); i++){
            steps.add(i,rSteps.get(i));
        }

        vegeterian=rVegeterian;
        gultenfree=rGulenfree;
        dairyFree=rdairyFree;
        duration = rDuration;
    }
    public String getName(){
        return name;
    }//accessor name

    public String getDifficulty(){
        return difficulty;
    }// accessor difficulty

    public String getPortion(){
        return portion;
    }// accessor Portion

    public String getCalories(){
        return calories;
    }//accessor Calories

    public List<String> getIngredients(){
        List<String> result = this.ingredients;
        return  result;
    }// accessor Ingredients

    public List<String> getSteps(){
        List<String> result = this.steps;

        return result;
    }//accessor steps
    public boolean isVegeterian(){return vegeterian.equals("1");}// helper

    public boolean isGultenfree(){return gultenfree.equals("1");}

    public boolean isDairyFree(){return dairyFree.equals("1");}

}
