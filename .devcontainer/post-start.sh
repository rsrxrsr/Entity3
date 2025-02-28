echo "* * Start Progres * *"

sudo service postgresql start
sleep 20

echo "* * Spring BOOT * *"

cd entity3
mvn spring-boot:run > ../mvn.log

echo "* * End post-start **"