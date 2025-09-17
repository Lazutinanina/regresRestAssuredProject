package api.products.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    private int id;
    private String title;
    private double price;
    private String category;
}
