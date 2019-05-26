package com.slide.txos;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by Oscar on 25/05/2018.
 */

public class GuiComponentes {

    public void toastShow(Context context, String data) {
        Toast toast = Toast.makeText(context, data, Toast.LENGTH_SHORT);
        toast.show();
    }

}
