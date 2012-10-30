package com.github.marceloemanoel.gradle.environments

class EnvironmentNotSupportedException extends RuntimeException {

    EnvironmentNotSupportedException(){
        super("The selected environment is not supported.")
    }    
    
}