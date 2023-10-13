package main.util;

import java.util.*;
import java.util.Map.*;

/** Provides utility methods and decorators for Map and SortedMap instances */
public class MapUtil {
	
	/** Orders a Map (key, value) in descending order of values, provided that these values ​​are integers
	 * @param <K> the key 
	 * @param <V> the value 
	 * @param map the map 
	 * @return a map ordered by descending values
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByDescendingValue(Map<K, V> map){
		List<Entry<K, V>>sortedEntries = new ArrayList<Entry<K, V>>(map. entrySet ());
		
		Collections.sort(sortedEntries , new Comparator<Entry<K, V>>(){
			@Override
			public int compare(Entry<K, V>e1, Entry<K, V>e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		Map<K, V> result = new  LinkedHashMap<>();
		for(Entry<K, V> entry : sortedEntries) {
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}
	
	/** Orders a Map (key, value) in ascending order of values
	 * @param <K> the key
	 * @param <V> the value 
	 * @param map the map
	 * @return a map ordered by ascending values
	 */
	public static <K, V> Map<K, V> sortByAscendingValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}