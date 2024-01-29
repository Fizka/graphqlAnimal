package com.ab.demo.resolver;

import com.ab.demo.domain.Animal;
import com.ab.demo.domain.Owner;
import com.ab.demo.repository.OwnerRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalResolver implements GraphQLResolver<Animal> {
    @Autowired
    private OwnerRepository ownerRepository;

    public AnimalResolver(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner getOwner(Animal animal) {
        return ownerRepository.findById(animal.getOwner().getId())
                .orElseThrow(null);
    }
}