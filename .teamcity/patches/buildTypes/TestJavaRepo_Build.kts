package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'TestJavaRepo_Build'
in the project with id = 'TestJavaRepo', and delete the patch script.
*/
create(RelativeId("TestJavaRepo"), BuildType({
    id("TestJavaRepo_Build")
    name = "Build"

    vcs {
        root(RelativeId("TestJavaRepo_HttpsGithubComPaksvTestJavaRepoGitRefsHeadsMaster"))
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }

    triggers {
        vcs {
        }
    }
}))

