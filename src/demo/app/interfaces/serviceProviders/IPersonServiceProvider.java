/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.interfaces.serviceProviders;

import demo.app.models.PersonModel;
import java.util.List;

/**
 *
 * @author thuanpv
 */
public interface IPersonServiceProvider {
    public List<PersonModel> getAllUser();
}
