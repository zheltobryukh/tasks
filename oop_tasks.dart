import 'package:args/args.dart';

const String version = '0.0.1';

// 1
class Person {
  String name;
  int age;
  String gender;

  Person(this.name, this.age, this.gender);

  void displayInfo() {
    print('Name: $name, Age: $age, Gender: $gender');
  }

  void increaseAge() {
    age++;
  }

  void changeName(String newName) {
    name = newName;
  }
}

// 2
class Worker extends Person {
  double salary;

  Worker(String name, int age, String gender, this.salary)
      : super(name, age, gender);

  @override
  void displayInfo() {
    super.displayInfo();
    print('Salary: \$${salary.toStringAsFixed(2)}');
  }
}

class Manager extends Worker {
  List<Worker> subordinates = [];

  Manager(String name, int age, String gender, double salary)
      : super(name, age, gender, salary);

  void addSubordinate(Worker worker) {
    subordinates.add(worker);
  }

  void displaySubordinates() {
    print('Subordinates of $name:');
    for (var worker in subordinates) {
      worker.displayInfo();
    }
  }
}

// 3
abstract class IAnimal {
  void makeSound();
}

class Dog implements IAnimal {
  @override
  void makeSound() {
    print('Woof!');
  }
}

class Cat implements IAnimal {
  @override
  void makeSound() {
    print('Meow!');
  }
}

class Cow implements IAnimal {
  @override
  void makeSound() {
    print('Moo!');
  }
}

// 4
abstract class Transport {
  void move();
}

class Car extends Transport {
  @override
  void move() {
    print('Car is driving on the road');
  }
}

class Bike extends Transport {
  @override
  void move() {
    print('Bike is pedaling on the road');
  }
}

// 5
class Book {
  String title;
  String author;
  int year;

  Book(this.title, this.author, this.year);
}

class Library {
  List<Book> books = [];

  void addBook(Book book) {
    books.add(book);
  }

  List<Book> findByAuthor(String author) {
    return books.where((book) => book.author == author).toList();
  }

  List<Book> findByYear(int year) {
    return books.where((book) => book.year == year).toList();
  }
}

// 6
class BankAccount {
  final String _accountNumber;
  double _balance;

  BankAccount(this._accountNumber) : _balance = 0.0;

  String get accountNumber => _accountNumber;
  double get balance => _balance;

  void deposit(double amount) {
    if (amount > 0) {
      _balance += amount;
    }
  }

  bool withdraw(double amount) {
    if (amount > 0 && amount <= _balance) {
      _balance -= amount;
      return true;
    }
    return false;
  }
}

// 7
class Counter {
  static int _count = 0;
  final int id;

  Counter() : id = ++_count;

  static int get totalCount => _count;
}

// 8
abstract class Shape {
  double getArea();
}

class Circle extends Shape {
  final double radius;
  static const double pi = 3.14159;

  Circle(this.radius);

  @override
  double getArea() => pi * radius * radius;
}

class Rectangle extends Shape {
  final double width;
  final double height;

  Rectangle(this.width, this.height);

  @override
  double getArea() => width * height;
}

// 9
class Animal {
  String name;

  Animal(this.name);

  void move() {
    print('Animal is moving');
  }
}

class Fish extends Animal {
  Fish(String name) : super(name);

  @override
  void move() {
    print('$name is swimming in the water');
  }
}

class Bird extends Animal {
  Bird(String name) : super(name);

  @override
  void move() {
    print('$name is flying in the sky');
  }
}

// 10
class Student {
  String name;
  String group;
  double grade;

  Student(this.name, this.group, this.grade);
}

class University {
  List<Student> students = [];

  void addStudent(Student student) {
    students.add(student);
  }

  List<Student> sortByName() {
    var sortedList = List<Student>.from(students);
    sortedList.sort((a, b) => a.name.compareTo(b.name));
    return sortedList;
  }

  List<Student> filterByGrade(double minGrade) {
    return students.where((student) => student.grade >= minGrade).toList();
  }
}

// 11
class Product {
  String name;
  double price;
  int quantity;

  Product(this.name, this.price, this.quantity);
}

class Store {
  List<Product> products = [];

  void addProduct(Product product) {
    products.add(product);
  }

  void removeProduct(String name) {
    products.removeWhere((product) => product.name == name);
  }

  Product? findProduct(String name) {
    try {
      return products.firstWhere((product) => product.name == name);
    } catch (e) {
      return null;
    }
  }
}

// 12
abstract class PaymentSystem {
  bool pay(double amount);
  bool refund(double amount);
}

class CreditCard implements PaymentSystem {
  String cardNumber;
  double balance;

  CreditCard(this.cardNumber, this.balance);

  @override
  bool pay(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  @override
  bool refund(double amount) {
    balance += amount;
    return true;
  }
}

class PayPal implements PaymentSystem {
  String email;
  double balance;

  PayPal(this.email, this.balance);

  @override
  bool pay(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  @override
  bool refund(double amount) {
    balance += amount;
    return true;
  }
}

// 13
class UniqueID {
  static int _lastId = 0;
  final int id;

  UniqueID() : id = ++_lastId;

  static int get lastGeneratedId => _lastId;
}

// 14
class Point {
  double x;
  double y;

