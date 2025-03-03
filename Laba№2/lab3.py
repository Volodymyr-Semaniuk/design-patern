#Адаптер (дозволяє об'єднувати несумісні інтерфейси шляхом адаптації одного до іншого)
class Target:
    def request(self):
        print("Target: стандартний виклик")

class Adaptee:
    def specific_request(self):
        print("Adaptee: специфічний виклик")

class Adapter(Target):
    def init(self, adaptee):
        self.adaptee = adaptee
    def request(self):
        self.adaptee.specific_request()

#Міст (дозволяє розділити абстракцію та її реалізацію так, щоб вони могли змінюватися незалежно одна від одної)
class Implementor:
    def operation(self):
        pass

class ConcreteImplementorA(Implementor):
    def operation(self):
        print("ConcreteImplementorA operation")

class Abstraction:
    def init(self, implementor):
        self.implementor = implementor
    def operation(self):
        self.implementor.operation()

#Компонувальник (дозволяє групувати об'єкти в деревоподібну структуру і працювати з ними як з єдиним об'єктом)
class Component:
    def operation(self):
        pass

class Leaf(Component):
    def operation(self):
        print("Leaf operation")

class Composite(Component):
    def init(self):
        self.children = []
    def add(self, component):
        self.children.append(component)
    def operation(self):
        print("Composite operation")
        for child in self.children:
            child.operation()

#Декоратор (дозволяє додавати нову поведінку об'єктам без зміни їхньої структури)
class BaseComponent:
    def operation(self):
        print("Base Component")

class Decorator(BaseComponent):
    def init(self, component):
        self.component = component
    def operation(self):
        self.component.operation()

class ConcreteDecorator(Decorator):
    def operation(self):
        super().operation()
        print("Concrete Decorator added behavior")

#Фасад (дозволяє спростити взаємодію між клієнтом та складною системою шляхом надання єдиного інтерфейсу)
class SubsystemA:
    def operation(self):
        print("Subsystem A operation")

class SubsystemB:
    def operation(self):
        print("Subsystem B operation")

class Facade:
    def init(self):
        self.a = SubsystemA()
        self.b = SubsystemB()
    def operation(self):
        print("Facade simplifies subsystems")
        self.a.operation()
        self.b.operation()

#Приспішник (дозволяє зменшити використання пам'яті шляхом повторного використання вже створених об'єктів)
class Flyweight:
    def operation(self):
        print("Flyweight operation")

class FlyweightFactory:
    _flyweights = {}
    def get_flyweight(self, key):
        if key not in self._flyweights:
            self._flyweights[key] = Flyweight()
        return self._flyweights[key]

#Замісник (дозволяє створити об'єкт-заступник для контролю доступу до іншого об'єкта)
class Subject:
    def request(self):
        pass

class RealSubject(Subject):
    def request(self):
        print("RealSubject request")

class Proxy(Subject):
    def init(self):
        self.real_subject = None
    def request(self):
        if self.real_subject is None:
            self.real_subject = RealSubject()
        self.real_subject.request()

#Головна функція для демонстрації
if name == "main":
    #Adapter
    print("=== Adapter Pattern ===")
    adapter = Adapter(Adaptee())
    adapter.request()

    #Bridge
    print("=== Bridge Pattern ===")
    abstraction = Abstraction(ConcreteImplementorA())
    abstraction.operation()

    #Composite
    print("=== Composite Pattern ===")
    composite = Composite()
    composite.add(Leaf())
    composite.operation()

    #Decorator
    print("=== Decorator Pattern ===")
    decorator = ConcreteDecorator(BaseComponent())
    decorator.operation()

    #Facade
    print("=== Facade Pattern ===")
    facade = Facade()
    facade.operation()

    #Flyweight
    print("=== Flyweight Pattern ===")
    factory = FlyweightFactory()
    flyweight = factory.get_flyweight("A")
    flyweight.operation()

    #Proxy
    print("=== Proxy Pattern ===")
    proxy = Proxy()
    proxy.request()