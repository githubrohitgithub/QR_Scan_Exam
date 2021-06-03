package com.aislyn.appointment.users.Newfragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aislyn.appointment.AdapterFetchBooked;
import com.aislyn.appointment.ConfigAllbooked;
import com.aislyn.appointment.FetchAllBooked;
import com.aislyn.appointment.Mysingleton;
import com.aislyn.appointment.PaymentActivity;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.SharedPrefmanager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {


    SharedPrefmanager sharedPrefmanager;
    FetchAllBooked fetchallbooked;
    AdapterFetchBooked adapter;
    ListView listView;
    TextView nodata;
    ProgressDialog progressDoalog;
    ShimmerFrameLayout layout;
    TextView username;
    public static ArrayList<FetchAllBooked> fetch_all_booked_arraylist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        sharedPrefmanager=new SharedPrefmanager(getActivity());
        //layout=view.findViewById(R.id.shimmer);
//        if(sharedPrefmanager.getuser().getUsername()==null){
//
//            etname.setText("Hello");
//
//        }else{
//            name=sharedPrefmanager.getuser().getUsername();
//            etname.setText("Hello "+name);
//        }

        new requestallbooked().execute("http://aislyn.in/DRappointment/fetchappointments.php?filter="+sharedPrefmanager.getuser().getId()+"&name="+sharedPrefmanager.getuser().getUsername());

        listView = view.findViewById(R.id.myListView);
        username = view.findViewById(R.id.username);
        nodata = view.findViewById(R.id.nodata);
        layout=view.findViewById(R.id.shimmer);
        adapter = new AdapterFetchBooked(getContext(), fetch_all_booked_arraylist);
        listView.setAdapter(adapter);

        username.append(sharedPrefmanager.getuser().getUsername());

        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please wait..");
        progressDoalog.setTitle("Deleting...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        listView.setOnItemClickListener((parent, v, position, id) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Appointment id "+fetch_all_booked_arraylist.get(position).getAppointment_id());



            CharSequence[] dialogItem = {"Cancel appointment"};

            builder.setIcon(R.drawable.ic_doctor_logo);


            builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(DialogInterface dialog, int i) {

                    if (i == 0) {

                        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                        builder.setMessage("Do you really want to cancel appointment ?")
                                .setCancelable(false)
                                .setPositiveButtonIcon(getResources().getDrawable(R.drawable.ic_yes))
                                .setNegativeButtonIcon(getResources().getDrawable(R.drawable.ic_no))
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        new deleteappointment().execute("http://aislyn.in/DRappointment/cancel.php?appointment_id="+fetch_all_booked_arraylist.get(position).getAppointment_id());


                                    }
                                })

                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();






                    }

                }
            });


            builder.create().show();


        });




        return view;
    }


    private class requestallbooked extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {



            String url=strings[0];

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            fetch_all_booked_arraylist.clear();


                            try {
                                JSONArray jsonArray=response.getJSONArray("result");


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jo = jsonArray.getJSONObject(i);
                                    String Sappointment_id = jo.getString(ConfigAllbooked.KEY_appointment_id);
                                    String Spatient_name = jo.getString(ConfigAllbooked.KEY_patient_name);
                                    String Spatient_id = jo.getString(ConfigAllbooked.KEY_patient_id);
                                    String Sdoctor_name = jo.getString(ConfigAllbooked.KEY_doctor_name);
                                    String Sdoctor_id = jo.getString(ConfigAllbooked.KEY_doctor_id);
                                    String Spatient_phone = jo.getString(ConfigAllbooked.KEY_patient_phone);
                                    String Sexpired = jo.getString(ConfigAllbooked.KEY_expired);


                                    final HashMap<String, String> user_courses = new HashMap<>();
                                    user_courses.put(ConfigAllbooked.KEY_appointment_id, Sappointment_id);
                                    user_courses.put(ConfigAllbooked.KEY_patient_name, Spatient_name);
                                    user_courses.put(ConfigAllbooked.KEY_patient_id, Spatient_id);
                                    user_courses.put(ConfigAllbooked.KEY_doctor_name, Sdoctor_name);
                                    user_courses.put(ConfigAllbooked.KEY_doctor_id, Sdoctor_id);
                                    user_courses.put(ConfigAllbooked.KEY_patient_phone, Spatient_phone);
                                    user_courses.put(ConfigAllbooked.KEY_expired, Sexpired);


                                    fetchallbooked = new FetchAllBooked(Integer.valueOf(Sappointment_id), Spatient_name, Spatient_id, Sdoctor_name, Sdoctor_id,Spatient_phone,Sexpired);
                                    fetch_all_booked_arraylist.add(fetchallbooked);
                                    adapter.notifyDataSetChanged();

                                    layout.stopShimmer();
                                    layout.hideShimmer();
                                    layout.setVisibility(View.GONE);

                                }



                                if(fetch_all_booked_arraylist.isEmpty()){

                                    nodata.setVisibility(View.VISIBLE);
                                    listView.setVisibility(View.GONE);
                                    layout.stopShimmer();
                                    layout.hideShimmer();
                                    layout.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();

                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }




                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(getContext(), "Check connectivity", Toast.LENGTH_SHORT).show();


                }
            });

            Mysingleton.getInstance(getContext()).addToRequestque(request);


            return null;
        }


    }

    private class deleteappointment extends AsyncTask<String,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDoalog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {



            String url=strings[0];
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    if(response.contains("1")){

                        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();


                    }else{


                        Toast.makeText(getContext(),"Failed", Toast.LENGTH_SHORT).show();

                    }






                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(getContext(), "Check your internet", Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;

                }
            };

            Mysingleton.getInstance(getContext()).addToRequestque(request);


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            new requestallbooked().execute("http://aislyn.in/DRappointment/fetchappointments.php?filter="+sharedPrefmanager.getuser().getId()+"&name="+sharedPrefmanager.getuser().getUsername());

            progressDoalog.dismiss();
        }
    }
}