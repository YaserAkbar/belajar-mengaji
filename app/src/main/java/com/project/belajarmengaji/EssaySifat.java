package com.project.belajarmengaji;

import java.util.Random;

public class EssaySifat {
    public String soalessaySifat[]={
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


    private String listgambarsoalSifat[] = {
            "soal_amanah",
            "soal_bagus",
            "soal_banyak",
            "soal_basah",
            "soal_benar",
            "soal_bersih",
            "soal_besar",
            "soal_bodoh",
            "soal_cepat",
            "soal_dekat",
            "soal_dingin",
            "soal_egois",
            "soal_gemuk",
            "soal_halus",
            "soal_jauh",
            "soal_jelek",
            "soal_jujur",
            "soal_kasar",
            "soal_kaya",
            "soal_kecil",
            "soal_kering",
            "soal_khianat",
            "soal_kotor",
            "soal_kuat",
            "soal_kurus",
            "soal_lambat",
            "soal_lemah",
            "soal_luas",
            "soal_lucu",
            "soal_mahal",
            "soal_malas",
            "soal_miskin",
            "soal_modern",
            "soal_muda",
            "soal_mulia",
            "soal_murah",
            "soal_panas",
            "soal_pecah",
            "soal_pelit",
            "soal_pemarah",
            "soal_pembohong",
            "soal_pencela",
            "soal_pendek",
            "soal_pintar",
            "soal_rajin",
            "soal_rusak",
            "soal_salah",
            "soal_sedikit",
            "soal_sempit",
            "soal_sempurna",
            "soal_sombong",
            "soal_tebal",
            "soal_tinggi",
            "soal_tipis",
            "soal_tua"
    };


    private String listjawabanSifat[] = {
            "amanah",
            "bagus",
            "banyak",
            "basah",
            "benar",
            "bersih",
            "besar",
            "bodoh",
            "cepat",
            "dekat",
            "dingin",
            "egois",
            "gemuk",
            "halus",
            "jauh",
            "jelek",
            "jujur",
            "kasar",
            "kaya",
            "kecil",
            "kering",
            "khianat",
            "kotor",
            "kuat",
            "kurus",
            "lambat",
            "lemah",
            "luas",
            "lucu",
            "mahal",
            "malas",
            "miskin",
            "modern",
            "muda",
            "mulia",
            "murah",
            "panas",
            "pecah",
            "pelit",
            "pemarah",
            "pembohong",
            "pencela",
            "pendek",
            "pintar",
            "rajin",
            "rusak",
            "salah",
            "sedikit",
            "sempit",
            "sempurna",
            "sombong",
            "tebal",
            "tinggi",
            "tipis",
            "tua"
    };

    public String[] getListgambarsoalSifat(){
        return listgambarsoalSifat;
    }

    public String[] getListjawabanSifat(){
        return listjawabanSifat;
    }

    public String getrandomSifat(int i) {
        String rnd = listjawabanSifat[new Random(i).nextInt(listjawabanSifat.length)];
        return rnd;
    }

    public String getrandomsoalSifat(int i) {
        String rand = soalessaySifat[new Random(i).nextInt(soalessaySifat.length)];
        return rand;
    }

    public String getrandomgambarSifat(int i) {
        String random = listgambarsoalSifat[new Random(i).nextInt(listgambarsoalSifat.length)];
        return random;
    }

    public String getsoalSifat(int i){
        return soalessaySifat[i];
    }

    public String getGambarSoal(int i) {
        String gambar = listgambarsoalSifat[i];
        return gambar;
    }

    public String getjawaban(int i) {
        return listjawabanSifat[i];

    }

    public int getjumlah() {
        return soalessaySifat.length;
    }

    public int getjumlah2() {
        return listjawabanSifat.length;
    }
}
