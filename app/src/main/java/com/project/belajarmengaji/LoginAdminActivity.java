package com.project.belajarmengaji;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import static android.text.Html.fromHtml;

public class LoginAdminActivity extends AppCompatActivity {

    MediaPlayer mp, suaraButton;
    ProgressBar pbLoginAdmin;
    TextInputEditText etUsernameAdmin, etPasswordAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);

        final Button btnLoginAdmin;

        loginSiswa();

        pbLoginAdmin = findViewById(R.id.pb_login_admin);

        etUsernameAdmin = findViewById(R.id.inp_username_admin);
        etPasswordAdmin = findViewById(R.id.inp_password_admin);
        btnLoginAdmin = findViewById(R.id.btn_login_admin);

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                String usernameKey = etUsernameAdmin.getText().toString();
                String passwordKey = etPasswordAdmin.getText().toString();

                if (usernameKey.equals("admin") && passwordKey.equals("admin")) {
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAdminActivity.this, TampilanAdmin.class);
                    LoginAdminActivity.this.startActivity(intent);
                    finish();
                } else {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdminActivity.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
        });
    }

    private void loginSiswa(){
        TextView logSiswa = findViewById(R.id.login_siswa);
        logSiswa.setText(fromHtml("<font color='#ffffff'>Kembali. </font><font color='#0c0099'>Login Siswa</font>"));
        logSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdminActivity.this,LoginSiswaActivity.class);
                startActivity(intent);
            }
        });

        }

    public void onBackPressed() {
        Intent intent = new Intent(LoginAdminActivity.this,LoginSiswaActivity.class);
        startActivity(intent);
    }

    }
