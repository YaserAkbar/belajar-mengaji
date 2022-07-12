package com.project.belajarmengaji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.project.belajarmengaji.Adapter.AdapterData;
import com.project.belajarmengaji.Model.ModelData;
import com.project.belajarmengaji.Util.AppController;
import com.project.belajarmengaji.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TampilanAdmin extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsert, btnDelete, btnLogout, btnNilai;
    ProgressDialog pd;
    MediaPlayer mp, suaraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_admin);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        mRecyclerview = findViewById(R.id.recyclerviewTemp);
        btnInsert = findViewById(R.id.btn_insert);
        btnDelete = findViewById(R.id.btn_delete_admin);
        btnLogout = findViewById(R.id.btn_logout);
        btnNilai = findViewById(R.id.btn_nilai);
        pd = new ProgressDialog(TampilanAdmin.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(TampilanAdmin.this,RecyclerView.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(TampilanAdmin.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(TampilanAdmin.this,InsertData.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent hapus = new Intent(TampilanAdmin.this,Delete.class);
                startActivity(hapus);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Toast.makeText(getApplicationContext(), "LOGOUT SUKSES", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(TampilanAdmin.this,LoginSiswaActivity.class);
                startActivity(logout);
            }
        });

        btnNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent logout = new Intent(TampilanAdmin.this, NilaiSiswa.class);
                startActivity(logout);
            }
        });

    }


    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());

                        for (int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setNis_siswa(data.getString("nis_siswa"));
                                md.setNama_siswa(data.getString("nama_siswa"));
                                md.setJenis_kelamin(data.getString("jenis_kelamin"));
                                md.setEmail(data.getString("email"));
                                md.setPassword(data.getString("password"));
                                mItems.add(md);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);

    }
    public void onBackPressed() {
        Toast.makeText(this, "Silahkan pilih tombol logout ! ! !", Toast.LENGTH_SHORT).show();
    }
}
