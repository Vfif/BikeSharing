package com.epam.project.repository.impl;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.specification.client.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ClientRepositoryTest {
    String login = "Person";

    @Test
    public void getInstance() {
        assertNotNull(ClientRepository.getInstance());
    }

    @Test
    public void saveAndFindClientTest() {
        String password = "Person123";
        String email = "person123@gmail.com";
        try {
            ClientRepository.getInstance().save(new Client(login, password, email));
            List<Client> clients = ClientRepository.getInstance().query(new ClientSelectByLoginAndPasswordSpecification(login, password));
            assertFalse(clients.isEmpty());
        } catch (RepositoryException e) {
            fail();
        }
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void removeTest() {
        ClientRepository.getInstance().remove(new Client());
    }

    @Test
    public void transactionTest() {
        double cash = 10.1;
        int bikeId = 1;
        boolean status = true;
        try {
            ClientRepository.getInstance().transaction(
                    new ClientUpdateCashSpecification(login, cash),
                    new ClientUpdateBikeIdSpecification(bikeId, login),
                    new ClientUpdateStatusSpecification(login, status));
            List<Client> clients = ClientRepository.getInstance().query(new ClientSelectByLoginSpecification(login));
            Client client = clients.get(0);
            assertEquals(client.getCash(), cash);
            assertEquals(client.isStatus(), status);
            assertEquals(client.getBikeId(), Integer.valueOf(bikeId));

        } catch (RepositoryException e) {
            fail();
        }
    }
}