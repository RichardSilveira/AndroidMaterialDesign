package silveira.richard.meuproximocarro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Richard on 04/07/2015.
 */
public class CarSearchFragment extends Fragment {

    public static String LOG_TAG = "CAR_SEARCH";

    private boolean isFirstSubmit = true;
    private TextInputLayout txtInputNomeModelo;
    private TextInputLayout txtInputAno;
    private FloatingActionButton btnBuscarCarro;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_car_search, container, false);

        txtInputNomeModelo = (TextInputLayout) view.findViewById(R.id.txt_input_nome_modelo);
        txtInputAno = (TextInputLayout) view.findViewById(R.id.txt_input_ano);
        btnBuscarCarro = (FloatingActionButton) view.findViewById(R.id.fab_buscar_carro);

        txtInputNomeModelo.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                Log.d(CarSearchFragment.LOG_TAG, "txtInputNomeModelo[onFocusChange], isFirstSubmit:" + isFirstSubmit);
                Log.d(CarSearchFragment.LOG_TAG, "txtInputNomeModelo[onFocusChange], hasFocus:" + hasFocus);

                if (!isFirstSubmit && !hasFocus) {
                    ValidarNomeModelo();
                }
            }
        });

        txtInputAno.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                Log.d(CarSearchFragment.LOG_TAG, "txtInputAno[onFocusChange], isFirstSubmit:" + isFirstSubmit);
                Log.d(CarSearchFragment.LOG_TAG, "txtInputAno[onFocusChange], hasFocus:" + hasFocus);

                if (!isFirstSubmit && !hasFocus) {
                    ValidarAnoFabricacao();
                }
            }
        });
        btnBuscarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(CarSearchFragment.LOG_TAG, "btnBuscarCarro.setOnClickListener");

                try {
                    isFirstSubmit = false;

                    if (ValidarNomeModelo() &
                            ValidarAnoFabricacao()) {

                        Intent intent = new Intent(getActivity(), CarDetailsActivity.class);
                        startActivity(intent);
                    }

                } catch (Exception ex) {
                    Log.e(CarSearchFragment.LOG_TAG, ex.getMessage());
                }
            }
        });

        return view;
    }

    private boolean ValidarNomeModelo() {

        Log.d(CarSearchFragment.LOG_TAG, "ValidarNomeModelo");

        if (txtInputNomeModelo.getEditText().getText().toString().isEmpty()) {
            txtInputNomeModelo.setErrorEnabled(true);
            txtInputNomeModelo.setError("Informe o nome ou o modelo do veículo");

            return false;
        } else {
            txtInputNomeModelo.setErrorEnabled(false);
            txtInputNomeModelo.setError("");
        }
        return true;
    }

    private boolean ValidarAnoFabricacao() {

        Log.d(CarSearchFragment.LOG_TAG, "ValidarAnoFabricacao");

        if (txtInputAno.getEditText().getText().toString().isEmpty()) {
            txtInputAno.setErrorEnabled(true);
            txtInputAno.setError("Informe o ano do veículo");

            return false;
        } else {
            txtInputAno.setErrorEnabled(false);
            txtInputAno.setError("");
        }
        return true;
    }
}
