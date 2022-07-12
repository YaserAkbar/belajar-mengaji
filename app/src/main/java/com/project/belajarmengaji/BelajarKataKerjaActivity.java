package com.project.belajarmengaji;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class BelajarKataKerjaActivity extends AppCompatActivity {

    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    EditText IndonesiaKerja;
    ImageView TampilGambarKerja;
    ImageButton kembali, keluar;
    MediaPlayer mp;
    private Button tombolKerja;
    private MediaPlayer player;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar_kata_kerja);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        checkPermission();
        setNis();

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null)
                    IndonesiaKerja.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        TampilGambarKerja = findViewById(R.id.tampilgambarKerja);
        IndonesiaKerja = findViewById(R.id.txtindonesiaKerja);
        tombolKerja = findViewById(R.id.btnTerjemahKerja);
        kembali = findViewById(R.id.buttonBack);
        keluar = findViewById(R.id.buttonExit);
        player = new MediaPlayer();

        findViewById(R.id.btnmicKerja).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        IndonesiaKerja.setHint("Hasil suara di sini");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        IndonesiaKerja.setText("");
                        IndonesiaKerja.setHint("Mendengarkan...");
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;
                }
                return false;
            }
        });

        kembali = findViewById(R.id.buttonBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(BelajarKataKerjaActivity.this, BelajarActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "BelajarKataKerja");
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

        tombolKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IndonesiaKerja.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(BelajarKataKerjaActivity.this, "Data Kosong, Silahkan Masukan Kata Terlebih Dahulu!!", Toast.LENGTH_SHORT).show();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("aman"))                            //Awalan Gambar Kata Kerja Dalam Al-Qur'an
                {
                    TampilGambarKerja.setImageResource(R.drawable.aman_atau_selamat);
                    play_aman_atau_selamat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tentram")) {
                    TampilGambarKerja.setImageResource(R.drawable.aman_atau_tentram_atau_selamat_dari);
                    play_aman_atau_tentram_atau_selamat_dari();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("selamat dari")) {
                    TampilGambarKerja.setImageResource(R.drawable.aman_atau_tentram_atau_selamat_dari);
                    play_aman_atau_tentram_atau_selamat_dari();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("baik")) {
                    TampilGambarKerja.setImageResource(R.drawable.baik_atau_lezat_atau_bahagia_atau_cukup);
                    play_baik_atau_lezat_atau_bahagia_atau_cukup();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lezat")) {
                    TampilGambarKerja.setImageResource(R.drawable.baik_atau_lezat_atau_bahagia_atau_cukup);
                    play_baik_atau_lezat_atau_bahagia_atau_cukup();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bahagia")) {
                    TampilGambarKerja.setImageResource(R.drawable.baik_atau_lezat_atau_bahagia_atau_cukup);
                    play_baik_atau_lezat_atau_bahagia_atau_cukup();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("cukup")) {
                    TampilGambarKerja.setImageResource(R.drawable.baik_atau_lezat_atau_bahagia_atau_cukup);
                    play_baik_atau_lezat_atau_bahagia_atau_cukup();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("batal")) {
                    TampilGambarKerja.setImageResource(R.drawable.batal_atau_sia_sia_atau_hilang_atau_gagal);
                    play_batal_atau_sia_sia_atau_hilang_atau_gagal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sia-sia")) {
                    TampilGambarKerja.setImageResource(R.drawable.batal_atau_sia_sia_atau_hilang_atau_gagal);
                    play_batal_atau_sia_sia_atau_hilang_atau_gagal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sia sia")) {
                    TampilGambarKerja.setImageResource(R.drawable.batal_atau_sia_sia_atau_hilang_atau_gagal);
                    play_batal_atau_sia_sia_atau_hilang_atau_gagal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("gagal")) {
                    TampilGambarKerja.setImageResource(R.drawable.batal_atau_sia_sia_atau_hilang_atau_gagal);
                    play_batal_atau_sia_sia_atau_hilang_atau_gagal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersih")) {
                    TampilGambarKerja.setImageResource(R.drawable.bebas_atau_bersih_atau_sembuh_atau_selamat);
                    play_bebas_atau_bersih_atau_sembuh_atau_selamat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sembuh")) {
                    TampilGambarKerja.setImageResource(R.drawable.bebas_atau_bersih_atau_sembuh_atau_selamat);
                    play_bebas_atau_bersih_atau_sembuh_atau_selamat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("terlepas diri")) {
                    TampilGambarKerja.setImageResource(R.drawable.bebas_atau_terlepas_diri);
                    play_bebas_atau_terlepas_diri();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("belajar")) {
                    TampilGambarKerja.setImageResource(R.drawable.belajar);
                    play_belajar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("belas kasihan")) {
                    TampilGambarKerja.setImageResource(R.drawable.belas_kasihan);
                    play_belas_kasihan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jujur")) {
                    TampilGambarKerja.setImageResource(R.drawable.benar_atau_jujur);
                    play_benar_atau_jujur();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("benci")) {
                    TampilGambarKerja.setImageResource(R.drawable.benci);
                    play_benci();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbakti")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbakti);
                    play_berbakti();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbantah")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbantah_atau_berdebat_dengan);
                    play_berbantah_atau_berdebat_dengan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berdebat dengan")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbantah_atau_berdebat_dengan);
                    play_berbantah_atau_berdebat_dengan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbohong")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbohong);
                    play_berbohong();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbuat adil")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbuat_adil);
                    play_berbuat_adil();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbuat dengan sukarela")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbuat_dengan_sukarela);
                    play_berbuat_dengan_sukarela();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbuat jahat")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbuat_jahat_atau_merusak);
                    play_berbuat_jahat_atau_merusak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbuat kebaikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.berbuat_kebaikan);
                    play_berbuat_kebaikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bercekcok")) {
                    TampilGambarKerja.setImageResource(R.drawable.bercekcok_atau_bertengkar_atau_bermusuhan);
                    play_bercekcok_atau_bertengkar_atau_bermusuhan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertengkar")) {
                    TampilGambarKerja.setImageResource(R.drawable.bercekcok_atau_bertengkar_atau_bermusuhan);
                    play_bercekcok_atau_bertengkar_atau_bermusuhan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bermusuhan")) {
                    TampilGambarKerja.setImageResource(R.drawable.bercekcok_atau_bertengkar_atau_bermusuhan);
                    play_bercekcok_atau_bertengkar_atau_bermusuhan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berdebat")) {
                    TampilGambarKerja.setImageResource(R.drawable.berdebat_atau_berbantah);
                    play_berdebat_atau_berbantah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berdiri")) {
                    TampilGambarKerja.setImageResource(R.drawable.berdiri_atau_bangkit_atau_bangun);
                    play_berdiri_atau_bangkit_atau_bangun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bangkit")) {
                    TampilGambarKerja.setImageResource(R.drawable.berdiri_atau_bangkit_atau_bangun);
                    play_berdiri_atau_bangkit_atau_bangun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bangun")) {
                    TampilGambarKerja.setImageResource(R.drawable.berdiri_atau_bangkit_atau_bangun);
                    play_berdiri_atau_bangkit_atau_bangun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beredar")) {
                    TampilGambarKerja.setImageResource(R.drawable.beredar_atau_berputar_atau_usang);
                    play_beredar_atau_berputar_atau_usang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berputar")) {
                    TampilGambarKerja.setImageResource(R.drawable.beredar_atau_berputar_atau_usang);
                    play_beredar_atau_berputar_atau_usang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("usang")) {
                    TampilGambarKerja.setImageResource(R.drawable.beredar_atau_berputar_atau_usang);
                    play_beredar_atau_berputar_atau_usang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berharap")) {
                    TampilGambarKerja.setImageResource(R.drawable.berharap);
                    play_berharap();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berhasil")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhasil_atau_sukses_atau_beruntung);
                    play_berhasil_atau_sukses_atau_beruntung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sukses")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhasil_atau_sukses_atau_beruntung);
                    play_berhasil_atau_sukses_atau_beruntung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berhati-hati")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhati_hati_atau_takut_atau_waspada);
                    play_berhati_hati_atau_takut_atau_waspada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berhati hati")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhati_hati_atau_takut_atau_waspada);
                    play_berhati_hati_atau_takut_atau_waspada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("waspada")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhati_hati_atau_takut_atau_waspada);
                    play_berhati_hati_atau_takut_atau_waspada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berhijrah")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhijrah);
                    play_berhijrah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berhutang piutang")) {
                    TampilGambarKerja.setImageResource(R.drawable.berhutang_piutang);
                    play_berhutang_piutang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beribadah")) {
                    TampilGambarKerja.setImageResource(R.drawable.beribadah);
                    play_beribadah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beriman")) {
                    TampilGambarKerja.setImageResource(R.drawable.beriman);
                    play_beriman();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beristirahat")) {
                    TampilGambarKerja.setImageResource(R.drawable.beristirahat_atau_berlindung_atau_tinggal_di);
                    play_beristirahat_atau_berlindung_atau_tinggal_di();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tinggal di")) {
                    TampilGambarKerja.setImageResource(R.drawable.beristirahat_atau_berlindung_atau_tinggal_di);
                    play_beristirahat_atau_berlindung_atau_tinggal_di();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beritikaf")) {
                    TampilGambarKerja.setImageResource(R.drawable.beritikaf);
                    play_beritikaf();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berjalan kaki")) {
                    TampilGambarKerja.setImageResource(R.drawable.berjalan_kaki);
                    play_berjalan_kaki();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berjanji")) {
                    TampilGambarKerja.setImageResource(R.drawable.berjanji_atau_bernadzar);
                    play_berjanji_atau_bernadzar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bernadzar")) {
                    TampilGambarKerja.setImageResource(R.drawable.berjanji_atau_bernadzar);
                    play_berjanji_atau_bernadzar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berjudi")) {
                    TampilGambarKerja.setImageResource(R.drawable.berjudi);
                    play_berjudi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkata")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkata);
                    play_berkata();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berbicara kepada")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkata_atau_berbicara_kepada);
                    play_berkata_atau_berbicara_kepada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkata keji")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkata_keji_atau_berkata_kotor);
                    play_berkata_keji_atau_berkata_kotor();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkata kotor")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkata_keji_atau_berkata_kotor);
                    play_berkata_keji_atau_berkata_kotor();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkembang")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkembang_atau_sholeh_atau_baik);
                    play_berkembang_atau_sholeh_atau_baik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkhianat")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkhianat);
                    play_berkhianat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkhitbah")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkhitbah_atau_meminang_atau_melamar);
                    play_berkhitbah_atau_meminang_atau_melamar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meminang")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkhitbah_atau_meminang_atau_melamar);
                    play_berkhitbah_atau_meminang_atau_melamar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melamar")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkhitbah_atau_meminang_atau_melamar);
                    play_berkhitbah_atau_meminang_atau_melamar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkurang")) {
                    TampilGambarKerja.setImageResource(R.drawable.berkurang);
                    play_berkurang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlaku adil")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlaku_adil);
                    play_berlaku_adil();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlaku munafik")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlaku_munafik);
                    play_berlaku_munafik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlalu")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlalu_atau_lewat);
                    play_berlalu_atau_lewat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lewat")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlalu_atau_lewat);
                    play_berlalu_atau_lewat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlari")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlari_atau_mengalir);
                    play_berlari_atau_mengalir();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengalir")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlari_atau_mengalir);
                    play_berlari_atau_mengalir();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlindung")) {
                    TampilGambarKerja.setImageResource(R.drawable.berlindung);
                    play_berlindung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berniat kuat")) {
                    TampilGambarKerja.setImageResource(R.drawable.berniat_kuat_atau_bermaksud);
                    play_berniat_kuat_atau_bermaksud();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bermaksud")) {
                    TampilGambarKerja.setImageResource(R.drawable.berniat_kuat_atau_bermaksud);
                    play_berniat_kuat_atau_bermaksud();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berpaling")) {
                    TampilGambarKerja.setImageResource(R.drawable.berpaling);
                    play_berpaling();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menguasai")) {
                    TampilGambarKerja.setImageResource(R.drawable.berpaling_atau_menguasai);
                    play_berpaling_atau_menguasai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berpegang teguh")) {
                    TampilGambarKerja.setImageResource(R.drawable.berpegang_teguh);
                    play_berpegang_teguh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berputar")) {
                    TampilGambarKerja.setImageResource(R.drawable.berputar_atau_berkeliling);
                    play_berputar_atau_berkeliling();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berkeliling")) {
                    TampilGambarKerja.setImageResource(R.drawable.berputar_atau_berkeliling);
                    play_berputar_atau_berkeliling();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berselisih")) {
                    TampilGambarKerja.setImageResource(R.drawable.berselisih_atau_tidak_sepaham);
                    play_berselisih_atau_tidak_sepaham();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tidak sepaham")) {
                    TampilGambarKerja.setImageResource(R.drawable.berselisih_atau_tidak_sepaham);
                    play_berselisih_atau_tidak_sepaham();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersinar")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersinar_atau_menyalakan);
                    play_bersinar_atau_menyalakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersungguh-sungguh")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat);
                    play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersungguh sungguh")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat);
                    play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berjuang")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat);
                    play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berjihad")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat);
                    play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("semangat")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat);
                    play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersyukur")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersyukur_atau_berterima_kasih);
                    play_bersyukur_atau_berterima_kasih();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berterima kasih")) {
                    TampilGambarKerja.setImageResource(R.drawable.bersyukur_atau_berterima_kasih);
                    play_bersyukur_atau_berterima_kasih();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertahta")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertahta_atau_menguasai_atau_sama);
                    play_bertahta_atau_menguasai_atau_sama();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sama")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertahta_atau_menguasai_atau_sama);
                    play_bertahta_atau_menguasai_atau_sama();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertambah")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertambah);
                    play_bertambah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertasbih")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertasbih);
                    play_bertasbih();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertaubat")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertaubat);
                    play_bertaubat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertemu")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertemu);
                    play_bertemu();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertolak")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertolak_atau_meninggalkan_atau_menuangkan);
                    play_bertolak_atau_meninggalkan_atau_menuangkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menuangkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.bertolak_atau_meninggalkan_atau_menuangkan);
                    play_bertolak_atau_meninggalkan_atau_menuangkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berubah")) {
                    TampilGambarKerja.setImageResource(R.drawable.berubah_atau_basi);
                    play_berubah_atau_basi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("basi")) {
                    TampilGambarKerja.setImageResource(R.drawable.berubah_atau_basi);
                    play_berubah_atau_basi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berumur antara 30 50 tahun")) {
                    TampilGambarKerja.setImageResource(R.drawable.berumur_antara_30_50_tahun);
                    play_berumur_antara_30_50_tahun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("beruntung")) {
                    TampilGambarKerja.setImageResource(R.drawable.beruntung);
                    play_beruntung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berusaha")) {
                    TampilGambarKerja.setImageResource(R.drawable.berusaha);
                    play_berusaha();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bicara yang bukan-bukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.bicara_yang_bukan_bukan_atau_keliru);
                    play_bicara_yang_bukan_bukan_atau_keliru();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bicara yang bukan bukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.bicara_yang_bukan_bukan_atau_keliru);
                    play_bicara_yang_bukan_bukan_atau_keliru();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("keliru")) {
                    TampilGambarKerja.setImageResource(R.drawable.bicara_yang_bukan_bukan_atau_keliru);
                    play_bicara_yang_bukan_bukan_atau_keliru();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bingung")) {
                    TampilGambarKerja.setImageResource(R.drawable.bingung_atau_terombang_ambing);
                    play_bingung_atau_terombang_ambing();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("terombang ambing")) {
                    TampilGambarKerja.setImageResource(R.drawable.bingung_atau_terombang_ambing);
                    play_bingung_atau_terombang_ambing();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bisu")) {
                    TampilGambarKerja.setImageResource(R.drawable.bisu);
                    play_bisu();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bodoh")) {
                    TampilGambarKerja.setImageResource(R.drawable.bodoh_atau_tolol_atau_jelek_akhlaknya);
                    play_bodoh_atau_tolol_atau_jelek_akhlaknya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tolol")) {
                    TampilGambarKerja.setImageResource(R.drawable.bodoh_atau_tolol_atau_jelek_akhlaknya);
                    play_bodoh_atau_tolol_atau_jelek_akhlaknya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jelek akhlaknya")) {
                    TampilGambarKerja.setImageResource(R.drawable.bodoh_atau_tolol_atau_jelek_akhlaknya);
                    play_bodoh_atau_tolol_atau_jelek_akhlaknya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("buta")) {
                    TampilGambarKerja.setImageResource(R.drawable.buta);
                    play_buta();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("condong")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_berpihak_atau_cenderung_kepada);
                    play_condong_atau_berpihak_atau_cenderung_kepada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berpihak")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_berpihak_atau_cenderung_kepada);
                    play_condong_atau_berpihak_atau_cenderung_kepada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("cenderung kepada")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_berpihak_atau_cenderung_kepada);
                    play_condong_atau_berpihak_atau_cenderung_kepada();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("miring")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_miring_atau_cenderung_atau_bersandar);
                    play_condong_atau_miring_atau_cenderung_atau_bersandar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("cenderung")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_miring_atau_cenderung_atau_bersandar);
                    play_condong_atau_miring_atau_cenderung_atau_bersandar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bersandar")) {
                    TampilGambarKerja.setImageResource(R.drawable.condong_atau_miring_atau_cenderung_atau_bersandar);
                    play_condong_atau_miring_atau_cenderung_atau_bersandar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencukupi")) {
                    TampilGambarKerja.setImageResource(R.drawable.cukup_atau_mencukupi);
                    play_cukup_atau_mencukupi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("datang")) {
                    TampilGambarKerja.setImageResource(R.drawable.datang);
                    play_datang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tiba")) {
                    TampilGambarKerja.setImageResource(R.drawable.datang_atau_tiba);
                    play_datang_atau_tiba();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("duduk")) {
                    TampilGambarKerja.setImageResource(R.drawable.duduk);
                    play_duduk();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("duduk lalu berdiri")) {
                    TampilGambarKerja.setImageResource(R.drawable.duduk_lalu_berdiri_atau_durhaka_atau_menentang);
                    play_duduk_lalu_berdiri_atau_durhaka_atau_menentang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("durhaka")) {
                    TampilGambarKerja.setImageResource(R.drawable.duduk_lalu_berdiri_atau_durhaka_atau_menentang);
                    play_duduk_lalu_berdiri_atau_durhaka_atau_menentang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menentang")) {
                    TampilGambarKerja.setImageResource(R.drawable.duduk_lalu_berdiri_atau_durhaka_atau_menentang);
                    play_duduk_lalu_berdiri_atau_durhaka_atau_menentang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("enggan")) {
                    TampilGambarKerja.setImageResource(R.drawable.enggan_atau_menjauhkan_diri_dari_yang_tidak_baik);
                    play_enggan_atau_menjauhkan_diri_dari_yang_tidak_baik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menjauhkan diri dari yang tidak baik")) {
                    TampilGambarKerja.setImageResource(R.drawable.enggan_atau_menjauhkan_diri_dari_yang_tidak_baik);
                    play_enggan_atau_menjauhkan_diri_dari_yang_tidak_baik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("fasiq")) {
                    TampilGambarKerja.setImageResource(R.drawable.fasiq);
                    play_fasiq();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("gagal")) {
                    TampilGambarKerja.setImageResource(R.drawable.gagal_atau_hilang_semangat);
                    play_gagal_atau_hilang_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("hilang semangat")) {
                    TampilGambarKerja.setImageResource(R.drawable.gagal_atau_hilang_semangat);
                    play_gagal_atau_hilang_semangat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("putus")) {
                    TampilGambarKerja.setImageResource(R.drawable.gagal_atau_putus_atau_harapan_atau_kecewa);
                    play_gagal_atau_putus_atau_harapan_atau_kecewa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("harapan")) {
                    TampilGambarKerja.setImageResource(R.drawable.gagal_atau_putus_atau_harapan_atau_kecewa);
                    play_gagal_atau_putus_atau_harapan_atau_kecewa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kecewa")) {
                    TampilGambarKerja.setImageResource(R.drawable.gagal_atau_putus_atau_harapan_atau_kecewa);
                    play_gagal_atau_putus_atau_harapan_atau_kecewa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("gampang")) {
                    TampilGambarKerja.setImageResource(R.drawable.gampang_atau_mudah_atau_lemah_atau_hina);
                    play_gampang_atau_mudah_atau_lemah_atau_hina();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mudah")) {
                    TampilGambarKerja.setImageResource(R.drawable.gampang_atau_mudah_atau_lemah_atau_hina);
                    play_gampang_atau_mudah_atau_lemah_atau_hina();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("hadir")) {
                    TampilGambarKerja.setImageResource(R.drawable.hadir_atau_datang);
                    play_hadir_atau_datang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("haji")) {
                    TampilGambarKerja.setImageResource(R.drawable.haji);
                    play_haji();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("halal")) {
                    TampilGambarKerja.setImageResource(R.drawable.halal);
                    play_halal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("hampir")) {
                    TampilGambarKerja.setImageResource(R.drawable.hampir);
                    play_hampir();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("heran")) {
                    TampilGambarKerja.setImageResource(R.drawable.heran_atau_kagum_atau_takjub);
                    play_heran_atau_kagum_atau_takjub();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kagum")) {
                    TampilGambarKerja.setImageResource(R.drawable.heran_atau_kagum_atau_takjub);
                    play_heran_atau_kagum_atau_takjub();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("takjub")) {
                    TampilGambarKerja.setImageResource(R.drawable.heran_atau_kagum_atau_takjub);
                    play_heran_atau_kagum_atau_takjub();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("hilang")) {
                    TampilGambarKerja.setImageResource(R.drawable.hilang);
                    play_hilang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("hina")) {
                    TampilGambarKerja.setImageResource(R.drawable.hina);
                    play_hina();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jelas")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelas);
                    play_jelas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("nyata")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelas_atau_nyata_atau_tampak_atau_terang);
                    play_jelas_atau_nyata_atau_tampak_atau_terang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tampak")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelas_atau_nyata_atau_tampak_atau_terang);
                    play_jelas_atau_nyata_atau_tampak_atau_terang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("terang")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelas_atau_nyata_atau_tampak_atau_terang);
                    play_jelas_atau_nyata_atau_tampak_atau_terang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jelek")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelek_atau_buruk);
                    play_jelek_atau_buruk();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("buruk")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelek_atau_buruk);
                    play_jelek_atau_buruk();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jahat")) {
                    TampilGambarKerja.setImageResource(R.drawable.jelek_atau_buruk_atau_jahat);
                    play_jelek_atau_buruk_atau_jahat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("jinak")) {
                    TampilGambarKerja.setImageResource(R.drawable.jinak_atau_ramah);
                    play_jinak_atau_ramah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("ramah")) {
                    TampilGambarKerja.setImageResource(R.drawable.jinak_atau_ramah);
                    play_jinak_atau_ramah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kafir")) {
                    TampilGambarKerja.setImageResource(R.drawable.kafir_atau_ingkar);
                    play_kafir_atau_ingkar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("ingkar")) {
                    TampilGambarKerja.setImageResource(R.drawable.kafir_atau_ingkar);
                    play_kafir_atau_ingkar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kasar tutur katanya")) {
                    TampilGambarKerja.setImageResource(R.drawable.kasar_tutur_katanya_atau_kurang_ajar);
                    play_kasar_tutur_katanya_atau_kurang_ajar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kurang ajar")) {
                    TampilGambarKerja.setImageResource(R.drawable.kasar_tutur_katanya_atau_kurang_ajar);
                    play_kasar_tutur_katanya_atau_kurang_ajar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kekal")) {
                    TampilGambarKerja.setImageResource(R.drawable.kekal_atau_abadi);
                    play_kekal_atau_abadi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("abadi")) {
                    TampilGambarKerja.setImageResource(R.drawable.kekal_atau_abadi);
                    play_kekal_atau_abadi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("keluar")) {
                    TampilGambarKerja.setImageResource(R.drawable.keluar_atau_muncul);
                    play_keluar_atau_muncul();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("muncul")) {
                    TampilGambarKerja.setImageResource(R.drawable.keluar_atau_muncul);
                    play_keluar_atau_muncul();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kembali")) {
                    TampilGambarKerja.setImageResource(R.drawable.kembali);
                    play_kembali();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("pulang")) {
                    TampilGambarKerja.setImageResource(R.drawable.kembali_atau_pulang);
                    play_kembali_atau_pulang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("keras")) {
                    TampilGambarKerja.setImageResource(R.drawable.keras);
                    play_keras();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kokoh")) {
                    TampilGambarKerja.setImageResource(R.drawable.kokoh_atau_kuat);
                    play_kokoh_atau_kuat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("konsisten")) {
                    TampilGambarKerja.setImageResource(R.drawable.konsisten_atau_menjadi_lurus_atau_tegak);
                    play_konsisten_atau_menjadi_lurus_atau_tegak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menjadi lurus")) {
                    TampilGambarKerja.setImageResource(R.drawable.konsisten_atau_menjadi_lurus_atau_tegak);
                    play_konsisten_atau_menjadi_lurus_atau_tegak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tegak")) {
                    TampilGambarKerja.setImageResource(R.drawable.konsisten_atau_menjadi_lurus_atau_tegak);
                    play_konsisten_atau_menjadi_lurus_atau_tegak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kosong")) {
                    TampilGambarKerja.setImageResource(R.drawable.kosong_atau_roboh_atau_runtuh);
                    play_kosong_atau_roboh_atau_runtuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("roboh")) {
                    TampilGambarKerja.setImageResource(R.drawable.kosong_atau_roboh_atau_runtuh);
                    play_kosong_atau_roboh_atau_runtuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("runtuh")) {
                    TampilGambarKerja.setImageResource(R.drawable.kosong_atau_roboh_atau_runtuh);
                    play_kosong_atau_roboh_atau_runtuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("selesai")) {
                    TampilGambarKerja.setImageResource(R.drawable.kosong_atau_selesai_atau_habis);
                    play_kosong_atau_selesai_atau_habis();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("habis")) {
                    TampilGambarKerja.setImageResource(R.drawable.kosong_atau_selesai_atau_habis);
                    play_kosong_atau_selesai_atau_habis();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membantu")) {
                    TampilGambarKerja.setImageResource(R.drawable.kuat_atau_membantu_atau_menguatkan);
                    play_kuat_atau_membantu_atau_menguatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menguatkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.kuat_atau_membantu_atau_menguatkan);
                    play_kuat_atau_membantu_atau_menguatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lapar")) {
                    TampilGambarKerja.setImageResource(R.drawable.lapar);
                    play_lapar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lari")) {
                    TampilGambarKerja.setImageResource(R.drawable.lari);
                    play_lari();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lemah lembut")) {
                    TampilGambarKerja.setImageResource(R.drawable.lemah_lembut_atau_lemas_atau_lunak);
                    play_lemah_lembut_atau_lemas_atau_lunak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lemas")) {
                    TampilGambarKerja.setImageResource(R.drawable.lemah_lembut_atau_lemas_atau_lunak);
                    play_lemah_lembut_atau_lemas_atau_lunak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lunak")) {
                    TampilGambarKerja.setImageResource(R.drawable.lemah_lembut_atau_lemas_atau_lunak);
                    play_lemah_lembut_atau_lemas_atau_lunak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("luas")) {
                    TampilGambarKerja.setImageResource(R.drawable.luas_atau_meliputi_atau_dapat);
                    play_luas_atau_meliputi_atau_dapat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meliputi")) {
                    TampilGambarKerja.setImageResource(R.drawable.luas_atau_meliputi_atau_dapat);
                    play_luas_atau_meliputi_atau_dapat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("dapat")) {
                    TampilGambarKerja.setImageResource(R.drawable.luas_atau_meliputi_atau_dapat);
                    play_luas_atau_meliputi_atau_dapat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lupa")) {
                    TampilGambarKerja.setImageResource(R.drawable.lupa);
                    play_lupa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lalai")) {
                    TampilGambarKerja.setImageResource(R.drawable.lupa_atau_lalai);
                    play_lupa_atau_lalai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("makan")) {
                    TampilGambarKerja.setImageResource(R.drawable.makan);
                    play_makan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("malu")) {
                    TampilGambarKerja.setImageResource(R.drawable.malu);
                    play_malu();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mampu")) {
                    TampilGambarKerja.setImageResource(R.drawable.mampu_atau_kuat_atau_kuasa);
                    play_mampu_atau_kuat_atau_kuasa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kuasa")) {
                    TampilGambarKerja.setImageResource(R.drawable.mampu_atau_kuat_atau_kuasa);
                    play_mampu_atau_kuat_atau_kuasa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("marah")) {
                    TampilGambarKerja.setImageResource(R.drawable.marah_atau_membenci);
                    play_marah_atau_membenci();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membenci")) {
                    TampilGambarKerja.setImageResource(R.drawable.marah_atau_membenci);
                    play_marah_atau_membenci();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("masuk")) {
                    TampilGambarKerja.setImageResource(R.drawable.masuk);
                    play_masuk();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mati")) {
                    TampilGambarKerja.setImageResource(R.drawable.mati);
                    play_mati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("binasa")) {
                    TampilGambarKerja.setImageResource(R.drawable.mati_atau_binasa);
                    play_mati_atau_binasa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mau")) {
                    TampilGambarKerja.setImageResource(R.drawable.mau_atau_ingin);
                    play_mau_atau_ingin();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("ingin")) {
                    TampilGambarKerja.setImageResource(R.drawable.mau_atau_ingin);
                    play_mau_atau_ingin();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melaknati")) {
                    TampilGambarKerja.setImageResource(R.drawable.melaknati);
                    play_melaknati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melampaui")) {
                    TampilGambarKerja.setImageResource(R.drawable.melampaui);
                    play_melampaui();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melewati batas")) {
                    TampilGambarKerja.setImageResource(R.drawable.melampaui_atau_melewati_batas);
                    play_melampaui_atau_melewati_batas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melampaui batas")) {
                    TampilGambarKerja.setImageResource(R.drawable.melampaui_batas_atau_berlebihan);
                    play_melampaui_batas_atau_berlebihan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berlebihan")) {
                    TampilGambarKerja.setImageResource(R.drawable.melampaui_batas_atau_berlebihan);
                    play_melampaui_batas_atau_berlebihan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melanggar")) {
                    TampilGambarKerja.setImageResource(R.drawable.melanggar_atau_melampaui_batas);
                    play_melanggar_atau_melampaui_batas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengkhianati")) {
                    TampilGambarKerja.setImageResource(R.drawable.melanggar_atau_mengkhianati);
                    play_melanggar_atau_mengkhianati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tidak menepati")) {
                    TampilGambarKerja.setImageResource(R.drawable.melanggar_atau_tidak_menepati);
                    play_melanggar_atau_tidak_menepati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melangkah")) {
                    TampilGambarKerja.setImageResource(R.drawable.melangkah);
                    play_melangkah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melapangkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.melapangkan_atau_lepas);
                    play_melapangkan_atau_lepas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("lepas")) {
                    TampilGambarKerja.setImageResource(R.drawable.melapangkan_atau_lepas);
                    play_melapangkan_atau_lepas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melarang")) {
                    TampilGambarKerja.setImageResource(R.drawable.melarang);
                    play_melarang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melempar")) {
                    TampilGambarKerja.setImageResource(R.drawable.melempar_atau_membuang_atau_mengesampingkan);
                    play_melempar_atau_membuang_atau_mengesampingkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membuang")) {
                    TampilGambarKerja.setImageResource(R.drawable.melempar_atau_membuang_atau_mengesampingkan);
                    play_melempar_atau_membuang_atau_mengesampingkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengesampingkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.melempar_atau_membuang_atau_mengesampingkan);
                    play_melempar_atau_membuang_atau_mengesampingkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melepaskan")) {
                    TampilGambarKerja.setImageResource(R.drawable.melepaskan_atau_mengeluarkan);
                    play_melepaskan_atau_mengeluarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melintasi")) {
                    TampilGambarKerja.setImageResource(R.drawable.melintasi_atau_menyebrangi);
                    play_melintasi_atau_menyebrangi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menyebrangi")) {
                    TampilGambarKerja.setImageResource(R.drawable.melintasi_atau_menyebrangi);
                    play_melintasi_atau_menyebrangi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melipat gandakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.melipat_gandakan);
                    play_melipat_gandakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meliputi")) {
                    TampilGambarKerja.setImageResource(R.drawable.meliputi_atau_mengelilingi);
                    play_meliputi_atau_mengelilingi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("melukai")) {
                    TampilGambarKerja.setImageResource(R.drawable.melukai);
                    play_melukai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memaafkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memaafkan);
                    play_memaafkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memahami")) {
                    TampilGambarKerja.setImageResource(R.drawable.memahami_atau_mengerti);
                    play_memahami_atau_mengerti();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengerti")) {
                    TampilGambarKerja.setImageResource(R.drawable.memahami_atau_mengerti);
                    play_memahami_atau_mengerti();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("cerdas")) {
                    TampilGambarKerja.setImageResource(R.drawable.memahami_atau_mengetahui_atau_cerdas_atau_pandai);
                    play_memahami_atau_mengetahui_atau_cerdas_atau_pandai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("pandai")) {
                    TampilGambarKerja.setImageResource(R.drawable.memahami_atau_mengetahui_atau_cerdas_atau_pandai);
                    play_memahami_atau_mengetahui_atau_cerdas_atau_pandai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memakaikan pakaian")) {
                    TampilGambarKerja.setImageResource(R.drawable.memakaikan_pakaian);
                    play_memakaikan_pakaian();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memaksa")) {
                    TampilGambarKerja.setImageResource(R.drawable.memaksa);
                    play_memaksa();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memancar")) {
                    TampilGambarKerja.setImageResource(R.drawable.memancar_atau_mengalir);
                    play_memancar_atau_mengalir();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menyembur")) {
                    TampilGambarKerja.setImageResource(R.drawable.memancar_atau_menyembur);
                    play_memancar_atau_menyembur();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memanggil")) {
                    TampilGambarKerja.setImageResource(R.drawable.memanggil_atau_berteriak);
                    play_memanggil_atau_berteriak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berteriak")) {
                    TampilGambarKerja.setImageResource(R.drawable.memanggil_atau_berteriak);
                    play_memanggil_atau_berteriak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengundang")) {
                    TampilGambarKerja.setImageResource(R.drawable.memanggil_atau_mengundang_atau_meminta);
                    play_memanggil_atau_mengundang_atau_meminta();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meminta")) {
                    TampilGambarKerja.setImageResource(R.drawable.memanggil_atau_mengundang_atau_meminta);
                    play_memanggil_atau_mengundang_atau_meminta();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mematikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mematikan);
                    play_mematikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membahayakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membahayakan);
                    play_membahayakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membalas")) {
                    TampilGambarKerja.setImageResource(R.drawable.membalas);
                    play_membalas();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membalikkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membalikkan_atau_merubah);
                    play_membalikkan_atau_merubah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("merubah")) {
                    TampilGambarKerja.setImageResource(R.drawable.membalikkan_atau_merubah);
                    play_membalikkan_atau_merubah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membangkitkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membangkitkan_atau_mengutus_atau_mengirim);
                    play_membangkitkan_atau_mengutus_atau_mengirim();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengutus")) {
                    TampilGambarKerja.setImageResource(R.drawable.membangkitkan_atau_mengutus_atau_mengirim);
                    play_membangkitkan_atau_mengutus_atau_mengirim();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengirim")) {
                    TampilGambarKerja.setImageResource(R.drawable.membangkitkan_atau_mengutus_atau_mengirim);
                    play_membangkitkan_atau_mengutus_atau_mengirim();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membangun")) {
                    TampilGambarKerja.setImageResource(R.drawable.membangun);
                    play_membangun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membayar")) {
                    TampilGambarKerja.setImageResource(R.drawable.membayar_atau_mendorong_atau_menolak);
                    play_membayar_atau_mendorong_atau_menolak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendorong")) {
                    TampilGambarKerja.setImageResource(R.drawable.membayar_atau_mendorong_atau_menolak);
                    play_membayar_atau_mendorong_atau_menolak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membayar zakat")) {
                    TampilGambarKerja.setImageResource(R.drawable.membayar_zakat);
                    play_membayar_zakat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membebani")) {
                    TampilGambarKerja.setImageResource(R.drawable.membebani);
                    play_membebani();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengalungkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membebani_atau_mengalungkan);
                    play_membebani_atau_mengalungkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membebankan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membebankan_atau_menawarkan);
                    play_membebankan_atau_menawarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menawarkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membebankan_atau_menawarkan);
                    play_membebankan_atau_menawarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membedakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membedakan);
                    play_membedakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memisahkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membedakan_atau_memisahkan);
                    play_membedakan_atau_memisahkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperkuat")) {
                    TampilGambarKerja.setImageResource(R.drawable.membela_atau_memperkuat_atau_mendukung);
                    play_membela_atau_memperkuat_atau_mendukung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendukung")) {
                    TampilGambarKerja.setImageResource(R.drawable.membela_atau_memperkuat_atau_mendukung);
                    play_membela_atau_memperkuat_atau_mendukung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membela")) {
                    TampilGambarKerja.setImageResource(R.drawable.membela_atau_memperkuat_atau_mendukung);
                    play_membela_atau_memperkuat_atau_mendukung();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membelah")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelah_atau_memecahkan_atau_sulit);
                    play_membelah_atau_memecahkan_atau_sulit();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memecahkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelah_atau_memecahkan_atau_sulit);
                    play_membelah_atau_memecahkan_atau_sulit();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("sulit")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelah_atau_memecahkan_atau_sulit);
                    play_membelah_atau_memecahkan_atau_sulit();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membeli")) {
                    TampilGambarKerja.setImageResource(R.drawable.membeli);
                    play_membeli();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menjual")) {
                    TampilGambarKerja.setImageResource(R.drawable.membeli_atau_menjual);
                    play_membeli_atau_menjual();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membelokkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelokkan_atau_memalingkan_atau_mengubah);
                    play_membelokkan_atau_memalingkan_atau_mengubah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memalingkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelokkan_atau_memalingkan_atau_mengubah);
                    play_membelokkan_atau_memalingkan_atau_mengubah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengubah")) {
                    TampilGambarKerja.setImageResource(R.drawable.membelokkan_atau_memalingkan_atau_mengubah);
                    play_membelokkan_atau_memalingkan_atau_mengubah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membenarkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membenarkan_atau_mempercayai);
                    play_membenarkan_atau_mempercayai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mempercayai")) {
                    TampilGambarKerja.setImageResource(R.drawable.membenarkan_atau_mempercayai);
                    play_membenarkan_atau_mempercayai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membentangkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membentangkan);
                    play_membentangkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meluaskan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membentangkan_atau_meluaskan);
                    play_membentangkan_atau_meluaskan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi);
                    play_memberi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi hidayah")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_hidayah);
                    play_memberi_hidayah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi infaq")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_infaq);
                    play_memberi_infaq();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi izin")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_izin);
                    play_memberi_izin();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi manfaat")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_manfaat_atau_berfaedah_atau_berguna);
                    play_memberi_manfaat_atau_berfaedah_atau_berguna();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berfaedah")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_manfaat_atau_berfaedah_atau_berguna);
                    play_memberi_manfaat_atau_berfaedah_atau_berguna();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("berguna")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_manfaat_atau_berfaedah_atau_berguna);
                    play_memberi_manfaat_atau_berfaedah_atau_berguna();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi nikmat")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_nikmat);
                    play_memberi_nikmat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi peringatan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_peringatan);
                    play_memberi_peringatan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi rahmat")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_rahmat);
                    play_memberi_rahmat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi rejeki")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_rejeki);
                    play_memberi_rejeki();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberi syafaat")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberi_syafaat);
                    play_memberi_syafaat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberikan);
                    play_memberikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kesaksian")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan);
                    play_memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengajukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan);
                    play_memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengemukakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan);
                    play_memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberitahu")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberitahu);
                    play_memberitahu();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memberitahukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberitahukan_atau_mengabarkan);
                    play_memberitahukan_atau_mengabarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengabarkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memberitahukan_atau_mengabarkan);
                    play_memberitahukan_atau_mengabarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membersihkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membersihkan_atau_menguji_atau_menjauhkan);
                    play_membersihkan_atau_menguji_atau_menjauhkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menjauhkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membersihkan_atau_menguji_atau_menjauhkan);
                    play_membersihkan_atau_menguji_atau_menjauhkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membuat")) {
                    TampilGambarKerja.setImageResource(R.drawable.membuat);
                    play_membuat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membuat marah")) {
                    TampilGambarKerja.setImageResource(R.drawable.membuat_marah);
                    play_membuat_marah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membuat senang")) {
                    TampilGambarKerja.setImageResource(R.drawable.membuat_senang);
                    play_membuat_senang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membuktikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.membuktikan);
                    play_membuktikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membunuh")) {
                    TampilGambarKerja.setImageResource(R.drawable.membunuh);
                    play_membunuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencincang")) {
                    TampilGambarKerja.setImageResource(R.drawable.memecahkan_atau_mencincang);
                    play_memecahkan_atau_mencincang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memeluk islam")) {
                    TampilGambarKerja.setImageResource(R.drawable.memeluk_islam_atau_tunduk_atau_patuh);
                    play_memeluk_islam_atau_tunduk_atau_patuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tunduk")) {
                    TampilGambarKerja.setImageResource(R.drawable.memeluk_islam_atau_tunduk_atau_patuh);
                    play_memeluk_islam_atau_tunduk_atau_patuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("patuh")) {
                    TampilGambarKerja.setImageResource(R.drawable.memeluk_islam_atau_tunduk_atau_patuh);
                    play_memeluk_islam_atau_tunduk_atau_patuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memenuhi janji")) {
                    TampilGambarKerja.setImageResource(R.drawable.memenuhi_janji);
                    play_memenuhi_janji();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memerdekakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memerdekakan_atau_menulis_atau_menerbitkan);
                    play_memerdekakan_atau_menulis_atau_menerbitkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menerbitkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memerdekakan_atau_menulis_atau_menerbitkan);
                    play_memerdekakan_atau_menulis_atau_menerbitkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memfitnah")) {
                    TampilGambarKerja.setImageResource(R.drawable.memfitnah);
                    play_memfitnah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memilih")) {
                    TampilGambarKerja.setImageResource(R.drawable.memilih);
                    play_memilih();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memiliki")) {
                    TampilGambarKerja.setImageResource(R.drawable.memiliki);
                    play_memiliki();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memimpin")) {
                    TampilGambarKerja.setImageResource(R.drawable.memimpin_atau_mengetuai);
                    play_memimpin_atau_mengetuai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengetuai")) {
                    TampilGambarKerja.setImageResource(R.drawable.memimpin_atau_mengetuai);
                    play_memimpin_atau_mengetuai();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meminjamkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.meminjamkan);
                    play_meminjamkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meminta")) {
                    TampilGambarKerja.setImageResource(R.drawable.meminta_atau_memohon_atau_bertanya);
                    play_meminta_atau_memohon_atau_bertanya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memohon")) {
                    TampilGambarKerja.setImageResource(R.drawable.meminta_atau_memohon_atau_bertanya);
                    play_meminta_atau_memohon_atau_bertanya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("bertanya")) {
                    TampilGambarKerja.setImageResource(R.drawable.meminta_atau_memohon_atau_bertanya);
                    play_meminta_atau_memohon_atau_bertanya();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("meminta pertolongan")) {
                    TampilGambarKerja.setImageResource(R.drawable.meminta_pertolongan);
                    play_meminta_pertolongan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengistimewakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memisahkan_atau_membedakan_atau_mengistimewakan);
                    play_memisahkan_atau_membedakan_atau_mengistimewakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menyapih")) {
                    TampilGambarKerja.setImageResource(R.drawable.memisahkan_atau_menyapih_atau_keluar);
                    play_memisahkan_atau_menyapih_atau_keluar();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memohon pertolongan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memohon_pertolongan);
                    play_memohon_pertolongan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memotong")) {
                    TampilGambarKerja.setImageResource(R.drawable.memotong_atau_memenggal);
                    play_memotong_atau_memenggal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memenggal")) {
                    TampilGambarKerja.setImageResource(R.drawable.memotong_atau_memenggal);
                    play_memotong_atau_memenggal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperhatikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memperhatikan);
                    play_memperhatikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperingatkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memperingatkan_atau_menyuruh_berhati_hati);
                    play_memperingatkan_atau_menyuruh_berhati_hati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperlihatkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.memperlihatkan);
                    play_memperlihatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperoleh")) {
                    TampilGambarKerja.setImageResource(R.drawable.memperoleh_atau_petunjuk_atau_menjadi_lurus_benar_atau_mencapai_kedewasaan);
                    play_memperoleh_atau_petunjuk_atau_menjadi_lurus_benar_atau_mencapai_kedewasaan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("petunjuk")) {
                    TampilGambarKerja.setImageResource(R.drawable.memperoleh_atau_petunjuk_atau_menjadi_lurus_benar_atau_mencapai_kedewasaan);
                    play_memperoleh_atau_petunjuk_atau_menjadi_lurus_benar_atau_mencapai_kedewasaan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mempersiapkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mempersiapkan);
                    play_mempersiapkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memuji")) {
                    TampilGambarKerja.setImageResource(R.drawable.memuji);
                    play_memuji();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memukul")) {
                    TampilGambarKerja.setImageResource(R.drawable.memukul_atau_mengetuk_atau_menimpakan_sesuatu_yang_menyakitkan);
                    play_memukul_atau_mengetuk_atau_menimpakan_sesuatu_yang_menyakitkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengetuk")) {
                    TampilGambarKerja.setImageResource(R.drawable.memukul_atau_mengetuk_atau_menimpakan_sesuatu_yang_menyakitkan);
                    play_memukul_atau_mengetuk_atau_menimpakan_sesuatu_yang_menyakitkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memusuhi")) {
                    TampilGambarKerja.setImageResource(R.drawable.memusuhi_atau_memalingkan_atau_menganiaya_atau_melampaui);
                    play_memusuhi_atau_memalingkan_atau_menganiaya_atau_melampaui();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menganiaya")) {
                    TampilGambarKerja.setImageResource(R.drawable.memusuhi_atau_memalingkan_atau_menganiaya_atau_melampaui);
                    play_memusuhi_atau_memalingkan_atau_menganiaya_atau_melampaui();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menahan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menahan);
                    play_menahan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menanam")) {
                    TampilGambarKerja.setImageResource(R.drawable.menanam_atau_membajak);
                    play_menanam_atau_membajak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("membajak")) {
                    TampilGambarKerja.setImageResource(R.drawable.menanam_atau_membajak);
                    play_menanam_atau_membajak();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menang")) {
                    TampilGambarKerja.setImageResource(R.drawable.menang);
                    play_menang();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menanggung")) {
                    TampilGambarKerja.setImageResource(R.drawable.menanggung_atau_mengurus_atau_mencukupi_nafkah);
                    play_menanggung_atau_mengurus_atau_mencukupi_nafkah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengurus")) {
                    TampilGambarKerja.setImageResource(R.drawable.menanggung_atau_mengurus_atau_mencukupi_nafkah);
                    play_menanggung_atau_mengurus_atau_mencukupi_nafkah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menahan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menanti_atau_tinggal_atau_berdiam_di_atau_menghindari);
                    play_menanti_atau_tinggal_atau_berdiam_di_atau_menghindari();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menasehati")) {
                    TampilGambarKerja.setImageResource(R.drawable.menasehati);
                    play_menasehati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menaungi")) {
                    TampilGambarKerja.setImageResource(R.drawable.menaungi);
                    play_menaungi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencabut")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencabut_atau_memecat);
                    play_mencabut_atau_memecat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memecat")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencabut_atau_memecat);
                    play_mencabut_atau_memecat();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencampurkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencampurkan);
                    play_mencampurkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencari")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencari);
                    play_mencari();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("dengki")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencari_atau_durhaka_atau_dengki);
                    play_mencari_atau_durhaka_atau_dengki();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("kemenangan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencari_atau_kemenangan);
                    play_mencari_atau_kemenangan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menyimpang")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencari_atau_menyimpang_atau_durhaka);
                    play_mencari_atau_menyimpang_atau_durhaka();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencegah")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencegah_atau_mengahalang_halangi);
                    play_mencegah_atau_mengahalang_halangi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencemooh")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencemooh);
                    play_mencemooh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menceritakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menceritakan_atau_memberitahukan);
                    play_menceritakan_atau_memberitahukan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menciduk")) {
                    TampilGambarKerja.setImageResource(R.drawable.menciduk);
                    play_menciduk();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencipta")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencipta);
                    play_mencipta();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menciptakan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menciptakan);
                    play_menciptakan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mencukur")) {
                    TampilGambarKerja.setImageResource(R.drawable.mencukur);
                    play_mencukur();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendahului")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendahului);
                    play_mendahului();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendaki")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendaki_atau_naik);
                    play_mendaki_atau_naik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("naik")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendaki_atau_naik);
                    play_mendaki_atau_naik();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendapat hidayah")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendapat_hidayah);
                    play_mendapat_hidayah();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendapatkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendapatkan);
                    play_mendapatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menyusul")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendapatkan_atau_menyusul_atau_mengikuti);
                    play_mendapatkan_atau_menyusul_atau_mengikuti();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendengarkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendengarkan);
                    play_mendengarkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendiktekan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendiktekan_atau_menjadikan_bosan);
                    play_mendiktekan_atau_menjadikan_bosan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mendirikan")) {
                    TampilGambarKerja.setImageResource(R.drawable.mendirikan);
                    play_mendirikan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menebus")) {
                    TampilGambarKerja.setImageResource(R.drawable.menebus);
                    play_menebus();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menempati")) {
                    TampilGambarKerja.setImageResource(R.drawable.menempati);
                    play_menempati();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menemukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menemukan_atau_memperoleh_atau_mendapatkan);
                    play_menemukan_atau_memperoleh_atau_mendapatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("memperoleh")) {
                    TampilGambarKerja.setImageResource(R.drawable.menemukan_atau_memperoleh_atau_mendapatkan);
                    play_menemukan_atau_memperoleh_atau_mendapatkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("menentukan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menentukan_atau_mengkhususkan);
                    play_menentukan_atau_mengkhususkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("mengkhususkan")) {
                    TampilGambarKerja.setImageResource(R.drawable.menentukan_atau_mengkhususkan);
                    play_menentukan_atau_mengkhususkan();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tinggal")) {
                    TampilGambarKerja.setImageResource(R.drawable.tinggal);
                    play_tinggal();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("diam")) {
                    TampilGambarKerja.setImageResource(R.drawable.tinggal_atau_diam);
                    play_tinggal_atau_diam();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tuli")) {
                    TampilGambarKerja.setImageResource(R.drawable.tuli);
                    play_tuli();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("tumbuh")) {
                    TampilGambarKerja.setImageResource(R.drawable.tumbuh);
                    play_tumbuh();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("turun")) {
                    TampilGambarKerja.setImageResource(R.drawable.turun);
                    play_turun();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("pergi")) {
                    TampilGambarKerja.setImageResource(R.drawable.pergi);
                    play_pergi();
                } else if (IndonesiaKerja.getText().toString().equalsIgnoreCase("umroh"))                            //Akhiran Gambar Kata Kerja Dalam Al-Qur'an
                {
                    TampilGambarKerja.setImageResource(R.drawable.umroh);
                    play_umroh();
                } else {
                    Toast.makeText(BelajarKataKerjaActivity.this, "Data Belum Terdaftar!!", Toast.LENGTH_SHORT).show();
                    playsalah();
                }
            }
    });
}
    private void play_aman_atau_selamat() {
        player = MediaPlayer.create(this, R.raw.ular);                      //Awalan Suara Kata Kerja Dalam Al-Qur'an
        player.start();
    }

    private void play_aman_atau_tentram_atau_selamat_dari() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_baik_atau_lezat_atau_bahagia_atau_cukup() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_batal_atau_sia_sia_atau_hilang_atau_gagal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bebas_atau_bersih_atau_sembuh_atau_selamat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bebas_atau_terlepas_diri() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_belajar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_belas_kasihan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_benar_atau_jujur() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_benci() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbakti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbantah_atau_berdebat_dengan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbohong() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbuat_adil() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbuat_dengan_sukarela() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbuat_jahat_atau_merusak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berbuat_kebaikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bercekcok_atau_bertengkar_atau_bermusuhan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berdebat_atau_berbantah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berdiri_atau_bangkit_atau_bangun() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beredar_atau_berputar_atau_usang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berharap() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berhasil_atau_sukses_atau_beruntung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berhati_hati_atau_takut_atau_waspada() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berhijrah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berhutang_piutang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beribadah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beriman() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beristirahat_atau_berlindung_atau_tinggal_di() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beritikaf() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berjalan_kaki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berjanji_atau_bernadzar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berjudi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkata() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkata_atau_berbicara_kepada() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkata_keji_atau_berkata_kotor() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkembang_atau_sholeh_atau_baik() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkhianat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkhitbah_atau_meminang_atau_melamar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berkurang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berlaku_adil() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berlaku_munafik() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berlalu_atau_lewat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berlari_atau_mengalir() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berlindung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berniat_kuat_atau_bermaksud() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berpaling() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berpaling_atau_menguasai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berpegang_teguh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berputar_atau_berkeliling() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berselisih_atau_tidak_sepaham() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bersinar_atau_menyalakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bersungguh_sungguh_atau_berjuang_atau_berjihad_atau_semangat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bersyukur_atau_berterima_kasih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertahta_atau_menguasai_atau_sama() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertambah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertasbih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertaubat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertemu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bertolak_atau_meninggalkan_atau_menuangkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berubah_atau_basi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berumur_antara_30_50_tahun() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_beruntung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_berusaha() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bicara_yang_bukan_bukan_atau_keliru() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bingung_atau_terombang_ambing() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bisu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_bodoh_atau_tolol_atau_jelek_akhlaknya() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_buta() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_condong_atau_berpihak_atau_cenderung_kepada() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_condong_atau_miring_atau_cenderung_atau_bersandar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_cukup_atau_mencukupi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_datang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_datang_atau_tiba() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_duduk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_duduk_lalu_berdiri_atau_durhaka_atau_menentang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_enggan_atau_menjauhkan_diri_dari_yang_tidak_baik() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_fasiq() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_gagal_atau_hilang_semangat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_gagal_atau_putus_atau_harapan_atau_kecewa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_gampang_atau_mudah_atau_lemah_atau_hina() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_hadir_atau_datang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_haji() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_halal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_hampir() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_heran_atau_kagum_atau_takjub() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_hilang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_hina() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_jelas() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_jelas_atau_nyata_atau_tampak_atau_terang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_jelek_atau_buruk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_jelek_atau_buruk_atau_jahat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_jinak_atau_ramah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kafir_atau_ingkar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kasar_tutur_katanya_atau_kurang_ajar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kekal_atau_abadi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_keluar_atau_muncul() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kembali() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kembali_atau_pulang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_keras() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kokoh_atau_kuat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_konsisten_atau_menjadi_lurus_atau_tegak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kosong_atau_roboh_atau_runtuh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kosong_atau_selesai_atau_habis() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_kuat_atau_membantu_atau_menguatkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_lapar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_lari() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_lemah_lembut_atau_lemas_atau_lunak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_luas_atau_meliputi_atau_dapat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_lupa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_lupa_atau_lalai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_makan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_malu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mampu_atau_kuat_atau_kuasa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_marah_atau_membenci() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_masuk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mati_atau_binasa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mau_atau_ingin() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melaknati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melampaui() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melampaui_atau_melewati_batas() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melampaui_batas_atau_berlebihan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melanggar_atau_melampaui_batas() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melanggar_atau_mengkhianati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melanggar_atau_tidak_menepati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melangkah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melapangkan_atau_lepas() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melarang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melempar_atau_membuang_atau_mengesampingkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melepaskan_atau_mengeluarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melihat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melintasi_atau_menyebrangi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melipat_gandakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meliputi_atau_mengelilingi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_melukai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memaafkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memahami_atau_mengerti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memahami_atau_mengetahui_atau_cerdas_atau_pandai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memakaikan_pakaian() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memaksa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memancar_atau_mengalir() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memancar_atau_menyembur() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memanggil_atau_berteriak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memanggil_atau_mengundang_atau_meminta() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membahayakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membalas() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membalikkan_atau_merubah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membangkitkan_atau_mengutus_atau_mengirim() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membangun() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membayar_atau_mendorong_atau_menolak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membayar_zakat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mematikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membebani() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membebani_atau_mengalungkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membebankan_atau_menawarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membedakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membedakan_atau_memisahkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membela_atau_memperkuat_atau_mendukung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membelah_atau_memecahkan_atau_sulit() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membeli() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membeli_atau_menjual() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membelokkan_atau_memalingkan_atau_mengubah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membenarkan_atau_mempercayai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membentangkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membentangkan_atau_meluaskan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_hidayah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_infaq() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_izin() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_manfaat_atau_berfaedah_atau_berguna() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_nikmat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_peringatan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_rahmat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_rejeki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberi_syafaat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberikan_atau_kesaksian_atau_mengajukan_atau_mengemukakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberitahu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memberitahukan_atau_mengabarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membersihkan_atau_menguji_atau_menjauhkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membuat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membuat_marah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membuat_senang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membuktikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_membunuh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memecahkan_atau_mencincang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memeluk_islam_atau_tunduk_atau_patuh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memenuhi_janji() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memerdekakan_atau_menulis_atau_menerbitkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memfitnah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memilih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memiliki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memimpin_atau_mengetuai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meminjamkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meminta_atau_memohon_atau_bertanya() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meminta_dengan_memaksa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meminta_pertolongan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memisahkan_atau_membedakan_atau_mengistimewakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memisahkan_atau_menyapih_atau_keluar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memohon_pertolongan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memotong_atau_memenggal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memperhatikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memperingatkan_atau_menyuruh_berhati_hati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memperlihatkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memperoleh_atau_petunjuk_atau_menjadi_lurus_benar_atau_mencapai_kedewasaan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mempersiapkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memuji() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memukul_atau_mengetuk_atau_menimpakan_sesuatu_yang_menyakitkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_memusuhi_atau_memalingkan_atau_menganiaya_atau_melampaui() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menahan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menakut_nakuti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menanam_atau_membajak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menanggung_atau_mengurus_atau_mencukupi_nafkah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menanti_atau_tinggal_atau_berdiam_di_atau_menghindari() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menasehati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menaungi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencabut_atau_memecat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencampur_adukkan_atau_menjadikan_samar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencampurkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencari() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencari_atau_durhaka_atau_dengki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencari_atau_kemenangan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencari_atau_menyimpang_atau_durhaka() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencegah_atau_mengahalang_halangi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencemooh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menceritakan_atau_memberitahukan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menciduk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencipta() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menciptakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mencukur() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendahului() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendaki_atau_naik() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendapat_hidayah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendapatkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendapatkan_atau_menyusul_atau_mengikuti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendengarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendiktekan_atau_menjadikan_bosan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mendirikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menebus() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menempati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menemukan_atau_memperoleh_atau_mendapatkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menentukan_atau_mengkhususkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meneriaki_atau_mengeraskan_suara() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menerima() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menetap() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengabulkan_atau_menerima() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengajar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengajukan_atau_mendahulukan_atau_menyuguhkan_atau_menghanturkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengakhirkan_atau_menunda_atau_menangguhkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengakui_atau_menyatakan_atau_berikrar_atau_menetapkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengalahkan_atau_mengatasi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengalahkan_atau_tidak_memberi_pertolongan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengalahkan_dalam_perdebatan_atau_pertengkaran() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengambil_bekal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengangkat_atau_menaikkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menganugerahi_atau_memberi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengejek() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengelilingi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengelilingi_atau_mengembara() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengeluarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengembalikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengenakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengendalikan_atau_menundukkan_atau_mempekerjakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengepung_atau_mengelilingi_atau_mengurung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengeraskan_suara() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengerjakan_atau_meluruskan_atau_meratakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengerjakan_atau_menghabiskan_atau_membayar_atau_menghukum() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengetahui_atau_mengenal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengganti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggauli_atau_mengurus_atau_mengendalikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggelincirkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggembirakan_atau_menyembunyikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggenggam() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggigit_atau_berpegang_teguh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghadap() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menggoncangkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghalalkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghalangi_atau_mencegah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghapus_atau_menghilangkan_berkasnya_atau_membinasakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghapus_atau_menutup_atau_melenyapkan_jejak_bekas_atau_pergi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghapuskan_atau_membatalkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengharamkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghendaki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghias() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghidupkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menghinakan_atau_membinasakan_atau_merendahkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengikat_atau_membalut() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengikuti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengikutkan_atau_menyusuli_atau_datang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menginginkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengingkari_atau_menunda_atau_menangguhkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengkuasakan_atau_memberi_kekuasaan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menguji() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengumpulkan_atau_menghimpun() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengumpulkan_atau_menyatukan_atau_menggabungkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengurangi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengutamakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mengutuk_atau_melaknat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menikah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menimpa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meninggal_atau_mati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meninggalkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meninggalkan_atau_membiarkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menipu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menipu_atau_menggoda_atau_membujuk_atau_memperdayakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meniup_atau_memompa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadi_hitam() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadi_mandul_atau_melukai_atau_menyembelih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadi_mulia() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadi_putih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadi_putus_atau_berhenti() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadikan_hina() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjadikan_ragu_ragu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjaga_atau_melindungi_atau_mencegah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjanjikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjauhi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjauhkan_diri() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjawab() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjelaskan_atau_menafsirkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjelaskan_atau_menerangkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjinakkan_atau_mempersatukan_atau_menulis_atau_mengarang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menjual() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menolak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menolak_atau_enggan_atau_tidak_menyukai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menolak_atau_menyesatkan_atau_berpaling_atau_menghindar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menolong() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mensihir() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menstruasi_atau_keluar_darah_haid() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mensucikan_atau_membersihkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menukar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menukar_atau_merubah_atau_memindahkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menulis_atau_menetapkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menumpahkan_atau_menuangkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menundukkan_atau_membungkukkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menurunkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menutup() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyaksikan_atau_mengakui() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyalakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyambar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyampaikan_kabar_gembira() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyebut_atau_mengingat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyelamatkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyelamatkan_atau_mengucapkan_salam_atau_menyerahkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyelesaikan_atau_menyempurnakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyembelih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyembunyikan_atau_merahasiakan_atau_memadamkan_atau_meredakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyentuh_atau_menimpa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyerang_atau_membunuh_atau_menghancurkan_atau_mengalahkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyeru_atau_memanggil_atau_mengundang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyiapkan_atau_menyediakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyiarkan_atau_menyebarluaskan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyiksa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyimpang_atau_sesat_atau_bengkok_atau_kabur_atau_lemah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyinari() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyiram_atau_memberi_minum() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyucikan_atau_membersihkan_atau_memperbaiki() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyukai_atau_mencintai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyukai_atau_menyayangi_atau_menginginkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_menyusui() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_merasa_senang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_merasakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_merenggut_atau_merampas_atau_menyambar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meringankan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_merobohkan_atau_meruntuhkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_merusak() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mewarisi_harta() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mewarnai_atau_mencelup() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_meyakini() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_minta_bantuan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_mundur_karena_takut() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_murka_atau_marah() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_murni_atau_lepas_atau_dari_atau_selesai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_murtad_atau_kembali_atau_mundur_atau_mengembalikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_pergi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_pergi_atau_melakukan_perjalanan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_puasa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_ragu_atau_bingung() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_rela_atau_ridho() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_rendah_atau_hina() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_rugi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_rusak_atau_busuk_atau_buruk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sabar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sabar_atau_murah_hati() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sakit() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_saling_membantu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sampai_di_atau_mencapai_akil_baligh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sangat_kuning_atau_tumbuh_atau_bertambah_besar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_selamat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_senang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_senang_atau_bergembira() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sengsara_atau_binasa_atau_rusak_atau_remuk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_serupa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sesat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sholat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sholeh_atau_bagus_atau_baik_atau_sesuai() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_suci_atau_bersih() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_sulit_atau_sukar() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_taat_atau_patuh_atau_tunduk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_takut() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_takut_atau_bertaqwa() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tamak_atau_rakus() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tamak_atau_sangat_menginginkan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_teguh_pendirian() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tenang_atau_tentram() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tenggelam() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terbakar_atau_kebakaran() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terbalik_atau_berubah_atau_meliuk_liuk() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tercengang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tergelincir_atau_jatuh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tergesa_gesa_atau_cepat_cepat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_teringat_atau_merenungkan_atau_terkenang() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terlambat() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terlepas_atau_kosong_atau_menyendiri() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terputus() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tersedia() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tersembunyi_atau_tidak_hadir() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tertimpa_atau_gangguan_atau_merugikan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tertutup_atau_tersembunyi() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_terus_mengerjakan() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tetap_atau_kekal_atau_stabil() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tidak_tahu_atau_bodoh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tinggal() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tinggal_atau_diam() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tinggi_atau_mendaki_atau_melebihi_atau_sombong() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tuli() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tumbuh() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_tunduk_atau_khusyu() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_turun() {
        player = MediaPlayer.create(this, R.raw.ular);
        player.start();
    }

    private void play_umroh() {
        player = MediaPlayer.create(this, R.raw.ular);                      //Akhiran Suara Kata Kerja Dalam Al-Qur'an
        player.start();
    }

    private void playsalah() {
        player = MediaPlayer.create(this, R.raw.data_tidak_ada);
        player.start();
    }
    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_belajar_kerja);

        final Intent intent = getIntent();
        if ("Belajar".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(BelajarKataKerjaActivity.this, BelajarActivity.class);
        intent.putExtra("NIS", NIS);
        intent.putExtra("activity", "BelajarKataKerja");
        startActivity(intent);
    }

                private void checkPermission() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {

                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                            finish();
                        }

                    }
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