package com.example.demo6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private int age;
    private String birthday;

    public User(String name, int age, String birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }



    public String getName()
    {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public int getAge() {
        return age;
    }
//返回通过生日计算的年龄的值
     public long getBirthAge(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(this.birthday);
            long time = System.currentTimeMillis() - date.getTime();
            return (time / 1000 / 60 / 60 / 24 / 365);

        } catch (ParseException e) {
            e.printStackTrace();
        }
         return 0;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age + '}';
    }
}