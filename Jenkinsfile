pipeline {
    agent any

    tools {
        maven 'mvn'
        jdk 'jdk'
    }

    environment {
        TELEGRAM_CHAT_ID = '786258626'
        BOT_TOKEN = "${env.BOT_TOKEN}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/lilheep/Testing'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        sh "mvn clean test -DapiKey=${env.API_KEY}"
                    } catch (e) {
                        echo e.message
                    }
                }
            }
        }
    }
}