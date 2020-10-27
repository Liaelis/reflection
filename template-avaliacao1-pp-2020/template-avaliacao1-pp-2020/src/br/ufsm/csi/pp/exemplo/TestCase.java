package br.ufsm.csi.pp.exemplo;

import br.ufsm.csi.pp.FormGenerator;
import br.ufsm.csi.pp.FormImplement;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestCase {

    private FormGenerator formGenerator;
    private ClasseExemplo classeExemplo;

    public TestCase(FormGenerator formGenerator, ClasseExemplo classeExemplo) {
        this.formGenerator = formGenerator;
        this.classeExemplo = classeExemplo;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        FormGenerator formGenerator =  FormImplement.getInstance(); //incluir o seu form aqui
        ClasseExemplo classeExemplo = new ClasseExemplo();
        classeExemplo.setAtivo(true);
        classeExemplo.setId((long) 111);
        classeExemplo.setCodigo((long) 1000);
        classeExemplo.setAtributo1("asdfadsfasdfasdfasdfasdfasdfasdf");
        TestCase testCase = new TestCase(formGenerator, classeExemplo);
        testCase.test1().test2().test3();
    }

    public TestCase test1() throws InvocationTargetException, IllegalAccessException {
        System.out.println(formGenerator.generateForm(classeExemplo));
        return this;
    }


    public TestCase test2() throws InvocationTargetException, IllegalAccessException {
        System.out.println(formGenerator.generateReadOnlyView(classeExemplo));
        return this;
    }

    public TestCase test3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Map<String, String> req = new HashMap<>();
        req.put("id", "111");
        req.put("codigo", "1000");
        req.put("atributo1", "asdfasdfasdfasdfasdf");
        req.put("atributo2", "asdfasdfasdfasdfasdf");
        req.put("ativo", "true");
        ClasseExemplo ex = (ClasseExemplo) formGenerator.populateObject(
                req,
                ClasseExemplo.class
        );
        System.out.println(formGenerator.generateReadOnlyView(ex));
        return this;
    }

}
