
import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jur
 */
public class ContactTest {

    @Test
    public void ConstructorTest() {
        /**
         * Constructor of this class
         *
         * @param name as String (added by jur) Must not be empty or null!
         * (removed by jur) First letter of the name is a capital the rest is
         * lowercase
         */
        //Normal cases
        try {
            new Contact("Henk");
        } catch (Exception e) {
            fail("Contact should be created!");
        }
        try {
            new Contact("Piet");
        } catch (Exception e) {
            fail("Contact should be created!");
        }
        //Try bad values
        try {
            new Contact(null);
            fail("Name should not be able to be null!");
        } catch (IllegalArgumentException e) {
        }
        try {
            new Contact("");
            fail("Name should not be able to be empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getNameTest() {
        /**
         * Getter of the variable name
         *
         * @return name as string (added by jur) First letter of the name is a
         * capital the rest is lowercase
         */
        //Normal cases
        Contact henk = new Contact("Henk");
        Contact piet = new Contact("Piet");
        assertEquals("Henk", henk.getName());
        assertEquals("Piet", piet.getName());

        //Test stranger cases
        henk = new Contact(" henk");
        piet = new Contact("piet ");
        assertEquals("Henk", henk.getName());
        assertEquals("Piet", piet.getName());

        //Test extreme cases
        henk = new Contact(" heNK      ");
        piet = new Contact("     PiET ");
        assertEquals("Henk", henk.getName());
        assertEquals("Piet", piet.getName());
    }

    @Test
    public void addAppointment() {
        //@return boolean  true if c is added, false if already exists in the list or is null.
        //Test normal values
        Contact henk = new Contact("Henk");
        Contact piet = new Contact("Piet");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        assertTrue(henk.addAppointment(ap1));
        assertTrue(piet.addAppointment(ap1));
        assertFalse(henk.addAppointment(ap1));
        assertFalse(piet.addAppointment(ap1));
        assertFalse(henk.addAppointment(null));
        assertFalse(henk.addAppointment(null));
    }

    @Test
    public void appointmentsTest() {
        //@return Appointment that is the next one in the Agenda
        Contact henk = new Contact("Henk");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        Appointment ap2 = new Appointment("GSO les", t1);
        henk.addAppointment(ap1);
        henk.addAppointment(ap2);
        Iterator<Appointment> i = henk.appointments();
        assertEquals(ap1, i.next());
        assertEquals(ap2, i.next());
    }
    
    @Test
    public void removeAppointmentTest() {
        Contact henk = new Contact("Henk");
        ITimeSpan t1 = new TimeSpan(new Time(2016, 3, 24, 10, 25),
                new Time(2016, 3, 24, 10, 45));
        Appointment ap1 = new Appointment("Vergadering met Nick", t1);
        Appointment ap2 = new Appointment("GSO les", t1);
        henk.addAppointment(ap1);
        henk.addAppointment(ap2);
        //Removes an appointment out of the list.
        henk.removeAppointment(ap1);
        assertEquals(ap2, henk.appointments().next());
        henk.addAppointment(ap1);
        henk.removeAppointment(ap2);
        assertEquals(ap1, henk.appointments().next());
    }
}
