package com.ab.demo.resolver;

import com.ab.demo.domain.Animal;
import com.ab.demo.domain.Owner;
import com.ab.demo.repository.AnimalRepository;
import com.ab.demo.repository.OwnerRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private OwnerRepository ownerRepository;
    private AnimalRepository animalRepository;

    @Autowired
    public Mutation(OwnerRepository ownerRepository,
                    AnimalRepository animalRepository) {
        this.ownerRepository = ownerRepository;
        this.animalRepository = animalRepository;
    }

    //    createOwner(name: String!, surname: String, age: Int!): Owner!
    public Owner createOwner(String name, String surname, Integer age) {
        Owner author = new Owner();
        author.setName(name);
        author.setAge(age);
        author.setSurname(surname);

        ownerRepository.save(author);

        return author;
    }


    //    createAnimal(name: String!, owner: Owner): Animal!
    public Animal createAnimal(String name, Long authorId) {
        Animal tutorial = new Animal();
        tutorial.setOwner(new Owner(authorId));
        tutorial.setName(name);

        animalRepository.save(tutorial);

        return tutorial;
    }

    //    deleteAnimal(id: ID!): Boolean
    public boolean deleteAnimal(Long id) {
        animalRepository.deleteById(id);
        return true;
    }

    //    updateAnimal(id: ID!, name: String): Animal!
    public Animal updateAnimal(Long id, String name) throws EntityNotFoundException {
        Optional<Animal> animalOpt = animalRepository.findById(id);

        if (animalOpt.isPresent()) {
            Animal animal = animalOpt.get();

            if (Objects.nonNull(name))
                animal.setName(name);

            animalRepository.save(animal);
            return animal;
        }

        throw new EntityNotFoundException("Not found Tutorial to update!");
    }

    //    updateOwner(id: ID!, name: String, surname: String): Owner!
    public Owner updateOwner(Long id, String name, String surname) throws EntityNotFoundException {
        Optional<Owner> ownerOpt = ownerRepository.findById(id);

        if (ownerOpt.isPresent()) {
            Owner owner = ownerOpt.get();

            if (Objects.nonNull(name))
                owner.setName(name);

            if (Objects.nonNull(surname))
                owner.setName(surname);

            ownerRepository.save(owner);
            return owner;
        }

        throw new EntityNotFoundException("Not found Tutorial to update!");
    }

    //    deleteOwner(id: ID!): Boolean
    public boolean deleteOwner(Long id) {
        ownerRepository.deleteById(id);
        return true;
    }
}
