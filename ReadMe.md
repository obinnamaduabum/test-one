mvn spring-boot:run -Dspring-boot.run.arguments="--params.accessFile=user_access.txt --params.start=2022-01-01.13:00:00 --params.limit=100 --params.duration=hourly"

mvn spring-boot:run -Dspring-boot.run.arguments="--params.accessFile=user_access.txt --params.start=2022-01-01.13:00:00 --params.limit=250 --params.duration=daily"