package com.github.marceloemanoel.gradle.environments

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy;

class EnvironmentsPlugins implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task("selectEnvironment", type: SelectEnvironmentTask)    
        project.task("loadEnvironmentConfiguration", type: LoadEnvironmentConfigTask)    
        project.task("copyEnvironmentFiles", type:Copy, dependsOn: "loadEnvironmentConfiguration"){
            group = "Environments"
            description = "Copy all the files from the selected environment to the main resources dir"
            doLast{
                println "src/main/environment/${project.ext.environment}"
                copy { 
                    from "src/main/environment/${project.ext.environment}"
                    into "src/main/resources"
                    include "**/*"
                    exclude ".default"
                }
            }
        }    
    }

}
