package com.gr2lab4.projekt.Entities.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.gr2lab4.projekt.Entities.Pracownik;

public class PracownikDAO extends DAO<Pracownik, Integer>{

	/*
    
    public void deleteKlienciById(Integer id_client) {
        
        EntityManager entityManager = JPAUtility.getEntityManager();
        entityManager.getTransaction().begin();

            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("deleteKlienciById");   
            query.setParameter("id_client", id_client);
            query.execute();

        entityManager.getTransaction().commit();
        entityManager.clear();
    }
    
    
    public void updateKlienci(Integer id_client,String imie, String nazwisko, String PESEL) {
        
        EntityManager entityManager = JPAUtility.getEntityManager();
        entityManager.getTransaction().begin();

            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("updateKlienci");   
            query.setParameter("id_client", id_client);
            query.setParameter("imie_client", imie);
            query.setParameter("nazwisko_client", nazwisko);
            query.setParameter("PESEL_client", PESEL);
            query.execute();

        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public List<Klienci> findAll() {
        openCurrentSession();
        List<Klienci> Oceny = (List<Klienci>) getCurrentSession().createQuery("from Klienci").list();
        closeCurrentSession();
        return Oceny;
    }

    @Override
    public Klienci findById(Integer id) {
        openCurrentSession();
        Klienci Ocena = (Klienci) getCurrentSession().get(Klienci.class, id);
        closeCurrentSession();
        return Ocena;
    }
    
    public Klienci findByNumber(long number) {
        openCurrentSession();
        Klienci Ocena = (Klienci) getCurrentSession().get(Klienci.class, number);
        closeCurrentSession();
        return Ocena;
    }
	 
	 */
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
	
	public void updatePracownik(String imie, String nazwisko, String stanowisko, String login, String haslo) {
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
