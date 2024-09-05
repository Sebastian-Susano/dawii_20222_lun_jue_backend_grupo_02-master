package com.cibertec.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Proveedor;
import com.cibertec.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository repository;

	@Override
	public Proveedor registrarProveedor(Proveedor obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Proveedor> listaProveedor() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Proveedor> listaFiltroProveedor(String ruc, int estado, int pais, String razonsocial) {
		// TODO Auto-generated method stub
		// return repository.listaFiltroProveedor(ruc, estado, pais, razonSocial);

		// realizando con hasmap
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("ruc", ruc);
		hm.put("estado", estado);
		hm.put("pais", pais);
		hm.put("razonsocial", razonsocial);

		List<Proveedor> listaProveedor = repository.ListFiltroproveedorJpaGetData(hm);

		return listaProveedor;
	}

	@Override
	public List<Proveedor> listaPorRazonSocialLike(String rsocial) {

		return repository.listaPorRazonSocialLike(rsocial);
	}

	@Override
	public void eliminaProveedor(int idProveedor) {

		repository.deleteById(idProveedor);

	}

}