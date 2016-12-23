package cn.kerison.coding.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import cn.kerison.coding.util.impl.KLogPrinter;
import cn.kerison.coding.util.impl.NoLogPrinter;


/**
 * Created by kerison
 */
public class KL {

    private KL() {
    }

    private static final int TEXT_INDENT = 4;

    private static boolean isDebug = true;
    private static ILogPrinter mPrinter = new KLogPrinter();

    /**
     * 设置
     * @param debug 是否是debug模式
     * @return Printer
     */
    public static ILogPrinter config(boolean debug) {
        isDebug = debug;
        if (!isDebug) {
            mPrinter = new NoLogPrinter();
        }
        return mPrinter;
    }

    /**
     * 自定义Printer
     *
     * @param printer 自定义ILogPrinter的实例
     */
    public static void setPrinter(ILogPrinter printer) {
        setPrinter(printer,false);
    }

    /**
     * 自定义Printer
     *
     * @param printer 自定义ILogPrinter的实例
     * @param force 忽略标志位，强制设置打印器
     */
    public static void setPrinter(ILogPrinter printer,boolean force) {
        if (isDebug || force) {
            mPrinter = printer;
        }
    }

    /**
     * 仅输出线程和方法栈
     */
    public static void d() {
        mPrinter.d(" ");
    }

    /**
     * 仅输出线程和方法栈
     */
    public static void i() {
        mPrinter.i(" ");
    }

    /**
     * 仅输出线程和方法栈
     */
    public static void w() {
        mPrinter.w(" ");
    }


    public static void v(String content) {
        mPrinter.v(content);
    }

    public static void v(String format, Object... args) {
        mPrinter.v(String.format(format, args));
    }

    public static void v(Throwable tr) {
        mPrinter.v(Log.getStackTraceString(tr));
    }

    public static void d(String content) {
        mPrinter.d(content);
    }

    public static void d(String format, Object... args) {
        mPrinter.d(String.format(format, args));
    }

    public static void d(Throwable tr) {
        mPrinter.d(Log.getStackTraceString(tr));
    }

    public static void i(String content) {
        mPrinter.i(content);
    }

    public static void i(String format, Object... args) {
        mPrinter.i(String.format(format, args));
    }

    public static void i(Throwable tr) {
        mPrinter.i(Log.getStackTraceString(tr));
    }

    public static void w(String content) {
        mPrinter.w(content);
    }

    public static void w(String format, Object... args) {
        mPrinter.w(String.format(format, args));
    }

    public static void w(Throwable tr) {
        mPrinter.w(Log.getStackTraceString(tr));
    }

    public static void e(String content) {
        mPrinter.e(content);
    }

    public static void e(String format, Object... args) {
        mPrinter.e(String.format(format, args));
    }

    public static void e(Throwable tr) {
        mPrinter.e(Log.getStackTraceString(tr));
    }

    public static void wtf(String content) {
        mPrinter.wtf(content);
    }

    public static void wtf(String format, Object... args) {
        mPrinter.wtf(String.format(format, args));
    }

    public static void wtf(Throwable tr) {
        mPrinter.wtf(Log.getStackTraceString(tr));
    }

    /**
     * 格式化输出json
     *
     * @param jsonObject json对象
     */
    public static void json(JSONObject jsonObject) {
        if (jsonObject == null) {
            mPrinter.e("Log json data is null");
            return;
        }
        try {
            mPrinter.text(jsonObject.toString(TEXT_INDENT));
        } catch (JSONException e) {
            e.printStackTrace();
            mPrinter.e("Log json error :\n" + e.getMessage());
        }
    }


    /**
     * 格式化输出json
     *
     * @param jsonArray json数组
     */
    public static void json(JSONArray jsonArray) {
        if (jsonArray == null) {
            mPrinter.e("Log json data is null");
            return;
        }
        try {
            mPrinter.text(jsonArray.toString(TEXT_INDENT));
        } catch (JSONException e) {
            mPrinter.e("Log json error :\n" + e.getMessage());
        }
    }

    /**
     * 格式化输出json
     *
     * @param jsonText json文本
     */
    public static void json(String jsonText) {
        if (isTextEmpty(jsonText)) {
            mPrinter.e("Log json data is empty!");
            return;
        }
        jsonText = jsonText.trim();
        try {
            if (jsonText.startsWith("{")) {
                mPrinter.text(new JSONObject(jsonText).toString(TEXT_INDENT));
            } else if (jsonText.startsWith("[")) {
                mPrinter.text(new JSONArray(jsonText).toString(TEXT_INDENT));
            } else {
                mPrinter.text("Log json data is " + jsonText);
            }
        } catch (JSONException e) {
            mPrinter.e("Log.json error :\n" + e.getMessage());
        }
    }


    /**
     * 格式化输出xml
     *
     * @param xml xml文本
     */
    public static void xml(String xml) {
        if (isTextEmpty(xml)) {
            mPrinter.e("Log xml data is empty!");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(TEXT_INDENT));
            transformer.transform(xmlInput, xmlOutput);
            mPrinter.text(xmlOutput.getWriter().toString());
        } catch (TransformerException e) {
            mPrinter.e("Log xml error :\n" + e.getMessage());
        }
    }

    private static boolean isTextEmpty(String text) {
        return text == null || "".equals(text.trim()) || "null".equals(text.trim());
    }
}
