package br.com.iuriraredu.model;

import br.com.iuriraredu.enums.RoomType;

public class Booking{
    private String email;
    private String country;
    private String password;
    private String dailyBudget;
    private Boolean newsletter;
    private RoomType roomType;
    private String roomDescription;

    public Booking(String email, String country, String password, String dailyBudget, Boolean newsletter,
                   RoomType roomType, String roomDescription) {
        this.email = email;
        this.country = country;
        this.password = password;
        this.dailyBudget = dailyBudget;
        this.newsletter = newsletter;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
    }

    public Booking() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDailyBudget() {
        return this.dailyBudget;
    }

    public Boolean getNewsletter() {
        return this.newsletter;
    }

    public String getRoomType() {
        return this.roomType.get();
    }

    public String getRoomDescription() {
        return this.roomDescription;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDailyBudget(String dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }


    public String toString() {
        return "Booking(email=" + this.getEmail() + ", country=" + this.getCountry() + ", dailyBudget=" +
                this.getDailyBudget() + ", newsletter=" + this.getNewsletter() + ", roomType="
                + this.getRoomType() + ", roomDescription=" + this.getRoomDescription() + ")";
    }
}
