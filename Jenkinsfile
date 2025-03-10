pipeline {
    agent any

    environment {
        BRANCH = 'master'
        DEPLOY_SERVER = 'ec2-user@34.235.143.19'
        APP_DIR = '/home/ec2-user/restApi'
        GIT_CREDENTIALS_ID = 'github-access-token'  // Jenkins Credentials에서 등록한 ID
    }

    stages {
        stage('Deploy to EC2 and Build') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: GIT_CREDENTIALS_ID, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                        sh '''
                            ssh -i /home/ec2-user/.ssh/id_rsa ${DEPLOY_SERVER} "bash -s" <<EOF
                            echo "[1️] 기존 실행 중인 스프링 부트 서버 종료 시도"
                            pgrep -f 'build/libs/restApi.jar' | xargs kill -9 || true
                            sleep 3
                            ps aux | grep java
                        
                            echo "[2️] 기존 프로젝트 삭제 후 새롭게 클론"
                            rm -rf ${APP_DIR}
                            git clone -b ${BRANCH} https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/supurjsgml/restApi.git ${APP_DIR}
                        
                            echo "[3️] 빌드 실행 시작"
                            cd ${APP_DIR}
                            chmod +x gradlew
                            ./gradlew clean build -x test
                        
                            echo "[4️] 새 JAR 실행"
                            nohup java -jar build/libs/restApi.jar --server.port=8081 --spring.profiles.active=prod > app.log 2>&1 &
                        
                            sleep 5
                        
                            echo "[5️] 실행된 프로세스 확인"
                            ps aux | grep java
                        
                            echo "✅ 배포 완료!"
                            EOF
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful'
        }
        failure {
            echo '❌ Deployment Failed'
        }
    }
}
