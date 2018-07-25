# herman-gameserver-springmvc-aggregation
gameserver aggregation
include herman-gameserver-springmvc-bean, herman-gameserver-springmvc-manager, herman-gameserver-springmvc-web, herman-gameserver-springmvc-app and so on

接下来的服务器功能完善
1.将设备注册逻辑的中的只以idfa来判断修改成dgudid和idfa共同判断
--2.增加数据统计表的中的点击和激活功能，
--3.后台数据接口
3.实现isdelete判断功能
4.增加互推检测和自动选择逻辑
5.创建web页面来增加游戏表数据和互推表数据
6.在线参数功能移植到当前服务器内
7.Nginx搭建
8.游戏数据保存功能实现
9.好友数据逻辑实现



目前进度：
所有后台接口已经完毕，可以写客户端进行接入
客户端
--实现获取游戏列表接口
--实现互推列表接口
--实现配置互推关系
实现添加新游戏功能
实现更新新游戏功能
优化展示效果
添加互推百分比计算
添加互推统计展示
