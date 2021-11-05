package com.example.inmoniliarialuceromoviles.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Pago implements Serializable {
    private int id;
    private int nroPago;
    private String fechaPago;
    private double importe;
    private Contrato contrato;

    public Pago() {}

    public Pago(int idPago, int nroPago, String fechaPago, double importe, Contrato contrato) {
        this.id = id;
        this.nroPago = nroPago;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int idP) {
        this.id= id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaPago() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(fechaPago);

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }


    public void setFechaPago(String fechaDePago) {
        this.fechaPago = fechaPago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}