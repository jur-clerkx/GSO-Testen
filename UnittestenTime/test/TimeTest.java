/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.DayInWeek;
import fontys.time.Time;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.security.util.Debug;

/**
 *
 * @author Nick van der Mullen
 */
public class TimeTest {

    Time testTime1;
    Time testTimeDifference;
    Time testTimePlus20;
    Time testFalseTime;
    Time testDayOfWeek;
    Time testTimePlus0;
    Time testTimePlus1Year;

    @Before
    public void Before() {
        testTime1 = new Time(2011, 11, 11, 11, 11);
        testTimeDifference = new Time(2012, 11, 11, 11, 11);
        testTimePlus20 = new Time(2011, 11, 11, 11, 31);
        testTimePlus0 = testTime1;
        testTimePlus1Year = testTimeDifference;
        testDayOfWeek = new Time(2015, 3, 17, 10, 10);

    }

    @Test
    public void testConstructor() {

        //Constructor
        try {
            Time ErrorTime = new Time(2000, 10, 10, 10, 10);
           
        } catch (IllegalArgumentException ex) {
            Assert.fail();
        }

        try {
            Time ErrorTime = new Time(2000, 100, 10, 10, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 100, 10, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 10, 100, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 10, 10, 100);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 10, 10, 100);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, -10, 10, 10, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, -10, 10, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 10, -10, 10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }

        try {
            Time ErrorTime = new Time(2000, 10, 10, 10, -10);
            Assert.fail("Invalid Value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Constructor test Passed");
        }
    }

    @Test
    public void testGetters() {
        /**
         *
         * @return the concerning year of this time
         */
        Assert.assertEquals("Year is incorrect", 2011, testTime1.getYear());

        /**
         *
         * @return the number of the month of this time (1..12)
         */
        Assert.assertEquals("Month is incorrect", 11, testTime1.getMonth());
        try {
            testFalseTime = new Time(2011, 40, 40, 40, 40);
            Assert.assertEquals("Month in invalid value", 0, testFalseTime.getMonth());
            Assert.fail("Month in invalid value");
        } catch (IllegalArgumentException ex) {
            System.out.println("Getter successfull");
        }
        /**
         *
         * @return the number of the day in the month of this time (1..31)
         */
        Assert.assertEquals("Day is incorrect", 11, testTime1.getDay());
        try {
            testFalseTime = new Time(2011, 40, 40, 40, 40);
            Assert.assertEquals("Day in invalid value", 0, testFalseTime.getDay());
            Assert.fail("Day in invalid value");
        } catch (IllegalArgumentException ex) {

        }

        /**
         *
         * @return the number of hours in a day of this time (0..23)
         */
        Assert.assertEquals("Day is incorrect", 11, testTime1.getHours());
        try {
            testFalseTime = new Time(2011, 40, 40, 40, 40);
            Assert.assertEquals("Day in invalid value", 0, testFalseTime.getHours());
            Assert.fail("Day in invalid value");
        } catch (IllegalArgumentException ex) {

        }

        /**
         *
         * @return the number of minutes in a hour of this time (0..59)
         */
        Assert.assertEquals("Day is incorrect", 11, testTime1.getMinutes());
        try {
            testFalseTime = new Time(2011, 40, 40, 40, 40);
            Assert.assertEquals("Day in invalid value", 0, testFalseTime.getMinutes());
            Assert.fail("Day in invalid value");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void testDayInWeek() {
        /**
         *
         * @return the concerning day in the week of this time
         */
        Assert.assertEquals("Day of the week is Incorrect", DayInWeek.TUE, testDayOfWeek.getDayInWeek());

    }

    @Test
    public void testAddMin() {
        /**
         *
         * @param minutes (a negative value is allowed)
         * @return this time plus minutes
         */
        testTime1.plus(20);
        Assert.assertEquals("Time isn't added correctly", 0, testTimePlus20.compareTo(testTime1));

        testTime1.plus(-20);
        Assert.assertEquals("Time isn't added correctly", 0, testTimePlus0.compareTo(testTime1));

        testTime1.plus(0);
        Assert.assertEquals("Time isn't added correctly", 0, testTime1.compareTo(testTimePlus0));

        testTime1.plus(525600);
        Assert.assertEquals("Time isn't added correctly", 0, testTime1.compareTo(testTimePlus1Year));
    }

    @Test
    public void TestDifference() {
        /**
         *
         * @param time
         * @return the difference between this time and [time] expressed in
         * minutes
         */
        Assert.assertEquals("Incorrect difference", 20, testTime1.difference(testTimePlus20));
        Assert.assertEquals("Incorrect difference", 20, testTimePlus20.difference(testTime1));
    }
}
