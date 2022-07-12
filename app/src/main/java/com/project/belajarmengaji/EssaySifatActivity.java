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

public class EssaySifatActivity extends AppCompatActivity {

    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    TextView soalSifat;
    ImageView soalGambarSifat;
    EditText jawabanessaySifat;
    ImageButton jwSifat;
    int skoressaySifat = 0;
    EssaySifat essaySifat = new EssaySifat();
    int i = 0, nis, NIS;

    String jawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_sifat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soalSifat = findViewById(R.id.pertanyaanessaySifat);
        soalGambarSifat = findViewById(R.id.soalgambaressaySifat);
        jawabanessaySifat = findViewById(R.id.txtessaySifat);
        jwSifat = findViewById(R.id.btnJawab);

        ubahGambarSifat();
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
                    jawabanessaySifat.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });



        jwSifat.setOnClickListener(new View.OnClickListener() {
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
                        jawabanessaySifat.setHint("Hasil suara di sini");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        jawabanessaySifat.setText("");
                        jawabanessaySifat.setHint("Mendengarkan...");
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
        tampilNis = findViewById(R.id.nis_menu_kuis_essay_sifat);

        final Intent intent = getIntent();
        if ("EssaySifat".equals(activity)) {
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
        jawabanessaySifat.setText(null);
        if (i >= 11) {
            Intent intent = new Intent(EssaySifatActivity.this,SubmitNilaiEssaySifatActivity.class);
            intent.putExtra("skorAkhirEssaySifat", skoressaySifat);
            intent.putExtra("NIS", NIS);
            intent.putExtra("activity", "EssaySifat");
            startActivity(intent);
        }else {
            soalSifat.setText(essaySifat.getrandomsoalSifat(i));
            ubahGambarSifat();
            jawaban = essaySifat.getrandomSifat(i);
        }
    }

    public void ubahGambarSifat(){
        Resources resources = getResources();
        String mPhotoSifat = essaySifat.getrandomgambarSifat(i);
        int resID = resources.getIdentifier(mPhotoSifat, "drawable", getPackageName());
        Drawable drawable = resources.getDrawable(resID);
        soalGambarSifat.setImageDrawable(drawable);
    }

    public void cekJawaban() {
        TextView tampilSkor = findViewById(R.id.skoressaySifat);

        if(!jawabanessaySifat.getText().toString().isEmpty()) {
            if(jawabanessaySifat.getText().toString().equalsIgnoreCase(jawaban)){
                MediaPlayer benar = MediaPlayer.create(getBaseContext(), R.raw.jawaban_benar);
                skoressaySifat += 10;
                benar.start();
                i++;
                pindah();
            }else{
                MediaPlayer salah = MediaPlayer.create(getBaseContext(), R.raw.jawaban_salah);
                salah.start();
                i++;
                pindah();
            }
            tampilSkor.setText("SKOR : " +skoressaySifat);
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
