package se.ams.track.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import se.ams.track.model.JobAdvertisement;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Set;


@Service
public class TrackRepository {

    final Logger logger = LoggerFactory.getLogger(TrackRepository.class);

    @Autowired
    private RedisTemplate<String, JobAdvertisement> redisTemplate;

    public void save(JobAdvertisement jobAdvertisement) {
        logger.info(String.format("Saving JobAdvertisement entity with values: '%s'"), jobAdvertisement);
        redisTemplate.opsForValue().set(jobAdvertisement.getId(), jobAdvertisement);
    }

    public JobAdvertisement findById(String key) {
        logger.info(String.format("Trying to find JobAdvertisement entity with key: '%s'"), key);
        return redisTemplate.opsForValue().get(key);
    }

    public List<JobAdvertisement> findAll() {

        logger.info("Searches for all JobAdvertisements entities form database");

        List<JobAdvertisement> jobAdvertisements = new ArrayList<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            jobAdvertisements.add(findById(it.next()));
        }

        return jobAdvertisements;
    }

    public void delete(JobAdvertisement b) {
        String key = b.getId();
        logger.info(String.format("Deleting JobAdvertisement entity with id: '%s'"), key);
        redisTemplate.opsForValue().getOperations().delete(key);
    }


    public void deleteAll() {
        logger.info("Removes all JobAdvertisements entities from database");
        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            JobAdvertisement b = new JobAdvertisement(it.next());
            delete(b);
        }
    }
}
