package com.wxf.storm.topology;

import com.wxf.storm.bolt.LogParseBolt;
import com.wxf.storm.bolt.ProductCountBolt;
import com.wxf.storm.spout.AccessLogKafkaSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class HotProductTopology {

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        Config config = new Config();

        builder.setSpout("AccessLogKafkaSpout", new AccessLogKafkaSpout())
                .setNumTasks(1);

        builder.setBolt("LogParseBolt", new LogParseBolt(), 1)
                .setNumTasks(1)
                .shuffleGrouping("AccessLogKafkaSpout");

        builder.setBolt("ProductCountBolt", new ProductCountBolt(), 1)
                .setNumTasks(1)
                .fieldsGrouping("LogParseBolt", new Fields("productId"));
        if (args != null && args.length > 0) {
            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        } else {
            try {
                LocalCluster localCluster = new LocalCluster();
                localCluster.submitTopology("HotProductTopology", config, builder.createTopology());
                Utils.sleep(30000);
                localCluster.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
