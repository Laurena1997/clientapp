
package com.realdolmen.maven.clientrepository.domain;

public class Person extends Klant{
    
    private String firstName;
    private String name;

   

    public Person(String firstName, String name) {
        this.firstName = firstName;
        this.name = name;
    }

    public Person() {
    }
    

    @Override
    public void hello() {
        System.out.println("Ik wil geen hello zeggen");
    }
    

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +"number= "+getNumber() + ", firstName=" + firstName + ", name=" + name + '}';
    }
    
    
    
    
    
}
