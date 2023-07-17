echo "PID Check..." 

CURRENT_PID=$(ps -ef | grep java | grep framework-0.0.1-SNAPSHOT* | awk '{print $2}') 

echo "Running PID: {$CURRENT_PID}" 

if [ -z "$CURRENT_PID" ] ; then 
	echo "Project is not running"
else 
	kill -9 $CURRENT_PID 
	sleep 10 
fi 

echo "Deploy Project...." 

nohup java -jar -Dspring.profiles.active=dev -Dproperties.jasypt.encryptor.password='dejay1234!@#$' /var/jenkins_home/workspace/dejay_backend/build/libs/framework-0.0.1-SNAPSHOT.jar >> /var/jenkins_home/workspace/dejay_backend/logs/framework.log 2>&1 &

echo "Done"