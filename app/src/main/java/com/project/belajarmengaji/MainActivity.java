package com.project.belajarmengaji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton pindah, keluar;
    MediaPlayer mp;
    int NIS, nis = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        mp = MediaPlayer.create(getBaseContext(),R.raw.backsound);
        mp.start();

        setNis();


        pindah = findViewById(R.id.buttonBelajar);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(MainActivity.this,BelajarActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Main");
                startActivity(intent);
                mp.stop();
                finish();
            }
        });

        pindah = findViewById(R.id.buttonKuis);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(MainActivity.this,KuisActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Main");
                startActivity(intent);
                mp.stop();
                finish();
            }
        });

        keluar = findViewById(R.id.buttonExit);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                onBackPressed();
            }
        });
    }
    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.tampil_nis_siswa);

        final Intent intent = getIntent();
        if ("LoginSiswa".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
    }else if ("Belajar".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : " + NIS);
            nis = NIS;
    }else if ("Kuis".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : " + NIS);
            nis = NIS;
        }else {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : " + NIS);
            nis = NIS;
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah Kamu Ingin Keluar?");
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                System.exit(0);
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
