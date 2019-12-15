package com.epam.project.entity;

import com.epam.project.type.ClientType;

public class Client extends Entity {
    private int id;
    private ClientType role;
    private String login;
    private String password;
    private String email;
    private boolean status;
    private double cash;
    private Integer bikeId;

    public Client() {
    }

    public Client(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public ClientType getRole() {
        return role;
    }

    public void setRole(ClientType role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", role=").append(role);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", status=").append(status);
        sb.append(", cash=").append(cash);
        sb.append(", bikeId=").append(bikeId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != Client.class) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (isStatus() != client.isStatus()) return false;
        if (Double.compare(client.getCash(), getCash()) != 0) return false;
        if (getRole() != client.getRole()) return false;
        if (getLogin() != null ? !getLogin().equals(client.getLogin()) : client.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(client.getPassword()) : client.getPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(client.getEmail()) : client.getEmail() != null) return false;
        return getBikeId() != null ? getBikeId().equals(client.getBikeId()) : client.getBikeId() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (isStatus() ? 1 : 0);
        temp = Double.doubleToLongBits(getCash());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getBikeId() != null ? getBikeId().hashCode() : 0);
        return result;
    }
}
