package reto.tecnico.products;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reto.tecnico.products.beans.Product;
import reto.tecnico.products.repository.ProductRepository;
import reto.tecnico.products.response.ProductResponse;
import reto.tecnico.products.service.ProductService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class JUnitTestRetoTecnicoTest {
  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private ProductService productService;

  public JUnitTestRetoTecnicoTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testInsertAndListProducts_Success() {
    Product product = new Product(1, "Test Product", new Date());
    List<Product> expectedProducts = Collections.singletonList(product);
    ProductResponse expectedResponse = new ProductResponse(0, "Ejecución con éxito", expectedProducts);

    when(productRepository.insertAndListProducts(product)).thenReturn(expectedResponse);

    ProductResponse actualResponse = productService.insertAndListProducts(product);

    assertEquals(expectedResponse, actualResponse);
    verify(productRepository, times(1)).insertAndListProducts(product);
  }

  @Test
  public void testInsertAndListProducts_Failure() {
    Product product = new Product(1, "Test Product", new Date());
    ProductResponse expectedResponse = new ProductResponse(1, "Error en el SP", Collections.emptyList());

    when(productRepository.insertAndListProducts(product)).thenReturn(expectedResponse);

    ProductResponse actualResponse = productService.insertAndListProducts(product);

    assertEquals(expectedResponse, actualResponse);
    verify(productRepository, times(1)).insertAndListProducts(product);
  }
}