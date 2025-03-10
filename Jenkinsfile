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
                                set +e
                        
                                echo "[1] 기존 실행 중인 스프링 부트 서버 종료 시도"
                                pgrep -f 'build/libs/restApi.jar' | xargs kill -9 || true
                            
                                echo "[2] 기존 프로젝트 유지한 채 소스 최신화"
                                cd ${APP_DIR}
                                git reset --hard origin/${BRANCH}  # 충돌 방지
                                git pull origin ${BRANCH}
                            
                                echo "[3] 새 JAR 파일 빌드 (기존 JAR 유지)"
                                cd ${APP_DIR}
                                chmod +x gradlew
                                #./gradlew clean build -x test || { echo "Gradle Build Failed"; exit 1; }
                            
                                echo "[4] 기존 JAR 파일을 이전 버전으로 백업"
                                if [ -f build/libs/restApi.jar ]; then
                                    mv build/libs/restApi.jar build/libs/restApi_prev.jar
                                fi
                            
                                echo "[5] 새로운 JAR 실행"
                                nohup java -jar build/libs/restApi.jar --server.port=8081 --spring.profiles.active=prod > app.log 2>&1 & disown
                        
                                echo "[6] 실행된 프로세스 확인"
                                ps aux | grep java
                            
                                echo "[7] nohup 로그 확인"
                                tail -n 20 app.log
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
