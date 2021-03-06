package ua.com.vertex.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.vertex.beans.Certificate;
import ua.com.vertex.context.MainTestContext;
import ua.com.vertex.dao.interfaces.CertificateDaoInf;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainTestContext.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CertificateDaoTest {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CertificateDaoInf certificateDao;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Test
    public void jdbcTemplateShouldNotBeNull() {
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void daoShouldReturnCertificateOptionalForCertificateExistingInDatabase() {
        Optional<Certificate> optional = certificateDao.getCertificateById(222);
        assertNotNull(optional);
        assertEquals(222, optional.get().getCertificationId());
    }

    @Test
    public void daoShouldReturnCertificateOptionalForCertificateNotExistingInDatabase() {
        Optional<Certificate> optional = certificateDao.getCertificateById(55555);
        assertNotNull(optional);
        assertEquals(new Certificate(), optional.orElse(new Certificate()));
    }
}
