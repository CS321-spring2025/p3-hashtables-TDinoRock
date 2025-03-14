public abstract class Hashtable {
    protected int size;
    protected int capacity;
    protected int numTableElements;
    protected double loadFactor;
    protected int totalElements;
    protected int duplicateCount;
    protected int totalProbes;
    protected HashObject[] hashObject;

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
        this.hashObject = new HashObject[capacity];
    }

    public abstract int h(Object key, int probe);

    public void insert(HashObject obj, int debugLevel) {
        int index;
        int startingProbeAmount = totalProbes;
        while (true) {
            index = h(obj.getKey(), obj.getProbeCount());
            if (hashObject[index] == null) {
                hashObject[index] = obj;
                numTableElements++;
                obj.incrementProbeCount();
                totalProbes++;
                break;
            }
            else if (hashObject[index].equals(obj)) {
                hashObject[index].incrementFrequencyCount();
                duplicateCount++;
                totalProbes = startingProbeAmount;
                break;
            }
            obj.incrementProbeCount();
            totalProbes++;
        }
        totalElements++;
    }

    public HashObject search(HashObject obj) {
        int index = 0;
        while (index < this.capacity) {
            index = h(obj.getKey(), index);
            if (hashObject[index].equals(obj)) {
                return hashObject[index];
            }
            index++;
        }
        return null;
    }

    public HashObject getAtIndex(int index) {
        return hashObject[index];
    }

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
        return (double) totalProbes / numTableElements;
    }
}