package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String lastName;
    private String address;
    private String mobilePhone;
    private String email;
    private String group;


    public ContactData withId(int id) {
        this.id= id;
        return this;
    }

//    public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.mobilePhone = mobilePhone;
//        this.email = email;
//        this.group = group;
//    }

    public ContactData withFirstName(String name) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
