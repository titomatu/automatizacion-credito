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
        sh 'mvn clean package -DskipTests'
   }

   stage('Pruebas Unitarias')
   {
        sh 'mvn test -Dtest=CentralesServiceTest -pl centrales-service'
   }

   stage('Build Contenedores de la Aplicación'){
        //when{branch 'development'}
        sh 'cd solicitud-service/'
        sh 'docker build -t tamatu/solicitud-service:latest .'
   }

   post
   {
        success
        {
            setBuildStatus("Build succeeded", "SUCCESS");
        }

        failure
        {
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