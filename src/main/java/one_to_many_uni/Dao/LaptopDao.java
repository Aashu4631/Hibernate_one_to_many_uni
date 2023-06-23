package one_to_many_uni.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import one_to_many_uni.dto.Laptop;

public class LaptopDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ashutosh");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void saveLaptop(Laptop laptop) {
		entityTransaction.begin();

		entityManager.persist(laptop);

		entityTransaction.commit();

	}

	public void removeLatop(int id) {
		Laptop laptop = entityManager.find(Laptop.class, id);
		if (laptop != null) {
			entityTransaction.begin();

			entityManager.remove(laptop);
			entityTransaction.commit();
		}
		System.err.println("ID NOT FOUND");
	}

	public void getLaptop(int id) {

		Laptop laptop = entityManager.find(Laptop.class, id);
		if (laptop != null) {
			System.out.println(laptop);
		}

		else {
			System.out.println("ID NOT FOUND");
		}

	}

	public void getAllLaptops() {
		Query query = entityManager.createQuery("select l from Laptop l");
		List list = query.getResultList();
		System.out.println(list);

	}

	public void updateLaptop(int id, String brand, String color) {
		Laptop laptop = entityManager.find(Laptop.class, id);
		if (laptop != null) {

			laptop.setBrand(brand);
			laptop.setColor(color);
			entityTransaction.begin();
			entityManager.merge(laptop);
			entityTransaction.commit();

		} else {
			System.out.println("ID NOT FOUND");
		}

	}

}
