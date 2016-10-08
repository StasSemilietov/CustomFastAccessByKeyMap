package strategies;

/**
 *
 * Интерфейс для стратегий
 */
public interface MapStrategy {
    /**
     * проверка наличия ключа
     * @return true если есть, false если нет
     * */
    boolean containsKey(Long key);

    /**
     * проверка наличия значения
     * @return true если есть, false если нет
     * */
    boolean containsValue(String value);

    /**
     * кладем пару в мапу
     *
     * */
    void put(Long key, String value);

    /**
     * отдаем ключ по значению
     * @return ключ мапы
     * */
    Long getKey(String value);

    /**
     * отдаем значение по ключу
     * @return значение мапы
     * */
    String getValue(Long key);
}
