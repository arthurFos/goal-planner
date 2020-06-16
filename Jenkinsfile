pipeline {
    agent any
    tools { 
        maven "maven3.6.2" 
        jdk "Java12" 
    }
    stages {
        stage ("Build") {
            steps {
                sh "mvn --version";
                sh "mvn clean";
                sh "mvn compile";
            }
        }
        
        stage ("test") {
            steps {
                sh "mvn test"
            }
        }
    }
}
