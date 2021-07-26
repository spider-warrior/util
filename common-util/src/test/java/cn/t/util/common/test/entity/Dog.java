package cn.t.util.common.test.entity;

public class Dog implements Animal {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void shout() {
        System.out.println(name + ": wang wang --- " + this);
        eat();
    }

    @Override
    public void eat() {
        System.out.println(name + ": eat bone --- " + this);
    }

}
