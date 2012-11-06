package com.github.marceloemanoel.gradle.environments;

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class EnvironmentsPluginTest {
    
    def Project project
    
    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: "environments"
    }

    @Test
    void applyAddThreeNewTasksToTheProject() {
        def pluginTasks = project.tasks.matching {it.group == "Environments"}
        assertEquals(3, pluginTasks.size())
    }
    
    @Test
    void applyAddsASelectEnvironmentTaskToTheProject(){
        ensureExistenceOfTask(withName: "selectEnvironment", ofType: SelectEnvironmentTask)
    }
    
    @Test
    void applyAddsALoadEnvironmentsTaskToTheProject(){
        ensureExistenceOfTask(withName: "loadEnvironmentConfiguration", ofType: LoadEnvironmentConfigTask)
    }
    
    @Test
    void applyAddsACopyEnvironmentConfigurationTaskToTheProject(){
        ensureExistenceOfTask(withName: "copyEnvironmentFiles", ofType: CopyEnvironmentConfigurationTask)
    }
    
    def ensureExistenceOfTask(task){
        Task taskInstance = project.tasks.getByName(task.withName)
        assertNotNull(taskInstance)
        assertTrue(task.ofType.isInstance(taskInstance))
    }

}
