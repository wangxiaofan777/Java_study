# redismaser
/etc/init.d/redis_7001 start
/etc/init.d/redis_7002 start
service mysql start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &
/usr/local/tools/nginx/nginx/sbin/nginx

# redisnode1
/etc/init.d/redis_7003 start
/etc/init.d/redis_7004 start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &
/usr/local/tools/nginx/nginx/sbin/nginx

# redisnode2
/etc/init.d/redis_7005 start
/etc/init.d/redis_7006 start
/usr/local/tools/zookeeper-3.4.5/bin/zkServer.sh start
nohup /usr/local/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh /usr/local/tools/kafka_2.12-2.7.0/config/server.properties &
/usr/local/tools/nginx/nginx/sbin/nginx


/usr/local/tools/kafka_2.12-2.7.0/bin/kafka-console-consumer.sh --bootstrap-server redismaster:9092,redisnode1:9092,redisnode2:9092 --topic access-log
