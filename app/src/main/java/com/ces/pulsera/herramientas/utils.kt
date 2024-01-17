package com.ces.pulsera.herramientas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.ces.pulsera.vista.sincronizarPulsera


fun Context.toast(msg: String){
    Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
}
fun baseUrl(): String {
    val BASE_URL="https://api.themoviedb.org/3/movie/popular?language=en-US&page=1"
    return BASE_URL;
}

fun apiKey(): String {
    val BASE_URL="https://api.themoviedb.org/3/movie/popular?language=en-US&page=1"
    return BASE_URL;
}




//fun Context