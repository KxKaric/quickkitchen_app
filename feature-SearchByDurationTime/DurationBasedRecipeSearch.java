import java.util.ArrayList;

/*
    + Search recipes that has duration time less than the specified time
    + Search recipes that has duration time that fits in a range of specified time
 */
public class DurationBasedRecipeSearch {

    private RecipeDatabase dataBase;
    private ArrayList<Recipe> filteredList;

    private double duration;
    private double upperBound;
    private double lowerBound;

    Boolean singleTime = false; // Flag to check which constructor is called

/*
    + This constructor search a recipe that has duration time less that the specified time
 */
    public DurationBasedRecipeSearch(double duration){

        this.duration = duration;
        dataBase = new RecipeDatabase();
        filteredList = new ArrayList<Recipe>();
        singleTime = true;

        // Iterate the database
        for(int i = 0; i < dataBase.getDatabase().size(); i++){

            Recipe currentRecipe = dataBase.getDatabase().get(i);   // Get the recipe
            double recipeDuration = currentRecipe.getDuration();    // Get the duration time of that recipe

            if( recipeDuration <= duration )
                filteredList.add( currentRecipe );
        }
    }

/*
    + This constructor search a recipe that has duration time in a specified range
 */
    public DurationBasedRecipeSearch(double lowerBound, double upperBound){

        // Need a user input check inthe future (are they putting in numbers?)

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        dataBase = new RecipeDatabase();
        filteredList = new ArrayList<Recipe>();

        // Simple check for valid under and upper bound
        if(lowerBound > upperBound){
            System.out.println("Invalid duration range");
            return;
        }

        // Start searching with valid bounds
        else{

            for(int i = 0; i < dataBase.getDatabase().size(); i++){     // Iterate the database

                Recipe currentRecipe = dataBase.getDatabase().get(i);   // Get the recipe
                double recipeDuration = currentRecipe.getDuration();    // Get the duration time of that recipe

                if( recipeDuration <= upperBound && recipeDuration >= lowerBound )
                    filteredList.add( currentRecipe );
            }
        }
    }

/*
    + A public method that show the search result
 */

    public void showList(){

        if(filteredList.size() > 0){

            for (int i = 0; i < filteredList.size(); i++)
                System.out.println(i+1 + ": " + filteredList.get(i).getName());
        }
        else{   // Found nothing that satisfy the query
            if(singleTime)
                System.out.println("No recipe has preparing time less than " + duration + " minutes");
            else
                System.out.println("No recipe has preparing time more than " + lowerBound + " minutes"
                                    + " and less than " + upperBound + " minutes");
        }

    }
}
