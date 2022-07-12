package com.project.belajarmengaji;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class EssayKerjaActivity extends AppCompatActivity {

    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    TextView soalKerja;
    ImageView soalGambarKerja;
    EditText jawabanessayKerja;
    ImageButton jwKerja;
    int skoressayKerja = 0;
    EssayKerja essayKerja = new EssayKerja();
    int i = 0, nis, NIS;

    String jawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_kerja);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soalKerja = findViewById(R.id.pertanyaanessayKerja);
        soalGambarKerja = findViewById(R.id.soalgambaressayKerja);
        jawabanessayKerja = findViewById(R.id.txtessayKerja);
        jwKerja = findViewById(R.id.btnJawab);

        ubahGambarKerja();
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
                    jawabanessayKerja.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });



        jwKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban();
            }
        });


        findViewById(R.id.btnMic).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        jawabanessayKerja.setHint("Hasil suara di sini");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        jawabanessayKerja.setText("");
                        jawabanessayKerja.setHint("Mendengarkan...");
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;
                }
                return false;
            }
        });
    }

    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_kuis_essay_kerja);

        final Intent intent = getIntent();
        if ("EssayKerja".equals(activity)) {
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
        jawabanessayKerja.setText(null);
        if (i >= 11) {
            Intent intent = new Intent(EssayKerjaActivity.this,SubmitNilaiEssayKerjaActivity.class);
            intent.putExtra("skorAkhirEssayKerja", skoressayKerja);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "EssayKerja");
            startActivity(intent);
        }else {
            soalKerja.setText(essayKerja.getrandomsoalKerja(i));
            ubahGambarKerja();
            jawaban = essayKerja.getrandomKerja(i);
        }
    }

    public void ubahGambarKerja(){
        Resources resources = getResources();
        String mPhotoKerja = essayKerja.getrandomgambarKerja(i);
        int resID = resources.getIdentifier(mPhotoKerja, "drawable", getPackageName());
        Drawable drawable = resources.getDrawable(resID);
        soalGambarKerja.setImageDrawable(drawable);
    }

    public void cekJawaban() {
        TextView tampilSkor = findViewById(R.id.skoressayKerja);

        if(!jawabanessayKerja.getText().toString().isEmpty()) {
            if(jawabanessayKerja.getText().toString().equalsIgnoreCase(jawaban)){
                MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
                skoressayKerja += 10;
                benar.start();
                i++;
                pindah();
            }else{
                MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
                salah.start();
                i++;
                pindah();
            }
            tampilSkor.setText("SKOR : " +skoressayKerja);
        }else{
            Toast.makeText(this, "Silahkan isi jawaban dulu!", Toast.LENGTH_SHORT).show();
            MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.salah);
            salah.start();
        }
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
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
}
