import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class AnagramSolver {

    private AnagramSolver() {};

    private static ArrayList<String> readFile(String filename) throws FileNotFoundException {
        File myFile = new File(filename);
        Scanner myScanner = new Scanner(myFile);
        ArrayList<String> words = new ArrayList<>();

        while (myScanner.hasNextLine()) {
            String word = myScanner.nextLine();
            words.add(word);
        }

        myScanner.close();

        return words;
    }

    private static String bubbleSort(String word) {
        char[] wordArray = word.toCharArray();
        char swap;

        for (int i = 0; i < wordArray.length - 1; i++) {
            for (int j = 0; j < wordArray.length - i - 1; j++) {
                if (wordArray[j] > wordArray[j + 1]) {
                    swap = wordArray[j];
                    wordArray[j] = wordArray[j + 1];
                    wordArray[j + 1] = swap;
                }
            }
        }

        return String.valueOf(wordArray);
    }

    /**
     * Input: name of text file (containing English words).
     * Output: a hashmap of lists of words that are anagrams.
     * @param filename
     * @return
     */
    public static HashMap<String, ArrayList<String>> anagrams(String filename) throws FileNotFoundException {
        ArrayList<String> words = readFile(filename);
        HashMap<String, ArrayList<String>> anagrams = new HashMap<>();
        String key;
        ArrayList<String> curr_set;

        for (String w: words) {
            key = bubbleSort(w);
            curr_set = anagrams.get(key);
            if (curr_set == null) {
                curr_set = new ArrayList<>();
            }

            curr_set.add(w);
            anagrams.put(key, curr_set);
        }

        return anagrams;
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: largest list of words in hashmap that are anagrams.
     * @param anagrams
     * @return
     */
    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        int max_size = 0;
        ArrayList<String> max_size_value = new ArrayList<>();
        for (ArrayList<String> value: anagrams.values()) {
            if (value.size() > max_size) {
                max_size = value.size();
                max_size_value = value;
            }
        }

        return max_size_value;
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: prints all key value pairs in the hashmap.
     * @param anagrams
     */
    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> value;
        for (String key: anagrams.keySet()) {
            value = anagrams.get(key);
            System.out.print(key + ": ");
            System.out.println(value);
        }
    }

}