package com.gabor_bagoly.oohq3e_bgabor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        String[] data = getResources().getStringArray(R.array.myArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.simple_list_item, // egyetlen cella
                data);//adatforrás

        OwnAdapter ownAdapter = new OwnAdapter(data,this);
        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(ownAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, data[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}