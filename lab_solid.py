#  Single Responsibility Principle (SRP) - Принцип єдиної відповідальності
# Кожен клас відповідає лише за одну задачу (розрахунок зарплати, збереження даних тощо).

class Employee:
    """Клас Employee зберігає інформацію про працівника (ім'я та зарплата)."""

    def __init__(self, name, salary):
        self.name = name
        self.salary = salary


class SalaryCalculator:
    """Окремий клас для розрахунку зарплати, щоб не змішувати логіку у класі Employee."""

    @staticmethod
    def calculate_salary(employee):
        return employee.salary * 12  # Розрахунок річної зарплати


class EmployeeRepository:
    """Окремий клас для збереження даних працівника в базу."""

    @staticmethod
    def save_to_database(employee):
        print(f"Saving {employee.name} to database")


# Open/Closed Principle (OCP) - Принцип відкритості/закритості
# Код має бути відкритий для розширення, але закритий для змін.

class PaymentMethod:
    """Базовий клас для всіх платіжних методів."""

    def process_payment(self, employee):
        pass


class BankTransfer(PaymentMethod):
    """Клас для оплати через банківський переказ."""

    def process_payment(self, employee):
        print(f"Processing bank transfer for {employee.name}")


class CryptoPayment(PaymentMethod):
    """Клас для оплати криптовалютою."""

    def process_payment(self, employee):
        print(f"Processing crypto transfer for {employee.name}")


# Liskov Substitution Principle (LSP) - Принцип підстановки Барбари Лісков
# Дочірні класи можуть замінювати батьківські без зміни поведінки.

class Bird:
    """Базовий клас для птахів."""
    pass


class FlyingBird(Bird):
    """Клас для птахів, які можуть літати."""

    def fly(self):
        print("Flying")


class Penguin(Bird):
    """Клас для пінгвінів, які не літають, а плавають."""

    def swim(self):
        print("Swimming")


# Interface Segregation Principle (ISP) - Принцип розділення інтерфейсів
# Великі інтерфейси потрібно розбивати на менші, щоб класи не реалізовували непотрібні методи.

class Workable:
    """Інтерфейс для об'єктів, які можуть працювати."""

    def work(self):
        pass


class Eatable:
    """Інтерфейс для об'єктів, які можуть їсти."""

    def eat(self):
        pass


class Human(Workable, Eatable):
    """Клас для людини, яка і працює, і їсть."""

    def work(self):
        print("Human working")

    def eat(self):
        print("Human eating")


class Robot(Workable):
    """Клас для робота, який може тільки працювати, але не їсти."""

    def work(self):
        print("Robot working")


# Dependency Inversion Principle (DIP) - Принцип інверсії залежностей
# Класи повинні залежати від абстракцій, а не від конкретних реалізацій.

class Database:
    """Абстрактний клас бази даних."""

    def save(self, data):
        pass


class MySQLDatabase(Database):
    """Конкретна реалізація MySQL бази даних."""

    def save(self, data):
        print(f"Saving {data} to MySQL database")


class PostgreSQLDatabase(Database):
    """Конкретна реалізація PostgreSQL бази даних."""

    def save(self, data):
        print(f"Saving {data} to PostgreSQL database")


class UserService:
    """Клас для роботи з користувачами, який залежить від абстракції Database."""

    def __init__(self, database: Database):
        self.database = database

    def save_user(self, user):
        self.database.save(user)


# Тестування всіх принципів SOLID

if __name__ == "__main__":
    print("=== Testing SRP ===")
    employee = Employee("John", 5000)
    print("Annual Salary:", SalaryCalculator.calculate_salary(employee))
    EmployeeRepository.save_to_database(employee)

    print("\n=== Testing OCP ===")
    bank_payment = BankTransfer()
    bank_payment.process_payment(employee)

    crypto_payment = CryptoPayment()
    crypto_payment.process_payment(employee)

    print("\n=== Testing LSP ===")
    sparrow = FlyingBird()
    sparrow.fly()

    penguin = Penguin()
    penguin.swim()

    print("\n=== Testing ISP ===")
    human = Human()
    human.work()
    human.eat()

    robot = Robot()
    robot.work()

    print("\n=== Testing DIP ===")
    db = MySQLDatabase()  # Можна змінити на PostgreSQLDatabase без зміни логіки
    service = UserService(db)
    service.save_user("Alice")
