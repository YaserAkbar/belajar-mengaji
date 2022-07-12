package com.project.belajarmengaji;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PilihanGandaMakananMinumanActivity extends AppCompatActivity {

    ImageButton jw1, jw2, jw3;
    ImageView soal;
    int s, s1, j1, j2, j3;
    int skormakananminuman = 0;
    PilganMakananMinuman pilganMakananMinuman = new PilganMakananMinuman();
    int n = pilganMakananMinuman.getjumlahmakananminuman();
    int nis, NIS;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda_makanan_minuman);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soal = findViewById(R.id.soalmakananminuman);
        jw1 = findViewById(R.id.jawabanmakananminuman1);
        jw2 = findViewById(R.id.jawabanmakananminuman2);
        jw3 = findViewById(R.id.jawabanmakananminuman3);

        newlevelmakananminuman();
        setNis();

        jw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(j1 == s);
            }
        });

        jw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(j2 == s);
            }
        });

        jw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(j3 == s);
            }
        });
    }

    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan_mami);

        final Intent intent = getIntent();
        if ("PilihanGanda".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }else {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
    }

    public void pindah(){
        if (i >= 10) {
            Intent intent = new Intent(PilihanGandaMakananMinumanActivity.this,SubmitNilaiMakananMinumanActivity.class);
            intent.putExtra("skorAkhirMami", skormakananminuman);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "PilihanGandaMakananMinuman");
            startActivity(intent);
        }
    }

    public void newlevelmakananminuman() {
        s  = pilganMakananMinuman.getrandommakananminuman();
        s1 = pilganMakananMinuman.getrandommakananminuman();

        int i = new Random().nextInt(3) + 1;

        if (i == 1) {
            j1 = s;
        } else {
            j1 = pilganMakananMinuman.getrandommakananminuman();
        }

        if (i == 2) {
            j2 = s;
        } else {
            j2 = pilganMakananMinuman.getrandommakananminuman();
        }
        if (i == 3) {
            j3 = s;
        } else {
            j3 = pilganMakananMinuman.getrandommakananminuman();
        }
        soal.setBackgroundResource(pilganMakananMinuman.getimagesoalmakananminuman(s));
        jw1.setBackgroundResource(pilganMakananMinuman.getimagejawabanmakananminuman(j1));
        jw2.setBackgroundResource(pilganMakananMinuman.getimagejawabanmakananminuman(j2));
        jw3.setBackgroundResource(pilganMakananMinuman.getimagejawabanmakananminuman(j3));


    }
    public void isCorrect(boolean input) {
        TextView tampilSkor = findViewById(R.id.skorMakananMinuman);

        if (input && i < n) {
            MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
                benar.start();
                skormakananminuman += 10;
                newlevelmakananminuman();
                i++;
                pindah();
            } else {
                MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
                salah.start();
                newlevelmakananminuman();
                i++;
                pindah();
            }
            tampilSkor.setText("SKOR : " + skormakananminuman);
        }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
