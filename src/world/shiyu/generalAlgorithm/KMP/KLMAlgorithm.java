package world.shiyu.generalAlgorithm.KMP;

public class KLMAlgorithm {
    public static void main(String[] args) {
        String str1 = "adjhajkgcjbcdgjqghwejkqhdajkb";
        String str2 = "jqghw";
    }

    // 获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest) {
        // 创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果字符串长度为1， 部分匹配值为0

        for (int i = 0, j = 0; i < dest.length(); i++) {
            // 当dest.chatAt(i) != dest.charAt(j), 我们需要从next[j-1]获取新的j
            // 直到我们发现有dest.charAt(i) == dest.charAt(j)
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 当dest.charAt(i) == dest.charAt(j) 满足时， 部分匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

}
