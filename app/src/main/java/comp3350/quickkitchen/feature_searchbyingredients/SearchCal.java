package comp3350.quickkitchen.feature_searchbyingredients;

import java.util.ArrayList;
public class SearchCal {
    private ArrayList<Recipe> result;

    private ArrayList<String> result1;
    private RecipeDatabase database;
    public SearchCal(){
        database= new RecipeDatabase();
        result = new ArrayList<Recipe>();
        //search(calory);
       // for(int i=0; i<result.size(); i++){
       //     System.out.println("name: "+result.get(i).getName());
       // }



    }
    public ArrayList<Recipe> search(int calory){
        for(int i=0; i<database.getDatabase().size();i++){
            if (database.getDatabase().get(i).getCalories() <= calory)
                  result.add(database.getDatabase().get(i));
        }
        return result;
    }
    public ArrayList<Recipe> getResult(){return result;}
    public ArrayList<String> searchString(int calory){
        for(int i=0; i<database.getDatabase().size();i++){
            if (database.getDatabase().get(i).getCalories() <= calory)
                result1.add(database.getDatabase().get(i).toString());
        }
        return result1;
    }

}
