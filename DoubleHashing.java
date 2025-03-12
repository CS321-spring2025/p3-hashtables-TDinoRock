public class DoubleHashing extends Hashtable {

    private HashObject[] table;

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
        table = new HashObject[capacity];
    }

    @Override
    public int h(Object key, int probe) {
        int hash1 = positiveMod(key.hashCode(), capacity);
        int hash2 = 1 + positiveMod(key.hashCode(), (capacity - 2));
        return (hash1 + probe * hash2) % capacity;
    }

    @Override
    public void insert(HashObject obj, int debugLevel) {
        int probe = 0;
        int index;
        while (true) {
            index = h(obj.getKey(), probe);
            if (table[index] == null) {
                table[index] = obj;
                totalElements++;
                totalProbes += probe + 1;
                break;
            } else if (table[index].equals(obj)) {
                table[index].incrementFrequencyCount();
                duplicateCount++;
                totalProbes += probe + 1;
                break;
            }
            probe++;
        }
    }

    @Override
    public Object search(Object key) {
        int probe = 0;
        int index;
        while (true) {
            index = h(key, probe);
            if (table[index] == null) {
                return null;
            } else if (table[index].getKey().equals(key)) {
                return table[index];
            }
            probe++;
        }
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }        
}