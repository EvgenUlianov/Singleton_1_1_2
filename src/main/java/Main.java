import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static {
        System.out.println("Main");
    }
    public static void main(String[] args) {
        System.out.println("Задача «Логгер»");
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

//        // проверка очередности инициализации
//        {
//            System.out.println("No LoggerHolder");
//        }
//        int example = Logger.example;
//
//        // проверка очередности инициализации
//        {
//            System.out.println("getInstance");
//        }


        logger.log("Запускаем программу");

        logger.log("Просим пользователя ввести входные данные для списка");
        System.out.print("Введите размер списка: ");
        int number = scanner.nextInt();
        System.out.print("Введите верхнюю границу для значений: ");
        int maxValue = scanner.nextInt();

        logger.log("Создаём и наполняем список");
        Random random = new Random();
        List<Integer> input = new ArrayList<>(number);
        for (int i = 0; i < number; i++)
            input.add(random.nextInt(maxValue));
        System.out.println("Вот случайный список: " + input.toString());

        logger.log("Просим пользователя ввести входные данные для фильтрации");
        System.out.print("Введите порог для фильтра: ");
        int threshold  = scanner.nextInt();

        logger.log("Запускаем фильтрацию");
        Filter filter = new Filter(threshold);
        List<Integer> result = filter.filterOut(input);

        logger.log("Выводим результат на экран");
        System.out.println("Отфильтрованный список: " + result.toString());

        logger.log("Завершаем программу");
    }
}
