package com.utils;

import com.collections.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: Nitish Goyal Date: 7/09/18 Time: 2:15 PM
 */
@SuppressWarnings("unused")
public final class StringUtils {


    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isBlank(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static String blankIfNull(String stringToCheck) {
        if (stringToCheck == null) {
            stringToCheck = org.apache.commons.lang.StringUtils.EMPTY;
        }
        return stringToCheck;
    }

    public static String nullIfBlank(String stringToCheck) {
        if (StringUtils.isBlank(stringToCheck)) {
            return null;
        }
        return stringToCheck;
    }

    public static String lowercaseFirstCharacter(String input) {
        if (isNotBlank(input)) {
            return Character.toLowerCase(input.charAt(0)) + input.substring(1);
        }
        return input;
    }

    public static String uppercaseFirstCharacter(String input) {
        if (isNotBlank(input)) {
            return Character.toUpperCase(input.charAt(0)) + input.substring(1);
        }
        return input;
    }

    public static String upperCaseFirstCharacterLowerCaseOthers(String input) {
        if (isNotBlank(input)) {
            return Character.toUpperCase(input.charAt(0)) + input.substring(1)
                    .toLowerCase();
        }
        return input;
    }

    public static List<String> tokenize(String input,
                                        TokenType... tokenTypes) {
        PreConditions.positiveInt(tokenTypes.length);
        List<String> rv = new ArrayList<>();
        String[] split = input.split(getSplitRegex(tokenTypes));
        for (String s : split) {
            s = s.trim();
            if (StringUtils.isNotBlank(s)) {
                rv.add(s);
            }
        }
        return rv;
    }

    private static String getSplitRegex(TokenType[] tokenTypes) {
        // something like: "[,\\s]+";
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (TokenType tokenType : tokenTypes) {
            builder.append(tokenType.pattern);
        }
        builder.append("+]");
        return builder.toString();
    }

    public static int countAlphaNumericCharacters(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        int count = 0;
        char[] chars = text.toCharArray();
        for (char character : chars) {
            if (Character.isLetterOrDigit(character)) {
                count++;
            }
        }
        return count;
    }

    public static boolean containsAny(String input,
                                      List<String> stringsToCheck) {
        for (String stringToCheck : stringsToCheck) {
            if (org.apache.commons.lang.StringUtils.contains(input, stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAnyIgnoreCase(String input,
                                                List<String> stringsToCheck) {
        for (String stringToCheck : stringsToCheck) {
            if (org.apache.commons.lang.StringUtils.containsIgnoreCase(input, stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    public static String join(String delimiter,
                              String... elements) {
        if (CollectionUtils.isEmpty(elements)) {
            return org.apache.commons.lang.StringUtils.EMPTY;
        }
        return org.apache.commons.lang.StringUtils.join(elements, delimiter);
    }

    public static String join(List<String> input,
                              TokenType tokenType) {
        if (CollectionUtils.isEmpty(input) || tokenType == null) {
            return null;
        }

        return org.apache.commons.lang.StringUtils.join(input.toArray(new String[]{}), tokenType.pattern);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static void replace(Set<String> strings) {
        String[] stringsArray = strings.toArray(new String[0]);
        for (int i = 0; i < stringsArray.length; ++i) {
            stringsArray[i] = stringsArray[i].toLowerCase();
        }
        strings.clear();
        strings.addAll(Arrays.asList(stringsArray));
    }

    public static boolean equalsWithBlankCheck(String str1,
                                               String str2) {
        return blankIfNull(str1).equals(blankIfNull(str2));
    }

    /**
     * Check if a given string ends with any one among the collection of given suffix. This method uses {@link
     * String#endsWith(String)} to validate this.
     *
     * @param str String to check for suffixes
     * @param suffixes Collection of string suffix to check for
     * @return returns true if given string ends with any one of the given suffix.
     */
    public static boolean endsWith(String str,
                                   Collection<String> suffixes) {
        if (isBlank(str) || CollectionUtils.isEmpty(suffixes)) {
            return false;
        }
        for (String suffix : CollectionUtils.nullAndBlankSafeValueSet(suffixes)) {
            if (str.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static enum TokenType {
        WHITESPACE("\\s"),
        COMMA(","),
        COLON(":"),
        UNDER_SCORE("_"),
        DOT("."),
        PLUS("+"),
        MINUS("-"),
        NEW_LINE("\n");
        private final String pattern;

        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

}


