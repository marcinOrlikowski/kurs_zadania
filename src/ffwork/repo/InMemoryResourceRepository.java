package ffwork.repo;

import ffwork.domain.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryResourceRepository implements ResourceRepository {
    List<Resource> resources = new ArrayList<>();

    @Override
    public void add(Resource r) {
        if (r == null) {
            throw new NullPointerException("Cannot add null reference");
        }
        findByName(r.getName())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Object with such name already exists");
                });
        resources.add(r);
    }

    @Override
    public Optional<Resource> findByName(String name) {
        for (Resource resource : resources) {
            if (resource.getName().equals(name)) {
                return Optional.of(resource);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Resource> findAll() {
        return resources;
    }

    @Override
    public List<Resource> findByType(Class<? extends Resource> t) {
        return resources.stream()
                .filter(t::isInstance)
                .collect(Collectors.toList());
    }
}
