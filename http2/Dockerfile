FROM nginx:latest

WORKDIR /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
COPY server.crt /etc/ssl/server.crt
COPY server.key /etc/ssl/server.key
COPY default.conf /etc/nginx/conf.d/default.conf
COPY html .