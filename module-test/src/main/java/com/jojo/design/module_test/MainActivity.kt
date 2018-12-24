package com.jojo.design.module_test

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.jojo.design.module_test.dagger22.B
import kotlinx.android.synthetic.main.activity_main.*
import org.xml.sax.XMLReader
import java.util.*

//TextView加载富文本 Html
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("TAG", B().getData())

        var html1 = "直播回放 | " +
                "<span style='color: #e05864;'>这是高部分</span><span style='color: #333333;'>这是标题</span>"
        var html2 = "<span style='color: #ff0000'>这是高亮部分</span><span style='color: #333333;'>这是描述</span>"
        Log.e("TAG", "html2=" + html2)
        html2.replace("span", "font")
        tv.text = Html.fromHtml(html1)
        tv_2.text = Html.fromHtml(html2)
    }

    //Android TextView通过解析html显示不同颜色和大小:https://www.jianshu.com/p/4c4a7870c554
    inner class HtmlTagHandler : Html.TagHandler {

        private val TAG_BLUE_FONT = "bluefont"
        fun dip2px(dpValue: Float): Int {
            val scale = getResources().getDisplayMetrics().density
            return (dpValue * scale + 0.5f).toInt()
        }

        private var startIndex = 0
        private var stopIndex = 0
        internal val attributes = HashMap<String, String>()

        override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
            processAttributes(xmlReader)

            if (tag.equals(TAG_BLUE_FONT, ignoreCase = true)) {
                if (opening) {
                    startFont(tag, output, xmlReader)
                } else {
                    endFont(tag, output, xmlReader)
                }
            }
        }

        fun startFont(tag: String, output: Editable, xmlReader: XMLReader) {
            startIndex = output.length
        }

        fun endFont(tag: String, output: Editable, xmlReader: XMLReader) {
            stopIndex = output.length

            val color = attributes["color"]
            var size = attributes["size"]
            size = size?.split("px".toRegex())?.dropLastWhile { it.isEmpty() }!!.toTypedArray()[0]
            Log.e("TAG", "Size=" + size)
            if (!TextUtils.isEmpty(color) && !TextUtils.isEmpty(size)) {
                output.setSpan(ForegroundColorSpan(Color.parseColor(color)), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            if (!TextUtils.isEmpty(size)) {
                var context: Context? = null
                output.setSpan(AbsoluteSizeSpan(dip2px(size.toFloat())), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        private fun processAttributes(xmlReader: XMLReader) {
            try {
                val elementField = xmlReader.javaClass.getDeclaredField("theNewElement")
                elementField.isAccessible = true
                val element = elementField.get(xmlReader)
                val attsField = element.javaClass.getDeclaredField("theAtts")
                attsField.isAccessible = true
                val atts = attsField.get(element)
                val dataField = atts.javaClass.getDeclaredField("data")
                dataField.isAccessible = true
                val data = dataField.get(atts) as Array<String>
                val lengthField = atts.javaClass.getDeclaredField("length")
                lengthField.isAccessible = true
                val len = lengthField.get(atts) as Int

                for (i in 0 until len) {
                    attributes.put(data[i * 5 + 1], data[i * 5 + 4])
                }
            } catch (e: Exception) {

            }

        }

    }


}
