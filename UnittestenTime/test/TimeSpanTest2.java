/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fontys.time.*;

/**
 *
 * Verbetering geschreven door Nick
 */
public class TimeSpanTest2 {

    @Before
    public void setUp() {

    }

    @After
    public void breakDown() {

    }

    @Test
    public void TimeSpan2Test() {
        /**
         * @param bt must be earlier than et
         * @param et
         */
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 16, 12, 15);
        //Test wrong value, where et is earlier than bt.
        try {
            TimeSpan2 timeSpan2 = new TimeSpan2(t1, -20);
            fail("End time of timespan may not be earlier than begin time!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        //Test wrong value, where et and bt are the same.
        try {
            TimeSpan2 timeSpan2 = new TimeSpan2(t1, 0);
            fail("Begin and end time of time span may not be the same!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        //Test correct values
        try {
            TimeSpan2 timeSpan2 = new TimeSpan2(t2, 20);
        } catch (IllegalArgumentException ex) {
            fail("Constructor failed with correct values!");
        }
    }

    @Test
    public void getBeginTimeTest() {
        /**
         *
         * @return the begin time of this time span
         */
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 16, 12, 15);
        TimeSpan2 ts = new TimeSpan2(t2, 1255);
        assertEquals("getBeginTime returns wrong begintime!", ts.getBeginTime(), t2);
    }

    @Test
    public void getEndTimeTest() {
        /**
         *
         * @return the end time of this time span
         */
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);
        assertEquals("getEndTime returns wrong endtime!", ts.getEndTime().compareTo(t2),0);
    }

    @Test
    public void lengthTest() {
        /**
         *
         * @return the length of this time span expressed in minutes (always
         * positive)
         */
        //Test normal values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);
        assertEquals("Lengt returned is wrong!", ts.length(), 5);
        assertTrue("Lenght is not positive!", ts.length() > 0);

        //Test big values
        t1 = new Time(2015, 3, 17, 9, 10);
        t2 = new Time(2115, 3, 17, 9, 10);
        ts = new TimeSpan2(t1, 52560000);
        assertTrue("Length of huge timespan is not positive", ts.length() > 0);
    }

    @Test
    public void setBeginTimeTest() {
        //@param endTime must be later than the current begin time
        //of this time span
        //Setup values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 4);

        //Test wrong values
        try {
            ts.setBeginTime(new Time(2015, 3, 17, 9, 15));
            fail("Begin time must not be able to be set to a time after the end time!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        //Test same time values
        try {
            ts.setBeginTime(t2);
            fail("Begin time must not be able to be set to the same time as the end time");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        //Test normal values
        try {
            ts.setBeginTime(new Time(2015, 2, 12, 15, 35));
        } catch (IllegalArgumentException ex) {
            fail("Begin time can't be set to a normal value!");
        }
        //beginTime will be the new begin time of this time span
        //Check if value has been set
        assertEquals("Begintime has not been set!", ts.getBeginTime().compareTo(new Time(2015, 2, 12, 15, 35)), 0);
    }

    @Test
    public void setEndTimeTest() {
        //@param endTime must be later than the current begin time
        //of this time span
        //Setup values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan ts = new TimeSpan(t1, t2);
        Time t = new Time(2015, 8, 7, 12, 11);
        //Test wrong values
        try {
            ts.setEndTime(new Time(2014, 3, 23, 12, 00));
            fail("End time must not be able to be set to a time before the begin time!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        //Test same time values
        try {
            ts.setEndTime(t1);
            fail("End time must not be able to be set to the same time as the begin time");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        //Test normal values
        try {

            ts.setEndTime(t);
        } catch (IllegalArgumentException ex) {
            fail("End time can't be set to a normal value!");
        }

        //endTime will be the new end time of this time span
        //Check if value has been set
        assertEquals("New end time has not been set!", ts.getEndTime().compareTo(t), 0);
    }

    @Test
    public void moveTest() {
        //Setup values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);

        //the end time of this time span will be moved up with [minutes] minutes
        //@param minutes (a negative value is allowed)
        //Test postive value
        ts.move(5);
        assertEquals("Begin time is not moved correctly!", ts.getBeginTime().compareTo(new Time(2015, 3, 17, 9, 15)), 0);
        assertEquals("End time is not moved correctly!", ts.getEndTime().compareTo(new Time(2015, 3, 17, 9, 20)), 0);
        //Test zero value
        ts.move(0);
        assertEquals("Begin time is not moved correctly!", ts.getBeginTime().compareTo(new Time(2015, 3, 17, 9, 15)), 0);
        assertEquals("End time is not moved correctly!", ts.getEndTime().compareTo(new Time(2015, 3, 17, 9, 20)), 0);
        //Test negative value
        ts.move(-5);
        assertEquals("Begin time is not moved correctly!", ts.getBeginTime().compareTo(new Time(2015, 3, 17, 9, 10)), 0);
        assertEquals("End time is not moved correctly!", ts.getEndTime().compareTo(new Time(2015, 3, 17, 9, 15)), 0);
    }

    @Test
    public void changeLengthWithTest() {
        //Setup values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);

        //@param minutes  minutes + length of this period must be positive
        //Test normal value
        try {
            ts.changeLengthWith(10);
        } catch (IllegalArgumentException ex) {
            fail(ex.getMessage());
        }
        //Test zero value
        try {
            ts.changeLengthWith(0);
            fail("Can't change lenght with 0!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        //Test negative value
        try {
            ts.changeLengthWith(-6);
            fail("Can't change length with negative value!");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        //Reset values
        t1 = new Time(2015, 3, 17, 9, 10);
        t2 = new Time(2015, 3, 17, 9, 15);
        ts = new TimeSpan2(t1, 5);
        //the end time of this time span will be moved up with [minutes] minutes
        ts.changeLengthWith(5);
        assertEquals("End time has not been changed correctly!", 0, ts.getEndTime().compareTo(new Time(2015, 3, 17, 9, 15 + 5)));
    }

    @Test
    public void isPartOfTest() {
        //@return true if all moments within this time span are included within [timeSpan], 
        // otherwise false
        //Set values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);
        //Test normal case
        assertTrue("Timespan is within bounds!", ts.isPartOf(new TimeSpan2(new Time(2015, 3, 17, 9, 8),20)));
        //Test with same dates
        assertTrue("Timespans are the same!", ts.isPartOf(ts));
        //Test with end time out of timespan
        assertFalse("End time is out of bounds!", ts.isPartOf(new TimeSpan2(new Time(2015, 3, 17, 9, 8), 6)));
        //Test with begin time out of bounds
        assertFalse("Begin time is out of bounds!", ts.isPartOf(new TimeSpan2(new Time(2015, 3, 17, 9, 11), 9)));
        //Test with full timespan out of bounds
        assertFalse("Timespan is fully out of bounds!", ts.isPartOf(new TimeSpan2(new Time(2015, 3, 18, 10, 12), 1388)));
    }

    @Test
    public void unionWithTest() {
        //Set values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);

        //@return if this time span and [timeSpan] are consecutive or possess a
        //common intersection, then the smallest time span ts will be returned, 
        //whereby this time span and [timeSpan] are part of ts, 
        //otherwise null will be returned 
        //Test with no union
        assertNull("There is no union!", ts.unionWith(new TimeSpan2(new Time(2015, 4, 18, 13, 11), 3296)));
        //Test with same timespan

        ITimeSpan2 t = ts.unionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 10), 5));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(ts.getEndTime()), 0);
        //Test with end different

        t = ts.unionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 10), 3));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(new Time(2015, 3, 17, 9, 13)), 0);

        t = ts.unionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 12), 3));
        //Test with begin different
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(new Time(2015, 3, 17, 9, 12)), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(ts.getEndTime()), 0);

        t = ts.unionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 12),6));
        //Test with both different
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(new Time(2015, 3, 17, 9, 12)), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(new Time(2015, 3, 17, 9, 15)), 0);

    }

    @Test
    public void intersectionWithTest() {
        //Set values
        Time t1 = new Time(2015, 3, 17, 9, 10);
        Time t2 = new Time(2015, 3, 17, 9, 15);
        TimeSpan2 ts = new TimeSpan2(t1, 5);
        ITimeSpan2 t;
        //@return the largest time span which is part of this time span 
        //and [timeSpan] will be returned; if the intersection is empty null will 
        //be returned
        //Test with no intersection
        assertNull("There is no intersection!", ts.intersectionWith(new TimeSpan2(new Time(2015, 4, 18, 13, 11), 3296)));
        //Test with same timespan
        t = ts.intersectionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 10), 5));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(ts.getEndTime()), 0);
        //Test with end different

        t = ts.intersectionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 10),3));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(ts.getEndTime()), 0);

        //Test with begin different
        t = ts.intersectionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 12),3));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(ts.getEndTime()), 0);
        //Test with both different
        t = ts.intersectionWith(new TimeSpan2(new Time(2015, 3, 17, 9, 12),6));
        assertEquals("The same timespan should be returned!", t.getBeginTime().compareTo(ts.getBeginTime()), 0);
        assertEquals("The same timespan should be returned!", t.getEndTime().compareTo(new Time(2015, 3, 17, 9, 18)), 0);
    }
}
