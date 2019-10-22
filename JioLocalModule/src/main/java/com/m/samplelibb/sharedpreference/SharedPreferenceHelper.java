package com.m.samplelibb.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import com.m.samplelibb.BuildConfig;


public class SharedPreferenceHelper {

    public static final String PREF_NAME = BuildConfig.APPLICATION_ID;

    private static SharedPreferenceHelper instance;

    private SharedPreferences prefs;

    public SharedPreferenceHelper(Context appContext) {
        prefs = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferenceHelper(Context appContext, String prefName) {
        prefs = appContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    /**
     * Returns unique instance of SharedPreferenceHelper class
     * Throws illegalStateException if @link #initialize(Context) not called first
     *
     * @return unique SharedPrefsHelper instance
     */
    public static SharedPreferenceHelper getInstance() {

        if (instance == null) {
            throw new IllegalStateException(
                    "SharedPrefsHelper is not initialized, call initialize(applicationContext) " +
                            "static method first");
        }

        return instance;
    }

    /**
     * Creates a new instance of SharedPreferenceHelper if not created already
     * Should be called before accessing instance
     * Called in @Link #haygot.togyah.app.common.application.TopprApplication.onCreate()
     */
    public static synchronized void initialize(Context appContext) {
        if (instance == null) {
            instance = new SharedPreferenceHelper(appContext);
        }
    }

    private SharedPreferences getPrefs() {
        return prefs;
    }

    /**
     * Clears all data in SharedPreferences
     */
    public void clearPrefs() {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.clear();
        editor.commit();
    }

    public void removeKey(String key) {
        getPrefs().edit().remove(key).commit();
    }

    public boolean containsKey(String key) {
        return getPrefs().contains(key);
    }

    public String getString(String key, String defValue) {
        return getPrefs().getString(key, defValue);
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return getPrefs().getInt(key, defValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public long getLong(String key, long defValue) {
        return getPrefs().getLong(key, defValue);
    }

    public long getLong(String key) {
        return getLong(key, 0L);
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getPrefs().getBoolean(key, defValue);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public float getFloat(String key) {
        return getPrefs().getFloat(key, 0);
    }

    public float getFloat(String key, float defValue) {
        return containsKey(key) ? getFloat(key) : defValue;
    }

    public void setFloat(String key, Float value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putFloat(key, value);
        editor.commit();
    }

}
