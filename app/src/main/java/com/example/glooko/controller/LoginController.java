package com.example.glooko.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.glooko.model.User;

public class LoginController {
    private static User user;
    private static LoginController instance = null;

    private static  final  String SHERED_PRES="myFile";
    private LoginController() {
        super();
    }

    public static final LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

public void  createUser(String username,String password){
        user=new User(username,password);

}



    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void createUser (String username, String password, Context context)
    {
        user =new User(username,password);
        SharedPreferences sharedPreferences= context.getSharedPreferences(SHERED_PRES,Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("UserName",username);
        editor.putString("Password",password);
        editor.apply();

    }





}
