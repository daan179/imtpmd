package com.solutions.guidedrecovery.ecapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.solutions.guidedrecovery.ecapp.HttpHandler;
import com.solutions.guidedrecovery.ecapp.R;
import com.solutions.guidedrecovery.ecapp.adapters.CourseAdapter;
import com.solutions.guidedrecovery.ecapp.models.Vak;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HoofdFase_Activity extends AppCompatActivity {


    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    TextView behaaldeEcs;
    CourseAdapter adapter;
    ArrayList<Vak> arrayOfCourses;
    ListView listView;
    Button result_knop;
    int sum = 0;
    int TotalFaseEc;
    private static String url;
    Intent i;
    String s;
    ArrayList<HashMap<String, String>> contactList;
    TextView EcTotal;
    TextView nogTebehalen;

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
        setContentView(R.layout.activity_hoofd_fase_);



//        Log.d("test", "" + nogTeDoen);
        OnClickButtonListener();

        Intent intent = this.getIntent();
        if (intent != null)
            s = intent.getStringExtra("EXTRA_SESSION_ID");

//        String s = i.getStringExtra("EXTRA_SESSION_ID");
        Log.d("123", s);


//        OnClickButtonListener();

        if (s.equals("MT")) {
            url = "http://185.183.182.128/api/mtcourses";
            setTitle(R.string.MT);
            TotalFaseEc = 167;
        }
        if (s.equals("SE")) {
            url = "http://185.183.182.128/api/secourses";
            setTitle(R.string.SE);
            TotalFaseEc = 164;
        }
        if (s.equals("BIT")) {
            url = "http://185.183.182.128/api/bitcourses";
            setTitle(R.string.BIT);
            TotalFaseEc = 170;
        }
        if (s.equals("FICT")) {
            url = "http://185.183.182.128/api/fitcourses";
            setTitle(R.string.FICT);
            TotalFaseEc = 167;
        }


        // Construct the data source
        arrayOfCourses = new ArrayList<>();

        // Create the adapter to convert the array to views
        adapter = new CourseAdapter(
                HoofdFase_Activity.this, contactList,
                R.layout.list_item, new String[]{"code", "title",
                "credits"}, new int[]{R.id.moduleCode,
                R.id.module, R.id.ec}, TotalFaseEc, s);


        contactList = new ArrayList<>();
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.listView);


        new HoofdFase_Activity.GetContacts().execute();


    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(HoofdFase_Activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {


                    // Getting JSON Array node
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("code");
                        String email = c.getString("title");
                        String address = c.getString("credits");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();


                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("code", name);
                        contact.put("title", email);
                        contact.put("credits", address);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            adapter = new CourseAdapter(
                    HoofdFase_Activity.this, contactList,
                    R.layout.list_item, new String[]{"code", "title",
                    "credits"}, new int[]{R.id.moduleCode,
                    R.id.module, R.id.ec}, TotalFaseEc, s);
            Log.d("123", "check onPostExecute");
            listView.setAdapter(adapter);


        }

    }

    public void OnClickButtonListener() {




//        Log.d("testButton", EcTotal);
        result_knop = (Button)findViewById(R.id.result);
        result_knop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final SharedPreferences sp = getSharedPreferences("key", 0);
                        String ecNodig = sp.getString("ecNogNodig", "");
                        String valueTotal = sp.getString("ecTotal", "");

                        Log.d("testButton", valueTotal+ ecNodig);
                        Intent i = new Intent(v.getContext(), EC_results.class);


                        i.putExtra("ecTotal", valueTotal);
                        i.putExtra("ecNogNodig", ecNodig);
                        startActivity(i);
                    }
                }
        );


    }
}


