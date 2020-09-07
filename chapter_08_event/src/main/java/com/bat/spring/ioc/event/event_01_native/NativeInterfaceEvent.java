package com.bat.spring.ioc.event.event_01_native;

import java.util.Observable;
import java.util.Observer;

/**
 * 基于 Java.util 包接口实现观察者模式
 *
 * @author ZhengYu
 * @version 1.0 2020/9/7 19:50
 * @see Observable
 * @see Observer
 * @see java.util.EventObject
 * @see java.util.EventListener
 **/
public class NativeInterfaceEvent {

    public static void main(String[] args) {
        ObservableImpl observable = new ObservableImpl();

        observable.addObserver(new ObserverImpl(1));
        observable.addObserver(new ObserverImpl(2));

        observable.setChanged();
        observable.notifyObservers("Hello Observer!");
        observable.clearChanged();
    }

    private static class ObservableImpl extends Observable {

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        protected synchronized void clearChanged() {
            super.clearChanged();
        }
    }

    private static class ObserverImpl implements Observer {

        private Integer id;

        ObserverImpl(Integer id) {
            this.id = id;
        }

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(this.toString() + " receive msg: " + arg.toString());
        }

        @Override
        public String toString() {
            return "observer_" + id;
        }
    }
}
