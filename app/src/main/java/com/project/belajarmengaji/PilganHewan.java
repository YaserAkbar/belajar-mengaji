package com.project.belajarmengaji;

import java.util.Random;

public class PilganHewan {
    private int listsoalhewan[] = {
            R.drawable.soal_anjing,
            R.drawable.soal_ayam,
            R.drawable.soal_babi,
            R.drawable.soal_badak,
            R.drawable.soal_bebek,
            R.drawable.soal_belalang,
            R.drawable.soal_beruang,
            R.drawable.soal_biawak,
            R.drawable.soal_bighal,
            R.drawable.soal_buaya,
            R.drawable.soal_burung,
            R.drawable.soal_burung_elang,
            R.drawable.soal_burung_gagak,
            R.drawable.soal_burung_puyuh,
            R.drawable.soal_cacing,
            R.drawable.soal_capung,
            R.drawable.soal_cicak,
            R.drawable.soal_domba,
            R.drawable.soal_gajah,
            R.drawable.soal_ikan,
            R.drawable.soal_ikan_paus,
            R.drawable.soal_jerapah,
            R.drawable.soal_kadal,
            R.drawable.soal_kalajengking,
            R.drawable.soal_kambing,
            R.drawable.soal_katak,
            R.drawable.soal_kecoa,
            R.drawable.soal_keledai,
            R.drawable.soal_kerbau,
            R.drawable.soal_kucing,
            R.drawable.soal_kuda,
            R.drawable.soal_kupu_kupu,
            R.drawable.soal_kura_kura,
            R.drawable.soal_kutu,
            R.drawable.soal_laba_laba,
            R.drawable.soal_lalat,
            R.drawable.soal_lebah,
            R.drawable.soal_macan,
            R.drawable.soal_kera,
            R.drawable.soal_nyamuk,
            R.drawable.soal_panda,
            R.drawable.soal_rayap,
            R.drawable.soal_rusa,
            R.drawable.soal_sapi,
            R.drawable.soal_semut,
            R.drawable.soal_singa,
            R.drawable.soal_srigala,
            R.drawable.soal_tikus,
            R.drawable.soal_ular,
            R.drawable.soal_unta


    };

    private int listjawabanhewan[] = {
            R.drawable.jawaban_anjing,
            R.drawable.jawaban_ayam,
            R.drawable.jawaban_babi,
            R.drawable.jawaban_badak,
            R.drawable.jawaban_bebek,
            R.drawable.jawaban_belalang,
            R.drawable.jawaban_beruang,
            R.drawable.jawaban_biawak,
            R.drawable.jawaban_bighal,
            R.drawable.jawaban_buaya,
            R.drawable.jawaban_burung,
            R.drawable.jawaban_burung_elang,
            R.drawable.jawaban_burung_gagak,
            R.drawable.jawaban_burung_puyuh,
            R.drawable.jawaban_cacing,
            R.drawable.jawaban_capung,
            R.drawable.jawaban_cicak,
            R.drawable.jawaban_domba,
            R.drawable.jawaban_gajah,
            R.drawable.jawaban_ikan,
            R.drawable.jawaban_ikan_paus,
            R.drawable.jawaban_jerapah,
            R.drawable.jawaban_kadal,
            R.drawable.jawaban_kalajengking,
            R.drawable.jawaban_kambing,
            R.drawable.jawaban_katak,
            R.drawable.jawaban_kecoa,
            R.drawable.jawaban_keledai,
            R.drawable.jawaban_kerbau,
            R.drawable.jawaban_kucing,
            R.drawable.jawaban_kuda,
            R.drawable.jawaban_kupu_kupu,
            R.drawable.jawaban_kura_kura,
            R.drawable.jawaban_kutu,
            R.drawable.jawaban_laba_laba,
            R.drawable.jawaban_lalat,
            R.drawable.jawaban_lebah,
            R.drawable.jawaban_macan,
            R.drawable.jawaban_kera,
            R.drawable.jawaban_nyamuk,
            R.drawable.jawaban_panda,
            R.drawable.jawaban_rayap,
            R.drawable.jawaban_rusa,
            R.drawable.jawaban_sapi,
            R.drawable.jawaban_semut,
            R.drawable.jawaban_singa,
            R.drawable.jawaban_srigala,
            R.drawable.jawaban_tikus,
            R.drawable.jawaban_ular,
            R.drawable.jawaban_unta
    };

    public int[] getListsoalhewan(){
        return listsoalhewan;
    }

    public int[] getListjawabanhewan(){
        return listjawabanhewan;
    }

    public int getrandomhewan() {
        int rnd = new Random().nextInt(listsoalhewan.length);
        return rnd;
    }

    public int getimagesoal(int i) {
        return listsoalhewan[i];
    }

    public int getimagejawaban(int i) {
        return listjawabanhewan[i];
    }

    public int getjumlah() { return listsoalhewan.length;
    }

    public int getjumlah2() {
        return listjawabanhewan.length;
    }
}

