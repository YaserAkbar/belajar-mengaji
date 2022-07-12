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

public class KuisActivity extends AppCompatActivity {
    ImageButton pindah, kembali, keluar;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        setNis();

        pindah = findViewById(R.id.pilgan);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(KuisActivity.this, PilihanGandaActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Kuis");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.essai);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(KuisActivity.this, EssayActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Kuis");
                startActivity(intent);
            }
        });

        kembali = findViewById(R.id.buttonBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(KuisActivity.this, MainActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Kuis");
                startActivity(intent);
                finish();
            }
        });

        keluar = findViewById(R.id.buttonExit);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Kaluar();
            }
        });
    }

    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_kuis);

        final Intent intent = getIntent();
        if ("Main".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("PilihanGanda".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("Kuis".equals(activity)){
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else{
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(KuisActivity.this, MainActivity.class);
        intent.putExtra("NIS", NIS);
        intent.putExtra("activity", "Kuis");
        startActivity(intent);
        finish();
    }

    public void Kaluar() {
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
