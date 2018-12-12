## OKHttp：作为Android内置网络访问api，很重要。
* Build模式
* executed 只能执行一次

### dispatcher（生产者）
* 维护异步同步的请求队列。
* executorService，线程池（消费者池）
    * 和runningAsyncCalls的区别？ 添加+线程池执行
    * 同步runningSyncCalls直接添加/移除，那么谁来执行call呢？
* finish()
    * 移除call
    * promoteCall调整队列。
* promoteCall() --> readyAsyncCalls
    * 移除一个ready中的call，添加到runningAsyncCalls
    * 放入executorService中执行。
##### 异步队列，两个
* ready、running
##### 同步
##### 异步
* 最大个数

### 拦截器
* 不区分同步和异步。
* 拦截器链：一次调用每一个拦截器。
##### 缓存拦截器
* DiskLruCache算法
* 只缓存GET方法的，通过url作为key
* 加密、解密
* 获取缓存策略
....

-------

## Retrofit
> Retrofit创建用于描述网络请求的接口，封装请求参数等信息，由OKHttp进行网络请求。    
  OKHttp将请求结果交给Retrofit，Retrofit根据用户需求将结果进行解析。
> Retrofit使用到了非常多的设计模式很重要。
### 动态代理模式
##### 静态代理
##### 动态代理
> 在程序运行时创建的代理方式。
* jdk动态代理
* CGLIB动态代理
* 运行期、和静态代理的区别

### create()
##### validateEagerly
> 是否要提前验证/解析接口。 验证什么？有什么用？
* ServiceMethod、loadServiceMethod()
##### 动态代理模式的应用
* 
-----
## Dagger2
* 使用反射，会消耗资源。
  但是，Dagger在编译时使用反射，生成中间代码，在运行时使用。所以在程序运行时没影响。
  所以，像Dagger、ButterKnife这样的注解框架，即使使用反射，在程序运行时也不会影响速度。
  「原理说明白！」
* component A 依赖 B时
    * A/B的Module可以给各自的Component提供实例。
    * 但是B的Module中提供的实例不能直接给A用，需要在B的Component中提供B Module中实例，A才能用。（原因？）
#### 为什么comptA依赖comptB的时候，comptA想用到moduleB中提供的实例obj，需要在comptB中显式的提供出来？
> 猜想：
* 若comptB中还未显式提供obj，那么只有单独使用comptB的时候，可以用到moduleB中的obj。
    * comptB的中间代码中，有moduleB的实例。但是是private。所以只有moduleB中自己可用obj。
* compA依赖compB后，在compA中虽然有compB的实例了，但在compA中不能访问到compB中的moduleB。所以，不能使用moudleB中的obj。
* 除非，此时在compB中显示提供出来moduleB中的obj才可访问到。






























