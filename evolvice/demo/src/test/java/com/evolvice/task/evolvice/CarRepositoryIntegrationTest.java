/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice;

import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.repositories.CarRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author mostafa.kashif
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void findCarByIdTest() {

        Car car = new Car("TestBrand", "TestModel", "1990", "TestCategory");
        entityManager.persist(car);
        entityManager.flush();

        Optional<Car> retrievedCar = carRepository.findById(1);

        assertThat(retrievedCar.get().getBrand()).isEqualTo(car.getBrand());
    }
}
