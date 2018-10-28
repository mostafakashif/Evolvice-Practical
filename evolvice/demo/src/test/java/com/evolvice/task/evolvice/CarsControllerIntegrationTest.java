/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice;

import com.evolvice.task.evolvice.controllers.CarsController;
import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author mostafa.kashif
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarsController.class)
public class CarsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carServiceMock;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    @WithMockUser(username = "admin", password = "adminPassword")
    public void getCarsTest() throws Exception {
        Car car1 = new Car(1,"TestBrand", "TestModel", "1990", "TestCategory");
        Car car2 = new Car(2,"TestBrand_2", "TestModel_2", "1990", "TestCategory_2");
        List<Car> allCars = new ArrayList<>();
        allCars.add(car1);
        allCars.add(car2);
        given(carServiceMock.getCars()).willReturn(allCars);
        mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("{class-name}/{method-name}"))
                .andExpect(jsonPath("$[1].brand", is("TestBrand_2")));
    }

    @Test
    @WithMockUser(username = "admin", password = "adminPassword")
    public void getCarById() throws Exception {
        Car car = new Car(3,"TestBrand", "TestModel", "1990", "TestCategory");
        given(carServiceMock.getCarById(1)).willReturn(car);
        mvc.perform(get("/cars/{carId}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("{class-name}/{method-name}"))
                .andExpect(jsonPath("$.brand", is("TestBrand")));
    }

    @Test
    @WithMockUser(username = "admin", password = "adminPassword")
    public void createCar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car("TestBrand4", null, "1990", "TestCategory");
        mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(car)))
                .andExpect(status().isCreated()).andDo(document("{class-name}/{method-name}"));
    }

    @Test
    @WithMockUser(username = "admin", password = "adminPassword")
    public void updateCar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car(2,"TestBrand4", null, "1990", "TestCategory");
        mvc.perform(put("/cars").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(car)))
                .andExpect(status().isOk()).andDo(document("{class-name}/{method-name}"));
    }
    
     @Test
    @WithMockUser(username = "admin", password = "adminPassword")
    public void deleteCarById() throws Exception {
        mvc.perform(delete("/cars/{carId}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("{class-name}/{method-name}"));
    }
}
