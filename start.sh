echo "PID Check..."

CURRENT_PID=$(ps -ef | grep java | grep framework-1.*-RELEASE | awk '{print $2}')

echo "Running PID: {$CURRENT_PID}"

if [ -z "$CURRENT_PID" ] ; then
        echo "Project is not running"
else
        kill -9 $CURRENT_PID
        sleep 10
fi

echo "Deploy Project...."

cd /home/jenkins/jenkins_home/workspace/dejay_backend

JAR_FILE=$(find ./build/libs/ -type f -iname "framework-1.*-RELEASE.jar")
#echo $JAR_FILE

#nohup java -jar -Dspring.profiles.active=dev -Dproperties.jasypt.encryptor.password='dejay1234!@#$' ./build/libs/framework-1.*.*.RELEASE.jar >> ./logs/framework.log 2>&1 &
nohup java -jar -Dspring.profiles.active=dev -Dproperties.jasypt.encryptor.password='dejay1234!@#$' $JAR_FILE >> ./logs/framework.log 2>&1 &

echo "Done"