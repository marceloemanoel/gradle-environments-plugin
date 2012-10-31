package com.github.marceloemanoel.gradle.environments

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy;

class EnvironmentsPlugins implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task("selectEnvironment", type: SelectEnvironmentTask)    
        project.task("loadEnvironmentConfiguration", type: LoadEnvironmentConfigTask)    
        project.task("copyEnvironmentFiles", type: CopyEnvironmentConfigurationTask)
    }

}
