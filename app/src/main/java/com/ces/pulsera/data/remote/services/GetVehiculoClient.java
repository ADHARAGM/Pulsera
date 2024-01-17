package com.ces.pulsera.data.remote.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetVehiculoClient {
    private static GetVehiculoClient instance=null;
    private GetVehiculoService getVehiculoService;
    private Retrofit retrofit;

    public GetVehiculoClient() {
       retrofit = new Retrofit.Builder()
               .baseUrl("https://servicios.cesmorelos.gob.mx/Vehiculo/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
        getVehiculoService= retrofit.create(GetVehiculoService.class);
    }
    //patron singleton
    public static GetVehiculoClient getInstance(){
        if(instance==null){
            instance= new GetVehiculoClient();
        }
        return instance;
    }

    public  GetVehiculoService getVehiculoService(){
        return getVehiculoService;
    }
}