  Point(this.x, this.y);
}

class RectangleWithPoints {
  Point topLeft;
  Point bottomRight;

  RectangleWithPoints(this.topLeft, this.bottomRight);

  double getArea() {
    return (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y);
  }
}

// 15
class ComplexNumber {
  double real;
  double imaginary;

  ComplexNumber(this.real, this.imaginary);

  ComplexNumber operator +(ComplexNumber other) {
    return ComplexNumber(
      real + other.real,
      imaginary + other.imaginary,
    );
  }

  ComplexNumber operator -(ComplexNumber other) {
    return ComplexNumber(
      real - other.real,
      imaginary - other.imaginary,
    );
  }

  ComplexNumber operator *(ComplexNumber other) {
    return ComplexNumber(
      real * other.real - imaginary * other.imaginary,
      real * other.imaginary + imaginary * other.real,
    );
  }

  @override
  String toString() => '$real ${imaginary >= 0 ? '+' : ''} ${imaginary}i';
}

// 16
class Matrix {
  List<List<double>> data;
  int rows;
  int columns;

  Matrix(this.rows, this.columns)
      : data = List.generate(rows, (_) => List.filled(columns, 0.0));

  Matrix operator +(Matrix other) {
    if (rows != other.rows || columns != other.columns) {
      throw Exception('Matrix dimensions must match');
    }

    var result = Matrix(rows, columns);
    for (var i = 0; i < rows; i++) {
      for (var j = 0; j < columns; j++) {
        result.data[i][j] = data[i][j] + other.data[i][j];
      }
    }
    return result;
  }

  Matrix operator *(Matrix other) {
    if (columns != other.rows) {
      throw Exception('Invalid matrix dimensions for multiplication');
    }

    var result = Matrix(rows, other.columns);
    for (var i = 0; i < rows; i++) {
      for (var j = 0; j < other.columns; j++) {
        for (var k = 0; k < columns; k++) {
          result.data[i][j] += data[i][k] * other.data[k][j];
        }
      }
    }
    return result;
  }
}

// 17
class GamePlayer {
  String name;
  int health;
  int level;
  Weapon? weapon;

  GamePlayer(this.name, this.health, this.level);

  void attack(Enemy enemy) {
    if (weapon != null) {
      enemy.takeDamage(weapon!.damage);
    }
  }
}

class Enemy {
  String type;
  int health;
  int damage;

  Enemy(this.type, this.health, this.damage);

  void takeDamage(int amount) {
    health -= amount;
    if (health < 0) health = 0;
  }
}

class Weapon {
  String name;
  int damage;

  Weapon(this.name, this.damage);
}

// 18
class Order {
  static int _lastOrderId = 0;
  final int orderId;
  final Customer customer;
  final List<Product> products;
  final DateTime orderDate;

  Order(this.customer, this.products)
      : orderId = ++_lastOrderId,
        orderDate = DateTime.now();

  double getTotalCost() {
    return products.fold(0, (sum, product) => sum + product.price);
  }
}

class Customer {
  String name;
  String email;
  List<Order> orderHistory = [];

  Customer(this.name, this.email);

  void addOrder(Order order) {
    orderHistory.add(order);
  }
}

// 19
class Device {
  String brand;
  bool _isOn = false;

  Device(this.brand);

  void turnOn() {
    _isOn = true;
    print('$brand device is turned on');
  }

  void turnOff() {
    _isOn = false;
    print('$brand device is turned off');
  }
}

class Smartphone extends Device {
  Smartphone(String brand) : super(brand);

  void takePhoto() {
    if (_isOn) {
      print('Taking photo with $brand smartphone');
    } else {
      print('Turn on the smartphone first');
    }
  }
}

class Laptop extends Device {
  Laptop(String brand) : super(brand);

  void runProgram(String programName) {
    if (_isOn) {
      print('Running $programName on $brand laptop');
    } else {
      print('Turn on the laptop first');
    }
  }
}

// 20
class TicTacToeGame {
  List<List<String>> board;
  String currentPlayer;
  bool gameOver;

  TicTacToeGame()
      : board = List.generate(3, (_) => List.filled(3, '')),
        currentPlayer = 'X',
        gameOver = false;

  bool makeMove(int row, int col) {
    if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col].isNotEmpty || gameOver) {
      return false;
    }

    board[row][col] = currentPlayer;
    if (checkWin(row, col)) {
      gameOver = true;
      return true;
    }

    currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    return true;
  }

  bool checkWin(int row, int col) {
    // Check row
    if (board[row].every((cell) => cell == currentPlayer)) return true;

    // Check column
    if (board.every((row) => row[col] == currentPlayer)) return true;

    // Check diagonals
    if (row == col &&
        board.asMap().entries.every((entry) => board[entry.key][entry.key] == currentPlayer)) {
      return true;
    }

    if (row + col == 2 &&
        board.asMap().entries.every((entry) => board[entry.key][2 - entry.key] == currentPlayer)) {
      return true;
    }

    return false;
  }

  void displayBoard() {
    for (var row in board) {
      print(row.map((cell) => cell.isEmpty ? '_' : cell).join(' | '));
      print('---------');
    }
  }
}

void main(List<String> arguments) {
  var person = Person('John', 25, 'Male');
  person.displayInfo();
  person.increaseAge();
}
