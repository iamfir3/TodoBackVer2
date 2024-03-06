node {
    def WORKSPACE ="/vagrant_data"
    def dockerImageTag="todobackver2${env.BUILD_NUMBER}"

    try{
        stage('Clone repository'){
            git url: 'https://gitlab.amitech.vn/longnt/TodoBackVer2',
                branch:'main'
        }

        stage('Run MySql'){
            script {
            sh "docker pull mysql"
            sh "docker stop mysql-container || true && docker rm mysql-container || true"
            sh "docker run  -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=374gpbttt -e MYSQL_DATABASE=todobackver2 -d  mysql"
        }
        }

        stage('Build docker'){
            dockerImage=docker.build("todobackver2:${env.BUILD_NUMBER}")
        }

        stage('Deploy docker'){
            script{
                echo "  Deploying docker"
                sh "docker stop todobackver2 || true && docker rm todobackver2 || true"
                sh "docker run --name todobackver2 -d -p 5001:5000 todobackver2:${env.BUILD_NUMBER}"
            }
        }

    }
    catch(e) {
    }
}
