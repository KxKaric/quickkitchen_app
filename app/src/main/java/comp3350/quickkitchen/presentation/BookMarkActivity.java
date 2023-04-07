package comp3350.quickkitchen.presentation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

import comp3350.quickkitchen.R;

public class BookMarkActivity extends AppCompatActivity {
    private ArrayAdapter<Recipe> bMArrayAdapter;
    private List<Recipe> recipeList ;
    private boolean isClicked = false; //to check if user has chosen a recipe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);
        recipeList = RecipeListActivity.BM.getBookMarkList();
        try {
            // show book mark list
            bMArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1, recipeList){
                @SuppressLint("SetTextI18n")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(recipeList.get(position).getName());
                    text2.setText(recipeList.get(position).getCalories()+" calories\t\tvegetarian: "+
                            recipeList.get(position).isVegeterian()+"\t\tglutenFree: "+
                            recipeList.get(position).isGultenfree()+"\t\tdiaryFree: "+
                            recipeList.get(position).isDairyFree() +"\t\tdifficulty: "+
                            recipeList.get(position).getDifficulty());

                    return view;
                }
            };
            final ListView listView = (ListView)findViewById(R.id.bookMark_List);
            listView.setAdapter(bMArrayAdapter);


            Button removeBtn =(Button)findViewById(R.id.removeFromBM);
            removeBtn.setEnabled(false);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Recipe r = (Recipe) (listView.getItemAtPosition(position));
                    if(r!=null) {
                        removeBtn.setEnabled(true);
                        isClicked = true;
                    }

                    // delete from bookMark (1)
                    removeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RecipeListActivity.BM.delRecipeFromBookMark(r);
                            recipeList.remove(r);
                            bMArrayAdapter.notifyDataSetChanged();
                            isClicked = false;
                            Toast.makeText(getApplicationContext(), "Deleted from bookmark", Toast.LENGTH_SHORT).show();

                            //disable button is the bookmark is empty or user did not choose recipe
                            if(recipeList.isEmpty()||!isClicked )
                                removeBtn.setEnabled(false);
                        }
                    });


                    // Create a new PopupMenu object
                    PopupMenu popup = new PopupMenu(BookMarkActivity.this, view);

                    // Inflate the menu layout file
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                    // Find the MenuItem for the description and set its title to the recipe's description
                    MenuItem descriptionMenuItem = popup.getMenu().findItem(R.id.menu_description);
                    descriptionMenuItem.setTitle("Ingredients List of "+r.getName()+":"+ r.getIngredients());

                    // Show the PopupMenu
                    popup.show();
                }
            });

            // Long click : delete from bookMark (2)
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    PopupMenu popupMenu = new PopupMenu(BookMarkActivity.this, view);
                    popupMenu.inflate(R.menu.bookmark_delete_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.delete_from_bookmark) {
                                // remove from bookmark
                                Recipe selectedRecipe = (Recipe) parent.getItemAtPosition(position);
                                RecipeListActivity.BM.delRecipeFromBookMark(selectedRecipe);
                                recipeList.remove(selectedRecipe);
                                bMArrayAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Deleted from bookmark", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });

        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }
}