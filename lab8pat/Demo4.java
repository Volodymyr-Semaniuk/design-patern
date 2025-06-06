
import java.util.*;

public class Demo4 {
    public static void main(String[] args) {
        System.out.println("===== Refactoring Techniques Demo =====\n");

        changeValueToReferenceDemo();
        replaceArrayWithObjectDemo();
        duplicateObservedDataDemo();
        changeUnidirectionalToBidirectionalDemo();
        changeBidirectionalToUnidirectionalDemo();
        replaceMagicNumberWithConstantDemo();
        encapsulateFieldDemo();
        encapsulateCollectionDemo();
        replaceTypeCodeWithClassDemo();
        replaceTypeCodeWithSubclassesDemo();
        replaceTypeCodeWithStateDemo();
    }

    // 4.1 Change Value to Reference
    private static void changeValueToReferenceDemo() {
        System.out.println("4.1 Change Value to Reference:");

        CurrencyRegistry registry = new CurrencyRegistry();
        Product prod1 = new Product("Phone", 799.99, registry.getCurrency("USD"));
        Product prod2 = new Product("Laptop", 1299.99, registry.getCurrency("USD"));

        System.out.println(prod1.getName() + ": " + prod1.getCurrency().getCode());
        System.out.println(prod2.getName() + ": " + prod2.getCurrency().getCode());

        System.out.println("Same currency instance: " + (prod1.getCurrency() == prod2.getCurrency()));
        System.out.println();
    }

    static class Currency {
        private String code;

