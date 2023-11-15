package com.example.sistemascasa.tigie.presentador;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Aladi;
import com.example.sistemascasa.tigie.pojo.Aladi;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.settings.IconosBandera;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by desarrolloweb on 18/08/16.
 */
public class RecyclerViewFragmentAladiPre implements IRecyclerViewFragmentAladiPre {
    private IRecyclerview_Aladi iRecyclerview_aladi;
    private ArrayList<Aladi> aladis;

    public RecyclerViewFragmentAladiPre(IRecyclerview_Aladi iRecyclerview_aladi, String email, String token, Integer id_fraccion, Integer valTigie) {
        this.iRecyclerview_aladi = iRecyclerview_aladi;
        getAladiWS(email, token, id_fraccion, valTigie);
    }

    @Override
    public void getAladiWS(String email, String token, Integer id_fraccion, Integer valTigie) {
        final IconosBandera iconosBandera   = new IconosBandera();

        try {
            RestApiAdapter restApiAdapter   = new RestApiAdapter();
            EndpointsService tlcService     = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<Aladi>> tlcCall  = tlcService.getAladi(id_fraccion, email, token, valTigie);

            tlcCall.enqueue(new Callback<ArrayList<Aladi>>() {
                @Override
                public void onResponse(Call<ArrayList<Aladi>> call, Response<ArrayList<Aladi>> response) {
                    try {
                        if (response.body() == null) {
                            mandarErrorAladi ();
                        } else {
                            int flagAladi = R.drawable.z_mundo2;
                            aladis = response.body();
                            if (aladis.size() > 0) {
                                for (Aladi aladi :aladis ) {
                                    if (aladi.getCountryCode() == null) {
                                        flagAladi = R.drawable.z_mundo2;
                                    } else {
                                        flagAladi = iconosBandera.IconosBandera(aladi.getCountryCode());
                                    }
                                    aladi.setFlagAladi(flagAladi);
                                }
                                showAladiDataRV();
                            }
                        }
                    } catch (IOError e) {
                        mandarErrorAladi ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Aladi>> call, Throwable t) {
                    mandarErrorAladi ();
                }
            });
        } catch (IOError e) {
            mandarErrorAladi ();
        }
    }

    @Override
    public void showAladiDataRV() {
        iRecyclerview_aladi.inicializarAdaptadorRV(iRecyclerview_aladi.crearAdaptador(aladis));
        iRecyclerview_aladi.generarLinearLayoutVertical();
    }

    public void mandarErrorAladi () {
        iRecyclerview_aladi.mandarErrorAladi();
    }

}
