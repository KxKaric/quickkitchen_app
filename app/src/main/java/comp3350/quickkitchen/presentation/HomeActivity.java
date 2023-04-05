package comp3350.quickkitchen.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import comp3350.quickkitchen.R;
import comp3350.quickkitchen.application.Main;

// home page UI
public class HomeActivity extends AppCompatActivity {

    private CheckBox potatoCheck, cheeseCheck, flourCheck, onionCheck, tomatoCheck, oilCheck, gravyCheck;
    private Button search;
    private ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //lay out of home page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Choose your own ingredients");
        copyDatabaseToDevice();

        potatoCheck = findViewById(R.id.potatoBtn);
        cheeseCheck = findViewById(R.id.cheeseBtn);
        flourCheck = findViewById(R.id.flourBtn);
        onionCheck = findViewById(R.id.onionBtn);
        tomatoCheck = findViewById(R.id.tomatoBtn);
        oilCheck = findViewById(R.id.oilBtn);
        gravyCheck = findViewById(R.id.gravyBtn);

        // Set up the result list and search button
        result = new ArrayList<>();
        search = (Button)findViewById(R.id.searchBtn);
        search.setEnabled(false);

        // Set a single OnClickListener for all the checkboxes
        View.OnClickListener checkboxListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the tag associated with the checkbox that was clicked
                String checkIngs = view.getTag().toString();

                // If the checkbox is checked, add it to the result list
                if (((CheckBox) view).isChecked())
                    result.add(checkIngs);
                else
                    result.remove(checkIngs);

                // Update the search button
                checkList();
            }
        };

        // Set the OnClickListener for all the checkboxes
        potatoCheck.setOnClickListener(checkboxListener);
        potatoCheck.setTag(potatoCheck.getText().toString());
        cheeseCheck.setOnClickListener(checkboxListener);
        cheeseCheck.setTag(cheeseCheck.getText().toString());
        flourCheck.setOnClickListener(checkboxListener);
        flourCheck.setTag(flourCheck.getText().toString());
        onionCheck.setOnClickListener(checkboxListener);
        onionCheck.setTag(onionCheck.getText().toString());
        tomatoCheck.setOnClickListener(checkboxListener);
        tomatoCheck.setTag(tomatoCheck.getText().toString());
        oilCheck.setOnClickListener(checkboxListener);
        oilCheck.setTag(oilCheck.getText().toString());
        gravyCheck.setOnClickListener(checkboxListener);
        gravyCheck.setTag(gravyCheck.getText().toString());
    }

//        potatoCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(potatoCheck.isChecked())
//                    result.add(potatoCheck.getText().toString());
//                else
//                    result.remove(potatoCheck.getText().toString());
//                checkList();
//
//            }
//        });
//
//        cheeseCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cheeseCheck.isChecked())
//                    result.add(cheeseCheck.getText().toString());
//                else
//                    result.remove(cheeseCheck.getText().toString());
//                checkList();
//            }
//        });
//
//        onionCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onionCheck.isChecked())
//                    result.add(onionCheck.getText().toString());
//                else
//                    result.remove(onionCheck.getText().toString());
//                checkList();
//            }
//        });
//
//        flourCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(flourCheck.isChecked())
//                    result.add(flourCheck.getText().toString());
//                else
//                    result.remove(flourCheck.getText().toString());
//                checkList();
//            }
//        });
//
//        tomatoCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(tomatoCheck.isChecked())
//                    result.add(tomatoCheck.getText().toString());
//                else
//                    result.remove(tomatoCheck.getText().toString());
//                checkList();
//            }
//        });
//
//        oilCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(oilCheck.isChecked())
//                    result.add(oilCheck.getText().toString());
//                else
//                    result.remove(oilCheck.getText().toString());
//                checkList();
//            }
//        });
//
//        gravyCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(gravyCheck.isChecked())
//                    result.add(gravyCheck.getText().toString());
//                else
//                    result.remove(gravyCheck.getText().toString());
//                checkList();
//            }
//        });

 //   }
    private void checkList(){
        if(result.isEmpty())
            search.setEnabled(false);
        else
            search.setEnabled(true);
    }

    public void buttonSearchOnclick(View v){
        Intent i = new Intent(this, RecipeListActivity.class);
        i.putExtra("result",result);
        startActivity(i);
    }

    public void buttonBookMarkOnclick(View v){
        Intent i = new Intent(this, BookMarkActivity.class);
        startActivity(i);
    }

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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }


    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

}
