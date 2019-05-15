package com.gr2lab4.projekt.Entities.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.gr2lab4.projekt.Entities.Pracownik;

public class PracownikDAO extends DAO<Pracownik, Integer>{

	public void insertPracownik(String imie, String nazwisko, String stanowisko, String login, String haslo) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
	
	public void deletePracownikById(int id_pracownika) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		
		entityManager.getTransaction().commit();
		entityManager.clear(); 
		
	}
	
	public void updatePracownik(Pracownik pracownik) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		
		entityManager.getTransaction().commit();
		entityManager.clear(); 
	}
	
	
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
		List<Pracownik> lista = (List<Pracownik>) getCurrentSession().createQuery("from pracownik").getResultList();
		closeCurrentSession();
		return lista;
		
	}

}
