package ua.com.vertex.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.vertex.beans.Certificate;
import ua.com.vertex.dao.CertificateDaoInf;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@SuppressWarnings("SqlDialectInspection")
public class CertificateDaoRealization implements CertificateDaoInf {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Profile("dev")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    @Profile("test")
    public void setTestDataSource(DataSource testDataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(testDataSource);
    }

    @Override
    public List<Certificate> getAllCertificateByUserId(int userId) {
        String query = "SELECT certification_id, user_id, certification_date, course_name, language "
                + "FROM Certificate WHERE user_id =:userId";

        return jdbcTemplate.query(query, new MapSqlParameterSource("userId", userId), new CertificateRowMapper());
    }

    @Override
    public Certificate getCertificateById(int certificateId) {
        String query = "SELECT certification_id, user_id, certification_date, course_name, language "
                + "FROM Certificate WHERE certification_id =:certificateId";
        return jdbcTemplate.queryForObject(query,
                new MapSqlParameterSource("certificateId", certificateId), new CertificateRowMapper());
    }

    private static final class CertificateRowMapper implements RowMapper<Certificate> {
        public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Certificate.Builder()
                    .setCertificationId(resultSet.getInt("certification_id"))
                    .setUserId(resultSet.getInt("user_id"))
                    .setCertificationDate(resultSet.getDate("certification_date").toLocalDate())
                    .setCourseName(resultSet.getString("course_name"))
                    .setLanguage(resultSet.getString("language"))
                    .getInstance();
        }
    }

//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(MainContext.class);
//        CertificateDaoRealization dao = context.getBean(CertificateDaoRealization.class);
//        Certificate cert = dao.getCertificateById(1);
//        System.out.println(cert);
//
//    }
}