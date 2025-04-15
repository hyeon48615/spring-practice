package net.fullstack10.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {
    @Test
    public void testConnection() throws Exception {
        // 1. 드라이버 지정
        // 2. DB URL 지정
        // 3. 접속자 아이디
        // 4. 비밀번호

        Class.forName("org.mariadb.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/fullstack10"
                , "root"
                , "1234"
        );
        Assertions.assertNotNull(conn);
        System.out.println("[conn] " + conn);
        conn.close();
    }

    @Test
    public void testHikariCP() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/fullstack10");
        config.setUsername("root");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();
        Assertions.assertNotNull(conn);
        System.out.println("[conn] " + conn);

        if (conn != null) conn.close();
        if (ds != null) ds.close();
    }
}
