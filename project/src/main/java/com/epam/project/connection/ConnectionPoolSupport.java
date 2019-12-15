package com.epam.project.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

 class ConnectionPoolSupport extends TimerTask {
    private static Logger Logger = LogManager.getLogger();
    private ReentrantLock lock;
    private Condition condition;
    private int poolSize;
    private BlockingQueue<ProxyConnection> freeConnections;
    private ArrayList<ProxyConnection> givenConnections;

     ConnectionPoolSupport(ReentrantLock lock, Condition condition, int poolSize, BlockingQueue<ProxyConnection> freeConnections, ArrayList<ProxyConnection> givenConnections) {
        this.lock = lock;
        this.condition = condition;
        this.poolSize = poolSize;
        this.freeConnections = freeConnections;
        this.givenConnections = givenConnections;
    }

    @Override
    public void run() {
        try {
            Logger.debug("Connect Pool Support");
            lock.lock();
            while (freeConnections.size() + givenConnections.size() != poolSize){
                ProxyConnection proxyConnection = new ProxyConnection();
                freeConnections.offer(proxyConnection);
            }
        } finally {
            lock.unlock();
            condition.signalAll();
        }
    }
}
