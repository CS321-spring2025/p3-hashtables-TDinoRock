public class DoubleHashing extends Hashtable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        int hash1 = positiveMod(key.hashCode(), capacity);
        int hash2 = 1 + positiveMod(key.hashCode(), (capacity - 2));
        return positiveMod(hash1 + (probe * hash2), capacity);
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }        
}