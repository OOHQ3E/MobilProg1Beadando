package com.gabor_bagoly.oohq3e_bgabor;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_PERMISSION_CODE = 100;
    TextView lbl_usernameText;
    Button btn_File;
    Button btn_SharePref;
    Button btn_call;
    Button btn_cameraPermission;
    Button btn_openOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lbl_usernameText = findViewById(R.id.lbl_username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        lbl_usernameText.setText("Hello, " + username + "!");
        btn_File = findViewById(R.id.btn_ShowDateFromFile);
        btn_File.setOnClickListener(this);
        btn_SharePref = findViewById(R.id.btn_showSharePrefData);
        btn_SharePref.setOnClickListener(this);
        btn_call = findViewById(R.id.btn_impicitIntent);
        btn_call.setOnClickListener(this);
        btn_cameraPermission = findViewById(R.id.btn_camera);
        btn_cameraPermission.setOnClickListener(this);
        btn_openOther = findViewById(R.id.btn_openActivity);
        btn_openOther.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view.equals(btn_File)){
            ReadData();
        }
        if (view.equals(btn_SharePref)){
            ReadDataSharePref();
        }
        if (view.equals(btn_call)){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:911"));
            startActivity(intent);
        }
        if (view.equals(btn_cameraPermission)){
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        }
        if (view.equals(btn_openOther)){
            Intent intent = new Intent(MainActivity.this, GetResultActivity.class);
            intent.putExtra("greetings","hello from");
            startActivityForResult(intent,100);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String result = data.getStringExtra("greeting");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
    public void ReadData(){
        try {
            Resources resources = getResources();
            FileInputStream fileInputStream = openFileInput("loginData.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            String data = buffer.readLine();
            Toast.makeText(this, data,Toast.LENGTH_SHORT).show();
            buffer.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadDataSharePref(){
        SharedPreferences sharedPreferences = getSharedPreferences("loginDate", Context.MODE_PRIVATE);
        String data = sharedPreferences.getString("user","");
        Toast.makeText(this, data,Toast.LENGTH_SHORT).show();
        }


}

