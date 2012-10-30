package com.github.marceloemanoel.gradle.environments

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class LoadEnvironmentConfigTask extends DefaultTask {

    String group = "Environments"
    String description = "Reads information from file env-properties.groovy and stores in the environment property"
    
    LoadEnvironmentConfigTask(){
        dependsOn("selectEnvironment")
    }
    
    def File configFile() {
        project.file('env-properties.groovy')
    }
    
    @TaskAction
    def loadEnvironmentConfig() {
        project.ext.config = new ConfigSlurper(project.environment).parse(configFile().toURL())
        logger.info("Configuration values: ")
        logger.info(project.ext.config.toString())
    }

}