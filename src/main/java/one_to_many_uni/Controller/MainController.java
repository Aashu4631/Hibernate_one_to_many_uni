package one_to_many_uni.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step;
import one_to_many_uni.Dao.LaptopDao;
import one_to_many_uni.Dao.ShopDao;
import one_to_many_uni.dto.Laptop;
import one_to_many_uni.dto.Shop;

public class MainController {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ashutosh");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Laptop l = new Laptop();
		Shop s = new Shop();
		LaptopDao laptopDao = new LaptopDao();
		ShopDao shopDao = new ShopDao();
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		do {
			System.out.println("1.LAPTOP 2.SHOP ");
			switch (scanner.nextInt()) {
			case 1:
				System.err
						.println("1.SAVE-LAPTOP 2.REMOVE-LAPTOP 3.GET-LAPTOP 4.GET-ALL LAPTOPS 5.UPDATE-LAPTOP 6.EXIT");
				switch (scanner.nextInt()) {
				case 1:
					System.out.println("ENTEr THE NUMBER OF LAPTOPS TO INSERT");
					int size = scanner.nextInt();

					Laptop laptop = new Laptop();

					System.out.println("ENTER THE BRAND NAME");
					laptop.setBrand(scanner.next());
					System.out.println("ENTER THE COLOR OF LAPTOP");
					laptop.setColor(scanner.next());

					laptopDao.saveLaptop(laptop);
					break;
				case 2:
					System.out.println("ENTER THE ID");
					laptopDao.removeLatop(scanner.nextInt());
					break;
				case 3:
					System.out.println("ENTER THE ID");
					laptopDao.getLaptop(scanner.nextInt());
					break;
				case 4:
					laptopDao.getAllLaptops();
					break;
				case 5:
					System.out.println("ENTER THE ID");
					int id = scanner.nextInt();
					System.out.println("ENTER THE NEW BRAND");
					String brand = scanner.next();

					System.out.println("ENTER THE NEW COLOR");
					String color = scanner.next();
					laptopDao.updateLaptop(id, brand, color);
				case 6:
					return;
				default:
					break;
				}
				break;

			case 2:
				System.err.println("1.ADD-SHOP 2.REMOVE-SHOP 3.GET-SHOP 4.GET-ALL SHOP'S 5.UPDATE-SHOP 6.EXIT ");
				switch (scanner.nextInt()) {
				case 1:

					System.out.println("ENTEr THE NUMBER OF LAPTOPS TO INSERT");
					int size = scanner.nextInt();
					List<Laptop> laptops = new ArrayList<Laptop>();
					for (int i = 1; i <= size; i++) {
						Laptop laptop = new Laptop();

						System.out.println("ENTER THE BRAND NAME");
						laptop.setBrand(scanner.next());
						System.out.println("ENTER THE COLOR OF LAPTOP");
						laptop.setColor(scanner.next());

						laptops.add(laptop);
					}

					Shop shop = new Shop();

					System.out.println("ENTER THE SHOP NAME");
					shop.setName(scanner.next());

					shop.setLaptops(laptops);
					shopDao.saveShop(shop);
					break;
				case 2:
					System.out.println("ENTER THE ID");
					shopDao.removeShop(scanner.nextInt());
					break;
				case 3:

					System.out.println("ENTER THE ID");
					shopDao.getShop(scanner.nextInt());

					break;
				case 4:
					shopDao.getAllShops();
					break;
				case 5:
					System.out.println("ENTER THE ID ");
					System.out.println("ENTER THE NEW NAME");
					shopDao.updateShop(scanner.nextInt(), scanner.next());
					break;
				case 6:
					return;
				default:
					break;
				}
				break;
			default:
				break;
			}
		} while (flag);
	}
}
