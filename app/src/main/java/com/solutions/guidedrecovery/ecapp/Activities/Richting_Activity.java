package com.solutions.guidedrecovery.ecapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.solutions.guidedrecovery.ecapp.R;

public class Richting_Activity extends AppCompatActivity {

    private static Button MT,BIT,FICT,SE;
    // geeft righting mee
    String sessionId;

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
        setContentView(R.layout.activity_richting_);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_1);
//        setSupportActionBar(toolbar);
        OnClickButtonListener();
    }
    public void OnClickButtonListener(){

        MT = (Button)findViewById(R.id.button_MT);
        MT.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        sessionId = "MT";
                        Intent i = new Intent(v.getContext(),HoofdFase_Activity.class);
                        i.putExtra("EXTRA_SESSION_ID", sessionId);

                        startActivity(i);
                    }
                }
        );

        SE = (Button)findViewById(R.id.button_SE);
        SE.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        sessionId = "SE";
                        Intent i = new Intent(v.getContext(),HoofdFase_Activity.class);
                        i.putExtra("EXTRA_SESSION_ID", sessionId);

                        startActivity(i);
                    }
                }
        );

        BIT = (Button)findViewById(R.id.button_BIT);
        BIT.setOnClickListener(
                new View.OnClickListener(){
                    @Override

                    public void  onClick(View v){
                        sessionId = "BIT";
                        Intent i = new Intent(v.getContext(),HoofdFase_Activity.class);
                        i.putExtra("EXTRA_SESSION_ID", sessionId);

                        startActivity(i);
                    }
                }
        );

        FICT = (Button)findViewById(R.id.button_FICT);
        FICT.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        sessionId = "FICT";
                        Intent i = new Intent(v.getContext(),HoofdFase_Activity.class);
                        i.putExtra("EXTRA_SESSION_ID", sessionId);

                        startActivity(i);
                    }
                }
        );


    }

    }



