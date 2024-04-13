FROM openjdk:17

WORKDIR /usrapp/bin

ENV PORT 46000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.ieti.bidify.BidifyApplication"]