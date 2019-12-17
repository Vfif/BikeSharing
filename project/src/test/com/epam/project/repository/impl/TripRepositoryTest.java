package com.epam.project.repository.impl;

import com.epam.project.entity.Card;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TripRepositoryTest {

    @Test
    public void getInstance() {
        assertNotNull(TripRepository.getInstance());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void removeTest() {
        CardRepository.getInstance().remove(new Card());
    }

}