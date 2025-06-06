public class Demo7 {

    public static void main(String[] args) {
        System.out.println("===== Refactoring: Inheritance =====\n");

        pullUpFieldDemo();
        pullUpMethodDemo();
        pullUpConstructorBodyDemo();
        pushDownMethodDemo();
        pushDownFieldDemo();
        extractSuperclassDemo();
        extractInterfaceDemo();
        collapseHierarchyDemo();
    }

    // 7.1 Pull Up Field
    static void pullUpFieldDemo() {
        Car car = new Car("Red", "Sedan");
        Truck truck = new Truck("Blue", 3000, "Dump");

        System.out.println("7.1 Pull Up Field:");
        System.out.println("Car color: " + car.color);
        System.out.println("Truck color: " + truck.color + "\n");
    }

    static class Vehicle {
        protected String color;
        public Vehicle(String color) {
            this.color = color;
        }
    }

    static class Car extends Vehicle {
        private String model;
        public Car(String color, String model) {
            super(color);
            this.model = model;
        }
    }

    static class Truck extends Vehicle {
        private String model;
        private int loadCapacity;
        public Truck(String color, int loadCapacity, String model) {
            super(color);
            this.model = model;
            this.loadCapacity = loadCapacity;
        }
    }

    // 7.2 Pull Up Method
    static void pullUpMethodDemo() {
        Dog dog = new Dog();
        Cat cat = new Cat();

        System.out.println("7.2 Pull Up Method:");
        dog.makeSound();
        cat.makeSound();
        System.out.println();
    }

    static class Animal {
        public void makeSound() {
            System.out.println("Generic animal sound");
        }
    }

    static class Dog extends Animal {}
    static class Cat extends Animal {}

    // 7.3 Pull Up Constructor Body
    static void pullUpConstructorBodyDemo() {
        Dog2 dog = new Dog2("Buddy", 3, "Labrador");
        Cat2 cat = new Cat2("Whiskers", 2, "Black");

        System.out.println("7.3 Pull Up Constructor Body:");
        System.out.println("Dog name: " + dog.name + ", age: " + dog.age);
        System.out.println("Cat name: " + cat.name + ", age: " + cat.age + "\n");
    }

    static class Animal2 {
        protected String name;
        protected int age;

        public Animal2(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class Dog2 extends Animal2 {
        private String breed;
        public Dog2(String name, int age, String breed) {
            super(name, age);
            this.breed = breed;
        }
    }

    static class Cat2 extends Animal2 {
        private String color;
        public Cat2(String name, int age, String color) {
            super(name, age);
            this.color = color;
        }
    }

    // 7.4 Push Down Method
    static void pushDownMethodDemo() {
        Dog3 dog = new Dog3();
        Cat3 cat = new Cat3();

        System.out.println("7.4 Push Down Method:");
        dog.makeSound();
        cat.makeSound();
        System.out.println();
    }

    static class Animal3 {}

    static class Dog3 extends Animal3 {
        void makeSound() {
            System.out.println("Woof!");
        }
    }

    static class Cat3 extends Animal3 {
        void makeSound() {
            System.out.println("Meow!");
        }
    }

    // 7.5 Push Down Field
    static void pushDownFieldDemo() {
        Car2 car = new Car2();
        Truck2 truck = new Truck2();

        car.model = "Sedan";
        truck.model = "Dump";
        truck.loadCapacity = 3000;

        System.out.println("7.5 Push Down Field:");
        System.out.println("Car model: " + car.model);
        System.out.println("Truck model: " + truck.model + ", capacity: " + truck.loadCapacity + "\n");
    }

    static class Vehicle2 {}

    static class Car2 extends Vehicle2 {
        protected String model;
    }

    static class Truck2 extends Vehicle2 {
        protected String model;
        protected int loadCapacity;
    }

    // 7.6 Extract Superclass
    static void extractSuperclassDemo() {
        Manager2 manager = new Manager2("Alice", 80000, "IT");

        System.out.println("7.6 Extract Superclass:");
        manager.displayDetails();
        System.out.println();
    }

    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public void displayDetails() {
            System.out.println("Name: " + name);
            System.out.println("Salary: $" + salary);
        }
    }

    static class Manager2 extends Employee {
        private String department;

        public Manager2(String name, double salary, String department) {
            super(name, salary);
            this.department = department;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Department: " + department);
        }
    }

    // 7.7 Extract Interface
    static void extractInterfaceDemo() {
        Shape rectangle = new Rectangle(5, 10);
        Shape square = new Square(4);

        System.out.println("7.7 Extract Interface:");
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        System.out.println("Square perimeter: " + square.calculatePerimeter() + "\n");
    }

    interface Shape {
        double calculateArea();
        double calculatePerimeter();
    }

    static class Rectangle implements Shape {
        private double width, height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double calculateArea() {
            return width * height;
        }

        public double calculatePerimeter() {
            return 2 * (width + height);
        }
    }

    static class Square implements Shape {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        public double calculateArea() {
            return side * side;
        }

        public double calculatePerimeter() {
            return 4 * side;
        }
    }

    // 7.8 Collapse Hierarchy
    static void collapseHierarchyDemo() {
        Animal4 animal = new Animal4("Dog");

        System.out.println("7.8 Collapse Hierarchy:");
        animal.eat();
        animal.sleep();
        animal.bark();
        System.out.println();
    }

    static class Animal4 {
        private String species;

        public Animal4(String species) {
            this.species = species;
        }

        public void eat() {
            System.out.println("The animal is eating.");
        }

        public void sleep() {
            System.out.println("The animal is sleeping.");
        }

        public void bark() {
            System.out.println("The dog is barking.");
        }
    }
}
