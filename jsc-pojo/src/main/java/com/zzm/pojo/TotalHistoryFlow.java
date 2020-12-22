package  com.zzm.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@SuppressWarnings("all")
@Table(name = "total_history_flow")
public class TotalHistoryFlow {

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

    @Column(name = "domain_type")
    private Integer domainType;

    @Column(name = "domain_id")
    private Integer domainId;

    @Column(name = "create_time")
    private Date createTime;
}
