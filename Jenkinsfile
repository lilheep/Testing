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

        stage('Get Allure') {
        steps {
            allure([
                includePropetries: false,
                jdk: '',
                results: [[path: 'target/allure-results']],
                reportBuildPolicy: 'ALWAYS'
            ])
            }
        }

        stage('Send message TG') {
            steps {
                script {
                    message = 'Тесты успешно прошли'
                    try {
                        sh """curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage"
                        -d chat_id='${env.TELEGRAM_CHAT_ID}'
                        -d text='${message}'
                        """
                    } catch (e) {
                        echo e.message
                    }
                }
            }
        }

        post {
            always {
                echo 'end'
            } failure {
                script {
                    try {
                        message = 'Тесты не прошли'
                        sh """curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage"
                        -d chat_id='${env.TELEGRAM_CHAT_ID}'
                        -d text='${message}'
                        """
                    } catch (e) {
                        echo e.message
                    }
                }
            }
        }
    }
}