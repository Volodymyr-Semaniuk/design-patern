
public class Demo5 {

    public static void main(String[] args) {
        System.out.println("===== Control Flow Refactoring Techniques Demo =====\n");

        decomposeConditionalDemo();
        consolidateConditionalExpressionDemo();
        removeControlFlagDemo();
        replaceNestedConditionalWithGuardClausesDemo();
        replaceConditionalWithPolymorphismDemo();
        introduceNullObjectDemo();
        introduceAssertionDemo();
    }

    // 5.1 Decompose Conditional 
    private static void decomposeConditionalDemo() {
        System.out.println("5.1 Decompose Conditional:");

        User user = new User(true, true);
        Resource resource = new Resource(true);

        AccessController controller = new AccessController();
        controller.checkAccess(user, resource);
        System.out.println();
    }

    static class User {
        private boolean loggedIn;
        private boolean hasPermission;

        public User(boolean loggedIn, boolean hasPermission) {
            this.loggedIn = loggedIn;
            this.hasPermission = hasPermission;
        }

        public boolean isLoggedIn() { return loggedIn; }

        public boolean hasPermission(Resource resource) {
            return hasPermission && resource != null && resource.isSecured();
        }
    }

    static class Resource {
        private boolean secured;

        public Resource(boolean secured) {
            this.secured = secured;
        }

        public boolean isSecured() { return secured; }
    }

    static class AccessController {
        public void checkAccess(User user, Resource resource) {
            if (!isAccessAllowed(user, resource)) {
                denyAccess();
                return;
            }

            if (user.hasPermission(resource)) {
                grantAccess();
            } else {
                denyAccess();
            }
        }

        private boolean isAccessAllowed(User user, Resource resource) {
            return user != null && user.isLoggedIn() && resource != null;
        }

        private void grantAccess() {
            System.out.println("Access granted");
        }

        private void denyAccess() {
            System.out.println("Access denied");
        }
    }

    // 5.2 Consolidate Conditional Expression
    private static void consolidateConditionalExpressionDemo() {
        System.out.println("5.2 Consolidate Conditional Expression:");

        double amount = 150;
        boolean isMember = true;
        boolean isDiscountAvailable = false;

        if ((amount > 100 && isMember) || (amount > 200 && isDiscountAvailable)) {
            applyDiscount();
        }
        System.out.println();
    }

    private static void applyDiscount() {
        System.out.println("Discount applied");
    }

    // 5.3 Remove Control Flag
    private static void removeControlFlagDemo() {
        System.out.println("5.3 Remove Control Flag:");

        int[] array = {1, 3, 5, 7, 9};
        int target = 5;

        for (int i : array) {
            if (i == target) {
                System.out.println("Element found");
                return;
            }
        }
        System.out.println("Element not found");
        System.out.println();
    }

    // 5.4 Replace Nested Conditional with Guard Clauses
    private static void replaceNestedConditionalWithGuardClausesDemo() {
        System.out.println("5.4 Replace Nested Conditional with Guard Clauses:");

        int quantity = 5;
        double price = 20;

        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }

        if (price <= 0) {
            System.out.println("Invalid price");
            return;
        }

        System.out.println("Order processed successfully");
        System.out.println();
    }

    // 5.5 Replace Conditional with Polymorphism 
    private static void replaceConditionalWithPolymorphismDemo() {
        System.out.println("5.5 Replace Conditional with Polymorphism:");

        Shape circle = new Circle(3);
        Shape rectangle = new Rectangle(4, 5);

        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());
        System.out.println();
    }

    interface Shape {
        double calculateArea();
    }

    static class Circle implements Shape {
        private double radius;
        public Circle(double radius) { this.radius = radius; }
        public double getRadius() { return radius; }

        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle implements Shape {
        private double length, width;
        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        public double calculateArea() {
            return length * width;
        }
    }

    // 5.6 Introduce Null Object
    private static void introduceNullObjectDemo() {
        System.out.println("5.6 Introduce Null Object:");

        Customer customerWithAddress = new Customer(new RealAddress("Main St", "Kyiv"));
        Customer customerWithoutAddress = new Customer(new NullAddress());

        System.out.println("Customer 1 city: " + customerWithAddress.getAddress().getCity());
        System.out.println("Customer 2 city: " + customerWithoutAddress.getAddress().getCity());
        System.out.println();
    }

    static class Customer {
        private Address address;

        public Customer(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    interface Address {
        String getStreet();
        String getCity();
    }

    static class RealAddress implements Address {
        private String street, city;
        public RealAddress(String street, String city) {
            this.street = street;
            this.city = city;
        }
        public String getStreet() { return street; }
        public String getCity() { return city; }
    }

    static class NullAddress implements Address {
        public String getStreet() { return "N/A"; }
        public String getCity() { return "N/A"; }
    }

    // ===== 5.7 Introduce Assertion =====
    private static void introduceAssertionDemo() {
        System.out.println("5.7 Introduce Assertion:");

        int[] numbers = {1, 2, 3, 4, 5};
        double average = calculateAverage(numbers);
        System.out.println("Average: " + average);
        System.out.println();
    }

    private static double calculateAverage(int[] numbers) {
        assert numbers != null && numbers.length > 0 : "Array must not be null or empty";
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
}