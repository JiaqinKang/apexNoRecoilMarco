 软件不再更新，用着自责

Disclaimer
The purpose of my development is for educational purposes. If you use this software to cause any damage, it has nothing to do with me.
我开发的目的是为了教育目的。如果你使用本软件造成任何损失与作者无关。

Apex 无后座力力脚本
Fully working no recoil with Logitech mouse with Logitech game software & ghub
---------------------------------------------------------------------------------------------------------------------------------------------------------
支持分辨率：
 - [x] 1920X1080
 - [x] 2560X1440
 - [x] 3440X1440
 - [x] 2560X1600
 - [ ] 其他未知，我时间有限，能动手的请自己来

---------------------------------------------------------------------------------------------------------------------------------------------------------
支持枪：注(自动开枪请按照设置第二开枪按键)
 - [x] Alternator SMG 转换者
 - [x] Car 
 - [x] Devotion LMG 专注 （有涡轮/无涡轮）
 - [x] G7 (不推荐安装双发班机)
 - [x] Havoc 哈沃克 （有涡轮/无涡轮）
 - [x] HemLock 赫姆洛克突击步枪 （单点自动连发开枪）
 - [x] L-STAR 能量机枪
 - [x] m600 喷火轻机枪
 - [x] p2020 手枪 （自动连发开枪）
 - [x] R301 卡宾枪
 - [x] Rampage LMG 暴走 （有燃烧弹/无燃烧弹）
 - [x] powler Burst PD 猎兽冲锋枪 （自动连发开枪）
 - [x] RE-45 自动手枪
 - [x] VK 平行步枪
 - [x] Volt SMG 电能冲锋枪
 - [x] Wing Man 辅助手枪
 - [x] R99 冲锋枪
---------------------------------------------------------------------------------------------------------------------------------------------------------
游戏准星效果：
 
  ![效果](https://user-images.githubusercontent.com/37282586/220811002-5efca547-fc51-4bb1-97f0-2cd139d9dda3.jpg)
  ![效果2](https://user-images.githubusercontent.com/37282586/220811078-bf444868-4529-4a45-b48c-277767b9072a.jpg)

---------------------------------------------------------------------------------------------------------------------------------------------------------

How to use it:
使用说明：within the ide 
    important : Make sure logitech game software or Ghub is installed
    重要：需要安装罗技鼠标驱动lgs或者ghub
    (lgs supports more guns than ghub although it still detects the weapon some weapons won't have recoil script tid to it in ghub)
    (lgs 支持的枪支比 ghub 多，尽管它仍然可以检测到武器，有些武器在 ghub 中不会有对应的无后座脚本)

     Please make sure your game language is set to Chinese, or the script will not work unless you update the weapon screen shots to english snapshots.
      请确保游戏语言设置为中文，否则脚本将无法工作，除非您更新武器屏幕截图为英文快照
   
    1. make sure you installed jdk-18_windows-x64_bin.msi 
       确保安装最新的java jdk，安装文件已经打包在

    2. upload correct marco script to your mouse driver (LGS/GHUB) in MarcoScripts Folder
       上传正确的分辨率脚本文件到对应的罗技驱动，lgs/ghub. 文件在MarcoScripts里面

    3. start apex.jar
       启动apex.jar

There is a version difference between lgs and ghub： if countering issues,please run the debug启动器 and check the console output for more information screenshot it and send it to me, I will try to help you.


lgs和ghub有版本区别问题：如果遇到问题请提交debug启动器的截图，我会尽快修复

    
---------------------------------------------------------------------------------------------------------------------------------------------------------
Envrioment 
  IDE: intellij
  libary: opencv-460
  java : 1.8
---------------------------------------------------------------------------------------------------------------------------------------------------------
  Huge thanks for 空话lua的解码
  https://github.com/KonghuanSmart
---------------------------------------------------------------------------------------------------------------------------------------------------------
**打赏杯咖啡**



![微信图片_20221023181158](https://user-images.githubusercontent.com/37282586/197405808-3ba7a3c3-de24-4f4f-ab51-8486959d466a.jpg)

 ---------------------------------------------------------------------------------------------------------------------------------------------------------
 请自行排查一下可能出现的问题, 查看前请看看你的:
 
        游戏灵敏度调成1.6了吗

        鼠标驱动报告率1000了吗

        游戏里的ADS是不是被你调过了，默认是1.0（建议直接按恢复默认）

        屏幕缩放设置100%（不会的自行baidu，这个太简单了自己搜一下）

        注意：如果觉得视觉移动慢，可以提高鼠标驱动面板的DPI来弥补

        导入脚本教程请自己找一下，小破站上一大堆。自行搜索lsg/ghub 导入脚本。 你也可以绑定apex的启动程序，在SteamLibrary里面，
                例如 D:\SteamLibrary\steamapps\common\Apex Legends\r5apex.exe


GHUB失灵
    请尝试重新安装GHUB

    压枪方法变成锁定数字键盘按键开启

压过头怎么办

    先检查游戏灵敏度有没有设置1.6和ADS设置1.0
    
    如果设置了还是压过头，就一点点的降低游戏灵敏度，直到自己觉得最合适为止

压枪左右抖动很厉害怎么办

    查看你的WIN10版本是否是2004或20H2，如果是这两个版本，会有几率导致压枪不稳，自行降级系统。

桌面有压枪效果，游戏里面没效果怎么办

    在任务栏小图标那里退出鼠标驱动，然后管理员权限启动鼠标驱动就可以了
    
为什么突然会截图失败？

    傻逼360，未经过用户同意把你的文件占用扫描了，大概率还会上传到360的服务器造成截图失败资源被占用。强烈推荐删除卸载傻逼360，使用火绒。

如果想改成默认启动就是管理员权限，右键鼠标驱动图标，打开文件位置，再右键，属性，点顶部的【兼容性】，勾上【以管理员权限运行此程序】
