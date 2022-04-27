package com.cat;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现连接zookeeper（注册中心），监听子节点（生产者服务）
 */
public class Consumer {
    public ZooKeeper client;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Consumer consumer = new Consumer();
        consumer.getConnect();
        consumer.getServerList();

        Thread.sleep(Integer.MAX_VALUE);
    }

    private void getConnect() throws IOException {
        //连接 zookeeper 集群，相当于连接注册中心
        client = new ZooKeeper("cp1:2181,cp2:2181,cp3:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    //触发watch 事件后，继续读取子节点
                    getServerList();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getServerList() throws KeeperException, InterruptedException {
        //读取 servers 的子节点，相当于provider，包含了他们的服务地址
        List<String> servers = new ArrayList<>();
        List<String> children = client.getChildren("/servers", true);
        for (String child : children) {
            byte[] data = client.getData("/servers/" + child, false, null);
            servers.add(new String(data));
        }
        System.out.println("Discovery " + servers);
    }
}
