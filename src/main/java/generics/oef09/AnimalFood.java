package generics.oef09;

public class AnimalFood {
    static class Animal<A extends Animal<A>> {
        public void eat(Food<A> food) {
            System.out.println(this.getClass().getSimpleName() + " says 'Yummie!'");
        }
    }
    static class Mammal<M extends Mammal<M>> extends Animal<M> {
        public void drink(Milk<M> milk) {
            this.eat(milk);
        }
    }
    static class Cat extends Mammal<Cat> {}
    static class Kitten extends Cat {}
    static class Dog extends Mammal<Dog> {}
    static class Food<A extends Animal<A>> {}
    static class Milk<M extends Mammal<M>> extends Food<M> {}

    static class Main {
        public static void main(String[] args) {
            Food<Cat> catFood = new Food<>();
            Milk<Cat> catMilk = new Milk<>();
            Food<Dog> dogFood = new Food<>();
            Milk<Dog> dogMilk = new Milk<>();

            Cat cat = new Cat();
            Dog dog = new Dog();
            Kitten kitten = new Kitten();

            cat.eat(catFood); // OK 👍
            cat.drink(catMilk); // OK 👍
            dog.eat(dogFood); // OK 👍
            dog.drink(dogMilk); // OK 👍
            kitten.eat(catFood); // OK 👍
            kitten.drink(catMilk); // OK 👍

            /*
            cat.eat(dogFood); // <- moet een compiler error geven! ❌
            kitten.eat(dogFood); // <- moet een compiler error geven! ❌
            kitten.drink(dogMilk); // <- moet een compiler error geven! ❌
            */
        }
    }
}
