/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sompopo.api.infraestructure.mapper;

import org.modelmapper.ModelMapper;

/**
 *
 * @author gcalvaradoa
 */
public class Mapper {
    
    private static final ModelMapper mapper = new ModelMapper() ;
    
    public  <S,T>  T  toModel( S source, Class<T> targetCalass  ){
        
        return mapper.map(source, targetCalass);
    }
    
    public static ModelMapper getInstance(){
        
        return mapper;
        
    }
}
