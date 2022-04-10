package com.example.aykankinali.proje;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class bitenzaman extends AppCompatActivity {
Button btdön;
Button btbolum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitenzaman);
        btdön=(Button)findViewById(R.id.btdön);
        btbolum=(Button)findViewById(R.id.btbolum);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();
        Intent y = getIntent();
        final String z = y.getStringExtra("ZORLUK");
        btdön.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(bitenzaman.this,AnaSayfa.class);
                startActivity(a);
                finish();
            }
        });
        btbolum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(bitenzaman.this,Oyun.class);
                b.putExtra("ZORLUK",z);
                startActivity(b);
                finish();

            }
        });
    }
}
