import java.util.Set;

public class Robot {
    private final Set<RobotsDetail> robotsDetail;// set уникальные значения в отл.от листа

    public Robot(Set<RobotsDetail> DetailsForRobotProduction) {
        this.robotsDetail = DetailsForRobotProduction;
    }
}

