package com.gr2lab4.projekt.Entities.Dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;

public class ZadanieDAO extends DAO<Zadanie, Integer>{

	
	
	
	
	public void insertZadanie(String tytul, String tresc, Date data_rozp, Date data_ukon, int aktywne, Pracownik pracownicy) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
	
	public void deleteZadanieById(int id_zadania) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		// znalezc zadanie w bazie i zaminiec z aktywnego na 0
		
		entityManager.getTransaction().commit();
		entityManager.clear(); 
		
	}
	
	
	public void updateZadanie(Zadanie zadanie) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		
		// napisac selecta
		
		entityManager.getTransaction().commit();
		entityManager.clear(); 
	}
	
	
	@Override
	public Zadanie findById(Integer id) {
		openCurrentSession();
		Zadanie zadanie = (Zadanie) getCurrentSession().get(Zadanie.class, id);
		closeCurrentSession();
		return zadanie;
	}

	@Override
	public List<Zadanie> findAll() {
		openCurrentSession();
		List<Zadanie> lista = (List<Zadanie>) getCurrentSession().createQuery("from Zadanie").getResultList();
		closeCurrentSession();
		return lista;
		
	}

}
