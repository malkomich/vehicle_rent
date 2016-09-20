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
public class InputError extends Exception{
    
    private String message;
    
    public InputError(){
    }
    
    public InputError(String text){
        this.message = text;
    }
    
    @Override
    public String toString(){
        return this.message;
    }
}
