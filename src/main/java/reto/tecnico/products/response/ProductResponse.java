package reto.tecnico.products.response;

import reto.tecnico.products.beans.Product;

import java.util.List;

public class ProductResponse {
  private int responseCode;
  private String responseMessage;
  private List<Product> products;

  public ProductResponse() {
  }

  public ProductResponse(int responseCode, String responseMessage, List<Product> products) {
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
    this.products = products;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}