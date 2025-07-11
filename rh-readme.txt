repo init -u git@gitee.com:openharmony/manifest.git -b OpenHarmony_feature_20250328 --no-repo-verify --depth=10
repo sync -c -j16
repo forall -c 'git lfs pull'




https://gitee.com/liwentao_uiw/arkcompiler_runtime_core/blob/ani_spec/static_core/plugins/ets/runtime/ani/docs/ani_scenario.md
ANI资料，可以学习看看


https://gitee.com/liwentao_uiw/arkcompiler_runtime_core/blob/ani_spec/static_core/plugins/ets/runtime/ani/docs/napi2ani.md
部分napi迁移ani的示例


1.repo工具下载
# 华为镜像（OpenHarmony 官方推荐）
curl https://mirrors.huaweicloud.com/repo > repo

# Gitee 镜像
curl https://gitee.com/oschina/repo/raw/fork_flow/repo > repo

2.repo上述只是下载脚本，还要修改权限

注意事项
依赖环境：repo 依赖 Python 3.6+ 和 Git 2.10+，请确保系统已安装。
更新方式：运行 repo selfupdate 可更新 repo 到最新版本。
权限问题：若提示权限不足，可尝试使用 sudo 或调整文件权限。

验证repo
repo --version


https://gitee.com/asanrocks/taihe/blob/main/cookbook/hello_world/README.md
1、本地运行Taihe环境和步骤
1.1 环境：使用prebuild里预发布的taihe_tryit即可试运行自己的文件夹，需要先执行预编译命令
./build/prebuilts_download.sh
1.2 执行步骤
文件夹格式为以下格式，即必须有idl、author/src、user三个文件夹爱
|- taihe_test/
|- author/src/xxx.impl.cpp
|- idl/xxx.taihe
|- user/main.ets
idl文件夹中存放.taihe文件，author/src中存放实现的cpp，user中存放main.ets文件
用例参考https://gitee.com/asanrocks/taihe/tree/main/cookbook/hello_ani

执行命令即可直接编译abc和so，并在host侧运行，无需上板验证
//prebuilts/taihe/ohos/linux-x86_64/taihe/bin/taihe-tryit test path_to_your_file --user sts
如果现实panda vm下载失败，答疑群空间可直接下载panda vm压缩包，注意匹配版本号





uname -a查看linux当前系统详情
下载 Python 3.10 源码：
wget https://www.python.org/ftp/python/3.10.12/Python-3.10.12.tgz
tar -xf Python-3.10.12.tgz
cd Python-3.10.12



python3.10 --version

无障碍：
https://gitee.com/openharmony/accessibility

taihe:
https://gitee.com/asanrocks/taihe/blob/main/docs/DevSetup.md

wifi:
monkey_yaohuaqiande
1XTS:123456789

 https://gitee.com/up200504098/accessibility
 
 
 润和邮箱：li_yun2@hoperun.com
邮箱初始密码：hoperun@123--Liyun1030
首次登陆，请登陆网址：mail.hoperun.com

如果遇到邮箱相关问题，如重置密码等，请在云之家上找IT ：蒋芳 000004436



OpenHarmony导航
1.OpenHarmony官网

https://www.openharmony.cn/mainPlay

2.码云地址

https://help.gitee.com/

3.代码下载

https://www.openharmony.cn/download

4.自动化用例

http://wiki.dev.com/link/199#bkmrk-https%3A%2F%2Fgitee.com%2Fop
 
https://gitee.com/openharmony/telephony_telephony_data













































