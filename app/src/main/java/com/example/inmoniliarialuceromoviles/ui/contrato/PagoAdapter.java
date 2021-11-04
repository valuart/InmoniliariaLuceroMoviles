package com.example.inmoniliarialuceromoviles.ui.contrato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {
    private List<Pago> lista;
    private View root;
    private LayoutInflater layoutInflater;

    public PagoAdapter(List<Pago> pagos, View root, LayoutInflater layoutInflater) {
        this.lista = pagos;
        this.root = root;
        this.layoutInflater = layoutInflater;
    }
    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolder holder, int position) {
        Pago p = lista.get(position);
        holder.codPago.setText(lista.get(position).getId()+"");
        holder.numPago.setText(lista.get(position).getNroPago()+"");
        holder.codCon.setText(lista.get(position).getContrato().getId()+"");
        holder.importe.setText(lista.get(position).getImporte()+"");
        holder.fechaPago.setText(lista.get(position).getFechaPago()+"");

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView codPago, numPago, codCon, importe, fechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //codPago = itemView.findViewById(R.id.tvCp);
            numPago = itemView.findViewById(R.id.tvNumP);
            codCon = itemView.findViewById(R.id.tvCodC);
            importe = itemView.findViewById(R.id.tvImporte);
            fechaPago = itemView.findViewById(R.id.tvFechaP);

        }
    }
}
