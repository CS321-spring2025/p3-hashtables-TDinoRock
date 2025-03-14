import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Date;
import java.util.Scanner;
import java.io.PrintWriter;

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
            } else {
                debugLevel = 0;
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

        Hashtable linearHash = new LinearProbing(tableSize, loadFactor);
        Hashtable doubleHash = new DoubleHashing(tableSize, loadFactor);

        switch (dataSource) {
            case 1:
                System.out.println("HashtableExperiment: Input: Random Numbers   Loadfactor: " + loadFactor);
                insertRandomNumbers(linearHash, doubleHash, debugLevel);
                break;
            case 2:
                System.out.println("HashtableExperiment: Input: Date Value as Long   Loadfactor: " + loadFactor);
                insertDateValues(linearHash, doubleHash, debugLevel);
                break;
            case 3:
                System.out.println("HashtableExperiment: Input: Word-List   Loadfactor: " + loadFactor);
                insertWordList(linearHash, doubleHash, debugLevel);
                break;
        }

        switch (debugLevel) {
            case 0:
                printResults(linearHash, "Linear Probing");
                printResults(doubleHash, "Double Hashing");
                break;
            case 1:
                printResults(linearHash, "Linear Probing");
                System.out.println("HashtableExperiment: Saved dump of hash table");
                printResults(doubleHash, "Double Hashing");
                System.out.println("HashtableExperiment: Saved dump of hash table");
                dumpToFile("linearHash.txt", linearHash);
                dumpToFile("doubleHash.txt", doubleHash);
                break;
            case 2:
                for (int i = 0; i < tableSize; i++) {
                    HashObject obj1 = linearHash.hashObject[i];
                    if (obj1 != null) {
                        System.out.println(obj1.toString());
                    }
                    HashObject obj2 = doubleHash.hashObject[i];
                    if (obj2 != null) {
                        System.out.println(obj2.toString());
                    }
                }
                break;
        }
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

    private static void insertRandomNumbers(Hashtable linearHash, Hashtable doubleHash, int debugLevel) {
        Random random = new Random();
        while (linearHash.getInsertedElements() < linearHash.tableLoadFactor()) {
            int number = random.nextInt();
            linearHash.insert(new HashObject(number), debugLevel);
            doubleHash.insert(new HashObject(number), debugLevel);
        }
    }

    private static void insertDateValues(Hashtable linearHash, Hashtable doubleHash, int debugLevel) {
        long dateValue = new Date().getTime();
        while (linearHash.getInsertedElements() < linearHash.tableLoadFactor()) {
            dateValue += 1000;
            Date date = new Date(dateValue);
            linearHash.insert(new HashObject(date), debugLevel);
            doubleHash.insert(new HashObject(date), debugLevel);
        }
    }

    private static void insertWordList(Hashtable linearHash, Hashtable doubleHash, int debugLevel) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("word-list.txt"));
            while (scanner.hasNext() && linearHash.getInsertedElements()+1 < linearHash.tableLoadFactor()) {
                String word = scanner.next();
                linearHash.insert(new HashObject(word), debugLevel);
                doubleHash.insert(new HashObject(word), debugLevel);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }

    private static void printResults(Hashtable table, String method) {
        DecimalFormat formatter = new DecimalFormat("#.00");
        System.out.println("\n        Using " + method);
        System.out.println("HashtableExperiment: size of hash table is " + table.tableLoadFactor());
        System.out.println("        Inserted " + table.getTotalElements() + " elements, of which "
                + table.getDuplicateCount() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + formatter.format(table.getAverageProbes()));
    }

    private static void dumpToFile(String fileName, Hashtable table) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            for (int i = 0; i < table.getSize(); i++) {
                HashObject obj = table.getAtIndex(i);
                if (obj != null) {
                    out.println("table[" + i + "]: " + obj.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}