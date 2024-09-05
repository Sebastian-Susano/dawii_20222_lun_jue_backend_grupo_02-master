package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Proveedor;

import java.util.HashMap;
import java.util.List;
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	public List<Proveedor> ListFiltroproveedorJpaGetData(HashMap<String, Object> conditions);
	
	@Query("select x from Proveedor x where x.razonsocial like ?1")
	public List<Proveedor> listaPorRazonSocialLike(String rsocial);
}
