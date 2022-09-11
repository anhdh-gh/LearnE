FROM asia.gcr.io/om-hkt21-team23/openjdk:11.0.5-jre-slim
VOLUME /tmp
ENV TZ="Asia/Ho_Chi_Minh"
RUN apt-get update \
    && apt-get install -y unzip curl
COPY build/booking-application-*.jar /build/booking-application.jar
RUN mkdir -p /app/lib /app/META-INF /target/dependency \
    && cd /target/dependency; unzip ../../build/booking-application.jar \
    && cp -r /target/dependency/BOOT-INF/lib/* /app/lib \
    && cp -r /target/dependency/META-INF/* /app/META-INF \
    && cp -r /target/dependency/BOOT-INF/classes/* /app \
    && rm -rf /target/dependency
CMD ["/bin/bash", "-c", "update-ca-certificates; java $JAVA_OPT -cp app:app/lib/* com.onemount.BookingApplication"]