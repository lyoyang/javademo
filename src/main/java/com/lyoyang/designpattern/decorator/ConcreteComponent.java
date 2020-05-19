package com.lyoyang.designpattern.decorator;

/**
 * @author: Brian
 * @Date: 2020/5/19 11:33
 * @Description:
 */
public class ConcreteComponent extends Component {

    @Override
    public void operation() {
        System.out.println(this.getClass() + " 具体操作。。。");
    }
}
