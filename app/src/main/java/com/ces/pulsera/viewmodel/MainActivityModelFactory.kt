package com.ces.pulsera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ces.pulsera.data.local.MacDatabase

class MainActivityModelFactory (
    private val macDatabase: MacDatabase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(macDatabase) as T
    }
}