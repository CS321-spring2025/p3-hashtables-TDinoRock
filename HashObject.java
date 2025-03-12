public class HashObject {
    private Object key;
    private int frequencyCount;
    private int probeCount;

    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    public Object getKey() {
        return key;
    }

    public int getFrequencyCount() {
        return frequencyCount;
    }

    public void incrementFrequencyCount() {
        frequencyCount++;
    }

    public int getProbeCount() {
        return probeCount;
    }

    public void incrementProbeCount() {
        probeCount++;
    }

    @Override
    public boolean equals(Object key) {
        if (key instanceof HashObject) {
            HashObject object = (HashObject) key;
            return object.getKey() == key;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "HashObject{" +
                "key=" + key +
                ", frequencyCount=" + frequencyCount +
                ", probeCount=" + probeCount +
                '}';
    }
}
