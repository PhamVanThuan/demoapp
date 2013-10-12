/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.impl.datalayers;

import demo.app.interfaces.datalayers.IPersonDatalayer;
import demo.app.models.PersonModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuanpv
 */
public class PersonDataLayer implements IPersonDatalayer {

    private List<PersonModel> persons = new ArrayList<>();
    
    @Override
    public List<PersonModel> getAllPerson() {
        // get Data From DB
        persons.add(new PersonModel("Hans", "Muster"));
        persons.add(new PersonModel("Ruth", "Mueller"));
        persons.add(new PersonModel("Heinz", "Kurz"));
        persons.add(new PersonModel("Cornelia", "Meier"));
        persons.add(new PersonModel("Werner", "Meyer"));
        persons.add(new PersonModel("Lydia", "Kunz"));
        persons.add(new PersonModel("Anna", "Best"));
        persons.add(new PersonModel("Stefan", "Meier"));
        persons.add(new PersonModel("Martin", "Mueller"));
        
        return persons;
    }

    @Override
    public void addPerson(PersonModel person) {
        persons.add(person);
    }
    
    
    
}
