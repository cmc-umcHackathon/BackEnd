#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop hackathon-ecr || true
docker rm hackathon-ecr || true
docker pull 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackathon-ecr:latest
docker run -d --name hackathon-ecr -p 8080:8080 365499268631.dkr.ecr.ap-northeast-2.amazonaws.com/hackathon-ecr:latest
echo "--------------- 서버 배포 끝 -----------------"