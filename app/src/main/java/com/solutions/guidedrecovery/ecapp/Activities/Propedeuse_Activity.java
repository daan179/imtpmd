package com.solutions.guidedrecovery.ecapp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

public class Propedeuse_Activity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    CheckBox cb;

    TextView behaaldeEcs;
    CourseAdapter adapter;
    ArrayList<Vak> arrayOfCourses;
    ListView listView;
    int sum = 0;
    int HaveNrEc = 0;
    int TotalPropEc = 60;

    // URL to get contacts JSON
    private static String url = "http://185.183.182.128/api/PropedeuseCourses";

    ArrayList<HashMap<String, String>> contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_propedeuse_);

        final TextView EcTotal = (TextView) findViewById(R.id.textView5);
        final TextView EcNogNietGehaald = (TextView) findViewById(R.id.textView7);
        final TextView nogTebehalen = (TextView) findViewById(R.id.textView6);
        CheckBox cb = (CheckBox)findViewById(R.id.checkBox);





        // Construct the data source
        arrayOfCourses = new ArrayList<>();

        // Create the adapter to convert the array to views
        adapter = new CourseAdapter(
                Propedeuse_Activity.this, contactList,
                R.layout.list_item, new String[]{"code", "title",
                "credits"}, new int[]{R.id.moduleCode,
                R.id.module, R.id.ec},TotalPropEc);



        contactList = new ArrayList<>();
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);

        // Get all the info
//        showInfo();


        new GetContacts().execute();


    }





    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Propedeuse_Activity.this);
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
                    JSONArray contacts =  new JSONArray(jsonStr);

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
                    Propedeuse_Activity.this, contactList,
                    R.layout.list_item, new String[]{"code", "title",
                    "credits"}, new int[]{R.id.moduleCode,
                    R.id.module, R.id.ec},TotalPropEc);
            Log.d("123","check onPostExecute");
            listView.setAdapter(adapter);

//            cb = (CheckBox) findViewById(R.id.checkBox);
//            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView,
//                                             boolean isChecked) {
//                    if (isChecked) {
//                        status.set(position, true);
//                    } else {
//                        status.set(position, false);
//                    }
//                }
//            });

        }

    }

}


