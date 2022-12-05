package com.gabor_bagoly.oohq3e_bgabor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login;
    TextView txt_username;
    TextView txt_password;
    String uname = "user";
    String pwd = "user";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_login);
        txt_username = findViewById(R.id.txt_UserName);
        txt_password = findViewById(R.id.txt_passwd);

        btn_login.setOnClickListener(this);
    }
    public void saveLogin(String name, String date){
        try {
            String dataToSave = name+" "+date;
            FileOutputStream fileOutputStream =
                    openFileOutput("loginData.txt",MODE_PRIVATE);
            fileOutputStream.write(dataToSave.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLoginSharePref(String name, String date){
        SharedPreferences sharedPreferences = getSharedPreferences("loginDate",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name,date);
        editor.commit();
    }



    @Override
    public void onClick(View view) {

        if (txt_username.getText().toString().equals("") || txt_password.getText().toString().equals("") ){
                Toast.makeText(this, "Can't leave input fields empty!",Toast.LENGTH_SHORT).show();
        }
        if (!txt_username.getText().toString().equals("user") || !txt_password.getText().toString().equals("user") ){
            Toast.makeText(this, "Username or password is incorrect!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Succesful login, "+txt_username.getText().toString(),Toast.LENGTH_SHORT).show();
            intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username",txt_username.getText().toString());
            startActivity(intent);
            File dir = getFilesDir();
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault());
            String formattedDate = df.format(c);
            saveLogin(txt_username.getText().toString(),formattedDate);
            saveLoginSharePref(txt_username.getText().toString(),formattedDate);
        }

    }

}