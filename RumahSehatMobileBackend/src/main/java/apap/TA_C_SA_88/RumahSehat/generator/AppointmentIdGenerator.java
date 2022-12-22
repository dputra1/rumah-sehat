package apap.TA_C_SA_88.RumahSehat.generator;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class AppointmentIdGenerator implements IdentifierGenerator{

    Logger logger = LoggerFactory.getLogger(AppointmentIdGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String prefix = "APT-";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(kode) from apap_db.appointment");

            if(rs.next())
            {
                int id=rs.getInt(1)+1;
                return prefix + Integer.toString(id);
            }
            statement.close();
        } catch (SQLException e) {
            logger.info(e.toString());
        }


        return null;
    }
}
