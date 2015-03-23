/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 * Specificatie en code geschreven door Nick, verbeterd door Jur
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
        GregorianCalendar n = new GregorianCalendar();
        GregorianCalendar c = new GregorianCalendar(timeSpan.getBeginTime().getYear(),timeSpan.getBeginTime().getMonth(),timeSpan.getBeginTime().getDay(),timeSpan.getBeginTime().getHours(),timeSpan.getBeginTime().getMinutes());
        if(subject != null && !subject.isEmpty() && c.after(n))
        {
        this.subject = subject.trim();
        this.timeSpan = timeSpan;
        } else {
            throw new IllegalArgumentException("Subject can't be null and begintime can't be in the past!");
        }
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
        boolean check = false;
        if(c != null && !this.invitees.contains(c))
        {
            this.invitees.add(c);
            check = true;
        }
        return check;
    }
    
    /**
     * Removes a Contact from this Appointment
     * @param c as Contact.
     */
    public void removeContact(Contact c)
    {
        if(c!= null && this.invitees.contains(c))
        {
            invitees.remove(c);
        }
    }
    
    @Override
    public ITime getBeginTime() {
        return this.timeSpan.getBeginTime();
    }

    @Override
    public ITime getEndTime() {
        return this.timeSpan.getEndTime();
    }

    @Override
    public int length() {
        return this.timeSpan.length();
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        this.timeSpan.setBeginTime(beginTime);
    }

    @Override
    public void setEndTime(ITime endTime) {
        this.timeSpan.setEndTime(endTime);
    }

    @Override
    public void move(int minutes) {
        this.timeSpan.move(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        this.timeSpan.changeLengthWith(minutes);
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return this.timeSpan.isPartOf(timeSpan);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
       return this.timeSpan.unionWith(timeSpan);
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
        return this.timeSpan.intersectionWith(timeSpan);
    }
    
}
