package strategies;

import java.io.Serializable;

/**
 * Урезаная карта для Long, String
 * +Внутренний класс для доступа, без узлов(Nodes)
 * по аналогии с HashMap
 * Реализован интерфейс стратегии
 */

public class MyFastMap implements MapStrategy {
    /**
     * дефолтный размер
     * */
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     *дефолтный коэффициент загрузки
     * */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     *массив пар
     * */
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    /**
     *размер
     * */
    int size;
    /**
     *вместимость
     * */
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    /**
     *коэффициент загрузки
     * */
    float loadFactor = DEFAULT_LOAD_FACTOR;

    /**
     *хэш
     * */
    int hash(Long k) {
        return k.hashCode();
    }
    /**
     *пересчитываем размер
     * */
    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }
    /**
     *достаем пару
     * */
    Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)];
                                             e != null;
                                             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
    /**
     *изменяем размер
     * */
    void resize(int newCapacity) {
        Entry[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        if (oldCap == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTab = new Entry[newCapacity];
        transfer(newTab);
        table = newTab;
        threshold = (int) Math.min(newCapacity * loadFactor, (1 << 30) + 1);
    }
    /**
     *перезаписываем мапу
     * */
    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry e : table) {
            while (null != e) {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }
    /**
     * добавляем пару
     * */
    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }
    /**
     *создаем место под пару с увеличением размера
     * */
    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }
    /**
     * реализуем интерфейс
     *
     * */
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }
    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;
        for (Entry aTable : table) {
            for (Entry e = aTable; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        }
        return false;
    }
    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key), table.length));
    }
    @Override
    public Long getKey(String value) {
        if (value == null) return 0l;
        for (Entry aTable : table) {
            for (Entry e = aTable; e != null; e = e.next)
                if (value.equals(e.value))
                    return aTable.getKey();
        }
        return null;
    }
    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }
    /**
     * внутренний класс для пар
     *
     * */
    private class Entry<K, V> implements Serializable {
        Long key;
        String value;
        Entry<K, V> next;
        int hash;

        public Entry(int hash, Long key, String value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Long getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    ", next=" + next +
                    ", hash=" + hash +
                    '}';
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + (next != null ? next.hashCode() : 0);
            result = 31 * result + hash;
            return result;
        }
    }
}
