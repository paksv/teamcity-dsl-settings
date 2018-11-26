package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'AmazonSesPlugin_Build'
in the project with id = 'AmazonSesPlugin', and delete the patch script.
*/
create(RelativeId("AmazonSesPlugin"), BuildType({
    id("AmazonSesPlugin_Build")
    name = "Build"

    vcs {
        root(RelativeId("AmazonSesPlugin_HttpsGithubComJetBrainsTeamCitySESPluginRefsHeadsMaster"))
    }

    triggers {
        vcs {
        }
    }
}))

