package com.example.sistemascasa.tigie.presentador;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Alliance;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.settings.IconosBandera;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by desarrolloweb on 20/08/16.
 */
public class RecylcerviewFragmentAlliancePre implements IRecylcerviewFragmentAlliancePre {

    private IRecyclerview_Alliance iRecyclerview_alliance;
    private ArrayList<AlliancePacific> alliancePacifics;

    public RecylcerviewFragmentAlliancePre(IRecyclerview_Alliance iRecyclerview_alliance, String email, String token, Integer id_fraccion, Integer valTigie) {
        this.iRecyclerview_alliance = iRecyclerview_alliance;
        getAllianceWS(email, token, id_fraccion, valTigie);
    }

    @Override
    public void getAllianceWS (String email, String token, Integer id_fraccion, Integer valTigie) {
        final IconosBandera iconosBandera   = new IconosBandera();
        try {
            RestApiAdapter restApiAdapter       = new RestApiAdapter();
            EndpointsService tlcService         = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<AlliancePacific>> tlcCall = tlcService.getAlliancePacific(id_fraccion, email, token, valTigie);

            tlcCall.enqueue(new Callback<ArrayList<AlliancePacific>>() {
                @Override
                public void onResponse(Call<ArrayList<AlliancePacific>> call, Response<ArrayList<AlliancePacific>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError ();
                        } else {
                            alliancePacifics = response.body();
                            if (alliancePacifics.size() > 0) {
                                int flagAladi = R.drawable.paisesnodeclarados2;
                                for (AlliancePacific alliancePacific : alliancePacifics) {
                                    if(alliancePacific.getTlcPacificAllianceFreeCountryCode() == null) {
                                        flagAladi = R.drawable.paisesnodeclarados2;
                                    } else {
                                        flagAladi = iconosBandera.IconosBandera(alliancePacific.getTlcPacificAllianceFreeCountryCode());
                                    }
                                    alliancePacific.setTlcPacificAllianceFlag(flagAladi);
                                }
                                showTlcDataRV();
                            }
                        }
                    } catch (IOError e) {
                        sentMessageError ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<AlliancePacific>> call, Throwable t) {
                    sentMessageError ();
                }
            });
        } catch (IOError e) {
            sentMessageError ();
        }
    }

    @Override
    public void showTlcDataRV() {
        iRecyclerview_alliance.inicializarAdaptadorRV(iRecyclerview_alliance.crearAdaptador(alliancePacifics));
        iRecyclerview_alliance.generarLinearLayoutVertical();
    }

    public void sentMessageError () {
        iRecyclerview_alliance.mandarErrorAliance();
    }
}
