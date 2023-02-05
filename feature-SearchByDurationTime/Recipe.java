import java.util.ArrayList;
public class Recipe {
    private String name; //name of the recipe
    private int difficulty; //difficulty level of the recipe
    private ArrayList<String>ingredients; //elements for the recipe
    private int portion; //How many people
    private int calories; //Calories on the recipe
    private double duration; //time to prepare the meal in hours
    private ArrayList<String>steps; //Steps to prepare for the recipe;

    private boolean vegeterian;//if its veg
    private boolean gultenfree; //if its gultenfree or not
    private boolean dairyFree; //if it has egg/milk false otherwise true
 
    public Recipe(String rName, int rDifficulty, ArrayList<String>rIngredient, int rPortion, int rCalories, ArrayList<String>rSteps, boolean rVegeterian, boolean rGulenfree, boolean rdairyFree, double rDuration)
    {
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
    }

    public int getDifficulty(){
        return difficulty;
    }

    public ArrayList<String> getIngredients(){
        return  ingredients;
    }

    public int getPortion(){
        return portion;
    }

    public int getCalories(){
        return calories;
    }

    public ArrayList<String> getSteps(){
        return  steps;
    }

    public double getDuration(){
        return duration;
    }

    public boolean isVegeterian(){
        return vegeterian;
    }

    public boolean isGultenfree(){
        return gultenfree;
    }

    public boolean isDairyFree(){
        return dairyFree;
    }

}
