import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);

        task20(scanner);
    }

    public static void task1(Scanner scanner) {
        System.out.print("Введите число: ");

        int num = scanner.nextInt();
        if (num % 2 == 0) {
            System.out.println("Четное");
        } else {
            System.out.println("Нечетное");
        }
    }

    public static void task2(Scanner scanner) {
        System.out.print("Введите числа: ");
        int num1, num2, num3;
        num1 = scanner.nextInt();
        num2 = scanner.nextInt();
        num3 = scanner.nextInt();
        int min = Math.min(Math.min(num1, num2), num3);
        System.out.println("Минимальное: " + min);
    }

    public static void task3(Scanner scanner) {

        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "\t " + i * 5);
        }
    }

    public static void task4(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("Сумма: " + sum);


    }

    public static void task5(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int first = 0, second = 1;
        System.out.print(first + " " + second + " ");
        for (int i = 2; i < n; i++) {
            int next = first + second;
            System.out.print(next + " ");
            first = second;
            second = next;
        }

    }

    public static void task6(Scanner scanner) {
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        for (int i = num - 1; i > 1; i--) {
            if (num % i == 0) {
                System.out.println("Не простое");
                return;
            }
        }
        System.out.println("Простое");


    }

    public static void task7(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        for (int i = n; i >= 1; i--) {
            System.out.println(i + " ");
        }
    }


    public static void task8(Scanner scanner) {
        System.out.print("Введите a: ");
        int a = scanner.nextInt();
        System.out.print("Введите b: ");
        int b = scanner.nextInt();
        int sum = 0;
        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        System.out.println("Сумма: " + sum);
    }

    public static void task9(Scanner scanner) {
        System.out.print("Введите строку: ");
        String s = scanner.next();
        char[] c = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(c[i]);
        }

    }

    public static void task10(Scanner scanner) {
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        int count = 1;
        while (num / 10 > 0) {
            count += 1;
            num = num / 10;
        }
        System.out.println(count);


    }

    public static void task11(Scanner scanner) {
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        int pr = 1;
        for (int i = num; i > 1; i--) {
            pr *= i;
        }
        System.out.println(pr);
    }

    public static void task12(Scanner scanner) {
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        System.out.println(sum);

    }

    public static void task13(Scanner scanner) {
        System.out.print("Введите строку: ");
        String s = scanner.next();
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length() / 2; i++) {
            if (c[i] != c[s.length() - 1 - i]) {
                System.out.println("Не палиндром");
                return;
            }
        }
        System.out.println("Палиндром");
    }

    public static void task14(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

        }
        System.out.println("Максимальное: " + max);
    }

    public static void task15(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];

        }
        System.out.println("Сумма: " + sum);
    }

    public static void task16(Scanner scanner) {
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int positive = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                positive++;
            }
            if (arr[i] < 0) {
                negative++;
            }

        }
        System.out.println("Положительных: " + positive);
        System.out.println("Отрицательных: " + negative);
    }

    public static void task17(Scanner scanner) {
        System.out.print("Введите a: ");
        int a = scanner.nextInt();
        System.out.print("Введите b: ");
        int b = scanner.nextInt();
        boolean f = true;

        for (int i = a; i <= b; i++) {
            f = true;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {

                    f = false;
                    break;
                }
            }
            if (f)
            System.out.println(i);

        }
        }
    public static void task18(Scanner scanner) {
        System.out.print("Введите строку: ");
        String s = scanner.next();
        String gl = "аеёиоуыэюя";
        char[] c = s.toCharArray();
        int countgl = 0;
        int countsogl = 0;
        for (int i = 0; i < s.length(); i++) {
            if (gl.indexOf(c[i]) != -1) countgl++;
            else countsogl++;
        }
        System.out.println("Число гласных: " + countgl);
        System.out.println("Число согласных: " + countsogl);
    }

    public static void task19(Scanner scanner) {
        System.out.print("Введите строку: ");
        String str = scanner.nextLine();
        String[] words = str.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            System.out.print(words[i] + " ");
        }
        System.out.println();
    }

    public static void task20(Scanner scanner) {
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        String numStr = String.valueOf(num);
        int power = numStr.length();
        int sum = 0;
        int temp = num;

        while (temp != 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, power);
            temp /= 10;
        }

        System.out.println(num == sum ?
                "Это число Армстронга" :
                "Это не число Армстронга");

    }

    }
