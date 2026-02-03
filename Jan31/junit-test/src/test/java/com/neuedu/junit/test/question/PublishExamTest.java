package com.neuedu.junit.test.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import io.qameta.allure.*;

/**
 * PublishExamTest
 */
@Feature("PublishExamTest")
public class PublishExamTest {
    private final PublishExam publishExam = new PublishExam();

    @Story("test_ddt_process")
    @Step("name: {name}, startDateStr: {startDateStr}, endDateStr: {endDateStr}, expected: {expected}")
    @Description("parametrized test for process method")
    @ParameterizedTest
    @CsvFileSource(resources = "/PublishExam_test_ddt_data.csv", numLinesToSkip = 1)
    public void test_ddt_process(String name, String startDateStr, String endDateStr, String expected){
        Assertions.assertEquals(expected, publishExam.process(name, startDateStr, endDateStr));
    }

    @Story("test_logic_process_1")
    @Step("name: {name}, startDateStr: {startDateStr}, endDateStr: {endDateStr}, expected: {expected}")
    @Description("first code coverage test of process method")
    @ParameterizedTest
    @CsvSource({
        "Caesar,2026-02-01,2026-02-03,OK"
    })
    public void test_logic_process_1(String name, String startDateStr, String endDateStr, String expected){
        Assertions.assertEquals(expected, publishExam.process(name, startDateStr, endDateStr));
    }

    @Story("test_logic_process_2")
    @Step("name: {name}, startDateStr: {startDateStr}, endDateStr: {endDateStr}, expected: {expected}")
    @Description("second code coverage test of process method")
    @ParameterizedTest
    @CsvSource({
        "Caesar ,2026-02-01,2026-02-03,OK"
    })
    public void test_logic_process_2(String name, String startDateStr, String endDateStr, String expected){
        Assertions.assertEquals(expected, publishExam.process(name, startDateStr, endDateStr));
    }

    @Story("test_errt_process_1")
    @Step("name: {name}, startDateStr: {startDateStr}, endDateStr: {endDateStr}, expected: {expected}")
    @Description("wrong name handling for process method")
    @ParameterizedTest
    @CsvSource({
        ",2026-02-01,2026-02-03,参数不符合要求",
        "qwertyuiopasdfghjklzxcvbnm,2026-02-01,2026-02-03,考核名称不符合要求"
    })
    public void test_errt_process_1(String name, String startDateStr, String endDateStr, String expected){
        Assertions.assertEquals(expected, publishExam.process(name, startDateStr, endDateStr));
    }

    @Story("test_errt_process_2")
    @Step("name: {name}, startDateStr: {startDateStr}, endDateStr: {endDateStr}, expected: {expected}")
    @Description("wrong date handling for process method")
    @ParameterizedTest
    @CsvSource({
        "Caesar,,2026-02-01,参数不符合要求",
        "Caesar,20,2026-02-01,参数不符合要求",
        "Caesar,2026-02-03,2026-02-01,发布结束日期不符合要求"
    })
    public void test_errt_process_2(String name, String startDateStr, String endDateStr, String expected){
        Assertions.assertEquals(expected, publishExam.process(name, startDateStr, endDateStr));
    }
}
