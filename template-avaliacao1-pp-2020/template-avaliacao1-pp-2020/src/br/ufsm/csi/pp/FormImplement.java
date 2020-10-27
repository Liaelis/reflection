package br.ufsm.csi.pp;

import br.ufsm.csi.pp.annotations.Field;
import br.ufsm.csi.pp.annotations.Form;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class FormImplement implements FormGenerator {

    private static FormImplement INSTANCE = null;

    public static FormGenerator getInstance() {
        synchronized (FormImplement.class) {
            if (INSTANCE == null) {
                INSTANCE = new FormImplement();
            }
        }
        return INSTANCE;
    }

    @Override
    public String generateForm(Object objeto) throws InvocationTargetException, IllegalAccessException {
        Class classe = objeto.getClass();
        StringBuffer sb = new StringBuffer();
        if (classe.isAnnotationPresent(Form.class)) {
            sb.append("<form name=\"" + ((Form) classe.getAnnotation(Form.class)).name() + "\" action =" +
                    ((Form) classe.getAnnotation(Form.class)).action() + "\" >" + "\n");
        }
        for (Method metodo : classe.getMethods()) {
            if (metodo.isAnnotationPresent(Field.class)) {
                if (metodo.getAnnotation(Field.class).type() == Field.FieldType.HIDDEN) {
                    sb.append("   <div>");
                    sb.append("\n       <input type= \"hidden\" " +
                            " id= \"" + metodo.getAnnotation(Field.class).name() +
                            "\" name= \"" + metodo.getAnnotation(Field.class).name() +
                            "\"  value=\"" + metodo.invoke(objeto) + "\"/> \n");
                    sb.append("   </div>\n");
                } else if (metodo.getAnnotation(Field.class).type() == Field.FieldType.TEXTAREA) {
                    sb.append("   <div>");
                    sb.append("\n       <text-area name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.invoke(objeto) + " </text-area> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                } else if (metodo.getReturnType() == boolean.class) {
                    sb.append("   <div>");
                    sb.append("\n       <input type=\"checkbox\" name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" checked /> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                } else {
                    sb.append("   <div>");
                    sb.append("\n       <input type= \"text\" name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" size=\"" + metodo.getAnnotation(Field.class).size() +
                            "\"  value=\"" + metodo.invoke(objeto) + "\"/> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                }
            }
        }
        sb.append("   <div>\n");
        sb.append("       <input type=\"submit\" value=\"" + ((Form) classe.getAnnotation(Form.class)).submitButton() + "\"/>\n");
        sb.append("   </div>\n");
        sb.append("</form>");
        return sb.toString();
    }

    @Override
    public String generateReadOnlyView(Object objeto) throws InvocationTargetException, IllegalAccessException {
        Class classe = objeto.getClass();
        StringBuffer sb = new StringBuffer();
        if (classe.isAnnotationPresent(Form.class)) {
            sb.append("<form name=\"" + ((Form) classe.getAnnotation(Form.class)).name() + "\" action =" +
                    ((Form) classe.getAnnotation(Form.class)).action() + "\" >" + "\n");
        }
        for (Method metodo : classe.getMethods()) {
            if (metodo.isAnnotationPresent(Field.class)) {
                if (metodo.getAnnotation(Field.class).type() == Field.FieldType.HIDDEN) {
                    sb.append("   <div>");
                    sb.append("\n       <input type= \"hidden\" " +
                            " id= \"" + metodo.getAnnotation(Field.class).name() +
                            "\" name= \"" + metodo.getAnnotation(Field.class).name() +
                            "\"  value=\"" + metodo.invoke(objeto) + "\"/> \n");
                    sb.append("   </div>\n");
                } else if (metodo.getAnnotation(Field.class).type() == Field.FieldType.TEXTAREA) {
                    sb.append("   <div>");
                    sb.append("\n       <text-area name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() + "\" readonly > " +
                            metodo.invoke(objeto) + " </text-area> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                } else if (metodo.getReturnType() == boolean.class) {
                    sb.append("   <div>");
                    sb.append("\n       <input type=\"checkbox\" name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" disabled /> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                } else {
                    sb.append("   <div>");
                    sb.append("\n       <input type= \"text\" name=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" id=\"" + metodo.getAnnotation(Field.class).name() +
                            "\" size=\"" + metodo.getAnnotation(Field.class).size() +
                            "\"  value=\"" + metodo.invoke(objeto) + "\" readonly/> \n");
                    sb.append("       <label for=\"" + metodo.getAnnotation(Field.class).name() + "\"> " +
                            metodo.getAnnotation(Field.class).label() + " </label> \n");
                    sb.append("   </div>\n");
                }
            }
        }
        sb.append("   <div>\n");
        sb.append("       <input type=\"submit\" value=\"" + ((Form) classe.getAnnotation(Form.class)).submitButton() + "\" disabled />\n");
        sb.append("   </div>\n");
        sb.append("</form>");
        return sb.toString();
    }

    @Override
    public Object populateObject(Map<String, String> parametrosRequisicao, Class classe) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object o = classe.getConstructor().newInstance();
        for (Method method : classe.getMethods()) {
            if (method.getName().startsWith("get")) {
                if (method.isAnnotationPresent(Field.class)) {
                    //formar nome do metodoset a partir do get
                    String nomeMetodoSet = "set" + method.getName().substring(3, 4) + method.getName().substring(4);
                    for (Method met : classe.getMethods()) {
                        if (met.getName().equals(nomeMetodoSet)) {
                            Class returnType = method.getReturnType();
                            if (Number.class.isAssignableFrom(returnType)) { // casao precise fazer cast dos tipos para inserir
                                if (Integer.class.isAssignableFrom(returnType)||Integer.TYPE.equals(returnType)) {
                                    Integer inteiroInsere = Integer.parseInt(parametrosRequisicao
                                            .get(method.getAnnotation(Field.class).name()));
                                    met.invoke(o, inteiroInsere);
                                } else if (Long.class.isAssignableFrom(returnType) || Long.TYPE.equals(returnType)) {
                                    Long numLong = Long.valueOf(parametrosRequisicao.
                                            get(method.getAnnotation(Field.class).name()));
                                    met.invoke(o, numLong);
                                } else if (Double.class.isAssignableFrom(returnType)|| Double.TYPE.equals(returnType)) {
                                    Double pontoflutuante = Double.valueOf(parametrosRequisicao.
                                            get(method.getAnnotation(Field.class).name()));
                                    met.invoke(o, pontoflutuante);
                                }
                            } else if (Boolean.class.isAssignableFrom(returnType)|| Boolean.TYPE.equals(classe)) {
                                Boolean bol = Boolean.valueOf(parametrosRequisicao.
                                        get(method.getAnnotation(Field.class).name()));
                                met.invoke(o, bol);
                            } else if (String.class.isAssignableFrom(returnType)) {
                                if(method.getAnnotation(Field.class).name() != method.getAnnotation(Field.class).label()){
                                    String aux = method.getAnnotation(Field.class).label();
                                    aux = aux.replaceAll(" ", "");
                                    aux = aux.toLowerCase();
                                    String valor = parametrosRequisicao.get(aux);
                                    met.invoke(o,valor);
                                }else{
                                    String valor = parametrosRequisicao.
                                            get(method.getAnnotation(Field.class).name());
                                    met.invoke(o,valor);
                                }
                            }
                        }
                    }
                }
            }
        }
        return o;
    }
}