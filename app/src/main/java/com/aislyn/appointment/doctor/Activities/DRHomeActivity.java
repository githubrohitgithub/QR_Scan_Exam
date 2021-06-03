package com.aislyn.appointment.doctor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aislyn.appointment.LandingActivity;
import com.aislyn.appointment.R;
import com.aislyn.appointment.doctor.ModelResponse.DeleteAccountResponse;
import com.aislyn.appointment.doctor.Newfragments.AddHospitalsFragment;
import com.aislyn.appointment.doctor.Newfragments.DashboardFragment;
import com.aislyn.appointment.doctor.Newfragments.ProfileFragment;
import com.aislyn.appointment.doctor.DoctorRetrofitClient;
import com.aislyn.appointment.doctor.SharedPrefmanager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DRHomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    SharedPrefmanager drSharedPrefmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dractivity_home);


//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());

        drSharedPrefmanager =new SharedPrefmanager(getApplicationContext());

        //Toast.makeText(this, drSharedPrefmanager.getuser().getDrname(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;

        switch (item.getItemId()){
            case R.id.dashboard:
                fragment=new DashboardFragment();
                break;
            case R.id.profile:
                fragment=new ProfileFragment();
                break;
            case R.id.addhospitals:
                fragment=new AddHospitalsFragment();
                break;


        }

        if(fragment!=null){
            loadFragment(fragment);
        }


        return true;
    }

   void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout,fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.logoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


            case R.id.logout:
                logoutuser();
                break;

            case R.id.deleteAccount:
                deleteaccount();
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    private void logoutuser() {

        drSharedPrefmanager.logout();
        Intent intent=new Intent(DRHomeActivity.this, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();



    }
    private void adminlogout() {

        drSharedPrefmanager.logout();


    }

    private void deleteaccount() {


        Call<DeleteAccountResponse> call= DoctorRetrofitClient.getInstance().getApi().
                deleteaccount(drSharedPrefmanager.getuser().getId());



        call.enqueue(new Callback<DeleteAccountResponse>() {
            @Override
            public void onResponse(Call<DeleteAccountResponse> call, Response<DeleteAccountResponse> response) {

                DeleteAccountResponse deleteAccountResponse=response.body();
                if(response.isSuccessful()){

                    if(deleteAccountResponse.getError().equals("200")){


                        logoutuser();
                        Toast.makeText(DRHomeActivity.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DRHomeActivity.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(DRHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAccountResponse> call, Throwable t) {

                Toast.makeText(DRHomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(drSharedPrefmanager.getuser().getId()==9){
            adminlogout();
        }
    }
}