package com.project.belajarmengaji;

import java.util.Random;

public class EssayKerja {
    public String soalessayKerja[]={
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


    private String listgambarsoalKerja[] = {
            "soal_belajar",
            "soal_berbohong",
            "soal_berhijrah",
            "soal_beribadah",
            "soal_beriman",
            "soal_berkata",
            "soal_bertasbih",
            "soal_bertaubat",
            "soal_bertemu",
            "soal_berusaha",
            "soal_duduk",
            "soal_lari",
            "soal_makan",
            "soal_masuk",
            "soal_melangkah",
            "soal_melarang",
            "soal_melihat",
            "soal_memaafkan",
            "soal_membaca",
            "soal_membalas",
            "soal_membedakan",
            "soal_membeli",
            "soal_memberi",
            "soal_memberitahu",
            "soal_membuat",
            "soal_memfitnah",
            "soal_memilih",
            "soal_memuji",
            "soal_mencari",
            "soal_mendahului",
            "soal_mendengarkan",
            "soal_mendirikan",
            "soal_menerima",
            "soal_mengajar",
            "soal_mengejek",
            "soal_mengeluarkan",
            "soal_mengenakan",
            "soal_mengganti",
            "soal_mengikuti",
            "soal_mengurangi",
            "soal_menikah",
            "soal_menjawab",
            "soal_menjual",
            "soal_menolak",
            "soal_menolong",
            "soal_menukar",
            "soal_menutup",
            "soal_merusak",
            "soal_pergi",
            "soal_puasa",
            "soal_sabar",
            "soal_sholat",
            "soal_tinggal"
    };


    private String listjawabanKerja[] = {
            "belajar",
            "berbohong",
            "berhijrah",
            "beribadah",
            "beriman",
            "berkata",
            "bertasbih",
            "bertaubat",
            "bertemu",
            "berusaha",
            "duduk",
            "lari",
            "makan",
            "masuk",
            "melangkah",
            "melarang",
            "melihat",
            "memaafkan",
            "membaca",
            "membalas",
            "membedakan",
            "membeli",
            "memberi",
            "memberitahu",
            "membuat",
            "memfitnah",
            "memilih",
            "memuji",
            "mencari",
            "mendahului",
            "mendengarkan",
            "mendirikan",
            "menerima",
            "mengajar",
            "mengejek",
            "mengeluarkan",
            "mengenakan",
            "mengganti",
            "mengikuti",
            "mengurangi",
            "menikah",
            "menjawab",
            "menjual",
            "menolak",
            "menolong",
            "menukar",
            "menutup",
            "merusak",
            "pergi",
            "puasa",
            "sabar",
            "sholat",
            "tinggal"
    };

    public String[] getListgambarsoalKerja(){
        return listgambarsoalKerja;
    }

    public String[] getListjawabanKerja(){
        return listjawabanKerja;
    }

    public String getrandomKerja(int i) {
        String rnd = listjawabanKerja[new Random(i).nextInt(listjawabanKerja.length)];
        return rnd;
    }

    public String getrandomsoalKerja(int i) {
        String rand = soalessayKerja[new Random(i).nextInt(soalessayKerja.length)];
        return rand;
    }

    public String getrandomgambarKerja(int i) {
        String random = listgambarsoalKerja[new Random(i).nextInt(listgambarsoalKerja.length)];
        return random;
    }

    public String getsoalKerja(int i){
        return soalessayKerja[i];
    }

    public String getGambarSoal(int i) {
        String gambar = listgambarsoalKerja[i];
        return gambar;
    }

    public String getjawaban(int i) {
        return listjawabanKerja[i];

    }

    public int getjumlah() {
        return soalessayKerja.length;
    }

    public int getjumlah2() {
        return listjawabanKerja.length;
    }
}
