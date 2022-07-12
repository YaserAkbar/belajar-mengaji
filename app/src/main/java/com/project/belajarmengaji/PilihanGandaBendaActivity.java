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

public class PilihanGandaBendaActivity extends AppCompatActivity {

    ImageButton jw1, jw2, jw3;
    ImageView soal;
    int s, s1, j1, j2, j3;
    int skorbenda = 0;
    PilganBenda pilganBenda = new PilganBenda();
    int n = pilganBenda.getjumlahbenda();
    int nis, NIS;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda_benda);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soal = findViewById(R.id.soalbenda);
        jw1 = findViewById(R.id.jawabanbenda1);
        jw2 = findViewById(R.id.jawabanbenda2);
        jw3 = findViewById(R.id.jawabanbenda3);

        newlevelbenda();
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
        tampilNis = findViewById(R.id.nis_menu_kuis_pilgan_benda);

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
            Intent intent = new Intent(PilihanGandaBendaActivity.this,SubmitNilaiBendaActivity.class);
            intent.putExtra("skorAkhirBenda", skorbenda);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "PilihanGandaBenda");
            startActivity(intent);
        }
    }

    public void newlevelbenda() {
        s  = pilganBenda.getrandombenda();
        s1 = pilganBenda.getrandombenda();

        int i = new Random().nextInt(3) + 1;

        if (i == 1) {
            j1 = s;
        } else {
            j1 = pilganBenda.getrandombenda();
        }

        if (i == 2) {
            j2 = s;
        } else {
            j2 = pilganBenda.getrandombenda();
        }
        if (i == 3) {
            j3 = s;
        } else {
            j3 = pilganBenda.getrandombenda();
        }
        soal.setBackgroundResource(pilganBenda.getimagesoalbenda(s));
        jw1.setBackgroundResource(pilganBenda.getimagejawabanbenda(j1));
        jw2.setBackgroundResource(pilganBenda.getimagejawabanbenda(j2));
        jw3.setBackgroundResource(pilganBenda.getimagejawabanbenda(j3));


    }
    public void isCorrect(boolean input) {
        TextView tampilSkor = findViewById(R.id.skorBenda);

        if(input && i < n) {
            MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
            skorbenda += 10;
            benar.start();
            newlevelbenda();
            i++;
            pindah();
        }else{
            MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
            salah.start();
            newlevelbenda();
            i++;
            pindah();
        }
        tampilSkor.setText("SKOR : " +skorbenda);
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
