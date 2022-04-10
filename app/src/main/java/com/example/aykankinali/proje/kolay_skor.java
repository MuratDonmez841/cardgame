package com.example.aykankinali.proje;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class kolay_skor extends AppCompatActivity {
    TextView tvskor;
    EditText etisim;
    Button btEkle;
    ListView LV;
    Button btdon;
    Button btsil;
    TextView tvsure;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kolay_liste;
    String kullanici_adlari[];
    String puanlar[];
    String sure[];
    int kullanici_idler[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();
        Intent z = getIntent();
        LV = (ListView) findViewById(R.id.LV);
        etisim = (EditText) findViewById(R.id.etIsim);
        tvskor = (TextView) findViewById(R.id.tvskor);
        btEkle = (Button) findViewById(R.id.btEkle);
        btsil=(Button)findViewById(R.id.btsil);
        btdon=(Button)findViewById(R.id.btdon);
        tvsure=(TextView)findViewById(R.id.tvsure);
        int a = z.getIntExtra("SKOR", 0);
        tvskor.setText(String.valueOf(a));
        int b=z.getIntExtra("SURE",0);
        tvsure.setText(String.valueOf(b));
        btEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adi, puani,sures;
                adi = etisim.getText().toString();
                puani = tvskor.getText().toString();
                sures=tvsure.getText().toString();
                if (adi.matches("") || puani.matches("")||sures.matches("")) {
                    Toast.makeText(getApplicationContext(), "İSİM KISMI BOŞ KALAMAZ", Toast.LENGTH_LONG).show();
                }
                else {
                    kolay_Database db = new kolay_Database(getApplicationContext());
                    db.kolay_skorEkle(adi,puani,sures);
                    db.close();
                    Toast.makeText(getApplicationContext(), "YENİ SKORUNUZ EKLENDİ", Toast.LENGTH_LONG).show();
                    tvskor.setText("");
                    etisim.setText("");
                    tvsure.setText("");
                    onResume();

                }


            }
        });
        btsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(kolay_skor.this);
                alertDialog.setTitle("UYARI");
                alertDialog.setMessage("BÜTÜN SKORLAR SİLİNSİN Mİ?");
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        kolay_Database db = new kolay_Database(getApplicationContext());
                        db.kolay_tabloTemizle();
                        onResume();
                        Toast.makeText(getApplicationContext(), "BÜTÜN SKORLAR SLİNDİ", Toast.LENGTH_LONG).show();

                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                    }
                });
                alertDialog.show();

            }
        });
        btdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(kolay_skor.this,AnaSayfa.class);
                startActivity(o);
            }
        });

    }

    public void onResume()
    {
        super.onResume();
        kolay_Database db = new kolay_Database(getApplicationContext());
        kolay_liste = db.kolay_skorlar();
        String  gecici,gecici2,gecici3;

            kullanici_adlari = new String[kolay_liste.size()];
            puanlar=new String[kolay_liste.size()];
            sure=new String[kolay_liste.size()];
            kullanici_idler = new int[kolay_liste.size()];
        String[] puanlar2 = new String[10];
        String[] kullanici_adlari2 = new String[10];
        String[] sure2 = new String[10];
        String [] lz=new String[10];
        String [] lz2=new String[kolay_liste.size()];
            for(int i=0;i<kolay_liste.size();i++){
                kullanici_adlari[i] = kolay_liste.get(i).get("kullanici_adi");
                kullanici_idler[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                puanlar[i] = kolay_liste.get(i).get("puan");
                kullanici_idler[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                sure[i] = kolay_liste.get(i).get("dbsure");
                kullanici_idler[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                for(int j=0;j<kolay_liste.size();j++){
                    if (Integer.parseInt(puanlar[j]) < Integer.parseInt(puanlar[i])){
                        gecici = puanlar[i];
                        puanlar[i] = puanlar[j];
                        puanlar[j] = gecici;

                        gecici3 = kullanici_adlari[i];
                        kullanici_adlari[i]=kullanici_adlari[j];
                        kullanici_adlari[j] = gecici3;

                        gecici2 = sure[i];
                        sure[i] = sure[j];
                        sure[j] = gecici2;
                    }
                    if (Integer.parseInt(puanlar[j]) == Integer.parseInt(puanlar[i])&&Integer.parseInt(sure[j]) < Integer.parseInt(sure[i]))
                    {
                        gecici = puanlar[i];
                        puanlar[i] = puanlar[j];
                        puanlar[j] = gecici;

                        gecici3 = kullanici_adlari[i];
                        kullanici_adlari[i]=kullanici_adlari[j];
                        kullanici_adlari[j] = gecici3;

                        gecici2 = sure[i];
                        sure[i] = sure[j];
                        sure[j] = gecici2;
                    }
                }
            }

        if(kolay_liste.size()>=10) {
            for (int i = 0; i < 10; i++) {
                gecici = puanlar[i];
                puanlar2[i] = gecici;

                gecici2 = kullanici_adlari[i];
                kullanici_adlari2[i] = gecici2;

                gecici3 = sure[i];
                sure2[i] = gecici3;

                lz[i] = i + 1 + "-" + kullanici_adlari2[i] + "                        " + puanlar2[i] + "                        " + sure2[i];
            }
            adapter = new ArrayAdapter<String>(this, R.layout.kolay_skor, R.id.kolay_sko, lz);
        }
        else {
            for (int i = 0; i < kolay_liste.size(); i++) {
                gecici = puanlar[i];
                puanlar2[i] = gecici;

                gecici2 = kullanici_adlari[i];
                kullanici_adlari2[i] = gecici2;

                gecici3 = sure[i];
                sure2[i] = gecici3;

                lz2[i] = i + 1 + "-" + kullanici_adlari2[i] + "                        " + puanlar2[i] + "                        " + sure2[i];
            }
            adapter = new ArrayAdapter<String>(this, R.layout.kolay_skor, R.id.kolay_sko, lz2);

        }
        LV.setAdapter(adapter);


    }

}

