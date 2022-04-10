package com.example.aykankinali.proje;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AnaSayfa extends AppCompatActivity {
        Button zor;
        Button kolay;
        Button orta;
        String  a=" ";
        TextView tvbest;
        TextView tvbest2;
        TextView tvbest3;
        String kullanici_adlari[];
        String puanlar[];
        String sure[];
        int kullanici_idler[];
        String kullanici_adlari1[];
        String puanlar1[];
        String sure1[];
        int kullanici_idler1[];
        ArrayList<HashMap<String, String>> kolay_liste;
        String kullanici_adlari2[];
        String puanlar2[];
        String sure2[];
        int kullanici_idler2[];
        LinearLayout linearLayoutBG;
    ArrayList<HashMap<String, String>> skor_liste;
    ArrayList<HashMap<String, String>> orta_liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();
        zor=(Button)findViewById(R.id.zor);
        kolay=(Button)findViewById(R.id.kolay);
        orta=(Button)findViewById(R.id.orta);
        tvbest=(TextView)findViewById(R.id.tvbest);
        tvbest2=(TextView)findViewById(R.id.tvbest2);
        tvbest3=(TextView)findViewById(R.id.tvbest3);
        linearLayoutBG=findViewById(R.id.line1);
        Random random = new Random();
        int number = random.nextInt(5) + 1;
        if (number==1){
            linearLayoutBG.setBackgroundResource(R.drawable.b1);
        }
        if (number==2){
            linearLayoutBG.setBackgroundResource(R.drawable.b2);
        }
        if (number==3){
            linearLayoutBG.setBackgroundResource(R.drawable.b3);
        }
        if (number==4){
            linearLayoutBG.setBackgroundResource(R.drawable.b4);
        }
        if (number==5){
            linearLayoutBG.setBackgroundResource(R.drawable.b5);
        }
        DataBase db = new DataBase(getApplicationContext());
        skor_liste = db.skorlar();
        if(skor_liste.size()==0){
            tvbest.setText("EN YÜKSEK SKOR YOK");
        }
       else
            {
                String  gecici,gecici2;
                kullanici_adlari = new String[skor_liste.size()];
                puanlar=new String[skor_liste.size()];
                sure=new String[skor_liste.size()];
                kullanici_idler = new int[skor_liste.size()];
                for(int i=0;i<skor_liste.size();i++){
                    kullanici_adlari[i] = skor_liste.get(i).get("kullanici_adi");
                    kullanici_idler[i] = Integer.parseInt(skor_liste.get(i).get("id"));
                }
                for(int i=0;i<skor_liste.size();i++){
                    puanlar[i] = skor_liste.get(i).get("puan");
                    kullanici_idler[i] = Integer.parseInt(skor_liste.get(i).get("id"));
                }
                for(int i=0;i<skor_liste.size();i++){
                    sure[i] = skor_liste.get(i).get("dbsure");
                    kullanici_idler[i] = Integer.parseInt(skor_liste.get(i).get("id"));

                }

                for(int i=0;i<skor_liste.size();i++){
                    for(int j=0;j<skor_liste.size();j++) {
                        if (Integer.parseInt(puanlar[j]) < Integer.parseInt(puanlar[i])) {
                            gecici = puanlar[i];
                            puanlar[i] = puanlar[j];
                            puanlar[j] = gecici;
                        }
                        if (Integer.parseInt(puanlar[j]) == Integer.parseInt(puanlar[i]) && Integer.parseInt(sure[j]) > Integer.parseInt(sure[i])) {
                            gecici2 = sure[i];
                            sure[i] = sure[j];
                            sure[j] = gecici2;
                        }
                    }
                }
                tvbest.setText(puanlar[0]);

        }
        orta_Database db1 = new orta_Database(getApplicationContext());
        orta_liste = db1.orta_skorlar();
        if(orta_liste.size()==0){
            tvbest2.setText("EN YÜKSEK SKOR YOK");
        }
        else
            {
                String  gecici3,gecici4;
                kullanici_adlari1 = new String[orta_liste.size()];
                puanlar1=new String[orta_liste.size()];
                sure1=new String[orta_liste.size()];
                kullanici_idler1 = new int[orta_liste.size()];
                for(int i=0;i<orta_liste.size();i++){
                    kullanici_adlari1[i] = orta_liste.get(i).get("kullanici_adi");
                    kullanici_idler1[i] = Integer.parseInt(orta_liste.get(i).get("id"));
                }
                for(int i=0;i<orta_liste.size();i++){
                    puanlar1[i] = orta_liste.get(i).get("puan");
                    kullanici_idler1[i] = Integer.parseInt(orta_liste.get(i).get("id"));
                }
                for(int i=0;i<orta_liste.size();i++){
                    sure1[i] = orta_liste.get(i).get("dbsure");
                    kullanici_idler1[i] = Integer.parseInt(orta_liste.get(i).get("id"));

                }

                for(int i=0;i<orta_liste.size();i++){
                    for(int j=0;j<orta_liste.size();j++) {
                        if (Integer.parseInt(puanlar1[j]) < Integer.parseInt(puanlar1[i])) {
                            gecici3 = puanlar1[i];
                            puanlar1[i] = puanlar1[j];
                            puanlar1[j] = gecici3;
                        }
                        if (Integer.parseInt(puanlar1[j]) == Integer.parseInt(puanlar1[i]) && Integer.parseInt(sure1[j]) > Integer.parseInt(sure1[i])) {
                            gecici4 = sure1[i];
                            sure1[i] = sure1[j];
                            sure1[j] = gecici4;
                        }
                    }
                }
                tvbest2.setText(puanlar1[0]);
            }
        kolay_Database db2 = new kolay_Database(getApplicationContext());
        kolay_liste = db2.kolay_skorlar();
        if(kolay_liste.size()==0){
            tvbest3.setText("EN YÜKSEK SKOR YOK");
        }
        else{
            String  gecici4,gecici5;
            kullanici_adlari2 = new String[kolay_liste.size()];
            puanlar2=new String[kolay_liste.size()];
            sure2=new String[kolay_liste.size()];
            kullanici_idler2 = new int[kolay_liste.size()];
            for(int i=0;i<kolay_liste.size();i++){
                kullanici_adlari2[i] = kolay_liste.get(i).get("kullanici_adi");
                kullanici_idler2[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                puanlar2[i] = kolay_liste.get(i).get("puan");
                kullanici_idler2[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                sure2[i] = kolay_liste.get(i).get("dbsure");
                kullanici_idler2[i] = Integer.parseInt(kolay_liste.get(i).get("id"));
            }
            for(int i=0;i<kolay_liste.size();i++){
                for(int j=0;j<kolay_liste.size();j++){
                    if (Integer.parseInt(puanlar2[j]) < Integer.parseInt(puanlar2[i])){
                        gecici4 = puanlar2[i];
                        puanlar2[i] = puanlar2[j];
                        puanlar2[j] = gecici4;
                    }
                    if (Integer.parseInt(puanlar2[j]) == Integer.parseInt(puanlar2[i])&&Integer.parseInt(sure2[j]) > Integer.parseInt(sure2[i]))
                    {
                        gecici5 = sure2[i];
                        sure2[i] = sure2[j];
                        sure2[j] = gecici5;
                    }
                }
            }
            tvbest3.setText(puanlar2[0]);
        }
        zor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a="ZOR";
                Intent oy =new Intent(AnaSayfa.this,Oyun.class);
                oy.putExtra("ZORLUK",a);
                startActivity(oy);
                finish();
            }
        });
        orta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a="ORTA";
                Intent oy =new Intent(AnaSayfa.this,Oyun.class);
                oy.putExtra("ZORLUK",a);
                startActivity(oy);
                finish();
            }
        });
       kolay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               a="KOLAY";
               Intent oy =new Intent(AnaSayfa.this,Oyun.class);
               oy.putExtra("ZORLUK",a);
               startActivity(oy);
               finish();
           }
       });


    }


}
