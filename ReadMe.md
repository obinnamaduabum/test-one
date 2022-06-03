
#Kindly use the below commands to run the application 

#for limit 100 and hourly
mvn spring-boot:run -Dspring-boot.run.arguments="--params.accessFile=user_access.txt --params.start=2022-01-01.13:00:00 --params.limit=100 --params.duration=hourly"

#for limit 250 and daily
mvn spring-boot:run -Dspring-boot.run.arguments="--params.accessFile=user_access.txt --params.start=2022-01-01.13:00:00 --params.limit=250 --params.duration=daily"