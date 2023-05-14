package structures;

// this class demonstrates using an object's hash code to calculate the 
// index into the array

public class ArrayHashTable<K, V> implements HashTable<K, V> {

  protected static final int defaultCapacity = 10;
  protected static final double defaultLoadFactor = 0.7;
  protected static final String defaultCollisionHandler = "linear";

  protected K[] keyTable;
  protected V[] valueTable;
  protected int capacity;
  protected boolean[] flag;
  protected String collisionHandler;
  protected double loadFactorLimit;
  protected int count;

  /**
   * Default Constructor.
   */
  public ArrayHashTable() {
    this(defaultCapacity, defaultLoadFactor, defaultCollisionHandler);
  }

  /**
   * Default Constructor.
   */
  public ArrayHashTable(String collisionHandler) {
    this(defaultCapacity, defaultLoadFactor, collisionHandler);
  }

  /**
   * Default Constructor.
   */
  public ArrayHashTable(int capacity, String collisionHandler){
    this(capacity, defaultLoadFactor, collisionHandler);
  }

  
  /**
   * Constructor.
   */
  public ArrayHashTable(int capacity, double loadFactor, String collisionHandler){
    // TODO 1: complete the constructors
    this.capacity = capacity;
    loadFactorLimit = loadFactor;
    this.collisionHandler = collisionHandler;
    keyTable = (K[]) new Object[capacity];
    valueTable = (V[]) new Object[capacity];
    flag = new boolean[capacity];
    count = 0;
  }
 
  /** 
  * This method ensures that the inputNum maps to
  * a return value that is in the current array.
  */
  private int getBoundedHash(int inputNum) {
    return Math.abs(inputNum) % this.capacity;
  }

  private int getHash(K key) {
    int index = getBoundedHash(key.hashCode());
    return index;
  }
  
  public K[] getKeyTable() {
    return this.keyTable;
  }
  
  public V[] getValueTable() {
    return this.valueTable;
  }
  
  public boolean[] getFlag() {
    return this.flag;
  }

  /** 
   * Returns the number of elements in the hash table.
   */
  public int size() {
    // TODO 2a: return the size
    return count;
  }

  /**
   * Returns the current maximal number of elements the hash table can save.
   * @return
   */
  public int getCapacity() {
    // TODO 2b: return the capacity
    return capacity;
  }

  /**
   * Returns the name of the collision handler for the current hash table. 
   * @return : name of the collision handler.
   */
  public String getCollisionHandlerName() {
    // TODO 2c: return the name of collision handler
    return collisionHandler;
  }

  /**
   * Enlarges the size of the array and rehash.
   */
  private void resizeArray() {
    // TODO 3: Double the size, then rehash into the new table
    capacity = capacity * 2;
    boolean[] newFlag = new boolean[capacity];
    K[] newKeyTable = (K[]) new Object[capacity];
    V[] newValueTable = (V[]) new Object[capacity];
    int index;
    for(int i = 0; i < capacity / 2; i++) {
      if(keyTable[i] != null) {
        index = getHash(keyTable[i]);
        newKeyTable[index] = keyTable[i];
        if(flag[i]) {
          newFlag[index] = true;
          newValueTable[index] = valueTable[i];
        }
      }
    }
    flag = newFlag;
    keyTable = newKeyTable;
    valueTable = newValueTable;
  }

  /**
  * Calculates the ratio of the size of the data in the table to the capacity.
  */
  private double calcCurrentLoad() {
    double currentLoadFactor = (double) size() / (double) capacity;
    return currentLoadFactor;
    // TODO 4: Calculate current load factor and return it 
    // Load factor = size / capacity
  } 

  /**
   * This method calls upon a collision resolution scheme
   * to put this value into the table.
   */
  private int resolveCollision(int index, K key) {
    if (this.collisionHandler.equals("linear")) {
      index = doLinearProbe(index);
    } else if (this.collisionHandler.equals("quadratic")) {
      index = doQuadraticProbe(index);
    } else if (this.collisionHandler.equals("doublehash")) {
      index = doDoubleHash(index, key);
    } else { 
      return -1; 
    }
    return index;
  }

  /** 
   * Put the new value into the hash table. Before adding, 
   * resize the array if the current load factor is larger than loadFactorLimit. 
   * Please use the getHash method provided to get the hash for the array index.
   * Call the searchIndex method to see if a collision occurs. 
   * If the key exists in the hash table, replace the old value with the input value.
   * Call the resolveCollision method if a collision happened.
   */
  public void put(K key, V value) {
    // TODO 5: put the new value into hash table.
    if(calcCurrentLoad() > loadFactorLimit) {
      resizeArray();
    }
    int index = getHash(key);
    if(flag[index]) {
      if(searchIndex(index, key) != -1) {
        valueTable[index] = value;
        keyTable[index] = key;
        flag[index] = true;
        ++count;
      }
      else {
        index = resolveCollision(index,key);
      }
    }
    valueTable[index] = value;
    keyTable[index] = key;
    flag[index] = true;
    ++count;
  }

