package com.aislyn.appointment.doctor;

import android.content.Context;
import android.content.SharedPreferences;

import com.aislyn.appointment.doctor.ModelResponse.Doctor;


public class SharedPrefmanager {



    String SHARED_PREF_NAME="drharharmahadev";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;


    public SharedPrefmanager(Context context) {
        this.context= context;
    }


    public void saveuser(Doctor doctor){

        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("id",doctor.getId());
        editor.putString("drname",doctor.getDrname());
        editor.putString("qualification",doctor.getQualification());
        editor.putString("specialist",doctor.getSpecialist());
        editor.putString("IMR_NO",doctor.getIMR_NO());
        editor.putString("email",doctor.getEmail());
        editor.putInt("fee",doctor.getFee());
        editor.putBoolean("logged",true);
        editor.apply();
    }


    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }


    public Doctor getuser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new Doctor(sharedPreferences.getInt("id",-1),sharedPreferences.getString("drname",null),
                sharedPreferences.getString("qualification",null),
                sharedPreferences.getString("specialist",null),
                sharedPreferences.getString("IMR_NO",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getInt("fee",-1));
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
