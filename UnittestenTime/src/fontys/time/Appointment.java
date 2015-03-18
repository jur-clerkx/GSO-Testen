/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Mnesymne
 */
public class Appointment implements ITimeSpan{

    public String subject;
    public ArrayList<Contact> invitees;
    public ITimeSpan timeSpan;

    /**
     * Constructor
     * @param subject as String, can't be empty
     * @param timeSpan as ITimeSpan, can't be before time of creation.
     */
    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        this.invitees = new ArrayList<>();
    }

    /**
     * Getter of subject
     * @return subject as String.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter of TImeSpan
     * @return timeSpan as ITimeSpan;
     */
    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }
    
    /**
     * Gives back the next object of the invitees
     * @return a Contact that is the next one in the invitees
     */
    public Iterator<Contact> invitees()
    {
        return invitees.iterator();
    }

    /**
     * Adds a Contact to this Appointment
     * @param c as Contact, can't be null and can't exist already.
     * @return boolean  true if c is added, false if already exists in the list or is null.
     */
    public boolean addContact(Contact c)
    {
        return true;
    }
    
    /**
     * Adds a Contact to this Appointment
     * @param c as Contact, can't be null.
     */
    public void removeContact(Contact c)
    {
        
    }
    
    @Override
    public ITime getBeginTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITime getEndTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndTime(ITime endTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move(int minutes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeLengthWith(int minutes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
