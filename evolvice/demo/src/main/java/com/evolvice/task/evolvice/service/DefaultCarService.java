/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.service;

import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.exceptions.CarServiceException;
import com.evolvice.task.evolvice.repositories.CarRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mostafa.kashif
 */
@Service
public class DefaultCarService implements CarService {

    @Autowired
    private CarRepository carReposiory;

    @Override
    public List<Car> getCars() throws CarServiceException {
        try {
            System.out.println("Start retrieving all cars in system");
            return carReposiory.findAll();
        } catch (Exception ex) {
            System.out.println("Exception while getting cars: "+ex.getMessage());
            throw new CarServiceException(ex.getMessage());
        }
    }

    @Override
    public Car getCarById(Integer id) throws CarServiceException {
        try {
            System.out.println("Start retrieving car with id:"+id);
            Optional<Car> optionalCar=carReposiory.findById(id);
            if(optionalCar.isPresent())
            {
                return optionalCar.get();
            }
            else {
                System.out.println("No car exists with id:"+id);
                throw new CarServiceException("No car exists with id:"+id);
            }
        } catch (Exception ex) {
            System.out.println("Exception while getting car: "+ex.getMessage()+" , for id:"+id);
            throw new CarServiceException(ex.getMessage());
        }
    }

    @Override
    public void updateCar(Car car) throws CarServiceException {
        try {
            System.out.println("Start updating car with id:"+car.getCarId());
            this.getCarById(car.getCarId());
            carReposiory.save(car);
        } catch (CarServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            System.out.println("Exception while updating car: "+ex.getMessage()+" , for id:"+car.getCarId());
            throw new CarServiceException(ex.getMessage());
        }
    }

    @Override
    public void addCar(Car car) throws CarServiceException {
        try {
            carReposiory.save(car);
        } catch (Exception ex) {
            throw new CarServiceException(ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws CarServiceException {
        try {
            System.out.println("Start deleting car with id:"+id);
            carReposiory.deleteById(id);
        } catch (Exception ex) {
            System.out.println("Exception while deleting car: "+ex.getMessage()+" , for id:"+id);
            throw new CarServiceException(ex.getMessage());
        }
    }

}
