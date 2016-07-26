package com.example.parul.twsearch;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by parul on 24/07/16.
 */
public class L {

    private static final int BUTTON_BACK_ID =11;

    public static void m(String message) {
        Log.d("VIVZ", "" + message);
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();

    }

    public static void T(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
    }
}
