//Referred to https://www.tutorialspoint.com/android/android_shared_preferences.htm

package com.example.quickcashgroup5.UserManagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.UserManagement.LogInActivity;

public class SessionManagement {
    private static final String preferencesName = "Session";
    private SharedPreferences sharedPreferences;
    private Context context;
    SharedPreferences.Editor editor;

    public SessionManagement(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(preferencesName, this.context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setName(String name){
        editor.putString("Name", name);
        editor.apply();
    }

    public void setEmail(String email){
        editor.putString("Email", email);
        editor.apply();
    }

    public void setRole(String role){
        editor.putString("Role", role);
        editor.apply();
    }

    public String getName(){
        return sharedPreferences.getString("Name", null);
    }

    public String getEmail(){
        return sharedPreferences.getString("Email", null);
    }

    public String getRole(){
        return sharedPreferences.getString("Role", null);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean("LoggedIn", false);
    }

    public void createSession(String name, String email, String role){
        editor.putBoolean("LoggedIn", true);
        editor.putString("Name", name);
        editor.putString("Email", email);
        editor.putString("Role", role);
        editor.apply();
    }

    public void accessControl(){
        if (isLoggedIn()){
            System.out.println("good");
            if(getRole().equals("Employee")) {
                System.out.println("Employee");
                Intent i = new Intent(context, EmployeeHomeActivity.class);
                context.startActivity(i);
            } else {
                System.out.println("Employer");
                Intent i = new Intent(context, EmployerHomeActivity.class);
                context.startActivity(i);
            }
        } else {
            Intent i = new Intent(context, LogInActivity.class);
            context.startActivity(i);
        }
    }

    public void logout(){
        this.editor.clear();
        this.editor.apply();
        Intent intent = new Intent(context, LogInActivity.class);
        context.startActivity(intent);
    }
}