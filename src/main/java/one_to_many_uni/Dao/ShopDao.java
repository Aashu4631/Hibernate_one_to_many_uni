package one_to_many_uni.Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.resource.beans.container.spi.BeanContainer;

import one_to_many_uni.dto.Laptop;
import one_to_many_uni.dto.Shop;

public class ShopDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ashutosh");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	Scanner scanner = new Scanner(System.in);

	public void saveShop(Shop shop) {

		entityTransaction.begin();
		for (Laptop laptop2 : shop.getLaptops()) {
			entityManager.persist(laptop2);
		}
		entityManager.persist(shop);
		entityTransaction.commit();
	}

	public void removeShop(int id) {
		Shop shop = entityManager.find(Shop.class, id);
		if (shop != null) {
			entityTransaction.begin();
			entityManager.remove(shop);
			entityTransaction.commit();

		} else {
			System.out.println("ID NOT FOUND");
		}

	}

	public void getShop(int id) {

		Shop shopdb = entityManager.find(Shop.class, id);
		System.out.println(shopdb);
		if (shopdb != null) {
			System.out.println(shopdb);
		} else {
			System.out.println("ID NOT FOUND");
		}

	}

	public void getAllShops() {
		Query query = entityManager.createQuery("select s from Shop s");
		List list = query.getResultList();
		System.out.println(list);

	}

	public void updateShop(int id, String name) {
		Shop shop = entityManager.find(Shop.class, id);
		if (shop != null) {
			shop.setName(name);
			entityTransaction.begin();
			entityManager.merge(shop);
			entityTransaction.commit();
		} else {
			System.out.println("ID NOT FOUND");
		}

	}

}
