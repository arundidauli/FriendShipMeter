package com.love2knot.friendshipmeter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.TextView;

import com.love2knot.friendshipmeter.R;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {


    private static Util prefs;
    private final SharedPreferences sharedPreferences;

    public static Util getInstance(Context context) {
        if (prefs == null) {
            prefs = new Util(context);
        }
        return prefs;
    }

    private Util(Context context) {
        this.sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
    }


    public void SetValue(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public void SetBValue(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public String GetValue(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }
        return null;
    }

    public boolean GetBValue(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public void ClearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();

    }

    public static String convertTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return formatter.format(new Date(Long.parseLong(time)));
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        return sdf.format(c.getTime());  // dt is now the new date
    }

    public static long getMilliSecond(String string_date) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        Date d = null;
        try {
            d = f.parse(string_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert d != null;
        return d.getTime();
    }
    public static void translate(Context context,TextView textView) {
        TranslateAPI translateAPI;
        if (Util.getInstance(context).GetValue("lang")!=null) {
            translateAPI = new TranslateAPI(
                    Languages.AUTO_DETECT,   //Source Language
                    Util.getInstance(context).GetValue("lang"),//Target Language
                    textView.getText().toString());


        }else {
            translateAPI = new TranslateAPI(
                    Languages.AUTO_DETECT,   //Source Language
                    Languages.HINDI,         //Target Language
                    textView.getText().toString());

        }
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                textView.setText(translatedText);
            }

            @Override
            public void onFailure(String ErrorText) {
            }
        });


    }

}
