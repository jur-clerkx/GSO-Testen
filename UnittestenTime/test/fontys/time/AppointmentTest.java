package fontys.time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Methodes die onder de interface vallen worden overgeslagen, aangezien deze al
 * getest zijn
 *
 * @author Jur
 */
public class AppointmentTest {

    @Test
    public void ConstructorTest() {
        //normal cases
        try {
            new Appointment("Vergadering met Nick",
                    new TimeSpan(new Time(2016, 3, 24, 10, 25),
                            new Time(2016, 3, 24, 10, 45)));
        } catch (IllegalArgumentException ex) {
            fail("Appiontment should be created!");
        }
        try {
            new Appointment("Les gso",
                    new TimeSpan(new Time(2016, 3, 24, 9, 45),
                            new Time(2016, 3, 24, 10, 45)));
        } catch (IllegalArgumentException ex) {
            fail("Appiontment should be created!");
        }

        //@param subject as String, can't be empty
        try {
            new Appointment("",
                    new TimeSpan(new Time(2016, 3, 24, 9, 45),
                            new Time(2016, 3, 24, 10, 45)));
            fail("Appiontment shouldn't be created!");
        } catch (IllegalArgumentException ex) {
        }
        try {
            new Appointment(null,
                    new TimeSpan(new Time(2016, 3, 24, 9, 45),
                            new Time(2016, 3, 24, 10, 45)));
            fail("Appiontment shouldn't be created!");
        } catch (IllegalArgumentException ex) {
        }

        //@param timeSpan as ITimeSpan, can't be before time of creation.
        try {
            new Appointment("Deze afspraak is al bezig...",
                    new TimeSpan(new Time(2014, 3, 24, 9, 45),
                            new Time(2015, 3, 24, 10, 45)));

        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void getSubjectTest() {
        //Normal cases
        Appointment ap1 = new Appointment("Vergadering met Nick",
                new TimeSpan(new Time(2016, 3, 24, 10, 25),
                        new Time(2016, 3, 24, 10, 45)));
        Appointment ap2 = new Appointment("Gso les",
                new TimeSpan(new Time(2016, 3, 24, 10, 25),
                        new Time(2016, 3, 24, 10, 45)));
        assertEquals("Vergadering met Nick", ap1.getSubject());
        assertEquals("Gso les", ap2.getSubject());

        //Special cases
        ap1 = new Appointment("  test123",
                new TimeSpan(new Time(2016, 3, 24, 10, 25),
                        new Time(2016, 3, 24, 10, 45)));
        ap2 = new Appointment("  test222  ",
                new TimeSpan(new Time(2016, 3, 24, 10, 25),
                        new Time(2016, 3, 24, 10, 45)));
        assertEquals("test123", ap1.getSubject());
        assertEquals("test222", ap2.getSubject());
    }

    @Test
    public void getTimeSpan() {
        //@return timeSpan as ITimeSpan;
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        ITimeSpan t2 = new TimeSpan(new Time(2016, 3, 24, 11, 25),
                new Time(2016, 3, 24, 11, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        Appointment ap2 = new Appointment("Gso les", t2);

        assertEquals(t1, ap1.getTimeSpan());
        assertEquals(t2, ap2.getTimeSpan());
    }

    @Test
    public void addContactTest() {
        //@return boolean  true if c is added, false if already exists in the list or is null.
        //Create objects
        Contact henk = new Contact("Henk");
        Contact piet = new Contact("Piet");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        //Test normal cases
        assertTrue(ap1.addContact(henk));
        assertTrue(ap1.addContact(piet));
        //Test false values
        assertFalse(ap1.addContact(null));
        assertFalse(ap1.addContact(henk));
        assertFalse(ap1.addContact(piet));
    }

    @Test
    public void removeContactTest() {
        //Removes a Contact from this Appointment
        //@param c as Contact.
        //Create objects
        Contact henk = new Contact("Henk");
        Contact piet = new Contact("Piet");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);

        //Test normal cases
        ap1.addContact(henk);
        ap1.addContact(piet);
        ap1.removeContact(henk);
        assertEquals(piet, ap1.invitees().next());
    }
    
    @Test
    public void invitesTest(){
        //Create objects
        Contact henk = new Contact("Henk");
        Contact piet = new Contact("Piet");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        //test normal cases
        ap1.addContact(henk);
        ap1.addContact(piet);
        Iterator<Contact> i = ap1.invitees();
        assertEquals(henk.getName(), i.next().getName());
        assertEquals(piet.getName(), i.next().getName());
    }
}
