# redismaser
/etc/init.d/redis_7001 start
/etc/init.d/redis_7002 start
service mysql start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &


# redisnode1
/etc/init.d/redis_7003 start
/etc/init.d/redis_7004 start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &


# redisnode2
/etc/init.d/redis_7005 start
/etc/init.d/redis_7006 start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &