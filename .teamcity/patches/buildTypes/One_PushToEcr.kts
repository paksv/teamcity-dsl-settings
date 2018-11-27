package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'One_PushToEcr'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("One_PushToEcr")) {
    requirements {
        add {
            contains("system.agent.name", "Default Agent")
        }
    }
}
