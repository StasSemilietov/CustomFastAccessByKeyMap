import strategies.HashMapStrategy;
import strategies.LinkedHashMapStrategy;
import strategies.MapStrategy;
import strategies.MyFastMap;
import utility.RandomGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Main {
    /**
     *для переданного множества строк возвращает множество
     *ключей (используя обработчик стратегий)
     *
     * */
    public static Set<Long> getIds(StrategyExecutor executor, Set<String> strings){
        Set<Long> resultId = new HashSet<>();
        for(String s : strings){
            resultId.add(executor.getId(s));
        }
        return resultId;
    }
    /**
     *для переданного множества ключей возвращает множество
     *значений(строк) (используя обработчик стратегий)
     *
     * */
    public static Set<String> getStrings(StrategyExecutor executor, Set<Long> keys){
        Set<String> resultStrings = new HashSet<>();
        for(Long l : keys){
            resultStrings.add(executor.getString(l));
        }
        return resultStrings;
    }
    /**
     * тестируем стратегию -  передаем обьект стратегии и количество пар
     * */
    public static void testStrategy(MapStrategy strategy, long elementsNumber){
        /**
         * печатаем название стратегии
         * */
        System.out.println(strategy.getClass().getSimpleName());

        /**
         * генерим множество рандомных строк
         * */
        Set<String> testSetString = new HashSet<>();
        for( long i = 0; i < elementsNumber; i++){
            testSetString.add(RandomGenerator.generateRandomString());
        }
        /**
         * обработчик стратегии
         * */
        StrategyExecutor executor = new StrategyExecutor(strategy);
        /**
         * создаем множество, заполняем ключами из карты стратегии, считаем время доступа к ключу по значению
         * */
        Set<Long> ids;
        Date startTime = new Date();
        ids = getIds(executor,testSetString);
        Date finishTime = new Date();
        System.out.println("Access by value : " + Long.toString(finishTime.getTime()-startTime.getTime()));

        /**
         * создаем множество строк (значений), заполняем строками из карты стратегии по полученому множеству ключейб считаем время
         * */
        Set<String> stringValues;
        startTime = new Date();
        stringValues = getStrings(executor,ids);
        finishTime = new Date();
        System.out.println("Access by key : " + Long.toString(finishTime.getTime()-startTime.getTime()));

/**
 * проверяем совпадения множества, которое было сгенерировано, и которое было получено
 * */
        if (stringValues.equals(testSetString)) System.out.println("Тест пройден.");
        else System.out.println("Тест не пройден.");
        System.out.println("-------------------------------------------------------------------------");


    }

    public static void main(String[] args) {
        testStrategy(new MyFastMap(),10000);
        testStrategy(new HashMapStrategy(),10000);
        testStrategy(new LinkedHashMapStrategy(),10000);
    }

}
