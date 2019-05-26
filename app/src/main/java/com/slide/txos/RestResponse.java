package com.slide.txos;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

/**
 * Created by Oscar on 12/02/2018.
 * Esta clase recibe los RESPONSEs del server REST y procesa sus comandos.
 * NOTA: Los requests se realizan desde las clases de control de los activities.
 */

public class RestResponse {

    public void inicio(HashMap hMapFromJson, HashMap<String, Object> uiHashMap) {

        //obtiene el comando enviado por el server
        String comando = (String) hMapFromJson.get("comando");
        if (comando == null) {
            comando = "";
        }
        //----------------------------------------------------------------------recupera las UIs
        Context context= (Context) uiHashMap.get("context");
        ProgressBar progressBar=(ProgressBar)uiHashMap.get("progressBar");
        Button scanBarcodeButton =(Button)uiHashMap.get("scanBarcodeButton");
        TextView textView_result =(TextView)uiHashMap.get("textView_result");
        //---------------------------------------------comandos posibles enviados desde el server
        int duration = Toast.LENGTH_LONG;
        Toast toast;
        String mensaje="";

        boolean realizado=false;
        //no se utiliza switch/case por que se requieren tener las declaraciones encapsuladas y no generales
        //-----------------------------------------------------provenientes de

        if (comando.equalsIgnoreCase("test")) {
            String estado = (String) hMapFromJson.get("estado");
            //realiza acciones
            progressBar.setVisibility(View.INVISIBLE);
            textView_result.setText("test OK, estado="+estado);
            realizado=true;
        }

        if (comando.equalsIgnoreCase( "error")) {
            mensaje = (String) hMapFromJson.get("mensaje");
            //realiza una accion
            toast = Toast.makeText(context, mensaje, duration);
            toast.show();
            realizado=true;
        }

         if(realizado==false) {
             mensaje = ("Instruccion no encontrada '" + comando + "'");
             //muestra el mensaje en toast
             toast = Toast.makeText(context, mensaje, duration);
             toast.show();
         }
        }

    }

