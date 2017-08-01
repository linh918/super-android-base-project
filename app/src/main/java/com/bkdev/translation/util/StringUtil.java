//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bkdev.translation.util;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String NUMBER_PATTERN = "(-|\\+)?[0-9]+(\\.[0-9]+)?";
    private static final char[] marked = new char[]{'à', 'á', 'ả', 'ã', 'ạ', 'ă', 'ằ', 'ắ', 'ẳ', 'ẵ', 'ặ', 'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ', 'À', 'Á', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ằ', 'Ắ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ẫ', 'Ậ', 'è', 'é', 'ẻ', 'ẽ', 'ẹ', 'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ', 'È', 'É', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ', 'ì', 'í', 'ỉ', 'ĩ', 'ị', 'Ì', 'Í', 'Ỉ', 'Ĩ', 'Ị', 'ò', 'ó', 'ỏ', 'õ', 'ọ', 'ô', 'ồ', 'ố', 'ổ', 'ỗ', 'ộ', 'ơ', 'ờ', 'ớ', 'ở', 'ỡ', 'ợ', 'Ò', 'Ó', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ', 'Ơ', 'Ờ', 'Ớ', 'Ở', 'Ỡ', 'Ợ', 'ù', 'ú', 'ủ', 'ũ', 'ụ', 'ư', 'ừ', 'ứ', 'ử', 'ữ', 'ự', 'Ù', 'Ú', 'Ủ', 'Ũ', 'Ụ', 'ỳ', 'ý', 'ỷ', 'ỹ', 'ỵ', 'Ỳ', 'Ý', 'Ỷ', 'Ỹ', 'Ỵ', 'đ', 'Đ', 'Ư', 'Ừ', 'Ử', 'Ữ', 'Ứ', 'Ự'};
    private static final char[] notmarked = new char[]{'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'i', 'i', 'i', 'i', 'i', 'I', 'I', 'I', 'I', 'I', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'U', 'U', 'U', 'U', 'U', 'y', 'y', 'y', 'y', 'y', 'Y', 'Y', 'Y', 'Y', 'Y', 'd', 'D', 'U', 'U', 'U', 'U', 'U', 'U'};
    public static final String EMPTY_ARRAY = "{}";
    public static final String NULL_STRING = "null";
    private static final String ARRAY_START = "{";
    private static final String ARRAY_END = "}";
    public static final String FOLDER_SEPARATOR = "/";
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    public static final String TOP_PATH = "..";
    public static final String CURRENT_PATH = ".";
    public static final char EXTENSION_SEPARATOR = '.';
    public static final String EMPTY_STRING = "";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String STRING_SPECIAL_CHAR = "!~`@#$%^&*-+();:=,.<>?\\/\"\'_|€£¥•";
    private static Pattern emailPattern;
    private static Matcher emailMatcher;

    public static String replacesAll(String source, String findString, String replaceString) {
        return source != null && findString != null && replaceString != null ? source.replaceAll(findString, replaceString) : source;
    }

    public static String removeAllOccurrenceOf(String source, String removeString) {
        return replacesAll(source, removeString, "");
    }


    public static String convertToEngString(String unicodeString) {
        if (unicodeString != null && unicodeString.trim().length() != 0) {
            unicodeString = unicodeString.trim();

            for (int i = 0; i < marked.length; ++i) {
                unicodeString = unicodeString.replace(marked[i], notmarked[i]);
            }

            return unicodeString;
        } else {
            return "";
        }
    }

    public static int convertCharToASCII(char character) {
        return character;
    }

    public static char convertASCIIToChar(int ascii) {
        return (char) ascii;
    }

    @SuppressLint({"NewApi"})
    public static String convertToHex(String message) {
        byte[] bytes = message.getBytes(Charset.forName("UTF-8"));
        char[] hexArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];

        for (int i = 0; i < bytes.length; ++i) {
            int v = bytes[i] & 255;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String convertToShortPhoneNumber(String phone) {
        return phone != null && phone.trim().length() != 0 ? (phone.startsWith("84") ? "0" + phone.substring(2) : (phone.startsWith("+84") ? "0" + phone.substring(3) : (phone.startsWith("+084") ? "0" + phone.substring(4) : phone))) : "";
    }

    public static String convertToLongPhoneNumber(String phone) {
        return phone != null && phone.trim().length() != 0 ? (phone.startsWith("0") ? "+84" + phone.substring(1) : phone) : "";
    }

    public static String convertToCurrency(double currency) {
        return NumberFormat.getCurrencyInstance().format(currency);
    }

    public static String convertToMoney(double currency, String commaOrDot) {
        String result = "";
        DecimalFormat df = new DecimalFormat("###.#");
        String money = "" + df.format(currency);

        for (int i = money.length() - 1; i >= 0; --i) {
            int offsetLast = money.length() - 1 - i;
            if (offsetLast > 0 && offsetLast % 3 == 0 && money.charAt(i) != 43 && money.charAt(i) != 45) {
                result = commaOrDot + result;
            }

            result = money.charAt(i) + result;
        }

        return result;
    }

    public static String convertToMoney(String money, String commaOrDot) {
        try {
            String ne = "";
            double currency = Double.parseDouble(money);
            DecimalFormat df = new DecimalFormat("###.#");
            String moneyStr = "" + df.format(currency);

            for (int i = moneyStr.length() - 1; i >= 0; --i) {
                int offsetLast = moneyStr.length() - 1 - i;
                if (offsetLast > 0 && offsetLast % 3 == 0 && moneyStr.charAt(i) != 43 && moneyStr.charAt(i) != 45) {
                    ne = commaOrDot + ne;
                }

                ne = moneyStr.charAt(i) + ne;
            }

            return ne;
        } catch (NumberFormatException var9) {
            return money;
        }
    }

    public static String convertToLanguageTag(Locale locale) {
        return locale.getLanguage() + (locale.getCountry().length() > 0 ? "-" + locale.getCountry() : "");
    }

    public static String convertDecimalFormatSymbols(String formatString, double value) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
        return df.format(value);
    }

    public static String convertDecimalFormatSymbolsVN(String formatString, double value) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
        return df.format(value);
    }

    public static String convertDecimalFormatSymbolsVN1(String formatString, double str) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
        return df.format(str);
    }

    public static byte[] convertToBytes(String hex) {
        return convertToBytes(hex.toCharArray());
    }

    public static byte[] convertToBytes(char[] hex) {
        CharBuffer charBuffer = CharBuffer.wrap(hex);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000');
        Arrays.fill(byteBuffer.array(), (byte) 0);

        return bytes;
    }

    public static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; ++i) {
            int halfbyte = data[i] >>> 4 & 15;
            int two_halfs = 0;

            do {
                if (0 <= halfbyte && halfbyte <= 9) {
                    buf.append((char) (48 + halfbyte));
                } else {
                    buf.append((char) (97 + (halfbyte - 10)));
                }

                halfbyte = data[i] & 15;
            } while (two_halfs++ < 1);
        }

        return buf.toString();
    }

    public static String convertToString(Object[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(String.valueOf(array[i]));
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(boolean[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(byte[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(char[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append("\'").append(array[i]).append("\'");
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(double[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(float[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(int[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(long[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(short[] array) {
        if (array == null) {
            return "null";
        } else {
            int length = array.length;
            if (length == 0) {
                return "{}";
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; ++i) {
                    if (i == 0) {
                        sb.append("{");
                    } else {
                        sb.append(",");
                    }

                    sb.append(array[i]);
                }

                sb.append("}");
                return sb.toString();
            }
        }
    }

    public static String convertToString(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof Object[]) {
            return convertToString((Object[]) ((Object[]) obj));
        } else if (obj instanceof boolean[]) {
            return convertToString((boolean[]) ((boolean[]) obj));
        } else if (obj instanceof byte[]) {
            return convertToString((byte[]) ((byte[]) obj));
        } else if (obj instanceof char[]) {
            return convertToString((char[]) ((char[]) obj));
        } else if (obj instanceof double[]) {
            return convertToString((double[]) ((double[]) obj));
        } else if (obj instanceof float[]) {
            return convertToString((float[]) ((float[]) obj));
        } else if (obj instanceof int[]) {
            return convertToString((int[]) ((int[]) obj));
        } else if (obj instanceof long[]) {
            return convertToString((long[]) ((long[]) obj));
        } else if (obj instanceof short[]) {
            return convertToString((short[]) ((short[]) obj));
        } else {
            String str = obj.toString();
            return str != null ? str : "";
        }
    }

    public static Object[] sortAscendingArray(Object[] array) {
        if (array != null && array.length != 0) {
            Arrays.sort(array);
            return array;
        } else {
            return new String[0];
        }
    }

    public static Object[] sortAscendingAndRemoveDuplicate(Object[] array) {
        if (array != null && array.length != 0) {
            TreeSet set = new TreeSet();
            Object[] rs = array;
            int len$ = array.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object element = rs[i$];
                set.add(element);
            }

            rs = set.toArray();
            Arrays.sort(rs);
            return rs;
        } else {
            return array;
        }
    }

    public static Object[] sortDescendingStringArray(Object[] array) {
        if (array != null && array.length != 0) {
            Arrays.sort(array, Collections.reverseOrder());
            return array;
        } else {
            return new String[0];
        }
    }

    public static Object[] sortDescendingAndRemoveDuplicate(Object[] array) {
        if (array != null && array.length != 0) {
            TreeSet set = new TreeSet();
            Object[] rs = array;
            int len$ = array.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object element = rs[i$];
                set.add(element);
            }

            rs = set.toArray();
            Arrays.sort(rs, Collections.reverseOrder());
            return rs;
        } else {
            return array;
        }
    }

    private StringUtil() {
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLength = str.length();

            for (int i = 0; i < strLength; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLength = str.length();

            for (int i = 0; i < strLength; ++i) {
                if (Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    public static String trimOnlyOneWhitespaceBetween(String str) {
        return !hasLength(str) ? str : str.replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);
            int index = 0;

            while (sb.length() > index) {
                if (Character.isWhitespace(sb.charAt(index))) {
                    sb.deleteCharAt(index);
                } else {
                    ++index;
                }
            }

            return sb.toString();
        }
    }

    public static String trimStartingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }

            return sb.toString();
        }
    }

    public static String trimEndingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    public static String trimStartingCharacter(String str, char startingCharacter) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && sb.charAt(0) == startingCharacter) {
                sb.deleteCharAt(0);
            }

            return sb.toString();
        }
    }

    public static String trimEndingCharacter(String str, char endingCharacter) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == endingCharacter) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str != null && prefix != null) {
            if (str.startsWith(prefix)) {
                return true;
            } else if (str.length() < prefix.length()) {
                return false;
            } else {
                String lcStr = str.substring(0, prefix.length()).toLowerCase();
                String lcPrefix = prefix.toLowerCase();
                return lcStr.equals(lcPrefix);
            }
        } else {
            return false;
        }
    }

    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str != null && suffix != null) {
            if (str.endsWith(suffix)) {
                return true;
            } else if (str.length() < suffix.length()) {
                return false;
            } else {
                String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
                String lcPrefix = suffix.toLowerCase();
                return lcStr.equals(lcPrefix);
            }
        } else {
            return false;
        }
    }

    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        for (int i = 0; i < substring.length(); ++i) {
            int j = index + i;
            if (j >= str.length() || str.charAt(j) != substring.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static int countOccurrencesOf(String str, String substring) {
        if (str != null && substring != null && str.length() != 0 && substring.length() != 0 && str.length() >= substring.length()) {
            int count = 0;

            int idx;
            for (int pos = 0; (idx = str.indexOf(substring, pos)) != -1; pos = idx + substring.length()) {
                ++count;
            }

            return count;
        } else {
            return 0;
        }
    }

    public static String replacesAllOccurrenceOf(String inString, String oldPattern, String newPattern) {
        return hasLength(inString) && hasLength(oldPattern) && newPattern != null ? inString.replace(oldPattern, newPattern) : inString;
    }

    public static String replacesWhitespaceByHtmlCode(String str) {
        return replacesAllOccurrenceOf(str, " ", "%20");
    }

    public static String deletesAllOccurrenceOf(String inString, String pattern) {
        return replacesAllOccurrenceOf(inString, pattern, "");
    }

    public static String deleteAnyCharacterOccurrenceOf(String inString, String charsToDelete) {
        if (hasLength(inString) && hasLength(charsToDelete)) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < inString.length(); ++i) {
                char c = inString.charAt(i);
                if (charsToDelete.indexOf(c) == -1) {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return inString;
        }
    }

    public static String quote(String str) {
        return str != null ? "\'" + str + "\'" : null;
    }

    public static Object quoteIfString(Object obj) {
        return obj instanceof String ? quote((String) obj) : obj;
    }

    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str.length());
            if (capitalize) {
                sb.append(Character.toUpperCase(str.charAt(0)));
            } else {
                sb.append(Character.toLowerCase(str.charAt(0)));
            }

            sb.append(str.substring(1));
            return sb.toString();
        }
    }

    public static String convertFromUTF8(String s) {
        String out = null;

        try {
            out = new String(s.getBytes("US-ASCII"), Charset.forName("UTF8"));
            return out;
        } catch (UnsupportedEncodingException var3) {
            return null;
        }
    }

    public static String convertToUTF8(String s) {
        String out = null;

        try {
            byte[] e = s.getBytes("UTF-8");
            out = new String(s.getBytes("UTF-8"), Charset.forName("ASCII"));
            return out;
        } catch (UnsupportedEncodingException var3) {
            return null;
        }
    }

    public static String convertUnicodeToEngString(String unicodeString) {
        if (!hasLength(unicodeString)) {
            return "";
        } else {
            unicodeString = trimWhitespace(unicodeString);

            for (int i = 0; i < marked.length; ++i) {
                unicodeString = unicodeString.replace(marked[i], notmarked[i]);
            }

            return unicodeString;
        }
    }

    public static String convertBytesToHex(byte[] bytes) {
        char[] hexArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];

        for (int i = 0; i < bytes.length; ++i) {
            int v = bytes[i] & 255;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String convertPhonenumberStringToHeader0109(String phonestring) {
        if (!hasLength(phonestring)) {
            return "";
        } else {
            if (phonestring.startsWith("84")) {
                phonestring = phonestring.substring(2);
                phonestring = "0" + phonestring;
            } else if (phonestring.startsWith("+84")) {
                phonestring = phonestring.substring(3);
                phonestring = "0" + phonestring;
            }

            return phonestring;
        }
    }

    public static String convertDoubleToCurrency(double currency) {
        return NumberFormat.getCurrencyInstance().format(currency);
    }

    public static String getFilename(String path) {
        if (path == null) {
            return null;
        } else {
            int separatorIndex = path.lastIndexOf("/");
            return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
        }
    }

    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        } else {
            int sepIndex = path.lastIndexOf(46);
            return sepIndex != -1 ? path.substring(sepIndex + 1) : null;
        }
    }

    public static String stripFilenameExtension(String path) {
        if (path == null) {
            return null;
        } else {
            int sepIndex = path.lastIndexOf(46);
            return sepIndex != -1 ? path.substring(0, sepIndex) : path;
        }
    }

    public static String addRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf("/");
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith("/")) {
                newPath = newPath + "/";
            }

            return newPath + relativePath;
        } else {
            return relativePath;
        }
    }

    public static String toLanguageTag(Locale locale) {
        return locale.getLanguage() + (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
    }

    public static String decimalFormatSymbols(String formatString, Double str) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
        return df.format(str);
    }

    public static String decimalFormatSymbolsVN(String formatString, Double str) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
        return df.format(str);
    }

    public static String[] addStringToArray(String[] array, String str) {
        if (array.length != 0 && array != null) {
            String[] newArr = new String[array.length + 1];
            System.arraycopy(array, 0, newArr, 0, array.length);
            newArr[array.length] = str;
            return newArr;
        } else {
            return new String[]{str};
        }
    }

    public static boolean containsInArray(String[] array, String str) {
        String[] arr$ = array;
        int len$ = array.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String element = arr$[i$];
            if (str.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        if (array1 != null && array1.length != 0) {
            if (array2 != null && array2.length != 0) {
                String[] newArr = new String[array1.length + array2.length];
                System.arraycopy(array1, 0, newArr, 0, array1.length);
                System.arraycopy(array2, 0, newArr, array1.length, array2.length);
                return newArr;
            } else {
                return array1;
            }
        } else {
            return array2;
        }
    }

    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (array1 != null && array1.length != 0) {
            if (array2 != null && array2.length != 0) {
                ArrayList result = new ArrayList();
                result.addAll(Arrays.asList(array1));
                String[] arr$ = array2;
                int len$ = array2.length;

                for (int i$ = 0; i$ < len$; ++i$) {
                    String str = arr$[i$];
                    if (!result.contains(str)) {
                        result.add(str);
                    }
                }

                return toStringArray((Collection) result);
            } else {
                return array1;
            }
        } else {
            return array2;
        }
    }

    public static String[] intersectionStringArrays(String[] array1, String[] array2) {
        if (array1 != null && array2 != null) {
            ArrayList result = new ArrayList();
            String[] arr$ = array1;
            int len$ = array1.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String str = arr$[i$];
                if (Arrays.asList(array2).contains(str)) {
                    result.add(str);
                }
            }

            return toStringArray((Collection) result);
        } else {
            return null;
        }
    }

    public static String[] sortAscendingStringArray(String[] array) {
        if (array != null && array.length != 0) {
            Arrays.sort(array);
            return array;
        } else {
            return new String[0];
        }
    }

    public static String[] sortDescendingStringArray(String[] array) {
        if (array != null && array.length != 0) {
            Arrays.sort(array, Collections.reverseOrder());
            return array;
        } else {
            return new String[0];
        }
    }

    public static String[] toStringArray(Collection<String> collection) {
        return collection == null ? null : (String[]) collection.toArray(new String[collection.size()]);
    }

    public static String[] toStringArray(Enumeration<String> enumeration) {
        if (enumeration == null) {
            return null;
        } else {
            ArrayList list = Collections.list(enumeration);
            return (String[]) list.toArray(new String[list.size()]);
        }
    }

    public static String[] trimArrayElements(String[] array) {
        if (array != null && array.length != 0) {
            String[] result = new String[array.length];

            for (int i = 0; i < array.length; ++i) {
                String element = array[i];
                result[i] = element != null ? trimWhitespace(element) : null;
            }

            return result;
        } else {
            return new String[0];
        }
    }

    public static String[] removeDuplicateAndSortStrings(String[] array) {
        if (array != null && array.length != 0) {
            TreeSet set = new TreeSet();
            String[] arr$ = array;
            int len$ = array.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String element = arr$[i$];
                set.add(element);
            }

            return toStringArray((Collection) set);
        } else {
            return array;
        }
    }

    public static String[] split(String toSplit, String delimiter) {
        if (hasLength(toSplit) && hasLength(delimiter)) {
            int offset = toSplit.indexOf(delimiter);
            if (offset < 0) {
                return null;
            } else {
                String beforeDelimiter = toSplit.substring(0, offset);
                String afterDelimiter = toSplit.substring(offset + delimiter.length());
                return new String[]{beforeDelimiter, afterDelimiter};
            }
        } else {
            return null;
        }
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static boolean isNullOrEmpty(Object object) {
        return object == null || "".equals(object);
    }
}
