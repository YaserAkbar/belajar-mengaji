package com.project.belajarmengaji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class NilaiSiswa extends AppCompatActivity {

    ImageButton btnNilaiPG, btnNilaiEssay, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_siswa2);

        btnNilaiPG = findViewById(R.id.btn_nilai_pg);
        btnNilaiEssay = findViewById(R.id.btn_nilai_essay);
        btnBack = findViewById(R.id.btn_back_nilai);

        btnNilaiPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NilaiSiswa.this,NilaiSiswaHewan.class);
                startActivity(intent);
            }
        });

        btnNilaiEssay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NilaiSiswa.this,NilaiEssayHewan.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NilaiSiswa.this,TampilanAdmin.class);
                startActivity(intent);
            }
        });
        
    }
    public void onBackPressed(){
        Intent intent = new Intent(NilaiSiswa.this,TampilanAdmin.class);
        startActivity(intent);
    }
}
