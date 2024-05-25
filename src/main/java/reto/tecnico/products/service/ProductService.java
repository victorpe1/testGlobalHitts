package reto.tecnico.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reto.tecnico.products.beans.Product;
import reto.tecnico.products.repository.ProductRepository;
import reto.tecnico.products.response.ProductResponse;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductResponse insertAndListProducts(Product product) {
		return productRepository.insertAndListProducts(product);
	}
}