/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.models;

import java.util.Calendar;

/**
 *
 * @author thuanpv
 */
public class PersonModel {

    private String firstName;
    private String lastName;
    private String street;
    private int postalCode;
    private String city;
    private Calendar birthday;

    public PersonModel() {
    }

    public PersonModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // some initial dummy data
        this.street = "some street";
        this.postalCode = 1234;
        this.city = "some city";
        this.birthday = Calendar.getInstance();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}
