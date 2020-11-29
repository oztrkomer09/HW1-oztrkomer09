

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main{

    public Main(String fileName, int topN) throws IOException {
        //Complete this constructor

        List<String> allLines = Files.readAllLines(Paths.get(fileName));

        List<String> words = new ArrayList<>();

        for (String line : allLines) {
            for (String word : line.split(" ")) {
                if (word.trim().length() > 0)
                    words.add(convert(word));
            }
        }


        computeAvgLengthByFirstChar(words, topN);

    }

    private <E> String convert (String word) {
        word = word.toLowerCase();
        String rVal = "";
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetterOrDigit(word.charAt(i)))
                rVal += word.charAt(i);
        }
        return rVal;
    }



    private void computeAvgLengthByFirstChar(List<String> words, int topN) {
        Map<Character, List<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.charAt(0)))
                map.put(word.charAt(0), new ArrayList<>());
            map.get(word.charAt(0)).add(word);
        }

        List<CharWithAverage> resultList = new ArrayList<>();
        for (Character ch : map.keySet()) {
            List<String> list = map.get(ch);
            double sum = 0;
            for (String word : list) {
                sum += word.length();
            }
            double average = sum / list.size();
            resultList.add(new CharWithAverage(ch, average));

        }

        System.out.println("Av. Length");
        for (CharWithAverage ca : resultList) {
            System.out.println(ca.ch + "\t" + ca.average);
        }


        System.out.println(words.size());

        Set<Pair> set = calculateMinPairDist(words);

        int printSize = Math.min(topN, set.size());

        Pair[] array = set.toArray(new Pair[0]);
        Arrays.sort(array);
        for (int i = 0; i < printSize; i++) {
            System.out.println(array[i]);
        }
    }

    class CharWithAverage {
        char ch;
        double average;

        public CharWithAverage(char ch, double average) {
            this.ch = ch;
            this.average = average;
        }
    }

    private Set<Pair> calculateMinPairDist(List<String> words) {
        Set<Pair> set = new HashSet<>();

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String token1 = words.get(i);
                String token2 = words.get(j);

                if (token1.equals(token2))
                    continue;

                double distance = calculatesTotalDistance(words, token1, token2);
                set.add(new Pair(token1, token2, distance));
            }
        }


        return set;
    }

    private double calculatesTotalDistance(List<String> words, String token1, String token2) {
        int hz1 = 0;
        int hz2 = 0;
        for (String word : words) {
            if (word.equals(token1))
                hz1++;
            if (word.equals(token2))
                hz2++;
        }
        int sum = 0;
        for (int a = 0; a < words.size(); a++) {
            if (!words.get(a).equals(token1))
                continue;;
            for (int b = a; b < words.size(); b++) {
                if (!words.get(b).equals(token2))
                    continue;
                sum = sum + b - a;
                break;
            }
        }

        return hz1 * hz2 / (1 + Math.log(sum));
    }


    public static void main(String[] args) throws IOException {
        new Main(args[0],Integer.parseInt(args[1]));

    }


}