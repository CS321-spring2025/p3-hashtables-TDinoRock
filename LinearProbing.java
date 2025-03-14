public class LinearProbing extends Hashtable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        return positiveMod(key.hashCode() + probe, capacity);
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }        
}