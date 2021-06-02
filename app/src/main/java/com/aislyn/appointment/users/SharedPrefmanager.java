package com.aislyn.appointment.users;

import android.content.Context;
import android.content.SharedPreferences;

import com.aislyn.appointment.users.ModelResponse.User;

public class SharedPrefmanager {



    String SHARED_PREF_NAME="harharmahadev";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;


    public SharedPrefmanager(Context context) {
       this.context= context;
    }


    public void saveuser(User user){

        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("username",user.getUsername());
        editor.putString("email",user.getEmail());
        editor.putString("phone",user.getPhone());
        editor.putBoolean("logged",true);
        editor.apply();
    }


    public boolean isLoggedIn(){
       sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("logged",false);

    }


   public User getuser(){
       sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return new User(sharedPreferences.getInt("id",-1),sharedPreferences.getString("username",null),
               sharedPreferences.getString("email",null),
               sharedPreferences.getString("phone",null));
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
