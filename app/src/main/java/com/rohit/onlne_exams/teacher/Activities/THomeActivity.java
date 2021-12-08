package com.rohit.onlne_exams.teacher.Activities;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.List;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_home);

        context=this;

        TEACHER_ID=getIntent().getStringExtra("teacher_id");


        actv =findViewById(R.id.multiAutoCompleteTextView1);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        add=findViewById(R.id.add);
        previus_result=findViewById(R.id.previus_result);



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











}