package com.project.belajarmengaji;

import java.util.Random;

public class PilganSifat {
    private int listsoalsifat[] = {
            R.drawable.soal_amanah,
            R.drawable.soal_bagus,
            R.drawable.soal_banyak,
            R.drawable.soal_basah,
            R.drawable.soal_benar,
            R.drawable.soal_bersih,
            R.drawable.soal_besar,
            R.drawable.soal_bodoh,
            R.drawable.soal_cepat,
            R.drawable.soal_dekat,
            R.drawable.soal_dingin,
            R.drawable.soal_egois,
            R.drawable.soal_gemuk,
            R.drawable.soal_halus,
            R.drawable.soal_jauh,
            R.drawable.soal_jelek,
            R.drawable.soal_jujur,
            R.drawable.soal_kasar,
            R.drawable.soal_kaya,
            R.drawable.soal_kecil,
            R.drawable.soal_kering,
            R.drawable.soal_khianat,
            R.drawable.soal_kotor,
            R.drawable.soal_kuat,
            R.drawable.soal_kurus,
            R.drawable.soal_lambat,
            R.drawable.soal_lemah,
            R.drawable.soal_luas,
            R.drawable.soal_lucu,
            R.drawable.soal_mahal,
            R.drawable.soal_malas,
            R.drawable.soal_miskin,
            R.drawable.soal_modern,
            R.drawable.soal_muda,
            R.drawable.soal_mulia,
            R.drawable.soal_murah,
            R.drawable.soal_panas,
            R.drawable.soal_pecah,
            R.drawable.soal_pelit,
            R.drawable.soal_pemarah,
            R.drawable.soal_pembohong,
            R.drawable.soal_pencela,
            R.drawable.soal_pendek,
            R.drawable.soal_pintar,
            R.drawable.soal_rajin,
            R.drawable.soal_rusak,
            R.drawable.soal_salah,
            R.drawable.soal_sedikit,
            R.drawable.soal_sempit,
            R.drawable.soal_sempurna,
            R.drawable.soal_sombong,
            R.drawable.soal_tebal,
            R.drawable.soal_tinggi,
            R.drawable.soal_tipis,
            R.drawable.soal_tua


    };

    private int listjawabansifat[] = {
            R.drawable.jawaban_amanah,
            R.drawable.jawaban_bagus,
            R.drawable.jawaban_banyak,
            R.drawable.jawaban_basah,
            R.drawable.jawaban_benar,
            R.drawable.jawaban_bersih,
            R.drawable.jawaban_besar,
            R.drawable.jawaban_bodoh,
            R.drawable.jawaban_cepat,
            R.drawable.jawaban_dekat,
            R.drawable.jawaban_dingin,
            R.drawable.jawaban_egois,
            R.drawable.jawaban_gemuk,
            R.drawable.jawaban_halus,
            R.drawable.jawaban_jauh,
            R.drawable.jawaban_jelek,
            R.drawable.jawaban_jujur,
            R.drawable.jawaban_kasar,
            R.drawable.jawaban_kaya,
            R.drawable.jawaban_kecil,
            R.drawable.jawaban_kering,
            R.drawable.jawaban_khianat,
            R.drawable.jawaban_kotor,
            R.drawable.jawaban_kuat,
            R.drawable.jawaban_kurus,
            R.drawable.jawaban_lambat,
            R.drawable.jawaban_lemah,
            R.drawable.jawaban_luas,
            R.drawable.jawaban_lucu,
            R.drawable.jawaban_mahal,
            R.drawable.jawaban_malas,
            R.drawable.jawaban_miskin,
            R.drawable.jawaban_modern,
            R.drawable.jawaban_muda,
            R.drawable.jawaban_mulia,
            R.drawable.jawaban_murah,
            R.drawable.jawaban_panas,
            R.drawable.jawaban_pecah,
            R.drawable.jawaban_pelit,
            R.drawable.jawaban_pemarah,
            R.drawable.jawaban_pembohong,
            R.drawable.jawaban_pencela,
            R.drawable.jawaban_pendek,
            R.drawable.jawaban_pintar,
            R.drawable.jawaban_rajin,
            R.drawable.jawaban_rusak,
            R.drawable.jawaban_salah,
            R.drawable.jawaban_sedikit,
            R.drawable.jawaban_sempit,
            R.drawable.jawaban_sempurna,
            R.drawable.jawaban_sombong,
            R.drawable.jawaban_tebal,
            R.drawable.jawaban_tinggi,
            R.drawable.jawaban_tipis,
            R.drawable.jawaban_tua

    };

    public int[] getListsoalsifat(){
        return listsoalsifat;
    }

    public int[] getListjawabansifat(){
        return listjawabansifat;
    }

    public int getrandomsifat() {
        int rndsifat = new Random().nextInt(listsoalsifat.length);
        return rndsifat;
    }

    public int getimagesoalsifat(int i) {
        return listsoalsifat[i];
    }

    public int getimagejawabansifat(int i) {
        return listjawabansifat[i];
    }

    public int getjumlahsifat() {
        return listsoalsifat.length;
    }

    public int getjumlahsifat2() {
        return listjawabansifat.length;
    }
}

