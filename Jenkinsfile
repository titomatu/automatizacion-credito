node {

    stage('Initialize')
    {
        def dockerHome = tool 'docker'
        def mavenHome  = tool 'maven-3.8.6'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout')
    {
        checkout scm
    }

    stage('Verificar Docker')
    {
        sh 'docker info'
    }
}