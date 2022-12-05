package com.gabor_bagoly.oohq3e_bgabor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class GetResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);
        Intent intent = getIntent();
        String result = intent.getStringExtra("greetings");
        result = result + " the other side";
        intent.putExtra("greeting",result);
        setResult(RESULT_OK,intent);
        finish();

    }
}