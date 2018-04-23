/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.havm.babibibo.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author thienkq1@viettel.com.vn
 * @since 12,Apr,2010
 * @version 1.0
 */
public final class StringUtils {

    /**
     * COLON_SEPARATOR.
     */
    public static final String COLON_SEPARATOR = ":";
    /**
     * COMMA_SEPARATOR.
     */
    public static final String COMMA_SEPARATOR = ",";
    /**
     * alphabeUpCaseNumber.
     */
    private static final String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /**
     * INVOICE_MAX_LENGTH.
     */
    private static final int INVOICE_MAX_LENGTH = 7;
    /**
     * ZERO.
     */
    private static final String ZERO = "0";

    /**
     * Creates a new instance of StringUtils
     */
    private StringUtils() {
    }

    /**
     *
     * @param in
     */
    public static String convertReaderToString(Reader in) throws IOException {
        final char[] buffer = new char[0x10000];
        StringBuilder out = new StringBuilder();

        int read;
        do {
            read = in.read(buffer, 0, buffer.length);
            if (read > 0) {
                out.append(buffer, 0, read);
            }
        } while (read >= 0);
        return out.toString();
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String convertReaderToString(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        in.close();
        return sb.toString();
    }

    /**
     *
     * @param htmlString
     * @return
     */
    public static String removeHTML(String htmlString) {
        // Remove HTML tag from java String
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");

        // Remove Carriage return from java String
        noHTMLString = noHTMLString.replaceAll("\r", "<br/>");
        // Remove New line from java string and replace html break
        noHTMLString = noHTMLString.replaceAll("\n", " ");
        return noHTMLString;
    }

    /**
     * Escape html characters
     *
     * @param string input string
     * @return null if input string is null, escaped string if input string is
     * not null
     */
    public static String deEscapeHtml(String string) {
        if (string == null) {
            return null;
        }
        String result = string;
        List<String[]> codecs = new ArrayList<>();
        codecs.add(new String[]{"&#39;", "'"});
        codecs.add(new String[]{"&quot;", "\""});
        codecs.add(new String[]{"&lt;", "<"});
        codecs.add(new String[]{"&gt;", ">"});
        codecs.add(new String[]{"&amp;", "&"});
        for (int i = 0; i < codecs.size(); i++) {
            while (result.contains(codecs.get(i)[0])) {
                result = result.replace(codecs.get(i)[0], codecs.get(i)[1]);
            }
        }
        return result;
    }

    /**
     * Escape html characters
     *
     * @param string input string
     * @return null if input string is null, escaped string if input string is
     * not null
     */
    public static String escapeHtml(String string) {
        if (string == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int length = string.length();
        for (int i = 0; i < length; ++i) {
            char c = string.charAt(i);
            switch (c) {
                case '\'':
                    stringBuilder.append("&39;");
                    break;
                case '"':
                    stringBuilder.append("&quot;");
                    break;
                case '<':
                    stringBuilder.append("&lt;");
                    break;
                case '>':
                    stringBuilder.append("&gt;");
                    break;
                case '&':
                    stringBuilder.append("&amp;");
                    break;
                default:
                    stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * escape special character in sql
     *
     * @param input input
     * @return String
     */
    public static String escapeSql(String input) {
        String result = input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
        return result;
    }

    /*
     * Check String that containt only AlphabeUpCase and Number
     * Return True if String was valid, false if String was not valid
     */
    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (!alphabeUpCaseNumber.contains(temp)) {
                result = false;
                return result;
            }
        }
        return result;
    }

    /**
     * Get like string in sql
     *
     * @param content
     * @return String content
     */
    public static String toLikeString(String content) {
        return "%" + StringUtils.escapeSql(content.toLowerCase().trim()) + "%";
    }

    /**
     * Get long value from string
     *
     * @param content
     * @return Long value (null: if is not numeric)
     */
    public static Long toLong(String content) {
        Long result = null;
        try {
            result = Long.valueOf(content);
        } catch (NumberFormatException ex) {
            //     ex.printStackTrace();
        }
        return result;
    }

    /**
     * break long word
     *
     * @param input
     * @param sublen
     * @return
     */
    public static String breakLongWord(String input, int sublen) {
        if (input.length() < sublen) {
            return input;
        }
        String ret = "";
        int cur = 0;
        boolean test = false;
        while ((cur + sublen - 1) <= input.length()) {
            test = true;
            ret += input.substring(cur, cur + sublen - 1) + " ";
            cur += sublen - 1;
        }
        ret += input.substring(cur, input.length());
        if (!test) {
            ret = input;
        }
        return ret;
    }

    /**
     * format long word
     *
     * @param input
     * @param length
     * @param sublen
     * @return
     */
    public static String formatString(String input, int length, int sublen) {
        String ret = "";
        String tmpInput = ((input.length() > length) ? input.substring(0, length) : input);
        String[] arrWords = tmpInput.split(" ");
        for (int i = 0; i < arrWords.length; i++) {
            ret += breakLongWord(arrWords[i], sublen) + " ";
        }
        if (input.length() > length) {
            ret += " ...";
        }
        return ret;
    }

    /**
     * wrap text
     *
     * @param input
     * @param sublen
     * @return
     */
    public static String wrapLongText(String input, int sublen) {
        String ret = "";
        String[] arrWords = input.split(" ");
        for (int i = 0; i < arrWords.length; i++) {
            ret += breakLongWord(arrWords[i], sublen) + " ";
        }
        return ret;
    }

    /**
     * Convert float mark to String
     *
     * @param mark
     * @return string
     */
    public static String markToString(Float mark) {
        String result = "";
        if (mark != null) {
            float roundMark = Math.round(mark * 100);
            roundMark = roundMark / 100;
            result = Float.toString(roundMark);
        }
        return result;
    }

    /**
     * lower Case First Character of string
     *
     * @author NgocTM
     * @param inString
     * @return String lower case first character string
     */
    public static String lowerCaseFirstCharacter(String inString) {
        if (inString != null && inString.isEmpty()) {
            String firstC = inString.substring(0, 1);
            inString = inString.replaceFirst(firstC, firstC.toLowerCase());
        }
        return inString;
    }

    /**
     * upper Case First Character of string
     *
     * @author NgocTM
     * @param inString
     * @return String upper case first character string
     */
    public static String upperCaseFirstCharacter(String inString) {
        if (inString != null && !inString.isEmpty()) {
            String firstC = inString.substring(0, 1);
            inString = inString.replaceFirst(firstC, firstC.toUpperCase());
        }
        return inString;
    }

    public static String myConvertString(String tmp) {
        if (tmp == null) {
            return null;
        } else {
            return tmp.replaceAll("\\<.*?\\>", "[removed]");
//            return strip_tags(tmp, "b,i,u");
        }
    }

    public static int countMatches(String str, String sub) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        if (sub == null || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}
