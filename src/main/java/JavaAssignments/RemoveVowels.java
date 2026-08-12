package JavaAssignments;

public class RemoveVowels {

    public static void main(String[] args) {

        StringBuffer str = new StringBuffer("automation");

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                    ch == 'o' || ch == 'u') {

                str.deleteCharAt(i);

                i--;
            }
        }

        System.out.println("After removing vowels: " + str);
    }
}