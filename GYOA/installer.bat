echo off 
::去掉rem对应的重新输入命令行(注释::)
rem 开始安装
java -classpath WebRoot/WEB-INF/classes cn.gy.oa.installer.Installer
rem 暂停
pause