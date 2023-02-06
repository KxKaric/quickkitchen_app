package comp3350.quickkitchen.feature_searchbyingredients;

import java.util.ArrayList;

public class IngredientSearch {
    private ArrayList<Recipe>filteredList;
    private RecipeDatabase dataBase;
    private ArrayList <Recipe> rawList;


    public IngredientSearch(ArrayList<String> ingredient){
        dataBase = new RecipeDatabase();
        rawList = new ArrayList<Recipe>();
        filteredList = new ArrayList<Recipe>();




        for(int i=0; i<dataBase.getDatabase().size(); i++){
            rawList.add(dataBase.getDatabase().get(i));
        }
        filterMySearch(ingredient);
    }

    public void filterMySearch(ArrayList<String> ingredient){

        for (int i=0; i<rawList.size(); i++){//for all database
            for (int j=0; j<rawList.get(i).getIngredients().size(); j++){//for a single recipe
                if(hasAll(ingredient,rawList.get(i).getIngredients())) {
                    if(notAddedPreviously(rawList.get(i).getName()))
                        filteredList.add(rawList.get(i));
                }
            }
        }
    }

    //helper method to see if the recipe already added on filtered list
    private boolean hasAll(ArrayList<String> ingredient, ArrayList<String>ingredients){
        boolean decision = true;
        Boolean[] cond = new Boolean[ingredient.size()];
        for(int i=0; i<cond.length; i++){
            cond[i]=false;
        }

        for (int i=0; i<ingredient.size(); i++){
            for(int j=0; j<ingredients.size(); j++){
                if(ingredients.get(j).equalsIgnoreCase(ingredient.get(i)))
                    cond[i]=true;
            }
        }

        for(int i=0; i<cond.length; i++){
            if(cond[i]==false)
                decision=false;
        }

        return decision;
    }

    private boolean notAddedPreviously(String name){
        boolean decision = true;
        for(int i=0; i<filteredList.size(); i++){
            if(filteredList.get(i).getName().equals(name))
                decision=false;
        }
        return decision;
    }

    public ArrayList<Recipe> getIngredientSearchResult(){
        return filteredList;
    }

    public ArrayList<String> getStringList(){
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<filteredList.size();i++)
            if(filteredList.get(i)!=null)
                result.add(filteredList.get(i).getName());
        return result;
    }
    public void showList(){
        System.out.println("The name of the recipes:");

        for (int i=0; i<filteredList.size(); i++){
            System.out.println(i+1+" "+filteredList.get(i).getName());
        }
    }


}
