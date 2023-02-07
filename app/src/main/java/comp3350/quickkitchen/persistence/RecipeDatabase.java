package comp3350.quickkitchen.persistence;

import java.util.ArrayList;

import comp3350.quickkitchen.objects.Recipe;

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

        //third recipe
        ArrayList<String> helperIngredient3 = new ArrayList<>();
        helperIngredient3.add("ONION");
        helperIngredient3.add("Oil");
        ArrayList<String> helperSteps3 = new ArrayList<>();
        helperSteps3.add( "Wash onions.");
        helperSteps3.add( "Cut the onions into thick slices.");
        helperSteps3.add( "Fry.");
        Recipe third = new Recipe("Onion Ring", 3, helperIngredient3, 2, 500, helperSteps3, false, false, false, 1);
        listData.add(third);

        //fourth recipe
        ArrayList<String> helperIngredient4 = new ArrayList<>();
        helperIngredient4.add("flour");
        helperIngredient4.add("Cheese");
        helperIngredient4.add("tomato");
        helperIngredient4.add("onion");
        ArrayList<String> helperSteps4 = new ArrayList<>();
        helperSteps4.add( "test.");
        helperSteps4.add( "test.");
        helperSteps4.add( "test.");
        helperSteps4.add( "test.");
        helperSteps4.add( "test.");
        Recipe fourth = new Recipe("Pizza", 3, helperIngredient4, 2, 1000, helperSteps4, false, false, false, 1);
        listData.add(fourth);
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

