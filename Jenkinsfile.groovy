node {
    def WORKSPACE ="/vagrant_data"
    def dockerImageTag="todobackver2${env.BUILD_NUMBER}"

    try{

    }
    catch(e) {
        stage('Clone repository'){
            git url: 'https://gitlab.amitech.vn/longnt/TodoBackVer2',
                branch:'main'
        }

        stage('Build docker'){
            dockerImage=docker.build("todobackver2:${env.BUILD_NUMBER}")
        }

        stage('Deploy docker'){
            echo "  Deploying docker"
            sh "docker stop todobackver2 || true && docker rm todobackver2 || true"
            sh "docker run --name todobackver2 -d -p 5001:5000 todobackver2:${env.BUILD_NUMBER}"
        }
    }
}