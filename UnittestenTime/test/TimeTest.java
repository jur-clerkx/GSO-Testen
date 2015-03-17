/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.Time;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mnesymne
 */
public class TimeTest {

    @Before
    public void Before()
    {
        Time testTime1 = new Time(2011,11,11,11,11);
        Time testTimeDifference = new Time(2012,11,11,11,11);
        Time testTimePlus20 = new Time(2011,11,11,11,31);
        Time testFalseTime = new Time(2011,40,40,40,40);
       
    }
    
    
    @Test
    public void testGetters()
    {
    /**
     * 
     * @return the concerning year of this time
     */
    
     /**
     * 
     * @return the number of the month of this time (1..12)
     */
    
     /**
     * 
     * @return the number of the day in the month of this time (1..31)
     */
    
    /**
     * 
     * @return the number of hours in a day of this time (0..23)
     */
    
    /**
     * 
     * @return the number of minutes in a hour of this time (0..59)
     */
    
    /**
     * 
     * @return the concerning day in the week of this time
     */
    
     /**
     * 
     * @param minutes (a negative value is allowed)
     * @return  this time plus minutes
     */
    
    /**
     * 
     * @param time
     * @return the difference between this time and [time] expressed in minutes
     */
    }
}
