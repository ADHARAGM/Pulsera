package com.ces.pulsera.data.services;

import com.ces.pulsera.data.pojo.GetStatusDispositivoResponse;
import com.ces.pulsera.data.pojo.MacResponse;
import com.ces.pulsera.data.pojo.PostUbicationResponse;
import com.ces.pulsera.data.pojo.RequestGuardaAlerta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetMacService {

    @GET("api/GetPersonaId/{mac}")
    //
    Call<MacResponse> getMacService( @Path("mac") String mac);
    @POST("api/PostAlerta")
    Call<MacResponse> setMacService(@Body RequestGuardaAlerta requestGuardaAlerta );
    //https://servicios.cesmorelos.gob.mx/Pulseras/api/GetStatusDispositivo/
    @GET ("api/GetStatusDispositivo/{id_persona}")
    Call<GetStatusDispositivoResponse> requestLocation(@Path("id_persona") String id_persona);
    @POST("api/PostUbicacion")
    Call<PostUbicationResponse> PostUbicacion(@Body RequestGuardaAlerta requestGuardaAlerta );
    /*@POST("/api/guardaAlerta")
    Call<ResponseVehiculo> getVehiculo(@Body RequestVehiculo requestVehiculo);
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
    */

}
