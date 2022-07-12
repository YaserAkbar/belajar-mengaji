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

public class SubmitNilaiBendaActivity extends AppCompatActivity {

    TextInputEditText etnisBenda, inpNilaiBenda;
    Button btnSubmitBenda;
    int skorbenda = 0, skorAkhirBenda, NIS, nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_nilai_benda);

        etnisBenda = findViewById(R.id.etnis_submit_benda);
        btnSubmitBenda = findViewById(R.id.btn_submit_benda);
        inpNilaiBenda = findViewById(R.id.etnilai_submit_benda);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        setNilai();

        btnSubmitBenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                submitNilai();
            }
        });

    }
    private void submitNilai(){
        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_NILAI_BENDA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(SubmitNilaiBendaActivity.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                            if (res.getString("message").equals("Update Berhasil")){
                                Intent intent = new Intent(SubmitNilaiBendaActivity.this,SkorAkhirActivity.class);
                                intent.putExtra("NIS", NIS);
                                intent.putExtra("activity", "SubmitNilaiBenda");
                                intent.putExtra("skorAkhirBenda", skorAkhirBenda);
                                intent.putExtra("activity", "SubmitNilaiBenda");
                                startActivity(intent);
                            }else {
                                Toast.makeText(SubmitNilaiBendaActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SubmitNilaiBendaActivity.this, "pesan : Gagal Submit Nilai", Toast.LENGTH_SHORT).show();
                        Log.e("volley","error : "+ error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nis_siswa",etnisBenda.getText().toString());
                map.put("nilai_siswa",inpNilaiBenda.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);

    }

    private void setNilai(){
        String activity = getIntent().getStringExtra("activity");

        final Intent intent = getIntent();
        if ("PilihanGandaBenda".equals(activity)) {
            skorAkhirBenda = intent.getIntExtra("skorAkhirBenda", 0);
            NIS = intent.getIntExtra("NIS", 0);
            inpNilaiBenda.setText(""+skorAkhirBenda);
            etnisBenda.setText(""+NIS);
            skorbenda = skorAkhirBenda;
            nis = NIS;
        }else {
            NIS = intent.getIntExtra("NIS", 0);
            etnisBenda.setText(""+NIS);
            nis = NIS;
        }
    }
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan submit nilai", Toast.LENGTH_SHORT).show();
    }
}