  /** T
   * This method calls upon a collision resolution scheme
   * to search for an index where the value can be inserted into the table.
   */
  private int searchIndex(int index, K value) {
    if (this.collisionHandler.equals("linear")) {
      index = doLinearSearch(index, value);
    } else if (this.collisionHandler.equals("quadratic")) {
      index = doQuadraticSearch(index, value);
    } else if (this.collisionHandler.equals("doublehash")) {
      index = doSecondHashSearch(index, value);
    }
    return index;
  }

  /**
   * Removes the target value from the hash table and return the value.
   * Throws ElementNotFoundException if the target does not exist in the table.
   * Calls searchIndex to get the index in case a collision occurred when
   * the value was put into the table.
   */
  public V remove(K target)throws ElementNotFoundException {
    //TODO 6: remove the target value from hash table and return value
    int index = getHash(target);
    if(keyTable[index].equals(target)) {
      flag[index] = false;
      count --;
      return valueTable[index];
    } else {
      index = searchIndex(index,target);
      if(index != -1) {
        flag[index] = false;
        count--;
      } else {
        throw new ElementNotFoundException(target.toString() + " not found");
      }
      return valueTable[index];
    }
  }

  /**
   * Returns the value if it exists in the table.
   * Returns null if the target does not exist in the table.
   * Calls searchIndex to get the index in case a collision occurred when
   * the value was put into the table.
   */
  public V get(K target) {
    //TODO 7: return target if it exist in the table
    int index = getHash(target);
    if (flag[index] && keyTable[index].equals(target)) {
      return valueTable[index];
    }
    int temp = searchIndex(index, target);
    if(temp == -1) {
      return null;
    }
    else if(!flag[temp]) {
      return null;
    }
      return valueTable[temp];
  }

  /** TODO 8: Complete linearProbe, quadraticProbe, and doubleHash
  * Start at index and search linearly (with probe length = 1) until an open spot
  * is found. A spot will be found because the table 
  * is never more full than the Load Factor, assuming it's <1.0
  */
  private int doLinearProbe(int index) {
    while(flag[index]) {
      index = (index + 1) % capacity;
    }
    return index;
  }
 
  /**
  * Start at index and search quadratically until an open spot is found
  * A spot will be found because the table 
  * is never more full than the Load Factor, assuming it's <1.0
  */
  private int doQuadraticProbe(int index) {
    int startIndex = index;
    int i = 1;
    while(flag[index] && i <= capacity) {
      index = (startIndex + (i*i)) % capacity;
      i++;
    }
    if(i >= capacity) {
      return -1;
    }
    return index;
  }


  /**
  * If the first hash resulted in a collision, use a second hash
  * as the probe length until a space is found. 
  * The second hash value is computed by length of value.hashCode()
  */
  private int doDoubleHash(int index, K key) {
    int target = key.hashCode();
    String temp = Integer.toString(target);
    int length = temp.length();
    while(flag[index]) {
      index += length;
    }
    return index;
  }  

  /**
  * TODO 9: Complete linearSearch, quadraticSearch, and doubleHashSearch.
  * Start at startIndex and search linearly until the target
  * is found. Return -1 if not found.
  */
  private int doLinearSearch(int startIndex, K target) {
    int index = startIndex;
    while(keyTable[index] != null) {
      if(target.equals(keyTable[index])) {
        return index;
      }
      index = (index + 1) % capacity;
    }
    return -1;
  }

  /**
  * Start at startIndex and search quadratically until the target
  * is found. Return -1 if not found.
  */
  private int doQuadraticSearch(int startIndex, K target) {
    int index = startIndex;
    int i = 1;
    while(keyTable[index] != null) {
      if(!target.equals(keyTable[index])) {
        index = (startIndex + (i*i)) % capacity;
        i++;
      }
      else {
        return index;
      }
    }
    return -1;
  }

  private int doSecondHashSearch(int startIndex, K target) {
    int key = target.hashCode();
    String temp = Integer.toString(key);
    int length = temp.length();
    int index = startIndex;
    while(keyTable[index] != null) {
      if(!target.equals(keyTable[index])) {
        index += length;
      }
      else {
        return index;
      }
    }
    return -1;
  }

  /**
   * Return all available keys in hash table as an array.
   */
  public K[] keys() {
    // TODO 10: return all available keys in hash table
    K[] keys = (K[]) new Object[count];
    int keyCount = 0;
    for(int i = 0; i < capacity; i++) {
      if(flag[i]) {
        keys[keyCount] = keyTable[i];
        keyCount++;
      }
    }
    return keys;
  }
}

