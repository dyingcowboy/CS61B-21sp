package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import org.junit.Assert.*;
import java.util.Comparator;
import static org.junit.Assert.assertEquals;


public class MaxArrayDequeTest {
    /*A simple comparator for Integers that compares them in natural order.
    * */

    public Comparator<Integer> getIntegerComparator(){
        return new IntegerComparator();
    }

    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b ;
        }
    }

    public Comparator<String> getStringLengthComparator() {
        return new StringLengthComparator();
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2){
            return s1.length() - s2.length();
        }
    }

    @Test
    public void testMaxWithDefaultComparator(){
        // 1.Setup
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(getIntegerComparator());
        mad.addLast(5);
        mad.addLast(100);
        mad.addLast(2);
        mad.addLast(99);

        // 2.Invoke and Assert
        assertEquals("Should return the largest integer.", 100, (Object)mad.max());
    }

    @Test
    public void testMaxWithCustomComparator(){
        // 1. Setup
        Comparator<String> defaultComparator = String::compareTo;
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(defaultComparator);
        mad.addLast("apple");
        mad.addLast("banana");
        mad.addLast("kiwi");
        mad.addLast("strawberry");

        // 2. Invoke and Assert with default comparator
        assertEquals("Default comparator should find 'strawberry'.", "strawberry", mad.max());

        // 3. Invoke and Assert with custom comparator
        Comparator<String> lengthComparator = getStringLengthComparator();
        assertEquals("Length comparator should find 'strawberry'.", "strawberry", mad.max(lengthComparator));

        // 4. Test with a different custom comparator that finds the "smallest" element (reverse order)
        Comparator<String> reverseComparator = defaultComparator.reversed();
        assertEquals("Reversed comparator should find 'apple'.", "apple", mad.max(reverseComparator));
    }

}
