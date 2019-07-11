package com.contactdata.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.contactdata.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Utils {
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean hasData(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean hasData(List value) {
        return value != null && !value.isEmpty();
    }

}
