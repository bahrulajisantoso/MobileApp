package com.example.mobileapp.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mobileapp.Model.DataLogin;

import java.util.HashMap;

public class SessionManager {

    private Context ctx;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String IS_LOGIN = "isLogin";
    private static final String ID_USER = "id_user";
    private static final String NAMA = "nama";
    private static final String EMAIL = "email";
    private static final String NO_HP = "noHp";
    private static final String TANGGAL_LAHIR = "tglLahir";
    private static final String JENIS_KELAMIN = "jenisKel";
    private static final String ALAMAT = "alamat";

    public SessionManager(Context context) {
        this.ctx = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(DataLogin dataLogin) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(ID_USER, dataLogin.getIdUser());
        editor.putString(NAMA, dataLogin.getNamaUser());
        editor.putString(EMAIL, dataLogin.getEmail());
        editor.putString(NO_HP, dataLogin.getNoHp());
        editor.putString(TANGGAL_LAHIR, dataLogin.getTglLahir());
        editor.putString(JENIS_KELAMIN, dataLogin.getJenisKelamin());
        editor.putString(ALAMAT, dataLogin.getAlamat());
        editor.commit();
    }

    public HashMap<String, String> getUserData() {
        HashMap<String, String> user = new HashMap<>();
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
        user.put(NO_HP, sharedPreferences.getString(NO_HP, null));
        user.put(TANGGAL_LAHIR, sharedPreferences.getString(TANGGAL_LAHIR, null));
        user.put(JENIS_KELAMIN, sharedPreferences.getString(JENIS_KELAMIN, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        return user;
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public static String getIDUser() {
        return ID_USER;

    }

    public static String getNAMA() {
        return NAMA;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getNoHp() {
        return NO_HP;
    }

    public static String getTanggalLahir() {
        return TANGGAL_LAHIR;
    }

    public static String getJenisKelamin() {
        return JENIS_KELAMIN;
    }

    public static String getALAMAT() {
        return ALAMAT;
    }
}
