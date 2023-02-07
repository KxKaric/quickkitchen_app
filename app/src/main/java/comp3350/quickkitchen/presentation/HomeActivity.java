package comp3350.quickkitchen.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.quickkitchen.R;

public class HomeActivity extends AppCompatActivity {

    private CheckBox potatoCheck, cheeseCheck, flourCheck, onionCheck, tomatoCheck,nothingCheck;
    private Button search;
    private ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Choose your own ingredients");


        potatoCheck = findViewById(R.id.potatoBtn);
        cheeseCheck = findViewById(R.id.cheeseBtn);
        flourCheck = findViewById(R.id.flourBtn);
        onionCheck = findViewById(R.id.onionBtn);
        tomatoCheck = findViewById(R.id.tomatoBtn);
        nothingCheck = findViewById(R.id.nothingBtn); // only for testing

        search = (Button)findViewById(R.id.searchBtn);
        search.setEnabled(false);

        result = new ArrayList<>();


        potatoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(potatoCheck.isChecked())
                    result.add(potatoCheck.getText().toString());
                else
                    result.remove(potatoCheck.getText().toString());
                checkList();

            }
        });

        cheeseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cheeseCheck.isChecked())
                    result.add(cheeseCheck.getText().toString());
                else
                    result.remove(cheeseCheck.getText().toString());
                checkList();
            }
        });

        onionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onionCheck.isChecked())
                    result.add(onionCheck.getText().toString());
                else
                    result.remove(onionCheck.getText().toString());
                checkList();
            }
        });

        flourCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flourCheck.isChecked())
                    result.add(flourCheck.getText().toString());
                else
                    result.remove(flourCheck.getText().toString());
                checkList();
            }
        });

        tomatoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tomatoCheck.isChecked())
                    result.add(tomatoCheck.getText().toString());
                else
                    result.remove(tomatoCheck.getText().toString());
                checkList();
            }
        });

        nothingCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nothingCheck.isChecked())
                    result.add(nothingCheck.getText().toString());
                else
                    result.remove(nothingCheck.getText().toString());
                checkList();
            }
        });

    }

    public ArrayList<String> getResult(){return result;}

    private void checkList(){
        if(result.isEmpty())
            search.setEnabled(false);
        else
            search.setEnabled(true);
    }

    public void buttonSearchOnclick(View v){

        // Log.d("a",result.toString());

        Intent i = new Intent(this, RecipeListActivity.class);
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
