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

public class SubmitNilaiMakananMinumanActivity extends AppCompatActivity {

    TextInputEditText etnisMami, inpNilaiMami;
    Button btnSubmitMami;
    int skormami = 0, skorAkhirMami, NIS, nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_nilai_makanan_minuman);

        etnisMami = findViewById(R.id.etnis_submit_mami);
        btnSubmitMami = findViewById(R.id.btn_submit_mami);
        inpNilaiMami = findViewById(R.id.etnilai_submit_mami);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        setNilai();

        btnSubmitMami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                submitNilai();
            }
        });

    }
    private void submitNilai(){
        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_NILAI_MAMI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(SubmitNilaiMakananMinumanActivity.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                            if (res.getString("message").equals("Update Berhasil")){
                                Intent intent = new Intent(SubmitNilaiMakananMinumanActivity.this,SkorAkhirActivity.class);
                                intent.putExtra("NIS", NIS);
                                intent.putExtra("activity", "SubmitNilaiMakananMinuman");
                                intent.putExtra("skorAkhirMami", skorAkhirMami);
                                intent.putExtra("activity", "SubmitNilaiMakananMinuman");
                                startActivity(intent);
                            }else {
                                Toast.makeText(SubmitNilaiMakananMinumanActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SubmitNilaiMakananMinumanActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                        Log.e("volley","error : "+ error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nis_siswa",etnisMami.getText().toString());
                map.put("nilai_siswa",inpNilaiMami.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);

    }

    private void setNilai(){
        String activity = getIntent().getStringExtra("activity");

        final Intent intent = getIntent();
        if ("PilihanGandaMakananMinuman".equals(activity)) {
            skorAkhirMami = intent.getIntExtra("skorAkhirMami", 0);
            NIS = intent.getIntExtra("NIS", 0);
            inpNilaiMami.setText(""+skorAkhirMami);
            etnisMami.setText(""+NIS);
            skormami = skorAkhirMami;
            nis = NIS;
        }else {
            NIS = intent.getIntExtra("NIS", 0);
            etnisMami.setText(""+NIS);
            nis = NIS;
        }
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan submit nilai", Toast.LENGTH_SHORT).show();

    }
}
