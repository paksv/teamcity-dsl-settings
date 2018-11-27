import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.1"

project {
    description = "Contains all other projects"

    features {
        feature {
            id = "PROJECT_EXT_1"
            type = "ReportTab"
            param("startPage", "coverage.zip!index.html")
            param("title", "Code Coverage")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_2"
            type = "OAuthProvider"
            param("clientId", "ef4752c5d254ccb6520d")
            param("defaultTokenScope", "public_repo,repo,repo:status,write:repo_hook")
            param("secure:clientSecret", "credentialsJSON:f4e83319-24d5-4c63-b454-773eaf15c48a")
            param("displayName", "GitHub.com")
            param("gitHubUrl", "https://github.com/")
            param("providerType", "GitHub")
        }
        feature {
            id = "PROJECT_EXT_3"
            type = "CloudImage"
            param("key-pair-name", "spak-test")
            param("use-spot-instances", "false")
            param("security-group-ids", "sg-07d361b36a7075cbb,")
            param("profileId", "amazon-1")
            param("agent_pool_id", "0")
            param("subnet-id", "subnet-0567abdb42b8d0dd1")
            param("ebs-optimized", "false")
            param("instance-type", "c5d.large")
            param("amazon-id", "ami-0d314c337e9bf7350")
            param("source-id", "ami-0d314c337e9bf7350")
        }
        feature {
            id = "PROJECT_EXT_7"
            type = "CloudImage"
            param("cpuReservationLimit", "")
            param("cluster", "default")
            param("agentNamePrefix", "")
            param("agent_pool_id", "0")
            param("source-id", "0")
            param("taskGroup", "")
            param("assignPublicIp", "true")
            param("profileId", "awsecs-1")
            param("securityGroups", "")
            param("subnets", "")
            param("taskDefinition", "ECS-Agent:3")
            param("maxInstances", "")
            param("launchType", "EC2")
        }
        feature {
            id = "PROJECT_EXT_8"
            type = "OAuthProvider"
            param("aws.use.default.credential.provider.chain", "true")
            param("displayName", "Amazon ECR")
            param("aws.credentials.type", "aws.access.keys")
            param("aws.region.name", "us-west-1")
            param("registryId", "913206223978")
            param("providerType", "AmazonDocker")
        }
        feature {
            id = "amazon-1"
            type = "CloudProfile"
            param("profileServerUrl", "")
            param("secure:access-id", "")
            param("system.cloud.profile_id", "amazon-1")
            param("total-work-time", "")
            param("description", "")
            param("user-script", "")
            param("cloud-code", "amazon")
            param("enabled", "true")
            param("max-running-instances", "")
            param("agentPushPreset", "")
            param("profileId", "amazon-1")
            param("use-instance-iam-role", "true")
            param("name", "EC2")
            param("next-hour", "")
            param("secure:secret-key", "")
            param("region", "us-west-1")
            param("terminate-idle-time", "30")
            param("not-checked", "")
        }
        feature {
            id = "awsecs-1"
            type = "CloudProfile"
            param("secure:aws.secret.access.key", "")
            param("aws.external.id", "TeamCity-server-00043e78-9412-4133-bdbe-50518ad988da")
            param("profileServerUrl", "")
            param("system.cloud.profile_id", "awsecs-1")
            param("total-work-time", "")
            param("aws.region.name", "us-west-1")
            param("description", "")
            param("cloud-code", "awsecs")
            param("enabled", "true")
            param("aws.use.default.credential.provider.chain", "true")
            param("agentPushPreset", "")
            param("profileInstanceLimit", "2")
            param("profileId", "awsecs-1")
            param("name", "ECS Agent")
            param("aws.access.key.id", "")
            param("aws.credentials.type", "aws.access.keys")
            param("next-hour", "")
            param("terminate-idle-time", "30")
            param("aws.iam.role.arn", "")
        }
    }

    cleanup {
        preventDependencyCleanup = false
    }

    subProject(TeamCityAmazonEcsPlugin)
    subProject(One)
    subProject(AmazonSesPlugin)
}


object AmazonSesPlugin : Project({
    name = "Amazon SES Plugin"
    archived = true

    vcsRoot(AmazonSesPlugin_HttpsGithubComJetBrainsTeamCitySESPluginRefsHeadsMaster)

    buildType(AmazonSesPlugin_Build)
})

object AmazonSesPlugin_Build : BuildType({
    name = "Build"
    paused = true

    vcs {
        root(AmazonSesPlugin_HttpsGithubComJetBrainsTeamCitySESPluginRefsHeadsMaster)
    }

    steps {
        maven {
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            param("teamcity.tool.jacoco", "%teamcity.tool.jacoco.DEFAULT%")
        }
    }

    triggers {
        vcs {
        }
    }
})

