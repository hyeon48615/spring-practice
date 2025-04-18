package net.fullstack10.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/root-context.xml"})
public class SampleTests {
//    @Autowired
//    private SampleService sampleService;
//
//    @Test
//    public void testSample() {
//        log.info("sampleService: " + sampleService);
//        Assertions.assertNotNull(sampleService);
//    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws Exception {
        Connection conn = dataSource.getConnection();
        log.info("conn: {}", conn);
        Assertions.assertNotNull(conn);
        conn.close();
    }

}
