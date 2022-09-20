node {
    stage('Initialize')
    {
        def dockerHome = tool 'maven-3.8.6'
        def mavenHome  = tool 'docker-latest'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout') 
    {
        checkout scm
    }

   stage('Build')
   {
        steps {
            sh 'mvn clean package -DskipTests'
        }
   }

   stage('Pruebas Unitarias')
   {
        steps {
            sh 'mvn test -Dtest=CentralesServiceTest -pl centrales-service'
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