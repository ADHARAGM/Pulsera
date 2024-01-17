package com.ces.pulsera.data.remote.services;

import com.ces.pulsera.data.local.RequestVehiculo;
import com.ces.pulsera.data.local.ResponseVehiculo;
import com.ces.pulsera.data.remote.model.VehiculoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetVehiculoService {

    @GET("api/VehiculoRobadoAdhara/{tipo}/{placa}")
    //
    Call<VehiculoResponse> getVehiculo(@Path("tipo") int tipo, @Path("placa") String placa);
    /*@POST("/api/guardaAlerta")
    Call<ResponseVehiculo> getVehiculo(@Body RequestVehiculo requestVehiculo);
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
    */

}
