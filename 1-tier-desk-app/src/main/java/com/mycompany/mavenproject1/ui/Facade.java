/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.ui;

import com.mycompany.mavenproject1.business.CountryBusiness;
import com.mycompany.mavenproject1.business.CustomerBusiness;
import com.mycompany.mavenproject1.data.Country;
import com.mycompany.mavenproject1.data.Customer;
import java.util.Set;

/**
 *
 * @author Liah Beeshop'
 */
public class Facade {
    CountryBusiness country = new CountryBusiness();
    CustomerBusiness customer = new CustomerBusiness();
    
    public void create(Country country) throws Exception {
        this.country.create(country);

    }

    public Set<Country> readAllCountry() {
        return country.readAll();

    }    
    
    public void create(Customer customer) throws Exception{
        this.customer.create(customer);
    }
    
    public Set<Customer> readAllCustomer() {
        return customer.readAll();
    }
    
    
}
