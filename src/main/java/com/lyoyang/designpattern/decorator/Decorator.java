package com.lyoyang.designpattern.decorator;

/**
 * @author: Brian
 * @Date: 2020/5/19 11:34
 * @Description: 装饰者抽象类
 */
public abstract class Decorator extends Component {

    private Component component;

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
