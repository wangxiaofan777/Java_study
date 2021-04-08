package com.wxf.storm;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 单词统计拓扑
 */
public class WordCountTopology {


    /**
     * Spout
     * <p>
     * spout,继承一个基类，实现接口，这个里面主要负责从数据源获取数据
     * <p>
     * 我们这里作为一个简化，就不从外部数据源获取数据了，只是自己内部不断发射一些句子
     */
    public static class RandomWordCountSpout extends BaseRichSpout {

        private static final long serialVersionUID = 42L;

        private SpoutOutputCollector collector;

        private Random random;

        /**
         * open 方法，是对Spout进行初始化，类似创建一个线程池
         *
         * @param map
         * @param topologyContext
         * @param spoutOutputCollector
         */
        @Override
        public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
            // spout在初始化的时候会传进来一个SpoutOutputCollector，这个就是用来发射东西的
            this.collector = spoutOutputCollector;
            this.random = new Random();
        }

        /**
         * nextTuple方法
         * 这个Spout类，最终会运行在某个executor的某个task当中，这个task会负责无限循环调用nextTuple方法，最终形成一个数据流
         */
        @Override
        public void nextTuple() {
            Utils.sleep(100);
            String[] sentences = new String[]{"the cow jumped over the moon", "an apple a day keeps the doctor away",
                    "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature"};
            String sentence = sentences[random.nextInt(sentences.length)];
            // 这个values可以认为就是构建的一个tuple，tuple是最小的数据单位，无限个tuple形成了一个stream
            this.collector.emit(new Values(sentence));
        }

        /**
         * declareOutputFields方法
         * 定义你发出去的每个tuple中的每个filed的名称是什么
         *
         * @param outputFieldsDeclarer
         */
        @Override
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("sentence"));
        }
    }

    /**
     * 实现一个bolt，直接继承一个BaseRichBolt基类
     * 实现里面的方法即可，每个bolt代码，同样是发送到某个executor里面的某个task里面去执行
     */
    public static class SplitSentence extends BaseRichBolt {

        private static final long serialVersionUID = 1L;

        private OutputCollector collector;

        /**
         * OutputCollector就是一个tuple的发射器
         *
         * @param map
         * @param topologyContext
         * @param outputCollector
         */
        @Override
        public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.collector = outputCollector;
        }

        /**
         * execute方法，每次接收到一条数据后，就交给execute方法执行
         *
         * @param tuple
         */
        @Override
        public void execute(Tuple tuple) {
            String sentence = tuple.getStringByField("sentence");
            String[] words = sentence.split(" ");
            for (String word : words) {
                this.collector.emit(new Values(word));
            }

        }

        /**
         * 定义发射出去的tuple
         *
         * @param outputFieldsDeclarer
         */
        @Override
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word"));
        }
    }

    public static class WordCount extends BaseRichBolt {

        private OutputCollector collector;
        private Map<String, Long> wordCounts = new HashMap<>();

        @Override
        public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.collector = outputCollector;
        }

        @Override
        public void execute(Tuple tuple) {
            String word = tuple.getStringByField("word");
            Long count = wordCounts.getOrDefault(word, 0L);

            if (count == 0L) {
                count++;
            }

            wordCounts.put(word, count);

            System.out.println(wordCounts);
            this.collector.emit(new Values(word, count));
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word", "count"));
        }
    }

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        /**
         * 第一个参数是给这个spout设置名字
         * 第二个是创建一个spout对象
         * 第三个是设置这个spout有几个executor
         */
        builder.setSpout("RandomSentence", new RandomWordCountSpout(), 2);
        builder.setBolt("SplitSentence", new SplitSentence(), 5)
                .setNumTasks(10)
                .shuffleGrouping("RandomSentence");

        /**
         * 相同的单词，从SplitSentence发射出来，一定会进入到相同的task当中，只有这样才能精准计算出每个单词的数量
         */
        builder.setBolt("WordCount", new WordCount(), 10)
                .setNumTasks(20)
                .fieldsGrouping("SplitSentence", new Fields("word"));

        Config config = new Config();
        config.setDebug(true);
        if (args != null && args.length > 0) {
            config.setNumWorkers(3);
            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            config.setNumWorkers(20);
            try {
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("WordCountTopology", config, builder.createTopology());
                Thread.sleep(10000);
                cluster.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
