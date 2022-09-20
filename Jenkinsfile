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
}