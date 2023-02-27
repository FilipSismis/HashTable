package HashTable;

import java.util.ArrayList;
import java.util.Objects;


//hlavna class hashtable
public class HashTable<K, V> {
    //list na ukladanie jednotlivy hash nodes
    private ArrayList<HashNode<K, V> > bucketArray;
    //aktualna kapacita hashtable
    private int currCapacity;
    //aktualna velkost hashtable
    private int currSize;
    //constructor
    public HashTable()
    {
        bucketArray = new ArrayList<>();
        currCapacity = 10;
        currSize = 0;


        for (int i = 0; i < currCapacity; i++)
            bucketArray.add(null);
    }

    //velkost a kontrola empty hashtable
    public int size() { return currSize; }
    public boolean isEmpty() { return size() == 0; }

    //hashcode jednotlivych klucov
    private int getHashCode(K key) {
        return Objects.hashCode(key);
    }

    //jednoducha hash funkcia na vytvorenie indexu
    //hash implementovany pomocou modulo
    private int getBucketIndex(K key)
    {
        int hashCode = getHashCode(key);
        int index = hashCode % currCapacity;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    //odstranenie zaznamu z hashtable
    public V remove(K key)
    {
        int bucketIndex = getBucketIndex(key);
        int hashCode = getHashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

            prev = head;
            head = head.nextNode;
        }

        if (head == null)
            return null;

        currSize--;

        if (prev != null)
            prev.nextNode = head.nextNode;
        else
            bucketArray.set(bucketIndex, head.nextNode);

        return head.value;
    }

    //najdenie zaznamu v hashtable
    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);
        int hashCode = getHashCode(key);

        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.nextNode;
        }

        return null;
    }

    //pridanie zaznamu do hashtable
    public void add(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);
        int hashCode = getHashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.nextNode;
        }

        currSize++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        newNode.nextNode = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * currSize) / currCapacity >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            currCapacity = 2 * currCapacity;
            currSize = 0;
            for (int i = 0; i < currCapacity; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.nextNode;
                }
            }
        }
    }
}