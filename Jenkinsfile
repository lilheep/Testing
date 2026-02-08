pipeline {
    agent any

    tools {
        maven 'mvn'
        jdk 'jdk'
    }

    environment {
        TELEGRAM_CHAT_ID = '786258626'
        BOT_TOKEN = "${env.BOT_TOKEN}" // Use Jenkins credentials
        API_KEY = "${env.API_KEY}" // Use Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean and Compile') {
            steps {
                sh 'mvn clean compile -DapiKey=${API_KEY}'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        sh "mvn test -DapiKey=${API_KEY}"
                    } catch (e) {
                        echo "Tests failed: ${e.message}"
                        // Continue pipeline even if tests fail
                        currentBuild.result = 'UNSTABLE'
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
                def jenkinsUrl = env.JENKINS_URL ?: "http://your-jenkins-server:8080/"
                def allureUrl = "${jenkinsUrl}job/${env.JOB_NAME}/${env.BUILD_NUMBER}/allure"

                def testngFile = 'target/surefire-reports/testng-results.xml'
                def passed = 0, failed = 0, skipped = 0, total = 0

                if (fileExists(testngFile)) {
                    def xml = readFile(testngFile)
                    def matcher = xml =~ /passed="(\d+)"/
                    if (matcher) passed = matcher[0][1] as Integer

                    matcher = xml =~ /failed="(\d+)"/
                    if (matcher) failed = matcher[0][1] as Integer

                    matcher = xml =~ /skipped="(\d+)"/
                    if (matcher) skipped = matcher[0][1] as Integer

                    matcher = xml =~ /total="(\d+)"/
                    if (matcher) total = matcher[0][1] as Integer
                } else {
                    echo "testng-results.xml not found!"
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

                🔗 *Build URL:* ${env.BUILD_URL}
                """

                // Send Telegram notification (with error handling)
                try {
                    sh """
                    curl -s -X POST \
                    https://api.telegram.org/bot${BOT_TOKEN}/sendMessage \
                    -H "Content-Type: application/json" \
                    -d '{
                        "chat_id": "${TELEGRAM_CHAT_ID}",
                        "text": ${groovy.json.JsonOutput.toJson(message)},
                        "parse_mode": "Markdown",
                        "disable_web_page_preview": false
                    }'
                    """
                } catch (e) {
                    echo "Failed to send Telegram notification: ${e.message}"
                }
            }
        }
    }
}