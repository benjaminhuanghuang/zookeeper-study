package ben.study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A new test instance will be created once per test class.
@TestInstance(Lifecycle.PER_CLASS)
class TestZookeeper {
    private String connectString = "localhost:2181";
    //    private String connectString = "localhost:2181,localhost:2182,localhost:2183";
    private int sessionTimeout = 2000;
    ZooKeeper zkClient;

    @BeforeAll
    public void setup() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    @Test
    public void createNode() throws IOException, KeeperException, InterruptedException {
        String path = zkClient.create("/ben", "testdata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", false);

    }

    @Test
    public void isNodeExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/test", false);
    }
}