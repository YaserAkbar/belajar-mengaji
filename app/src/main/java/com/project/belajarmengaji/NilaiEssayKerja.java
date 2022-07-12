package com.project.belajarmengaji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.project.belajarmengaji.Adapter.AdapterDataNilai;
import com.project.belajarmengaji.Model.ModelData;
import com.project.belajarmengaji.Util.AppController;
import com.project.belajarmengaji.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NilaiEssayKerja extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnKembali;
    ImageButton btnPindahKiri, btnPindahKanan;
    ProgressDialog pd;
    MediaPlayer mp, suaraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_essay_kerja);

        final MediaPlayer suaraButton = MediaPlayer.create(this, R.raw.button);
        btnPindahKiri = findViewById(R.id.kiri_essay_kerja);
        btnPindahKanan = findViewById(R.id.kanan_essay_kerja);
        btnKembali = findViewById(R.id.back_essay_kerja);
        mRecyclerView = findViewById(R.id.recyclerviewNilaiEssayKerja);
        pd = new ProgressDialog(NilaiEssayKerja.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(NilaiEssayKerja.this,RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new AdapterDataNilai(NilaiEssayKerja.this,mItems);
        mRecyclerView.setAdapter(mAdapter);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(NilaiEssayKerja.this, TampilanAdmin.class);
                startActivity(intent);
            }
        });

        btnPindahKiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(NilaiEssayKerja.this, NilaiEssayBenda.class);
                startActivity(intent);
            }
        });

        btnPindahKanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraButton.start();
                Intent intent = new Intent(NilaiEssayKerja.this, NilaiEssaySifat.class);
                startActivity(intent);
            }
        });
    }

    private void loadJson(){
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA_NILAI_ESSAY_KERJA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley", "response : " + response.toString());

                        for (int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md_nilai = new ModelData();
                                md_nilai.setNama_siswa(data.getString("nama_siswa"));
                                md_nilai.setNis_siswa(data.getString("nis_siswa"));
                                md_nilai.setNilai_siswa(data.getString("nilai_siswa"));
                                mItems.add(md_nilai);
                            } catch (JSONException e) {
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
    public void onBackPressed(){
        Intent intent = new Intent(NilaiEssayKerja.this,NilaiEssayBenda.class);
        startActivity(intent);

    }
}
