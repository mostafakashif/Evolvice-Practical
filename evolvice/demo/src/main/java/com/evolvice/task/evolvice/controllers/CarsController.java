/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.controllers;

import com.evolvice.task.evolvice.entities.Car;
import com.evolvice.task.evolvice.exceptions.CarServiceException;
import com.evolvice.task.evolvice.service.CarService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author mostafa.kashif
 */
@RestController
@RequestMapping(value = "/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<Car>> getCars() throws CarServiceException {
        System.out.println("Recieved a request on controller getCars");
        List<Car> carsList = carService.getCars();
        carsList.stream().forEach(car -> {
            car.add(linkTo(CarsController.class).withRel("cars"));
            try {
                car.add(linkTo(methodOn(CarsController.class).getCarById(car.getCarId())).withSelfRel());
            } catch (CarServiceException ex) {
                System.out.println("Excepton while creating links:"+ex.getMessage());
            }
        });
        return new ResponseEntity<>(carsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{carId}", method = RequestMethod.GET)
    public HttpEntity<Car> getCarById(@PathVariable Integer carId) throws CarServiceException {
        System.out.println("Recieved a request on controller getCarById with carId:"+carId);
        Car car = carService.getCarById(carId);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
    public HttpEntity deleteCar(@PathVariable Integer carId) throws CarServiceException {
        System.out.println("Recieved a request on controller deleteCar with carId:"+carId);
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity addCar(@RequestBody Car car) throws CarServiceException {
        System.out.println("Recieved a request on controller addCar with car information: "+car.toString());
        carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HttpEntity updateCar(@RequestBody Car car) throws CarServiceException {
        System.out.println("Recieved a request on controller updateCar with car information: "+car.toString());
        carService.updateCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
