public class AdvancedStringRecursion {
    public static void main(String[] args) {
        System.out.println("Permutations of abc:");
        permutations("", "abc");

        System.out.println("Remove duplicates: " + removeDuplicates("banana"));
        System.out.println("All substrings of 'ab':");
        substrings("ab", "");
    }

    public static void permutations(String prefix, String str) {
        if (str.length() == 0)
            System.out.println(prefix);
        for (int i = 0; i < str.length(); i++) {
            permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
        }
    }

    public static String removeDuplicates(String s) {
        if (s.length() <= 1)
            return s;
        if (s.charAt(0) == s.charAt(1))
            return removeDuplicates(s.substring(1));
        return s.charAt(0) + removeDuplicates(s.substring(1));
    }

    public static void substrings(String s, String curr) {
        if (s.length() == 0) {
            if (!curr.isEmpty())
                System.out.println(curr);
            return;
        }
        substrings(s.substring(1), curr);
        substrings(s.substring(1), curr + s.charAt(0));
    }
}
