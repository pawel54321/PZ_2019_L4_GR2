package com.gr2lab4.projekt.Entities.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.gr2lab4.projekt.Entities.Pracownik;

public class PracownikDAO extends DAO<Pracownik, Integer>{

	
	@Override
	public Pracownik findById(Integer id) {
		openCurrentSession();
		Pracownik pracownik = (Pracownik) getCurrentSession().get(Pracownik.class, id);
		closeCurrentSession();
		return pracownik;
	}

	@Override
	public List<Pracownik> findAll() {
		openCurrentSession();
		List<Pracownik> lista = (List<Pracownik>) getCurrentSession().createQuery("from Pracownik").getResultList();
		closeCurrentSession();
		return lista;
		
	}

}
