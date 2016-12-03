#!/usr/bin/env bash
mvn package
container_name=hsweb-demo
image_name=hsweb-demo
server_port=8088
data_dir="${pwd}/target/"
if [ -f "target/hsweb-demo.jar" ]; then
        container_id=$(docker ps -a | grep "${container_name}" | awk '{print $1}')
        if [ "${container_id}" != "" ];then
            docker stop ${container_name}
            docker rm ${container_name}
            docker rmi  ${image_name}
        fi
          docker build -t ${image_name} .
          docker run -d -p ${server_port}:8080 \
          -v "${pwd}"/lib/sigar/libsigar-amd64-linux.so:/usr/lib/libsigar-amd64-linux.so \
          -v /etc/localtime:/etc/localtime \
          -v ${data_dir}/db:/data \
          -v ${data_dir}/upload:/upload \
          --name ${container_name} ${image_name}
    else
        echo "build error!"
        exit -1
fi
