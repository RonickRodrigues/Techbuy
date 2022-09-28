package br.com.ronick.controller;

/**
 * @author ronic
 */
public class BaseFacade {

    public void fecharPrograma(boolean entrarPrincipal) {
        if (entrarPrincipal == false) {
            System.exit(0);
        }
    }
}
