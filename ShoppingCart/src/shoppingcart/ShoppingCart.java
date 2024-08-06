package shoppingcart;

import java.util.ArrayList;
import java.util.Iterator;
import products.Product;

public class ShoppingCart {

	private ArrayList<CartLine> cartLines = new ArrayList<>();

	public void action(Product product, String action) {
		switch (action) {
			case "add":
				addProduct(product);
				break;
			case "remove":
				removeProduct(product);
				break;
			case "print":
				printCart();
				break;
			default:
				throw new IllegalArgumentException("Invalid action: " + action);
		}
	}

	private void addProduct(Product product) {
		for (CartLine cartLine : cartLines) {
			if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
				cartLine.setQuantity(cartLine.getQuantity() + 1);
				return;
			}
		}
		CartLine cartLine = new CartLine();
		cartLine.setProduct(product);
		cartLine.setQuantity(1);
		cartLines.add(cartLine);
	}

	private void removeProduct(Product product) {
		Iterator<CartLine> iterator = cartLines.iterator();
		while (iterator.hasNext()) {
			CartLine cartLine = iterator.next();
			if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
				if (cartLine.getQuantity() > 1) {
					cartLine.setQuantity(cartLine.getQuantity() - 1);
				} else {
					iterator.remove();
				}
			}
		}
	}

	private void printCart() {
		System.out.println("Content of the shopping cart:");
		for (CartLine cartLine : cartLines) {
			System.out.println(cartLine.getQuantity() + " "
					+ cartLine.getProduct().getProductNumber() + " "
					+ cartLine.getProduct().getDescription() + " "
					+ cartLine.getProduct().getPrice());
		}
		System.out.println("Total price = " + getTotalPrice());
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;
		for (CartLine cartLine : cartLines) {
			totalPrice += cartLine.getProduct().getPrice() * cartLine.getQuantity();
		}
		return totalPrice;
	}
}
