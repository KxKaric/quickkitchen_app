package comp3350.quickkitchen.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.quickkitchen.R;

public class HomeActivity extends AppCompatActivity {

    private CheckBox potatoCheck, cheeseCheck;
    private Button search;
    private ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Choose your own ingredients");


        potatoCheck = findViewById(R.id.potatoBtn);
        cheeseCheck = findViewById(R.id.cheeseBtn);

        search = (Button)findViewById(R.id.searchBtn);
        search.setEnabled(false);

        result = new ArrayList<>();


        potatoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(potatoCheck.isChecked()){
                    result.add("potato");
                    search.setEnabled(true);
                }
                else{
                    result.remove("potato");
                    if(!cheeseCheck.isChecked())
                        search.setEnabled(false);
                }
            }
        });

        cheeseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cheeseCheck.isChecked()){
                    result.add("cheese");
                    search.setEnabled(true);
                }
                else{
                    result.remove("cheese");
                    if(!potatoCheck.isChecked())
                        search.setEnabled(false);
                }
            }
        });

        if(!cheeseCheck.isChecked()&&!potatoCheck.isChecked())
            search.setEnabled(false);


    }

    public ArrayList<String> getResult(){return result;}

    public void buttonSearchOnclick(View v){

        // Log.d("a",result.toString());

        Intent i = new Intent(this, RecipeList.class);
        i.putExtra("result",result);
        startActivity(i);
    }

   /* public String handleText(View v){
        String input;
        TextView t = findViewById(R.id.source);
        input = t.getText().toString();

        return input;

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }



}
