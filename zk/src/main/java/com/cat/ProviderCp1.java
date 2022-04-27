package com.cat;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 实现创建 zookeeper 创建子节点（生产者往注册中心注册）
 * 前置条件：zk集群创建节点 create /servers "servers"
 */
public class ProviderCp1 {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //zookeeper 相当于注册中心
        ZooKeeper client = new ZooKeeper("cp1:2181,cp2:2181,cp3:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        //创建的节点相当于一个生产者服务往注册中心注册
        String hostName = "cp1";
        //临时节点，带序列号
        client.create("/servers/"+hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " register!");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