object AmazonSesPlugin_HttpsGithubComJetBrainsTeamCitySESPluginRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/JetBrains/TeamCity.SESPlugin#refs/heads/master"
    url = "https://github.com/JetBrains/TeamCity.SESPlugin"
    authMethod = password {
        userName = "paksv"
        password = "credentialsJSON:845babb9-cf28-4149-bc82-7fc98e52c0df"
    }
})


object One : Project({
    name = "One"

    vcsRoot(One_TestJavaRepo)
    vcsRoot(One_DockerTutorial)
    vcsRoot(One_HttpsGithubComPaksvTestJavaLibRepoGit)

    buildType(One_Lib)
    buildType(One_TestPackage)
    buildType(One_PushToEcr)

    features {
        feature {
            id = "PROJECT_EXT_4"
            type = "storage_settings"
            param("aws.service.endpoint", "https://s3.us-west-1.amazonaws.com")
            param("aws.use.default.credential.provider.chain", "true")
            param("aws.external.id", "TeamCity-server-00043e78-9412-4133-bdbe-50518ad988da")
            param("aws.environment", "custom")
            param("storage.s3.bucket.name", "tc-reinvent-demo")
            param("storage.type", "S3_storage")
            param("aws.credentials.type", "aws.access.keys")
            param("aws.region.name", "us-west-1")
            param("storage.s3.upload.presignedUrl.enabled", "true")
        }
        feature {
            id = "PROJECT_EXT_5"
            type = "active_storage"
            param("active.storage.feature.id", "PROJECT_EXT_4")
        }
    }
})

object One_Lib : BuildType({
    name = "Lib"
    description = "Library"

    artifactRules = "target/shared-1.0-SNAPSHOT.jar"

    vcs {
        root(One_HttpsGithubComPaksvTestJavaLibRepoGit)
    }

    steps {
        maven {
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            param("teamcity.tool.jacoco", "%teamcity.tool.jacoco.DEFAULT%")
        }
    }
})

object One_PushToEcr : BuildType({
    name = "Push to ECR"

    params {
        param("tag_ecr", "913206223978.dkr.ecr.us-west-1.amazonaws.com/mytestrepo:%build.number%")
    }

    vcs {
        root(One_DockerTutorial)
    }

    steps {
        dockerCommand {
            name = "Build"
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                namesAndTags = "%tag_ecr%"
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            name = "Push"
            commandType = push {
                namesAndTags = "%tag_ecr%"
            }
        }
    }

    features {
        dockerSupport {
            cleanupPushedImages = true
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_8"
            }
        }
    }

    requirements {
        contains("system.agent.name", "Default Agent")
    }
})

object One_TestPackage : BuildType({
    name = "Test Package"

    vcs {
        root(One_TestJavaRepo)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }

    dependencies {
        dependency(One_Lib) {
            snapshot {
            }

            artifacts {
                artifactRules = "shared-1.0-SNAPSHOT.jar => lib/"
            }
        }
    }
})

object One_DockerTutorial : GitVcsRoot({
    name = "Docker Tutorial"
    url = "https://github.com/paksv/docker_tutorial"
})

object One_HttpsGithubComPaksvTestJavaLibRepoGit : GitVcsRoot({
    name = "Java LIb"
    url = "https://github.com/paksv/test-java-lib-repo.git"
})

object One_TestJavaRepo : GitVcsRoot({
    name = "Test Java Repo"
    url = "https://github.com/paksv/test-java-repo.git"
})


object TeamCityAmazonEcsPlugin : Project({
    name = "TeamCity Amazon Ecs Plugin"

    vcsRoot(TeamcityAmazonEcsPlugin_HttpsGithubComJetbrainsTeamcityAmazonEcsPluginGitRefsHea)

    buildType(TeamcityAmazonEcsPlugin_Build)
})

object TeamcityAmazonEcsPlugin_Build : BuildType({
    name = "Build"

    params {
        param("teamcity-version", "2018.1")
    }

    vcs {
        root(TeamcityAmazonEcsPlugin_HttpsGithubComJetbrainsTeamcityAmazonEcsPluginGitRefsHea)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleParams = "-PteamcityVersion=%teamcity-version%"
            param("teamcity.tool.jacoco", "%teamcity.tool.jacoco.DEFAULT%")
        }
    }

    triggers {
        vcs {
        }
    }
})

object TeamcityAmazonEcsPlugin_HttpsGithubComJetbrainsTeamcityAmazonEcsPluginGitRefsHea : GitVcsRoot({
    name = "https://github.com/jetbrains/teamcity-amazon-ecs-plugin.git#refs/heads/master"
    url = "https://github.com/jetbrains/teamcity-amazon-ecs-plugin.git"
})
