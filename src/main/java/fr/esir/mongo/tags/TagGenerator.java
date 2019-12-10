package fr.esir.mongo.tags;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TagGenerator {

    @Value("classpath:tags.txt")
    private Resource tagsResource;

    private String[] tags;

    @PostConstruct
    private void initTags() throws IOException {
        List<String> tagsList = IOUtils.readLines(tagsResource.getInputStream(), StandardCharsets.UTF_8.name());
        tags = tagsList.toArray(new String[tagsList.size()]);
    }

    public List<String> getTags() {
        int max = tags.length;

        int random = (int) (Math.random() * max);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < random; i++) {
            res.add(tags[i]);
        }
        return res;
    }
}
