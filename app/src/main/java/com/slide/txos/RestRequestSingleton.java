package com.slide.txos;

import android.content.Context;
import android.widget.Toast;

//se implemento volley 1.1.1 y gson 2.8.2 mediante gradle (module app)
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
/**
 * Created by Oscar on 24/05/2018.
 * NO REQUIERE DE CAMBIOS
 */

public class RestRequestSingleton {

    private String mJSONURLString = "http://192.168.0.2:8001";
    int duration = Toast.LENGTH_SHORT;
    Toast toast;


    //Singleton
    private static RestRequestSingleton instance = null;
    protected RestRequestSingleton() {
        // Exists only to defeat instantiation.
    }
    public static RestRequestSingleton getInstance() {
        if(instance == null) {
            instance = new RestRequestSingleton();
        }
        return instance;
    }
    //

    public void restRequestResponse(final Class<? extends MainActivityControl> aClass,
                                    JSONObject requestParams,
                                    final HashMap<String,Object> uiHashMap){
        //final String activityName=clase.getName();

        final Context context=(Context)uiHashMap.get("context");
        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                mJSONURLString,
                requestParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //envia la respuesta a la clase xxxActivityRestResponse, la que determina que hacer con la misma.
                        String jSonS = response.toString();
                        Gson gson = new Gson();
                        //convierte el json en hash map
                        HashMap hMapFromJson = gson.fromJson(jSonS, HashMap.class);
                        boolean enviado=false;

                        RestInterface restInterface;
                        //envia a la clase xxxActivityRestResponse segun su activity de origen
                       // if (activityName.equalsIgnoreCase("MainActivity")){
                            enviado=true;

// parte clave del software, que devuelve al metodo que genero el request
                        try {
                            //llama a la clase que genero el request
                            Class[] cArg = new Class[1];
                            cArg[0] = HashMap.class;
                            Object obj = aClass.getDeclaredConstructor(cArg).newInstance(uiHashMap);

                            //llama al metodo restResponse de la clase que genero el request
                            Class[] cAr = new Class[1];
                            cAr[0] = HashMap.class;
                            Method lMethod = aClass.getDeclaredMethod("restResponse",cAr);

                            final Object a = lMethod.invoke(obj,hMapFromJson);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        String text ="ErrorResponse: "+error.toString()+ " ErrMensaje: "+error.getMessage() ;
                        toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                }
        );

        //extendiendo el tiempo de timeout de volley ya que era muy corto (13 segs)
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

}
