FROM amazoncorretto:8-alpine-jdk
MAINTAINER REDIT <erfan@redltd.tech>                                                                                   
#RUN apk update && apk add bash                                                                                          
COPY . /root                                                                                                           
WORKDIR /root  
#RUN apk add --no-cache tzdata
#ENV TZ Asia/Dhaka                                                                                      
CMD java -Xmx256m -jar mlajon_referrals_scheduler-0.0.1-SNAPSHOT.jar