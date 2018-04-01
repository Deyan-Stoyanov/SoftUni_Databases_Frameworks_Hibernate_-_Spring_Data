package org.bookshopsystem.procedures;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;

public class TotalBooksProcedure extends StoredProcedure {
    public TotalBooksProcedure() {
    }

    public TotalBooksProcedure(DataSource ds, String name) {
        super(ds, name);
    }

    public TotalBooksProcedure(JdbcTemplate jdbcTemplate, String name) {
        super(jdbcTemplate, "usp_total_books");
    }


}
