package com.jojo.design.common_base.utils

import android.os.Environment

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.Enumeration
import java.util.Properties

/**
 * Created by SmileXie on 2017/7/4.
 */

class BuildProperties @Throws(IOException::class)
private constructor() {

    private val properties: Properties

    val isEmpty: Boolean
        get() = properties.isEmpty

    init {
        properties = Properties()
        properties.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
    }

    fun containsKey(key: Any): Boolean {
        return properties.containsKey(key)
    }

    fun containsValue(value: Any): Boolean {
        return properties.containsValue(value)
    }

    fun entrySet(): MutableSet<MutableMap.MutableEntry<Any, Any>> {
        return properties.entries
    }

    fun getProperty(name: String): String {
        return properties.getProperty(name)
    }

    fun getProperty(name: String, defaultValue: String?): String {
        return properties.getProperty(name, defaultValue)
    }

    fun keys(): Enumeration<Any> {
        return properties.keys()
    }

    fun keySet(): Set<Any> {
        return properties.keys
    }

    fun size(): Int {
        return properties.size
    }

    fun values(): Collection<Any> {
        return properties.values
    }

    companion object {

        @Throws(IOException::class)
        fun newInstance(): BuildProperties {
            return BuildProperties()
        }
    }
}
