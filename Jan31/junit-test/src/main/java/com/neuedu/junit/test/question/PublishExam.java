package com.neuedu.junit.test.question;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 发布考核信息时需要填写考核名称、发布时间。
 * 其中要求考核名称不可以为空或空字符，最多20个字符；
 * 发布开始日期不可以为空，时间的格式必须是yyyy-MM-dd；
 * 发布结束日期可以为空，但若不为空时，要求必须时间的格式必须是yyyy-MM-dd，而且不可以小于发布开始日期；
 * 如果符合以上条件的更新课件，提示“OK”，
 * 否则根据实际情况提示“**不符合要求”（**为考核名称或发布开始日期、发布结束日期）,退出。
 */
public class PublishExam {

    private static final int MAX_TAG_LENGTH = 20;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String process(String name, String startDateStr, String endDateStr) {
        try {
            // 考核名称不可以为空，最多20个字符
            //fix_01
            if (name.isEmpty() || name.length() > MAX_TAG_LENGTH) {
                return "考核名称不符合要求";
            }
            // 判断发布开始日期是否为空或格式不正确
            //fix_02
            if (startDateStr.isEmpty() || !isValidDate(startDateStr)) {
                return "发布开始日期不符合要求";
            }
            // 判断发布结束日期的格式和关系
            //fix_03
            if (endDateStr.isEmpty() || !isValidDate(endDateStr) || !isBefore(startDateStr,endDateStr)) {
                return "发布结束日期不符合要求";
            }
            //fix_04
        } catch (Exception e) {
            return "参数不符合要求";
        }

        // 符合要求
        return "OK";
    }

    // 判断日期格式是否正确
    private static boolean isValidDate(String date) {
        //fix_05
        LocalDate.parse(date, formatter);
        return true;
    }

    // 判断日期先后关系，startDate是否早于endDate
    private static boolean isBefore(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        //fix_06
        return start.isBefore(end);
    }

}
