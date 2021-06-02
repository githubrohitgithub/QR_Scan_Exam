package com.aislyn.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aislyn.appointment.users.Activities.HomeActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class PaymentActivity extends AppCompatActivity {

    CircularProgressButton pay;
    String fee,patient_name,patient_id,doctor_id,doctor_name,patient_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        fee=getIntent().getStringExtra("fee");
        doctor_name=getIntent().getStringExtra("doctor_name");
        doctor_id=getIntent().getStringExtra("doctor_id");
        patient_id=getIntent().getStringExtra("patient_id");
        patient_name=getIntent().getStringExtra("patient_name");
        patient_phone=getIntent().getStringExtra("patient_phone");


        pay=findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                book("http://aislyn.in/DRappointment/bookappointment.php?feekey="+fee+"&pnamekey="+patient_name+"&pidkey="+patient_id+"&didkey="+doctor_id+"&dnamekey="+doctor_name+"&pphonekey="+patient_phone);
                pay.startAnimation();



            }



        });

    }

    private void book(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if(response.contains("1")){

                   pay.revertAnimation();
                    Toast.makeText(PaymentActivity.this, "Booked", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
                    finish();

                }else{

                    pay.revertAnimation();
                    Toast.makeText(PaymentActivity.this,"Already booked", Toast.LENGTH_SHORT).show();

                }






            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

                pay.revertAnimation();
                Toast.makeText(PaymentActivity.this, "Check your internet", Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;

            }
        };

//        RequestQueue requestQueue = Volley.newRequestQueue(EmailActivity.this);
//        requestQueue.add(request);
        Mysingleton.getInstance(PaymentActivity.this).addToRequestque(request);

    }

}