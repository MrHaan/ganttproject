/*
Copyright 2022 BarD Software s.r.o., Anastasiia Postnikova

This file is part of GanttProject, an open-source project management tool.

GanttProject is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

GanttProject is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with GanttProject.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.sourceforge.ganttproject.storage

import net.sourceforge.ganttproject.storage.ProjectDatabase.*
import net.sourceforge.ganttproject.task.Task
import net.sourceforge.ganttproject.task.dependency.TaskDependency

/**
 * ProjectDatabase implementation with lazy initialization. After each shutdown, a new database is created.
 *
 * @param databaseFactory - factory for generating a project state database.
 */
class LazyProjectDatabaseProxy(private val databaseFactory: () -> ProjectDatabase): ProjectDatabase {
  private var lazyProjectDatabase: ProjectDatabase? = null

  private fun isInitialized(): Boolean = lazyProjectDatabase != null

  private fun getDatabase(): ProjectDatabase {
    return lazyProjectDatabase ?: databaseFactory().also { it.init(); lazyProjectDatabase = it }
  }

  private fun resetDatabase() { lazyProjectDatabase = null  }

  override fun init() {
    // Lazy initialization.
  }

  override fun createTaskUpdateBuilder(task: Task): TaskUpdateBuilder {
    return getDatabase().createTaskUpdateBuilder(task)
  }

  override fun insertTask(task: Task) {
    getDatabase().insertTask(task)
  }

  override fun insertTaskDependency(taskDependency: TaskDependency) {
    getDatabase().insertTaskDependency(taskDependency)
  }

  override fun shutdown() {
    if (isInitialized()) {
      getDatabase().shutdown()
      resetDatabase()
    }
  }

  override fun fetchLogRecords(startId: Int, limit: Int): List<LogRecord> {
    return getDatabase().fetchLogRecords(startId, limit)
  }
}
