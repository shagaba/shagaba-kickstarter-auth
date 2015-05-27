package com.shagaba.kickstarter.core.security.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordMeterUtils {
    private static final int PASSWORD_LENGTH_MIN = 8; 
    
    // http://www.passwordmeter.com/ 
     
    public enum Complexity {VERY_WEAK, WEAK, GOOD, STRONG, VERY_STRONG} 
    public enum CharTypeEnum {ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE, ALPHABETIC, NUMERIC, SYMBOL} 
    
    
    private static final int LENGTH_BONUS = 4; 
    private static final int CASE_BONUS = 2; 
    private static final int NUMERIC_BONUS = 4; 
    private static final int SYMBOL_BONUS = 6; 
    private static final int REQUIREMENTS_BONUS = 2; 
    private static final int REPEAT_BONUS = -2; 
    private static final int SEQUENTIAL_BONUS = -3;
    
    /**
     * @param password
     * @return
     */
    public static Complexity getComplexity(String password) {
    	int score = getScore(password); 
   		return getComplexity(score);
    }
 
    /**
     * @param score
     * @return
     */
    public static Complexity getComplexity(int score) {
    	if (score < 20) 
    		return Complexity.VERY_WEAK;
    	if (score >= 20 && score < 40) 
    		return Complexity.WEAK;
    	if (score >= 40 && score < 60) 
    		return Complexity.GOOD;
    	if (score >= 60 && score < 80) 
    		return Complexity.STRONG;
   		return Complexity.VERY_STRONG;
    }
 
    /**
     * @param password
     * @return
     */
    public static int getScore(String password) { 
        if (password == null || password.isEmpty() || password.trim().isEmpty()) { 
            throw new IllegalArgumentException("no Password resource"); 
        } 

        String trimmedPassword = password.trim(); 
        int score = 0; 
 
        // Additions 
        score += scoreNumberOfCharacters(trimmedPassword); 
        score += scoreUpperCaseLetters(trimmedPassword); 
        score += scoreLowerCaseLetter(trimmedPassword); 
        score += scoreNumbers(trimmedPassword); 
        score += scoreSymbols(trimmedPassword); 
        score += scoreRequirements(trimmedPassword); 
 
        // Deductions 
        score += scoreLettersOnly(trimmedPassword); 
        score += scoreNumbersOnly(trimmedPassword); 
        score += scoreRepeatCharacters(trimmedPassword); 
        score += scoreConsecutiveUppercaseLetters(trimmedPassword); 
        score += scoreConsecutiveLowercaseLetters(trimmedPassword); 
        score += scoreConsecutiveNumbers(trimmedPassword); 
        score += scoreSequentialLetters(trimmedPassword); 
        score += scoreSequentialNumbers(trimmedPassword); 
        score += scoreSequentialSymbols(trimmedPassword); 
         
        if (score > 100) { 
            score = 100; 
        } 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreNumberOfCharacters(String password) { 
        // +(n*4) 
        int count = password.length(); 
        int score = count * LENGTH_BONUS; 
        System.out.println(String.format("Number of Characters (n*4) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreUpperCaseLetters(String password) { 
        // +((len-n)*2) 
        int count = regexCount(password, "[A-Z]"); 
        int score = count == 0 ? 0 : (password.length() - count) * CASE_BONUS; 
        System.out.println(String.format("Uppercase Letters +((len-n)*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreLowerCaseLetter(String password) { 
        // +((len-n)*2) 
        int count = regexCount(password, "[a-z]"); 
        int score = count == 0 ? 0 : (password.length() - count) * CASE_BONUS; 
        System.out.println(String.format("Lower Letters +((len-n)*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreNumbers(String password) { 
        // +(n*4) 
        int count = regexCount(password, "[0-9]"); 
        int countOthers = regexCount(password, "[^0-9]"); 
        int score = countOthers == 0 ? 0 : count * NUMERIC_BONUS; 
        System.out.println(String.format("Numbers +(n*4) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreSymbols(String password) { 
        // +(n*6) 
        int count = regexCount(password, "[^a-zA-Z0-9 ]"); 
        int score = count * SYMBOL_BONUS; 
        System.out.println(String.format("Symbols +(n*6) : Count %d, Score %d", count, score)); 
        return score; 
    } 
     
    /** 
     * Minimum 8 characters in length AND Contains 3/4 of the following items: 
     * Uppercase Letters, Lowercase Letters, Numbers, Symbols 
     *  
     * @param password 
     * @return 
     */ 
    public static int scoreRequirements(String password) { 
        // +(req*2) 
        int count = 0; 
        int score = 0; 
        // length >= 8 
        if (password.length() >= PASSWORD_LENGTH_MIN) { 
            ++count; 
            // upper case 
            if (regexCount(password, "[A-Z]") > 0) 
                ++count; 
            // lower case 
            if (regexCount(password, "[a-z]") > 0) 
                ++count; 
            // numbers 
            if (regexCount(password, "[0-9]") > 0) 
                ++count; 
            // symbols 
            if (regexCount(password, "[^a-zA-Z0-9 ]") > 0) 
                ++count; 
            score = count >= 3 ? count * REQUIREMENTS_BONUS : 0; 
        } 
        System.out.println(String.format("Requirements (n*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreLettersOnly(String password) { 
        // -(n) 
        int count = regexCount(password, "[^a-zA-Z]"); 
        int score = count == 0 ? (-1 * password.length()) : 0; 
        System.out.println(String.format("Letters Only -(n) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreNumbersOnly(String password) { 
        // -(n) 
        int count = regexCount(password, "[^0-9]"); 
        int score = count == 0 ? (-1 * password.length()) : 0; 
        System.out.println(String.format("Numbers Only -(n) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreRepeatCharacters(String password) { 
        // -(n*2) 
        int count = countRepeatCharacters(password); 
        int score = count * REPEAT_BONUS; 
        System.out.println(String.format("Repeat Characters -(n*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreConsecutiveUppercaseLetters(String password) { 
        // -(n*2) 
        int count = countConsecutive(password, CharTypeEnum.ALPHABETIC_UPPER_CASE); 
        int score = count * REPEAT_BONUS; 
        System.out.println(String.format("Consecutive Uppercase Letters -(n*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreConsecutiveLowercaseLetters(String password) { 
        // -(n*2) 
        int count = countConsecutive(password, CharTypeEnum.ALPHABETIC_LOWER_CASE); 
        int score = count * REPEAT_BONUS; 
        System.out.println(String.format("Consecutive Lowercase Letters -(n*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreConsecutiveNumbers(String password) { 
        // -(n*2) 
        int count = countConsecutive(password, CharTypeEnum.NUMERIC); 
        int score = count * REPEAT_BONUS; 
        System.out.println(String.format("Consecutive Numbers -(n*2) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreSequentialLetters(String password) { 
        // -(n*2) 
        int count = countSequential(password, CharTypeEnum.ALPHABETIC); 
        int score = count * SEQUENTIAL_BONUS; 
        System.out.println(String.format("Sequential Letters -(n*3) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreSequentialNumbers(String password) { 
        // -(n*2) 
        int count = countSequential(password, CharTypeEnum.NUMERIC); 
        int score = count * SEQUENTIAL_BONUS; 
        System.out.println(String.format("Sequential Numbers -(n*3) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int scoreSequentialSymbols(String password) { 
        // -(n*2) 
        int count = countSequential(password, CharTypeEnum.SYMBOL); 
        int score = count * SEQUENTIAL_BONUS; 
        System.out.println(String.format("Sequential Symbols -(n*3) : Count %d, Score %d", count, score)); 
        return score; 
    } 
 
    /** 
     * @param password 
     * @param pattern 
     * @return 
     */ 
    public static int regexCount(String password, String pattern) { 
        int count = 0; 
        Pattern compiledPattern = Pattern.compile(pattern); 
        Matcher matcher = compiledPattern.matcher(password); 
        while (matcher.find()) { 
            for (int i = 0; i < matcher.groupCount() + 1; ++i) { 
                ++count; 
            } 
        } 
        return count; 
    } 
 
    /** 
     * @param password 
     * @return 
     */ 
    public static int countRepeatCharacters(String password) { 
        int count = 0; 
        for (int i = 0; i < password.length() - 1; ++i) { 
            if (password.charAt(i) == password.charAt(i + 1)) 
                ++count; 
        } 
        return count; 
    } 
 
    /** 
     * @param password 
     * @param charTypeEnum 
     * @return 
     */ 
    public static int countConsecutive(String password, CharTypeEnum charTypeEnum) { 
        int count = 0; 
        for (int i = 0; i < password.length() - 1; ++i) { 
            switch (charTypeEnum) { 
            case ALPHABETIC: 
                if (Character.isLetter(password.charAt(i)) && Character.isLetter(password.charAt(i + 1))) 
                    ++count; 
                break; 
 
            case ALPHABETIC_LOWER_CASE: 
                if (Character.isLowerCase(password.charAt(i)) && Character.isLowerCase(password.charAt(i + 1))) 
                    ++count; 
                break; 
 
            case ALPHABETIC_UPPER_CASE: 
                if (Character.isUpperCase(password.charAt(i)) && Character.isUpperCase(password.charAt(i + 1))) 
                    ++count; 
                break; 
 
            case NUMERIC: 
                if (Character.isDigit(password.charAt(i)) && Character.isDigit(password.charAt(i + 1))) 
                    ++count; 
                break; 
 
            case SYMBOL: 
                if (!Character.isLetter(password.charAt(i)) && !Character.isLetter(password.charAt(i)) && 
                        !Character.isDigit(password.charAt(i + 1)) && !Character.isDigit(password.charAt(i + 1))) 
                    ++count; 
                break; 
 
            default: 
                break; 
            } 
        } 
        return count; 
    } 
 
 
    /** 
     * @param password 
     * @param charTypeEnum 
     * @return 
     */ 
    public static int countSequential(String password, CharTypeEnum charTypeEnum) { 
        int count = 0; 
        String tstPassword = password; 
        if (charTypeEnum == CharTypeEnum.ALPHABETIC) { 
            tstPassword = password.toLowerCase(); 
        } 
        for (int i = 0; i < password.length() - 2; ++i) { 
            boolean isApproved = false; 
            switch (charTypeEnum) { 
            case ALPHABETIC: 
                isApproved = Character.isLetter(tstPassword.charAt(i)) && Character.isLetter(tstPassword.charAt(i + 2)); 
                break; 
 
            case ALPHABETIC_LOWER_CASE: 
                isApproved = Character.isLowerCase(tstPassword.charAt(i)) && Character.isLowerCase(tstPassword.charAt(i + 2)); 
                break; 
 
            case ALPHABETIC_UPPER_CASE: 
                isApproved = Character.isUpperCase(tstPassword.charAt(i)) && Character.isUpperCase(tstPassword.charAt(i + 2)); 
                break; 
 
            case NUMERIC: 
                isApproved = Character.isDigit(tstPassword.charAt(i)) && Character.isDigit(tstPassword.charAt(i + 2)); 
                break; 
 
            case SYMBOL: 
                isApproved = !Character.isLetter(tstPassword.charAt(i)) && !Character.isDigit(tstPassword.charAt(i)) &&  
                        !Character.isLetter(tstPassword.charAt(i + 2)) && !Character.isDigit(tstPassword.charAt(i + 2)); 
                break; 
            } 
             
            if (isApproved) { 
                int seq1 = Character.getNumericValue(tstPassword.charAt(i)) - Character.getNumericValue(tstPassword.charAt(i + 1)); 
                int seq2 = Character.getNumericValue(tstPassword.charAt(i + 1)) - Character.getNumericValue(tstPassword.charAt(i + 2)); 
                if (Math.abs(seq1) == 1 && seq1 == seq2) 
                    ++count; 
            } 
        } 
        return count; 
    } 
}
