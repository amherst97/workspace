package my.home.repository;

import my.home.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public class HibernateRepositoryImpl implements SpeakerRepository {
    @Override
    public List<Speaker> finalAll() {
        List<Speaker> speakers = new ArrayList<Speaker>();

        Speaker speaker = new Speaker();

        speaker.setFirstName("Dansong");
        speaker.setLastName("Chen");

        speakers.add(speaker);

        return speakers;
    }
}
