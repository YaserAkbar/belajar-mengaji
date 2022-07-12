package com.project.belajarmengaji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import static android.text.Html.fromHtml;

public class LoginSiswaActivity extends AppCompatActivity {

    TextView tampilNIs;
    MediaPlayer mp, suaraButton;
    TextInputEditText etUsername, etPassword;
    Button btnLogin, btnLupaPassword;
    ProgressBar pbLogin;
    int NIS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);


        pbLogin = findViewById(R.id.pb_login);
        etUsername = findViewById(R.id.inp_username_login);
        etPassword = findViewById(R.id.inp_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnLupaPassword = findViewById(R.id.btn_lupa_password);
        tampilNIs = findViewById(R.id.tampil_nis);


        loginAdmin();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Login();
            }
        });

        btnLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(LoginSiswaActivity.this,LupaPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(getApplicationContext(), "Berhasil Login ! ! !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginSiswaActivity.this,MainActivity.class);
                            intent.putExtra("NIS", NIS);
                            intent.putExtra("activity", "LoginSiswa");
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "Login Gagal ! ! !", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "error : " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("UserName", etUsername.getText().toString().trim());
                params.put("Password", etPassword.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
        NIS = Integer.parseInt(etUsername.getText().toString());
        tampilNIs.setText("NIS Anda : "+NIS);
    }

    private void loginAdmin(){
        TextView logAdmin = findViewById(R.id.login_admin);
        logAdmin.setText(fromHtml("<font color='#ffffff'>Tambah Akun Siswa. </font><font color='#0c0099'>Login Admin</font>"));
        logAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSiswaActivity.this,LoginAdminActivity.class);
                startActivity(intent);
            }
        });


    }

    public void onBackPressed() {
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
