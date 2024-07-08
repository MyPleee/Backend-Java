

# TEST

## DB Pool Test

#### 클래스명

`ConnectionTestMain.java` - dbPool을 사용한 select문 1500번과 새로운 커넥션을 1500번 생성해 select를 비교한 main 테스트 클래스 (pool안의 커넥션은 100개로 고정)

`DbManager.java` - 트랜잭션당 새로운 커넥션을 만드는 db manager

`DbManagerWithPool.java`- `DbPoolManager'의 pool을 사용하여 db를 사용하는 db manager

`DbPoolManager.java` - `initializePool`로 pool을 설정한 커넥션 개수만큼 초기화하고 `wait()`과 `notifyAll()`로 pool에서 커넥션을 얻게 만드는 dbpool manager

`TestDaoWithDb.java` - pool을 사용하지 않는 db manager로 커넥션을 맺는 DAO

`TestDaoWithDbPool.java` - pool을 사용하는 db manager로 커넥션을 맺는 DAO
