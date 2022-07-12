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

public class PilihanGandaHewanActivity extends AppCompatActivity {

    ImageButton jw1, jw2, jw3;
    ImageView soal;
    int s, s1, j1, j2, j3;
    int skor = 0;
    PilganHewan pilganHewan = new PilganHewan();
    int n = pilganHewan.getjumlah();
    int nis, NIS;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda_hewan);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soal = findViewById(R.id.soal);
        jw1 = findViewById(R.id.jawaban1);
        jw2 = findViewById(R.id.jawaban2);
        jw3 = findViewById(R.id.jawaban3);

        newlevel();
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
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan_hewan);

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
            Intent intent = new Intent(PilihanGandaHewanActivity.this,SubmitNilaiHewanActivity.class);
            intent.putExtra("skorAkhir", skor);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "PilihanGandaHewan");
            startActivity(intent);
        }
    }

    public void newlevel() {
        s  = pilganHewan.getrandomhewan();
        s1 = pilganHewan.getrandomhewan();

        int i = new Random().nextInt(3) + 1;

        if (i == 1) {
            j1 = s;
        } else {
            j1 = pilganHewan.getrandomhewan();
        }

        if (i == 2) {
            j2 = s;
        } else {
            j2 = pilganHewan.getrandomhewan();
        }
        if (i == 3) {
            j3 = s;
        } else {
            j3 = pilganHewan.getrandomhewan();
        }
        soal.setBackgroundResource(pilganHewan.getimagesoal(s));
        jw1.setBackgroundResource(pilganHewan.getimagejawaban(j1));
        jw2.setBackgroundResource(pilganHewan.getimagejawaban(j2));
        jw3.setBackgroundResource(pilganHewan.getimagejawaban(j3));


    }
    public void isCorrect(boolean input) {
        TextView tampilSkor = findViewById(R.id.skor);

        if(input && i < n) {
            MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
            skor += 10;
            benar.start();
            newlevel();
            i++;
            pindah();
        }else{
            MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
            salah.start();
            newlevel();
            i++;
            pindah();
        }
        tampilSkor.setText("SKOR : " +skor);
    }

    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
