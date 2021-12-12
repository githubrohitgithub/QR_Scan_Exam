package com.rohit.onlne_exams.student.Activities;

import static android.widget.Toast.LENGTH_LONG;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SHomeActivity extends AppCompatActivity  {


    Button scan;
    CircularProgressButton done;

    TextView textView3;
    AutoCompleteTextView  actv;
    public static String Choosensub_code;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity_home);



        done=findViewById(R.id.done);
        textView3=findViewById(R.id.textView3);
        scan=findViewById(R.id.scan);
        actv =findViewById(R.id.multiAutoCompleteTextView1);





        //chemistry
        GLOBAL.data.put("Calculus and Linear Algebra", "18MAT11");
        GLOBAL.data.put("Engineering Chemistry", "18CHE12");
        GLOBAL.data.put("C Programming for Problem Solving", "18CPS13");
        GLOBAL.data.put("Basic Electronics", "18ELN14");
        GLOBAL.data.put("Elements of Mechanical Engineering", "18ME15");
        GLOBAL.data.put("Engineering Chemistry Laboratory", "18CHEL16");
        GLOBAL.data.put("C Programming Laboratory", "18CPL17");
        GLOBAL.data.put("Technical English-I", "18EGH18");

        //physics
        GLOBAL.data.put("Advanced Calculus and Numerical Methods", "18MAT21");

        //3rd
        GLOBAL.data.put("Transform Calculus, Fourier Series And Numerical Techniques", "18MAT31");
        GLOBAL.data.put("Data Structures and Applications", "18CS32");
        GLOBAL.data.put("Analog and Digital Electronics", "18CS33");
        GLOBAL.data.put("Computer Organization", "18CS34");
        GLOBAL.data.put("Software Engineering", "18CS35");
        GLOBAL.data.put("Discrete Mathematical Structures", "18CS36");
        GLOBAL.data.put("Analog and Digital Electronics Laboratory", "18CSL37");
        GLOBAL.data.put("Data Structures Laboratory", "18CSL38");
        GLOBAL.data.put("Vyavaharika Kannada (Kannada for communication)", "18KVK39");
        GLOBAL.data.put("Aadalitha Kannada (Kannada for Administration)", "18KAK39");
        GLOBAL.data.put("Constitution of India, Professional Ethics and Cyber Law", "18CPC39");
        GLOBAL.data.put("ADDITIONAL MATHEMATICS – I", "18MATDIP31");


        //4th
        GLOBAL.data.put("Complex Analysis, Probability and Statistical Methods", "18MAT41");
        GLOBAL.data.put("Design and Analysis of Algorithms", "18CS42");
        GLOBAL.data.put("Operating Systems", "18CS43");
        GLOBAL.data.put("Microcontroller and Embedded Systems", "18CS44");
        GLOBAL.data.put("Object Oriented Concepts", "18CS45");
        GLOBAL.data.put("Data Communication", "18CS46");
        GLOBAL.data.put("Design and Analysis of Algorithm Laboratory", "18CSL47");
        GLOBAL.data.put("Microcontroller and Embedded Systems Laboratory", "18CSL48");
        GLOBAL.data.put("Vyavaharika Kannada (Kannada for communication)", "18KVK49");
        GLOBAL.data.put("ADDITIONAL MATHEMATICS – II", "18MATDIP41");


        //5th
        GLOBAL.data.put("Management, Entrepreneurship for IT idustry", "18CS51");
        GLOBAL.data.put("Computer Networks and Security", "18CS52");
        GLOBAL.data.put("Database Management System", "18CS53");
        GLOBAL.data.put("Automata theory and Computability", "18CS54");
        GLOBAL.data.put("Application Development using Python", "18CS55");
        GLOBAL.data.put("Unix Programming", "18CS56");
        GLOBAL.data.put("Computer Network Laboratory", "18CSL57");
        GLOBAL.data.put("DBMS Laboratory with mini project", "18CSL58");
        GLOBAL.data.put("Environmental Studies", "18CIV59");

        //6th
        GLOBAL.data.put("System Software and Compilers", "18CS61");
        GLOBAL.data.put("Computer Graphics and Visualization", "18CS62");
        GLOBAL.data.put("Web Technology and its applications", "18CS63");
        GLOBAL.data.put("System Software Laboratory", "18CSL66");
        GLOBAL.data.put("Computer Graphics Laboratory with mini project", "18CSL67");
        GLOBAL.data.put("Data Mining and Data Warehousing", "18CS641");
        GLOBAL.data.put("Object Oriented Modelling and Design", "18CS642");
        GLOBAL.data.put("Cloud Computing and its Applications", "18CS643");
        GLOBAL.data.put("Advanced JAVA and J2EE", "18CS644");
        GLOBAL.data.put("System Modelling and Simulation", "18CS645");
        GLOBAL.data.put("Mobile Application Development", "18CS651");
        GLOBAL.data.put("Introduction to Data Structures and Algorithms", "18CS652");
        GLOBAL.data.put("Programming in JAVA", "18CS653");
        GLOBAL.data.put("Introduction to Operating System", "18CS654");

        //7th
        GLOBAL.data.put("Artificial Intelligence and Machine Learning", "18CS71");
        GLOBAL.data.put("Big Data Analytics", "18CS72");
        GLOBAL.data.put("Artificial Intelligence and Machine Learning Laboratory", "18CSL76");
        GLOBAL.data.put("Project Work Phase – 1 ", "18CSP77");
        GLOBAL.data.put("Software Architecture and Design Patterns", "18CS731");
        GLOBAL.data.put("High Performance Computing", "18CS732");
        GLOBAL.data.put("Advanced Computer Architecture", "18CS733");
        GLOBAL.data.put("User Interface Design", "18CS734");
        GLOBAL.data.put("Digital Image Processing", "18CS741");
        GLOBAL.data.put("Network management", "18CS742");
        GLOBAL.data.put("Natural Language Processing", "18CS743");
        GLOBAL.data.put("Cryptography", "18CS744");
        GLOBAL.data.put("Robotic Process Automation Design & Development", "18CS745");
        GLOBAL.data.put("Introduction to Big Data Analytics", "18CS751");
        GLOBAL.data.put("Python Application Programming", "18CS752");
        GLOBAL.data.put("Introduction to Artificial Intelligence", "18CS753");
        GLOBAL.data.put("Introduction to Dot Net framework for Application Development", "18CS754");


        //8th
        GLOBAL.data.put("Internet of Things", "18CS81");
        GLOBAL.data.put("Project Work Phase-2", "18CSP83");
        GLOBAL.data.put("Technical Seminar", "18CSS84");
        GLOBAL.data.put("Internship", "18CSI85");
        GLOBAL.data.put("Mobile Computing", "18CS821");
        GLOBAL.data.put("Storage Area Networks", "18CS822");
        GLOBAL.data.put("NoSQL Database", "18CS823");
        GLOBAL.data.put("Multicore Architecture and Programming", "18CS824");


        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1, GLOBAL.data.values().toArray());

        actv.setAdapter(adapter);
        actv.setThreshold(1);
        actv.setAdapter(adapter);



        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                textView3.setText(GLOBAL.data.get("NoSQL Database"));
                textView3.setText(getKeys(GLOBAL.data, actv.getText().toString()).toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
                textView3.setText("Choose Subject code");
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });




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
    private static Set<String> getKeys(Map<String, String> map, String value) {

        Set<String> result = new HashSet<>();
        if (map.containsValue(value)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.add(entry.getKey());
                }
                // we can't compare like this, null will throws exception
              /*(if (entry.getValue().equals(value)) {
                  result.add(entry.getKey());
              }*/
            }
        }
        return result;
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