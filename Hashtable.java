public abstract class Hashtable {
    protected int size;
    protected int capacity;
    protected int numTableElements;
    protected double loadFactor;
    protected int totalElements;
    protected int duplicateCount;
    protected int totalProbes;

    public Hashtable() {
        this.size = 0;
        this.capacity = 0;
        this.numTableElements = 0;
        this.loadFactor = 0;
        this.totalElements = 0;
        this.duplicateCount = 0;
        this.totalProbes = 0;
    }

    public Hashtable(int capacity, double loadFactor) {
        this.size = (int) Math.ceil(capacity * loadFactor);
        this.capacity = capacity;
        this.numTableElements = 0;
        this.loadFactor = loadFactor;
        this.totalElements = 0;
        this.duplicateCount = 0;
        this.totalProbes = 0;
    }

    public abstract int h(Object key, int probe);

    public abstract void insert(HashObject obj, int debugLevel);

    public int tableLoadFactor() {
        return size;
    }

    public int getSize() {
        return capacity;
    }

    public int getInsertedElements() {
        return numTableElements;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public double getAverageProbes() {
        return (double) totalProbes / totalElements;
    }
}