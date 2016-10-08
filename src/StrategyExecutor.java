import strategies.MapStrategy;

/**
 * Класс обработки стратегиий
 */
public class StrategyExecutor {
    /**
     * последний ID(дефолтно = 0)
     * */
    private Long lastId = 0L;
    /**
     *стратегия (через интерфейс)
     * */
    private MapStrategy mapStrategy;
    /**
     *конструктор, инициализируем стратегию
     * */
    public StrategyExecutor(MapStrategy mapStrategy) {
        this.mapStrategy = mapStrategy;
    }
    /**
     *возвращаем ключ(ID), если нет переданого значения - добавляем новую запись в карту с увеличением ID, возвращаем инкрементированый ID
     * */
    public Long getId(String string){
        if(mapStrategy.containsValue(string)){
            return mapStrategy.getKey(string);
        }else {
            lastId++;
            mapStrategy.put(lastId,string);
            return lastId;
        }
    }
    /**
     *возвращаем значение
     * */
    public String getString(Long id){
        if(mapStrategy.containsKey(id))
            return mapStrategy.getValue(id);
        else return null;
    }
}
