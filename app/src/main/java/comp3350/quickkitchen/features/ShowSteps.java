package comp3350.quickkitchen.features;

import java.util.ArrayList;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipeDatabase;

public class ShowSteps {

    ArrayList<Recipe> rawDatabase;
    public ShowSteps(){
        RecipeDatabase db = new RecipeDatabase();
        rawDatabase = new ArrayList<Recipe>();
        rawDatabase=db.getDatabase();
    }

    public ArrayList<String> showSteps(String name){
        ArrayList<String>emptyList = new ArrayList<>();
        for(int i=0; i<rawDatabase.size(); i++){
            if(rawDatabase.get(i).getName().equalsIgnoreCase(name))
                return rawDatabase.get(i).getSteps();
        }
        return emptyList;
    }
}
