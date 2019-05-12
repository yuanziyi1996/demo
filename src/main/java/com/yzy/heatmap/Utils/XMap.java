package com.yzy.heatmap.Utils;


import java.util.HashMap;
import java.util.Map;

public class XMap<K,V> extends HashMap<K,V> {
    public XMap() {
        super();
    }

    public XMap(int initialCapacity) {
        super(initialCapacity);
    }

    public XMap(Map m) {
        super(m);
    }

    public String getString(K key) {
        return Convert.toString(this.get(key));
    }

    public String[] getStrings(K key)
    {
        return (String[])this.get(key);
    }

    public int getInt(K key) {
        return Convert.toInt(this.get(key));
    }
}
