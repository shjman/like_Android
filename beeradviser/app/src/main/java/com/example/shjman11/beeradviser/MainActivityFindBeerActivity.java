package com.example.shjman11.beeradviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivityFindBeerActivity extends AppCompatActivity {

    private Beer beer=new Beer();

    public void onClickButton(View view) {

        TextView hello = (TextView) findViewById(R.id.hello);
        Spinner spinner_color1 = (Spinner) findViewById(R.id.spinner_color1);
        String tmpSTR = String.valueOf(spinner_color1.getSelectedItem());
        List<String>brandList=beer.getBrands(tmpSTR);
        StringBuilder brandsFormatted=new StringBuilder();
        for(String name : brandList){
            brandsFormatted.append(name).append('\n');
        }

        hello.setText(brandsFormatted);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_find_beer);

    }
}
