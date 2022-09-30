Disclaimer
The purpose of my development is for educational purposes. If you use this software to cause any damage, it has nothing to do with me.
我开发的目的是为了教育目的。如果你使用本软件造成任何损失与作者无关。

Apex 无后座力力脚本
Fully working no recoil with Logitech mouse with Logitech game software & ghub
---------------------------------------------------------------------------------------------------------------------------------------------------------
How to use it:
使用说明：within the ide 
    important : Make sure logitech game software or Ghub is installed
    重要：需要安装罗技鼠标驱动lgs或者ghub
    (lgs supports more guns than ghub although it still detects the weapon some weapons won't have recoil script tid to it in ghub)
    (lgs 支持的枪支比 ghub 多，尽管它仍然可以检测到武器，有些武器在 ghub 中不会有对应的无后座脚本)

     Please make sure your game language is set to Chinese, or the script will not work unless you update the weapon screen shots to english snapshots.
      请确保游戏语言设置为中文，否则脚本将无法工作，除非您更新武器屏幕截图为英文快照
                
         操作说明:
                游戏中鼠标灵敏度为1.6，鼠标加速度关闭，罗技驱动——>指针设置——>报告率改为1000，加速关闭
                哈沃克和轻型机枪必须有涡轮增压器,Numlock开关宏,开枪时按住鼠标右键启动宏
                
         English:
                Mouse sensitivity in game is 1.6, mouse acceleration is off, Logitech driver->pointer settings->report rate is 1000, acceleration is off
                Havoc and light machine gun must have turbocharger, Numlock switch for on/off macro, hold the right mouse button to start the macro when shooting);
   
    1. make sure you installed jdk-18_windows-x64_bin.msi 
       确保安装最新的java jdk，安装文件已经打包在

    2. upload correct marco script to your mouse driver (LGS/GHUB) in MarcoScripts Folder
       上传正确的分辨率脚本文件到对应的罗技驱动，lgs/ghub. 文件在MarcoScripts里面

    3. start apex.jar
       启动apex.jar

There is a version difference between lgs and ghub：
lgs和ghub有版本区别问题：
	如果发生问题请手动修改两处loadstring 成load，在每行的开头就可以发现a=loadstring ，修改成 a=load
	If there is a problem, please manually modify the two loadstrings into load, 
	you can find a=loadstring at the beginning of each line, and modify it to a=load

    
---------------------------------------------------------------------------------------------------------------------------------------------------------
Envrioment 
  IDE: intellij
  libary: opencv-460
  java : 1.8
