package comp3350.quickkitchen.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;



public class FakePersistenceDB implements RecipePersistence {

    private ArrayList<Recipe> listOfRecipe;

    public FakePersistenceDB() {
        initialize(true);
    }

    public void initialize(boolean command) {
        listOfRecipe = new ArrayList<Recipe>();

        //first recipe
        ArrayList<String> helperIngredient = new ArrayList<>();
        helperIngredient.add("Potato");
        helperIngredient.add("Oil");
        ArrayList<String> helperSteps = new ArrayList<>();
        helperSteps.add( "Wash potates.");
        helperSteps.add( "Cut the potatoes into thin slices.");
        helperSteps.add( "Fry.");
        Recipe one = new Recipe("1","French fries", "2", helperIngredient, "1", "300", helperSteps, "1", "0", "1", "0.5", "1");
        listOfRecipe.add(one);

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
        Recipe two = new Recipe("2","Poutine", "3", helperIngredient2, "2", "900", helperSteps2, "0", "0", "0", "1", "2");
        listOfRecipe.add(two);

        //third recipe
        ArrayList<String> helperIngredient3 = new ArrayList<>();
        helperIngredient3.add("Onion");
        helperIngredient3.add("Oil");
        ArrayList<String> helperSteps3 = new ArrayList<>();
        helperSteps3.add( "Wash onions.");
        helperSteps3.add( "Cut the onions into thick slices.");
        helperSteps3.add( "Fry.");
        Recipe third = new Recipe("3","Onion Ring", "3", helperIngredient3, "2", "500", helperSteps3, "0", "0", "0", "1", "3");
        listOfRecipe.add(third);

        //fourth recipe
        ArrayList<String> helperIngredient4 = new ArrayList<>();
        helperIngredient4.add("flour");
        helperIngredient4.add("Cheese");
        helperIngredient4.add("tomato");
        helperIngredient4.add("onion");
        ArrayList<String> helperSteps4 = new ArrayList<>();
        helperSteps4.add( "Mixing flour.");
        helperSteps4.add( "Add tomato and onion.");
        helperSteps4.add( "Bake for 5 minutes.");
        helperSteps4.add( "Sprinkle cheese on top");
        helperSteps4.add( "Add sauce.");
        Recipe fourth = new Recipe("4","Pizza", "3", helperIngredient4, "2", "1000", helperSteps4, "0", "0", "0", "1", "4");
        listOfRecipe.add(fourth);
    }

    public ArrayList<Recipe> getDatabase(){
        return listOfRecipe;
    }

    public Recipe getRecipeByName(String name){
        Recipe result = null;
        Boolean found = false;

        for(int i = 0; i < listOfRecipe.size() && !found; i++){
            if( listOfRecipe.get(i).getName().equalsIgnoreCase(name) ){
                result = listOfRecipe.get(i);
                found = true;
            }
        }

        return result;
    }

    public List<Recipe> getRecipeByIngredient(List<String> ingredients){
        List<Recipe> result = new ArrayList<Recipe>();
        Boolean found = false;

        // For each recipe in our db
        for(int i = 0; i < listOfRecipe.size(); i++){

            List<String> ingreOfThisRecipe = listOfRecipe.get(i).getIngredients();

            // For each ingredient given in the parameter
            for(int j = 0; j < ingredients.size() && !found; j++){
                String ingredient = ingredients.get(j);
                found = ingreOfThisRecipe.contains(ingredient);
            }


            if(found){
                result.add(listOfRecipe.get(i));
                found = false;
            }
        }

        return result;
    }
}

