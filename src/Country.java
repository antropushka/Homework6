import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Country {

    private static final int ARMY_SIZE = 20;

    private final String nameOfTheCountry;
    private final List<Robot> armyOfRobots = new ArrayList<>();
    private final RobotsDetailFactory robotsDetailFactory;
    private final Set<RobotsDetail> robotsDetailStorage = new HashSet<>();


    public Country(String nameOfTheCountry, RobotsDetailFactory robotsDetailFactory) {
        this.nameOfTheCountry = nameOfTheCountry;
        this.robotsDetailFactory = robotsDetailFactory;
    }

    public void createArmy() {
        while (armyOfRobots.size() < ARMY_SIZE) {
            armyOfRobots.add(createRobot());
            System.out.printf("New robot in army" + this.nameOfTheCountry + "has been created. \n" +
                    "The current army size is " + armyOfRobots.size());
        }
    }

    private Robot createRobot() {
        Set<RobotsDetail> robotsDetails = new HashSet<>();
        while (robotsDetails.size() < 6) {
            for (RobotsDetail present : RobotsDetail.values()) {
                if (!robotsDetails.contains(present)) {
                    RobotsDetail newRobotsDetail = findDetailsOnTheFactory(present);
                    if (newRobotsDetail != null) {
                        robotsDetails.add(newRobotsDetail);
                    }
                }
            }
        }
        return new Robot(robotsDetails);
    }

    private RobotsDetail findDetailsOnTheFactory(RobotsDetail robotsDetail) {
        RobotsDetail newRobotsDetail = robotsDetailFactory.takeRobotsDetailFromStorage(robotsDetail);
        if (newRobotsDetail != null) {
            System.out.println("Country" + this.nameOfTheCountry + "took from the storage" + robotsDetail);
        }
        return newRobotsDetail;
    }
    private boolean checkRobotsDetailStorage(RobotsDetail robotsDetail) {
        return robotsDetailStorage.contains(robotsDetail);
    }
}