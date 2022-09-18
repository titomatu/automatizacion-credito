pipeline {
    agent any
    tools {
        maven 'maven-3.8.6'
    }
    stages {
        stage('configuration') {
            steps {
                echo 'BRANCH NAME: ' + env.BRANCH_NAME
                echo sh(returnStdout: true, script: 'env')
            }
        }
        stage('maven') {
            steps {
                if(isUnix()){
                    sh 'mvn -version'
                } else {
                    bat("mvn -version")
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Testing') {
            steps {
                script {
                    sh 'echo "Testing"'
                    sh "cat file.txt"
                }
            }
        }

        stage("build"){
            when {
                branch 'main'
            }

            steps{
                sh 'echo "Build Started"'
            }
        }

        stage("Deploy"){
            when {
                branch 'main'
            }

            steps{
                sh 'echo "Deploying App"'
            }
        }
    }

    post{
        success{
            setBuildStatus("Build succeeded", "SUCCESS");
        }

        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
    }
}

void setBuildStatus(String message, String state) {
    step([
        $class: "GitHubCommitStatusSetter",
        reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://https://github.com/titomatu/automatizacion-credito"],
        contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
        errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
        statusResultSource: [$class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]]]
    ]);
}
