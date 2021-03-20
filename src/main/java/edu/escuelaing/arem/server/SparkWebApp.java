
package edu.escuelaing.arem.server;


import edu.escuelaing.arem.model.Usuario;
import edu.escuelaing.arem.persistence.DataUser;
import java.math.BigInteger;
import java.security.MessageDigest;
import static spark.Spark.*;
/**
 *
 * @author StivenVanegas
 */
public class SparkWebApp {
    
    public static void main(String[] args) {
        
        secure("keystore/ecikeystore.p12","123456",null,null);
        port(getPort());
        staticFileLocation("/");
        get("/hello", (req, res) -> "Hello from secure service");
        
        get("/", (req, res) -> {
            res.redirect("/index.html");
            res.status(200);
            return null;
        });
        
        post("/login", (req, res) -> {
            
            DataUser data = new DataUser();
            String user = req.queryParams("user");
            String pwd = req.queryParams("pwd");
            
            Usuario usuario = data.getUserByName(user);
            if(usuario != null){
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.reset();
                digest.update(pwd.getBytes("utf8"));
                String hash = String.format("%064x", new BigInteger(1, digest.digest()));
                if(usuario.getPassword().equals(hash)){
                    res.redirect("/hello");
                    return "";
                }
            }
            res.redirect("/index.html");
            return "";
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
	
}
