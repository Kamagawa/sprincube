
## Store Reference to Microservices in the Config Map
The Following Config Map stores the reference to the service path: 

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: path-config
  namespace: default
data:
  ACCOUNT_SERVICE: "http://account"
  FRIEND_SERVICE: "http://friend-service"
  ENEMY_SERVICE: "http://enemy-service"
```


Then Deployment imports the environment variable into the pod: 

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  ...
spec:
  ...
  template:
    ...
    spec:
      containers:
        - name: bff
          image: bff:v0
          imagePullPolicy: IfNotPresent
          ...
          envFrom:
          - configMapRef:
              name: path-config
```

## Store Environmental Variable referenced Across Services and Pods 
The following file stores a number of gloabal variabkles for the game

```yaml
apiVersion: v1
data:
  game.properties: |
    enemies=aliens
    lives=3
    enemies.cheat=true
    enemies.cheat.level=noGoodRotten
    secret.code.passphrase=UUDDLRLRBABAS
    secret.code.allowed=true
    secret.code.lives=30
  ui.properties: |
    color.good=purple
    color.bad=yellow
    allow.textmode=true
    how.nice.to.look=fairlyNice
kind: ConfigMap
metadata:
  creationTimestamp: 2016-02-18T18:52:05Z
  name: game-config
  namespace: default
  resourceVersion: "516"
  selfLink: /api/v1/namespaces/default/configmaps/game-config
  uid: b4952dc3-d670-11e5-8cd0-68f728db1985
``` 

## Store Environmental Variables to manipulate Pod set up
The following config map is used to set up the reddis cache
```yaml
apiVersion: v1
data:
  redis-config: |
    maxmemory 2mb
    maxmemory-policy allkeys-lru
kind: ConfigMap
metadata:
  creationTimestamp: 2016-03-30T18:14:41Z
  name: example-redis-config
  namespace: default
  resourceVersion: "24686"
  selfLink: /api/v1/namespaces/default/configmaps/example-redis-config
  uid: 460a2b6e-f6a3-11e5-8ae5-42010af00002
```

Then The Raddis Deployment refer to the 'example-radis-config' to intialize the pod. 
Any other database, persistent volumne is set up pretty much the same way.
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: redis
spec:
  containers:
  - name: redis
    image: kubernetes/redis:v1
    env:
    - name: MASTER
      value: "true"
    ports:
    - containerPort: 6379
    resources:
      limits:
        cpu: "0.1"
    volumeMounts:
    - mountPath: /redis-master-data
      name: data
    - mountPath: /redis-master
      name: config
  volumes:
    - name: data
      emptyDir: {}
    - name: config
      configMap:
        name: example-redis-config
        items:
        - key: redis-config
          path: redis.conf
```

## Container profile vs Local Profile.
Spring allows you to set up specific profiles. The variables in the application.properties or application.yml falls into the default profile. To enable container profile, set container entry point at 
```bash
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=container", "/app.jar"]
```
Where the '-Dspring.profiles.active=container' will activate the container profile. 
Now you can have two set of variables for uncontainerized and containerized environment. 

```yaml
spring:
  jpa:
    generate-ddl: true
    hibernate:
      ddl-auto: none
    show-sql: true
  application:
    name: friend-service
  datasource:
    url: jdbc:mysql://den1.mysql6.gear.host/coolfriends
    username: coolfriends
    password: Ab77F-88M_9l
  data:
    rest:
      base-path: api
---
spring:
  profiles: container
  datasource:
    url: jdbc:mysql://den1.mysql6.gear.host/coolfriends
    host: den1.mysql6.gear.host/32
    username: ${DATABASE_USER}
    password: ${DATABASE_PASSWORD}
server:
  port: 8081

The first part of spring is the uncontainerized set up, and the second part is the containerized profile. 

```
##Secres 
A secret is an object that stores sensitive data in an encoded fashion. Secret stores base64 encoded data in a map: 
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque
data:
  username: YWRtaW4=
  password: MWYyZDFlMmU2N2Rm
```
The String can be encoded: 
```bash
$ echo -n 'admin' | base64
YWRtaW4=
$ echo -n '1f2d1e2e67df' | base64
MWYyZDFlMmU2N2Rm
```
and decoded: 
```bash
$ echo 'MWYyZDFlMmU2N2Rm' | base64 --decode
1f2d1e2e67df
```
Then import it as environment Variable: 
```yaml
 - name: SECRET_PASSWORD
        valueFrom:
          secretKeyRef:
            name: mysecret
            key: password
```

