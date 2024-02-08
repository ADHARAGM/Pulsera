package com.ces.pulsera.data.services;

import com.ces.pulsera.data.pojo.MacResponse;
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
    /*@POST("/api/guardaAlerta")
    Call<ResponseVehiculo> getVehiculo(@Body RequestVehiculo requestVehiculo);
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
    */

}
