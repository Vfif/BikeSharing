package com.epam.project.entity;

import com.epam.project.type.ClientType;

public class Client extends Entity {
    private ClientType role;
    private String login;
    private String password;
    private String email;

    public Client() {
    }

    public Client(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != Client.class) return false;

        Client client = (Client) o;

        if (getRole() != client.getRole()) return false;
        if (getLogin() != null ? !getLogin().equals(client.getLogin()) : client.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(client.getPassword()) : client.getPassword() != null)
            return false;
        return getEmail() != null ? getEmail().equals(client.getEmail()) : client.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getRole() != null ? getRole().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("role=").append(role);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
