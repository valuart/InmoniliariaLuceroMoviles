package com.example.inmoniliarialuceromoviles.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Contrato;

public class ConDetalleFragment extends Fragment {
    private TextView id,fechaIni, fechaF, monto, nomInqu, dirInm;
    private Button pagoAl;
    private ConDetalleViewModel cdViewModel;

    public static ConDetalleFragment newInstance() {
        return new ConDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        cdViewModel = new ViewModelProvider(this).get(ConDetalleViewModel.class);

        View root = inflater.inflate(R.layout.con_detalle_fragment, container, false);

        inicializar(root);

        cdViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                //id.setText(contrato.getId()+"");
                fechaIni.setText(contrato.getFechaInicio()+"");
                fechaF.setText(contrato.getFechaFin()+"");
                monto.setText(contrato.getMonto()+"");
                nomInqu.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
                dirInm.setText(contrato.getInmueble().getDireccion()+"");
                pagoAl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagoFragment, bundle);

                    }
                 });
            }

        });
        cdViewModel.setInmueble(getArguments());
        return root;
    }

    private void inicializar(View view) {
        id = view.findViewById(R.id.tvIdc);
        fechaIni = view.findViewById(R.id.tvTitF);
        fechaF = view.findViewById(R.id.tvFecha);
        monto = view.findViewById(R.id.tvMonto);
        nomInqu = view.findViewById(R.id.tvInqCon);
        dirInm = view.findViewById(R.id.tvInmuCon);
        pagoAl = view.findViewById(R.id.btPagos);

    }
}