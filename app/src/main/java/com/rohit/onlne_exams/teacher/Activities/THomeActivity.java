package com.rohit.onlne_exams.teacher.Activities;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.Activities.SHomeActivity;
import com.rohit.onlne_exams.student.Activities.Starttest;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class THomeActivity extends AppCompatActivity  {


    EditText sub_code;
    Button add,previus_result;
    public static String TEACHER_ID,SUB_CODE,SET_CODE;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    Context context;
    AutoCompleteTextView actv;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_home);

        context=this;

        TEACHER_ID=getIntent().getStringExtra("teacher_id");


        actv =findViewById(R.id.multiAutoCompleteTextView1);
        textView3 =findViewById(R.id.textView3);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        add=findViewById(R.id.add);
        previus_result=findViewById(R.id.previus_result);




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

        Dexter.withContext(context)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {



                if(!report.areAllPermissionsGranted()) {


                    Toast.makeText(context, "Not all permissions granted.", Toast.LENGTH_SHORT).show();

                }




            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                token.continuePermissionRequest();


            }
        }).check();



        previus_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),StudentResultActivity.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(view -> {

            String code=actv.getText().toString();
            int selectedId=radioSexGroup.getCheckedRadioButtonId();
            radioSexButton= findViewById(selectedId);

            if(code.isEmpty()){
                sub_code.requestFocus();
                sub_code.setError("Please Enter Subject Code");
                return;
            }



            SUB_CODE=code;
            SET_CODE=radioSexButton.getText().toString();



            Call<SRegisterResponse> call= RetrofitClient
                    .getInstance()
                    .getApi()
                    .checksubcode(SUB_CODE,SET_CODE);

            call.enqueue(new Callback<SRegisterResponse>() {
                @Override
                public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {


                    if(response.isSuccessful()){



                        if(response.body().getError().equals("200")){


                            Intent intent=new Intent(getApplicationContext(),QuestionAddMainActivity.class);
                            startActivity(intent);
                            finish();


                        }else{

                            Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                        }



                    }else{
                        Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<SRegisterResponse> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                }
            });









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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        GLOBAL.data.clear();
    }
}