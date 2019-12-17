package com.epam.project.repository.impl;

import com.epam.project.entity.Bike;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.specification.bike.BikeSelectAllSpecification;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class BikeRepositoryTest {
    String name = "Bike";

    @Test
    public void getInstance() {
        assertNotNull(BikeRepository.getInstance());
    }

    @Test
    public void saveAndFindBikeTest() {
        double cost = 10;
        String address = "Минск";
        String description = "Быстрый";
        String imageName = "123.png";

        try {
            BikeRepository.getInstance().save(new Bike(name, cost, address, description, imageName));
            List<Bike> bikes = BikeRepository.getInstance().query(new BikeSelectAllSpecification());
            Bike bike = bikes.stream()
                    .filter(o -> name.equals(o.getName())
                            && cost == o.getCost()
                            && address.equals(o.getAddress())
                            && description.equals(o.getDescription())
                            && imageName.equals(o.getImage()))
                    .findFirst()
                    .orElse(null);
            assertNotNull(bike);
        } catch (RepositoryException e) {
            fail();
        }
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void removeTest() {
        BikeRepository.getInstance().remove(new Bike());
    }

    @Test(dependsOnMethods = "saveAndFindBikeTest")
    public void selectAllTest() {
        try {
            List<Bike> bikes = BikeRepository.getInstance().query(new BikeSelectAllSpecification());
            assertNotNull(bikes);
        } catch (RepositoryException e) {
            fail();
        }
    }
}