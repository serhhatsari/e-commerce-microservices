package com.serhat.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("GET /api/v1/products - Success")
    void getProductsSuccess() throws Exception {
        // given
        List<ProductDto> products = new ArrayList<>();
        ProductDto product1 = new ProductDto();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(new BigDecimal(10));
        products.add(product1);

        ProductDto product2 = new ProductDto();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(new BigDecimal(20));
        products.add(product2);

        // when
        when(productService.getProducts()).thenReturn(products);
        ResultActions result = mockMvc.perform(get("/api/v1/products").content(objectMapper.writeValueAsString(products)));

        // then
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(10));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(20));
    }

    @Test
    @DisplayName("POST /api/v1/products - Success")
    void createProduct() throws Exception {
        ProductAddRequest productAddRequest = ProductAddRequest.builder()
                .brand("Samsung")
                .price(BigDecimal.valueOf(30000))
                .availability(true)
                .color("Black")
                .name("Samsung S10")
                .stock(10)
                .category("Phone")
                .seller_id(1L)
                .description("This is a phone")
                .build();

        ResultActions resultActions = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productAddRequest)));

        resultActions.andExpect(status().isCreated());

    }

    @Test
    @DisplayName("GET /api/v1/products/{id} - Success")
    void getProductById() throws Exception {
        // given
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(new BigDecimal(10));

        // when
        when(productService.getProductById(1L)).thenReturn(product);
        ResultActions result = mockMvc.perform(get("/api/v1/products").content(objectMapper.writeValueAsString(product)));

        // then
        result.andExpect(status().isOk());

    }

}