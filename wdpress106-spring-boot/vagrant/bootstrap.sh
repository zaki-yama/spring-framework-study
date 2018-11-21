#!/bin/bash -xv
# -*- mode: bash; coding: utf-8-unix  -*-

yum update -y
yum install -y java-1.8.0-openjdk
java -version

echo "== install httpd =="
yum install -y httpd
chkconfig httpd on

sed -i -e 's/#ServerName www.example.com:80/ServerName spring-boot/g' \
    /etc/httpd/conf/httpd.conf
cat < /etc/httpd/conf/httpd.conf |
    grep -v "^#" |
    grep "ServerName"

cat <<EOF > /etc/httpd/conf.d/todo.conf
AllowEncodedSlashes NoDecode
ProxyRequests     Off
<Location /todo/ >
  ProxyPass        http://localhost:8081/todo/ nocanon
  ProxyPassReverse http://localhost:8081/todo/
</Location>
<Proxy http://localhost:8081/todo/*>
  Order deny,allow
  Allow from all
</Proxy>
EOF

cat <<EOF > /etc/httpd/conf.d/qiita.conf
AllowEncodedSlashes NoDecode
ProxyRequests     Off
<Location /qiita/ >
  ProxyPass        http://localhost:8082/qiita/ nocanon
  ProxyPassReverse http://localhost:8082/qiita/
</Location>
<Proxy http://localhost:8082/qiita/*>
  Order deny,allow
  Allow from all
</Proxy>
EOF
service httpd restart

APP_NAMES=()
APP_NAMES+=("todo")
APP_NAMES+=("qiita")

# inid.d setting
for app_name in "${APP_NAMES[@]}"; do
    ln -sf /vagrant/"${app_name}"-*.jar \
       /etc/init.d/"${app_name}"
    chkconfig --add "${app_name}"
    chkconfig "${app_name}" on
    service "${app_name}" start
    service "${app_name}" status
done

# logrotate setting
for app_name in "${APP_NAMES[@]}"; do
    cat <<EOF > /etc/logrotate.d/"${app_name}"
/var/log/${app_name}.log {
    rotate 10
    daily
    dateext
    missingok
    notifempty
    sharedscripts
    postrotate
        /sbin/service ${app_name} restart \
          > /dev/null 2>/dev/null || true
    endscript
}
EOF
done

# disable iptables
iptables -F

# disable SELinux
setsebool -P httpd_can_network_connect=1
getenforce

