package com.slide.txos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Oscar on 24/05/2018.
 */

public class RestRequestList {

    HashMap<String, Object> uiHashMap;
    Class<? extends MainActivityControl> aClass;

    public RestRequestList(HashMap<String, Object> uiHashMap,Class<? extends MainActivityControl> aClass){
        this.aClass=aClass;
        this.uiHashMap=uiHashMap;
    }

    //lista de requests posibles
    //
    public void requestTest() {
        RestRequestSingleton classRestRequestSingleton = RestRequestSingleton.getInstance();
        JSONObject requestParams = new JSONObject();
        try {
            //parametros a ser enviados
            requestParams.put("comando", "test");// encabezado para command
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //envia el request y espera el response
        classRestRequestSingleton.restRequestResponse(aClass, requestParams, uiHashMap);
    }
}
