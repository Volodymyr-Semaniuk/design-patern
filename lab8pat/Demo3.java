import java.util.*;

public class Demo3 {

    public static void main(String[] args) {
        System.out.println("===== Refactoring: Organizing Data =====\n");

        moveMethodDemo();
        hideDelegateDemo();
        removeMiddleManDemo();
        introduceForeignMethodDemo();
        introduceLocalExtensionDemo();
    }

    // 3.1 Move Method
    static void moveMethodDemo() {
        System.out.println("--- 3.1 Move Method ---");
        Bank bank = new Bank();
        bank.addAccount(new Account(1000, 5));
        bank.processAccounts();
        System.out.println();
    }

    static class Account {
        double balance;
        double interestRate;

        public Account(double balance, double interestRate) {
            this.balance = balance;
            this.interestRate = interestRate;
        }
    }

    static class Bank {
        private List<Account> accounts = new ArrayList<>();

        public void addAccount(Account account) {
            accounts.add(account);
        }

        public void processAccounts() {
            for (Account account : accounts) {
                double interest = account.balance * account.interestRate / 100;
                System.out.println("Interest calculated: " + interest);
            }
        }
    }

    // 3.2 Hide Delegate
    static void hideDelegateDemo() {
        System.out.println("--- 3.2 Hide Delegate ---");
        Department dept = new Department("IT", new Employee("Alice"));
        Employee emp = new Employee("Bob", dept);
        System.out.println("Manager's name: " + emp.getManagerName());
        System.out.println();
    }

    // 3.3 Remove Middle Man
    static void removeMiddleManDemo() {
        System.out.println("--- 3.3 Remove Middle Man ---");
        Department dept = new Department("HR", new Employee("Carol"));
        Employee emp = new Employee("Dave", dept);
        System.out.println("Manager's name: " + emp.getDepartment().getManager().getName());
        System.out.println();
    }

    static class Department {
        private String name;
        private Employee manager;

        public Department(String name, Employee manager) {
            this.name = name;
            this.manager = manager;
        }

        public Employee getManager() {
            return manager;
        }
    }

    static class Employee {
        private String name;
        private Department department;

        public Employee(String name) {
            this.name = name;
        }

        public Employee(String name, Department department) {
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public Department getDepartment() {
            return department;
        }

        // Used in 3.2 Hide Delegate
        public String getManagerName() {
            return department.getManager().getName();
        }
    }

    // 3.4 Introduce Foreign Method
    static void introduceForeignMethodDemo() {
        System.out.println("--- 3.4 Introduce Foreign Method ---");
        Calendar currentDate = Calendar.getInstance();
        if (Client.isWeekend(currentDate)) {
            System.out.println("It's weekend!");
        } else {
            System.out.println("It's not weekend!");
        }
        System.out.println();
    }

    static class Client {
        public static boolean isWeekend(Calendar date) {
            int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
        }
    }

    // 3.8 Introduce Local Extension
    static void introduceLocalExtensionDemo() {
        System.out.println("--- 3.8 Introduce Local Extension ---");
        Date now = new Date();
        Date future = new Date(now.getTime() + 3 * 24 * 60 * 60 * 1000L); 
        DateHelperExtended helper = new DateHelperExtended();
        System.out.println("Days between: " + helper.daysBetween(now, future));
        System.out.println();
    }

    static class DateHelper {
        public static Date addDays(Date date, int days) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, days);
            return cal.getTime();
        }
    }

    static class DateHelperExtended extends DateHelper {
        public int daysBetween(Date start, Date end) {
            long diff = end.getTime() - start.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24));
        }
    }
}
