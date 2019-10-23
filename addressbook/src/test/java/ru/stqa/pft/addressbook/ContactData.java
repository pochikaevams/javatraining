package ru.stqa.pft.addressbook;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String nickName;

    public ContactData(String name, String lastName, String nickName) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public String getName() {

        return name;
    }

    public String getLastName() {

        return lastName;
    }

    public String getNickName() {

        return nickName;
    }
}
