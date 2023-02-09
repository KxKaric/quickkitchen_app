package comp3350.quickkitchen.features;

import java.util.ArrayList;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipeDatabase;

public class SearchCal {

    private RecipeDatabase database;
    ArrayList<Recipe> searchResult = new ArrayList();

    public SearchCal(){
        database= new RecipeDatabase();
        searchResult = new ArrayList<Recipe>();

    }
    public ArrayList<Recipe> search(int calory){
        ArrayList<Recipe> result = new ArrayList();
        for(int i=0; i<database.getDatabase().size();i++){
            if (database.getDatabase().get(i).getCalories() <= calory)
                  result.add(database.getDatabase().get(i));
        }
        searchResult = result;
        return result;
    }
    public ArrayList<Recipe> getResult(){return searchResult;}

    public ArrayList<String> searchString(int calory){
        ArrayList<String> result1 = new ArrayList();
        for(int i=0; i<database.getDatabase().size();i++){
            if (database.getDatabase().get(i).getCalories() <= calory)
                result1.add(database.getDatabase().get(i).getName());
        }
        return result1;
    }

}
