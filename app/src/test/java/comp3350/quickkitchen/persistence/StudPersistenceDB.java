package comp3350.quickkitchen.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class StudPersistenceDB implements RecipePersistence {

    private ArrayList<Recipe> listData;

    public StudPersistenceDB() {
        initialize(true);
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
        Recipe one = new Recipe("1","French fries", "2", helperIngredient, "1", "300", helperSteps, "1", "0", "1", "0.5");
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
        Recipe two = new Recipe("2","Poutine", "3", helperIngredient2, "2", "900", helperSteps2, "0", "0", "0", "1");
        listData.add(two);

        //third recipe
        ArrayList<String> helperIngredient3 = new ArrayList<>();
        helperIngredient3.add("ONION");
        helperIngredient3.add("Oil");
        ArrayList<String> helperSteps3 = new ArrayList<>();
        helperSteps3.add( "Wash onions.");
        helperSteps3.add( "Cut the onions into thick slices.");
        helperSteps3.add( "Fry.");
        Recipe third = new Recipe("3","Onion Ring", "3", helperIngredient3, "2", "500", helperSteps3, "0", "0", "0", "1");
        listData.add(third);

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
        Recipe fourth = new Recipe("4","Pizza", "3", helperIngredient4, "2", "1000", helperSteps4, "0", "0", "0", "1");
        listData.add(fourth);
    }

    public ArrayList<Recipe> getDatabase(){
        return listData;
    }

    public Recipe getRecipeByName(String name){
        Recipe result = null;
        Boolean found = false;

        for(int i = 0; i < listData.size() && !found; i++){
            if( listData.get(i).getName().equalsIgnoreCase(name) ){
                result = listData.get(i);
                found = true;
            }
        }

        return result;
    }

    public List<Recipe> getRecipeByIngredient(List<String> ingredients){
        List<Recipe> result = new ArrayList<Recipe>();
        Boolean found = false;

        for(int i = 0; i < listData.size(); i++){

            List<String> ingreOfThisRecipe = listData.get(i).getIngredients();
            found = ingreOfThisRecipe.containsAll(ingredients);

            if(found){
                result.add(listData.get(i));
                found = false;
            }
        }

        return result;
    }
}

