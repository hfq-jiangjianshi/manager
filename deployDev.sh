#!/usr/bin/env bash
echo "----------------------------------------------------------------------------------------------------------------------"
echo "Start pull new code from repository......"
cd /data/www/hzf_platform_manager
git clone -b dev git@github.com:huifenqi/hzf-platform-manager.git manager/
cd manager

echo "------cd manager----------------------------------------------------------------------------------------------------------------"


echo "-------shutdown---------------------------------------------------------------------------------------------------------------"
curl -X POST localhost:8089/shutdown
echo "-------shutdown success---------------------------------------------------------------------------------------------------------------"


echo "------------------------------------------------------------------------------------------------------------------------"
echo "Start build project......"
mvn clean package
cd target
cp hzf_platform_manager-1.0.jar ../..
cd ../..
nohup java -jar *.jar & tail -f nohup.out
cd ..
rm -rf manager
echo "Start success......"