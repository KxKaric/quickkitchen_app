package comp3350.quickkitchen.persistence;

import comp3350.quickkitchen.objects.Recipe;
import java.util.ArrayList;

public interface RecipePersistence {
    void initialize(boolean command);
    ArrayList<Recipe> getDatabase();
    void showDB();

}
