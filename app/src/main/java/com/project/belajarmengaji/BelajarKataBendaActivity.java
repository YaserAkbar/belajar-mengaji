package com.project.belajarmengaji;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.belajarmengaji.Util.ServerAPI;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class BelajarKataBendaActivity extends AppCompatActivity {

    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    EditText IndonesiaBenda;
    ImageView tampilGambarBenda;
    ImageButton kembali, keluar;
    Button tombolBenda;
    MediaPlayer player, mp;
    String kataBenda;
    int nis, NIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar_kata_benda);
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
                    IndonesiaBenda.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        tampilGambarBenda = findViewById(R.id.tampilgambarBenda);
        IndonesiaBenda = findViewById(R.id.txtindonesiaBenda);
        tombolBenda = findViewById(R.id.btnTerjemahBenda);
        kembali = findViewById(R.id.buttonBack);
        keluar = findViewById(R.id.buttonExit);
        player = new MediaPlayer();

        final JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        findViewById(R.id.btnmicBenda).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        IndonesiaBenda.setHint("Hasil suara di sini");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        IndonesiaBenda.setText("");
                        IndonesiaBenda.setHint("Mendengarkan...");
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
                Intent intent = new Intent(BelajarKataBendaActivity.this, BelajarActivity.class);
                intent.putExtra("NIS", NIS);
                intent.putExtra("activity", "BelajarKataBenda");
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

        tombolBenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    kataBenda();
            }
        });
    }

    private void setNis(){
        String activity = getIntent().getStringExtra("activity");
        TextView tampilNis;
        tampilNis = findViewById(R.id.nis_menu_belajar_benda);

        final Intent intent = getIntent();
        if ("Belajar".equals(activity)) {
            NIS = intent.getIntExtra("NIS", 0);
            tampilNis.setText("NIS Anda : "+NIS);
            nis = NIS;
        }
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

    public void kataBenda(){
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServerAPI.URL_BELAJAR_BENDA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//menaruh data JSON kedalam variabel JSON Object
                            JSONArray jsonArray = new JSONArray(response.toString());

                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonPost = jsonArray.getJSONObject(i);
//men set data ke dalam tampilan
                                if (!IndonesiaBenda.getText().toString().equalsIgnoreCase("")) {
                                if(IndonesiaBenda.getText().toString().equalsIgnoreCase(kataBenda = jsonPost.getString("nama_kata"))) {
                                    Picasso.with(BelajarKataBendaActivity.this)
                                            .load(jsonPost.getString("link_gambar"))
                                            .into(tampilGambarBenda);
                                    try {
                                        mp.setDataSource(jsonPost.getString("link_suara"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        mp.prepare(); // might take long! (for buffering, etc)
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    mp.start();
                                }
                                }else {
                                        Toast.makeText(BelajarKataBendaActivity.this, "Silahkan isi dulu ! ! !", Toast.LENGTH_SHORT).show();
                                    }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        queue.add(stringRequest);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BelajarKataBendaActivity.this, BelajarActivity.class);
        intent.putExtra("NIS", NIS);
        intent.putExtra("activity", "BelajarKataBenda");
        startActivity(intent);
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
