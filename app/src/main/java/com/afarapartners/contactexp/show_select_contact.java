package com.afarapartners.contactexp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class show_select_contact extends AppCompatActivity {

    TextView displayContactname,displayphoneno;


    private String cname,cphoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_select_contact);
        displayContactname = findViewById(R.id.displayname);
        displayphoneno = findViewById(R.id.displayphoneno);

       cname = getIntent().getStringExtra("contactname");
       cphoneno = getIntent().getStringExtra("displayphoneno");


       displayContactname.setText(cname);
       displayphoneno.setText(cphoneno);

        Log.e("showselect",cname+" "+cphoneno);



    }
}
