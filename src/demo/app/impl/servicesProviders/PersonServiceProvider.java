/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.impl.servicesProviders;

import demo.app.interfaces.datalayers.IPersonDatalayer;
import demo.app.interfaces.serviceProviders.IPersonServiceProvider;
import demo.app.models.PersonModel;
import java.util.List;

/**
 *
 * @author thuanpv
 */
public class PersonServiceProvider implements IPersonServiceProvider{

     private IPersonDatalayer m_PersonDataLayer;
    
     public PersonServiceProvider(IPersonDatalayer personDataLayer){
         m_PersonDataLayer = personDataLayer;
     }
     
    @Override
    public List<PersonModel> getAllUser() {
        return m_PersonDataLayer.getAllPerson();
    }

    @Override
    public void addPerson(PersonModel person) {
        m_PersonDataLayer.addPerson(person);
    }
    
}
