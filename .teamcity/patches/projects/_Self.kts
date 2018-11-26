package patches.projects

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    features {
        val feature1 = find<ProjectFeature> {
            feature {
                type = "CloudImage"
                id = "PROJECT_EXT_7"
                param("agentNamePrefix", "")
                param("agent_pool_id", "0")
                param("assignPublicIp", "true")
                param("cluster", "MyCluster")
                param("cpuReservationLimit", "")
                param("launchType", "EC2")
                param("maxInstances", "")
                param("profileId", "awsecs-1")
                param("securityGroups", "")
                param("source-id", "0")
                param("subnets", "")
                param("taskDefinition", "ECS-Agent:1")
                param("taskGroup", "")
            }
        }
        feature1.apply {
            param("taskDefinition", "ECS-Agent:2")
        }
        val feature2 = find<ProjectFeature> {
            feature {
                type = "CloudProfile"
                id = "awsecs-1"
                param("agentPushPreset", "")
                param("aws.access.key.id", "")
                param("aws.credentials.type", "aws.access.keys")
                param("aws.external.id", "TeamCity-server-00043e78-9412-4133-bdbe-50518ad988da")
                param("aws.iam.role.arn", "")
                param("aws.region.name", "us-west-1")
                param("aws.use.default.credential.provider.chain", "true")
                param("cloud-code", "awsecs")
                param("description", "")
                param("enabled", "false")
                param("name", "ECS Agent")
                param("next-hour", "")
                param("profileId", "awsecs-1")
                param("profileInstanceLimit", "2")
                param("profileServerUrl", "")
                param("secure:aws.secret.access.key", "")
                param("system.cloud.profile_id", "awsecs-1")
                param("terminate-idle-time", "30")
                param("total-work-time", "")
            }
        }
        feature2.apply {
            param("enabled", "true")
        }
    }
}
