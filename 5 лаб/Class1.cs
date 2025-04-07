using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static void Main()
    {
        //Ініціалізація списків для демонстрації
        List<int> numbers = new List<int> { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<double> doubleNumbers = new List<double> { 2.5, 3.5, 4.5, 5.5 };
        List<string> strings = new List<string> { "яблуко", "банан", "", "вишня", "Авокадо" };

        //Виклик кожного методу та вивід результату
        Console.WriteLine("Непарні числа: " + string.Join(", ", FilterOddNumbers(numbers)));
        Console.WriteLine("Середнє значення: " + CalculateAverage(doubleNumbers));
        Console.WriteLine("Відсортовані рядки: " + string.Join(", ", SortStrings(strings)));
        Console.WriteLine("Сума парних чисел: " + SumEvenNumbers(numbers));
        Console.WriteLine("Факторіал 5: " + Factorial(5));
        Console.WriteLine("Добуток елементів: " + MultiplyElements(numbers));
        Console.WriteLine("Числа у квадраті: " + string.Join(", ", SquareNumbers(numbers)));
        Console.WriteLine("Сортування за довжиною: " + string.Join(", ", SortByLength(strings)));
        Console.WriteLine("Кількість слів: " + CountWords("Привіт світ, ласкаво просимо до C#"));
        Console.WriteLine("Перший непорожній рядок: " + FindFirstNonEmptyString(strings));
        Console.WriteLine("Усі слова з великої літери: " + AllStartWithUppercase(strings));
        Console.WriteLine("Друге за величиною число: " + SecondLargest(numbers));
        Console.WriteLine("Найбільше парне число: " + LargestEvenNumber(numbers));
    }

    static List<int> FilterOddNumbers(List<int> numbers) => numbers.Where(n => n % 2 != 0).ToList();
    static double CalculateAverage(List<double> numbers) => numbers.Average();
    static List<string> SortStrings(List<string> strings) => strings.OrderBy(s => s).ToList();
    static int SumEvenNumbers(List<int> numbers) => numbers.Where(n => n % 2 == 0).Sum();
    static int Factorial(int n) => n == 0 ? 1 : Enumerable.Range(1, n).Aggregate((a, b) => a * b);
    static int MultiplyElements(List<int> numbers) => numbers.Aggregate(1, (a, b) => a * b);
    static List<int> SquareNumbers(List<int> numbers) => numbers.Select(n => n * n).ToList();
    static List<string> SortByLength(List<string> strings) => strings.OrderBy(s => s.Length).ToList();
    static int CountWords(string sentence) => sentence.Split(' ').Count(w => !string.IsNullOrEmpty(w));
    static string FindFirstNonEmptyString(List<string> strings) => strings.FirstOrDefault(s => !string.IsNullOrEmpty(s)) ?? "Немає";
    static bool AllStartWithUppercase(List<string> strings) => strings.All(s => !string.IsNullOrEmpty(s) && char.IsUpper(s[0]));
    static int SecondLargest(List<int> numbers) => numbers.OrderByDescending(n => n).Distinct().Skip(1).FirstOrDefault();
    static int LargestEvenNumber(List<int> numbers) => numbers.Where(n => n % 2 == 0).DefaultIfEmpty().Max();
}