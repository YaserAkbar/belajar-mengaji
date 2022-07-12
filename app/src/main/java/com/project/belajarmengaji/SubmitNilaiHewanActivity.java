package com.project.belajarmengaji;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.project.belajarmengaji.Util.AppController;
import com.project.belajarmengaji.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SubmitNilaiHewanActivity extends AppCompatActivity {

    TextInputEditText etnis, inpNilai;
    Button btnSubmit;
    int skor = 0, skorAkhir, NIS, nis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_nilai_hewan);

        etnis = findViewById(R.id.etnis_submit);
        btnSubmit = findViewById(R.id.btn_submit_hewan);
        inpNilai = findViewById(R.id.etnilai_submit);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        setNilai();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                submitNilai();
            }
        });

    }
    private void submitNilai(){
            StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_NILAI_HEWAN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject res = new JSONObject(response);
                                Toast.makeText(SubmitNilaiHewanActivity.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                                if (res.getString("message").equals("Update Berhasil")){
                                    Intent intent = new Intent(SubmitNilaiHewanActivity.this,SkorAkhirActivity.class);
                                    intent.putExtra("NIS", NIS);
                                    intent.putExtra("activity", "SubmitNilaiHewan");
                                    intent.putExtra("skorAkhir", skorAkhir);
                                    intent.putExtra("activity", "SubmitNilaiHewan");
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(SubmitNilaiHewanActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SubmitNilaiHewanActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                            Log.e("volley","error : "+ error.getMessage());
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("nis_siswa",etnis.getText().toString());
                    map.put("nilai_siswa",inpNilai.getText().toString());

                    return map;
                }
            };

            AppController.getInstance().addToRequestQueue(updateReq);

    }

    private void setNilai(){
    String activity = getIntent().getStringExtra("activity");

    final Intent intent = getIntent();
        if ("PilihanGandaHewan".equals(activity)) {
        skorAkhir = intent.getIntExtra("skorAkhir", 0);
        NIS = intent.getIntExtra("NIS", 0);
        inpNilai.setText(""+skorAkhir);
        etnis.setText(""+NIS);
        skor = skorAkhir;
        nis = NIS;
    }else {
        NIS = intent.getIntExtra("NIS", 0);
        etnis.setText(""+NIS);
        nis = NIS;
        }
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan submit nilai", Toast.LENGTH_SHORT).show();
    }
}
