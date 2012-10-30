package com.github.marceloemanoel.gradle.environments

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SelectEnvironmentTask extends DefaultTask {

    String group = "Environments"
    String description = "Choose wich environment should be used"

    @TaskAction
    def selectEnvironment() {
        logging.level = "INFO"

        if(!project.hasProperty("environment")) {
            ant.input(addproperty: "environment",
                    defaultvalue: defaultEnv(),
                    validargs: discoverEnvironments(),
                    message: "Select the environment: "
                    )
            if(ant.environment == ""){
                throw new EnvironmentNotSupportedException()
            }
            project.ext.environment = ant.environment
        }

        logger.info("Environment is set to ${project.environment}")
    }

    def defaultEnv() {
        withEnvironment {
            def defaultEnv = it.grep { env -> !env.listFiles({file -> file.name == ".default"} as FileFilter).toList().empty }
            return (defaultEnv.empty ? "" : defaultEnv.first().name)
        }
    }

    def withEnvironment(Closure closure) {
        File envDir = project.file("src/main/environment")
        if(envDir.exists() && envDir.directory){
            def environments = envDir.listFiles({file -> file.directory} as FileFilter).toList()
            if(environments.empty){
                logger.info("No environment found!")
            }
            else closure environments
        }
        else throw new RuntimeException("No environment directory found!")
    }

    def discoverEnvironments() {
        withEnvironment { it.collect {it.name}.join(",") }
    }
}