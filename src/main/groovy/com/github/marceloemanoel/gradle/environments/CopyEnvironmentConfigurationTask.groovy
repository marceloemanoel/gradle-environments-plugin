package com.github.marceloemanoel.gradle.environments

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskAction


class CopyEnvironmentConfigurationTask extends DefaultTask {

    String group = "Environments"
    String description = "Copy all the files from the selected environment to the main resources dir"

    CopyEnvironmentConfigurationTask(){
        dependsOn("loadEnvironmentConfiguration")
        project.task("proccessResources").dependsOn this 
    }

    @TaskAction
    def copyFiles(){
        project.copy {
            from "src/main/environment/${project.environment}"
            into "src/main/resources"
            include "**/*"
            exclude ".default"
        }
    }
}