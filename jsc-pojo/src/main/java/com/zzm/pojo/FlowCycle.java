package  com.zzm.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@SuppressWarnings("all")
@Table(name = "flow_cycle")
public class FlowCycle {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "cycle")
    private Integer cycle;
}
