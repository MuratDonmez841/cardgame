package com.example.aykankinali.proje;
import android.content.Context;

public class kart extends android.support.v7.widget.AppCompatButton
{
    int OnYuz, ArkaYuz;
    boolean durum = false;

    public kart(Context context, int id) {
        super(context);
        setId(id);
        OnYuz = R.drawable.arka;
        switch(id%10)
        {
            case 1: ArkaYuz = R.drawable.a1; break;
            case 2: ArkaYuz = R.drawable.a2; break;
            case 3: ArkaYuz = R.drawable.a3; break;
            case 4: ArkaYuz = R.drawable.a4; break;
            case 5: ArkaYuz = R.drawable.a5; break;
            case 6: ArkaYuz = R.drawable.a6; break;
            case 7: ArkaYuz = R.drawable.a7; break;
            case 8: ArkaYuz = R.drawable.a8; break;
            case 9: ArkaYuz = R.drawable.a9; break;
            case 0: ArkaYuz = R.drawable.a0; break;
        }
        setBackgroundResource(OnYuz);
    }

    public void cevir()
    {
        /*if(durum == false)
        {
            durum = true;
            setBackgroundResource(ArkaYuz);
            setClickable(false);

        }
        else{
            durum = false;
            setBackgroundResource(OnYuz);
            setClickable(true);
        }*/
    }
}

