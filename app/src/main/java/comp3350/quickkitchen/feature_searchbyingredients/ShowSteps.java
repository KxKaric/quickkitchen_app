package comp3350.quickkitchen.feature_searchbyingredients;

import java.util.ArrayList;

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
