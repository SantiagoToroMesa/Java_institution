package models;

public class Person {
    private String name;
    private int age;
    private int document;
    private String email;

    public Person(String name, int age, int document, String email) {
        this.name = name;
        this.age = age;
        this.document = document;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String mostrarinformacion(){
        return "name: " + name + " " +
                "age: " + age + " " +
                "document: " + document + " " +
                "email: " + email;
    }
}
