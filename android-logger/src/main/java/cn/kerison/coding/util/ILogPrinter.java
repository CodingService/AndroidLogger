package cn.kerison.coding.util;

/**
 * Created by kerison
 */
public interface ILogPrinter {

    /**
     * Log的Tag
     * @param tag 标签
     * @return ILogPrinter
     */
    ILogPrinter setTag(String tag);

    /**
     * 打印方法栈中的层级个数
     * @param count 层级个数 默认1层
     * @return ILogPrinter
     */
    ILogPrinter setMethodCount(int count);

    /**
     * 只打印输出的消息
     * @return ILogPrinter
     */
    ILogPrinter hideExtraInfo();

    /**
     * 设置文本 json xml 的输出级别
     * @param level log级别
     * @return ILogPrinter
     */
    ILogPrinter setTextLevel(int level);

    void v(String msg);

    void d(String msg);

    void i(String msg);

    void w(String msg);

    void e(String msg);

    void wtf(String msg);

    void text(String msg);
}
