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

public class PilihanGandaActivity extends AppCompatActivity {

    ImageButton pindah, keluar, kembali;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        setNis();

        pindah = findViewById(R.id.hewan);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, PilihanGandaHewanActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.kataBenda);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, PilihanGandaBendaActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.kataSifat);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, PilihanGandaSifatActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
                startActivity(intent);
            }
        });

        pindah = findViewById(R.id.kataKerja);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, PilihanGandaKerjaActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
                startActivity(intent);
                finish();
            }
        });

        pindah = findViewById(R.id.makananMinuman);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, PilihanGandaMakananMinumanActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
                startActivity(intent);
                finish();
            }
        });

        kembali = findViewById(R.id.buttonBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(PilihanGandaActivity.this, KuisActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "PilihanGanda");
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
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan);

        final Intent intent = getIntent();
        if ("Kuis".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(PilihanGandaActivity.this, KuisActivity.class);
        intent.putExtra("NIS", NIS);
        intent.putExtra("activity", "PilihanGanda");
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
