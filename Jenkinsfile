pipeline {
    agent any

    tools {
        maven 'mvn'
        jdk 'jdk'
    }

    environment {
        TELEGRAM_CHAT_ID = '786258626'
        BOT_TOKEN = credentials('BOT_TOKEN')
        API_KEY = credentials('API_KEY')
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
                        sh "mvn clean test -DapiKey=${API_KEY}"
                    } catch (e) {
                        echo e.message
                    }
                }
            }
        }

        stage('Get Allure') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']],
                    reportBuildPolicy: 'ALWAYS'
                ])
            }
        }

        stage('Parsing allure') {
            steps {
                script {
                    def json = readFile 'allure-report/widgets/summary.json'
                    def result = new groovy.json.JsonSlurper().parseText(json)
                    def stats = result.statistic

                    env.PASSED = stats.passed.toString()
                    env.FAILED = stats.failed.toString()
                    env.SKIPPED = stats.skipped.toString()
                    env.BROKEN = stats.broken.toString()
                    env.UNKNOWN = stats.unknown.toString()
                    env.TOTAL = stats.total.toString()
                }
            }
        }

        stage('Send message TG') {
            steps {
                script {
                    def allureUrl = "${env.BUILD_URL}allure/"
                    def message = """
Results tests:
PASSED: ${env.PASSED}
FAILED: ${env.FAILED}
SKIPPED: ${env.SKIPPED}
BROKEN: ${env.BROKEN}
UNKNOWN: ${env.UNKNOWN}
**TOTAL**: ${env.TOTAL}
ALLURE: ${allureUrl}
                    """
                    try {
                        sh """curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
                        -d chat_id='${env.TELEGRAM_CHAT_ID}' \
                        -d text='${message}'"""
                    } catch (e) {
                        echo e.message
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Succefuly send allure-report'
        }
        failure {
            script {
                try {
                    def message = 'Тесты не прошли'
                    sh """curl -s -X POST "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage" \
                    -d chat_id='${env.TELEGRAM_CHAT_ID}' \
                    -d text='${message}'"""
                } catch (e) {
                    echo e.message
                }
            }
        }
    }
}