/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.interfaces.datalayers;

import demo.app.models.PersonModel;
import java.util.List;

/**
 *
 * @author thuanpv
 */
public interface IPersonDatalayer {
    public List<PersonModel> getAllPerson();
    public void addPerson(PersonModel person);
}
