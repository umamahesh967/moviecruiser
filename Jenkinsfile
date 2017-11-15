pipeline {
    agent any

   environment {
        MYSQL_URL='172.23.238.168'
        MYSQL_USER='root'
	MYSQL_PASS='root123'
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
