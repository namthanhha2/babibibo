/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.havm.babibibo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author sondm2@viettel.com.vn
 * @since Apr,12,2010
 * @version 1.0
 */
public class DateTimeUtils {

    public static final String FORMAT_YEAR_MONTH = "yyyyMM";
    public static final String FORMAT_YEAR_MONTH_DAY = "yyyyMMdd";
    public static final String FORMAT_YEAR_MONTH_SLASH = "yyyy/MM";
    public static final String FORMAT_YEAR_MONTH_DAY_SLASH = "yyyy/MM/dd";
    public static final String FORMAT_YEAR_MONTH_DAY_MINUS = "yyyy-MM-dd";
    public static final String FORMAT_YEAR_MONTH_DAY_SLASH_TIME = "yyyy/MM/dd HH:mm";
    public static final String FORMAT_YEAR_MONTH_DAY_DOT_TIME = "yyyy.MM.dd HH:mm";
    public static final String FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String FORMAT_HOUR_MINUTE = "HH:mm";
    public static final String FORMAT_YEAR_MONTH_DAY_TIME = "yyyy.M.d HH:mm";
    public static final String FORMAT_YEAR_MONTH_DAY_FULL_TIME = "yyyyMMddHHmmss";
    public static final String FORMAT_DAY_MONTH_YEAR_SLASH = "dd/MM/yyyy";
    public static final String FORMAT_YEAR = "yyyy";

    /**
     * private constructor
     */
    public DateTimeUtils() {
    }

    /**
     *
     * @param date to convert
     * @param pattern in converting
     * @return date
     */
    public static Date convertStringToTime(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    /**
     * Convert date to string with any format
     *
     * @author NgocTM
     * @param date
     * @param format date format
     * @return String date format string
     */
    public static String convertDateToString(Date date, String format) {
        String result = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }
// /**
//     * Ham nay dung chuyen doi 1 chuoi string ra dinh dang Date
//     * @param date
//     */
//    public static Date convertStringToDateTime(String date) throws Exception {
//        String pattern = "yyyy-MM-dd";
//        Date date2 = DateTimeUtils.convertStringToTime(date, pattern);
//        Calendar clf = Calendar.getInstance();
//        clf.setTime(date2);
//        clf.set(Calendar.HOUR_OF_DAY, 0);
//        clf.set(Calendar.SECOND, 0);
//        clf.set(Calendar.MINUTE, 0);
//        date2 = clf.getTime();
//        return date2;
//    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDate(String date) throws Exception {
        String pattern = "yyyy-MM-dd";
        return convertStringToTime(date, pattern);
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static String convertDateToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @return String
     * @throws Exception if error
     */
    public static String getSysdate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        return convertDateToString(calendar.getTime());
    }

    /**
     *
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param pattern to convert
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime(String pattern) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String strDay = "";
        switch (day) {
            case 1:
                strDay = "Sunday";
                break;
            case 2:
                strDay = "Monday";
                break;
            case 3:
                strDay = "Tuesday";
                break;
            case 4:
                strDay = "Wednesday";
                break;
            case 5:
                strDay = "Thursday";
                break;
            case 6:
                strDay = "Friday";
                break;
            case 7:
                strDay = "Saturday";
        }
        return strDay;
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDateTime(String date) throws Exception {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        return convertStringToTime(date, pattern);
    }

    /**
     *
     * @param date to convert
     * @return String
     */
    public static String convertDateTimeToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    /**
     *
     * @param utilDate to convert
     * @return date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static Date fromDateClockToDate(String dateStr) throws Exception {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            throw e;
        }

    }

    public static Date dateFromString(String dateStr) throws Exception {
        String[] dateParam = dateStr.split("/");
        int year;
        int month;
        int date;
        Date returnDate;
        if (dateParam.length == 3) {
            year = Integer.parseInt(dateParam[2]);
            month = Integer.parseInt(dateParam[1]);
            date = Integer.parseInt(dateParam[0]);
        } else if (dateParam.length == 2) {
            year = Integer.parseInt(dateParam[1]);
            month = Integer.parseInt(dateParam[0]);
            date = 1;
        } else {
            return null;
        }
        year = year - 1900;
        month = month - 1;
        returnDate = new Date(year, month, date);
        return returnDate;
    }

    /**
     * copy from http://forums.sun.com/thread.jspa?threadID=395202
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {

        int tempDifference;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        if (date1.compareTo(date2) < 0) {
            earlier.setTime(date1);
            later.setTime(date2);
        } else {
            earlier.setTime(date2);
            later.setTime(date1);
        }

        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
            tempDifference = later.get(Calendar.DAY_OF_YEAR)
                    - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }
        return difference;
    }

    public static Date getDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;

    }

    /**
     * Lay ve ngay cach ngay hien tai days ngay months thang
     *
     * @param months
     * @param days
     * @return
     */
    public static Date getAddDate(Date date, int months, int days) {

        Calendar cl = Calendar.getInstance();
        cl.setTime(date);

        cl.add(Calendar.MONTH, months);
        cl.add(Calendar.DATE, days);

        return cl.getTime();
    }

    public static Date addOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return c.getTime();
    }

    public static Date addDay(Date date, int i) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, i);
        }
        return c.getTime();
    }

    public static XMLGregorianCalendar dateToXML(Date date) throws DatatypeConfigurationException {
        if (date == null) {
            return null;
        }
        //System.out.println(date.toString());
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeZone(TimeZone.getTimeZone("GMT+07"));
        c.setTime(date);
        XMLGregorianCalendar dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        //System.out.println(dateXML.toString());
        return dateXML;
    }

    /**
     * convert date to calendar
     *
     * @param date
     * @return
     */
    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * convert calendar to date
     *
     * @param calendar
     * @return
     */
    public static Date CalendarToDate(Calendar calendar) {
        Date date = calendar.getTime();
        return date;
    }

    /**
     * là cuối tuần +1
     *
     * @param input
     * @return
     */
    public static Calendar checkWeekend(Calendar input) {
        Calendar output = input;
        while (output.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || output.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            output.add(Calendar.DATE, 1);
        }
        return output;
    }

    /**
     * la cuoi tuan
     *
     * @param input
     * @return
     */
    public static boolean checkIsWeekend(Calendar input) {
        boolean ck = false;
        if (input.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || input.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            ck = true;
        }
        return ck;
    }

    /**
     *
     * @param input
     * @return
     */
    public static Date getStartWeekByDate(Date input) {
        return new Date();
    }

    public static Date getAtLastDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year, month, day, 23, 59, 59);
        return cal.getTime();
    }

    public static Date getAfterDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public Date maxDayToCompare(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return DateTimeUtils.convertStringToDateTime(DateTimeUtils.convertDateTimeToString(date) + " 23:59:59");
    }

    public Date minDayToCompare(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return DateTimeUtils.convertStringToDateTime(DateTimeUtils.convertDateTimeToString(date) + " 00:00:00");
    }

    public static List splitDateTimeToString(Date date) {
        List lstDateStr = new ArrayList();
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        int day = today.get(Calendar.DATE);
        int month = today.get(Calendar.MONTH) + 1;
        int year = today.get(Calendar.YEAR);
        String strDay = day < 10 ? ("0" + day) : Integer.toString(day);
        String strMonth = month < 10 ? ("0" + month) : Integer.toString(month);
        lstDateStr.add(strDay);
        lstDateStr.add(strMonth);
        lstDateStr.add(Integer.toString(year));

        return lstDateStr;
    }
}
