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

public class PilihanGandaKerjaActivity extends AppCompatActivity {

    ImageButton jw1, jw2, jw3;
    ImageView soal;
    int s, s1, j1, j2, j3;
    int skorkerja = 0;
    PilganKerja pilganKerja = new PilganKerja();
    int n = pilganKerja.getjumlahkerja();
    int nis, NIS;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda_kerja);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soal = findViewById(R.id.soalkerja);
        jw1 = findViewById(R.id.jawabankerja1);
        jw2 = findViewById(R.id.jawabankerja2);
        jw3 = findViewById(R.id.jawabankerja3);

        newlevelkerja();
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
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan_kerja);

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
            Intent intent = new Intent(PilihanGandaKerjaActivity.this,SubmitNilaiKerjaActivity.class);
            intent.putExtra("skorAkhirKerja", skorkerja);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "PilihanGandaKerja");
            startActivity(intent);
        }
    }

    public void newlevelkerja() {
        s  = pilganKerja.getrandomkerja();
        s1 = pilganKerja.getrandomkerja();

        int i = new Random().nextInt(3) + 1;

        if (i == 1) {
            j1 = s;
        } else {
            j1 = pilganKerja.getrandomkerja();
        }

        if (i == 2) {
            j2 = s;
        } else {
            j2 = pilganKerja.getrandomkerja();
        }
        if (i == 3) {
            j3 = s;
        } else {
            j3 = pilganKerja.getrandomkerja();
        }
        soal.setBackgroundResource(pilganKerja.getimagesoalkerja(s));
        jw1.setBackgroundResource(pilganKerja.getimagejawabankerja(j1));
        jw2.setBackgroundResource(pilganKerja.getimagejawabankerja(j2));
        jw3.setBackgroundResource(pilganKerja.getimagejawabankerja(j3));


    }
    public void isCorrect(boolean input) {
        TextView tampilSkor = findViewById(R.id.skorKerja);

        if(input && i < n) {
            MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
            skorkerja += 10;
            benar.start();
            newlevelkerja();
            i++;
            pindah();
        }else{
            MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
            salah.start();
            newlevelkerja();
            i++;
            pindah();
        }
        tampilSkor.setText("SKOR : " +skorkerja);
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
