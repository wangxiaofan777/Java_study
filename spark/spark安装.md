```shell
# 解压，并重命名
tar xf spark-3.0.1-bin-hadoop3.2.tgz
mv spark-3.0.1-bin-hadoop3.2 spark-3.0.1
```



```shell
# 配置环境变量
sudo vim /etc/profile

export SPARK_HOME=/usr/local/software/spark-3.0.1
export PATH=$PATH:$SPARK_HOME/bin
# 生效
source /etc/profile
```

```shell
# 对配置文件进行重命名
cd /usr/local/software/spark-3.0.1/conf
mv log4j.properties.template log4j.properties
mv slaves.template slaves
mv spark-env.sh.template spark-env.sh
mv spark-defaults.conf.template spark.conf
mv fairscheduler.xml.template fairscheduler.xml
mv metrics.properties.template metrics.properties
```

