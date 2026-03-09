package fr.istic.taa.jaxrs.jpa;


import java.time.LocalDate;
import java.time.Month;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);
		
		User u = new User();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			
			u.setNom("Kouassi");
			u.setPrenom("Rose");
			u.setDateNaiss(	LocalDate.of(2003,Month.JULY, 12));
			u.setEmail("kouassiR@gmail.com");
			u.setMdp("mdp");
			u.setTel(977555999);
		
		manager.persist(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
