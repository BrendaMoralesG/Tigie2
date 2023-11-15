package com.example.sistemascasa.tigie.FragmentsActivity;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.TranspacificAdapter;
import com.example.sistemascasa.tigie.pojo.Transpacific;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.Comunicador;
import com.example.sistemascasa.tigie.settings.IconosBandera;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TranspacificFragment extends Fragment {
    private Context context;

    public TranspacificFragment() {

    }
    RecyclerView rvC ;
    TextView tvFraccionTranspCode;
    Comunicador comunicador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transpacific, container, false);
        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();
        rvC = (RecyclerView) v.findViewById(R.id.rvTranpacific);

        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        Integer valTigie = activity.getValTigie();

        tvFraccionTranspCode = (TextView) v.findViewById(R.id.tvFraccionTranspCode);
        tvFraccionTranspCode.setText(fraccion);

        getTranspacificWs(id_fraccion, email, token, valTigie);
        return v;
    }

    public void getTranspacificWs (Integer id_fraccion, String email, String token, Integer valTigie) {
        try {
            RestApiAdapter restApiAdapter       = new RestApiAdapter();
            EndpointsService tlcService         = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<Transpacific>> tlcCall= tlcService.getTlcTranspacific(id_fraccion, email, token, valTigie);

            tlcCall.enqueue(new Callback<ArrayList<Transpacific>>() {
                @Override
                public void onResponse(Call<ArrayList<Transpacific>> call, Response<ArrayList<Transpacific>> response) {
                    try {
                        if (response.body() == null) {
                            mandarError ();
                        } else {
                            ArrayList<Transpacific> transpacifics = response.body();
                            if (transpacifics.size() > 0) {
                                final IconosBandera iconosBandera   = new IconosBandera();
                                int tranFlag  = R.drawable.z_mundo2;
                                for (Transpacific tran : transpacifics) {
                                    tranFlag  = iconosBandera.IconosBandera(tran.getTipTranspacificAFreeCountryCode());
                                    tran.setFlagIcon(tranFlag);
                                }
                                GridLayoutManager llm = new GridLayoutManager(getActivity(),2);
                                rvC.setLayoutManager(llm);

                                TranspacificAdapter adapter = new TranspacificAdapter(transpacifics);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e){
                        mandarError ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Transpacific>> call, Throwable t) {
                    mandarError ();
                }
            });
        } catch (IOError e) {
            mandarError ();
        }
    }

    public void mandarError() {
        Toast.makeText(TranspacificFragment.super.getContext() , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

}
