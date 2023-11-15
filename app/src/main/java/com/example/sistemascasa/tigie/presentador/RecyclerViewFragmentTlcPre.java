package com.example.sistemascasa.tigie.presentador;

import com.example.sistemascasa.tigie.fragments.IRecyclerview_tlc;
import com.example.sistemascasa.tigie.pojo.Tlc;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.settings.IconosBandera;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public class RecyclerViewFragmentTlcPre implements IRecyclerViewFragmentTlcPre {
    private IRecyclerview_tlc   iRecyclerview_tlc;
    private ArrayList<Tlc>      tlcs;
    private int                 tlcFlag;
    private String email, token;
    private Integer id_fraccion;
    private Integer valTigie;

    public RecyclerViewFragmentTlcPre(IRecyclerview_tlc iRecyclerview_tlc, String email, String token, Integer id_fraccion, Integer valTigie) {
        this.iRecyclerview_tlc = iRecyclerview_tlc;
        this.email = email;
        this.token = token;
        this.id_fraccion = id_fraccion;
        this.valTigie = valTigie;
        getTlcWS(id_fraccion, valTigie);
    }

    @Override
    public void getTlcWS(Integer id_fraccion, Integer valTigie) {
        final IconosBandera iconosBandera = new IconosBandera();

        try {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsService tlcService = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<Tlc>> tlcCall = tlcService.getTlc(id_fraccion, email, token, valTigie);
            tlcCall.enqueue(new Callback<ArrayList<Tlc>>() {
                @Override
                public void onResponse(Call<ArrayList<Tlc>> call, Response<ArrayList<Tlc>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            tlcs = response.body();
                            if (tlcs.size() > 0) {
                                for (Tlc tlc : tlcs) {
                                    tlcFlag  = iconosBandera.IconosBandera(tlc.getTlcCode());
                                    tlc.setTlcFlag(tlcFlag);
                                }
                                showTlcDataRV();
                            }
                        }
                    } catch (IOError e){
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Tlc>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (IOError e){
            sentMessageError();
        }
    }

    @Override
    public void showTlcDataRV() {
        iRecyclerview_tlc.inicializarAdaptadorRV(iRecyclerview_tlc.crearAdaptador(tlcs));
        iRecyclerview_tlc.generarGridLayout();
    }

    public void sentMessageError() {
        iRecyclerview_tlc.mandarError();
    }
}
