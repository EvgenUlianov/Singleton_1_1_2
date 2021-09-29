import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> source) {
        Logger logger = Logger.getInstance();

        Predicate<Integer> integerPredicate = (sourceEntry) -> {
            if(sourceEntry > treshold) {
                logger.log(String.format("Элемент \"%d\" проходит", sourceEntry));
                return true;
            } else  {
                logger.log(String.format("Элемент \"%d\" не проходит", sourceEntry));
                return false;
            }
        };
        List<Integer> result = source.stream().filter(integerPredicate).toList();
        return result;
    }
}