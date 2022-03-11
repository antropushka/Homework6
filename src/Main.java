import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean stopFlag = new AtomicBoolean(false);
        List<RobotsDetail> robotsDetails = new ArrayList<>();

        RobotsDetailFactory robotsDetailFactory = new RobotsDetailFactory(stopFlag, robotsDetails);
        Country aggressor = new Country("aggressor", robotsDetailFactory);
        Country  defending = new Country("defending", robotsDetailFactory);

        Thread robotsFactoryThread = new Thread(robotsDetailFactory::createRobot);
        Thread aggressorCountryThread = new Thread(aggressor::createArmy);
        Thread defendingCountryThread = new Thread(defending::createArmy);
        robotsFactoryThread.start();
        aggressorCountryThread.start();
        defendingCountryThread.start();
        robotsFactoryThread.join();
        aggressorCountryThread.join();
        defendingCountryThread.join();
    }
}
