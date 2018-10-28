/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice;

import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.exceptions.CarServiceException;
import com.evolvice.task.evolvice.repositories.CarRepository;
import com.evolvice.task.evolvice.service.CarService;
import com.evolvice.task.evolvice.service.DefaultCarService;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mostafa.kashif
 */
@RunWith(SpringRunner.class)
public class CarServiceIntegrationTest {

    @TestConfiguration
    static class CarServiceImplTestContextConfiguration {

        @Bean
        public CarService carService() {
            return new DefaultCarService();
        }
    }

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Before
    public void setUp() {
        Car car = new Car("TestBrand", "TestModel", "1990", "TestCategory");
        List<Car> cars=new ArrayList<>();
        cars.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(cars);
    }

    @Test
    public void getAllCarsTest() throws CarServiceException {
        List<Car> cars= carService.getCars();
        assertThat(cars.get(0).getBrand()).isEqualTo("TestBrand");
    }
}
