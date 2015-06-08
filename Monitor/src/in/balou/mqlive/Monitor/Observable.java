package in.balou.mqlive.Monitor;

import java.util.ArrayList;

/**
 * Created by marco on 08/06/15.
 */
public class Observable
{
    public Observable()
    {
        observers = new ArrayList<Observer>();
    }
    private ArrayList<Observer> observers;

    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    protected void informAll()
    {
        observers.forEach(x -> x.update());
    }
}
