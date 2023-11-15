package com.example.sistemascasa.tigie.FragmentsActivity;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.TlcAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_tlc;
import com.example.sistemascasa.tigie.pojo.Tlc;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentTlcPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentTlcPre;
import com.example.sistemascasa.tigie.settings.Comunicador;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TlcFragmen extends Fragment implements IRecyclerview_tlc {
    private Context context;
    private RecyclerView listTlc;
    private IRecyclerViewFragmentTlcPre presenter;

    TextView tvFraccionTlcCode;
    Comunicador comunicador;

    public TlcFragmen () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v          = inflater.inflate(R.layout.fragment_tlc, container, false);
        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        Integer valTigie = activity.getValTigie();

        tvFraccionTlcCode = (TextView) v.findViewById(R.id.tvFraccionTlcCode);
        tvFraccionTlcCode.setText(fraccion);

        listTlc = (RecyclerView) v.findViewById(R.id.rvTlc);
        presenter = new RecyclerViewFragmentTlcPre(this, email, token, id_fraccion, valTigie);
        return v;
    }

    @Override
    public TlcAdapter crearAdaptador(ArrayList<Tlc> tlcs) {
        TlcAdapter adapter = new TlcAdapter(tlcs, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(TlcAdapter adapter) {
        listTlc.setAdapter(adapter);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        listTlc.setLayoutManager(glm);
    }

    @Override
    public void mandarError() {
        Toast.makeText(TlcFragmen.super.getContext() , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }
}
