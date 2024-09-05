package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Proveedor;

public interface ProveedorService {

	// registra y actualiza
	public Proveedor registrarProveedor(Proveedor obj);

	// listado de proveedores
	public List<Proveedor> listaProveedor();

	// filtro proveedor
	public List<Proveedor> listaFiltroProveedor(String ruc, int estado, int pais, String razonsocial);

	// Lista por la ruc
	public abstract List<Proveedor> listaPorRazonSocialLike(String rsocial);

	// elimina Proveedor
	public abstract void eliminaProveedor(int idProveedor);

}