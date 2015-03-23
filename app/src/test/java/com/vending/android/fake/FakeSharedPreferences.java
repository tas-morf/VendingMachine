package com.vending.android.fake;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implements the Shared Preferences interface by using a Map. Very useful for testing.
 */
public class FakeSharedPreferences implements SharedPreferences {

    private Map<String, Object> mMap = new HashMap<>();    

    @Override
    public Map<String, ?> getAll() {
        return mMap;
    }

    @Override
    public String getString(String key, String defValue) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValue;
        }
        return (String) mMap.get(key);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValues;
        }
        return (Set<String>) mMap.get(key);
    }

    @Override
    public int getInt(String key, int defValue) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValue;
        }
        return (int) result;
    }

    @Override
    public long getLong(String key, long defValue) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValue;
        }
        return (long) mMap.get(key);
    }

    @Override
    public float getFloat(String key, float defValue) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValue;
        }
        return (float) mMap.get(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        Object result = mMap.get(key);
        if(result == null) {
            return defValue;
        }
        return (boolean) mMap.get(key);
    }

    @Override
    public boolean contains(String key) {
        return mMap.containsKey(key);
    }

    @Override
    public Editor edit() {
        //use the simpler interface we have for putting
        return new FakeEditor();
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener listener) {
        //do nothing
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener listener) {
        //do nothing
    }

    private class FakeEditor implements Editor {

        @Override
        public Editor putString(String key, String value) {
            mMap.put(key, value);
            return this;
        }

        @Override
        public Editor putStringSet(String key, Set<String> values) {
            mMap.put(key, values);
            return this;
        }

        @Override
        public Editor putInt(String key, int value) {
            mMap.put(key, value);
            return this;
        }

        @Override
        public Editor putLong(String key, long value) {
            mMap.put(key, value);
            return this;
        }

        @Override
        public Editor putFloat(String key, float value) {
            mMap.put(key, value);
            return this;
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            mMap.put(key, value);
            return this;
        }

        @Override
        public Editor remove(String key) {
            mMap.remove(key);
            return this;
        }

        @Override
        public Editor clear() {
            mMap.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return true;
        }

        @Override
        public void apply() {

        }
    }
}
