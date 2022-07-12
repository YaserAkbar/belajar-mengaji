package com.project.belajarmengaji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SkorAkhirActivity extends AppCompatActivity {
    ImageButton ButtonMenuKuis, ButtonMenu, CobaLagi;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_akhir);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButtonMenu = findViewById(R.id.buttonHome);
        ButtonMenuKuis = findViewById(R.id.buttonKuis);
        CobaLagi = findViewById(R.id.ulangi);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        setSkor();
        setNis();

        ButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(SkorAkhirActivity.this, MainActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "SkorAkhir");
                startActivity(intent);
                finish();
            }
        });
        ButtonMenuKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(SkorAkhirActivity.this, KuisActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "SkorAkhir");
                startActivity(intent);
                finish();
            }
        });
    }

    public void setSkor() {
        TextView txtskortertinggi = findViewById(R.id.nilaiTertinggi);
        TextView txtskor = findViewById(R.id.HasilSkor);

        String activity = getIntent().getStringExtra("activity");

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        Intent intent = getIntent();
        switch (activity) {
            case "SubmitNilaiHewan": {
                int skorAkhir = intent.getIntExtra("skorAkhir", 0);
                txtskor.setText("Skor Anda : " + skorAkhir);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggihewan = mypref.getInt("skortertinggihewan", 0);
                if (skortertinggihewan >= skorAkhir)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggihewan);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhir);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggihewan", skorAkhir);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, PilihanGandaHewanActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiBenda": {
                int skorAkhirBenda = intent.getIntExtra("skorAkhirBenda", 0);
                txtskor.setText("Skor Anda : " + skorAkhirBenda);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggibenda = mypref.getInt("skortertinggibenda", 0);
                if (skortertinggibenda >= skorAkhirBenda)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggibenda);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirBenda);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggibenda", skorAkhirBenda);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, PilihanGandaBendaActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiSifat": {
                int skorAkhirSifat = intent.getIntExtra("skorAkhirSifat", 0);
                txtskor.setText("Skor Anda : " + skorAkhirSifat);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggisifat = mypref.getInt("skortertinggisifat", 0);
                if (skortertinggisifat >= skorAkhirSifat)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggisifat);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirSifat);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggisifat", skorAkhirSifat);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, PilihanGandaSifatActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiKerja": {
                int skorAkhirKerja = intent.getIntExtra("skorAkhirKerja", 0);
                txtskor.setText("Skor Anda : " + skorAkhirKerja);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggikerja = mypref.getInt("skortertinggikerja", 0);
                if (skortertinggikerja >= skorAkhirKerja)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggikerja);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirKerja);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggikerja", skorAkhirKerja);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, PilihanGandaSifatActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiMakananMinuman": {
                int skorAkhirMami = intent.getIntExtra("skorAkhirMami", 0);
                txtskor.setText("Skor Anda : " + skorAkhirMami);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggimami = mypref.getInt("skortertinggimami", 0);
                if (skortertinggimami >= skorAkhirMami)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggimami);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirMami);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggimami", skorAkhirMami);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, PilihanGandaMakananMinumanActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiEssayHewan": {
                int skorAkhirEssayHewan = intent.getIntExtra("skorAkhirEssayHewan", 0);
                txtskor.setText("Skor Anda : " + skorAkhirEssayHewan);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggiessayhewan = mypref.getInt("skortertinggiessayhewan", 0);
                if (skortertinggiessayhewan >= skorAkhirEssayHewan)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggiessayhewan);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirEssayHewan);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggiessayhewan", skorAkhirEssayHewan);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, EssayHewanActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiEssayBenda": {
                int skorAkhirEssayBenda = intent.getIntExtra("skorAkhirEssayBenda", 0);
                txtskor.setText("Skor Anda : " + skorAkhirEssayBenda);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggiessayBenda = mypref.getInt("skortertinggiessayBenda", 0);
                if (skortertinggiessayBenda >= skorAkhirEssayBenda)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggiessayBenda);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirEssayBenda);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggiessayBenda", skorAkhirEssayBenda);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, EssayBendaActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
            case "SubmitNilaiEssaySifat": {
                int skorAkhirEssaySifat = intent.getIntExtra("skorAkhirEssaySifat", 0);
                txtskor.setText("Skor Anda : " + skorAkhirEssaySifat);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggiessaySifat = mypref.getInt("skortertinggiessaySifat", 0);
                if (skortertinggiessaySifat >= skorAkhirEssaySifat)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggiessaySifat);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda :" + skorAkhirEssaySifat);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggiessaySifat", skorAkhirEssaySifat);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, EssaySifatActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }case "SubmitNilaiEssayKerja": {
                int skorAkhirEssayKerja = intent.getIntExtra("skorAkhirEssayKerja", 0);
                txtskor.setText("Skor Anda : " + skorAkhirEssayKerja);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggiessayKerja = mypref.getInt("skortertinggiessayKerja", 0);
                if (skortertinggiessayKerja >= skorAkhirEssayKerja)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggiessayKerja);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda :" + skorAkhirEssayKerja);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggiessayKerja", skorAkhirEssayKerja);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, EssayKerjaActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }default: {
                int skorAkhirEssayMami = intent.getIntExtra("skorAkhirEssayMami", 0);
                txtskor.setText("Skor Anda : " + skorAkhirEssayMami);

                SharedPreferences mypref = getPreferences(MODE_PRIVATE);
                int skortertinggiessayMami = mypref.getInt("skortertinggiessayMami", 0);
                if (skortertinggiessayMami >= skorAkhirEssayMami)
                    txtskortertinggi.setText("Skor Tertinggi Anda : " + skortertinggiessayMami);
                else {
                    txtskortertinggi.setText("BARU Skor Tertinggi Anda : " + skorAkhirEssayMami);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putInt("skortertinggiessayMami", skorAkhirEssayMami);
                    editor.commit();
                }
                CobaLagi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suaraButton.start();
                        Intent intent = new Intent(SkorAkhirActivity.this, EssayMakananMinumanActivity.class);
                        intent.putExtra("NIS", NIS);
                        intent.putExtra("activity", "SkorAkhir");
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            }
        }
    }

    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_skor_akhir);

        final Intent intent = getIntent();
        if ("SubmitNilaiHewan".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("SubmitNilaiBenda".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("SubmitNilaiKerja".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else if ("SubmitNilaiSifat".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else{
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
    }

    public void onBackPressed(){
        Toast.makeText(this, "Tidak bisa kembali, silahkan tekan menu", Toast.LENGTH_SHORT).show();
    }
}