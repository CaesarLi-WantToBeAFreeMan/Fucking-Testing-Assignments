package com.neuedu.junit.test.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import io.qameta.allure.*;

/**
 * DateUtilTest
 */
@Feature("DateUtilTest")
public class DateUtilTest {
    @Story("test_ddt_getDays")
    @Step("year: {year}, month: {month}, expected: {expected}")
    @Description("Parameterized test for getDays method")
    @ParameterizedTest
    @CsvFileSource(resources = "/DateUtil_test_ddt_data.csv", numLinesToSkip = 1)
    public void test_ddt_getDays(int year, int month, String expected){
        Assertions.assertEquals(expected, DateUtil.getDays(year, month));
    }

    @Story("test_logic_getDays_1")
    @Step("year: {year}, month: {month}, expected: {expected}")
    @Description("code coverage of non-February for getDays method")
    @ParameterizedTest
    @CsvSource({
        "2026,1,2026年1月份的天数是31天",
        "2026,3,2026年3月份的天数是31天",
        "2026,4,2026年4月份的天数是30天",
        "2026,5,2026年5月份的天数是31天",
        "2026,6,2026年6月份的天数是30天",
        "2026,7,2026年7月份的天数是31天",
        "2026,8,2026年8月份的天数是31天",
        "2026,9,2026年9月份的天数是30天",
        "2026,10,2026年10月份的天数是31天",
        "2026,11,2026年11月份的天数是30天",
        "2026,12,2026年12月份的天数是31天",
    })
    public void test_logic_getDays_1(int year, int month, String expected){
        Assertions.assertEquals(expected, DateUtil.getDays(year, month));
    }

    @Story("test_logic_getDays_2")
    @Step("year: {year}, month: {month}, expected: {expected}")
    @Description("code coverage of February for getDays method")
    @ParameterizedTest
    @CsvSource({
        "2026,2,2026年2月份的天数是28天",
        "2004,2,2004年2月份的天数是29天",
        "2000,2,2000年2月份的天数是29天",
        "1900,2,1900年2月份的天数是28天"
    })
    public void test_logic_getDays_2(int year, int month, String expected){
        Assertions.assertEquals(expected, DateUtil.getDays(year, month));
    }

    @Story("test_errt_getDays_1")
    @Step("year: {year}, month: {month}, expected: {expected}")
    @Description("wrong year handling for getDays method")
    @ParameterizedTest
    @CsvSource({
        "-1,1,输入的年份不正确",
        "0,1,输入的年份不正确"
    })
    public void test_errt_getDays_1(int year, int month, String expected){
        Assertions.assertEquals(expected, DateUtil.getDays(year, month));
    }

    @Story("test_errt_getDays_2")
    @Step("year: {year}, month: {month}, expected: {expected}")
    @Description("wrong month handling for getDays method")
    @ParameterizedTest
    @CsvSource({
        "2026,-1,输入的月份不正确",
        "2026,0,输入的月份不正确",
        "2026,13,输入的月份不正确"
    })
    public void test_errt_getDays_2(int year, int month, String expected){
        Assertions.assertEquals(expected, DateUtil.getDays(year, month));
    }
}
