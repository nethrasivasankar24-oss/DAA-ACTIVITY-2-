class Solution {
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        int[] count = new int[128];

        // Store how many times each character is needed
        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int left = 0;
        int right = 0;

        int required = t.length();
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {

            char c = s.charAt(right);

            // Character is needed
            if (count[c] > 0) {
                required--;
            }

            count[c]--;
            right++;

            // Window contains all characters of t
            while (required == 0) {

                // Check if current window is smaller
                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }

                char leftChar = s.charAt(left);
                count[leftChar]++;

                // Removing this character makes window invalid
                if (count[leftChar] > 0) {
                    required++;
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }
}
