package com.cat.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributedLock {
    private ZooKeeper client;
    private CountDownLatch connectLatch = new CountDownLatch(1);
    //监听路径
    private String waitPath;
    private CountDownLatch waitLatch = new CountDownLatch(1);
    private String node;

    public DistributedLock() throws IOException, InterruptedException, KeeperException {
        client = new ZooKeeper("cp1:2181,cp2:2181,cp3:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //客户端连接事件
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    connectLatch.countDown();
                }
                //监听节点删除事件，且删除的节点的前一个节点
                if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
                    //取消阻塞，可以获取锁
                    waitLatch.countDown();
                }
            }
        });

        connectLatch.await();

        //不存在 /locks 节点时创建节点
        Stat exists = client.exists("/locks", false);
        if (exists == null) {
            client.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 获取锁
     */
    public void acquire() throws KeeperException, InterruptedException {
        //locks目录下创建新节点，无值，临时节点带序号
        node = client.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        List<String> children = client.getChildren("/locks", false);

        //locks目录下只有一个节点，就是刚创建的节点，无竞争
        if (children.size() == 1) {
            System.out.println("acquire " + node);
            return;
        } else {
            Collections.sort(children);

            String seqNode = node.substring("/locks/".length());
            int index = children.indexOf(seqNode);

            if (index == -1) {
                System.out.println(seqNode + " 节点不存在");
                throw new RuntimeException(seqNode + " 节点不存在");
            } else if (index == 0) {
                System.out.println("acquire " + node);
                //节点排在最前，锁获取成功
                return;
            } else {
                //监听前一个节点，等到前一个节点释放回调，就可以去获取锁
                waitPath = "/locks/" + children.get(index - 1);
                client.getData(waitPath, true, null);
                //等待前一个节点释放
                waitLatch.await();
                System.out.println("acquire " + node);
                return;
            }
        }
    }

    /**
     * 释放锁
     */
    public void release() throws KeeperException, InterruptedException {
        //删除节点
        System.out.println("release " + node);
        client.delete(node, -1);
    }
}
