package com.ces.pulsera.data.remote.services;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.ces.pulsera.data.local.MacDao;
import com.ces.pulsera.data.local.MacPersonaDatabase;
import com.ces.pulsera.data.local.ResponseMac;
import com.ces.pulsera.data.network.NetworkBoundResource;
import com.ces.pulsera.data.network.Resource;
import com.ces.pulsera.data.remote.model.MacResponse;
import com.ces.pulsera.herramientas.BeaconReferenceApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MacRepository {
    private static MacRepository instance=null;
    private GetMacService getMacService;
    private Retrofit retrofit;
    private final MacDao macDao;

    public MacRepository() {
        MacPersonaDatabase macPersonaDatabase = Room.databaseBuilder(
                BeaconReferenceApplication.Companion.getContext(),
                MacPersonaDatabase.class,
                "personaDispositivo"
        ).build();

        macDao = macPersonaDatabase.getMacDao();

       retrofit = new Retrofit.Builder()
               .baseUrl("https://servicios.cesmorelos.gob.mx/Pulseras/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
        getMacService= retrofit.create(GetMacService.class);
    }

   public LiveData<Resource<List<ResponseMac>>> getMacpersona(String mac){
        //Tipo que devuelve ROOM(BD LOCAL), tipo que devuelve la API RETROFIT
        return new NetworkBoundResource<List<ResponseMac>, MacResponse>(){
            @Override
            public void saveCallResult(@NonNull MacResponse item) {
                //alamecena la respuesta del servidor a la base de datos local
                macDao.saveMac(item.getObjeto());
            }
            @NonNull
            @Override
            public LiveData<List<ResponseMac>> loadFromDb() {
                //metodo que duevulve los datos que dispongamos en room la base de datos local

                return macDao.loadMac();
            }

            @NonNull
            @Override
            public Call<MacResponse> createCall() {
                //obtiene los datos de la api de retrofit
                return getMacService.getMacService( mac);
            }
        }.getAsLiveData();
    }
  public static MacRepository getInstance(){
        if(instance==null){
            instance= new MacRepository();
        }
        return instance;
    }

    public GetMacService getMacService(){
        return getMacService;
    }
}
