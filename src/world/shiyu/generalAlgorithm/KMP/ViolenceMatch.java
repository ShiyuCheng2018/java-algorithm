package world.shiyu.generalAlgorithm.KMP;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "ABCAAKJGXAMHXCGAJDGHBYXUAJSDSDAADGHKAGWWQASDAS";
        String str2 = "YXUAJS";

        System.out.println(violenceMatch(str1, str2));
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i 索引指向s1
        int j = 0; // j 索引指向s2

        while (i < s1Len && j < s2Len) { // 保证匹配时不越界

            if (s1[i] == s2[j]) {
                i++; // 指针后移
                j++;
            } else { // 没有匹配成功
                i = i - j + 1; // 返回到下一个索引
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
