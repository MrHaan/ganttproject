/*
GanttProject is an opensource project management tool.
Copyright (C) 2002-2011 GanttProject Team

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.sourceforge.ganttproject

import biz.ganttproject.core.calendar.GPCalendarCalc
import biz.ganttproject.core.time.TimeUnitStack
import biz.ganttproject.ganttview.TaskFilterManager
import net.sourceforge.ganttproject.document.Document
import net.sourceforge.ganttproject.document.DocumentManager
import net.sourceforge.ganttproject.gui.UIConfiguration
import net.sourceforge.ganttproject.resource.HumanResourceManager
import net.sourceforge.ganttproject.roles.RoleManager
import net.sourceforge.ganttproject.task.TaskManager
import java.io.IOException

/**
 * This interface represents a project as a logical business entity, without any
 * UI (except some configuration options :)
 *
 * @author bard
 */
interface IGanttProject {
  var projectName: String
  var description: String
  var organization: String
  var webLink: String
  val uIConfiguration: UIConfiguration
  val humanResourceManager: HumanResourceManager
  val roleManager: RoleManager
  val taskManager: TaskManager
  val activeCalendar: GPCalendarCalc
  val timeUnitStack: TimeUnitStack
  fun setModified()
  fun close()
  var document: Document
  val documentManager: DocumentManager
  fun addProjectEventListener(listener: ProjectEventListener)
  fun removeProjectEventListener(listener: ProjectEventListener)
  var isModified: Boolean

  @Throws(IOException::class, Document.DocumentException::class)
  fun open(document: Document)
  val resourceCustomPropertyManager: CustomPropertyManager
  val taskCustomColumnManager: CustomPropertyManager
  val baselines: List<GanttPreviousState>

  @Throws(Document.DocumentException::class, IOException::class)
  fun restore(fromDocument: Document)
  val taskFilterManager: TaskFilterManager
}
