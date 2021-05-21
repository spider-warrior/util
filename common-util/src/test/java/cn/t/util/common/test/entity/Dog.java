package cn.t.util.common.test.entity;

public class Dog implements Animal {

    @Override
    public void shout() {
        System.out.println("wang wang --- " + this);
        eat();
    }

    @Override
    public void eat() {
        System.out.println("dog eat bone --- " + this);
    }

}
