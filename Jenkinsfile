pipeline {

    agent any

    options {
        disableConcurrentBuilds()
        skipDefaultCheckout(true)
    }

    tools {
        maven 'Maven-3.9.9'
        jdk 'JDK-21'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh 'docker compose up -d --wait'
            }
        }

        stage('Run Tests') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'saucedemo-login',
                        usernameVariable: 'SAUCE_USERNAME',
                        passwordVariable: 'SAUCE_PASSWORD'
                    )
                ]) {
                    script {
                        if (env.JOB_BASE_NAME == 'SauceDemo-Smoke') {
                            sh 'mvn clean test -PSmoke -Dexecution=remote -Dbrowser=chrome -Dheadless=true'
                        } else if (env.JOB_BASE_NAME == 'SauceDemo-Regression') {
                            sh 'mvn clean test -PRegression -Dexecution=remote -Dbrowser=chrome -Dheadless=true'
                        } else {
                            error "Unknown Jenkins job: ${env.JOB_BASE_NAME}"
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'target/allure-results']]
        }

        cleanup {
            sh 'docker compose down'
        }
    }
}
