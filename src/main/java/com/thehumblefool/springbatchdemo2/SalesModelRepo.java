/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.springbatchdemo2;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author shash
 */
public interface SalesModelRepo extends JpaRepository<SalesModel, Long> {

}
