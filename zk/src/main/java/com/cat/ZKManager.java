package com.cat;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKManager {
    private static ZooKeeper zkeeper;
    private static ZKConnection zkConnection;

    public ZKManager() throws Exception {
        zkConnection = new ZKConnection();
        zkeeper = zkConnection.connect();
    }

    public void closeConnection() throws Exception {
        zkConnection.close();
    }

    public void create(String path, byte[] data) throws Exception {
        zkeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public Object getZNodeData(String path) throws Exception {
        byte[] b = null;
        b = zkeeper.getData(path, null, null);
        return new String(b, "UTF-8");
    }

    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
    }

    public Stat exist(String path) throws Exception {
        return zkeeper.exists(path, true);
    }

    public static void main(String[] args) throws Exception {
        ZKManager zkm = new ZKManager();
//		zkm.create("/node2", "node2val".getBytes());

//		System.out.println(zkm.getZNodeData("/node2"));

//		zkm.update("/node2", "node2valupdate".getBytes());

        System.out.println(zkm.exist("/node2"));
    }

}