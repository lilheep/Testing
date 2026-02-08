pipeline {
    agent any

    tools {
        maven 'mvn'
        jdk 'jdk'
    }

    environment {
        TELEGRAM_CHAT_ID = '786258626'
        BOT_TOKEN = "${env.BOT_TOKEN}"
        API_KEY = "${env.apiKey}"
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
    }

    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']],
                reportBuildPolicy: 'ALWAYS'
            ])

            script {
                def allureUrl = "${env.JENKINS_URL}job/${env.JOB_NAME}/${env.BUILD_NUMBER}/allure"

                def testngFile = 'target/surefire-reports/testng-results.xml'
                def passed = 0, failed = 0, skipped = 0, total = 0

                if (fileExists(testngFile)) {
                    def xml = readFile(testngFile)
                    passed = (xml =~ /passed="(\d+)"/)[0][1] as Integer
                    failed = (xml =~ /failed="(\d+)"/)[0][1] as Integer
                    skipped = (xml =~ /skipped="(\d+)"/)[0][1] as Integer
                    total = (xml =~ /total="(\d+)"/)[0][1] as Integer
                }

                def message = """
                🚀 *Test Execution Report*

📊 *Results:*
✅ Passed: ${passed}
❌ Failed: ${failed}
⏭ Skipped: ${skipped}
📈 Total: ${total}

📋 *Allure Report:*
${allureUrl}
                """

                sh """
                curl -s -X POST \
                https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage \
                -d chat_id=${env.TELEGRAM_CHAT_ID} \
                -d text="${message}" \
                -d parse_mode="Markdown"
                """
            }
        }
    }
}