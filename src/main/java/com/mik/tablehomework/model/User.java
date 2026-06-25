package com.mik.tablehomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private LocalDateTime registerTime;  // --> dd.MM.yyyy HH:mm:ss
    private String email;
    private Integer age;
    private String country;

    @JsonIgnore
    private boolean isSend;

    public User(Integer id, String name, String registerTime, String email, Integer age, String country, boolean isSend) {
        this.id = id;
        this.name = name;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.registerTime = LocalDateTime.parse(registerTime, format);
        this.email = email;
        this.age = age;
        this.country = country;
        this.isSend = isSend;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime  getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String  registerTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm:ss");
        this.registerTime = LocalDateTime.parse(registerTime, format);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isSend == user.isSend && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(registerTime, user.registerTime) && Objects.equals(email, user.email) && Objects.equals(age, user.age) && Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registerTime, email, age, country, isSend);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registerTime=" + registerTime +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", isSend=" + isSend +
                '}';
    }
}
