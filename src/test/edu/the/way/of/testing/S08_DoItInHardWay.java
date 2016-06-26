package edu.the.way.of.testing;

import java.util.Objects;

/**
 * Created by pawel on 6/22/16.
 */
public class S08_DoItInHardWay {

    public void should_static_be_your_religion() {
        new StaticIsMyReligion().performSomeAction();
    }

    public void should_you_used_new_in_constructor() {
        new ConstructorMadness().doSomething();
    }

    public void should_class_have_many_dependencies() {

    }

    public void should_validate_law_of_demeter() {
        new SomeClass().doMagic();
    }


    private class StaticIsMyReligion {

        public Object performSomeAction() {
            Object value = StaticFactory.getObject();
            //do maginc
            return value;
        }
    }

    private static class StaticFactory {

        static Object getObject() {
            return new Object();
        }
    }

    private class ConstructorMadness {

        Object one;
        Object two;

        public ConstructorMadness() {
            one = StaticFactory.getObject();
            two = new Object();
        }

        public void doSomething(){
            //one object depends on second
        }

    }

    private class SomeClass {
        Person person;

        public Object doMagic() {
            return person.getAddress().getCity();
        }

    }

    private class Person {
        private String name;
        private Address address;

        public Address getAddress() {
            return address;
        }
    }

    private class Address {
        private String city;

        public String getCity() {
            return city;
        }
    }

}
