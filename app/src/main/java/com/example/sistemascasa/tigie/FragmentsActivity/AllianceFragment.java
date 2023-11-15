package com.example.sistemascasa.tigie.FragmentsActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.AllianceAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Alliance;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;
import com.example.sistemascasa.tigie.presentador.IRecylcerviewFragmentAlliancePre;
import com.example.sistemascasa.tigie.presentador.RecylcerviewFragmentAlliancePre;
import com.example.sistemascasa.tigie.settings.Comunicador;

import java.util.ArrayList;


public class AllianceFragment extends Fragment implements IRecyclerview_Alliance {

    private Context context;
    private RecyclerView listAlliance;
    private IRecylcerviewFragmentAlliancePre presenter;
    TextView tvFraccionAllianceCode;

    Comunicador comunicador;

    public AllianceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v          = inflater.inflate(R.layout.fragment_alliance, container, false);
        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        tvFraccionAllianceCode = (TextView) v.findViewById(R.id.tvFraccionAllianceCode);
        tvFraccionAllianceCode.setText(fraccion);

        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        Integer valTigie = activity.getValTigie();

        listAlliance    = (RecyclerView) v.findViewById(R.id.rvAlliance);
        presenter       = new RecylcerviewFragmentAlliancePre(this, email, token, id_fraccion, valTigie);
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listAlliance.setLayoutManager(llm);
    }

    @Override
    public AllianceAdapter crearAdaptador(ArrayList<AlliancePacific> alliancePacifics) {
        AllianceAdapter adapter = new AllianceAdapter(alliancePacifics, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AllianceAdapter adapter) {
        listAlliance.setAdapter(adapter);
    }

    @Override
    public void mandarErrorAliance() {
        Toast.makeText(AllianceFragment.super.getContext() , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }
}
