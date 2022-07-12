package com.project.belajarmengaji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class InsertData extends AppCompatActivity {

    TextInputEditText nis_siswa, nama_siswa, jenis_kelamin, email, password;
    Button btnbatal, btnsimpan;
    ProgressDialog pd;
    ProgressBar pb;
    MediaPlayer mp, suaraButton;
    ImageView tambahData, updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_nis_siswa = data.getStringExtra("nis_siswa");
        String intent_nama_siswa = data.getStringExtra("nama_siswa");
        String intent_jenis_kelamin = data.getStringExtra("jenis_kelamin");
        String intent_email = data.getStringExtra("email");
        String intent_password = data.getStringExtra("password");

        nis_siswa = findViewById(R.id.inp_nis_siswa);
        nama_siswa = findViewById(R.id.inp_nama_siswa);
        jenis_kelamin = findViewById(R.id.inp_jenis_kelamin);
        email = findViewById(R.id.inp_email);
        password = findViewById(R.id.inp_password);
        btnbatal = findViewById(R.id.btn_batal);
        btnsimpan = findViewById(R.id.btn_simpan);
        pb = findViewById(R.id.loading);
        pd = new ProgressDialog(InsertData.this);
        tambahData = findViewById(R.id.tambahSiswa);
        updateData = findViewById(R.id.updateSiswa);

        if (update == 1)
        {
            tambahData.setVisibility(View.GONE);
            updateData.setVisibility(View.VISIBLE);
            btnsimpan.setText("Update Data");
            nis_siswa.setText(intent_nis_siswa);
            nis_siswa.setVisibility(View.GONE);
            nama_siswa.setText(intent_nama_siswa);
            jenis_kelamin.setText(intent_jenis_kelamin);
            email.setText(intent_email);
            password.setText(intent_password);
        }

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                if (update == 1)
                {
                    updateData();
                }else {
                    simpanData();
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent admin = new Intent(InsertData.this, TampilanAdmin.class);
                startActivity(admin);
            }
        });
    }

    private void updateData()
    {
        pb.setVisibility(View.VISIBLE);
        btnsimpan.setVisibility(View.GONE);

        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();


        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(InsertData.this, TampilanAdmin.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Update Data", Toast.LENGTH_SHORT).show();
                        Log.e("volley","error : "+ error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nis_siswa",nis_siswa.getText().toString());
                map.put("nama_siswa",nama_siswa.getText().toString());
                map.put("jenis_kelamin",jenis_kelamin.getText().toString());
                map.put("email",email.getText().toString());
                map.put("password",password.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }

    private void simpanData()
    {
        pb.setVisibility(View.VISIBLE);
        btnsimpan.setVisibility(View.GONE);



        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(InsertData.this, TampilanAdmin.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Tambah Data", Toast.LENGTH_SHORT).show();
                        Log.e("volley","error : "+ error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nis_siswa",nis_siswa.getText().toString());
                map.put("nama_siswa",nama_siswa.getText().toString());
                map.put("jenis_kelamin",jenis_kelamin.getText().toString());
                map.put("email",email.getText().toString());
                map.put("password",password.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
    public void onBackPressed() {
        Intent intent = new Intent(InsertData.this,TampilanAdmin.class);
        startActivity(intent);
    }
}
