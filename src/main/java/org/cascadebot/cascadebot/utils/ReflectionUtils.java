/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.cascadebot.utils;

import com.google.common.reflect.ClassPath;
import lombok.experimental.UtilityClass;
import org.cascadebot.cascadebot.CascadeBot;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ReflectionUtils {

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException Thrown if the loader cannot find the class.
     * @throws IOException            If something goes badly.
     */
    public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        List<ClassPath.ClassInfo> classInfos = ClassPath.from(CascadeBot.class.getClassLoader()).getTopLevelClassesRecursive(packageName).asList();
        return classInfos.stream().map(ClassPath.ClassInfo::load).collect(Collectors.toList());
    }

}
