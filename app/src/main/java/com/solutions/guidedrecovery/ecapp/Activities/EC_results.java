package com.solutions.guidedrecovery.ecapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.solutions.guidedrecovery.ecapp.R;



import java.util.ArrayList;
import java.util.List;

import static com.solutions.guidedrecovery.ecapp.R.id.chart;

public class EC_results extends AppCompatActivity {


    private PieChart mChart;
    int x;
    int y;

    private int[] yData = { x,y };
    private String[] xData = { "EC's behaald", "Overige ec's" };
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
        setContentView(R.layout.activity_ec_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mChart = (PieChart) findViewById(R.id.piechart);

//        PieChart pieChart = (PieChart) findViewById(R.id.piechart);





//        mChart.setUsePercentValues(true);


        // enable hole and configure
        mChart.setDrawHoleEnabled(true);

        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);


        Intent intent=this.getIntent();
        if(intent !=null)
            x = Integer.parseInt(intent.getStringExtra("ecTotal"));
            y = Integer.parseInt(intent.getStringExtra("ecNogNodig"));
        Log.d("0079 not te halen",""+y);
        Log.d("0079 behaanld",""+x);





//        mChart.setUsePercentValues(true);


        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(x, "EC's behaald"));
        entries.add(new PieEntry(y, "Overige Ec's"));




        PieDataSet dataSet = new PieDataSet(entries, "EC's");


        ArrayList<String> Entry = new ArrayList<String>();

        Entry.add("Ec's behaald");
        Entry.add("Overige EC's");

//        mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(Entry));
        final int[] MY_COLORS = {getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);
;
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(25f);
        data.setValueTextColor(Color.WHITE);
         mChart.animateXY(1400, 1400);
        mChart.setDrawHoleEnabled(true);
        mChart.setTransparentCircleRadius(25f);
        mChart.setHoleRadius(35f);
        Description description = new Description();
        description.setPosition(600f,825f);
        description.setTextColor(getResources().getColor(R.color.colorPrimary));
        description.setText("EC's");
        description.setTextSize(25f);
        mChart.setDescription(description);
//        data.setValueFormatter(new PercentFormatter());

        Legend l = mChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis


        mChart.setData(data);




    }


    }




