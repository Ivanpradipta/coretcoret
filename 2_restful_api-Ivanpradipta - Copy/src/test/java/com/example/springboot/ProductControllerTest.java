package com.example.springboot;

import com.example.springboot.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    public static String requestBody(Object request) {
        try {
            return MAPPER.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
        try {
            String contentAsString = result.getResponse().getContentAsString();
            return MAPPER.readValue(contentAsString, responseClass);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllProduct() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> provideInputPostAddProduct() {
        return Stream.of(
                Arguments.of(new Product(0, "Jam Tangan", 10), status().is2xxSuccessful())
//                Arguments.of(null, status().is4xxClientError())
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputPostAddProduct")
    public void postAddProductTest(Product newProduct,
                                   ResultMatcher resultMatcher) throws Exception {
        if (newProduct == null) {
            mvc.perform(MockMvcRequestBuilders.post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody(newProduct)))
                    .andExpect(resultMatcher);
        } else {
            mvc.perform(MockMvcRequestBuilders.post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody(newProduct)))
                    .andExpect(resultMatcher)
                    .andExpect(jsonPath("$.id", is(newProduct.getId()), Long.class))
                    .andExpect(jsonPath("$.name", is(newProduct.getName()), String.class))
                    .andExpect(jsonPath("$.stock", is(newProduct.getStock()), Integer.class));
        }
    }

    private static Stream<Arguments> provideUpdateProductTest() {
        return Stream.of(
                Arguments.of(new Product(0, "Jam Tangan", 10), 0, status().is2xxSuccessful())
//                Arguments.of(null, 0, status().is4xxClientError())
        );
    }

    @ParameterizedTest
    @MethodSource("provideUpdateProductTest")
    public void updateProductTest(Product updateProduct, long id,
                                  ResultMatcher resultMatcher) throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody(new Product(0, "Handuk", 10))));

        if (updateProduct == null) {
            mvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody(updateProduct)))
                    .andExpect(resultMatcher);
        } else {
            mvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody(updateProduct)))
                    .andExpect(resultMatcher)
                    .andExpect(jsonPath("$.id", is(updateProduct.getId()), Long.class))
                    .andExpect(jsonPath("$.name", is(updateProduct.getName()), String.class))
                    .andExpect(jsonPath("$.stock", is(updateProduct.getStock()), Integer.class));
        }
    }

    private static Stream<Arguments> provideDeleteProductTest() {
        return Stream.of(
                Arguments.of(0, status().isOk())
//                Arguments.of(2, status().is4xxClientError())
        );
    }

    @ParameterizedTest
    @MethodSource("provideDeleteProductTest")
    public void deleteProductTest(long id,
                                  ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody(new Product(0, "Handuk", 10))));

        mvc.perform(MockMvcRequestBuilders.delete("/products/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(resultMatcher);
    }

    static class PatchTest {
        public int amount;
        public String operation;

        public PatchTest(int amount, String operation) {
            this.amount = amount;
            this.operation = operation;
        }
    }
    private static Stream<Arguments> providePatchProductTest() {
        return Stream.of(
                Arguments.of(0, new PatchTest(5, "add"), status().isOk()),
                Arguments.of(0, new PatchTest(2, "deduct"), status().isOk())
        );
    }

    @ParameterizedTest
    @MethodSource("providePatchProductTest")
    public void patchTest(long id, PatchTest patchTest,
                          ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody(new Product(0, "Handuk", 10))));

        mvc.perform(MockMvcRequestBuilders.patch("/products/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody(patchTest)))
                .andExpect(resultMatcher);
    }
}
