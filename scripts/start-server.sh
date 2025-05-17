#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop hackathon-server || true
docker rm hackathon-server || true
docker pull 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackathon-server:latest
docker run -d --name hackathon-server -p 8080:8080 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackathon-server:latest
echo "--------------- 서버 배포 끝 -----------------"