        public Currency(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    static class CurrencyRegistry {
        private Map<String, Currency> currencies = new HashMap<>();

        public Currency getCurrency(String code) {
            return currencies.computeIfAbsent(code, Currency::new);
        }
    }

    static class Product {
        private String name;
        private double price;
        private Currency currency;

        public Product(String name, double price, Currency currency) {
            this.name = name;
            this.price = price;
            this.currency = currency;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public Currency getCurrency() { return currency; }
    }

    // 4.2 Replace Array with Object
    private static void replaceArrayWithObjectDemo() {
        System.out.println("4.2 Replace Array with Object:");

        CarData carData = new CarData();
        carData.addCar(new Car("Toyota", 20000, 2020));
        carData.addCar(new Car("Honda", 22000, 2021));
        carData.getCars().forEach(car ->
            System.out.println(car.name + ", " + car.price + ", " + car.year)
        );
        System.out.println();
    }

    static class Car {
        String name;
        int price;
        int year;

        public Car(String name, int price, int year) {
            this.name = name;
            this.price = price;
            this.year = year;
        }
    }

    static class CarData {
        private List<Car> cars = new ArrayList<>();

        public void addCar(Car car) {
            cars.add(car);
        }

        public List<Car> getCars() {
            return cars;
        }
    }

    // 4.3 Duplicate Observed Data
    private static void duplicateObservedDataDemo() {
        System.out.println("4.3 Duplicate Observed Data:");

        Order order = new Order("Alice", "Keyboard", 3);
        OrderManager orderManager = new OrderManager(order);
        orderManager.displayOrder();

        System.out.println();
    }

    static class Order {
        String customerName;
        String productName;
        int quantity;

        public Order(String customerName, String productName, int quantity) {
            this.customerName = customerName;
            this.productName = productName;
            this.quantity = quantity;
        }
    }

    static class OrderManager {
        private Order order;

        public OrderManager(Order order) {
            this.order = order;
        }

        public void displayOrder() {
            System.out.println("Customer: " + order.customerName);
            System.out.println("Product: " + order.productName);
            System.out.println("Quantity: " + order.quantity);
        }
    }

    // 4.4 Change Unidirectional Association to Bidirectional
    private static void changeUnidirectionalToBidirectionalDemo() {
        System.out.println("4.4 Change Unidirectional Association to Bidirectional:");

        Student student = new Student("Bob");
        Course course = new Course("Math");

        student.enrollCourse(course);
        course.addStudent(student);

        System.out.println("Course '" + course.title + "' has student: " + course.getStudents().get(0).name);
        System.out.println();
    }

    static class Student {
        String name;
        List<Course> courses = new ArrayList<>();

        public Student(String name) {
            this.name = name;
        }

        public void enrollCourse(Course course) {
            courses.add(course);
        }
    }

    static class Course {
        String title;
        List<Student> students = new ArrayList<>();

        public Course(String title) {
            this.title = title;
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public List<Student> getStudents() {
            return students;
        }
    }

    // 4.5 Change Bidirectional to Unidirectional
    private static void changeBidirectionalToUnidirectionalDemo() {
        System.out.println("4.5 Change Bidirectional to Unidirectional:");

        Department dept = new Department("IT", Arrays.asList(new Employee("John")));
        System.out.println("Department: " + dept.name);
        dept.getEmployees().forEach(e -> System.out.println("Employee: " + e.name));
        System.out.println();
    }

    static class Department {
        String name;
        List<Employee> employees;

        public Department(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }
    }

    static class Employee {
        String name;

        public Employee(String name) {
            this.name = name;
        }
    }

    // 4.6 Replace Magic Number with Constant
    private static void replaceMagicNumberWithConstantDemo() {
        System.out.println("4.6 Replace Magic Number with Constant:");

        final int ITEM_COUNT = 100;
        List<String> items = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            items.add("Item " + i);
        }
        System.out.println("Items added: " + items.size());
        System.out.println();
    }

    // 4.7 Encapsulate Field
    private static void encapsulateFieldDemo() {
        System.out.println("4.7 Encapsulate Field:");

        Person person = new Person(25);
        System.out.println("Age: " + person.getAge());
        System.out.println();
    }

    static class Person {
        private int age;

        public Person(int age) {
            this.age = age;
        }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }

    // 4.8 Encapsulate Collection
    private static void encapsulateCollectionDemo() {
        System.out.println("4.8 Encapsulate Collection:");

        Library library = new Library();
        library.addBook(new Book("Java Basics"));
        library.getBooks().forEach(b -> System.out.println("Book: " + b.title));
        System.out.println();
    }

    static class Book {
        String title;

        public Book(String title) {
            this.title = title;
        }
    }

    static class Library {
        private List<Book> books = new ArrayList<>();

        public List<Book> getBooks() {
            return Collections.unmodifiableList(books);
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void removeBook(Book book) {
            books.remove(book);
        }
    }

    // 4.9 Replace Type Code with Class
    private static void replaceTypeCodeWithClassDemo() {
        System.out.println("4.9 Replace Type Code with Class:");

        ProductType type = new ProductType("ELECTRONICS");
        ProductWithType product = new ProductWithType(type, "TV");

        System.out.println("Product: " + product.name + ", Type: " + product.type.getName());
        System.out.println();
    }

    static class ProductType {
        private String name;

        public ProductType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class ProductWithType {
        ProductType type;
        String name;

        public ProductWithType(ProductType type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    // 4.10 Replace Type Code with Subclasses
    private static void replaceTypeCodeWithSubclassesDemo() {
        System.out.println("4.10 Replace Type Code with Subclasses:");

        AbstractProduct prod1 = new PhysicalProduct("Desk");
        AbstractProduct prod2 = new DigitalProduct("Ebook");

        prod1.process();
        prod2.process();

        System.out.println();
    }

    static abstract class AbstractProduct {
        String name;
        public AbstractProduct(String name) {
            this.name = name;
        }

        public abstract void process();
    }

    static class PhysicalProduct extends AbstractProduct {
        public PhysicalProduct(String name) {
            super(name);
        }

        @Override
        public void process() {
            System.out.println("Processing physical product: " + name);
        }
    }

    static class DigitalProduct extends AbstractProduct {
        public DigitalProduct(String name) {
            super(name);
        }

        @Override
        public void process() {
            System.out.println("Processing digital product: " + name);
        }
    }

    // 4.11 Replace Type Code with State/Strategy
    private static void replaceTypeCodeWithStateDemo() {
        System.out.println("4.11 Replace Type Code with State/Strategy:");

        OrderWithState order = new OrderWithState();
        order.setStatus(new CompletedStatus());
        order.printStatus();
        System.out.println();
    }

    interface OrderStatus {
        void print();
    }

    static class NewStatus implements OrderStatus {
        public void print() { System.out.println("Order is new."); }
    }

    static class CompletedStatus implements OrderStatus {
        public void print() { System.out.println("Order completed."); }
    }

    static class OrderWithState {
        private OrderStatus status;

        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        public void printStatus() {
            status.print();
        }
    }
}