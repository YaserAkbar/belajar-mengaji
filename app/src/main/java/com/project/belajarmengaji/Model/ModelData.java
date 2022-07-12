package com.project.belajarmengaji.Model;

public class ModelData {
    private String nis_siswa, nama_siswa, jenis_kelamin, email, password, nilai_siswa;

    public  ModelData(){}

    public ModelData(String nis_siswa, String nama_siswa, String jenis_kelamin, String email, String password, String nilai_siswa) {
        this.nis_siswa = nis_siswa;
        this.nama_siswa = nama_siswa;
        this.jenis_kelamin = jenis_kelamin;
        this.email = email;
        this.password = password;
        this.nilai_siswa = nilai_siswa;
    }

    public String getNis_siswa() {
        return nis_siswa;
    }

    public void setNis_siswa(String nis_siswa) {
        this.nis_siswa = nis_siswa;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getEmail(){ return email;}

    public void setEmail(String email){this.email = email;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNilai_siswa(){
        return nilai_siswa;}

    public void setNilai_siswa(String nilai_siswa){
        this.nilai_siswa = nilai_siswa;
    }
}