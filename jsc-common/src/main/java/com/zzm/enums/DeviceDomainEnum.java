package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description
 */
public enum DeviceDomainEnum {

    UNKNOWN(0, "UNKNOWN"),
    SWITCH(1, "SWITCH "),
    COLLECTION(2, "COLLECTION"),
    DPI(3, "DPI"),
    LTE(4, "LTE"),
    COLREAR(5, "COLREAR"),
    JKREAR(6, "JKREAR"),
    NETPROB(7, "NETPROB"),
    OPERATOR(8, "OPERATOR"),
    TAPBOX(9,"TAPBOX");

    public int code;
    public String name;

    DeviceDomainEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static DeviceDomainEnum fromName(String name)
    {
        for (DeviceDomainEnum d : DeviceDomainEnum.values())
        {
            if (d.getName().equals(name))
            {
                return d;
            }
        }
        return null;
    }

    public static DeviceDomainEnum fromCode(int code)
    {
        for (DeviceDomainEnum d : DeviceDomainEnum.values())
        {
            if (d.getCode() == code)
            {
                return d;
            }
        }
        return null;
    }
}
