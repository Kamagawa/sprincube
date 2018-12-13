package sprincube.bff.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;


/**
 * The Command class display a list of available system variables
 *
 */
@Component
public class Command implements CommandLineRunner {
    @Override
    public void run(String...args) {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }
}