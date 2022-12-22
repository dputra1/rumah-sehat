package apap.TA_C_SA_88.RumahSehat.generator;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class TagihanIdGenerator implements IdentifierGenerator{

    Logger logger = LoggerFactory.getLogger(TagihanIdGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String prefix = "BILL-";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(kode) from apap_db.tagihan");

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
