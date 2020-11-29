# 基于Jetpack天气APP
## 体系结构

> 基于Google Android JetPack官方建议的框架来设计

![静态结构](C:\Users\peichendong\Desktop\draw\final-architecture.png)

## 第一次提交

### 模型图

​																				<img src="C:\Users\peichendong\Desktop\draw\第一次模型图.png" alt="模型图"  />  



### 实现的模块

1. 实现了Activity与Fragment的创建
2. 创建了FuturesViewModel,RealTimeViewModel来获取Reposiory中的数据
3. 数据来源:聚合api提供的天气接口

### 出现的问题

#### 1.LiveData

多次创建LiveData以及LiveData生成位置不对导致多次获取数据不能存储的LiveData中

### 解决方法

#### 1.LiveData

创建LiveData单例模式,并且每次获取数据需要使用postVlaue将任务发布到主线程。

注意: 每次必须要先创建LiveData然后在获取数据并postValue

setValue与postValue的区别:

在主线程中setValue的优先级比postValue的优先级高,如果在主线程中先执行setValue

<img src="C:%5CUsers%5Cpeichendong%5CDesktop%5Cdraw%5CpostValue.png" alt="postValue" style="zoom:80%;" />

<img src="C:%5CUsers%5Cpeichendong%5CDesktop%5Cdraw%5CsetValue.png" alt="setValue" style="zoom:80%;" />

## 第二次提交

### 模型图

![第二次模型图](C:%5CUsers%5Cpeichendong%5CDesktop%5Cdraw%5C%E7%AC%AC%E4%BA%8C%E6%AC%A1%E6%A8%A1%E5%9E%8B%E5%9B%BE.png)

### 实现的模块

1. 将两个ViewModel整合为一个ViewModel
2. 创建了WeatherRepository(天气仓库)
3. 将获取WebService的方法迁移到WeatherRepository，创建WeatherRepository单例这样可以更方便的获取连接仓库
4. 设置了状态提示,防止因没有数据而造成闪退,使用户知道为什么获取不到数据

### 出现的问题

Toast的连续显示问题,当有一个Toast还未显示完时,另一个Toast又需要马上显示来提示用户

### 解决方法

```java
  weatherViewModel.getStatusInfo ().observe ( WeatherFragment.this, new Observer<String> () {
           @SuppressLint("ShowToast")
           @Override
           public void onChanged(String s) {
               if (toast == null){
                   toast = Toast.makeText ( getContext (),s,Toast.LENGTH_SHORT );
               }else {
                   toast.setText ( s );
               }
               toast.show ();
           }
       } );
```

如果当前Toast不为空则将需要显示的内容通过上一个Toast显示出来

