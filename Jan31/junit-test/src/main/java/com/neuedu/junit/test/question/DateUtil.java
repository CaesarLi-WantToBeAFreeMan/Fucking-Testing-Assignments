package com.neuedu.junit.test.question;

/**
 * 计算某年某月有多少天
 */
public class DateUtil {

    public static String getDays(int year, int month) {
        int days;
        if (year > 0) {
            //fix_01
            if (month >= 1 && month <= 12) {
                //fix_02
                if (month == 1 || month == 3 || month == 5 || month == 7 ||  month == 8 || month == 10 || month == 12) {
                    days = 31;
                } else if (month == 2) {
                    if (isLeapYear(year)) {
                        days = 29;
                    } else {
                        days = 28;
                    }
                } else {
                    days = 30;
                }
                return(year + "年" + month + "月份的天数是" + days + "天");
            } else {
                return("输入的月份不正确");
            }
        } else {
            return("输入的年份不正确");
        }
    }

    private static boolean isLeapYear(int year) {
        //fix_03
        if (!isCenturyLeapYear(year) && isNormalLeapYear(year)) {
            return true;
        }

        return false;
    }

    private static boolean isCenturyLeapYear(int year) {
        //fix_04
        return year % 400 != 0 && year % 100 == 0;
    }

    private static boolean isNormalLeapYear(int year) {
        //fix_05
        return year % 4 == 0;
    }

}
