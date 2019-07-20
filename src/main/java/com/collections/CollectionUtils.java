package com.collections;

import com.domain.Tuple;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.utils.PreConditions;
import fj.data.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.sonatype.inject.Nullable;

/**
 * User: Nitish Goyal Date: 23/08/18 Time: 10:51 PM
 */
@SuppressWarnings({"all"})
public class CollectionUtils {

    public static final Transformer<Object, String> OBJECT_STRING_TRANSFORMER = new Transformer<Object, String>() {
        @Override
        public String transform(Object t) {
            return String.valueOf(t);
        }

    };
    public static final Transformer<Object, Long> OBJECT_LONG_TRANSFORMER = new Transformer<Object, Long>() {
        @Override
        public Long transform(Object t) {
            if (t == null) {
                return null;
            }
            if (t instanceof Long) {
                return (Long) t;
            }
            return Long.valueOf(t.toString());
        }
    };
    public static final Transformer<Object, Float> OBJECT_FLOAT_TRANSFORMER = new Transformer<Object, Float>() {
        @Override
        public Float transform(Object t) {
            if (t == null) {
                return null;
            }
            if (t instanceof Float) {
                return (Float) t;
            }
            return Float.valueOf(t.toString());
        }
    };

    public static final Transformer<Object, String> OBJECT_TO_STR_TRANSFORMER = new CollectionUtils.Transformer<Object, String>() {
        @Override
        public String transform(Object id) {
            if (id != null) {
                return String.valueOf(id);
            }
            return null;
        }
    };
    public static final Transformer<Object, Integer> OBJECT_TO_INT_TRANSFORMER =
            new CollectionUtils.Transformer<Object, Integer>() {
                @Override
                public Integer transform(Object id) {
                    if (id != null) {
                        return Integer.valueOf(String.valueOf(id));
                    }
                    return null;
                }
            };
    public static final Transformer<String, String> LOWER_CASE_TRANSFORMER = new CollectionUtils.Transformer<String, String>() {
        @Override
        public String transform(String t) {
            return (StringUtils.isBlank(t)) ? null : t.trim().toLowerCase();
        }
    };

    public static final Transformer<String, ObjectId> STRING_TO_OBJECT_ID_TRANSFORMER =
            new CollectionUtils.Transformer<String, ObjectId>() {
                @Override
                public ObjectId transform(String t) {
                    return new ObjectId(t);
                }
            };
    public static final Transformer<String, String> STR_TO_STR_TRANSFORMER = new CollectionUtils.Transformer<String, String>() {
        @Override
        public String transform(String id) {
            return id;
        }
    };
    public static final Transformer<String, Object> STR_TO_OBJECT_TRANSFORMER = new CollectionUtils.Transformer<String, Object>() {
        @Override
        public Object transform(String id) {
            return id;
        }
    };
    public static final CollectionUtils.Transformer<Long, Object> LONG_TO_OBJECT_TRANSFORMER =
            new CollectionUtils.Transformer<Long, Object>() {
                @Override
                public Object transform(Long t) {
                    return t;
                }
            };
    public static final CollectionUtils.Transformer<Integer, Object> INTEGER_TO_OBJECT_TRANSFORMER =
            new CollectionUtils.Transformer<Integer, Object>() {
                @Override
                public Object transform(Integer t) {
                    return t;
                }
            };
    public static final CollectionUtils.Transformer<Object, Long> OBJECT_TO_LONG_TRANSFORMER =
            new CollectionUtils.Transformer<Object, Long>() {
                @Override
                public Long transform(Object t) {
                    return Long.valueOf(String.valueOf(t));
                }
            };
    public static final Transformer<Long, String> LONG_TO_STR_TRANSFORMER = new CollectionUtils.Transformer<Long, String>() {
        @Override
        public String transform(Long id) {
            if (id != null) {
                return String.valueOf(id);
            }
            return null;
        }
    };
    public static final Transformer<Double, Long> DOUBLE_TO_LONG_TRANSFORMER = new Transformer<Double, Long>() {
        @Override
        public Long transform(Double t) {
            if (t != null) {
                return t.longValue();
            }
            return null;
        }
    };
    public static final Transformer<Double, Float> DOUBLE_TO_FLOAT_TRANSFORMER = new Transformer<Double, Float>() {
        @Override
        public Float transform(Double t) {
            if (t != null) {
                return t.floatValue();
            }
            return null;
        }
    };
    public static final CollectionUtils.Transformer<String, Long> STR_TO_LONG_TRANSFORMER =
            new CollectionUtils.Transformer<String, Long>() {
                @Override
                public Long transform(String id) {
                    if (StringUtils.isNotEmpty(id) && !"null".equals(id)) {
                        return Long.valueOf(id);
                    }
                    return null;
                }
            };
    public static final CollectionUtils.Transformer<String, Integer> STR_TO_INTEGER_TRANSFORMER =
            new CollectionUtils.Transformer<String, Integer>() {
                @Override
                public Integer transform(String id) {
                    if (StringUtils.isNotEmpty(id)) {
                        return Integer.valueOf(id);
                    }
                    return null;
                }
            };
    public static final CollectionUtils.Transformer<String, String> STRING_TO_TRIMMED_LOWERCASE_STRING =
            new CollectionUtils.Transformer<String, String>() {
                @Override
                public String transform(String t) {
                    return t.trim().toLowerCase();
                }
            };
    public static final CollectionUtils.Transformer<String, String> STRING_TO_TRIMMED_STRING =
            new CollectionUtils.Transformer<String, String>() {
                @Override
                public String transform(String t) {
                    return t.trim();
                }
            };
    public static final Transformer<String, Integer> STR_TO_INTEGER_SAFE_TRANSFORMER = new Transformer<String, Integer>() {
        @Override
        public Integer transform(String t) {
            if (StringUtils.isNotEmpty(t)) {
                try {
                    return Integer.valueOf(t);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }
    };
    public static final CollectionUtils.Transformer<Long, String> LONG_TO_STRING_TRANSFORMER =
            new CollectionUtils.Transformer<Long, String>() {
                @Override
                public String transform(Long aLong) {
                    return String.valueOf(aLong);
                }
            };
    private static CollectionUtils.Transformer<String, String> STRING_VALUE_TO_TRIMMED_LOWER_CASE =
            new CollectionUtils.Transformer<String, String>() {
                @Override
                public String transform(String tag) {
                    if (StringUtils.isNotBlank(tag)) {
                        tag = tag.trim().toLowerCase();
                    }
                    return tag;
                }
            };
    private static CollectionUtils.Transformer<String, String> STRING_VALUE_TO_TRIMMED_UPPER_CASE =
            new CollectionUtils.Transformer<String, String>() {
                @Override
                public String transform(String tag) {
                    if (StringUtils.isNotBlank(tag)) {
                        tag = tag.trim().toUpperCase();
                    }
                    return tag;
                }
            };

    public static <S> Transformer<S, S> identity() {
        return new Transformer<S, S>() {
            @Override
            public S transform(S t) {
                return t;
            }
        };
    }

    public static <E> java.util.function.Predicate<E> not(java.util.function.Predicate<E> predicate) {
        return predicate.negate();
    }

    public static String[] toArray(Collection<String> collection) {
        if (isEmpty(collection)) {
            return new String[0];
        }
        return collection.toArray(new String[collection.size()]);
    }

    public static <E> List<List<E>> partition(List<E> list, int size) {
        List<List<E>> partitionedList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(list)) {
            return partitionedList;
        }

        List<E> currentList = null;
        int currentSize = 0;
        for (E e : list) {
            if (currentList == null || currentSize >= size) {
                currentSize = 0;
                currentList = Lists.newArrayList();
                partitionedList.add(currentList);
            }

            currentList.add(e);
            currentSize++;
        }

        return partitionedList;
    }

    public static <E> List<List<E>> partition(Collection<E> list, int size) {
        List<List<E>> partitionedList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(list)) {
            return partitionedList;
        }

        List<E> currentList = null;
        int currentSize = 0;
        for (E e : list) {
            if (currentList == null || currentSize >= size) {
                currentSize = 0;
                currentList = Lists.newArrayList();
                partitionedList.add(currentList);
            }

            currentList.add(e);
            currentSize++;
        }

        return partitionedList;
    }

    public static <K1, K2, V> Map<K2, V> transformMap(Map<K1, V> map, Transformer<K1, K2> keyTransformer) {
        if (isEmpty(map)) {
            return Collections.emptyMap();
        }

        Map<K2, V> rv = Maps.newHashMap();
        for (Map.Entry<K1, V> entry : map.entrySet()) {
            K2 newKey = keyTransformer.transform(entry.getKey());
            if (newKey != null) {
                rv.put(newKey, entry.getValue());
            }
        }
        return rv;
    }

