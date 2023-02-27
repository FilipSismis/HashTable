import HashTable.HashTable;

public class Main {
    public static void main(String[] args) {
        //tu bude testovanie
        HashTable<String, String> hashTable = new HashTable<String, String>();
        hashTable.add("banana", "yellow");
        hashTable.add("orange", "orange");
        hashTable.add("lemon", "yellow");
        hashTable.add("apple", "red");
        System.out.println(hashTable.size());
        System.out.println(hashTable.remove("lemon"));
        System.out.println(hashTable.size());
        System.out.println( hashTable.get("banana"));
        System.out.println( hashTable.get("orange"));
        System.out.println( hashTable.get("apple"));
        System.out.println(hashTable.isEmpty());
    }
}