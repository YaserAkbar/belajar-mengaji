package com.project.belajarmengaji;

import java.util.Random;

public class PilganKerja {
    private int listsoalkerja[] = {
            R.drawable.soal_belajar,
            R.drawable.soal_berbohong,
            R.drawable.soal_berhijrah,
            R.drawable.soal_beribadah,
            R.drawable.soal_beriman,
            R.drawable.soal_berkata,
            R.drawable.soal_bertasbih,
            R.drawable.soal_bertaubat,
            R.drawable.soal_bertemu,
            R.drawable.soal_berusaha,
            R.drawable.soal_duduk,
            R.drawable.soal_lari,
            R.drawable.soal_makan,
            R.drawable.soal_masuk,
            R.drawable.soal_melangkah,
            R.drawable.soal_melarang,
            R.drawable.soal_melihat,
            R.drawable.soal_memaafkan,
            R.drawable.soal_membaca,
            R.drawable.soal_membalas,
            R.drawable.soal_membedakan,
            R.drawable.soal_membeli,
            R.drawable.soal_memberi,
            R.drawable.soal_memberitahu,
            R.drawable.soal_membuat,
            R.drawable.soal_memfitnah,
            R.drawable.soal_memilih,
            R.drawable.soal_memuji,
            R.drawable.soal_mencari,
            R.drawable.soal_mendahului,
            R.drawable.soal_mendengarkan,
            R.drawable.soal_mendirikan,
            R.drawable.soal_menerima,
            R.drawable.soal_mengajar,
            R.drawable.soal_mengejek,
            R.drawable.soal_mengeluarkan,
            R.drawable.soal_mengenakan,
            R.drawable.soal_mengganti,
            R.drawable.soal_mengikuti,
            R.drawable.soal_mengurangi,
            R.drawable.soal_menikah,
            R.drawable.soal_menjawab,
            R.drawable.soal_menjual,
            R.drawable.soal_menolak,
            R.drawable.soal_menolong,
            R.drawable.soal_menukar,
            R.drawable.soal_menutup,
            R.drawable.soal_merusak,
            R.drawable.soal_pergi,
            R.drawable.soal_puasa,
            R.drawable.soal_sabar,
            R.drawable.soal_sholat,
            R.drawable.soal_tinggal


    };

    private int listjawabankerja[] = {
            R.drawable.jawaban_belajar,
            R.drawable.jawaban_berbohong,
            R.drawable.jawaban_berhijrah,
            R.drawable.jawaban_beribadah,
            R.drawable.jawaban_beriman,
            R.drawable.jawaban_berkata,
            R.drawable.jawaban_bertasbih,
            R.drawable.jawaban_bertaubat,
            R.drawable.jawaban_bertemu,
            R.drawable.jawaban_berusaha,
            R.drawable.jawaban_duduk,
            R.drawable.jawaban_lari,
            R.drawable.jawaban_makan,
            R.drawable.jawaban_masuk,
            R.drawable.jawaban_melangkah,
            R.drawable.jawaban_melarang,
            R.drawable.jawaban_melihat,
            R.drawable.jawaban_memaafkan,
            R.drawable.jawaban_membaca,
            R.drawable.jawaban_membalas,
            R.drawable.jawaban_membedakan,
            R.drawable.jawaban_membeli,
            R.drawable.jawaban_memberi,
            R.drawable.jawaban_memberitahu,
            R.drawable.jawaban_membuat,
            R.drawable.jawaban_memfitnah,
            R.drawable.jawaban_memilih,
            R.drawable.jawaban_memuji,
            R.drawable.jawaban_mencari,
            R.drawable.jawaban_mendahului,
            R.drawable.jawaban_mendengarkan,
            R.drawable.jawaban_mendirikan,
            R.drawable.jawaban_menerima,
            R.drawable.jawaban_mengajar,
            R.drawable.jawaban_mengejek,
            R.drawable.jawaban_mengeluarkan,
            R.drawable.jawaban_mengenakan,
            R.drawable.jawaban_mengganti,
            R.drawable.jawaban_mengikuti,
            R.drawable.jawaban_mengurangi,
            R.drawable.jawaban_menikah,
            R.drawable.jawaban_menjawab,
            R.drawable.jawaban_menjual,
            R.drawable.jawaban_menolak,
            R.drawable.jawaban_menolong,
            R.drawable.jawaban_menukar,
            R.drawable.jawaban_menutup,
            R.drawable.jawaban_merusak,
            R.drawable.jawaban_pergi,
            R.drawable.jawaban_puasa,
            R.drawable.jawaban_sabar,
            R.drawable.jawaban_sholat,
            R.drawable.jawaban_tinggal

    };

    public int[] getListsoalkerja(){
        return listsoalkerja;
    }

    public int[] getListjawabankerja(){
        return listjawabankerja;
    }

    public int getrandomkerja() {
        int rndkerja = new Random().nextInt(listsoalkerja.length);
        return rndkerja;
    }

    public int getimagesoalkerja(int i) {
        return listsoalkerja[i];
    }

    public int getimagejawabankerja(int i) {
        return listjawabankerja[i];
    }

    public int getjumlahkerja() {
        return listsoalkerja.length;
    }

    public int getjumlahkerja2() {
        return listjawabankerja.length;
    }
}

