package com.aislyn.appointment.users.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aislyn.appointment.LandingActivity;
import com.aislyn.appointment.users.ModelResponse.DeleteAccountResponse;
import com.aislyn.appointment.users.Newfragments.DashboardFragment;
import com.aislyn.appointment.users.Newfragments.HospitalFragment;
import com.aislyn.appointment.users.Newfragments.ProfileFragment;
import com.aislyn.appointment.users.Newfragments.DoctorsFragment;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.RetrofitClient;
import com.aislyn.appointment.users.SharedPrefmanager;
import com.aislyn.appointment.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding binding;

    BottomNavigationView bottomNavigationView;

    SharedPrefmanager sharedPrefmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        binding= ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       binding.bottomNav.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());

        sharedPrefmanager=new SharedPrefmanager(getApplicationContext());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;

        switch (item.getItemId()){
            case R.id.dashboard:
                fragment=new DashboardFragment();
                break;
            case R.id.users:
                fragment=new DoctorsFragment();
                break;

            case R.id.hospitals:
                fragment=new HospitalFragment();
                break;

            case R.id.profile:
                fragment=new ProfileFragment();
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

        sharedPrefmanager.logout();
        Intent intent=new Intent(HomeActivity.this, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();



    }

    private void deleteaccount() {


        Call<DeleteAccountResponse> call= RetrofitClient.getInstance().getApi().
                deleteaccount(sharedPrefmanager.getuser().getId());



        call.enqueue(new Callback<DeleteAccountResponse>() {
            @Override
            public void onResponse(Call<DeleteAccountResponse> call, Response<DeleteAccountResponse> response) {

                DeleteAccountResponse deleteAccountResponse=response.body();
                if(response.isSuccessful()){

                    if(deleteAccountResponse.getError().equals("200")){


                        logoutuser();
                        Toast.makeText(HomeActivity.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(HomeActivity.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(HomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAccountResponse> call, Throwable t) {

                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}