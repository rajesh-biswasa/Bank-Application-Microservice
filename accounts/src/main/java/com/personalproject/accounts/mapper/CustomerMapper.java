package com.personalproject.accounts.mapper;

import com.personalproject.accounts.dto.CustomerDto;
import com.personalproject.accounts.entity.Customer;

public class CustomerMapper {

    /**
     *
     * @param customer
     * @param customerDto
     * @return
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){

        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());

        return customerDto;
    }

    /**
     *
     * @param customerDto
     * @param customer
     * @return
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }


}
