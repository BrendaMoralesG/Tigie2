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
import com.example.sistemascasa.tigie.adapter.AladiAdapter;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Aladi;
import com.example.sistemascasa.tigie.pojo.Aladi;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentAladiPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentAladiPre;
import com.example.sistemascasa.tigie.settings.Comunicador;

import java.util.ArrayList;


public class AladiFragment extends Fragment implements IRecyclerview_Aladi {

    private RecyclerView listAladi;
    private IRecyclerViewFragmentAladiPre presenter;
    Comunicador comunicador;
    TextView tvFraccionAladiCode;

    public AladiFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_aladi, container, false);

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        Integer valTigie = activity.getValTigie();

        tvFraccionAladiCode = (TextView) v.findViewById(R.id.tvFraccionAladiCode);
        tvFraccionAladiCode.setText(fraccion);

        listAladi = (RecyclerView) v.findViewById(R.id.rvAladi);
        presenter = new RecyclerViewFragmentAladiPre(this, email, token, id_fraccion, valTigie);
        return v;
    }

    @Override
    public AladiAdapter crearAdaptador(ArrayList<Aladi> aladis) {
        AladiAdapter adapter = new AladiAdapter(aladis, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AladiAdapter adapter) {
        listAladi.setAdapter(adapter);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listAladi.setLayoutManager(llm);
    }

    @Override
    public void mandarErrorAladi() {
        Toast.makeText(AladiFragment.super.getContext() , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }
}
