package se.ams.track.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import se.ams.track.model.JobAdvertisement;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Set;


@Service
public class TrackRepository {

    @Inject
    private RedisTemplate<String, JobAdvertisement> redisTemplate;

    public void save(JobAdvertisement jobAdvertisement) {
        redisTemplate.opsForValue().set(jobAdvertisement.getId(), jobAdvertisement);
    }

    public JobAdvertisement findById(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<JobAdvertisement> findAll() {
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
        redisTemplate.opsForValue().getOperations().delete(key);
    }


    public void deleteAll() {
        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            JobAdvertisement b = new JobAdvertisement(it.next());
            delete(b);
        }
    }
}
