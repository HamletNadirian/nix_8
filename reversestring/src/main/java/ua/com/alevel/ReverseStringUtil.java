package ua.com.alevel;

public final class ReverseStringUtil {
    private ReverseStringUtil() {
    }

    public static String reverse(String src, Boolean isWord) {
        if (src != null && !src.isEmpty()) {

            String strReverse = "";
            String[] words = src.split(" ");
            if (isWord) {
                String temp = "";
                for (int i = 0; i < words.length; i++) {
                    for (int j = words[i].length() - 1; j >= 0; j--) {
                        strReverse += words[i].charAt(j);
                    }
                    strReverse += " ";
                }
            } else {
                for (int i = src.length() - 1; i >= 0; i--) {
                    strReverse += src.charAt(i);
                }
            }
            return strReverse;

        } else
            return "Введена пустая строка. Введите строку!";
    }

    public static String reverse(String src, String dest) {
        String strReverse = "";
        int index = src.lastIndexOf(dest);
        if (src.contains(dest)) {
            String newSt = src.substring(index, index + dest.length());

            for (int i = newSt.length() - 1; i >= 0; i--) {
                strReverse += newSt.charAt(i);
            }
            src = src.replace(dest, strReverse);
            return src;
        } else return "Строка: \"" + src + "\" не содержит подстроку: \"" + dest + "\"";
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (src.length() > (firstIndex) && src.length() >= (lastIndex)&&firstIndex<lastIndex) {
        String subStrSppace = src.substring(firstIndex, lastIndex);

        int i = 0,
                spaceCount = 0;
        while (i < subStrSppace.length()) {
            if (subStrSppace.charAt(i) == ' ') {
                spaceCount++;
            }
            i++;
        }

            String subStr = src.substring(firstIndex, lastIndex + spaceCount);
            String wordSub[] = subStr.split(" ");
            String revSt[] = new String[wordSub.length];
            for (i = 0; i < wordSub.length; i++) {
                revSt[i] = reverse(wordSub[i], false);
            }

            StringBuffer sb = new StringBuffer();
            for (i = 0; i < revSt.length; i++) {
                sb.append(revSt[i]);
                sb.append(' ');
            }

            String str_rev = sb.toString();
            char[] str_to_char = src.toCharArray();
            int char_rev = 0;
            for (i = firstIndex; i < lastIndex + spaceCount; i++) {
                str_to_char[i] = str_rev.charAt(char_rev);
                char_rev++;
            }
            src = String.valueOf(str_to_char);
            System.out.println(src);
            return src;
        }
        return "Индексы введены не корректно. Введите индексы корректно!!!";
    }
}

