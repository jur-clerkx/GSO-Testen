/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * Verbetering geschreven door Nick
 */
public class TimeSpan2 implements ITimeSpan2 {

    /* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
     */
    private ITime bt, et;
    private int duration;
    
    /**
     * 
     * @param bt must be earlier than et
     * @param minutes 
     */
    public TimeSpan2(ITime bt,int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.duration = minutes;
        this.et = this.bt.plus(minutes);
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return et;
    }

    @Override
    public int length() {
        return et.difference(bt);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(int minutes) {
        if (this.et.plus(minutes).compareTo(this.bt) <= 0) {
            throw new IllegalArgumentException("end time "
                    + et + " must be later then begin time " + bt);
        }

        et = this.et.plus(minutes);
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        duration += minutes;
        et = et.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        
        et = et.plus(minutes);
    }

    @Override
    public boolean isPartOf(ITimeSpan2 timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) <= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) >= 0);
    }

    @Override
    public ITimeSpan2 unionWith(ITimeSpan2 timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
        
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }
        int duration = begintime.difference(endtime);
        return new TimeSpan2(begintime, duration);

    }

    @Override
    public ITimeSpan2 intersectionWith(ITimeSpan2 timeSpan) {

        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(endtime) < 0) {
            return null;
        }
        int duration = begintime.difference(endtime);
        return new TimeSpan2(begintime, duration);
    }
}
