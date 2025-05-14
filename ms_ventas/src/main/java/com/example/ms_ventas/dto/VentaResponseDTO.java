package com.example.ms_ventas.dto;

public class VentaResponseDTO {
    private Object cliente;
    private Object categoria;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Object cliente, Object categoria) {
        this.cliente = cliente;
        this.categoria = categoria;
    }

    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Object cliente) {
        this.cliente = cliente;
    }

    public Object getCategoria() {
        return categoria;
    }

    public void setCategoria(Object categoria) {
        this.categoria = categoria;
    }
}
