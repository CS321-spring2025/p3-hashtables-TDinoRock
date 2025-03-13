public class LinearProbing extends Hashtable {

    private HashObject[] table;

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
        table = new HashObject[capacity];
    }

    @Override
    public int h(Object key, int probe) {
        return positiveMod(key.hashCode() + probe, capacity);
    }

    @Override
    public void insert(HashObject obj, int debugLevel) {
        int index;
        int startingProbeAmount = totalProbes;
        while (true) {
            index = h(obj.getKey(), obj.getProbeCount());
            if (table[index] == null) {
                table[index] = obj;
                numTableElements++;
                break;
            }
            else if (table[index].equals(obj)) {
                table[index].incrementFrequencyCount();
                duplicateCount++;
                totalProbes = startingProbeAmount;
                break;
            }
            obj.incrementProbeCount();
            totalProbes++;
            //System.out.println(index);
        }
        totalElements++;
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }        
}