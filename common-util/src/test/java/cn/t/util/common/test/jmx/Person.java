package cn.t.util.common.test.jmx;

public class Person implements PersonMBean {
    private String name;

    public Person() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("My name is " + name);
    }
}
