#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop hackthon-ecr || true
docker rm hackthon-ecr || true
docker pull 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackthon-ecr:latest
docker run -d --name hackthon-ecr -p 8080:8080 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackthon-ecr:latest
echo "--------------- 서버 배포 끝 -----------------"