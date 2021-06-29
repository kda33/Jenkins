First add a stage in JenkinsFile to retrieve commit author (and message) from git log into an env. var:
_______________________________________________________________________________________________________
stage('get_commit_details') {
        steps {
            script {
                env.GIT_COMMIT_MSG = sh (script: 'git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
                env.GIT_AUTHOR = sh (script: 'git log -1 --pretty=%cn ${GIT_COMMIT}', returnStdout: true).trim()
            }
        }
    }
_______________________________________________________________________________________________________
Then in post-build action send the Slack message: (BTW I send to two different channels (success/failure) so that the success channel can be muted.
_______________________________________________________________________________________________________
post {
    failure {
        slackSend (channel: 'xyz-build-failure', color: '#FF0000', message: """FAILED:
Job: ${env.JOB_NAME}
Build #${env.BUILD_NUMBER}
Build: ${env.BUILD_URL})
Comitted by: ${env.GIT_AUTHOR}
Last commit message: '${env.GIT_COMMIT_MSG}'""")
    }
    success {
        slackSend (channel: 'xyz-build-success', color: '#00FF00', message: """SUCCESS:
Job: ${env.JOB_NAME}
Build #${env.BUILD_NUMBER}
Build: ${env.BUILD_URL})
Comitted by: ${env.GIT_AUTHOR}
Last commit message: '${env.GIT_COMMIT_MSG}'""")
    }
  }
__________________________________________________________________________________________________________
