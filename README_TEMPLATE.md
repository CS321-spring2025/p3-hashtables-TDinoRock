# Project 3: Experiemnting With Hashing

* Author: Trennon Talbot
* Class: CS321 Section 2
* Semester: Spring

## Overview

The HashTable program creates an array and fills it with HashObjects based on the probing algorithm; both linear and double algorithms are used.
These HashObjects can be integers, doubles, or strings depending on user input. How much of the list is filled is also dependent on user input.
The size of the list is the prime number 95,791.

## Reflection

Unlike the previous projects, in this project I had to write all of the scripts that would be used. This includes the main methods, the constructors,
and all of the necessary helper methods. This was certainly a challenge to figure out all of the methods that I needed to create a HashTable. I created
the insert loop pretty quickly, but over time I added neccessary update varables as I moved forward throughout the project. It was a great experience
learning how to create and populate my own files through code.

One of my greatest challenges was effectively creating a looping limit based on how full the table is supposed to get. I needed to create a new update
varable and compare it to the intended amount of unique inserts in the HashTable. Most of my time spent in the insert method was logically running through
where each update variable needed to be updated. The most unique challenge was, for a while, I had 44 extra duplicates compared to the project examples.
These seemed to be very random duplicates since it happened to random insert variables. It was not until later that I learned that the Scanner methods pares
the lines that are read. Meaning, typicially empty or special characters would not be read into a text file. By switching to BufferedReader, which reads the
entire line, special characters are not accidently excluded. This fixed the extra duplicates since special characters are read and inserted into the HashTable.

## Compiling and Using

1. Download the folder
2. Type in the terminal "javac <nameofjavafile.java>" to compile the different java files
3. After compiling, write in the terminal
    "java CacheExperiment <dataSource> <loadFactor> optional:<debugLevel>"
        - dataSource is the type of data that is passed in:
                <1> is random integers
                <2> is random doubles
                <3> is strings from a word-text file
        - loadFactor is how full to insert new HashObjects from dataSource, this range is from 0 to 1
        - debugLevel
                <0> prints a summary of the inserted HashObjects into the HashTable, this is also the default setting
                <1> prints a summary of the inserted HashObjects and creates a file of the final results
                <2> prints a summary of the inserted HashObjects and lists in the terminal whether the element are inserted or a duplicate

## Results 

Terminal Input: java HashTableExperiment     3           0.9                    0
                                        <dataSource> <loadFactor> optional:<debugLevel>

Terminal Output:
    HashtableExperiment: Found a twin prime table capacity: 95791
    HashtableExperiment: Input: Word-List   Loadfactor: 0.9

            Using Linear Probing
    HashtableExperiment: size of hash table is 86212
            Inserted 2840050 elements, of which 2753838 were duplicates
            Avg. no. of probes = 19.81

            Using Double Hashing
    HashtableExperiment: size of hash table is 86212
            Inserted 2840050 elements, of which 2753838 were duplicates
            Avg. no. of probes = 2.57

## Sources used

- VSCode CoPilot
- Reddit Posts
- W3Schools
- GeekForGeeks
- StackOverflow
