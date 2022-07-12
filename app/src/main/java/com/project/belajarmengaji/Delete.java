package com.project.belajarmengaji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.belajarmengaji.Util.AppController;
import com.project.belajarmengaji.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Delete extends AppCompatActivity {

    EditText deleteID;
    Button btnDelete, btnBatal;
    ProgressDialog pd;
    MediaPlayer mp, suaraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        deleteID = findViewById(R.id.nis_siswa_param);
        btnDelete = findViewById(R.id.btn_delete);
        btnBatal = findViewById(R.id.btn_batal_hapus);
        pd = new ProgressDialog(Delete.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                deleteData();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(Delete.this, TampilanAdmin.class);
                startActivity(intent);
            }
        });
    }


    private void deleteData()
    {
        pd.setMessage("Delete Data ....");
        pd.setCancelable(false);
        pd.show();

        StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);

                            Toast.makeText(Delete.this, "pesan : "+ res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(Delete.this, TampilanAdmin.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley","error : " + error.getMessage());
                        Toast.makeText(Delete.this, "pesan : Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nis_siswa",deleteID.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(delReq);

    }

    public void onBackPressed() {
        Intent intent = new Intent(Delete.this,TampilanAdmin.class);
        startActivity(intent);
    }
}
