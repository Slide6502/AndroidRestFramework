package com.slide.txos;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.HashMap;

/**
 * Created by Oscar on 24/05/2018.
 */

public class MainActivityControl {

    Context context;
    ProgressBar progressBar;
    RestRequestList restRequestList;
    GuiComponentes guiComponentes;

    public MainActivityControl(HashMap<String, Object> uiHashMap){
        guiComponentes =new GuiComponentes();
        restRequestList =new RestRequestList(uiHashMap,this.getClass());
        context=(Context)uiHashMap.get("context");
        progressBar=(ProgressBar) uiHashMap.get("progressBar");
    }

    //------------------------botones
    public void buttonLeerQrOnClick() {
        progressBar.setVisibility(View.VISIBLE);
        guiComponentes.toastShow(context,"boton presionado");
        restRequestList.requestTest();
    }

    //-----------------------respuestas REST
    /*
    public void restResponse(String a){
        guiComponentes.toastShow(context,"Llego!!!");
        Log.d("MyApp","I am here");
    }
    */
public void restResponse(HashMap hMapFromJson){

    String enviado=(String)hMapFromJson.get("comando");
    String recibido=(String)hMapFromJson.get("estado");
    guiComponentes.toastShow(context,"Llego!!! enviado="+enviado+" recibido="+recibido);

}
}
