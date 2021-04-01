FROM unleashorg/unleash-server:3.17.4-node14-alpine
USER root
RUN apk add --no-cache bash netcat-openbsd
COPY run.sh /unleash/.
RUN chmod ug+x run.sh && chown node run.sh
USER node
ENTRYPOINT ["/unleash/run.sh"]