import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RobotsDetailFactory {

    private static final Random RANDOM = new Random();

    private final AtomicBoolean stopFlag;
    private List<RobotsDetail> robotsDetailStorage = new ArrayList<>();

    public RobotsDetailFactory(AtomicBoolean stopFlag, List<RobotsDetail> robotsDetailStorage) {
        this.stopFlag = stopFlag;
        this.robotsDetailStorage = robotsDetailStorage;
    }

    public void createRobotsDetail() {
        while (!stopFlag.get()) {
            synchronized (robotsDetailStorage) {
                robotsDetailStorage.add(generateRobotDetail());
                System.out.println("Robots detail has been created" +
                        robotsDetailStorage.get(robotsDetailStorage.size() - 1).name());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    stopFlag.set(true);
                }
            }
        }
    }

    private RobotsDetail generateRobotDetail() {
        return switch (RANDOM.nextInt(6)) {
            case 0 -> RobotsDetail.BODY;
            case 1 -> RobotsDetail.HEAD;
            case 2 -> RobotsDetail.RIGHT_LEG;
            case 3 -> RobotsDetail.RIGHT_HAND;
            case 4 -> RobotsDetail.LEFT_LEG;
            case 5 -> RobotsDetail.LEFT_HAND;
            default -> RobotsDetail.BODY;
        };
    }

    public void takeRobotsDetailFromStorage(Set<RobotsDetail> detailsForRobotsProduction, String nameOfTheCountry) {
        List<RobotsDetail> tempList = new ArrayList<>();
        synchronized (robotsDetailStorage) {
            if (!robotsDetailStorage.isEmpty()) {
                for (RobotsDetail robotsDetail : this.robotsDetailStorage) {
                    if (!detailsForRobotsProduction.contains(robotsDetail)) {
                        detailsForRobotsProduction.add(robotsDetail);
                        tempList.add(robotsDetail);
                    }
                }
                if (!tempList.isEmpty()) {
                    System.out.println(nameOfTheCountry + " has received" + tempList);
                }
                for (RobotsDetail robotsDetail : tempList) {
                    this.robotsDetailStorage.remove(robotsDetail);
                }
            }
        }
    }

    public AtomicBoolean getStopFlag() {
        return stopFlag;
    }

    public List<RobotsDetail> getRobotsDetailStorage() {
        return robotsDetailStorage;
    }



}
