package com.example.sistemascasa.tigie.FragmentsActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.ModuleAdapter;
import com.example.sistemascasa.tigie.pojo.ModulesData;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.Comunicador;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulesFragment extends Fragment {
    TextView tvFraccionModuleCode;
    private RecyclerView rvC;
    Comunicador comunicador;

    public ModulesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modules, container, false);

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        tvFraccionModuleCode = (TextView) v.findViewById(R.id.tvFraccionModuleCode);
        tvFraccionModuleCode.setText(fraccion);
        rvC = (RecyclerView) v.findViewById(R.id.rvModules);
        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        Integer valTigie = activity.getValTigie();

        getModulesWS(fraccion, email, token, id_fraccion, valTigie);
        return v;
    }

    public void getModulesWS (final String fraccion, String email, String token, final Integer id_fraccion, final Integer valTigie) {
        try {
            RestApiAdapter restApiAdapter       = new RestApiAdapter();
            EndpointsService tlcService         = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<ModulesData>> tlcCall = tlcService.getmodules(id_fraccion, email, token, valTigie);

            tlcCall.enqueue(new Callback<ArrayList<ModulesData>>() {
                @Override
                public void onResponse(Call<ArrayList<ModulesData>> call, Response<ArrayList<ModulesData>> response) {
                    try {
                        if (response.body() == null) {
                            mandarMensajeError();
                        } else {
                            ArrayList<ModulesData> modules = new ArrayList<ModulesData>();
                            for (int i = 0; i < response.body().size() ; i++) {
                                String module =  response.body().get(i).getModule();
                                String title  = response.body().get(i).getTitle();
                                int number    = response.body().get(i).getNumberElement();

                                int image = R.drawable.z_aduanas2;

                                if (response.body().get(i).getModule().equals("compensatoryshares")) {
                                    title = "Cuotas Compensatorias";
                                    image = R.drawable.z_aducuotas;
                                }

                                if (response.body().get(i).getModule().equals("prosec")) {
                                    image = R.drawable.z_aduanaprose;
                                }

                                if (response.body().get(i).getModule().equals("annexes")) {
                                    image = R.drawable.z_aduannex2;
                                }

                                if (response.body().get(i).getModule().equals("estimatedprices")) {
                                    image = R.drawable.z_aduestimated2;
                                }

                                if (response.body().get(i).getModule().equals("importpermits")) {
                                    image = R.drawable.z_aduimport;
                                }

                                if (response.body().get(i).getModule().equals("borderstrip")) {
                                    image = R.drawable.z_aduborde3;
                                }

                                if (response.body().get(i).getModule().equals("tlcnotes")) {
                                    image = R.drawable.z_adutlc;
                                }

                                if (response.body().get(i).getModule().equals("othertaxes")) {
                                    image = R.drawable.z_aduother;
                                }

                                if (response.body().get(i).getNumberElement() > 0) {
                                    modules.add(new ModulesData(module, number, title, image, fraccion, id_fraccion));
                                }
                            }
                            if (valTigie == 2020){
                                modules.add(new ModulesData("other", 0, "Correlación TIGIE 2012", R.drawable.z_aduanas2, fraccion, id_fraccion));
                            }
                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            rvC.setLayoutManager(llm);

                            ModuleAdapter adapter = new ModuleAdapter(modules, getActivity(), valTigie);
                            rvC.setAdapter(adapter);
                        }
                    } catch (IOError e) {
                        mandarMensajeError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<ModulesData>> call, Throwable t) {
                    mandarMensajeError();
                }
            });
        } catch (IOError e) {
            mandarMensajeError();
        }
    }

    public void mandarMensajeError() {
        Toast.makeText(ModulesFragment.super.getContext(), "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }
}
