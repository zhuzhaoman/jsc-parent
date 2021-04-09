package com.zzm.job;

import com.zzm.dao.ErrorsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2021-02-28
 * @description:
 **/
@Component
public class HistoryDataJob {

    @Resource
    private JdbcTemplate jdbcTemplate;
    private ErrorsRepository errorsRepository;

    private final Integer TABLE_SIZE = 1000;
    private final Integer SAVE_DATA = 15552000;

    @Scheduled(cron = "0 */30 * * * ?")
    @Transactional
    public void produceHistoryFlow() throws Exception {
//        List<String> tables = Arrays.asList("errors", "total_history_flow", "port_history_flow", "port_history_optical_power");
        List<String> tables = Arrays.asList("errors");

        tables.stream().forEach(table -> {
            BigDecimal tableSize = this.getTableSize(table);
            if (tableSize.intValue() >= TABLE_SIZE) {
                Calendar now = Calendar.getInstance();
                now.add(Calendar.SECOND, -SAVE_DATA);
                System.out.println(now.getTime());
                errorsRepository.deleteErrorsByDate(now.getTime());
            }
        });
    }

    private BigDecimal getTableSize(String table) {
        Map<String, Object> result = jdbcTemplate
                .queryForMap("select round(sum(DATA_LENGTH/1024/1024),2) as size " +
                        "from information_schema.tables " +
                        "where table_schema='jsc_web'" +
                        "AND table_name='" + table + "'");
        return (BigDecimal) result.get("size");
    }
}
