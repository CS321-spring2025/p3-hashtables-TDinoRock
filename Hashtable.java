public abstract class Hashtable {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected int totalElements;
    protected int duplicateCount;
    protected int totalProbes;

    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = (int) (capacity * loadFactor);
        this.totalElements = 0;
        this.duplicateCount = 0;
        this.totalProbes = 0;
    }

    public abstract int h(Object key, int probe);

    public abstract void insert(HashObject obj, int debugLevel);

    public abstract Object search(Object key);

    public int getSize() {
        return size;
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