package se.ams.track.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ams.track.model.JobAdvertisement;
import se.ams.track.repository.TrackRepository;


import java.util.List;


@Service
public class TrackService {

    final Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    private TrackRepository repository;

    public List<JobAdvertisement> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public JobAdvertisement findById(String id) {
        return repository.findById(id);
    }

    public void save(JobAdvertisement jobAdvertisement) {
        repository.save(jobAdvertisement);
    }

    public void delete(JobAdvertisement jobAdvertisement) {
        repository.delete(jobAdvertisement);
    }


    public void deleteAll() {
        repository.deleteAll();
    }


    public void restoreDefaultJobAdvertisement() {
        repository.deleteAll();

        JobAdvertisement it = new JobAdvertisement("11111", "IT");
        JobAdvertisement transport = new JobAdvertisement("11112", "Transport");

        repository.save(it);
        repository.save(transport);
    }

}
