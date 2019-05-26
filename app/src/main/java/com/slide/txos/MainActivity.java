package com.slide.txos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;

/*
Notas de diseno:
Volley y Gson se agregan directo desde gradle (module app) y se usan en RestRequestSingleton
La declaracion para permiso de internet se declara en androidManifest
La direccion ip donde se contactara la app se encuentra en RestRequestSingleton
 */
public class MainActivity extends AppCompatActivity {

    MainActivityControl mainActivityControl;

    //declaraciones de UI
    private Button scanBarcodeButton;
    private TextView textView_result;
    private ProgressBar progressBar;

    // otras declaraciones
    HashMap<String, Object> uiHashMap;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rescata widgets de UI
        context = getApplicationContext();
        textView_result=(TextView) findViewById(R.id.textView_result);
        scanBarcodeButton=(Button) findViewById(R.id.buttonLeerQr);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        //guarda los componentes del UI en un hashmap
        uiHashMap = new HashMap<>();
        uiHashMap.put("context", context);
        uiHashMap.put("scanBarcodeButton", scanBarcodeButton);
        uiHashMap.put("textView_result", textView_result);
        uiHashMap.put("progressBar", progressBar);

        mainActivityControl=new MainActivityControl(uiHashMap);
    }

    // todos los eventos son atrapados aqui y son enviados
    // a mainActivityControl en donde son procesados
    //--------------------Eventos-------------------------------------------------------------------
    public void buttonLeerQrOnClick(View view) {
        mainActivityControl.buttonLeerQrOnClick();
    }


}
