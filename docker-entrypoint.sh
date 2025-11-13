#!/bin/bash

apt-get -y install libgtk-4-1
apt-get -y install xvfb
apt-get -y install libxtst-dev

Xvfb :100 -ac &
export DISPLAY=:100

cd /test/corvus.product_1.0.0
./eclipse

exit 0
