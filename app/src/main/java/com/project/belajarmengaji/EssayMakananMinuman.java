package com.project.belajarmengaji;

import java.util.Random;

public class EssayMakananMinuman {
    public String soalessayMakananMinuman[]={
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


    private String listgambarsoalMakananMinuman[] = {
            "soal_air",
            "soal_anggur",
            "soal_apel",
            "soal_bawang",
            "soal_bayam",
            "soal_belimbing",
            "soal_brokoli",
            "soal_bubur",
            "soal_cabe",
            "soal_daging",
            "soal_delima",
            "soal_durian",
            "soal_gandunm",
            "soal_garam",
            "soal_gula",
            "soal_jagung",
            "soal_jambu",
            "soal_jeruk",
            "soal_jus",
            "soal_kacang_tanah",
            "soal_kangkung",
            "soal_kedelai",
            "soal_kelapa",
            "soal_kentang",
            "soal_ketimun",
            "soal_kol",
            "soal_kopi",
            "soal_kue",
            "soal_kurma",
            "soal_lemon",
            "soal_mangga",
            "soal_manggis",
            "soal_melon",
            "soal_mie",
            "soal_nanas",
            "soal_nasi",
            "soal_pepaya",
            "soal_pisang",
            "soal_rambutan",
            "soal_roti",
            "soal_semangka",
            "soal_stroberi",
            "soal_susu",
            "soal_teh",
            "soal_telur",
            "soal_tepung",
            "soal_tin",
            "soal_tomat",
            "soal_umbi",
            "soal_wortel"
    };


    private String listjawabanMakananMinuman[] = {
            "air",
            "anggur",
            "apel",
            "bawang",
            "bayam",
            "belimbing",
            "brokoli",
            "bubur",
            "cabe",
            "daging",
            "delima",
            "durian",
            "gandunm",
            "garam",
            "gula",
            "jagung",
            "jambu",
            "jeruk",
            "jus",
            "kacang tanah",
            "kangkung",
            "kedelai",
            "kelapa",
            "kentang",
            "ketimun",
            "kol",
            "kopi",
            "kue",
            "kurma",
            "lemon",
            "mangga",
            "manggis",
            "melon",
            "mie",
            "nanas",
            "nasi",
            "pepaya",
            "pisang",
            "rambutan",
            "roti",
            "semangka",
            "stroberi",
            "susu",
            "teh",
            "telur",
            "tepung",
            "tin",
            "tomat",
            "umbi",
            "wortel"
    };

    public String[] getListgambarsoalMakananMinuman(){
        return listgambarsoalMakananMinuman;
    }

    public String[] getListjawabanMakananMinuman(){
        return listjawabanMakananMinuman;
    }

    public String getrandomMakananMinuman(int i) {
        String rnd = listjawabanMakananMinuman[new Random(i).nextInt(listjawabanMakananMinuman.length)];
        return rnd;
    }

    String getrandomsoalMakananMinuman(int i) {
        String rand = soalessayMakananMinuman[new Random(i).nextInt(soalessayMakananMinuman.length)];
        return rand;
    }

    String getrandomgambarMakananMinuman(int i) {
        String random = listgambarsoalMakananMinuman[new Random(i).nextInt(listgambarsoalMakananMinuman.length)];
        return random;
    }

    public String getsoalMakananMinuman(int i){
        return soalessayMakananMinuman[i];
    }

    public String getGambarSoal(int i) {
        String gambar = listgambarsoalMakananMinuman[i];
        return gambar;
    }

    public String getjawaban(int i) {
        return listjawabanMakananMinuman[i];

    }

    public int getjumlah() {
        return soalessayMakananMinuman.length;
    }

    public int getjumlah2() {
        return listjawabanMakananMinuman.length;
    }
}
