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
 * Specificatie en code geschreven door Nick, verbeterd door Jur
 */
public class Contact {

    private final String name;
    private ArrayList<Appointment> myAgenda;

    /**
     * Constructor of this class
     *
     * @param name as String
     * (added by jur) Must not be empty or null!
     * (removed by jur) First letter of the name is a capital the rest is
     * lowercase
     */
    public Contact(String name) {
        //(Added by jur) Check for bad values
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name must not be empty or null!");
        }
        String n = name.trim();
        n = n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase();
        this.name = n;
        myAgenda = new ArrayList();
    }

    /**
     * Getter of the variable name
     *
     * @return name as string
     * (added by jur) First letter of the name is a capital the rest is
     * lowercase
     */
    public String getName() {
        return name;
    }

    /**
     * Removes an appointment out of the list.
     *
     * @param a as Appointment
     */
    public void removeAppointment(Appointment a) {
        if (a != null) {
            if (myAgenda.contains(a)) {
                myAgenda.remove(a);
            }
        }
    }

    /**
     * Adds an appointment to the Agenda. Appointment can't be null and can't
     * exist in the current Agenda.
     *
     * @param a as Appointment
     * @return true if Appointment is added, returns false if not. 
     */
    boolean addAppointment(Appointment a) {
        boolean check = false;
        if(a!= null)
        {
            if(!myAgenda.contains(a))
            {
                myAgenda.add(a);
                check = true;
            }
        }
        return check;
    }

    /**
     * Gives the next object in the Agenda
     *
     * @return Appointment that is the next one in the Agenda
     */
    public Iterator<Appointment> appointments() {
        return myAgenda.iterator();
    }

}
