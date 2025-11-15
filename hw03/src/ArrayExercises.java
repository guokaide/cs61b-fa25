public class ArrayExercises {
    /** Returns the second to last item in the given array.
     *  Assumes the array has at least 2 elements. */
    public static String secondToLastItem(String[] items) {
        return items[items.length - 2];
    }    

    /** Returns the difference between the minimum and maximum item in the given array */
    public static int minMaxDifference(int[] items) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int x : items) {
            min = Math.min(x, min);
            max = Math.max(x, max);
        }
        return Math.abs(max - min);
    }
}
