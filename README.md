Gradle Environments Plugin
--------------------------
[![Build Status](https://travis-ci.org/marceloemanoel/gradle-environments-plugin.png)](https://travis-ci.org/marceloemanoel/gradle-environments-plugin)


How to Apply the plugin?
------------------------

    buildscript {
      repositories{
        mavenCentral()
      }
      dependencies {
        classpath: "com.github.marceloemanoel:gradle-environments-plugin:0.1"
      }
    }
    
    apply plugin: "environments"
