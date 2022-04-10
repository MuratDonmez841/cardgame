package com.example.aykankinali.proje;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Oyun extends AppCompatActivity {
    int SonKart = -1;
    int Eslesme = 0;
    public static int skor = 0;
    TextView skor1;
    TextView tvhamle;
    Handler handle = null;
    Runnable runnable = null;
    int zaman;
    int hamlesayisi = 0;
    private boolean durdur;
    private boolean s = true;
    int dur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();
        Intent y = getIntent();
        final String a = y.getStringExtra("ZORLUK");
        final GridLayout glOyun = (GridLayout) findViewById(R.id.glOyun);
        final TextView time = (TextView) findViewById(R.id.time);
        final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);

        handle = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (zaman > 0 && Eslesme != 10)
                    durdur = true;
                else
                    durdur = false;
                time.setText(String.valueOf(zaman));
                if (zaman != 0 && durdur == true) {
                    zaman--;
                } else {
                    handle.removeCallbacks(runnable);
                    if (s == true && zaman == 0) {
                        s = false;
                        Intent d = new Intent(Oyun.this, bitenzaman.class);
                        d.putExtra("ZORLUK", a);
                        startActivity(d);
                        finish();
                    }
                }
                handle.postDelayed(runnable, 1000);

            }
        };

        if (a.equals("ZOR")) {
            kart x[] = new kart[20];
            int i;
            skor = 0;
            zaman = 30;
            runnable.run();
            for (i = 0; i < 20; i++) {
                x[i] = new kart(this, i);
                x[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final kart k = (kart) view;

                        if (dur == 1) {
                            k.cevir();
                            if (SonKart > -1) {

                                dur = 0;
                                final kart k1 = (kart) findViewById(SonKart);
                                if (k1.ArkaYuz == k.ArkaYuz) {
                                    dur = 1;
                                    k.setClickable(false);
                                    k1.setClickable(false);
                                    Eslesme++;
                                    hamlesayisi++;
                                    skor = (skor + 100);
                                    final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                    skor1.setText(String.valueOf(skor));
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));
                                    if (Eslesme == 10) {

                                        if (zaman > 15 && hamlesayisi < 20) {
                                            skor = skor * 3;
                                        } else if (zaman > 10 && hamlesayisi < 30) {
                                            skor = skor * 2;
                                        }
                                        durdur = false;
                                        Intent d = new Intent(Oyun.this, skor.class);
                                        d.putExtra("SURE", zaman);
                                        d.putExtra("SKOR", skor);
                                        skor = 0;
                                        hamlesayisi = 0;
                                        startActivity(d);
                                        finish();

                                    }
                                } else {
                                    if (skor > 0) {
                                        skor = skor - 25;
                                        final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                        skor1.setText(String.valueOf(skor));
                                    }
                                    hamlesayisi++;
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));


                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            k.cevir();
                                            k1.cevir();
                                            dur = 1;
                                        }
                                    }, 500);
                                }

                                SonKart = -1;
                            } else {
                                SonKart = k.getId();
                            }
                        }
                    }
                });
            }
            for (i = 0; i < 20; i++) {
                int temp = (int) (Math.random() * 20);
                kart k = x[temp];
                x[temp] = x[i];
                x[i] = k;
            }
            for (i = 0; i < 20; i++) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 150);
                glOyun.addView(x[i], lp);
            }
        }
        else if (a.equals("ORTA")) {
            kart x[] = new kart[20];
            int i;
            zaman = 60;
            skor = 0;
            runnable.run();
            for (i = 0; i < 20; i++) {
                x[i] = new kart(this, i);
                x[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final kart k = (kart) view;

                        if (dur == 1) {
                            k.cevir();
                            if (SonKart > -1) {
                                dur = 0;
                                final kart k1 = (kart) findViewById(SonKart);
                                if (k1.ArkaYuz == k.ArkaYuz) {
                                    dur = 1;
                                    k.setClickable(false);
                                    k1.setClickable(false);
                                    Eslesme++;
                                    hamlesayisi++;
                                    skor = (skor + 200);
                                    final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                    skor1.setText(String.valueOf(skor));
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));
                                    if (Eslesme == 10) {
                                        if (zaman > 40 && hamlesayisi < 30)
                                            skor = skor * 3;
                                        else if (zaman > 20 && hamlesayisi < 50)
                                            skor = skor * 2;
                                        Intent d = new Intent(Oyun.this, orta_skor.class);
                                        d.putExtra("SKOR", skor);
                                        d.putExtra("SURE", zaman);
                                        skor = 0;
                                        hamlesayisi = 0;
                                        startActivity(d);
                                        finish();
                                    }
                                } else {
                                    if (skor > 0) {
                                        skor = skor - 25;
                                        final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                        skor1.setText(String.valueOf(skor));
                                    }
                                    hamlesayisi++;
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));
                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            k.cevir();
                                            k1.cevir();
                                            dur = 1;
                                        }
                                    }, 1000);

                                }
                                SonKart = -1;
                            } else {
                                SonKart = k.getId();
                            }
                        }
                    }
                });
            }
            for (i = 0; i < 20; i++) {
                int temp = (int) (Math.random() * 20);
                kart k = x[temp];
                x[temp] = x[i];
                x[i] = k;
            }
            for (i = 0; i < 20; i++) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 150);
                glOyun.addView(x[i], lp);
            }
        }
        else if (a.equals("KOLAY")) {
            kart x[] = new kart[20];
            int i;
            zaman = 90;
            skor = 0;
            runnable.run();
            for (i = 0; i < 20; i++) {
                x[i] = new kart(this, i);
                x[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final kart k = (kart) view;
                        if (dur == 1) {
                            k.cevir();
                            if (SonKart > -1) {
                                dur = 0;
                                final kart k1 = (kart) findViewById(SonKart);
                                if (k1.ArkaYuz == k.ArkaYuz) {
                                    dur = 1;
                                    k.setClickable(false);
                                    k1.setClickable(false);
                                    Eslesme++;
                                    hamlesayisi++;
                                    skor = (skor + 300);
                                    final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                    skor1.setText(String.valueOf(skor));
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));
                                    if (Eslesme == 10) {
                                        if (zaman > 50 && hamlesayisi < 40)
                                            skor = skor * 3;
                                        else if (zaman > 25 && hamlesayisi < 60)
                                            skor = skor * 2;
                                        Intent d = new Intent(Oyun.this, kolay_skor.class);
                                        d.putExtra("SKOR", skor);
                                        d.putExtra("SURE", zaman);
                                        skor = 0;
                                        hamlesayisi = 0;
                                        startActivity(d);
                                        finish();

                                    }
                                } else {
                                    if (skor > 0) {
                                        skor = skor - 10;
                                        final TextView skor1 = (TextView) findViewById(R.id.skor1);
                                        skor1.setText(String.valueOf(skor));
                                    }
                                    hamlesayisi++;
                                    final TextView tvhamle = (TextView) findViewById(R.id.tvhamle);
                                    tvhamle.setText(String.valueOf(hamlesayisi));
                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            k.cevir();
                                            k1.cevir();
                                            dur = 1;

                                        }
                                    }, 1000);

                                }
                                SonKart = -1;
                            } else {
                                SonKart = k.getId();
                            }
                        }
                    }
                });
            }
            for (i = 0; i < 20; i++) {
                int temp = (int) (Math.random() * 20);
                kart k = x[temp];
                x[temp] = x[i];
                x[i] = k;
            }
            for (i = 0; i < 20; i++) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 150);
                glOyun.addView(x[i], lp);
            }
        }
    }

}
