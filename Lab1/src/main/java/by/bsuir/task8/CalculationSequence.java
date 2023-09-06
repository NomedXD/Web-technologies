package by.bsuir.task8;

import java.util.ArrayList;
import java.util.List;

public class CalculationSequence {
    public static List<Integer> calculateSequence(List<Integer> a, List<Integer> b) {
        int indexA = 0;
        int indexB = 0;
        ArrayList<Integer> resultList = new ArrayList<>();
        while (indexB != b.size() && indexA != a.size() - 1) {
            if (a.get(indexA) <= b.get(indexB) && a.get(indexA + 1) > b.get(indexB)) {
                indexA++;
                resultList.add(indexA);
                a.add(indexA, b.get(indexB));
                indexB++;
            } else {
                indexA++;
            }
        }
        while (indexB != b.size()) {
            resultList.add(indexA+1);
            indexA++;
            indexB++;
        }
        return resultList;
    }
}
