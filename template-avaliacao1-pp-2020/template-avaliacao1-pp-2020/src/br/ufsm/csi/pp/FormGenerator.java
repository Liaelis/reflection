package br.ufsm.csi.pp;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Interface que representa o servi�o que dever� ser provido por um gerador de form html
 */
public interface FormGenerator {

    /**
     * Gera o c�digo em html para um formul�rio d o objeto recebido como par�metro
     * O formul�rio dever� vir preenchido, caso o objeto tenha valores nos par�metros
     *
     * @param objeto objeto que ser� utilizado para gerar o form e preencher os valores, caso existam
     * @return a string html que representa o formul�rio
     */
    String generateForm(Object objeto) throws InvocationTargetException, IllegalAccessException;

    /**
     * Gera o c�digo em html para exibi��o do objeto recebido como par�metro.
     * Poder� ser um formul�rio preenchido e read-only ou um HTML apenas exibindo nomes das
     * propriedades e valores.
     *
     * @param objeto objeto que ser� utilizado para gerar o HTML com os valores
     * @return a string html que representa o documento
     */
    String generateReadOnlyView(Object objeto) throws InvocationTargetException, IllegalAccessException;


    /**
     * Preenche um objeto com os par�metros recebidos do formul�rio.
     *
     * @param parametrosRequisicao mapa dos par�metros do formul�rio
     * @param classe classe do objeto que ser� instanciado e preenchido
     * @return o objeto com as propriedades preenchidas do form.
     */
    Object populateObject(Map<String, String> parametrosRequisicao, Class classe) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;

}
