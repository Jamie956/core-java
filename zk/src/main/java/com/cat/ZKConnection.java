package com.cat;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKConnection {
	private ZooKeeper zoo;
	CountDownLatch connectionLatch = new CountDownLatch(1);

	public ZooKeeper connect() throws IOException, InterruptedException {
		zoo = new ZooKeeper("localhost", 2181, new Watcher() {
			@Override
			public void process(WatchedEvent we) {
				if (we.getState() == KeeperState.SyncConnected) {
					connectionLatch.countDown();
				}
			}
		});

		connectionLatch.await();
		return zoo;
	}

	public void close() throws InterruptedException {
		zoo.close();
	}
}