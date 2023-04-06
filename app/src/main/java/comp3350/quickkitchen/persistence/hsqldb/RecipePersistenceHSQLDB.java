package comp3350.quickkitchen.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.objects.Recipe;

public class RecipePersistenceHSQLDB implements RecipePersistence {
    private final String dbPath;

    public RecipePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Recipe fromResultSetMakeRecipe(final ResultSet rs) throws SQLException {
        /**
         * From a ResultSet pointer, create and return a Recipe instance
         */

        final String recipeID = rs.getString("recipeID");
        final String recipeName = rs.getString("recipeName");
        final String difficulty = rs.getString("difficulty");

        String[] ingredients = rs.getString("ingredient").split(",");
        final ArrayList<String> ingredientList = new ArrayList<String>();
        ingredientList.addAll(Arrays.asList(ingredients));

        final String portion = rs.getString("portion");
        final String calories = rs.getString("calories");

        String[] steps = rs.getString("steps").split(",");
        ArrayList<String> stepList = new ArrayList<String>();
        stepList.addAll(Arrays.asList(steps));

        String vegetarian = rs.getString("vegetarian");
        String glutenFree = rs.getString("glutenFree");
        String diaryFree = rs.getString("diaryFree");
        String duration = rs.getString("duration");
        String rank = rs.getString("rank");
        return new Recipe(recipeID, recipeName, difficulty, ingredientList, portion, calories, stepList,vegetarian, glutenFree, diaryFree, duration, rank);
    }

    @Override
    public Recipe getRecipeByName(String name){
        /**
         * Return the recipe that has matching name with the parameter
         */

        Recipe recipe;
        try (final Connection c = connection()){

            final PreparedStatement st = c.prepareStatement("SELECT * FROM recipes WHERE recipeName = ?");
            st.setString(1, name);

            final ResultSet rs = st.executeQuery();

            // Here we assume that there is exactly 1 recipe with given name (name is unique)
            // If there are more than 1, the method need to return a list of Recipe
            rs.next();
            recipe = fromResultSetMakeRecipe(rs);

            rs.close();
            st.close();

            return recipe;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public List<Recipe> getRecipeByIngredient(List<String> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        try (final Connection c = connection()) {
            String intersect = " UNION ";
            String str = "SELECT DISTINCT recipeName FROM recipes r JOIN recipeIngredient ri ON r.recipeId = ri.recipeId JOIN ingredients i ON i.ingreId = ri.ingreId WHERE i.ingreName = ?";

            // Only execute this for loop if we have more than 1 ingredient in the parameter
            for(int i = 1; i < ingredients.size(); i++){
                str += intersect + "SELECT DISTINCT recipeName FROM recipes r JOIN recipeIngredient ri ON r.recipeId = ri.recipeId JOIN ingredients i ON i.ingreId = ri.ingreId WHERE i.ingreName = ?";
            }

            final PreparedStatement st = c.prepareStatement(str);

            for(int i = 0; i < ingredients.size(); i++){
                st.setString(i+1, ingredients.get(i));
            }

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final Recipe recipe = getRecipeByName(rs.getString("recipeName"));
                recipes.add(recipe);
            }

            rs.close();
            st.close();

            return recipes;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

}
