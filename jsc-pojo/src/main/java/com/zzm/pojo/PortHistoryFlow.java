package  com.zzm.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-23
 * @description:
 **/
@Data
@Entity
@SuppressWarnings("all")
@Table(name = "port_history_flow")
public class PortHistoryFlow {

    @Id
    @Column(name = "id")
    private String id;

    /**
     * 输入
     */
    @Column(name = "rx")
    private float rx;

    /**
     * 输出
     */
    @Column(name = "tx")
    private float tx;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "domain_id")
    private Integer domainId;

    @Column(name = "domain_type")
    private Integer domainType;

    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "port_name")
    private String portName;

    @Column(name = "create_time")
    private Date createTime;
}
