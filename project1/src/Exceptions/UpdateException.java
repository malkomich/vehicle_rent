/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author JuanCarlos
 */
public class UpdateException extends Exception{
    
    private String message;
    
    public UpdateException(){
    }
    
    public UpdateException(String text){
        this.message = text;
    }
    
    @Override
    public String toString(){
        return this.message;
    }
}
