import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

//    // проверка очередности инициализации
//    static {
//        System.out.println("Logger");
//    }
//    // проверка очередности инициализации
//    public static int example = 1;

    protected int num = 1;
    private DateFormat dateFormat;

    // locking the constructor
    private Logger() {this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");}

    // решил воспользоваться вариантом синглтона, не описанным в лекции
    // это ленивая потокобезопасная реализация Singleton
    // использовал прием, основанный на очередности инициализации классов
    // https://docs.oracle.com/javase/specs/jls/se8/html/jls-12.html#jls-12.4
    // см раздел 12.4.1
    // класс инициализируется, когда:
    // --- T  является классом и создаётся экземпляр T.
    // --- Вызывается статический метод, объявленный в T.
    // --- Присваивается значение статическому полю, объявленному в T.
    // --- Используется значение статического поля T, которое не является константой.
    // --- T  — это top level класс, и вызывается assert, лексически вложенный в T
    // а в других случаях класс не инициализируется

    // это потокобезопасная реализация, потому что
    // если другой поток попытается одновременно получить Logger,
    // то он все равно будет ждать, это обеспечивает classloader (все потоки используют один classloader)

    // то, что мы здесь определили класс LoggerHolder, не значит,
    // что JVM его проинициализирует немедленно при инициализации Logger
    // сначала должно быть обращение к статическому полю Logger класса LoggerHolder
    private static class LoggerHolder {
//        // проверка очередности инициализации
//        static {
//            System.out.println("LoggerHolder");
//        }

        // при инициализации класса LoggerHolder инициализируется его статическое поле Logger
        public static final Logger instance = new Logger();
    }

    // перед вызовом Logger.getInstance()
    // класс Logger не был инициализирован
    // класс Logger будет инициализирован сейчас
    // потому что произошло обращение к его статическому методу getInstance()
    public static Logger getInstance()  {
        // класс LoggerHolder еще не инициализирован
        // LoggerHolder будет инициализирован сейчас,
        // потому что произошло обращение к его статическому полю Logger
        return LoggerHolder.instance;
    }
    // сам пример такого синглтона описан здесь:
    // https://urvanov.ru/2017/04/07/%d1%81%d0%b8%d0%bd%d0%b3%d0%bb%d0%b5%d1%82%d0%be%d0%bd-%d0%be%d0%b4%d0%b8%d0%bd%d0%be%d1%87%d0%ba%d0%b0-%d0%b2-java/
    // см. № 7

    public void log(String msg) {
        System.out.print(dateFormat.format(new Date()));
        System.out.println(" [" + num++ + "] " + msg);
    }
}