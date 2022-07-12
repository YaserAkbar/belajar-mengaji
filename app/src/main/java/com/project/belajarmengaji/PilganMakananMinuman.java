package com.project.belajarmengaji;

import java.util.Random;

public class PilganMakananMinuman {
    private int listsoalmakananminuman[] = {
            R.drawable.soal_air,
            R.drawable.soal_anggur,
            R.drawable.soal_apel,
            R.drawable.soal_bawang,
            R.drawable.soal_bayam,
            R.drawable.soal_belimbing,
            R.drawable.soal_brokoli,
            R.drawable.soal_bubur,
            R.drawable.soal_cabe,
            R.drawable.soal_daging,
            R.drawable.soal_delima,
            R.drawable.soal_durian,
            R.drawable.soal_gandum,
            R.drawable.soal_garam,
            R.drawable.soal_gula,
            R.drawable.soal_jagung,
            R.drawable.soal_jambu,
            R.drawable.soal_jeruk,
            R.drawable.soal_jus,
            R.drawable.soal_kacang_tanah,
            R.drawable.soal_kangkung,
            R.drawable.soal_kedelai,
            R.drawable.soal_kelapa,
            R.drawable.soal_kentang,
            R.drawable.soal_ketimun,
            R.drawable.soal_kol,
            R.drawable.soal_kopi,
            R.drawable.soal_kue,
            R.drawable.soal_kurma,
            R.drawable.soal_lemon,
            R.drawable.soal_mangga,
            R.drawable.soal_manggis,
            R.drawable.soal_melon,
            R.drawable.soal_mie,
            R.drawable.soal_nanas,
            R.drawable.soal_nasi,
            R.drawable.soal_pepaya,
            R.drawable.soal_pisang,
            R.drawable.soal_rambutan,
            R.drawable.soal_roti,
            R.drawable.soal_semangka,
            R.drawable.soal_stroberi,
            R.drawable.soal_susu,
            R.drawable.soal_teh,
            R.drawable.soal_telur,
            R.drawable.soal_tepung,
            R.drawable.soal_tin,
            R.drawable.soal_tomat,
            R.drawable.soal_umbi,
            R.drawable.soal_wortel


    };

    private int listjawabanmakananminuman[] = {
            R.drawable.jawaban_air,
            R.drawable.jawaban_anggur,
            R.drawable.jawaban_apel,
            R.drawable.jawaban_bawang,
            R.drawable.jawaban_bayam,
            R.drawable.jawaban_belimbing,
            R.drawable.jawaban_brokoli,
            R.drawable.jawaban_bubur,
            R.drawable.jawaban_cabe,
            R.drawable.jawaban_daging,
            R.drawable.jawaban_delima,
            R.drawable.jawaban_durian,
            R.drawable.jawaban_gandum,
            R.drawable.jawaban_garam,
            R.drawable.jawaban_gula,
            R.drawable.jawaban_jagung,
            R.drawable.jawaban_jambu,
            R.drawable.jawaban_jeruk,
            R.drawable.jawaban_jus,
            R.drawable.jawaban_kacang_tanah,
            R.drawable.jawaban_kangkung,
            R.drawable.jawaban_kedelai,
            R.drawable.jawaban_kelapa,
            R.drawable.jawaban_kentang,
            R.drawable.jawaban_ketimun,
            R.drawable.jawaban_kol,
            R.drawable.jawaban_kopi,
            R.drawable.jawaban_kue,
            R.drawable.jawaban_kurma,
            R.drawable.jawaban_lemon,
            R.drawable.jawaban_mangga,
            R.drawable.jawaban_manggis,
            R.drawable.jawaban_melon,
            R.drawable.jawaban_mie,
            R.drawable.jawaban_nanas,
            R.drawable.jawaban_nasi,
            R.drawable.jawaban_pepaya,
            R.drawable.jawaban_pisang,
            R.drawable.jawaban_rambutan,
            R.drawable.jawaban_roti,
            R.drawable.jawaban_semangka,
            R.drawable.jawaban_stroberi,
            R.drawable.jawaban_susu,
            R.drawable.jawaban_teh,
            R.drawable.jawaban_telur,
            R.drawable.jawaban_tepung,
            R.drawable.jawaban_tin,
            R.drawable.jawaban_tomat,
            R.drawable.jawaban_umbi,
            R.drawable.jawaban_wortel

    };

    public int[] getListsoalmakananminuman(){
        return listsoalmakananminuman;
    }

    public int[] getListjawabanmakananminuman(){
        return listjawabanmakananminuman;
    }

    public int getrandommakananminuman() {
        int rndmakananminuman = new Random().nextInt(listsoalmakananminuman.length);
        return rndmakananminuman;
    }

    public int getimagesoalmakananminuman(int i) {
        return listsoalmakananminuman[i];
    }

    public int getimagejawabanmakananminuman(int i) {
        return listjawabanmakananminuman[i];
    }

    public int getjumlahmakananminuman() {
        return listsoalmakananminuman.length;
    }

    public int getjumlahmakananminuman2() {
        return listjawabanmakananminuman.length;
    }
}

