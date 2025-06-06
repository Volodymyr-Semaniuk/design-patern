import java.util.ArrayList;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println("===== Refactoring Techniques Demo =====\n");
        
        extractMethodDemo();
        
        extractVariableDemo();
        
        inlineTempDemo();
        
        replaceTempWithQueryDemo();
        
        splitTemporaryVariableDemo();
        
        removeAssignmentsToParametersDemo();
        
        replaceMethodWithMethodObjectDemo();
        
        substituteAlgorithmDemo();
    }
    
    // 2.1 Extract Method 
    private static void extractMethodDemo() {
        System.out.println("2.1 Extract Method:");
        
        Employee employee = new Employee(1000, 10, 15, 20);
        SalaryCalculator calculator = new SalaryCalculator();
        double salary = calculator.calculateSalary(employee);
        System.out.println("Calculated salary: " + salary);
        System.out.println();
    }
    
    static class Employee {
        private double baseSalary;
        private int overtimeHours;
        private double bonusPercentage;
        private double taxRate;
        
        public Employee(double baseSalary, int overtimeHours, double bonusPercentage, double taxRate) {
            this.baseSalary = baseSalary;
            this.overtimeHours = overtimeHours;
            this.bonusPercentage = bonusPercentage;
            this.taxRate = taxRate;
        }
        
        public double getBaseSalary() { return baseSalary; }
        public int getOvertimeHours() { return overtimeHours; }
        public double getBonusPercentage() { return bonusPercentage; }
        public double getTaxRate() { return taxRate; }
    }
    
    static class SalaryCalculator {
        public double calculateSalary(Employee employee) {
            double totalSalary = calculateBaseSalary(employee);
            totalSalary += calculateBonus(employee);
            return calculateNetSalary(totalSalary, employee);
        }
        
        private double calculateBaseSalary(Employee employee) {
            double baseSalary = employee.getBaseSalary();
            int overtimeHours = employee.getOvertimeHours();
            double totalSalary = baseSalary;
            
            if (overtimeHours > 0) {
                double overtimePay = overtimeHours * baseSalary * 1.5;
                totalSalary += overtimePay;
            }
            
            return totalSalary;
        }
        
        private double calculateBonus(Employee employee) {
            return employee.getBaseSalary() * employee.getBonusPercentage() / 100;
        }
        
        private double calculateNetSalary(double totalSalary, Employee employee) {
            double taxAmount = totalSalary * employee.getTaxRate() / 100;
            return totalSalary - taxAmount;
        }
    }
    
    // 2.2 Extract Variable 
    private static void extractVariableDemo() {
        System.out.println("2.2 Extract Variable:");
        
        Circle circle = new Circle(5.0);
        System.out.println("Circle calculation results:");
        circle.printCircleDetails();
        System.out.println();
    }
    
    static class Circle {
        private double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        public double calculateArea() {
            double area = Math.PI * radius * radius;
            return area;
        }
        
        public double calculateCircumference() {
            double circumference = 2 * Math.PI * radius;
            return circumference;
        }
        
        public void printCircleDetails() {
            double area = calculateArea();
            double circumference = calculateCircumference();
            
            System.out.println("Circle with radius " + radius);
            System.out.println("Area: " + area);
            System.out.println("Circumference: " + circumference);
        }
    }
    
    // 2.3 Inline Temp 
    private static void inlineTempDemo() {
        System.out.println("2.3 Inline Temp:");
        
        double fahrenheit = 98.6;
        TemperatureConverter converter = new TemperatureConverter();
        System.out.println(fahrenheit + "°F = " + converter.convertToCelsius(fahrenheit) + "°C");
        System.out.println();
    }
    
    static class TemperatureConverter {
        public double convertToCelsius(double fahrenheit) {
            return (fahrenheit - 32) * 5 / 9;
        }
    }
    
    // 2.4 Replace Temp with Query 
    private static void replaceTempWithQueryDemo() {
        System.out.println("2.4 Replace Temp with Query:");
        
        List<Item> items = new ArrayList<>();
        items.add(new Item(10.0, 2));
        items.add(new Item(15.0, 3));
        items.add(new Item(5.0, 1));
        
        ShoppingCart cart = new ShoppingCart(items);
        System.out.println("Total price: " + cart.calculateTotalPrice());
        System.out.println();
    }
    
    static class Item {
        private double price;
        private int quantity;
        
        public Item(double price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }
        
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
    }
    
    static class ShoppingCart {
        private List<Item> items;
        
        public ShoppingCart(List<Item> items) {
            this.items = items;
        }
        
        public double calculateTotalPrice() {
            double totalPrice = 0;
            for (Item item : items) {
                totalPrice += calculateItemTotalPrice(item);
            }
            return totalPrice;
        }
        
        private double calculateItemTotalPrice(Item item) {
            return item.getPrice() * item.getQuantity();
        }
    }
    
    // 2.5 Split Temporary Variable 
    private static void splitTemporaryVariableDemo() {
        System.out.println("2.5 Split Temporary Variable:");
        
        double loanAmount = 10000;
        double annualInterestRate = 5.5;
        int loanTermYears = 5;
        
        LoanCalculator calculator = new LoanCalculator();
        System.out.println("Total interest: " + 
                          calculator.calculateLoanInterest(loanAmount, annualInterestRate, loanTermYears));
        System.out.println();
    }
    
    static class LoanCalculator {
        public double calculateLoanInterest(double loanAmount, double annualInterestRate, int loanTermYears) {
            double interestRate = annualInterestRate / 100;
            double totalInterest = 0;
            
            double monthlyInterestRate = interestRate / 12;
            int numberOfPayments = loanTermYears * 12;
            
            for (int i = 0; i < numberOfPayments; i++) {
                totalInterest += loanAmount * monthlyInterestRate;
            }
            
            return totalInterest;
        }
    }
    
    // 2.6 Remove Assignments to Parameters 
    private static void removeAssignmentsToParametersDemo() {
        System.out.println("2.6 Remove Assignments to Parameters:");
        
        User user = new User("John", false);
        System.out.println("User before: " + user);
        
        UserManager manager = new UserManager();
        manager.addUser(user);
        
        System.out.println("User after: " + user);
        System.out.println("Users in manager: " + manager.getUsers());
        System.out.println();
    }
    
    static class User {
        private String name;
        private boolean registered;
        
        public User(String name, boolean registered) {
            this.name = name;
            this.registered = registered;
        }
        
        public String getName() { return name; }
        public boolean isRegistered() { return registered; }
        public void setRegistered(boolean registered) { this.registered = registered; }
        
        @Override
        public String toString() {
            return "User{name='" + name + "', registered=" + registered + "}";
        }
    }
    
    static class UserManager {
        private List<User> users = new ArrayList<>();
        
        public void addUser(User user) {
            if (user != null) {
                User registeredUser = user;  // local variable instead of modifying parameter
                registeredUser.setRegistered(true);
                users.add(registeredUser);
            }
        }
        
        public List<User> getUsers() { return users; }
    }
    
    // 2.7 Replace Method with Method Object 
    private static void replaceMethodWithMethodObjectDemo() {
        System.out.println("2.7 Replace Method with Method Object:");
        
        List<Item> orderItems = new ArrayList<>();
        orderItems.add(new Item(10.0, 2));
        orderItems.add(new Item(15.0, 3));
        Order order = new Order(orderItems, new Customer(true));
        
        OrderProcessor processor = new OrderProcessor();
        processor.processOrder(order);
        
        System.out.println("Order total cost: " + order.getTotalCost() + 
                          ", Status: " + order.getStatus());
        System.out.println();
    }
    
    static class Customer {
        private boolean vip;
        
        public Customer(boolean vip) {
            this.vip = vip;
        }
        
        public boolean isVIP() { return vip; }
    }
    
    static class Order {
        private List<Item> items;
        private Customer customer;
        private double totalCost;
        private String status;
        
        public Order(List<Item> items, Customer customer) {
            this.items = items;
            this.customer = customer;
            this.status = "New";
        }
        
        public int getItemCount() { return items.size(); }
        public Item getItem(int index) { return items.get(index); }
        public Customer getCustomer() { return customer; }
        public double getTotalCost() { return totalCost; }
        public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
    
    static class OrderProcessor {
        public void processOrder(Order order) {
            OrderProcessorHelper helper = new OrderProcessorHelper(order);
            helper.compute();
        }
    }
    
    static class OrderProcessorHelper {
        private final Order order;
        private double totalCost;
        
        public OrderProcessorHelper(Order order) {
            this.order = order;
        }
        
        public void compute() {
            calculateItemsTotal();
            applyDiscounts();
            updateOrder();
        }
        
        private void calculateItemsTotal() {
            totalCost = 0;
            int itemCount = order.getItemCount();
            
            for (int i = 0; i < itemCount; i++) {
                Item item = order.getItem(i);
                totalCost += item.getPrice() * item.getQuantity();
            }
        }
        
        private void applyDiscounts() {
            Customer customer = order.getCustomer();
            if (customer.isVIP()) {
                totalCost -= totalCost * 0.1; 
            }
        }
        
        private void updateOrder() {
            order.setTotalCost(totalCost);
            order.setStatus("Processed");
        }
    }
    
    // 2.8 Substitute Algorithm
    private static void substituteAlgorithmDemo() {
        System.out.println("2.8 Substitute Algorithm:");
        
        String input = "Hello World! Testing Refactoring.";
        
        StringManipulator manipulator = new StringManipulator();
        String result = manipulator.manipulateString(input);
        
        System.out.println("Original: " + input);
        System.out.println("Result: " + result);
    }
    
    static class StringManipulator {
        public String manipulateString(String input) {
            StringBuilder result = new StringBuilder(input.length());
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (Character.isLowerCase(ch)) {
                    result.append(Character.toUpperCase(ch));
                } else if (Character.isUpperCase(ch)) {
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }
    }
}