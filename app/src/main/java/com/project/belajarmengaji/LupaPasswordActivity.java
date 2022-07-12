package com.project.belajarmengaji;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.project.belajarmengaji.Util.ServerAPI;

import java.util.HashMap;
import java.util.Map;

public class LupaPasswordActivity extends AppCompatActivity {

    MediaPlayer mp, suaraButton;
    Button btnLupass;
    TextInputEditText nis, email;
    String Nis, Email;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        pb = findViewById(R.id.pb_lupass);
        btnLupass = findViewById(R.id.btn_lupass);
        nis = findViewById(R.id.inp_username_lupass);
        email = findViewById(R.id.inp_email_lupass);

        btnLupass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                submit();
            }
        });

    }
    private void submit() {
                Nis = nis.getText().toString();
                Email = email.getText().toString();

                requestQueue = Volley.newRequestQueue(this);
                stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_LUPASS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("SUKSES")){
                            Toast.makeText(getApplicationContext(), "Password berhasil terkirim cek email anda.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LupaPasswordActivity.this,LoginSiswaActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Password gagal terkirim.", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                    new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LupaPasswordActivity.this, "Mohon cek koneksi anda", Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("nis_siswa",Nis);
                        params.put("email",Email);

                        return params;
                    }
                };

                requestQueue.add(stringRequest);
    }
    public void onBackPressed() {
        Intent intent = new Intent(LupaPasswordActivity.this,LoginSiswaActivity.class);
        startActivity(intent);
    }
}
