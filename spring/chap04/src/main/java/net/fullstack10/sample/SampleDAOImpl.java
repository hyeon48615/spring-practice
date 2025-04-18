package net.fullstack10.sample;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
// @Primary
@Qualifier("normal")
public class SampleDAOImpl implements SampleDAOIf {
    public SampleDAOImpl() {
        log.info("SampleDAOImpl >>> ");
    }
}
