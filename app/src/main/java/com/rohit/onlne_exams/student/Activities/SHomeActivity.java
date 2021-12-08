package com.rohit.onlne_exams.student.Activities;

import static android.widget.Toast.LENGTH_LONG;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SHomeActivity extends AppCompatActivity  {


    Button scan;
    CircularProgressButton done;


    AutoCompleteTextView  actv;
    public static String Choosensub_code;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity_home);



        done=findViewById(R.id.done);
        scan=findViewById(R.id.scan);
        actv =findViewById(R.id.multiAutoCompleteTextView1);




        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT11");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CHE12");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CPS13");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELN14");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ME15");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CHEL16");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CPL17");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGH18");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT21");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CHE22");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CPS23");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELN24");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ME25");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CHEL26");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CPL27");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGH28");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT11");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18PHY12");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CIV14");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGDL15");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18PHYL16");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELEL17");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGH18");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELE13");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT21");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18PHY22");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELE23");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CIV24");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGDL25");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18PHYL26");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18ELEL27");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18EGH28");



        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT31");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS32");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS33");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS34");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS35");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS36");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL37");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL38");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18KVK39");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18KAK39");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CPC39");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MATDIP31");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MAT41");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS42");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS43");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS44");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS45");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS46");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL47");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL48");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18KVK49");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18KAK49");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18MATDIP41");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS51");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS52");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS53");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS54");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS55");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS56");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL57");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL58");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CIV59");

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS61");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS62");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS63");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL66");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL67");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSMP68");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS641");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS642");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS643");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS644");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS645");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS651");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS652");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS653");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS654");


        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS71");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS72");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSL76");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSP77");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS731");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS732");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS733");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS734");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS741");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS742");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS743");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS744");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS745");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS751");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS752");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS753");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS754");


        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS81");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSP83");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSS84");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CSI85");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS821");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS822");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS823");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("18CS824");


        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1, GLOBAL.SUBJECT_CODE_ARRAYLIST);

        actv.setAdapter(adapter);
        actv.setThreshold(1);
        actv.setAdapter(adapter);


        scan.setOnClickListener(view -> {

            Choosensub_code=actv.getText().toString();

            if(Choosensub_code.isEmpty()){
                actv.requestFocus();
                actv.setError("Please choose subject code");
                done.revertAnimation();
                return;
            }

            if(GLOBAL.SUBJECT_CODE_ARRAYLIST.contains(Choosensub_code)){

                scanqr();
            }else{

                actv.requestFocus();
                actv.setError("Please choose subject code");
                done.revertAnimation();

            }






        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                checkstudent();



            }
        });


    }

    public void checkstudent() {

        Call<SRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .checkstudent(STUDENT_ID,Choosensub_code);

        call.enqueue(new Callback<SRegisterResponse>() {
            @Override
            public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {

                SRegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){



                    if(response.body().getError().equals("400")){


                        if(url==null){

                            Toast.makeText(getApplicationContext(), "Scan QR", Toast.LENGTH_SHORT).show();

                        }else{

                            Intent intent=new Intent(SHomeActivity.this,Starttest.class);
                            intent.putExtra("US",url);
                            startActivity(intent);
                            finish();
                        }


                    }else{

                        Toast.makeText(getApplicationContext(), "You already given exam.", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SRegisterResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void scanqr() {

        IntentIntegrator intentIntegrator=new IntentIntegrator(SHomeActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setPrompt("Scanning");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();


    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null &&result.getContents()!=null){

            //Toast.makeText(login.this,result.getContents(),Toast.LENGTH_LONG).show();

            url=result.getContents();
            scan.setText("Click to start button below");


            // Toast.makeText(Scan.this,url,Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(SHomeActivity.this,"Please scan properly",LENGTH_LONG).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }





}