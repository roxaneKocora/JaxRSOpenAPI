package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.enumeration.Role;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.mindrot.jbcrypt.BCrypt;

import java.util.logging.Logger;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        ut.deploy(TestApplication.class);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );
        initialiserAdmin();

        logger.info("JAX-RS based micro-service running! \n"
                + "API running on: http://localhost:8080 \n"
                + "Swagger UI: http://localhost:8080/swagger-api/");
    }


    private static void initialiserAdmin() {
        UserDao userDao = new UserDao();

        // Vérifie si un admin existe déjà
        if (userDao.findByEmailNamedQuery("admin@concert.com") == null) {
            Admin admin = new Admin();
            admin.setNom("Admin");
            admin.setPrenom("Super");
            admin.setEmail("admin@concert.com");
            admin.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt()));
            admin.setTelephone("0600000000");
            admin.setRole(Role.ADMIN);
            admin.setStatut_user(true);

            userDao.save(admin);
            logger.info("Admin par défaut créé : admin@concert.com / admin123");
        } else {
            logger.info("Admin déjà existant, pas de création.");
        }
    }
}
