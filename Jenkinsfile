pipeline {
    agent any

   environment {
        MYSQL_URL='172.23.238.153'
    }

   stages {
        stage('Build') {
            steps {
		sh 'printenv'
                sh 'mvn clean package'
            }
        }
    }
}
