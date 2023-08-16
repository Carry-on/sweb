package com.xiao.sweb.learn;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    public static void main(String[] args) {
        System.out.println(1 << 4);
        System.out.println(1 << 30);
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(5));

    }

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;  // 16
    /**
     * 最大容量，如果两个构造函数中的任何一个带参数地隐式指定了更高的值，则使用最大容量。必须是2的幂<= 1<<30。
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 在构造函数中未指定时使用的负载因子。
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 使用树而不是列表的bin计数阈值。当向至少有这么多节点的桶中添加元素时，桶将转换为树。该值必须大于2，并且应该至少为8，以符合在树木移除中关于收缩后转换回普通箱的假设。
     */
    static final int TREEIFY_THRESHOLD = 8;
    /**
     * 在调整大小操作期间取消树化(拆分)bin的bin计数阈值。应小于TREEIFY_THRESHOLD，且最多为6，以便在移除时进行收缩检测。
     */
    static final int UNTREEIFY_THRESHOLD = 6;
    /**
     * 可以对桶进行树化的最小表容量。(否则，如果一个bin中的节点太多，则会调整表的大小。)应该至少为4 * TREEIFY_THRESHOLD，以避免调整大小和树化阈值之间的冲突。
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        public String toString() {
            return key + "=" + value;
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry e = (Map.Entry) o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */

    static final int hash(Object key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = h ^ h >>> 16;
        }
        return h;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n = n | n >>> 1;
        n = n | n >>> 2;
        n = n | n >>> 4;
        n = n | n >>> 8;
        n = n | n >>> 16;
        if (n < 0) {
            return 1;
        }
        if (n >= MAXIMUM_CAPACITY) {
            return MAXIMUM_CAPACITY;
        }
        return n + 1;
    }

    /**
     * 表，在第一次使用时初始化，并根据需要调整大小。在分配时，长度总是2的幂。(我们也允许某些操作的长度为零，以允许目前不需要的引导机制。)
     */
    transient Node<K, V>[] table;
    /**
     * 保存缓存的entrySet()。注意，AbstractMap字段用于keySet()和values()。
     */
    transient Set<Map.Entry<K, V>> entrySet;
    /**
     * 此map中包含的键值映射的数量。
     */
    transient int size;
    /**
     * 结构修改是指改变HashMap中的映射数量或以其他方式修改其内部结构(例如，重新散列)。该字段用于使HashMap的集合视图上的迭代器快速失败。(见ConcurrentModificationException)。
     */
    transient int modCount;
    /**
     * 要调整大小的下一个大小值(容量*负载因子)。
     */
    int threshold;
    /**
     * 哈希表的负载因子。
     */
    final float loadFactor;

    public MyHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("");
        }
        if (initCapacity > MAXIMUM_CAPACITY) {
            initCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("");
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initCapacity);
    }

    public MyHashMap(int initCapacity) {
        this(initCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 使用默认初始容量(16)和默认负载因子(0.75)构造一个空HashMap。
     */
    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * 构造一个与指定Map具有相同映射的新HashMap。HashMap是用默认负载因子(0.75)创建的，初始容量足以在指定的Map中保存映射。
     *
     * @param m
     */
    public MyHashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntry(m, false);
    }

    final void putMapEntry(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
            if (table == null) {
                float ft = (s / loadFactor) + 1.0F;
                int t = ft < (float) MAXIMUM_CAPACITY ? (int) ft : MAXIMUM_CAPACITY;
                if (t > threshold) {
                    threshold = tableSizeFor(t);
                }
            } else if (s > threshold) {
                resize();
            }
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                putVal(hash(key), key, value, false, evict);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(Object key) {
        Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return null;
        } else {
            return e.value;
        }
    }

    final Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;
        tab = table;
        if (tab != null) {
            n = tab.length;
            if (n > 0) {
                first = tab[(n - 1) & hash];
                if (first != null) {
                    if (first.hash == hash) {
                        k = first.key;
                        if (Objects.equals(key, k)) {
                            return first;
                        }
                    }
                    e = first.next;
                    if (e != null) {
                        if (first instanceof TreeNode) {
                            return ((TreeNode<K, V>) first).getTreeNode(hash, key);
                        }
                        do {
                            if (e.hash == hash) {
                                k = e.key;
                                if (Objects.equals(k, key)) {
                                    return e;
                                }
                            }
                        } while ((e = e.next) != null);
                    }
                }
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }

    /**
     * 将指定值与此映射中的指定键关联。如果映射以前包含键的映射，则替换旧值。
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    private V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;
        tab = table;
        if (tab == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        i = (n - 1) & hash;
        p = tab[i];
        if (p == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            Node<K, V> e;
            K k;
            k = p.key;
            if (p.hash == hash && Objects.equals(k, key)) {
                e = p;
            } else if (p instanceof TreeNode) {
                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            } else {
                for (int binCount = 0; ; ++binCount) {
                    e = p.next;
                    if (e == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    if (e.hash == hash && Objects.equals(k, key)) {
                        break;
                    }
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold) {
            resize();
        }
        afterNodeInsertion(evict);
        return null;
    }

    // 回调允许LinkedHashMap后动作
    void afterNodeAccess(Node<K, V> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }

    void afterNodeRemoval(Node<K, V> p) {
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap = 0, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1; // double threshold
            }
        } else if (oldThr > 0) {
            newThr = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY) ? (int) ft : Integer.MAX_VALUE;
        }
        threshold = newThr;
        Node<K, V>[] newTab = new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; j++) {
                Node<K, V> e;
                e = oldTab[j];
                if (e != null) {
                    oldTab[j] = null;
                    if (e.next == null){
                        newTab[e.hash & (newCap - 1)] = e;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 替换bin中给定哈希索引处的所有链接节点，除非表太小，在这种情况下
     *
     * @param tab
     * @param hash
     */
    final void treeifyBin(Node<K, V>[] tab, int hash) {

    }


    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es;
        es = entrySet;
        if (es == null) {
            es = new EntrySet();
        }
        return es;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    /* ------------------------------------------------------------ */
    // Tree bins

    /**
     * Tree bins条目。LinkedHashMap延伸。条目(又扩展Node)，因此可以用作常规节点或链接节点的扩展。
     *
     * @param <K>
     * @param <V>
     */
    static final class TreeNode<K, V> extends MyHashMap.Node<K, V> {
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;
        boolean red;

        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }

        /**
         * 返回包含此节点的树的根。
         *
         * @return
         */
        final TreeNode<K, V> root() {
            for (TreeNode<K, V> r = this, p; ; ) {
                p = r.parent;
                if (p == null) {
                    return r;
                }
                r = p;
            }
        }

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
            int n = tab.length;
            if (root != null && tab != null && n > 0) {
                int index = (n - 1) & root.hash;
                TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
                if (root != first) {
                    Node<K, V> rn;
                    tab[index] = root;
                    TreeNode<K, V> rp = root.prev;
                    rn = root.next;

                }
            }
        }

        final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            return null;
        }

        final TreeNode<K, V> getTreeNode(int h, Object k) {
            if (parent != null) {
                return root().find(h, k, null);
            } else {
                return this.find(h, k, null);
            }
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

        public Node<K, V> putTreeVal(MyHashMap<K, V> kvMyHashMap, Node<K, V>[] tab, int hash, K key, V value) {
            return null;
        }
    }
}
