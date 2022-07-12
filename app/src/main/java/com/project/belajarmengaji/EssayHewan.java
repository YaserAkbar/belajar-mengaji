package com.project.belajarmengaji;

import java.util.Random;

public class EssayHewan {
    public String soalessayHewan[]={
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?",
            "Apakah \n Terjemahan \n Dari Gambar di atas ?"

    };


    private String listgambarsoalhewan[] = {
            "soal_anjing",
            "soal_ayam",
            "soal_babi",
            "soal_badak",
            "soal_bebek",
            "soal_belalang",
            "soal_beruang",
            "soal_biawak",
            "soal_bighal",
            "soal_buaya",
            "soal_burung",
            "soal_burung_elang",
            "soal_burung_gagak",
            "soal_burung_puyuh",
            "soal_cacing",
            "soal_capung",
            "soal_cicak",
            "soal_domba",
            "soal_gajah",
            "soal_ikan",
            "soal_ikan_paus",
            "soal_jerapah",
            "soal_kadal",
            "soal_kalajengking",
            "soal_kambing",
            "soal_katak",
            "soal_kecoa",
            "soal_keledai",
            "soal_kerbau",
            "soal_kucing",
            "soal_kuda",
            "soal_kupu_kupu",
            "soal_kura_kura",
            "soal_kutu",
            "soal_laba_laba",
            "soal_lalat",
            "soal_lebah",
            "soal_macan",
            "soal_kera",
            "soal_nyamuk",
            "soal_panda",
            "soal_rayap",
            "soal_rusa",
            "soal_sapi",
            "soal_semut",
            "soal_singa",
            "soal_srigala",
            "soal_tikus",
            "soal_ular",
            "soal_unta"
    };


    private String listjawabanhewan[] = {
            "anjing",
            "ayam",
            "babi",
            "badak",
            "bebek",
            "belalang",
            "beruang",
            "biawak",
            "bighal",
            "buaya",
            "burung",
            "burung elang",
            "burung gagak",
            "burung puyuh",
            "cacing",
            "capung",
            "cicak",
            "domba",
            "gajah",
            "ikan",
            "ikan paus",
            "jerapah",
            "kadal",
            "kalajengking",
            "kambing",
            "katak",
            "kecoa",
            "keledai",
            "kerbau",
            "kucing",
            "kuda",
            "kupu-kupu",
            "kura-kura",
            "kutu",
            "laba-laba",
            "lalat",
            "lebah",
            "macan",
            "kera",
            "nyamuk",
            "panda",
            "rayap",
            "rusa",
            "sapi",
            "semut",
            "singa",
            "srigala",
            "tikus",
            "ular",
            "unta"

    };

    public String[] getListgambarsoalhewan(){
        return listgambarsoalhewan;
    }

    public String[] getListjawabanhewan(){
        return listjawabanhewan;
    }

    public String getrandomhewan(int i) {
        String rnd = listjawabanhewan[new Random(i).nextInt(listjawabanhewan.length)];
        return rnd;
    }

    public String getrandomsoalHewan(int i) {
        String rand = soalessayHewan[new Random().nextInt(soalessayHewan.length)];
        return rand;
    }

    public String getrandomgambarHewan(int i) {
        String random = listgambarsoalhewan[new Random(i).nextInt(listgambarsoalhewan.length)];
        return random;
    }

    public String getsoalHewan(int i){
        return soalessayHewan[i];
    }

    public String getGambarSoal(int i) {
        String gambar = listgambarsoalhewan[i];
        return gambar;
    }

    public String getjawaban(int i) {
        String jawaban = listjawabanhewan[i];
        return jawaban;

    }

    public int getjumlah() {
        return soalessayHewan.length;
    }

    public int getjumlah2() {
        return listjawabanhewan.length;
    }
}
