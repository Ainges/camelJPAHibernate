package org.acme;

public class PersonCreateDTO {
    public String name;
    public String birth;
    
    public PersonCreateDTO() {
    }
    
    public PersonCreateDTO(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBirth() {
        return birth;
    }
    
    public void setBirth(String birth) {
        this.birth = birth;
    }
}
