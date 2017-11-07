package org.baize.utils.clone;

import java.io.Serializable;

public class Person implements Serializable {
    /**
     * 姓名
     **/
    private String name;

    /**
     * 电子邮件
     **/
    private Email email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Person(String name, Email email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name) {
        this.name = name;
    }

    protected Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }

    public static void main(String[] args) {
        //写封邮件
        Email email = new Email("请与今天12:30到二会议室参加会议..");

        Person person1 = new Person("张三", email);

        Person person2 = CloneUtils.clone(person1);
        person2.setName("李四");
        Person person3 = CloneUtils.clone(person1);
        person3.setName("王五");
        person1.getEmail().setMsg("请与今天12:00到二会议室参加会议...");

        System.out.println(person1.getName() + "的邮件内容是：" + person1.getEmail().getMsg());
        System.out.println(person2.getName() + "的邮件内容是：" + person2.getEmail().getMsg());
        System.out.println(person3.getName() + "的邮件内容是：" + person3.getEmail().getMsg());
    }
}  
  