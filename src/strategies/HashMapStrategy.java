package strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализован интерфейс стратегии для HashMap
 *
 */
public class HashMapStrategy implements MapStrategy {
    HashMap<Long,String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        for(Map.Entry entry : data.entrySet()){
            if(entry.getKey().equals(key))return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for(Map.Entry entry : data.entrySet()){
            if(entry.getValue().equals(value)) return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        data.put(key,value);
    }

    @Override
    public Long getKey(String value) {
        for(Map.Entry entry : data.entrySet()){
            if(entry.getValue().equals(value)) return (Long) entry.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for(Map.Entry entry : data.entrySet()){
            if(entry.getKey().equals(key))return (String) entry.getValue();
        }
        return null;
    }
}
