package com.myandroid.pleasealertme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button alertBtn, cancelBtn, button;
    AlarmManager alarmManager;

    TextView textView;
    boolean state = true;

    private AdView adView;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //광고
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                // 광고가 문제 없이 로드시 출력됩니다.
                Log.d("@@@", "onAdLoaded");

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == true) {
                    button.setText("숨기기");
                    textView.setVisibility(View.VISIBLE);
                } else {
                    button.setText("주의사항");
                    textView.setVisibility(View.GONE);
                }
                state = !state;
            }
        });

        alertBtn = (Button)findViewById(R.id.alertBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        spinner = (Spinner)findViewById(R.id.spinner);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        alertBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(pos == 1) {
                    System.out.println("포지션 값 : " + pos);
                    setClock1();
                } else if(pos == 2) {
                    System.out.println("포지션 값 : " + pos);
                    setClock2();
                } else if(pos == 3) {
                    System.out.println("포지션 값 : " + pos);
                    setClock3();
                } else if(pos == 4) {
                    System.out.println("포지션 값 : " + pos);
                    setClock4();
                } else if(pos == 5) {
                    System.out.println("포지션 값 : " + pos);
                    setClock5();
                } else if(pos == 6) {
                    System.out.println("포지션 값 : " + pos);
                    setClock6();
                } else if(pos == 7) {
                    System.out.println("포지션 값 : " + pos);
                    setClock7();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "알람이 취소되었습니다.", Toast.LENGTH_SHORT).show();

                Intent cancelIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 10, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent2 = new Intent(getApplicationContext(), AlarmReceiver2.class);
                PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 20, cancelIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent3 = new Intent(getApplicationContext(), AlarmReceiver3.class);
                PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(), 30, cancelIntent3, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent4 = new Intent(getApplicationContext(), AlarmReceiver4.class);
                PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getApplicationContext(), 40, cancelIntent4, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent5 = new Intent(getApplicationContext(), AlarmReceiver5.class);
                PendingIntent pendingIntent5 = PendingIntent.getBroadcast(getApplicationContext(), 50, cancelIntent5, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent6 = new Intent(getApplicationContext(), AlarmReceiver6.class);
                PendingIntent pendingIntent6 = PendingIntent.getBroadcast(getApplicationContext(), 60, cancelIntent6, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent cancelIntent7 = new Intent(getApplicationContext(), AlarmReceiver7.class);
                PendingIntent pendingIntent7 = PendingIntent.getBroadcast(getApplicationContext(), 70, cancelIntent7, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.cancel(pendingIntent);
                alarmManager.cancel(pendingIntent2);
                alarmManager.cancel(pendingIntent3);
                alarmManager.cancel(pendingIntent4);
                alarmManager.cancel(pendingIntent5);
                alarmManager.cancel(pendingIntent6);
                alarmManager.cancel(pendingIntent7);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock1() {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock2() {
        Intent intent2 = new Intent(getApplicationContext(), AlarmReceiver2.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 20, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent2);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock3() {
        Intent intent3 = new Intent(getApplicationContext(), AlarmReceiver3.class);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(), 30, intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent3);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent3);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock4() {
        Intent intent4 = new Intent(getApplicationContext(), AlarmReceiver4.class);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getApplicationContext(), 40, intent4, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent4);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent4);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock5() {
        Intent intent5 = new Intent(getApplicationContext(), AlarmReceiver5.class);
        PendingIntent pendingIntent5 = PendingIntent.getBroadcast(getApplicationContext(), 50, intent5, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent5);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent5);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock6() {
        Intent intent6 = new Intent(getApplicationContext(), AlarmReceiver6.class);
        PendingIntent pendingIntent6 = PendingIntent.getBroadcast(getApplicationContext(), 60, intent6, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent6);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent6);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setClock7() {
        Intent intent7 = new Intent(getApplicationContext(), AlarmReceiver7.class);
        PendingIntent pendingIntent7 = PendingIntent.getBroadcast(getApplicationContext(), 70, intent7, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent7);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pendingIntent7);
        }
    }
}
