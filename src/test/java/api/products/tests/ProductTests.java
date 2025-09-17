package api.products.tests;

import api.config.Config;
import api.products.service.ProductService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.*;

public class ProductTests {
    private final ProductService productService = new ProductService();

    @Test
    public void getAllProducts() {
        Response response = productService.getAllProductsResponse();
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getProductId(){
      Response response =  productService.getProductId(1);
      response.prettyPrint();
      assertNotNull(response);
      assertTrue(response.jsonPath().getDouble("price") > 0);//проверяем, что цена больше 0
    }

    @Test
    public void getRandomProductAndCheck() {
        Response response = productService.getAllProductsResponse();//получаю все продукты
        assertEquals(response.getStatusCode(), 200);//проверяю статус код

        List<Integer> id = response.jsonPath().getList("id", Integer.class);//собираю все id в лист
        assertNotNull(id);
        Integer randomId = id.get(new Random().nextInt(id.size()));

        Response product = productService.getProductId(randomId);
        assertEquals(product.getStatusCode(), 200);
        product.prettyPrint();
    }

    @Test
    public void getProductIdValidation() {
        productService.getProductIdValidatable(2)
                .statusCode(200)
                .contentType(Config.contentTypeJson)
                .body("id", equalTo(2))//проверка что id = 2
                .body("image", startsWith("https://fakestoreapi.com/"))//начинается image
                .body("$", hasKey("price"))
                .body("id", instanceOf(Integer.class)); // тип данных
    }
}
