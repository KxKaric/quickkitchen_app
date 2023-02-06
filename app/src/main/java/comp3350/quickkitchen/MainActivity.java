package comp3350.quickkitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.quickkitchen.presentation.RecipeList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CheckBox potatoCheck, cheeseCheck;
    private Button search;
    private ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void buttonPotatoOnclick(View v){

    }

    public void buttonCheeseOnclick(View v){

    }

    public void buttonSearchOnclick(View v){
        //launch a new activity


        //View myview = findViewById(R.id.searchBtn);

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
}