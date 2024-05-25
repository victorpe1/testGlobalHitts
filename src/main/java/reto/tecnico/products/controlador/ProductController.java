package reto.tecnico.products.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import reto.tecnico.products.beans.Product;
import reto.tecnico.products.response.ProductResponse;
import reto.tecnico.products.service.ProductService;

import java.util.List;

@RequestMapping("/uri")
public class ProductController {

	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@PostMapping("/insertarListar")
	public ProductResponse insertAndListProducts(@RequestBody Product product) {
		logger.info("Inicia el metodo");
		try {
			return productService.insertAndListProducts(product);
		} catch (Exception e) {
			logger.info("Entro en error el servicio al responder con la capa de controlador");
		}
		return new ProductResponse();
	}
}
