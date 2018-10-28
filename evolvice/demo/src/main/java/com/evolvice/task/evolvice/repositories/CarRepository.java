/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.repositories;

import com.evolvice.task.evolvice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mostafa.kashif
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    
}
