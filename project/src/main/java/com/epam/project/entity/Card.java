package com.epam.project.entity;

public class Card extends Entity{
    private String number;
    private double cash;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != Card.class) return false;

        Card card = (Card) o;

        if (Double.compare(card.getCash(), getCash()) != 0) return false;
        return getNumber() != null ? getNumber().equals(card.getNumber()) : card.getNumber() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getNumber() != null ? getNumber().hashCode() : 0;
        temp = Double.doubleToLongBits(getCash());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("number='").append(number).append('\'');
        sb.append(", cash=").append(cash);
        sb.append('}');
        return sb.toString();
    }
}
