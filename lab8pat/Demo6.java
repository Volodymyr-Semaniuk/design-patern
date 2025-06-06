
public class Demo6 {

    public static void main(String[] args) {
        System.out.println("===== Refactoring: Methods and Parameters =====\n");

        renameMethodDemo();
        addParameterDemo();
        separateQueryFromModifierDemo();
        parameterizeMethodDemo();
        replaceParameterWithExplicitMethodsDemo();
        preserveWholeObjectDemo();
        introduceParameterObjectDemo();
        removeSettingMethodDemo();
        hideMethodDemo();
        replaceConstructorWithFactoryMethodDemo();
        replaceErrorCodeWithExceptionDemo();
        replaceExceptionWithTestDemo();
    }

    // 6.1 Rename Method
    static void renameMethodDemo() {
        Calculator calculator = new Calculator();
        int result = calculator.add(5, 3);
        System.out.println("6.1 Rename Method: Result = " + result + "\n");
    }

    static class Calculator {
        public int add(int x, int y) { return x + y; } // Renamed from 'calc'
    }

    // 6.2 Add Parameter
    static void addParameterDemo() {
        double total = calculateTotal(100.0, 2, 0.1);
        System.out.println("6.2 Add Parameter: Total with tax = " + total + "\n");
    }

    static double calculateTotal(double price, int quantity, double taxRate) {
        return price * quantity * (1 + taxRate);
    }

    // 6.3 Separate Query from Modifier
    static void separateQueryFromModifierDemo() {
        Account account = new Account(1000);
        double balance = account.getBalance();
        account.deductCommission();
        System.out.println("6.3 Separate Query from Modifier: Balance = " + balance + ", After commission = " + account.getBalance() + "\n");
    }

    static class Account {
        private double balance;
        public Account(double balance) { this.balance = balance; }
        public double getBalance() { return balance; }
        public void deductCommission() { balance -= balance * 0.05; }
    }

    // 6.4 Parameterize Method
    static void parameterizeMethodDemo() {
        PowerCalculator pc = new PowerCalculator();
        System.out.println("6.4 Parameterize Method: Square = " + pc.calculatePower(3, 2) + ", Cube = " + pc.calculatePower(3, 3) + "\n");
    }

    static class PowerCalculator {
        public int calculatePower(int num, int power) {
            return (int) Math.pow(num, power);
        }
    }

    // 6.5 Replace Parameter with Explicit Methods
    static void replaceParameterWithExplicitMethodsDemo() {
        Printer printer = new Printer();
        printer.printTextDocument("Hello world");
        printer.printImageDocument("image.png");
        System.out.println();
    }

    static class Printer {
        public void printTextDocument(String content) {
            System.out.println("6.5 Print text document: " + content);
        }
        public void printImageDocument(String content) {
            System.out.println("6.5 Print image document: " + content);
        }
    }

    // 6.7 Preserve Whole Object
    static void preserveWholeObjectDemo() {
        Person person = new Person("Alice", 30, "Main St");
        ProcessPerson processor = new ProcessPerson();
        processor.process(person);
        System.out.println();
    }

    static class Person {
        private String name;
        private int age;
        private String address;
        public Person(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getAddress() { return address; }
    }

    static class ProcessPerson {
        public void process(Person p) {
            System.out.println("6.7 Processing: " + p.getName() + ", " + p.getAge() + ", " + p.getAddress());
        }
    }

    // 6.8 Introduce Parameter Object
    static void introduceParameterObjectDemo() {
        User user = new User("Bob", 25, "bob@mail.com");
        UserManager manager = new UserManager();
        manager.processUser(user);
        System.out.println();
    }

    static class User {
        private String name;
        private int age;
        private String email;
        public User(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
    }

    static class UserManager {
        public void processUser(User user) {
            System.out.println("6.8 Processing user: " + user.getName() + ", " + user.getAge() + ", " + user.getEmail());
        }
    }

    // 6.9 Remove Setting Method
    static void removeSettingMethodDemo() {
        ImmutablePerson person = new ImmutablePerson("Charlie", 40);
        System.out.println("6.9 Immutable Person: " + person.getName() + ", " + person.getAge() + "\n");
    }

    static class ImmutablePerson {
        private final String name;
        private final int age;
        public ImmutablePerson(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
    }

    // 6.10 Hide Method
    static void hideMethodDemo() {
        HiddenLogic logic = new HiddenLogic();
        logic.publicOperation();
        System.out.println();
    }

    static class HiddenLogic {
        public void publicOperation() {
            System.out.println("6.10 Public operation executed");
            internalHelper();
        }
        private void internalHelper() {
            System.out.println("6.10 Internal helper executed");
        }
    }

    // 6.11 Replace Constructor with Factory Method
    static void replaceConstructorWithFactoryMethodDemo() {
        Car car = Car.create("Toyota", "Camry", 2020, "Red");
        System.out.println();
    }

    static class Car {
        private String brand, model, color;
        private int year;

        private Car(String brand, String model, int year, String color) {
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.color = color;
            registerCar();
        }

        private void registerCar() {
            System.out.println("6.11 Car registered: " + brand + " " + model + " (" + year + ", " + color + ")");
        }

        public static Car create(String brand, String model, int year, String color) {
            return new Car(brand, model, year, color);
        }
    }

    // 6.12 Replace Error Code with Exception
    static void replaceErrorCodeWithExceptionDemo() {
        try {
            int result = divide(10, 0);
            System.out.println("6.12 Division result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("6.12 Error: " + e.getMessage());
        }
        System.out.println();
    }

    static int divide(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Cannot divide by zero");
        return dividend / divisor;
    }

    // 6.13 Replace Exception with Test
    static void replaceExceptionWithTestDemo() {
        double celsius = convertToCelsius(-500);
        System.out.println("6.13 Celsius = " + celsius + "\n");
    }

    static double convertToCelsius(double fahrenheit) {
        if (fahrenheit < -459.67) {
            System.out.println("6.13 Error: Temperature below absolute zero");
            return Double.NaN;
        }
        return (fahrenheit - 32) * 5 / 9;
    }
}