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

public class PilihanGandaSifatActivity extends AppCompatActivity {

    ImageButton jw1, jw2, jw3;
    ImageView soal;
    int s, s1, j1, j2, j3;
    int skorsifat = 0;
    PilganSifat pilganSifat = new PilganSifat();
    int n = pilganSifat.getjumlahsifat();
    int nis, NIS;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda_sifat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soal = findViewById(R.id.soalsifat);
        jw1 = findViewById(R.id.jawabansifat1);
        jw2 = findViewById(R.id.jawabansifat2);
        jw3 = findViewById(R.id.jawabansifat3);

        newlevelsifat();
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
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan_sifat);

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
            Intent intent = new Intent(PilihanGandaSifatActivity.this,SubmitNilaiSifatActivity.class);
            intent.putExtra("skorAkhirSifat", skorsifat);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "PilihanGandaSifat");
            startActivity(intent);
        }
    }

    public void newlevelsifat() {
        s  = pilganSifat.getrandomsifat();
        s1 = pilganSifat.getrandomsifat();

        int i = new Random().nextInt(3) + 1;

        if (i == 1) {
            j1 = s;
        } else {
            j1 = pilganSifat.getrandomsifat();
        }

        if (i == 2) {
            j2 = s;
        } else {
            j2 = pilganSifat.getrandomsifat();
        }
        if (i == 3) {
            j3 = s;
        } else {
            j3 = pilganSifat.getrandomsifat();
        }
        soal.setBackgroundResource(pilganSifat.getimagesoalsifat(s));
        jw1.setBackgroundResource(pilganSifat.getimagejawabansifat(j1));
        jw2.setBackgroundResource(pilganSifat.getimagejawabansifat(j2));
        jw3.setBackgroundResource(pilganSifat.getimagejawabansifat(j3));


    }
    public void isCorrect(boolean input) {
        TextView tampilSkor = findViewById(R.id.skorSifat);

        if(input && i < n) {
            MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
            skorsifat += 10;
            benar.start();
            newlevelsifat();
            i++;
            pindah();
        }else{
            MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
            salah.start();
            newlevelsifat();
            i++;
            pindah();
        }
        tampilSkor.setText("SKOR : " +skorsifat);
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
