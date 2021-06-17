
package com.apioriente.appsoporte.model;

import java.io.Serializable;

/**
 *
 * @author Jaasiel Guerra
 */
public class Parameter implements Serializable{
    
    public String param;
    public Object value;

    public Parameter(String param, Object value) {
        this.param = param;
        this.value = value;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
    
}
