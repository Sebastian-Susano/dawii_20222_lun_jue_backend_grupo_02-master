package com.cibertec.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cibertec.entity.Proveedor;

public class ProveedorRepositoryImpl {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Proveedor> ListFiltroproveedorJpaGetData(HashMap<String, Object> conditions) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Proveedor> query = cb.createQuery(Proveedor.class);
		Root<Proveedor> root = query.from(Proveedor.class);

		List<Predicate> predicates = new ArrayList<>();
		
		conditions.forEach((field, value) -> {
			switch (field) {
			
			case "ruc":
				if(!value.equals("")) {
					predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
				}
				break;
			case "estado":
				if(!value.equals(-1)) {
					predicates.add(cb.equal (root.get(field), (Integer)value));
				}
				break;
			case "pais":
				if(!value.equals(-1)) {
					predicates.add(cb.equal (root.get(field), (Integer)value));
				}
				break;
			case "razonsocial":
				if(!value.equals("")) {
					predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
				}
				break;
			}
		});
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getResultList();

	}

}
