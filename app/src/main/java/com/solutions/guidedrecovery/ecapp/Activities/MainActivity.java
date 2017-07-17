package com.solutions.guidedrecovery.ecapp.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.solutions.guidedrecovery.ecapp.R;

public class MainActivity extends AppCompatActivity {

    private static Button propedeuse_knop,hoofdfase_knop;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_app, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_one:
                Intent intent = new Intent(this, About_Activity.class);
                startActivity(intent);
                return true;
            case R.id.Home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();




    }

    public void OnClickButtonListener(){

        propedeuse_knop = (Button)findViewById(R.id.propedeuse);
        propedeuse_knop.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        Intent i = new Intent(v.getContext(),Propedeuse_Activity.class);

                        startActivity(i);
                    }
                }
        );

        hoofdfase_knop = (Button)findViewById(R.id.hoofdFase);
        hoofdfase_knop.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        Intent i = new Intent(v.getContext(),Richting_Activity.class);

                        startActivity(i);
                    }
                }
        );


    }

}


