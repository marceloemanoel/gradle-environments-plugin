Gradle Environments Plugin [![Build Status](https://travis-ci.org/marceloemanoel/gradle-environments-plugin.png)](https://travis-ci.org/marceloemanoel/gradle-environments-plugin)
--------------------------
This plugin aims to help the development of projects using more than one configurations. 
Projects that have specific configurations for development, staging and production for instance, 
but not limited to it! It integrates seamlessly with gradle life cycle just put the files you want 
to be specific to a given environment inside its correspondent directory and voilá! You're all set
and ready to go!


How to Apply the plugin?
------------------------

```groovy
    buildscript {
      repositories{
        mavenCentral()
      }
      dependencies {
        classpath: "com.github.marceloemanoel:gradle-environments-plugin:0.1"
      }
    }
    
    apply plugin: "environments"
```

Configuration:
--------------

Just create a directory for each environment you need at `src/main/environment` that should be
enough to let you select witch environment you need. 

```shell
    ├── src
    │   ├── main
    │   │   ├── environment
    │   │   │   ├── development
    │   │   │   │   ├── database.properties
    │   │   │   │   └── log4j.properties
    │   │   │   ├── staging
    │   │   │   │   ├── database.properties
    │   │   │   │   ├── log4j.properties
    │   │   │   └── production
    │   │   │   │   ├── database.properties
    │   │   │   │   ├── log4j.properties
```

Just by using this structure, the plugin knows that:
 * you have 3 environments 
 * that when you need to generate resources, such as a Jar, War, Zip or any other thing that uses the task `:processResources`,
      it will ask wich environment you want to generate the resource and will copy the correct files to your 
     `src/main/resources`.

Tasks
-----

The plugin adds a new group of tasks `Environment Tasks` wich is composed by the following tasks:

selectEnvironment
----------------- 
Choose wich environment among the directory structure created at `src/main/environment` should be used.
If any of the environment directory has a file named .default, it will be treated as the default choice
if you don't select any of the given ones. 

<table>
    <thead>
        <tr><th>Depends On</th><tr>
    </thead>
    <tbody>
        <tr><td>None</td></tr>
    </tbody>
</table>

copyEnvironmentFiles
-------------------- 
Copy all the files from the selected environment to the main resources dir

<table>
    <thead>
        <tr><th>Depends On</th><tr>
    </thead>
    <tbody>
        <tr><td>:selectEnvironment</td></tr>
    </tbody>
</table>


loadEnvironmentConfiguration
---------------------------- 
Reads information from file env-properties.groovy and stores in the environment property

<table>
    <thead>
        <tr><th>Depends On</th><tr>
    </thead>
    <tbody>
        <tr><td>:selectEnvironment</td></tr>
    </tbody>
</table>

<table>
    <thead>
        <tr>
            <th>Requires</th>
            <th>Default Value</th>
        <tr>
    </thead>
    <tbody>
        <tr>
            <td>/env-properties.groovy</td>
            <td>None</td>
        </tr>
    </tbody>
</table>


