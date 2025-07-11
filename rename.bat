@echo off
setlocal enabledelayedexpansion

chcp 65001 >nul

rem 设置目标目录
set "targetDir=.\hap"
set "sourceDir=D:\openharmony_ws\OpenHarmony-5.0-Release\applications_systemui\applications_systemui\"
set "screenlock=D:\openharmony_ws\OpenHarmony-5.0-Release\applications_screenlock\applications_screenlock\"
rem 创建目录
if not exist "%targetDir%" mkdir "%targetDir%"

rem 复制文件并重命名

copy %sourceDir%product\phone\dropdownpanel\build\default\outputs\default\phone_dropdownpanel-phone_entry-default-signed.hap %targetDir%\SystemUI-DropdownPanel.hap
copy %sourceDir%product\phone\statusbar\build\default\outputs\default\phone_statusbar-phone_entry-default-signed.hap  %targetDir%\SystemUI-StatusBar.hap
copy %sourceDir%product\default\navigationBar\build\default\outputs\default\default_navigationBar-phone_entry-default-signed.hap  %targetDir%\SystemUI-NavigationBar.hap
copy %sourceDir%product\default\notificationmanagement\build\default\outputs\default\default_notificationmanagement-phone_entry-default-signed.hap  %targetDir%\SystemUI-NotificationManagement.hap
copy %sourceDir%product\default\volumepanel\build\default\outputs\default\default_volumepanel-phone_entry-default-signed.hap  %targetDir%\SystemUI-VolumePanel.hap
copy %sourceDir%entry\phone\build\default\outputs\default\phone_entry-default-signed.hap  %targetDir%\SystemUI.hap
copy %sourceDir%product\default\dialog\build\default\outputs\default\default_dialog-phone_entry-default-signed.hap  %targetDir%\SystemUI-SystemDialog.hap

copy %screenlock%product\phone\build\default\outputs\default\phone-entry-default-signed.hap %targetDir%\SystemUI-ScreenLock.hap


rem 显示结果
echo.
echo 目标目录文件:
dir /B "%targetDir%"

pause