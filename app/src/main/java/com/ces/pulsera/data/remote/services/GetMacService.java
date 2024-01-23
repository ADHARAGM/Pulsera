package com.ces.pulsera.data.remote.services;

import com.ces.pulsera.data.remote.model.MacResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetMacService {

    @GET("api/GetPersonaId/{mac}")
    //
    Call<MacResponse> getMacService( @Path("mac") String mac);
    /*@POST("/api/guardaAlerta")
    Call<ResponseVehiculo> getVehiculo(@Body RequestVehiculo requestVehiculo);
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
    */

}
