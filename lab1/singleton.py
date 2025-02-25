# Singleton Pattern
class Singleton:
    _instance = None

    def new(cls):
        if cls._instance is None:
            cls._instance = super(Singleton, cls).new(cls)
        return cls._instance

    def show_message(self):
        print("Singleton Instance Invoked")

# Factory Method Pattern
class Product:
    def use(self):
        pass

class ConcreteProductA(Product):
    def use(self):
        print("Using Product A")

class ConcreteProductB(Product):
    def use(self):
        print("Using Product B")

class Creator:
    def factory_method(self):
        pass

class ConcreteCreatorA(Creator):
    def factory_method(self):
        return ConcreteProductA()

class ConcreteCreatorB(Creator):
    def factory_method(self):
        return ConcreteProductB()

# Abstract Factory Pattern
class Button:
    def paint(self):
        pass

class Checkbox:
    def paint(self):
        pass

class WinButton(Button):
    def paint(self):
        print("Rendering a Windows button")

class WinCheckbox(Checkbox):
    def paint(self):
        print("Rendering a Windows checkbox")

class MacButton(Button):
    def paint(self):
        print("Rendering a Mac button")

class MacCheckbox(Checkbox):
    def paint(self):
        print("Rendering a Mac checkbox")

class GUIFactory:
    def create_button(self):
        pass

    def create_checkbox(self):
        pass

class WinFactory(GUIFactory):
    def create_button(self):
        return WinButton()
    def create_checkbox(self):
        return WinCheckbox()

class MacFactory(GUIFactory):
    def create_button(self):
        return MacButton()
    def create_checkbox(self):
        return MacCheckbox()

# Builder Pattern
class ProductBuilder:
    def init(self):
        self.part_a = None
        self.part_b = None

    def set_part_a(self, part_a):
        self.part_a = part_a
        return self

    def set_part_b(self, part_b):
        self.part_b = part_b
        return self

    def build(self):
        return BuiltProduct(self.part_a, self.part_b)

class BuiltProduct:
    def init(self, part_a, part_b):
        self.part_a = part_a
        self.part_b = part_b

    def show_parts(self):
        print(f"Part A: {self.part_a}, Part B: {self.part_b}")

# Prototype Pattern
import copy

class Prototype:
    def init(self, field):
        self.field = field

    def clone(self):
        return copy.deepcopy(self)

    def show_field(self):
        print(f"Field: {self.field}")

# Main function demonstrating all patterns
if __name__ == "__main__":
    # Singleton
    singleton = Singleton()
    singleton.show_message()

    # Factory Method
    creator_a = ConcreteCreatorA()
    product_a = creator_a.factory_method()
    product_a.use()

    # Abstract Factory
    factory = WinFactory()
    button = factory.create_button()
    button.paint()

    # Builder
    product = ProductBuilder().set_part_a("Engine").set_part_b("Wheels").build()
    product.show_parts()

    # Prototype
    original = Prototype("Original")
    clone = original.clone()
    clone.field = "Cloned"
    original.show_field()
    clone.show_field()