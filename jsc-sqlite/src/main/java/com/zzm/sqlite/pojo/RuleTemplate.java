package com.zzm.sqlite.pojo;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Data
public class RuleTemplate {

    private long domainId;
    private long templateType;
    private long priority;
    private long userId;

}
