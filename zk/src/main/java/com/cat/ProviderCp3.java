package com.cat;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ProviderCp3 {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper client = new ZooKeeper("cp1:2181,cp2:2181,cp3:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        String hostName = "cp3";
        client.create("/servers/"+hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " register!");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
