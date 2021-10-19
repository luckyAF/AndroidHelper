package com.luckyaf.smartandroid.ui.binding_adapter.commond;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/19
 */
public class BindingCommand<T> {
    private BindingAction execute;
    private BindingConsumer<T> consumer;

    public BindingCommand(BindingAction execute) {
        this.execute = execute;
    }
    /**
     * @param execute 带泛型参数的命令绑定
     */
    public BindingCommand(BindingConsumer<T> execute) {
        this.consumer = execute;
    }

    /**
     * 执行BindingAction命令
     */
    public void execute() {
        if (execute != null) {
            execute.call();
        }
    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    public void execute(T parameter) {
        if (consumer != null) {
            consumer.call(parameter);
        }
    }



}
