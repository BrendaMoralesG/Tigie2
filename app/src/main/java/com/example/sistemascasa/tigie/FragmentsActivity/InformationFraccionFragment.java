package com.example.sistemascasa.tigie.FragmentsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.CorrelacionFraccAdapter;
import com.example.sistemascasa.tigie.pojo.InfFRaction;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.Comunicador;

import java.io.IOError;
import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFraccionFragment extends Fragment {
    TextView fracCode;
    TextView fracDesc;
    TextView fracChapter;
    TextView fracChapterDesc;
    TextView fracHeading;
    TextView fracHeadingDesc;
    TextView fracSubHeading;
    TextView fracSubHeadingDesc;
    TextView tvImpImpCode;
    TextView tvImpExpCode;
    TextView tvUnidadCode;
    TextView iepsExpo;
    TextView iepsExpoCode;
    TextView iepsImpo;
    TextView iepsImpoCode;
    TextView isan;
    TextView isanCode;
    TextView tvIva;
    TextView tvIvaCode;
    TextView isan1;
    TextView iepsExpo1;
    TextView tvNicoDesc;
    TextView tvCorrelacionCodigo;
    TextView tvTigieCorr;
    RelativeLayout relativeCorr, relativeNico, rlCorrFrac;
    Comunicador comunicador;
    RelativeLayout relativePrincipal;
    View fillInfFrac2;
    RecyclerView rvCorrFracc;
    View viewSeprCorrFrac;

    public InformationFraccionFragment( ) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_information_fraccion, container, false);

        fracCode               = (TextView)  v.findViewById(R.id.fracCode);
        fracDesc               = (TextView)  v.findViewById(R.id.fracDesc);
        fracChapter            = (TextView)  v.findViewById(R.id.fracChapter);
        fracChapterDesc        = (TextView)  v.findViewById(R.id.fracChapterDesc);
        fracHeading            = (TextView)  v.findViewById(R.id.fracHeading);
        fracHeadingDesc        = (TextView)  v.findViewById(R.id.fracHeadingDesc);
        fracSubHeading         = (TextView)  v.findViewById(R.id.fracSubHeading);
        fracSubHeadingDesc     = (TextView)  v.findViewById(R.id.fracSubHeadingDesc);
        tvNicoDesc             = (TextView)  v.findViewById(R.id.tvNicoDesc);
        tvCorrelacionCodigo    = (TextView)  v.findViewById(R.id.tvCorrelacionCodigo);

        tvImpImpCode    = (TextView)  v.findViewById(R.id.tvImpImpCode);
        tvImpExpCode    = (TextView)  v.findViewById(R.id.tvImpExpCode);
        tvUnidadCode    = (TextView)  v.findViewById(R.id.tvUnidadCode);
        iepsExpo        = (TextView)  v.findViewById(R.id.iepsImpo);
        iepsExpoCode    = (TextView)  v.findViewById(R.id.iepsExpoCode1);
        rvCorrFracc     = (RecyclerView) v.findViewById(R.id.rvCorrFracc);
        tvTigieCorr     = (TextView)  v.findViewById(R.id.tvTigieCorr);

        iepsImpo        = (TextView)  v.findViewById(R.id.iepsImpo);
        iepsImpoCode    = (TextView)  v.findViewById(R.id.iepsImpoCode1);
        isan            = (TextView)  v.findViewById(R.id.isan1);
        isanCode        = (TextView)  v.findViewById(R.id.isanCode1);
        tvIva           = (TextView)  v.findViewById(R.id.tvIva);
        tvIvaCode       = (TextView)  v.findViewById(R.id.tvIvaCode);

        relativeCorr    = (RelativeLayout)  v.findViewById(R.id.relativeCorr);
        relativeNico    = (RelativeLayout)  v.findViewById(R.id.relativeNico);
        rlCorrFrac      = (RelativeLayout)  v.findViewById(R.id.rlCorrFracc);
        viewSeprCorrFrac= (View)            v.findViewById(R.id.viewSeprCorrFrac);

        fillInfFrac2       = (View)  v.findViewById(R.id.fillInfFrac2);
        relativePrincipal  = (RelativeLayout) v.findViewById(R.id.relativePrincipal);
        isan1              = (TextView)  v.findViewById(R.id.isan1);
        iepsExpo1          = (TextView)  v.findViewById(R.id.iepsExpo1);

        comunicador     = (Comunicador) getActivity();
        TextView fracc  = (TextView) getActivity().findViewById(R.id.tvFraccion);
        String fraccion = fracc.getText().toString();

        final FractionInformationActivity activity = (FractionInformationActivity) getActivity();
        String email = activity.getEmail();
        String token = activity.getToken();
        Integer id_fraccion  = activity.getIdFraccion();
        final Integer valTigie = activity.getValTigie();

        RestApiAdapter      restApiAdapter  = new RestApiAdapter();
        EndpointsService    endService      = restApiAdapter.establecerConexionRestApi();
        Call<InfFRaction>   service         = endService.getInformationData(id_fraccion, fraccion, email, token, valTigie);

        service.enqueue(new Callback<InfFRaction>() {
            @Override
            public void onResponse(Call<InfFRaction> call, Response<InfFRaction> response) {
                try {
                    if (response.body() == null) {
                        sentMessageError();
                    } else {
                        InfFRaction infFRaction = response.body();
                        fracChapter.setText(infFRaction.getTariffChapterCode());
                        fracHeading.setText(infFRaction.getTariffHeadingCode());
                        fracSubHeading.setText(infFRaction.getTariffSubheadingCode());
                        fracCode.setText(infFRaction.getTariffFractionCode());
                        tvImpImpCode.setText(infFRaction.getImpuestoGeneralDeImpo());
                        tvImpExpCode.setText(infFRaction.getImpuestoGeneralDeExpo());
                        tvUnidadCode.setText(infFRaction.getUnidadDeMedida());
                        tvIvaCode.setText(infFRaction.getIvaRateAmountCode());
                        if(infFRaction.getIvaRateAmountCode().isEmpty()){
                            tvIva.setVisibility(View.INVISIBLE);
                        }

                        if (infFRaction.getCorrelaciones2020() != null) {
                            if (infFRaction.getCorrelaciones2020().size() > 0) {
                                rlCorrFrac.setVisibility(View.VISIBLE);
                                viewSeprCorrFrac.setVisibility(View.VISIBLE);
                                relativeCorr.setVisibility(View.GONE);
                                relativeNico.setVisibility(View.GONE);

                                GridLayoutManager ly = new GridLayoutManager(getContext(), 1);
                                CorrelacionFraccAdapter adapter = new CorrelacionFraccAdapter(infFRaction.getCorrelaciones2020(), activity);
                                rvCorrFracc.setLayoutManager(ly);
                                rvCorrFracc.setAdapter(adapter);
                            }
                        } else {
                            rlCorrFrac.setVisibility(View.GONE);
                            viewSeprCorrFrac.setVisibility(View.GONE);
                            relativeCorr.setVisibility(View.VISIBLE);
                            relativeNico.setVisibility(View.VISIBLE);
                        }

                        if (valTigie == 2012){
                            tvTigieCorr.setText("2020");
                        } else {
                            tvTigieCorr.setText("2012");
                        }

                        /*try {
                            tvNicoDesc.setText(Html.fromHtml(infFRaction.getNico()));
                        } catch (Exception e) { }

                        try {
                            tvCorrelacionCodigo.setText( Html.fromHtml(infFRaction.getCorrelacion()));
                        } catch (Exception e) { }*/

                        String text1 = "";
                        String encodedText1 = "";
                        String text2 = "";
                        String encodedText2 = "";
                        String text3 = "";
                        String encodedText3 = "";
                        String text4 = "";
                        String encodedText4 = "";

                        if (infFRaction.getTariffChapterDescription() != null) {
                            try {
                                text1 = new String(infFRaction.getTariffChapterDescription().getBytes(), "UTF-8");
                                encodedText1 = Html.fromHtml(text1).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText1 = infFRaction.getTariffChapterDescription();
                            }
                            fracChapterDesc.setText(encodedText1);
                        }

                        if (infFRaction.getTariffHeadingDescription() != null) {
                            try {
                                text2 = new String(infFRaction.getTariffHeadingDescription().trim().getBytes(), "UTF-8");
                                encodedText2 = Html.fromHtml(text2).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText2 = infFRaction.getTariffHeadingDescription().trim();
                            }
                            fracHeadingDesc.setText(encodedText2);
                        }

                        if (infFRaction.getTariffSubheadingDescription() != null) {
                            try {
                                text3 = new String(infFRaction.getTariffSubheadingDescription().trim().getBytes(), "UTF-8");
                                encodedText3 = Html.fromHtml(text3).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText3 = infFRaction.getTariffSubheadingDescription().trim();
                            }
                            fracSubHeadingDesc.setText(encodedText3);
                        }

                        if (infFRaction.getTariffFractionDescription() != null) {
                            try {
                                text4 = new String(infFRaction.getTariffFractionDescription().getBytes(), "UTF-8");
                                encodedText4 = Html.fromHtml(text4).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText4 = infFRaction.getTariffFractionDescription();
                            }
                            fracDesc.setText(encodedText4);
                        }

                        if (infFRaction.getIepsExpo() != null) {
                            if (!infFRaction.getIepsExpo().equals("")) {
                                String text5, encodedText5 = "";

                                try {
                                    text5 = new String(infFRaction.getIepsExpo().getBytes(), "UTF-8");
                                    encodedText5 = Html.fromHtml(text5).toString();
                                } catch (UnsupportedEncodingException e) {
                                    encodedText5 = infFRaction.getIepsExpo();
                                }

                                iepsExpo.setVisibility(View.VISIBLE);
                                iepsExpoCode.setVisibility(View.VISIBLE);
                                iepsExpoCode.setText(encodedText5);
                                iepsExpo1.setVisibility(View.VISIBLE);
                            } else {
                                iepsExpo.setVisibility(View.INVISIBLE);
                                iepsExpo1.setVisibility(View.INVISIBLE);
                                iepsExpoCode.setVisibility(View.INVISIBLE);
                            }
                        }

                        if (!infFRaction.getIepsImpo().equals("")) {
                            String text6, encodedText6 = "";

                            try {
                                text6 = new String(infFRaction.getIepsImpo().getBytes(), "UTF-8");
                                encodedText6 = Html.fromHtml(text6).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText6 = infFRaction.getIepsImpo();
                            }
                            iepsImpo.setVisibility(View.VISIBLE);
                            iepsImpoCode.setVisibility(View.VISIBLE);
                            iepsImpoCode.setText(encodedText6);
                        } else {
                            iepsImpo.setVisibility(View.INVISIBLE);
                            iepsImpoCode.setVisibility(View.INVISIBLE);
                        }

                        if (!infFRaction.getIsan().equals("") ) {
                            String text7, encodedText7 = "";

                            try {
                                text7 = new String(infFRaction.getIsan().getBytes(), "UTF-8");
                                encodedText7 = Html.fromHtml(text7).toString();
                            } catch (UnsupportedEncodingException e) {
                                encodedText7 = infFRaction.getIsan();
                            }

                            isan.setVisibility(View.VISIBLE);
                            isanCode.setVisibility(View.VISIBLE);
                            isanCode.setText(encodedText7);
                            isan1.setVisibility(View.VISIBLE);
                        } else {
                            isan.setVisibility(View.INVISIBLE);
                            isanCode.setVisibility(View.INVISIBLE);
                            isan1.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (IOError e){
                    sentMessageError();
                }
            }
            @Override
            public void onFailure(Call<InfFRaction> call, Throwable t) {
                sentMessageError();
            }
        });
        return v;
    }

    public void sentMessageError () {
        Toast.makeText(InformationFraccionFragment.super.getContext() , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();

        fillInfFrac2.setVisibility(View.INVISIBLE);
        relativePrincipal.setVisibility(View.INVISIBLE);
    }

}
