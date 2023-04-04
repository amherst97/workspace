import my.home.service.SpeakerService;
import my.home.service.SpeakerServiceImpl;

public class Application {
    public static void main(String args[]) {
        SpeakerService service = new SpeakerServiceImpl();

        System.out.println(service.findAll().get(0).getFirstName());
    }
}
