Gradle Environments Plugin
--------------------------



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
