package com.ces.pulsera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ces.pulsera.data.local.ResponseMac;
import com.ces.pulsera.data.network.Resource;
import com.ces.pulsera.data.remote.services.MacRepository;

import java.util.List;

public class borrar extends ViewModel {
    List<ResponseMac> listMac;
/*private final LiveData<Resource<List<ResponseMac>>> sincronizarPulseraModel;
private MacRepository macRepository;
public borrar(String mac){
    macRepository = new MacRepository();
   // sincronizarPulseraModel= macRepository.getMacService();
}
public LiveData<Resource<List<ResponseMac>>> getMacpersona(){
    return sincronizarPulseraModel;
}
*/

}
