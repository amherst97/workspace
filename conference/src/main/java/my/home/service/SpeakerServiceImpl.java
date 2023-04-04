package my.home.service;

import my.home.model.Speaker;
import my.home.repository.HibernateRepositoryImpl;
import my.home.repository.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository = new HibernateRepositoryImpl();

    @Override
    public List<Speaker> findAll() {
        return repository.finalAll();
    }
}
