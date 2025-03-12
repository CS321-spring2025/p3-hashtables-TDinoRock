import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        int dataSource;
        double loadFactor;
        int debugLevel = 0;

        try {
            dataSource = Integer.parseInt(args[0]);
            loadFactor = Double.parseDouble(args[1]);
            if (args.length == 3) {
                debugLevel = Integer.parseInt(args[2]);
            }
        } catch (NumberFormatException e) {
            printUsage();
            return;
        }

        if (dataSource < 1 || dataSource > 3 || loadFactor <= 0 || loadFactor > 1 || debugLevel < 0 || debugLevel > 2) {
            printUsage();
            return;
        }

        int tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableSize);

        Hashtable linearProbingTable = new LinearProbing(tableSize, loadFactor);
        Hashtable doubleHashingTable = new DoubleHashing(tableSize, loadFactor);

        switch (dataSource) {
            case 1:
                System.out.println("HashtableExperiment: Input: Random Numbers   Loadfactor: " + loadFactor);
                insertRandomNumbers(linearProbingTable, doubleHashingTable, tableSize, debugLevel);
                break;
            case 2:
                System.out.println("HashtableExperiment: Input: Date Value as Long   Loadfactor: " + loadFactor);
                insertDateValues(linearProbingTable, doubleHashingTable, tableSize, debugLevel);
                break;
            case 3:
                System.out.println("HashtableExperiment: Input: Word-List   Loadfactor: " + loadFactor);
                insertWordList(linearProbingTable, doubleHashingTable, tableSize, debugLevel);
                break;
        }

        printResults(linearProbingTable, "Linear Probing");
        printResults(doubleHashingTable, "Double Hashing");
    }

    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println(" <dataSource>: 1 ==> random numbers");
        System.out.println("               2 ==> date value as a long");
        System.out.println("               3 ==> word list");
        System.out.println(" <loadFactor>: The ratio of objects to table size, denoted by alpha = n/m");
        System.out.println(" <debugLevel>: 0 ==> print summary of experiment");
        System.out.println("               1 ==> save the two hash tables to a file at the end");
        System.out.println("               2 ==> print debugging output for each insert");
    }

    private static void insertRandomNumbers(Hashtable linearProbingTable, Hashtable doubleHashingTable, int tableSize, int debugLevel) {
        Random random = new Random();
        for (int i = 0; i < tableSize; i++) {
            int number = random.nextInt();
            linearProbingTable.insert(new HashObject(number), debugLevel);
            doubleHashingTable.insert(new HashObject(number), debugLevel);
        }
    }

    private static void insertDateValues(Hashtable linearProbingTable, Hashtable doubleHashingTable, int tableSize, int debugLevel) {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < tableSize; i++) {
            long dateValue = currentTime + i;
            linearProbingTable.insert(new HashObject(dateValue), debugLevel);
            doubleHashingTable.insert(new HashObject(dateValue), debugLevel);
        }
    }

    private static void insertWordList(Hashtable linearProbingTable, Hashtable doubleHashingTable, int tableSize, int debugLevel) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("word-list.txt"));
            while(scanner.hasNext()) {
                String word = scanner.next();
                linearProbingTable.insert(new HashObject(word), debugLevel);
                doubleHashingTable.insert(new HashObject(word), debugLevel);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }

    private static void printResults(Hashtable table, String method) {
        System.out.println("        Using " + method);
        System.out.println("HashtableExperiment: size of hash table is " + table.getSize());
        System.out.println("        Inserted " + table.getTotalElements() + " elements, of which " + table.getDuplicateCount() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + table.getAverageProbes());
    }
}