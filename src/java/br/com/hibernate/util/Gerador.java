/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.util;

/**
 *
 * @author Aluno
 */
public class Gerador {

    public static String gerarCpf() {
        //999.999.999-99        
        return gerarNumero(3) + "." + gerarNumero(3) + "." +
                gerarNumero(3) + "-" + gerarNumero(2);
    }

    public static String gerarNumero(int quantidade) {
        String numero = "";
        for (int i = 0; i < quantidade; i++) {
            numero += (int) (Math.random() * 10);
        }
        return numero;
    }
    
    public static void main(String[] args) {
        System.out.println(gerarCpf());
    }

}