    public static <K, K1, V, V1> Map<K1, V1> transformMap(Map<K, V> map, Transformer<K, K1> keyTransformer,
            Transformer<V, V1> valueTransformer) {
        if (isEmpty(map)) {
            return Collections.emptyMap();
        }

        Map<K1, V1> rv = Maps.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K1 newKey = keyTransformer.transform(entry.getKey());
            if (newKey != null) {
                V1 newValue = valueTransformer.transform(entry.getValue());
                rv.put(newKey, newValue);
            }
        }
        return rv;
    }

    public static <K, V, V1> Map<K, V1> transformMapValues(Map<K, V> map, Transformer<V, V1> valueTransformer) {
        if (isEmpty(map)) {
            return Collections.emptyMap();
        }

        Map<K, V1> rv = Maps.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V1 newValue = valueTransformer.transform(entry.getValue());
            rv.put(entry.getKey(), newValue);
        }
        return rv;
    }

    public static <K, K1, V> Map<K1, V> transformToMapWithFilter(Collection<K> kCollection,
            Transformer<K, K1> keyTransformer,
            Transformer<K, V> valueTransformer, Predicate<K> predicate) {
        if (isEmpty(kCollection)) {
            return Collections.emptyMap();
        }

        Map<K1, V> rv = Maps.newHashMap();
        for (K k : kCollection) {
            if (predicate.apply(k)) {
                K1 newKey = keyTransformer.transform(k);
                if (newKey != null) {
                    V newValue = valueTransformer.transform(k);
                    rv.put(newKey, newValue);
                }
            }
        }
        return rv;
    }

    public static <K, V, E> Map<K, Collection<V>> transformToMap(Collection<E> collection,
            Function<E, Tuple<K, V>> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }

        Map<K, Collection<V>> kvMap = Maps.newHashMap();

        for (E e : collection) {
            final Tuple<K, V> tuple = function.apply(e);
            if (tuple != null) {
                Collection<V> vCollection = kvMap.get(tuple.v1());
                if (vCollection == null) {
                    vCollection = Lists.newArrayList();
                    kvMap.put(tuple.v1(), vCollection);
                }

                vCollection.add(tuple.v2());
            }
        }

        return kvMap;
    }

    public static <T> List<List<T>> transpose(List<List<T>> table) {
        final List<List<T>> ret = new ArrayList<>();
        final int N = table.get(0).size();
        for (int index = 0; index < N; index++) {
            List<T> col = new ArrayList<>();
            for (List<T> row : table) {
                if (row.size() != N) {
                    //invalid matrix - return original
                    return table;
                }
                col.add(row.get(index));
            }
            ret.add(col);
        }
        return ret;
    }

    public static <T> Collection<T> addAll(Collection<T> existingData, Collection<T> dataToAdd) {
        if (CollectionUtils.isNotEmpty(dataToAdd)) {
            if (CollectionUtils.isEmpty(existingData)) {
                existingData = new HashSet<>();
            }
            existingData.addAll(nullAndEmptySafeValueCollection(dataToAdd));
        }
        return existingData;
    }

    public static <S, T> Map<S, T> put(Map<S, T> existingData, S key, T value) {
        if (key != null) {
            if (MapUtils.isEmpty(existingData)) {
                existingData = new HashMap<>();
            }
            if (value != null) {
                existingData.put(key, value);
            }
        }
        return existingData;
    }

    public static <T> Collection<List<T>> createBatches(Collection<T> data, int batchSize) {
        return createBatches(toList(data), batchSize);
    }

    public static <T> Collection<List<T>> createBatches(List<T> data, int batchSize) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        Collection<List<T>> batches = new ArrayList<>();
        int noBatches = data.size() / batchSize + (data.size() % batchSize > 0 ? 1 : 0);
        for (int i = 0; i < noBatches; i++) {
            int startIndex = i * batchSize;
            int endIndex = startIndex + batchSize > data.size() ? data.size() : startIndex + batchSize;
            batches.add(new ArrayList<>(data.subList(startIndex, endIndex)));
        }
        return batches;
    }

    public static <T> Collection<Set<T>> createBatches(Set<T> data, int batchSize) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        Collection<List<T>> batches = createBatches(new ArrayList<>(data), batchSize);
        Collection<Set<T>> batchesAsSet = new ArrayList<>();
        for (List<T> batchedItems : nullSafeValueList(batches)) {
            batchesAsSet.add(new TreeSet<>(batchedItems));
        }
        return batchesAsSet;
    }

    public static <T> Collection<Set<T>> createBatchesWithHashSet(Set<T> data, int batchSize) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        Collection<List<T>> batches = createBatches(new ArrayList<>(data), batchSize);
        Collection<Set<T>> batchesAsSet = new ArrayList<>();
        for (List<T> batchedItems : batches) {
            batchesAsSet.add(new HashSet<>(batchedItems));
        }
        return batchesAsSet;
    }

    public static <T> Collection<List<T>> createMaxBatches(T[] data, int numOfBatches) {
        return createMaxBatches(Lists.newArrayList(data), numOfBatches);
    }

    public static <T> Collection<List<T>> createMaxBatches(List<T> data, int numOfBatches) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }

        if (data.size() < numOfBatches) {
            return createBatches(data, 1);
        }

        List<List<T>> batches = new ArrayList<>();
        int batchSize = data.size() / numOfBatches;
        int remainder = data.size() % numOfBatches;
        int startIndex = 0;

        for (int i = 0; i < numOfBatches; i++) {
            int endIndex = (startIndex + batchSize > data.size()) ? data.size() : startIndex + batchSize;
            if (endIndex < data.size() && remainder > 0) {
                endIndex++;
                remainder--;
            }
            batches.add(Lists.newArrayList(data.subList(startIndex, endIndex)));
            startIndex = endIndex;
        }
        return batches;
    }

    public static <T> Map<String, T> putAll(Map<String, T> existingData, Map<String, T> dataToAdd) {
        if (MapUtils.isNotEmpty(dataToAdd)) {
            if (MapUtils.isEmpty(existingData)) {
                existingData = new HashMap<>();
            }
            existingData.putAll(dataToAdd);
        }
        return existingData;
    }

    /**
     * Group by on a collection based on your group by strategy.
     */
    public static <T> Map<GroupKey, Collection<T>> group(Collection<T> tCollection,
            GroupByStrategy<T> groupByStrategy) {

        if (CollectionUtils.isEmpty(tCollection) || groupByStrategy == null) {
            return Collections.emptyMap();
        }

        Map<GroupKey, Collection<T>> result = new HashMap<>();
        for (T t : tCollection) {
            GroupKey groupKey = groupByStrategy.group(t);
            if (groupKey != null) {
                Collection<T> groupedTs = result.get(groupKey);
                if (CollectionUtils.isEmpty(groupedTs)) {
                    groupedTs = new ArrayList<>();
                    result.put(groupKey, groupedTs);
                }
                groupedTs.add(t);
            }
        }

        return result;
    }

    public static <T> List<T> toList(Collection<T> collection) {
        if (collection instanceof List) {
            //noinspection unchecked
            return (List) collection;
        }
        if (collection == null) {
            return Lists.newArrayList();
        }
        return new ArrayList<>(collection);
    }

    public static <T> List<T> toMutalbleList(Collection<T> collection) {
        return new ArrayList<>(nullSafeValueCollection(collection));
    }

    public static <T> Set<T> toMutalbleSet(Collection<T> collection) {
        return new HashSet<>(nullSafeValueCollection(collection));
    }

    public static <T> Set<T> toSet(Collection<T> collection) {
        if (collection instanceof Set) {
            //noinspection unchecked
            return (Set) collection;
        }
        if (collection == null) {
            return Sets.newHashSet();
        }
        return new HashSet<>(collection);
    }

    public static <T extends Comparable> TreeSet<T> toTreeSet(Collection<T> collection) {
        if (collection instanceof TreeSet) {
            //noinspection unchecked
            return (TreeSet) collection;
        }
        if (collection == null) {
            return Sets.newTreeSet();
        }
        return new TreeSet<>(collection);
    }

    public static <T> Set<T> nullSafeSet(Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptySet();
        }
        return data;
    }

    public static <T> Set<T> nullSafeNonEmptySet(Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Sets.newHashSet();
        }
        return data;
    }

    public static <T> Collection<T> nullSafeCollection(Collection<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return data;
    }

    public static <T> List<T> nullSafeList(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return data;
    }

    public static <T> List<T> nonEmptyList(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Lists.newArrayList();
        }
        return data;
    }

    public static <T> List<T> nullSafeListFromCollection(Collection<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(data);
    }

    /**
     * always returns an arraylist ensuring that the list is mutable. This method ignores the actual implementation of
     * parameter and always returns the arrayList. That is why return type has been fixed to ArrayList.
     */
    public static <T> ArrayList<T> nullSafeMutableList(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(data);
    }

    public static <T> Set<T> nullSafeMutableSet(Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Sets.newHashSet();
        }
        return Sets.newHashSet(data);
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Set<T> getMutableSetIfNullOrEmpty(Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            data = Sets.newHashSet();
        }
        return data;
    }

    /**
     * Transform a list with each element
     *
     * @param data data
     * @param prefix prefix to append in each element
     * @param separator separator between prefix and separator.
     */
    public static <T> List<String> transformToListWithPrefix(Collection<T> data, final Object prefix,
            final String separator) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return transformToList(data, new Transformer<T, String>() {
            @Override
            public String transform(T t) {
                return prefix + separator + t;
            }
        });
    }

    public static <T> List<String> transformToListWithSuffix(Collection<T> data, final Object suffix,
            final String separator) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return transformToList(data, new Transformer<T, String>() {
            @Override
            public String transform(T t) {
                return t + separator + suffix;
            }
        });
    }

    public static <P, Q, R> Map<P, R> transformValuesInMap(Map<P, Q> params, Transformer<Q, R> transformer) {
        if (MapUtils.isEmpty(params)) {
            return Collections.emptyMap();
        }
        Map<P, R> transformedMap = Maps.newHashMap();
        for (Map.Entry<P, Q> entry : params.entrySet()) {
            transformedMap.put(entry.getKey(), transformer.transform(entry.getValue()));
        }
        return transformedMap;
    }

    /**
     * This method is particularly helpful when you want to maintain type Key constants as enums but would later want
     * the key to be transformed into a string key.
     *
     * @param originalMap The {@link Map} which needs to be transformed
     * @param transformer The {@link Transformer} to use.
     * @param <K> The key type in the Map which should extend an Enum.
     * @param <V> The value type in the Map
     * @param <T> The transformed key type
     * @return The transformed {@link Map} with the key's of the original map. transformed,
     */
    public static <K, V, T> Map<T, V> transformKeysInMap(Map<K, V> originalMap, Transformer<K, T> transformer) {
        if (MapUtils.isEmpty(originalMap)) {
            return Collections.emptyMap();
        }
        Map<T, V> transformedMap = Maps.newHashMap();
        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            transformedMap.put(transformer.transform(entry.getKey()), entry.getValue());
        }
        return transformedMap;
    }

    public static <T> Collection<T> nullSafeValueCollection(Collection<T> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }
        return Collections2.filter(objs, new Predicate<T>() {
            @Override
            public boolean apply(@Nullable T t) {
                return t != null;
            }
        });
    }

    public static <S, T> Map<S, T> nullSafeValueMap(Map<S, T> map) {
        if (MapUtils.isEmpty(map)) {
            return Maps.newHashMap();
        }
        Map<S, T> result = new HashMap<>();
        for (Map.Entry<S, T> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public static <T> List<T> nullAndEmptySafeValueList(Collection<T> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }
        return CollectionUtils.toList(Collections2.filter(objs, new Predicate<T>() {
            @Override
            public boolean apply(@Nullable T t) {
                if (t == null) {
                    return false;
                }
                //noinspection SimplifiableIfStatement
                if (t instanceof String) {
                    return StringUtils.isNotBlank((String) t);
                }
                return true;
            }
        }));
    }

    public static <T> Set<T> nonEmptySet(Set<T> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new HashSet<>();
        }
        return values;
    }

    public static <T> Set<T> nullAndEmptySafeValueSet(Collection<T> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptySet();
        }

        return CollectionUtils.toSet(Collections2.filter(objs, new Predicate<T>() {
            @Override
            public boolean apply(@Nullable T t) {
                if (t == null) {
                    return false;
                }
                //noinspection SimplifiableIfStatement
                if (t instanceof String) {
                    return StringUtils.isNotEmpty((String) t);
                }
                return true;
            }
        }));
    }

    public static <T> Collection<T> nullAndEmptySafeValueCollection(Collection<T> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }
        return Collections2.filter(objs, new Predicate<T>() {
            @Override
            public boolean apply(@Nullable T t) {
                if (t == null) {
                    return false;
                }
                //noinspection SimplifiableIfStatement
                if (t instanceof String) {
                    return StringUtils.isNotEmpty((String) t);
                }
                return true;
            }
        });
    }

    public static <T, S> Map<T, S> nullSafeMap(Map<T, S> data) {
        if (data != null) {
            return data;
        }
        return Collections.emptyMap();
    }

    public static <T, S> Map<T, S> nullSafeMutableMap(Map<T, S> data) {
        if (data != null) {
            return Maps.newHashMap(data);
        }
        return Maps.newHashMap();
    }

    /**
     * Return new list instance with null values filtered.
     */
    public static <T> List<T> nullSafeValueList(Collection<T> values) {
        Collection<T> nullSafeCollection = nullSafeValueCollection(values);
        return toList(nullSafeCollection);
    }

    public static <T> Set<T> nullSafeValueSet(Collection<T> values) {
        Collection<T> nullSafeCollection = nullSafeValueCollection(values);
        return toSet(nullSafeCollection);
    }

    public static <T> Set<T> getUnmodifiableSet(final Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(data);
    }

    public static Map<String, List<String>> mergeMapValuesWithNoDuplicates(Map<String, List<String>> oldValues,
            Map<String, List<String>> newValues) {

        Map<String, List<String>> mergedValues = Maps.newHashMap();
        if (MapUtils.isEmpty(oldValues) && MapUtils.isEmpty(newValues)) {
            return mergedValues;
        }

        if (MapUtils.isEmpty(oldValues)) {
            mergedValues.putAll(newValues);
            return mergedValues;
        }

        if (MapUtils.isEmpty(newValues)) {
            mergedValues.putAll(oldValues);
            return mergedValues;
        }

        Map<String, List<String>> copyOfNewValues = Maps.newHashMap(newValues);
        for (Map.Entry<String, List<String>> oldValuesEntry : oldValues.entrySet()) {
            String key = oldValuesEntry.getKey();
            List<String> oldValue = oldValuesEntry.getValue();
            List<String> newValue = copyOfNewValues.remove(key);
            mergedValues.put(key, mergeListsWithNoDuplicate(oldValue, newValue));
        }

        if (MapUtils.isNotEmpty(copyOfNewValues)) {
            mergedValues.putAll(copyOfNewValues);
        }

        return mergedValues;
    }

    public static <T> List<T> nullAndEmptySafeValueList(T[] values) {
        if (values == null) {
            return Collections.emptyList();
        }
        List<T> nullSafeValues = Lists.newArrayList();
        for (T value : values) {
            if (value != null) {
                nullSafeValues.add(value);
            }
        }
        return nullSafeValues;
    }

    public static <T> boolean containsAny(Collection<T> source, Collection<T> values) {
        boolean isSourceEmpty = CollectionUtils.isEmpty(source);
        boolean isValuesEmpty = CollectionUtils.isEmpty(values);
        if (isSourceEmpty && isValuesEmpty) {
            return true;
        }
        if (isSourceEmpty || isValuesEmpty) {
            return false;
        }

        Set<T> sourceSet = toSet(source);
        for (T value : values) {
            if (sourceSet.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean containsAll(Collection<T> source, Collection<T> values) {
        boolean isSourceEmpty = CollectionUtils.isEmpty(source);
        boolean isValuesEmpty = CollectionUtils.isEmpty(values);
        if (isSourceEmpty && isValuesEmpty) {
            return true;
        }
        if (isSourceEmpty || isValuesEmpty) {
            return false;
        }

        Set<T> sourceSet = toSet(source);
        for (T value : values) {
            if (!sourceSet.contains(value)) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> transformToLowerCaseSet(Collection<String> values) {
        return transformToSet(values, STRING_VALUE_TO_TRIMMED_LOWER_CASE);
    }

    public static Set<String> transformToUpperCaseSet(Collection<String> values) {
        return transformToSet(values, STRING_VALUE_TO_TRIMMED_UPPER_CASE);
    }

    public static <K, V> Collection<V> getAll(Map<K, V> map, Collection<K> keys) {
        if (isEmpty(map) || isEmpty(keys)) {
            return Collections.emptyList();
        }

        List<V> values = new ArrayList<>();
        for (K key : keys) {
            V value = map.get(key);
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }

    public static <K> Collection<K> getAllAndKeyIfNull(Map<K, K> map, Collection<K> keys) {
        if (isEmpty(keys)) {
            return Collections.emptyList();
        }
        if (isEmpty(map)) {
            return toList(keys);
        }

        List<K> values = new ArrayList<>();
        for (K key : keys) {
            K value = map.get(key);
            if (value != null) {
                values.add(value);
            } else {
                values.add(key);
            }
        }
        return values;
    }

    /**
     * Returns removed key-value pairs
     */
    public static <K, V> void removeAll(Map<K, V> map, Collection<K> keys) {
        if (isEmpty(map) || isEmpty(keys)) {
            return;
        }

        Map<K, V> removedMap = new HashMap<>();
        for (K key : keys) {
            V value = map.remove(key);
            removedMap.put(key, value);
        }
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> map, Predicate<K> predicate) {
        if (isEmpty(map) || predicate == null) {
            return Collections.emptyMap();
        }
        Map<K, V> filtered = new HashMap<>();
        for (K k : map.keySet()) {
            boolean add = predicate.apply(k);
            if (add) {
                filtered.put(k, map.get(k));
            }
        }
        return filtered;
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> map, Predicate<V> predicate) {
        if (isEmpty(map) || predicate == null) {
            return Collections.emptyMap();
        }

        Map<K, V> filtered = new HashMap<>();
        map.forEach((k, v) -> {
            boolean add = predicate.apply(v);
            if (add) {
                filtered.put(k, map.get(k));
            }
        });
        return filtered;
    }

    public static List<String> nullAndBlankSafeValueList(Collection<String> tags) {
        return CollectionUtils.filterList(tags, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String s) {
                return StringUtils.isNotBlank(s);
            }
        });
    }

    public static Set<String> nullAndBlankSafeValueSet(Collection<String> input) {
        if (CollectionUtils.isEmpty(input)) {
            return Sets.newHashSet();
        }

        final Set<String> rv = Sets.newHashSet();
        for (String s : input) {
            if (StringUtils.isNotBlank(s)) {
                rv.add(s);
            }
        }
        return rv;
    }

    /**
     * Deep Clone a Map with supported value as {@link List}
     *
     * @param map A map with value as List
     * @return Deep clone of a map with a value as list
     */
    public static Map deepClone(Map map) {
        if (MapUtils.isEmpty(map)) {
            return new HashMap();
        }
        Map newMap = new HashMap();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map) {
                value = deepClone((Map) value);
            } else if (value instanceof List) {
                value = deepClone((List) value);
            }
            newMap.put(key, value);
        }
        return newMap;
    }

    /**
     * Deep clone a List with supported value as {@link Map}
     *
     * @param list List to clone
     * @return Cloned List
     */
    public static List deepClone(List list) {
        if (isEmpty(list)) {
            return Lists.newArrayList();
        }
        List newList = Lists.newArrayList();
        for (Object value : list) {
            if (value instanceof Map) {
                value = deepClone((Map) value);
            } else if (value instanceof List) {
                value = deepClone((List) value);
            }
            newList.add(value);
        }
        return newList;
    }

    /**
     * Standard transform operation on collections, slightly altered for returning different collection.
     */
    public static <T, S> Collection<S> transform(Collection<? extends T> tCollection, Transformer<T, S> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        Collection<S> sCollection = new ArrayList<>();
        for (T t : tCollection) {
            S transformedObject = transformer.transform(t);
            if (transformedObject != null) {
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    public static <T> List<T> filterNull(List<? extends T> tCollection) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        List<T> sCollection = new ArrayList<>();
        for (T t : tCollection) {
            if (t != null) {
                sCollection.add(t);
            }
        }
        return sCollection;
    }

    public static <T> Set<T> filterNull(Set<? extends T> tCollection) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Sets.newHashSet();
        }

        Set<T> sCollection = Sets.newHashSet();
        for (T t : tCollection) {
            if (t != null) {
                sCollection.add(t);
            }
        }
        return sCollection;
    }

    /**
     * Standard transform operation on collections, slightly altered for returning different collection.
     */
    public static <T, S> Collection<S> transformWithFilter(Collection<T> tCollection, Transformer<T, S> transformer,
            Predicate<T> predicate) {

        if (CollectionUtils.isEmpty(tCollection)) {
            return Collections.emptyList();
        }

        Collection<S> sCollection = new ArrayList<>();
        for (T t : tCollection) {
            S transformedObject = transformer.transform(t);
            if (transformedObject != null && predicate.apply(t)) {
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    /**
     * Standard transform operation on collections, slightly altered for returning different collection.
     */
    public static <T, S> List<S> transformToListWithFilter(Collection<T> tCollection, Transformer<T, S> transformer,
            Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Collections.emptyList();
        }

        List<S> sList = new ArrayList<>();
        for (T t : tCollection) {
            S transformedObject = transformer.transform(t);
            if (transformedObject != null && predicate.apply(t)) {
                sList.add(transformedObject);
            }
        }
        return sList;
    }

    /**
     * Applies the standard reduce operation on a collection.
     *
     * @param tCollection input collection
     * @param sTransformer A transformer which converts each element in the collection to an intermediate object
     * @param accumulator A transformer which combines the intermediate object and current result to produce a new
     * result
     * @param <T> The type of each element in collection
     * @param <S> The type of the intermediate objects created from collection's elements
     * @param <V> The return type
     * @return Returns the reduced value
     */
    public static <T, S, V> V reduce(Collection<T> tCollection, Transformer<T, S> sTransformer,
            TransformerWithArgs<S, V, V> accumulator) {
        V ret = null;
        if (CollectionUtils.isNotEmpty(tCollection)) {
            for (T t : tCollection) {
                S transformedObject = sTransformer.transform(t);
                ret = accumulator.transform(transformedObject, ret);
            }
        }
        return ret;
    }

    /**
     * Filter the list by applying predicate.
     *
     * @return filtered list
     */
    public static <T> List<T> filterList(Collection<T> tList, Predicate<T> predicate) {
        List<T> filteredList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(tList)) {
            return filteredList;
        }
        for (T t : tList) {
            boolean add = predicate.apply(t);
            if (add) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    /**
     * Partition the list into 2 collections (matching, notMatching) of the specified target type by applying
     * predicate.
     *
     * @return tuple of list partition collections
     */
    public static <T, C extends Collection<T>> Tuple<C, C> partitionBy(Collection<T> tList,
            CollectionProvider<T, C> targetProvider,
            Predicate<T> predicate) {
        C matching = targetProvider.newCollection();
        C notMatching = targetProvider.newCollection();
        Tuple<C, C> rv = Tuple.of(matching, notMatching);

        if (CollectionUtils.isEmpty(tList)) {
            return rv;
        }

        for (T t : tList) {
            boolean match = predicate.apply(t);
            C target = match ? matching : notMatching;
            target.add(t);
        }
        return rv;
    }

    public static <T> Tuple<List<T>, List<T>> listPartitionBy(Collection<T> tList, Predicate<T> predicate) {
        return partitionBy(tList, CollectionUtils.<T>listProvider(), predicate);
    }

    public static <T> Tuple<Set<T>, Set<T>> setPartitionBy(Collection<T> tList, Predicate<T> predicate) {
        return partitionBy(tList, CollectionUtils.<T>setProvider(), predicate);
    }

    /**
     * Filter the first item in list by applying predicate.
     *
     * @return filtered Item
     */
    public static <T> T filterFirstItemInList(Collection<T> tList, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(tList)) {
            return null;
        }
        for (T t : tList) {
            boolean add = predicate.apply(t);
            if (add) {
                return t;
            }
        }
        return null;
    }

    public static <T> Set<T> filterSet(Collection<T> tSet, Predicate<T> predicate) {
        Set<T> filteredSet = Sets.newHashSet();
        if (CollectionUtils.isEmpty(tSet)) {
            return filteredSet;
        }
        for (T t : tSet) {
            boolean add = predicate.apply(t);
            if (add) {
                filteredSet.add(t);
            }
        }
        return filteredSet;
    }

    /*
        Gives the Predicate for distinct objects based on a key

        @param Function to relate key to the Object
     */
    public static <T> java.util.function.Predicate<T> distinctByKey(
            java.util.function.Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Transforms collection to set.
     */
    public static <T, S> Set<S> transformToSet(Collection<? extends T> tCollection, Transformer<T, S> transformer) {
        return transformIterableToSet(tCollection, transformer);
    }

    public static <T, S> Set<S> transformIterableToSet(Iterable<? extends T> iterable, Transformer<T, S> transformer) {
        if (iterable == null) {
            return Sets.newHashSet();
        }

        Set<S> sCollection = new HashSet<>();
        for (T t : iterable) {
            S transform = transformer.transform(t);
            if (transform != null) {
                sCollection.add(transform);
            }
        }
        return sCollection;
    }

    public static <T, S> Set<S> transformToConcurrentHashSet(Collection<? extends T> tCollection,
            Transformer<T, S> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Sets.newConcurrentHashSet();
        }

        Set<S> sCollection = Sets.newConcurrentHashSet();
        for (T t : tCollection) {
            S transform = transformer.transform(t);
            if (transform != null) {
                sCollection.add(transform);
            }
        }
        return sCollection;
    }

    public static <T, S> List<S> transformToList(T[] array, Transformer<T, S> transformer) {
        if (ArrayUtils.isEmpty(array)) {
            return Collections.emptyList();
        }

        List<S> sCollection = Lists.newArrayList();
        for (T t : array) {
            S transform = transformer.transform(t);
            if (transform != null) {
                sCollection.add(transform);
            }
        }
        return sCollection;
    }

    public static <T, S> List<S> transformAndAddToList(List<T> input, Transformer<T, List<S>> transformer) {
        if (CollectionUtils.isEmpty(input)) {
            return Collections.emptyList();
        }

        List<S> sCollection = Lists.newArrayList();
        for (T t : input) {
            List<S> transform = transformer.transform(t);
            if (CollectionUtils.isEmpty(transform)) {
                continue;
            }
            sCollection.addAll(transform);
        }
        return sCollection;
    }

    public static <T, S> Set<S> transformToSet(T[] array, Transformer<T, S> transformer) {
        if (ArrayUtils.isEmpty(array)) {
            return Sets.newHashSet();
        }

        Set<S> sCollection = new HashSet<>();
        for (T t : array) {
            S transform = transformer.transform(t);
            if (transform != null) {
                sCollection.add(transform);
            }
        }
        return sCollection;
    }

    /**
     * Standard transform operation on collections, slightly altered for returning different collection.
     */
    public static <From, To> List<To> transformToList(Collection<? extends From> tCollection,
            Transformer<From, To> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        List<To> sCollection = new ArrayList<>();
        for (From t : tCollection) {
            To transformedObject = transformer.transform(t);
            if (transformedObject != null) {
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    public static <From, To> List<To> transformAndAppendToList(Collection<? extends From> tCollection,
            Transformer<From, To> transformer,
            List<To> existingCollection) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        if (existingCollection == null) {
            existingCollection = new ArrayList<>();
        }
        for (From t : tCollection) {
            To transformedObject = transformer.transform(t);
            if (transformedObject != null) {
                existingCollection.add(transformedObject);
            }
        }
        return existingCollection;
    }

    public static <From, To, Arg> List<To> transformToListWithArgs(Collection<? extends From> tCollection,
            TransformerWithArgs<From, To, Arg> transformer, Arg arg) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        List<To> sCollection = new ArrayList<>();
        for (From t : tCollection) {
            To transformedObject = transformer.transform(t, arg);
            if (transformedObject != null) {
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    /**
     * Transforms each element in the source collection and adds that to the target collection.
     */
    public static <From, To> void transformAndAddToCollection(Collection<? extends From> sourceCollection,
            Transformer<From, To> transformer,
            Collection<To> targetCollection) {
        PreConditions.notNull(targetCollection);
        PreConditions.notNull(transformer);
        for (From f : nullSafeCollection(sourceCollection)) {
            targetCollection.add(transformer.transform(f));
        }
    }

    public static <From, To, Arg> List<To> transformToListWithListArgs(List<? extends From> tCollection,
            TransformerWithArgs<From, To, Arg> transformer, List<Arg> argList) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }

        List<To> sCollection = new ArrayList<>();
        for (int i = 0; i < tCollection.size(); i++) {
            From t = tCollection.get(i);
            Arg arg = argList.get(i);

            To transformedObject = transformer.transform(t, arg);
            if (transformedObject != null) {
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    public static <T, S> List<S> transformToFlattenedList(Collection<T> tCollection,
            Transformer<T, Collection<S>> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Collections.emptyList();
        }
        List<S> flattenedList = new ArrayList<>();
        for (T t : tCollection) {
            Collection<S> transformedCollection = transformer.transform(t);
            if (isNotEmpty(transformedCollection)) {
                flattenedList.addAll(transformedCollection);
            }
        }
        return flattenedList;
    }

    public static <From, To, Arg> Map<From, To> transformToMapWithArgs(Collection<? extends From> tCollection,
            TransformerWithArgs<From, To, Arg> transformer, Arg arg) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Maps.newHashMap();
        }

        Map<From, To> sMap = Maps.newHashMap();
        for (From from : tCollection) {
            To transformedObject = transformer.transform(from, arg);
            if (transformedObject != null) {
                sMap.put(from, transformedObject);
            }
        }
        return sMap;
    }

    public static <From, To, Arg> Map<To, From> transformToMapWithArguments(Collection<? extends From> tCollection,
            TransformerWithArgs<From, To, Arg> transformer, Arg arg) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Maps.newHashMap();
        }

        Map<To, From> sMap = Maps.newHashMap();
        for (From from : tCollection) {
            To transformedObject = transformer.transform(from, arg);
            if (transformedObject != null) {
                sMap.put(transformedObject, from);
            }
        }
        return sMap;
    }

    public static <From, To> List<To> transformToListWithNull(Collection<From> tCollection,
            Transformer<From, To> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Lists.newArrayList();
        }
        List<To> sCollection = new ArrayList<>();
        for (From t : tCollection) {
            if (t == null) {
                sCollection.add(null);
            } else {
                To transformedObject = transformer.transform(t);
                sCollection.add(transformedObject);
            }
        }
        return sCollection;
    }

    /**
     * Transforms the colletion to map. Key is created by applying transform function on value, by specified
     * transformer.
     */
    public static <ParentT, ChildT extends ParentT, S> Map<S, ChildT> transformToMap(Collection<ChildT> tCollection,
            Transformer<ParentT, S> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Maps.newHashMap();
        }

        return transformCollectionToMap(tCollection, transformer);
    }

    public static <T, S> LinkedHashMap<S, T> transformToLinkedHashMap(Collection<T> tCollection,
            Transformer<T, S> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Maps.newLinkedHashMap();
        }

        return transformCollectionToLinkedHashMap(tCollection, transformer);
    }

    public static <K, K1, V> LinkedHashMap<K1, V> transformToLinkedHashMap(Collection<K> kCollection,
            Transformer<K, K1> keyTransformer,
            Transformer<K, V> valueTransformer) {
        if (CollectionUtils.isEmpty(kCollection)) {
            return Maps.newLinkedHashMap();
        }

        LinkedHashMap<K1, V> map = new LinkedHashMap<>();
        for (K k : kCollection) {
            K1 k1 = keyTransformer.transform(k);
            V v = valueTransformer.transform(k);
            if (k1 != null || v != null) {
                map.put(k1, v);
            }
        }
        return map;
    }

    public static <T, S> Map<S, Collection<T>> transformToMultiMap(Collection<T> tCollection,
            Transformer<T, S> transformer) {
        HashMultimap<S, T> hashMultimap = HashMultimap.create();
        for (T value : tCollection) {
            if (value != null) {
                hashMultimap.put(transformer.transform(value), value);
            }
        }
        return hashMultimap.asMap();
    }

    public static <T, S> Map<S, Collection<T>> transformToLinkedMultiMap(Collection<T> tCollection,
            Transformer<T, S> transformer) {
        LinkedHashMultimap<S, T> linkedHashMultimap = LinkedHashMultimap.create();
        for (T value : tCollection) {
            if (value != null) {
                linkedHashMultimap.put(transformer.transform(value), value);
            }
        }
        return linkedHashMultimap.asMap();
    }

    public static <K, V, E> Map<K, V> transformToValueMap(Collection<E> eCollection,
            Transformer<? super E, K> transformer1,
            Transformer<? super E, V> transformer2) {
        if (CollectionUtils.isEmpty(eCollection)) {
            return Maps.newHashMap();
        }

        Map<K, V> map = new HashMap<>();
        for (E e : eCollection) {
            K k = transformer1.transform(e);
            V v = transformer2.transform(e);
            if (k != null || v != null) {
                map.put(k, v);
            }
        }
        return map;
    }

    public static <K, V> Map<K, List<V>> transformToMultiValuedMapList(Collection<V> collection,
            Transformer<V, K> transformer) {
        Map<K, List<V>> rv = Maps.newHashMap();
        if (isEmpty(collection)) {
            return rv;
        }
        for (V e : collection) {
            if (e == null) {
                continue;
            }
            K key = transformer.transform(e);
            if (key == null) {
                continue;
            }
            addToMultivaluedMapList(rv, key, e);
        }
        return rv;
    }

    public static <K, V, E> Map<K, List<V>> transformToMultiValuedMapList(Collection<E> collection,
            Transformer<E, K> keyTransformer,
            Transformer<E, V> valueTransformer) {
        Map<K, List<V>> rv = Maps.newHashMap();
        if (isEmpty(collection)) {
            return rv;
        }
        for (E e : collection) {
            if (e == null) {
                continue;
            }
            K key = keyTransformer.transform(e);
            if (key == null) {
                continue;
            }
            V value = valueTransformer.transform(e);
            if (value == null) {
                continue;
            }
            addToMultivaluedMapList(rv, key, value);
        }
        return rv;
    }

    public static <K, V> Map<K, Set<V>> transformToMultiValuedMapSet(Collection<V> collection,
            Transformer<V, K> transformer) {
        Map<K, Set<V>> rv = Maps.newHashMap();
        if (isEmpty(collection)) {
            return rv;
        }
        for (V e : collection) {
            if (e == null) {
                continue;
            }
            K key = transformer.transform(e);
            if (key == null) {
                continue;
            }
            addToMultivaluedMapSet(rv, key, e);
        }
        return rv;
    }

    public static <C, K, V> Map<K, Set<V>> transformToMultiValuedMapSet(Collection<C> collection,
            Transformer<C, K> keyTransformer,
            Transformer<C, V> valueTransformer) {
        Map<K, Set<V>> rv = Maps.newHashMap();
        for (C e : collection) {
            if (e == null) {
                continue;
            }
            K key = keyTransformer.transform(e);
            if (key == null) {
                continue;
            }
            V value = valueTransformer.transform(e);
            if (value == null) {
                continue;
            }
            addToMultivaluedMapSet(rv, key, value);
        }
        return rv;
    }

    public static <T, S> Map<T, S> transformToMapWithEqualKeysAndValues(Collection<T> tCollection,
            Collection<S> sCollection) {
        if (CollectionUtils.isEmpty(tCollection) || CollectionUtils.isEmpty(sCollection)) {
            return Maps.newHashMap();
        }
        if (tCollection.size() != sCollection.size()) {
            throw new IllegalArgumentException("Both Collections should contain the same number of elements");
        }
        Iterator<T> tIterator = tCollection.iterator();
        Iterator<S> sIterator = sCollection.iterator();
        Map<T, S> rv = Maps.newLinkedHashMap();

        while (tIterator.hasNext() && sIterator.hasNext()) {
            rv.put(tIterator.next(), sIterator.next());
        }

        return rv;
    }

    public static <T, S> Map<S, T> transformToMutableMap(Collection<T> tCollection, Transformer<T, S> transformer) {
        if (CollectionUtils.isEmpty(tCollection)) {
            return Maps.newHashMap();
        }

        return transformCollectionToMap(tCollection, transformer);
    }

    private static <ParentT, ChildT extends ParentT, S> Map<S, ChildT> transformCollectionToMap(
            Collection<ChildT> tCollection,
            Transformer<ParentT, S> transformer) {
        Map<S, ChildT> map = new HashMap<>();
        for (ChildT t : tCollection) {
            S transform = transformer.transform(t);
            if (transform != null) {
                map.put(transform, t);
            }
        }
        return map;
    }

    private static <T, S> LinkedHashMap<S, T> transformCollectionToLinkedHashMap(Collection<T> tCollection,
            Transformer<T, S> transformer) {
        LinkedHashMap<S, T> map = new LinkedHashMap<>();
        for (T t : tCollection) {
            S transform = transformer.transform(t);
            if (transform != null) {
                map.put(transform, t);
            }
        }
        return map;
    }

    public static <T> T getFirstElementFromCollection(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    /*public static final Transformer<String, Long> STR_TO_LONG_SAFE_TRANSFORMER = new Transformer<String, Long>() {
        @Override
        public Long transform(String t) {
            if (!SprStringUtils.isBlank(t) && !"null".equals(t)) {
                try {
                    return Long.valueOf(t.trim());
                } catch (NumberFormatException e) {
                    //eat up the error
                }
            }
            return null;
        }
    };

    public static final Transformer<String, Double> STR_TO_DOUBLE_SAFE_TRANSFORMER = new Transformer<String, Double>() {
        @Override
        public Double transform(String t) {
            if (!SprStringUtils.isBlank(t) && !"null".equals(t)) {
                try {
                    return Double.valueOf(t.trim());
                } catch (NumberFormatException e) {
                    //eat up the error
                }
            }
            return null;
        }
    };*/

    public static <T> T getFirstNonNullElementFromCollection(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }

        for (T next : collection) {
            if (next != null) {
                return next;
            }
        }
        return null;
    }

    public static <T> T getFirstNonNullElement(T[] objects) {
        if (ArrayUtils.isEmpty(objects)) {
            return null;
        }

        for (T next : objects) {
            if (next != null) {
                return next;
            }
        }
        return null;
    }

    /**
     * @return The first element or null of this collection, safely.
     */
    public static <T> T head(Collection<T> collection) {
        return getFirstElementFromCollection(nullSafeCollection(collection));
    }

    public static <T> CollectionProvider<T, Set<T>> setProvider() {
        return new CollectionProvider<T, Set<T>>() {
            @Override
            public Set<T> newCollection() {
                return Sets.newHashSet();
            }
        };
    }

    public static <T> CollectionProvider<T, List<T>> listProvider() {
        return new CollectionProvider<T, List<T>>() {
            @Override
            public List<T> newCollection() {
                return Lists.newArrayList();
            }
        };
    }

    /**
     * Parses a comma separated string.
     *
     * @param input input string
     * @return list of tokens
     */
    public static List<String> parseCommaSeparatedString(String input) {
        return parseDelimiterSeparatedString(input, ",");
    }

    public static <T> List<T> parseCommaSeparatedString(String input, Transformer<String, T> transformer) {
        List<String> strList = parseCommaSeparatedString(input);
        List<T> ret = Lists.newArrayList();
        for (String str : strList) {
            T t = transformer.transform(str);
            if (t != null) {
                ret.add(t);
            }
        }
        return ret;
    }

    public static Set<String> parseCommaSeparatedStringToSet(String input) {
        return parseDelimiterSeparatedStringToSet(input, ",");
    }

    public static List<String> parseDelimiterSeparatedString(String input, String delimiter) {
        return parseDelimiterSeparatedString(input, delimiter, true);
    }

    public static Set<String> parseDelimiterSeparatedStringToSet(String input, String delimiter) {
        return parseDelimiterSeparatedStringToSet(input, delimiter, true);
    }

    public static List<String> parseDelimiterSeparatedString(String input, String delimiter, boolean trimTokens) {
        if (StringUtils.isBlank(input)) {
            return Collections.emptyList();
        }
        input = input.replaceAll("]", "");
        input = input.replaceAll("\\[", "");
        List<String> inputList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delimiter);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            inputList.add(token);
        }
        return inputList;
    }

    public static Set<String> parseDelimiterSeparatedStringToSet(String input, String delimiter, boolean trimTokens) {
        if (StringUtils.isBlank(input)) {
            return Collections.emptySet();
        }
        input = input.replaceAll("]", "");
        input = input.replaceAll("\\[", "");
        Set<String> inputSet = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delimiter);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            inputSet.add(token);
        }
        return inputSet;
    }

    public static List<String> parseListWithCommaSeparatedElements(List<String> values) {
        return CollectionUtils.toList(parseCollectionWithCommaSeparatedElements(values));
    }

    public static Collection<String> parseCollectionWithCommaSeparatedElements(Collection<String> values) {
        return parseCollectionWithDelimiterSeparatedElements(values, ",");
    }

    public static Collection<String> parseCollectionWithDelimiterSeparatedElements(Collection<String> values,
            String delimiter) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        Collection<String> result = Lists.newArrayList();
        for (String value : values) {
            Collection<String> elements = CollectionUtils.parseDelimiterSeparatedString(value, delimiter);
            result.addAll(elements);
        }
        return result;
    }

    public static List<String> parseCollectionAndDropDups(Collection<String> values, String delimiter) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        }

        Set<String> result = Sets.newHashSet();
        for (String value : values) {
            Collection<String> elements = CollectionUtils.parseDelimiterSeparatedString(value, delimiter);
            result.addAll(elements);
        }
        return Lists.newArrayList(result);
    }

    public static <KeyType, EntityType> Map<KeyType, Collection<EntityType>> groupByKey(
            Collection<EntityType> collectionData,
            Transformer<EntityType, KeyType> transformer) {
        if (CollectionUtils.isEmpty(collectionData)) {
            return Collections.emptyMap();
        }
        Map<KeyType, Collection<EntityType>> result = Maps.newHashMap();
        for (EntityType data : collectionData) {
            KeyType transform = transformer.transform(data);
            if (transform != null) {
                Collection<EntityType> values = result.get(transform);
                if (values == null) {
                    values = Lists.newArrayList();
                    result.put(transform, values);
                }
                values.add(data);
            }
        }
        return result;
    }

    public static <KeyType, EntityType, ValueType> Map<KeyType, Collection<ValueType>> groupByKey(
            Collection<EntityType> collectionData,
            Transformer<EntityType, KeyType> groupTransformer,
            Transformer<EntityType, ValueType> valueTransformer) {
        if (CollectionUtils.isEmpty(collectionData)) {
            return Collections.emptyMap();
        }
        Map<KeyType, Collection<ValueType>> result = Maps.newHashMap();
        for (EntityType data : collectionData) {
            KeyType transform = groupTransformer.transform(data);
            if (transform != null) {
                Collection<ValueType> values = result.get(transform);
                if (values == null) {
                    values = Lists.newArrayList();
                    result.put(transform, values);
                }
                values.add(valueTransformer.transform(data));
            }
        }
        return result;
    }

    public static <KeyType, EntityType, ValueType> Map<KeyType, ValueType> transformToMap(
            Collection<EntityType> collectionData,
            Transformer<EntityType, KeyType> groupTransformer,
            Transformer<EntityType, ValueType> valueTransformer) {
        if (CollectionUtils.isEmpty(collectionData)) {
            return Collections.emptyMap();
        }
        Map<KeyType, ValueType> result = Maps.newHashMap();
        for (EntityType data : collectionData) {
            KeyType key = groupTransformer.transform(data);
            if (key != null) {
                ValueType value = valueTransformer.transform(data);
                if (value != null) {
                    result.put(key, value);
                }
            }
        }
        return result;
    }

    public static <KeyType, EntityType> Map<KeyType, List<EntityType>> groupInListByKey(
            Collection<EntityType> collectionData,
            Transformer<EntityType, KeyType> transformer) {
        if (CollectionUtils.isEmpty(collectionData)) {
            return Collections.emptyMap();
        }
        Map<KeyType, List<EntityType>> result = Maps.newHashMap();
        for (EntityType data : collectionData) {
            KeyType transform = transformer.transform(data);
            if (transform != null) {
                List<EntityType> values = result.get(transform);
                if (values == null) {
                    values = Lists.newArrayList();
                    result.put(transform, values);
                }
                values.add(data);
            }
        }
        return result;
    }

    public static <K> void addOrUdpdateCount(Map<K, AtomicInteger> map, K key) {
        if (map == null) {
            return;
        }
        AtomicInteger value = map.get(key);
        if (value == null) {
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (map) {
                value = map.get(key);
                if (value == null) {
                    value = new AtomicInteger(0);
                    map.put(key, value);
                }
            }
        }
        value.incrementAndGet();
    }

    public static <KeyType, ValueType> Map<KeyType, List<ValueType>> groupByKeyMultiValued(Collection<ValueType> data,
            Transformer<ValueType, List<KeyType>> transformer) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyMap();
        }
        Map<KeyType, List<ValueType>> rv = Maps.newHashMap();
        for (ValueType valueType : data) {
            final List<KeyType> keyTypes = transformer.transform(valueType);
            if (CollectionUtils.isNotEmpty(keyTypes)) {
                for (KeyType transform : keyTypes) {
                    if (transform != null) {
                        addToMultivaluedMapList(rv, transform, valueType);
                    }
                }
            }
        }
        return rv;
    }

    public static <KeyType, ValueType> Set<KeyType> transformToSetWithMultiValueKeys(Collection<ValueType> data,
            Transformer<ValueType, List<KeyType>> transformer) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptySet();
        }
        Set<KeyType> rv = Sets.newHashSet();
        for (ValueType valueType : data) {
            final List<KeyType> keyTypes = transformer.transform(valueType);
            if (CollectionUtils.isNotEmpty(keyTypes)) {
                for (KeyType transform : keyTypes) {
                    if (transform != null) {
                        rv.add(transform);
                    }
                }
            }
        }
        return rv;
    }

    public static List<String> trimmedAndLowerCaseList(Collection<String> values) {
        return CollectionUtils.toList(trimmedAndLowerCaseCollection(values));
    }

    public static Collection<String> trimmedAndLowerCaseCollection(Collection<String> values) {
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        return CollectionUtils.nullAndEmptySafeValueCollection(
                CollectionUtils.transformToList(values, new CollectionUtils.Transformer<String, String>() {
                    @Override
                    public String transform(String tag) {
                        if (StringUtils.isNotBlank(tag)) {
                            tag = tag.trim().toLowerCase();
                            return tag;
                        }
                        return null;
                    }
                }));
    }

    public static List<Integer> transformLongToIntegerList(List<Long> longList) {
        Function<? super Long, ? extends Integer> fu = new Function<Long, Integer>() {
            @Override
            public Integer apply(@Nullable Long theVal) {
                return theVal != null ? theVal.intValue() : 0;
            }
        };

        return Lists.transform(longList, fu);
    }

    public static void removeDuplicatesPreservingOrder(List<String> values) {
        if (isEmpty(values)) {
            return;
        }

        Set<String> valueSet = new LinkedHashSet<>();
        valueSet.addAll(values);
        //If no duplicates return
        if (values.size() == valueSet.size()) {
            return;
        }
        values.clear();
        values.addAll(valueSet);
    }

    public static <T> List<T> removeDuplicates(List<T> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            Set<T> uniqueValues = Sets.newHashSet(values);
            List<T> uniqueValuesList = Lists.newArrayList();
            for (T value : values) {
                if (uniqueValues.contains(value)) {
                    uniqueValuesList.add(value);
                    uniqueValues.remove(value);
                }
            }
            return uniqueValuesList;
        }
        return values;
    }

    public static List<String> transFormToStringList(Collection<?> ids) {
        return CollectionUtils.transformToList(ids, OBJECT_TO_STR_TRANSFORMER);
    }

    public static List<Long> transFormToLongList(Collection<?> ids) {
        return CollectionUtils.transformToList(ids, OBJECT_LONG_TRANSFORMER);
    }

    public static List<Integer> transFormToIntegerList(Collection<?> ids) {
        return CollectionUtils.transformToList(ids, OBJECT_TO_INT_TRANSFORMER);
    }

    public static Long[] transformObjectToLongArray(Collection<?> ids) {
        final List<Long> longValues = transFormToLongList(ids);
        return longValues.toArray(new Long[longValues.size()]);
    }

    public static List<Double> convertDoubleArrayToList(double[] values) {
        List<Double> valuesList = Lists.newArrayList();
        for (double value : values) {
            valuesList.add(value);
        }
        return valuesList;
    }

    public static List<Long> convertLongArrayToList(long[] values) {
        List<Long> valuesList = new ArrayList<>();
        for (long value : values) {
            valuesList.add(value);
        }
        return valuesList;
    }

    public static double[] convertDoubleListToArray(List values) {
        if (CollectionUtils.isEmpty(values)) {
            return ArrayUtils.EMPTY_DOUBLE_ARRAY;
        }
        double[] valuesArray = new double[values.size()];
        int index = 0;
        for (Object value : values) {
            valuesArray[index++] = ((Number) value).doubleValue();
        }
        return valuesArray;
    }

    public static List<Long> convertToLongIds(Collection<String> ids) {
        return CollectionUtils.transformToList(ids, CollectionUtils.STR_TO_LONG_TRANSFORMER);
    }

    public static Set<Integer> convertToIntegerIds(Collection<String> ids) {
        return CollectionUtils.transformToSet(ids, CollectionUtils.STR_TO_INTEGER_TRANSFORMER);
    }

    public static Set<Long> convertToNullAndEmptySafeLongIdsSet(List<String> ids) {
        return CollectionUtils
                .transformToSet(nullAndEmptySafeValueCollection(ids), CollectionUtils.STR_TO_LONG_TRANSFORMER);
    }

    public static <T> Array<T> toFunctionalArray(T[] inputColl) {
        return fj.data.Array.array(inputColl);
    }

    public static <T> Array<T> toFunctionalArray(Collection<T> inputColl) {
        return fj.data.Array.array(toTypedArray(inputColl));
    }

    public static <T> T[] toTypedArray(Collection<? extends T> inputColl) {
        //noinspection unchecked
        return (T[]) inputColl.toArray();
    }

    public static <K, V> Map<K, V> nullAndEmptySafeMap(Map<K, V> map) {
        Map<K, V> nullSafeMap = nullSafeMap(map);
        // Since we do entry.setValue below, it could be a problem when somebody passes a singleton Map
        // OR in maps where entry.setValue is not supported
        // hence always copy the data to new Map
        nullSafeMap = copyMap(nullSafeMap);

        Iterator<Map.Entry<K, V>> itr = nullSafeMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<K, V> entry = itr.next();
            V value = entry.getValue();
            if (value == null) {
                itr.remove();
            }
            if (value instanceof String) {
                if (StringUtils.isEmpty((String) value)) {
                    itr.remove();
                }
            }
        }
        return nullSafeMap;
    }

    public static <K, V> Map<K, V> nullAndEmptySafeKeyValueMap(Map<K, V> map) {
        Map<K, V> nullSafeMap = nullSafeMutableMap(map);

        Iterator<Map.Entry<K, V>> itr = nullSafeMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<K, V> entry = itr.next();
            K key = entry.getKey();
            if (key == null) {
                itr.remove();
            }
            if (key instanceof String) {
                if (StringUtils.isEmpty((String) key)) {
                    itr.remove();
                }
            }
            V value = entry.getValue();
            if (value == null) {
                itr.remove();
            }
            if (value instanceof String) {
                if (StringUtils.isEmpty((String) value)) {
                    itr.remove();
                }
            }
        }
        return nullSafeMap;
    }

    public static <K, V> Map<K, List<V>> nullAndEmptySafeMultiValuedMap(Map<K, List<V>> map) {
        Map<K, List<V>> nullSafeMap = nullSafeMap(map);
        // Since we do entry.setValue below, it could be a problem when somebody passes a singleton Map
        // OR in maps where entry.setValue is not supported
        // hence always copy the data to new Map
        nullSafeMap = copyMultiValuedMap(nullSafeMap);

        Iterator<Map.Entry<K, List<V>>> itr = nullSafeMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<K, List<V>> entry = itr.next();
            List<V> values = nullSafeValueList(entry.getValue());
            if (CollectionUtils.isEmpty(values)) {
                itr.remove();
            } else {
                entry.setValue(values);
            }
        }
        return nullSafeMap;
    }

    private static <K, V> Map<K, V> copyMap(Map<K, V> nullSafeMap) {
        Map<K, V> newMap = new HashMap<>();
        newMap.putAll(nullSafeMap);
        return newMap;
    }

    private static <K, V> Map<K, List<V>> copyMultiValuedMap(Map<K, List<V>> nullSafeMap) {
        Map<K, List<V>> newMap = new HashMap<>();
        newMap.putAll(nullSafeMap);
        return newMap;
    }

    public static <K, V> Map<K, List<V>> nullAndEmptySafeMergeMultiValuedMaps(Map<K, List<V>> oldMap,
            Map<K, List<V>> newMap) {
        oldMap = nullAndEmptySafeMultiValuedMap(oldMap);
        newMap = nullAndEmptySafeMultiValuedMap(newMap);
        return mergeMultiValuedMaps(oldMap, newMap);
    }

    public static <K, V> Map<K, V> nullAndEmptySafeMergeMaps(Map<K, V> oldMap, Map<K, V> newMap) {
        oldMap = nullAndEmptySafeMap(oldMap);
        newMap = nullAndEmptySafeMap(newMap);
        return mergeMaps(oldMap, newMap);
    }

    /**
     * Merge both the map, and overwrite the key value from newMap.
     *
     * @param oldMap Old Map
     * @param newMap New Map
     * @param <K> Key Type
     * @param <V> Value Type
     * @return Merged Map
     */
    public static <K, V> Map<K, V> mergeMaps(Map<K, V> oldMap, Map<K, V> newMap) {
        Map<K, V> mergedMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(oldMap)) {
            mergedMap.putAll(oldMap);
        }
        if (MapUtils.isNotEmpty(newMap)) {
            mergedMap.putAll(newMap);
        }
        return mergedMap;
    }

    public static <K, V> Map<K, List<V>> mergeMultiValuedMaps(Map<K, List<V>> oldMap, Map<K, List<V>> newMap) {
        Map<K, List<V>> mergedMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(oldMap)) {
            mergedMap.putAll(oldMap);
        }

        if (MapUtils.isNotEmpty(newMap)) {
            for (Map.Entry<K, List<V>> entry : newMap.entrySet()) {
                K key = entry.getKey();
                mergedMap.put(key, nullAndEmptySafeMergeLists(mergedMap.get(key), entry.getValue()));
            }
        }
        return mergedMap;
    }

    public static <K, V> Map<K, Set<V>> mergeMultiUniqueValuedMaps(Map<K, Set<V>> oldMap, Map<K, Set<V>> newMap) {
        Map<K, Set<V>> mergedMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(oldMap)) {
            mergedMap.putAll(oldMap);
        }

        if (MapUtils.isNotEmpty(newMap)) {
            for (Map.Entry<K, Set<V>> entry : newMap.entrySet()) {
                K key = entry.getKey();
                mergedMap.put(key, mergeSetsToSingleSet(oldMap.get(key), entry.getValue()));
            }
        }
        return mergedMap;
    }

    public static <K> Set<K> mergeSetsToSingleSet(Set<K> set1, Set<K> set2) {
        Set<K> mergedSet = Sets.newHashSet();
        if (isNotEmpty(set1)) {
            mergedSet.addAll(set1);
        }
        if (isNotEmpty(set2)) {
            mergedSet.addAll(set2);
        }
        return mergedSet;
    }

    public static <T> List<T> mergeCollectionsToList(Collection<T> oldValues, Collection<T> newValues) {
        if (CollectionUtils.isEmpty(oldValues)) {
            return newValues == null ? Lists.<T>newArrayList() : Lists.newArrayList(newValues);
        }
        if (CollectionUtils.isEmpty(newValues)) {
            //noinspection ConstantConditions
            return oldValues == null ? Lists.<T>newArrayList() : Lists.newArrayList(oldValues);
        }

        Set<T> oldValuesSet = Sets.newHashSet(oldValues);
        List<T> mergedList = Lists.newArrayList(oldValues);
        for (T newValue : newValues) {
            if (!oldValuesSet.contains(newValue)) {
                mergedList.add(newValue);
            }
        }
        return mergedList;
    }

    public static <T> List<T> mergeLists(List<T> oldValues, List<T> newValues) {
        return mergeCollectionsToList(oldValues, newValues);
    }

    public static <T> List<T> nullAndEmptySafeMergeLists(List<T> oldValues, List<T> newValues) {
        List<T> cleanOldValues = nullAndEmptySafeValueList(oldValues);
        List<T> cleanNewValues = nullAndEmptySafeValueList(newValues);
        if (CollectionUtils.isEmpty(cleanOldValues)) {
            return cleanNewValues;
        }
        if (CollectionUtils.isEmpty(cleanNewValues)) {
            return cleanOldValues;
        }

        List<T> mergedList = Lists.newArrayList(cleanOldValues);
        for (T newValue : cleanNewValues) {
            if (!cleanOldValues.contains(newValue)) {
                mergedList.add(newValue);
            }
        }
        return mergedList;
    }

    public static <T> String joinListElements(Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        return Joiner.on(",").join(CollectionUtils.nullAndEmptySafeValueCollection(list));
    }

    public static <K, V> Cache<K, V> buildCache(int concurrency, int cacheSize) {
        return CacheBuilder.newBuilder().concurrencyLevel(concurrency).maximumSize(cacheSize).build();
    }

    public static <K, V> Cache<K, V> buildTimeBasedCache(TimeUnit timeUnit, int expiryDuration, int cacheSize) {
        return CacheBuilder.newBuilder().concurrencyLevel(4).maximumSize(cacheSize)
                .expireAfterWrite(expiryDuration, timeUnit).build();

    }

    public static <K, V> Cache<K, V> buildTimeBasedCache(int concurrency, TimeUnit timeUnit, int expiryDuration,
            int cacheSize) {
        return CacheBuilder.newBuilder().concurrencyLevel(concurrency).maximumSize(cacheSize)
                .expireAfterWrite(expiryDuration, timeUnit)
                .build();
    }

    public static <K, V> Cache<K, V> buildTimeBasedCacheWithListener(TimeUnit timeUnit, int expiryDuration,
            int cacheSize,
            RemovalListener removalListener) {
        //noinspection unchecked
        return CacheBuilder.newBuilder().concurrencyLevel(4).maximumSize(cacheSize)
                .expireAfterWrite(expiryDuration, timeUnit)
                .removalListener(removalListener).build();

    }

    @SuppressWarnings("Duplicates")
    public static <K> List<K> mergeListsWithNoDuplicate(List<K> list1, List<K> list2) {
        if (CollectionUtils.isEmpty(list1) && CollectionUtils.isEmpty(list2)) {
            return Collections.emptyList();
        }

        List<K> mergedList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(list1)) {
            mergedList.addAll(list2);
        } else if (CollectionUtils.isEmpty(list2)) {
            mergedList.addAll(list1);
        } else {
            Set<K> allValues = Sets.newHashSet();

            allValues.addAll(list1);
            allValues.addAll(list2);

            mergedList.addAll(allValues);
        }

        return mergedList;
    }

    @SuppressWarnings("Duplicates")
    public static <K> List<K> mergeCollectionsWithNoDuplicate(Collection<K> collection1, Collection<K> collection2) {
        if (CollectionUtils.isEmpty(collection1) && CollectionUtils.isEmpty(collection2)) {
            return Collections.emptyList();
        }

        List<K> mergedList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(collection1)) {
            mergedList.addAll(collection2);
        } else if (CollectionUtils.isEmpty(collection2)) {
            mergedList.addAll(collection1);
        } else {
            Set<K> allValues = Sets.newHashSet();

            allValues.addAll(collection1);
            allValues.addAll(collection2);

            mergedList.addAll(allValues);
        }

        return mergedList;
    }

    public static <K, V> void addToMultivaluedMapList(Map<K, List<V>> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (key == null || value == null) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            List<V> values = map.get(key);
            if (values == null) {
                values = Lists.newLinkedList();
                map.put(key, values);
            }
            values.add(value);
        }
    }

    public static <K, T, V> void addToNestedMap(Map<K, Map<T, V>> map, K outerKey, T innerKey, V value) {
        if (map == null || outerKey == null || innerKey == null) {
            return;
        }
        synchronized (map) {
            Map<T, V> innerMap = map.get(outerKey);
            if (innerMap == null) {
                innerMap = Maps.newHashMap();
                map.put(outerKey, innerMap);
            }
            innerMap.put(innerKey, value);
        }
    }

    public static <K, T, V> V getFromNestedMap(Map<K, Map<T, V>> map, K outerKey, T innerKey) {
        if (map == null || outerKey == null || innerKey == null) {
            return null;
        }
        Map<T, V> innerMap = map.get(outerKey);
        if (innerMap != null) {
            return innerMap.get(innerKey);
        }
        return null;
    }

    public static <K, V> void addToMultivaluedMapCollection(Map<K, Collection<V>> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (key == null || value == null) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            Collection<V> values = map.get(key);
            if (values == null) {
                values = Lists.newLinkedList();
                map.put(key, values);
            }
            values.add(value);
        }
    }

    public static <K, V> void addToMultivaluedMapList(Map<K, List<V>> map, K key, Collection<V> values) {
        addAllToMultivaluedMapList(map, key, values);
    }

    public static <K, V> void addAllToMultivaluedMapList(Map<K, List<V>> map, K key, Collection<V> values) {
        if (map == null) {
            return;
        }
        if (key == null || CollectionUtils.isEmpty(values)) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            List<V> existing = map.get(key);
            if (existing == null) {
                existing = Lists.newArrayList();
                map.put(key, existing);
            }
            existing.addAll(values);
        }
    }

    public static <K, V> void addToMultivaluedMapSet(Map<K, Set<V>> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (key == null || value == null) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            Set<V> values = map.get(key);
            if (values == null) {
                values = Sets.newLinkedHashSet();
                map.put(key, values);
            }
            values.add(value);
        }
    }

    public static <K, V extends Comparable> void addToMultivaluedMapTreeSet(Map<K, Set<V>> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (key == null || value == null) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            Set<V> values = map.get(key);
            if (values == null) {
                values = Sets.newTreeSet();
                map.put(key, values);
            }
            values.add(value);
        }
    }

    public static <K, V> void addToMultivaluedMapSet(Map<K, Set<V>> map, K key, Collection<V> values) {
        if (map == null) {
            return;
        }
        if (key == null || CollectionUtils.isEmpty(values)) {
            return;
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (map) {
            Set<V> existing = map.get(key);
            if (existing == null) {
                existing = Sets.newHashSet();
                map.put(key, existing);
            }
            existing.addAll(values);
        }
    }

    public static <K, V> void putToMapIfNotNull(Map<K, V> map, K key, V value) {
        PreConditions.notNull(map);
        if (key == null || value == null) {
            return;
        }
        map.put(key, value);
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    //default delimiter is colon
    public static String getDelimiterSeparatedMethod(final Collection<String> values, String delimiter) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        if (delimiter == null) {
            delimiter = ":";
        }
        String rv = "";
        for (String value : values) {
            rv = rv + value + delimiter;
        }
        rv = rv.substring(0, rv.length() - 1);
        return rv;
    }

    public static <T> String getDelimiterSeparatedCollection(final Collection<T> values, String delimiter) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        if (delimiter == null) {
            delimiter = ":";
        }
        String rv = "";
        for (T value : values) {
            rv = rv + value.toString() + delimiter;
        }
        rv = rv.substring(0, rv.length() - delimiter.length());
        return rv;
    }

    public static String getDelimiterSeparatedMethodForPaidExport(final Collection<String> values, String delimiter,
            String appendedString) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        if (delimiter == null) {
            delimiter = ",";
        }
        String rv = "";
        for (String value : values) {
            rv = rv + appendedString + value + delimiter;
        }
        rv = rv.substring(0, rv.length() - 1);
        return rv;
    }

    public static <T> List<T> mergeBatchesToSingleCollection(Collection<List<T>> batches) {
        if (CollectionUtils.isEmpty(batches)) {
            return Collections.emptyList();
        }

        List<T> rv = Lists.newArrayList();
        for (List<T> batch : batches) {
            if (CollectionUtils.isNotEmpty(batch)) {
                rv.addAll(batch);
            }
        }
        return rv;
    }

    public static <T> Set<T> mergeBatchesToSingleSet(Collection<List<T>> batches) {
        if (CollectionUtils.isEmpty(batches)) {
            return Collections.emptySet();
        }

        Set<T> rv = Sets.newHashSet();
        for (List<T> batch : batches) {
            if (CollectionUtils.isNotEmpty(batch)) {
                rv.addAll(batch);
            }
        }
        return rv;
    }

    public static <T> Collection<Set<T>> createBatchesOfSet(Collection<T> data, int batchSize) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }

        Collection<Set<T>> batchesAsSet = new ArrayList<>();
        int count = 0;
        Set<T> batchedData = Sets.newHashSet();
        for (T t : data) {
            batchedData.add(t);
            count++;
            if ((count % batchSize) == 0) {
                batchesAsSet.add(batchedData);
                batchedData = Sets.newHashSet();
            }
        }
        if (CollectionUtils.isNotEmpty(batchedData)) {
            batchesAsSet.add(batchedData);
        }
        return batchesAsSet;
    }

    public static <K, V> Map<V, K> reverseMap(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        Map<V, K> result = Maps.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    public static <K, V> Map<V, List<K>> reverseMapToMultivalued(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        Map<V, List<K>> result = Maps.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            addToMultivaluedMapList(result, entry.getValue(), entry.getKey());
        }

        return result;
    }

    public static <K, V> Map<V, List<K>> reverseMultivaluedMapList(Map<K, List<V>> map) {
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<V, List<K>> result = Maps.newHashMap();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            for (V value : CollectionUtils.nullSafeList(entry.getValue())) {
                addToMultivaluedMapList(result, value, entry.getKey());
            }
        }
        return result;
    }

    public static <K, V> Map<V, K> reverseMultiValuedMapListToMap(Map<K, List<V>> map) {
        if (isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<V, K> result = new HashMap<>();
        for (Entry<K, List<V>> entry : map.entrySet()) {
            //noinspection Convert2streamapi
            for (V value : nullSafeList(entry.getValue())) {
                if (value != null) {
                    result.put(value, entry.getKey());
                }
            }
        }
        return result;
    }

    public static <S> List<S> reverseList(List<S> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }

        List<S> result = Lists.newArrayList();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public static <K, V> List<Tuple<K, V>> transformMapToTuples(Map<K, List<V>> map) {
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyList();
        }
        List<Tuple<K, V>> rv = Lists.newArrayList();

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (CollectionUtils.isNotEmpty(entry.getValue())) {
                for (V v : entry.getValue()) {
                    rv.add(Tuple.of(entry.getKey(), v));
                }
            }
        }
        return rv;
    }

    public static <K, V> Map<K, V> transformTuplesToMap(List<Tuple<K, V>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<K, V> rv = Maps.newHashMap();

        for (Tuple<K, V> tuple : list) {
            if (tuple != null && tuple.v1() != null) {
                rv.put(tuple.v1(), tuple.v2());
            }
        }
        return rv;
    }

    public static <T> Set<T> mergeSetsToSingleSet(Collection<Set<T>> batches) {
        if (CollectionUtils.isEmpty(batches)) {
            return Collections.emptySet();
        }

        Set<T> rv = Sets.newHashSet();
        for (Collection<T> batch : batches) {
            if (CollectionUtils.isNotEmpty(batch)) {
                rv.addAll(batch);
            }
        }
        return rv;
    }

    public static List<String> splitCSV(String csv) {
        return Lists.newArrayList(Arrays.asList(csv.split(",")));
    }

    public static <K, V> Map<K, V> subMap(Map<K, V> originalMap, Set<K> keys) {
        if (CollectionUtils.isEmpty(keys) || MapUtils.isEmpty(originalMap)) {
            return Collections.emptyMap();
        }
        Map<K, V> result = Maps.newHashMap();
        for (K key : keys) {
            V value = originalMap.get(key);
            if (value != null) {
                result.put(key, value);
            } else if (originalMap.containsKey(key)) {
                result.put(key, null);//since null is also a valid value if key is present then add null
            }
        }
        return result;
    }

    public static List<ObjectId> transformToMongoObjectId(Collection<String> ids) {
        return transformToList(ids, STRING_TO_OBJECT_ID_TRANSFORMER);
    }

    public static <K, V> ConcurrentMap<K, V> transformToMap(Iterable<V> iterable, Transformer<V, K> keyTransformer,
            boolean throwExceptionOnDuplicateKeys) {
        ConcurrentMap<K, V> rv = Maps.newConcurrentMap();
        for (V v : iterable) {
            if (v == null) {
                continue;
            }
            K key = keyTransformer.transform(v);
            if (key == null) {
                continue;
            }
            V old = rv.putIfAbsent(key, v);
            if (throwExceptionOnDuplicateKeys && old != null) {
                throw new RuntimeException("Duplicate key - '" + key + "', existing: " + old + ",new: " + v);
            }
        }
        return rv;

    }

    public static <T, S> Collection<S> getValuesOfMultipleKeysFromMap(Map<T, S> map, Collection<T> keys) {
        List<S> values = new ArrayList<>();
        if (MapUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)) {
            return values;
        }

        for (T key : keys) {
            S value = map.get(key);
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }

    public static long[] transformToLongArray(Collection<String> batch) {
        if (CollectionUtils.isEmpty(batch)) {
            return new long[0];
        }

        int i = 0;
        long[] rv = new long[batch.size()];
        for (String id : batch) {
            rv[i++] = Long.parseLong(id);
        }
        return rv;
    }

    public static <T> Set<T> diff(Collection<T> before, Collection<T> after) {
        return Sets.difference(toSet(before), toSet(after));
    }

    public static <T> Set<T> intersection(Collection<T> before, Collection<T> after) {
        return Sets.intersection(toSet(before), toSet(after));
    }

    public static <K> Set<K> addedRemoved(Collection<K> coll1, Collection<K> coll2) {
        Set<K> rv = Sets.newHashSet();
        Set<K> set1 = toSet(coll1);
        Set<K> set2 = toSet(coll2);

        Set<K> diff = Sets.difference(set1, set2);
        if (CollectionUtils.isNotEmpty(diff)) {
            rv.addAll(diff);
        }
        diff = Sets.difference(set2, set1);
        if (CollectionUtils.isNotEmpty(diff)) {
            rv.addAll(diff);
        }

        return rv;
    }

    public static String getAnyNonBlankEntry(Collection<String> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return null;
        }

        for (String entry : coll) {
            if (StringUtils.isNotBlank(entry)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Safe disjoint, checks for empty. Note that if either of the collection is null or empty it returns true because
     * the method is supposed to return true if there are no common elements. Useful for set.containsAny(collection)
     */
    public static <T> boolean safeDisjoint(Collection<T> c1, Collection<T> c2) {
        return CollectionUtils.isEmpty(c1) || CollectionUtils.isEmpty(c2) || Collections.disjoint(c1, c2);
    }

    public static <T> void traverse(Collection<T> collection, Callback<T> callback) {
        for (T element : collection) {
            callback.onVisit(element);
        }
    }

    public static <K, V> List<Map<K, V>> partitionMaps(Map<K, V> paramMap, int batchSize) {
        if (MapUtils.isEmpty(paramMap)) {
            return Collections.emptyList();
        }

        List<Map<K, V>> partitionedMaps = new ArrayList<>();
        Map<K, V> currentMap = new HashMap<>();
        partitionedMaps.add(currentMap);

        for (Map.Entry<K, V> entry : paramMap.entrySet()) {
            if (currentMap.size() == batchSize) {
                currentMap = new HashMap<>();
                partitionedMaps.add(currentMap);
            }
            currentMap.put(entry.getKey(), entry.getValue());
        }
        return partitionedMaps;
    }

    public static <K, V> List<LinkedHashMap<K, V>> partitionLinkedHashMaps(Map<K, V> paramMap, int batchSize) {
        if (MapUtils.isEmpty(paramMap)) {
            return Collections.emptyList();
        }

        List<LinkedHashMap<K, V>> partitionedMaps = new ArrayList<>();
        LinkedHashMap<K, V> currentMap = new LinkedHashMap<>();
        partitionedMaps.add(currentMap);

        for (Map.Entry<K, V> entry : paramMap.entrySet()) {
            if (currentMap.size() == batchSize) {
                currentMap = new LinkedHashMap<>();
                partitionedMaps.add(currentMap);
            }
            currentMap.put(entry.getKey(), entry.getValue());
        }
        return partitionedMaps;
    }

    public static <E> List<Set<E>> partitionSet(Set<E> param, int batchSize) {
        if (CollectionUtils.isEmpty(param)) {
            return Collections.emptyList();
        }

        List<Set<E>> result = new ArrayList<>();
        Set<E> currentBatch = new HashSet<>();
        result.add(currentBatch);
        for (E entry : param) {
            if (currentBatch.size() >= batchSize) {
                currentBatch = new HashSet<>();
                result.add(currentBatch);
            }
            currentBatch.add(entry);
        }
        return result;
    }

    public static <E> boolean listEquals(List<E> first, List<E> second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        if (first.size() != second.size()) {
            return false;
        }
        Object[] firstArray = first.toArray(new Object[first.size()]);
        Comparator comparator = Ordering.natural().nullsLast();
        Arrays.sort(firstArray, comparator);
        Object[] secondArray = second.toArray(new Object[second.size()]);
        Arrays.sort(secondArray, comparator);
        return Arrays.equals(firstArray, secondArray);
    }

    // Scala version
    public static <T> String mkString(Collection<T> list, String sep) {
        StringBuilder sb = new StringBuilder();
        if (isEmpty(list)) {
            return "";
        }

        for (T obj : list) {
            sb.append(obj.toString());
            sb.append(sep);
        }
        // whack off extra separator
        int sepLength = sep.length();
        int sbLength = sb.length();
        sb.delete(sbLength - sepLength, sbLength);

        return sb.toString();
    }

    // Sprinklr version
    @SafeVarargs
    public static <T> String mkString(String sep, T... list) {
        return mkString(Arrays.asList(list), sep);
    }

    /**
     * Given a key, groups records by the given key's values.
     * <pre>
     *  [
     *   [name:'Bob', grade: 10, zip:78701],
     *   [name:'Rich', grade: 8, zip: 90000],
     *   [name:'Larry', grade: 8, zip: 80801],
     *   [name:'Jack', grade: 9, zip: 10000],
     *   [name:'Jack', grade: 10, zip: 10000],
     *  ]
     *   Group by key: 'grade' would return:
     *
     *  [
     *   10: [[name:'Bob', grade: 10, zip:78701], [name:'Jack', grade: 10, zip: 10000]],
     *   8:  [[name:'Rich', grade: 8, zip: 90000], [name:'Larry', grade: 8, zip: 80801]]
     *   9:  [[name:'Jack', grade: 9, zip: 10000]],
     *  ]
     *  </pre>
     */
    public static <K, V> Map<V, List<Map<K, V>>> groupBy(List<Map<K, V>> records, K groupByKey) {
        Map<V, List<Map<K, V>>> results = new LinkedHashMap<>();
        for (Map<K, V> record : records) {
            V groupByValue = record.get(groupByKey);
            List<Map<K, V>> groupByList = results.get(groupByValue);
            if (groupByList == null) {
                groupByList = new ArrayList<>();
                results.put(groupByValue, groupByList);
            }
            groupByList.add(record);
        }

        return results;
    }

    public static boolean checkIfContainsAny(Collection<String> searchIn, Collection<String> searchFor) {
        for (String value : searchFor) {
            if (searchIn.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkAnySubString(Collection<String> searchIn, Collection<String> searchFor) {
        if (isEmpty(searchIn) || isEmpty(searchFor)) {
            return false;
        }

        for (String valueFor : searchFor) {
            for (String valueIn : searchIn) {
                if (StringUtils.containsIgnoreCase(valueIn, valueFor)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIfContainsAny(Collection<String> searchIn, String... searchFor) {
        if (isEmpty(searchFor) || isEmpty(searchIn)) {
            return false;
        }
        for (String value : searchFor) {
            if (searchIn.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubStringMatch(Collection<String> searchIn, Collection<String> searchFor) {
        for (String value : searchFor) {
            for (String input : searchIn) {
                if (input.contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <K, V> boolean mapEquals(Map<K, List<V>> map1, Map<K, List<V>> map2) {

        if (map1 == map2) {
            return true;
        }

        if (map1 == null || map2 == null) {
            return false;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry entry : map1.entrySet()) {
            //noinspection SuspiciousMethodCalls
            List<V> list1 = map1.get(entry.getKey());
            //noinspection SuspiciousMethodCalls
            List<V> list2 = map2.get(entry.getKey());

            if (!listEquals(list1, list2)) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> V putAndGet(ConcurrentMap<K, V> errorKeyMap, K key, InstanceGeneratorByKey<K, V> generator) {
        V errorKeys = errorKeyMap.get(key);
        if (errorKeys == null) {
            errorKeys = errorKeyMap.putIfAbsent(key, generator.getNewInstance(key));
            if (errorKeys == null) {
                errorKeys = errorKeyMap.get(key);
            }
        }
        return errorKeys;
    }

    public static <K, V> V putAndGet(ConcurrentMap<K, V> errorKeyMap, K key, InstanceGenerator<V> generator) {
        V errorKeys = errorKeyMap.get(key);
        if (errorKeys == null) {
            errorKeys = errorKeyMap.putIfAbsent(key, generator.getNewInstance());
            if (errorKeys == null) {
                errorKeys = errorKeyMap.get(key);
            }
        }
        return errorKeys;
    }

    public static <K, V extends Comparable<V>> TreeMap<K, V> getMapSortedOnValue(Map<K, V> anyMap,
            boolean isAscending) {
        if (isEmpty(anyMap)) {
            return Maps.newTreeMap(new ValueComparator<>(anyMap, isAscending));
        }

        TreeMap<K, V> mapToReturn = Maps.newTreeMap(new ValueComparator<>(anyMap, isAscending));
        for (Map.Entry<K, V> entry : anyMap.entrySet()) {
            mapToReturn.put(entry.getKey(), entry.getValue());
        }
        return mapToReturn;
    }

    public static <K, V extends Comparable<V>> TreeMap<K, V> getMapSortedOnValue(Map<K, V> anyMap, boolean isAscending,
            Comparator<V> comparator) {
        if (isEmpty(anyMap)) {
            return Maps.newTreeMap(new ValueComparator<>(anyMap, isAscending, comparator));
        }
        TreeMap<K, V> mapToReturn = Maps.newTreeMap(new ValueComparator<>(anyMap, isAscending, comparator));
        for (Map.Entry<K, V> entry : anyMap.entrySet()) {
            mapToReturn.put(entry.getKey(), entry.getValue());
        }
        return mapToReturn;
    }

    public static <E> List<E> filterProcessed(Set<E> toProcess, Set<E> processed) {
        if (processed.isEmpty()) {
            return Lists.newArrayList(toProcess);
        }

        List<E> toReturn = Lists.newArrayList();
        for (E entity : toProcess) {
            if (!toProcess.contains(entity)) {
                toReturn.add(entity);
            }
        }

        return toReturn;
    }

    public static boolean containsOneBlankStringOnly(Collection<String> collection) {
        return (collection.size() == 1) && (StringUtils.isBlank(collection.iterator().next()));
    }

    public static boolean listContainsOneBlankStringOnly(List<String> stringList) {
        return (stringList.size() == 1) && (StringUtils.isBlank(stringList.get(0)));
    }

    public static <E> boolean addToCollectionIfDoesntExist(Collection<E> collection, E obj) {
        if (collection == null || obj == null) {
            return false;
        }

        boolean exists = false;

        for (E e : collection) {
            if (e.equals(obj)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            collection.add(obj);
        }

        return exists;
    }

    public static <E> void addIfNotNull(Collection<E> collection, E obj) {
        if (collection == null || obj == null) {
            return;
        }
        collection.add(obj);
    }

    public static <T> List<T> getRandomizedEntriesFromList(List<T> input) {
        if (isEmpty(input)) {
            return Collections.emptyList();
        }

        return getRandomizedEntriesFromList(input, input.size());
    }

    public static <T> List<T> getRandomizedEntriesFromList(List<T> input, int maxItems) {
        if (CollectionUtils.isEmpty(input)) {
            return Collections.emptyList();
        }

        List<T> shuffled = new ArrayList<>(input);
        Collections.shuffle(shuffled);

        if (shuffled.size() <= maxItems) {
            return shuffled;
        } else {
            List<T> rv = new ArrayList<>(maxItems);
            for (int i = 0; i < maxItems; i++) {
                rv.add(shuffled.get(i));
            }
            return rv;
        }
    }

    public static <E> boolean nullSafeRemove(Collection<E> collection, Collection<E> toRemove) {
        if (CollectionUtils.isEmpty(toRemove)) {
            return false;
        }
        return collection.removeAll(toRemove);
    }

    /*public static fj.data.HashMap<String, String> mapFromString(String str, String itemSep, final String pairSep) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(itemSep) || StringUtils.isBlank(pairSep)) {
            return new fj.data.HashMap<String, String>(Collections.<String, String>emptyMap());
        }

        return new fj.data.HashMap<>(Array.array(str.split(itemSep)).
                foldLeft(new F2<Map<String, String>, String, Map<String, String>>() {
                    @Override
                    public Map<String, String> f(Map<String, String> zero, String item) {
                        String[] pair = item.split(pairSep);
                        zero.put(pair[0], pair[1]);
                        return zero;
                    }
                }, new HashMap<String, String>()));
    }*/

    public static <T> List<T> getIntersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        if (CollectionUtils.isEmpty(list1) || CollectionUtils.isEmpty(list2)) {
            return list;
        }

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static Set<Integer> transformToInteger(Collection<Long> input) {
        if (CollectionUtils.isEmpty(input)) {
            return Collections.emptySet();
        }

        Set<Integer> rv = Sets.newHashSet();
        for (Long i : input) {
            if (i != null) {
                rv.add(i.intValue());
            }
        }
        return rv;
    }

    public static Set<Long> transformToLong(Collection<String> input) {
        if (CollectionUtils.isEmpty(input)) {
            return Collections.emptySet();
        }

        Set<Long> rv = Sets.newHashSet();
        for (String in : input) {
            if (StringUtils.isNotBlank(in)) {
                rv.add(Long.parseLong(in));
            }
        }
        return rv;
    }

    public static <T> Stream<T> minus(Collection<T> from, Collection<T> with) {
        return from.stream().filter(x -> !with.contains(x));
    }

    public static String joinWithComma(Enumeration<String> enumeration) {
        if (enumeration == null) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String element = enumeration.nextElement();
            if (StringUtils.isNotBlank(element)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(element);
            }
        }
        return sb.toString();
    }

    public static String joinWithComma(String[] elements) {
        if (ArrayUtils.isEmpty(elements)) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        for (String element : elements) {
            if (StringUtils.isNotBlank(element)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(element);
            }
        }
        return sb.toString();
    }

    public interface GroupByStrategy<T> {

        GroupKey group(T t);
    }

    public interface Transformer<From, To> {

        static <T> Transformer<T, T> identity() {
            return t -> t;
        }

        To transform(From t);
    }

    public interface TransformerWithArgs<From, To, Arg> {

        To transform(From t, Arg args);
    }

    public interface NamedTransformer<From, To> extends Transformer<From, To> {

        String getTransformerName();
    }

    /*public static Set<String> getTrimmedValueSet(String commaSeparatedString) {
        if (SprStringUtils.isBlank(commaSeparatedString)) {
            return Collections.emptySet();
        }
        Set<String> valueSet = new HashSet<>();
        List<String> values = Arrays.asList(commaSeparatedString.split(","));
        for (String value : values) {
            String trimmedValue = value.trim();
            if (SprStringUtils.isNotBlank(trimmedValue)) {
                valueSet.add(trimmedValue);
            }
        }
        return valueSet;
    }*/


    public interface CollectionProvider<T, C extends Collection<T>> {

        C newCollection();
    }

    public interface InstanceGeneratorByKey<K, V> {

        V getNewInstance(K key);
    }

    public interface InstanceGenerator<V> {

        V getNewInstance();
    }

    public static class GroupKey {

        private Object[] keys;

        public GroupKey(Object... keys) {
            this.keys = keys;
        }

        public Object[] getKeys() {
            return keys;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            GroupKey groupKey = (GroupKey) o;

            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(keys, groupKey.keys);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(keys);
        }
    }

    public static abstract class Callback<T> {

        public abstract void onVisit(T element);
    }

    public static abstract class CallbackWithArgs<T, A> {

        public abstract void onVisit(T element, A argument);
    }

    private static class ValueComparator<K, V extends Comparable<V>> implements Comparator<K> {

        private final Map<K, V> base;
        private final boolean isAscending;
        private final Comparator<V> comparator;

        public ValueComparator(Map<K, V> base, boolean isAscending) {
            this.base = base;
            this.isAscending = isAscending;
            this.comparator = null;
        }

        public ValueComparator(Map<K, V> base, boolean isAscending, Comparator<V> comparator) {
            this.base = base;
            this.isAscending = isAscending;
            this.comparator = comparator;
        }

        // Note: this comparator imposes orderings that are inconsistent with
        // equals.
        public int compare(K a, K b) {
            V value1 = base.get(a);
            V value2 = base.get(b);

            if (comparator != null) {
                return returnByOrder() * comparator.compare(value1, value2);
            }

            if (value1 == null) {
                if (value2 != null) {
                    return returnByOrder() * -1;
                } else {
                    return returnByOrder();
                }
            } else if (value2 == null) {
                return returnByOrder();
            }

            int toReturn = value1.compareTo(value2);
            return toReturn == 0 ? returnByOrder() : toReturn * returnByOrder();
        }

        private int returnByOrder() {
            return isAscending ? 1 : -1;
        }
    }
}

