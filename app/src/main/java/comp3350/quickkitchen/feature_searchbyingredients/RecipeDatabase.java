package comp3350.quickkitchen.feature_searchbyingredients;

import java.util.ArrayList;

public class RecipeDatabase {

    private ArrayList<Recipe> listData;

    public RecipeDatabase() {
        initialize(true);
        //showDB();
    }

    public void initialize(boolean command) {
        listData = new ArrayList<Recipe>();

        //first recipe
        ArrayList<String> helperIngredient = new ArrayList<>();
        helperIngredient.add("Potato");
        helperIngredient.add("Oil");
        ArrayList<String> helperSteps = new ArrayList<>();
        helperSteps.add( "Wash potates.");
        helperSteps.add( "Cut the potatoes into thin slices.");
        helperSteps.add( "Fry.");
        Recipe one = new Recipe("French fries", 2, helperIngredient, 1, 300, helperSteps, true, false, true, 0.5);
        listData.add(one);

        //second recipe
        ArrayList<String> helperIngredient2 = new ArrayList<>();
        helperIngredient2.add("Potato");
        helperIngredient2.add("Cheese");
        helperIngredient2.add("Oil");
        helperIngredient2.add("Gravy");
        ArrayList<String> helperSteps2 = new ArrayList<>();
        helperSteps2.add( "Wash potates.");
        helperSteps2.add( "Cut the potatoes into thin slices.");
        helperSteps2.add( "Fry.");
        helperSteps2.add( "Put gravy.");
        helperSteps2.add( "Put cheese.");
        Recipe two = new Recipe("Poutine", 3, helperIngredient2, 2, 900, helperSteps2, false, false, false, 1);
        listData.add(two);
    }

    public ArrayList<Recipe> getDatabase(){
        return listData;
    }


    public void showDB(){
        for(int i=0; i<listData.size(); i++){
            Recipe x = listData.get(i);
            System.out.println("Name:"+x.getName());
            System.out.println("Difficulty:"+x.getDifficulty());
            System.out.println("Ingredients:");
            for(int j=0; j<x.getIngredients().size(); j++){
                System.out.println(x.getIngredients().get(j));
            }
            System.out.println("Calories:"+x.getCalories());
        }
    }
}

