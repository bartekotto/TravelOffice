import java.io.IOException;
import java.util.logging.*;

public class Test {



    static {
//        Logger.getLogger("mod12.ex01.to");
//        try {
//            fileHandler  = new FileHandler("log.txt");
//            fileHandler.setFormatter(new SimpleFormatter());
//            logger.addHandler(fileHandler);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger("mod12.ex01.to");
//        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler  = new FileHandler("log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainHandler mainHandler = new MainHandler();
        while (true) {
            mainHandler.setDecision();
        }

    }
}
