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

public class BelajarActivity extends AppCompatActivity {

    ImageButton pindah, keluar, kembali;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setNis();

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        pindah = findViewById(R.id.belajar_hewan);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, BelajarHewanActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.belajar_kata_benda);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, BelajarKataBendaActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.belajar_kata_kerja);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, BelajarKataKerjaActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.belajar_kata_sifat);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, BelajarKataSifatActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
                startActivity(intent);
                finish();
            }
        });

        pindah = findViewById(R.id.belajar_makanan_minuman);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, BelajarMakananMinumanActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
                startActivity(intent);
                finish();
            }
        });

        kembali = findViewById(R.id.buttonBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarActivity.this, MainActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "Belajar");
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
        tampilNis = findViewById(R.id.nis_menu_belajar);

        final Intent intent = getIntent();
        if ("Main".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("BelajarHewan".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("BelajarKataBenda".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("BelajarKataKerja".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("BelajarKataSifat".equals(activity)) {
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
        Intent intent = new Intent(BelajarActivity.this, MainActivity.class);
        intent.putExtra("NIS", NIS);
        intent.putExtra("activity", "Belajar");
        startActivity(intent);
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
