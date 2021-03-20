/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.persistence;

import edu.escuelaing.arem.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author StivenVanegas
 */
public class DataUser {
    
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
    
    public DataUser(){
        Usuario stiven = new Usuario("janer.vanegas","8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
        usuarios.put("janer.vanegas",stiven);
        
    }
  
    public Usuario getUserByName(String name){
        if(usuarios.containsKey(name)){
            return usuarios.get(name);
        }
        return null;
    }
    
    /*
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String input = "123456";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(input.getBytes("utf8"));
        String hash = String.format("%064x", new BigInteger(1, digest.digest()));
        System.out.println(hash);
    }
    */
    
}
