# fkcloud所需组件安装
## zookeeper安装
```jshelllanguage
docker run --privileged=true -d --name zookeeper  --publish 2181:2181  -d zookeeper:latest
```
## zkui安装
```jshelllanguage

```

## 私有库 registry安装
```jshelllanguage
docker run -d -v /Users/egojit/docker/registry:/var/lib/registry -p 5000:5000 --restart=always --privileged=true --name registry registry:latest
```
