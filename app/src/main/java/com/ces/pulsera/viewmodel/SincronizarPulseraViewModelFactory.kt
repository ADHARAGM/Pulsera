package com.ces.pulsera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ces.pulsera.data.local.MacDatabase

class SincronizarPulseraViewModelFactory (
    private val macDatabase: MacDatabase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SincronizarPulseraViewModel(macDatabase) as T
    }
}