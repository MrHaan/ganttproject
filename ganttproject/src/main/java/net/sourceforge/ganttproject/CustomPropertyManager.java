/*
Copyright 2003-2012 Dmitry Barashev, GanttProject Team

This file is part of GanttProject, an opensource project management tool.

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
package net.sourceforge.ganttproject;

import java.util.List;
import java.util.Map;

public interface CustomPropertyManager {
  List<CustomPropertyDefinition> getDefinitions();

  CustomPropertyDefinition createDefinition(String id, String typeAsString, String name, String defaultValueAsString);

  CustomPropertyDefinition createDefinition(String typeAsString, String colName, String defValue);
  CustomPropertyDefinition createDefinition(CustomPropertyClass propertyClass, String colName, String defValue);
  CustomPropertyDefinition getCustomPropertyDefinition(String id);

  void deleteDefinition(CustomPropertyDefinition def);

  Map<CustomPropertyDefinition, CustomPropertyDefinition> importData(CustomPropertyManager source);

  void addListener(CustomPropertyListener listener);

  void reset();

}
