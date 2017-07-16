package com.solutions.guidedrecovery.ecapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.solutions.guidedrecovery.ecapp.Activities.Propedeuse_Activity;
import com.solutions.guidedrecovery.ecapp.R;
import com.solutions.guidedrecovery.ecapp.models.Vak;

import java.util.ArrayList;
import java.util.HashMap;

import static android.media.CamcorderProfile.get;
import static com.solutions.guidedrecovery.ecapp.R.id.checkBox;
import static com.solutions.guidedrecovery.ecapp.R.id.vakken;


/**
 * Created by Dipak on 21-6-2017.
 */

public class CourseAdapter extends BaseAdapter {


    private static LayoutInflater inflater = null;
    private ArrayList<Boolean> status = new ArrayList<Boolean>();
    private ArrayList<HashMap<String, String>> data;
    ArrayList<Vak> vakken;
    int lay;
    String [] from;
    int []to;
    int HaveNrEc;
    int TotalEcRequired;
    Context context;
    ViewHolder holder;
    SharedPreferences.Editor editor;
    public CourseAdapter(){

    }


    public CourseAdapter(Context context, ArrayList<HashMap<String, String>> data,int lay, String [] from,int []to, int TotalEcRequired){
//        super(context, 0, vakken);
        this.context = context;

        this.TotalEcRequired = TotalEcRequired;
        this.lay = lay;
        this.from = from;
        this.to = to;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data != null){
            for (int i = 0; i < data.size(); i++) {
                status.add(false);
            }
        }

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (data != null) {
            return data.size();
        }
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        holder = new ViewHolder();


        final TextView EcTotal = (TextView) ((Activity)context).findViewById(R.id.textView5);
        final TextView EcNogNietGehaald = (TextView) ((Activity)context).findViewById(R.id.textView7);
        final TextView nogTebehalen = (TextView) ((Activity)context).findViewById(R.id.textView6);



        final SharedPreferences sp = context.getSharedPreferences("key", 0);


        String ecNodig = sp.getString("ecNogNodig","");
        String nogTeDoen = sp.getString("nogTeBehalen","");
        String valueTotal = sp.getString("ecTotal","");
        final boolean cbCheck = sp.getBoolean("check" + position, false);

        EcNogNietGehaald.setText(ecNodig);
        nogTebehalen.setText(nogTeDoen);
        EcTotal.setText(valueTotal);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox);

            holder.cb.setChecked(cbCheck);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }



        final HashMap<String, String> info = data.get(position);

        TextView tvModule = (TextView) convertView.findViewById(R.id.module);
        TextView tvModuleCode = (TextView) convertView.findViewById(R.id.moduleCode);
        TextView tvEc = (TextView) convertView.findViewById(R.id.ec);



        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {



                if (isChecked)
                {
                    if (status.get(position)!= true )
                    {
                        status.set(position, true);
                        int  aantalEcs = Integer.parseInt(info.get("credits"));
                        HaveNrEc += aantalEcs;
                        TotalEcRequired -= aantalEcs;

                        EcTotal.setText(  String.valueOf(HaveNrEc));
                        EcNogNietGehaald.setText(  String.valueOf(TotalEcRequired));
                        nogTebehalen.setText("Nog te behalen Ec's: ");
                        Log.d("123",""+HaveNrEc+TotalEcRequired);
                        if(  TotalEcRequired== 0)
                        {
                            Log.d("123","gehaald");
                            CharSequence text = "Gefeliciteerd, je hebt al je EC's binnen!";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            EcNogNietGehaald.setText("");
                            nogTebehalen.setText("Propedeuse behaald!");

                        }

                    }

                }
                else
                {
                    if (status.get(position)!= false )
                    {
                        status.set(position, false);
                        int aantalEcs = Integer.parseInt(info.get("credits"));
                        HaveNrEc -= aantalEcs;
                        TotalEcRequired += aantalEcs;
                        EcTotal.setText(  String.valueOf(HaveNrEc));
                        EcNogNietGehaald.setText(  String.valueOf(TotalEcRequired));
                        Log.d("EC - = ", String.valueOf(aantalEcs));


                    }
                }

                SharedPreferences sp = context.getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();





                sedt.putString("ecNogNodig", EcNogNietGehaald.getText().toString());
                sedt.putString("nogTeBehalen", nogTebehalen.getText().toString());
                sedt.putString("ecTotal", EcTotal.getText().toString());
                sedt.putBoolean("check" + position, isChecked);

                sedt.commit();


            }
        });



        holder.cb.setChecked(status.get(position));







        // Populate the data into the template view using the data object
        tvModuleCode.setText(info.get("code"));
        tvModule.setText(info.get("title"));
        tvEc.setText(info.get("credits"));

        // Return the completed view to render on screen
        return convertView;
    }

    static class ViewHolder{
        CheckBox cb;
    }

}

