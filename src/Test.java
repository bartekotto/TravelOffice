import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Test {
    private static Logger logger = Logger.getLogger("src");
    private static FileHandler fileHandler;


    static {
        Logger.getLogger("mod12.ex01.to");
        try {
            fileHandler  = new FileHandler("log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        logger.setUseParentHandlers(false);
        MainHandler mainHandler = new MainHandler();
        while (true) {
            mainHandler.setDecision();
        }

    }
}
