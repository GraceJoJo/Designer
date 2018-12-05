package com.jojo.design.common_base.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.IntRange
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.jojo.design.common_base.R
import java.io.IOException

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 11:58 AM
 *    desc   : 状态栏处理工具类
 */
object StatusBarHelper {
    var screenWidth = 0
    var screenHeight = 0
    var navigationHeight = 0
    private var mMetrics: DisplayMetrics? = null
    private val HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION"
    private val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"
    private val FAKE_STATUS_BAR_VIEW_ID = R.id.fake_status_bar_view
    /**
     * @param activity
     * @param useThemeStatusBarColor   是否要状态栏的颜色，不设置则为透明色   true:全屏不透明状态栏  false：全屏透明状态栏
     * @param isStatusBarLightMode 是否使用状态栏为浅色色调  true:白色文字、icon  false:黑色文字、icon
     */
   @JvmStatic fun setStatusBar(activity: Activity, useThemeStatusBarColor: Boolean, isStatusBarLightMode: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            val decorView = activity.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            if (useThemeStatusBarColor) {
                activity.window.statusBarColor = Color.RED
            } else {
                activity.window.statusBarColor = Color.TRANSPARENT
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = activity.window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStatusBarLightMode) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 设置状态栏文字色值为深色调或者浅色调
     * @param isDarkMode 是否使用深色调
     */
    fun setStatusTextColor(isDarkMode: Boolean, activity: Activity) {
        if (isFlyme()) {
            processFlyMe(isDarkMode, activity)
        } else if (isMIUI()) {
            processMIUI(isDarkMode, activity)
        } else {
            if (isDarkMode) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            activity.window.decorView.findViewById<View>(android.R.id.content).setPadding(0, 0, 0, navigationHeight)
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity       需要设置的activity
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度  0-255  0：完全透明 255 完全不透明
     * StatusBarHelper.setStautsBarColor(this, Color.RED, 255)：非全屏 黑色状态栏，白色文字、icon
     */

    fun setStautsBarColor(activity: Activity, @ColorInt color: Int, @IntRange(from = 0, to = 255) statusBarAlpha: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val decorView = activity.window.decorView as ViewGroup
            val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.visibility == View.GONE) {
                    fakeStatusBarView.visibility = View.VISIBLE
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
            } else {
                decorView.addView(createStatusBarView(activity, color, statusBarAlpha))
            }
            setRootView(activity)
        }
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private fun calculateStatusColor(@ColorInt color: Int, alpha: Int): Int {
        if (alpha == 0) {
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    private fun createStatusBarView(activity: Activity, @ColorInt color: Int, alpha: Int): View {
        // 绘制一个和状态栏一样高的矩形
        val statusBarView = View(activity)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
        statusBarView.layoutParams = params
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha))
        statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
        return statusBarView
    }

    /**
     * 设置根布局参数
     */
    private fun setRootView(activity: Activity) {
        val parent = activity.findViewById<View>(android.R.id.content) as ViewGroup
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup) {
                childView.setFitsSystemWindows(true)
                childView.clipToPadding = true
            }
            i++
        }
    }

    /**
     * 判断手机是否是小米
     * @return
     */
    fun isMIUI(): Boolean {
        try {
            val prop = BuildProperties.newInstance()
            return (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null)
        } catch (e: IOException) {
            return false
        }

    }

    /**
     * 判断是否是魅族手机
     */
    fun isFlyme(): Boolean {
        try {
            // Invoke Build.hasSmartBar()
            val method = Build::class.java.getMethod("hasSmartBar")
            return method != null
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
     */
    fun processFlyMe(isLightStatusBar: Boolean, activity: Activity) {
        val lp = activity.getWindow().getAttributes()
        try {
            val instance = Class.forName("android.view.WindowManager\$LayoutParams")
            val value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp)
            val field = instance.getDeclaredField("meizuFlags")
            field.isAccessible = true
            val origin = field.getInt(lp)
            if (isLightStatusBar) {
                field.set(lp, origin or value)
            } else {
                field.set(lp, value.inv() and origin)
            }
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }

    }

    /**
     * 改变小米的状态栏字体颜色为黑色, 要求MIUI6以上  lightStatusBar为真时表示黑色字体
     */
    private fun processMIUI(lightStatusBar: Boolean, activity: Activity) {
        val clazz = activity.window.javaClass
        try {
            val darkModeFlag: Int
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(activity.window, if (lightStatusBar) darkModeFlag else 0, darkModeFlag)
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }

    }

    /**
     * 通过反射的方式获取状态栏的高度
     */
    fun getStatusBarHeight(context: Context): Int {
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = Integer.parseInt(field.get(obj).toString())
            return context.resources.getDimensionPixelSize(x)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 获取底部导航栏高度
     */
    fun getNavigationBarHeight(context: Context): Int {
        var resources = context.resources
        var resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        //获取NavigationBar的高度
        navigationHeight = resources.getDimensionPixelSize(resourceId)
        return navigationHeight
    }

    /**
     * 获取是否存在NavigationBar
     */
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar = false
        var rs = context.resources
        val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id)
        }
        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties")
            val m = systemPropertiesClass.getMethod("get", String::class.java)
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
            if ("1" == navBarOverride) {
                hasNavigationBar = false
            } else if ("0" == navBarOverride) {
                hasNavigationBar = true
            }
        } catch (e: Exception) {

        }
        return hasNavigationBar
    }
}