package com.ab.demo.resolver;

import com.ab.demo.domain.Animal;
import com.ab.demo.domain.Owner;
import com.ab.demo.repository.AnimalRepository;
import com.ab.demo.repository.OwnerRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private final OwnerRepository ownerRepository;
    private final AnimalRepository animalRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
            .aliasedScalar(ExtendedScalars.GraphQLLong)
            .build();

    @Autowired
    public Query(OwnerRepository ownerRepository,
                 AnimalRepository animalRepository) {
        this.ownerRepository = ownerRepository;
        this.animalRepository = animalRepository;
    }

    public Iterable<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    public Iterable<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    public long countOwners() {
        return ownerRepository.count();
    }

    public long countAnimals() {
        return animalRepository.count();
    }

}