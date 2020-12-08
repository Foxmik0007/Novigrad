package com.example.novigrad30;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testclientconstructor()
    {
        Client client = new Client("qr","lumbu","david","123456","david@.com");
        assertEquals("david",client.getPrenom());

    }


    @Test
    public void testheur()
    {
        Heure heure = new Heure("12","23");
        assertEquals("12",heure.getDebut());
        assertEquals("23",heure.getFin());

    }

    @Test
    public void testEmployeHelperclass()
    {
        EmployeHelperClass employeHelperClass = new EmployeHelperClass("mika","mika","mikazo@.com","123456789","moncton","123 rue daniel");

        assertEquals("mika",employeHelperClass.getID());
        assertEquals("mika",employeHelperClass.getName());
        assertEquals("mikazo@.com",employeHelperClass.getEmail());
        assertEquals("123456789",employeHelperClass.getPassword());
        assertEquals("moncton",employeHelperClass.getNomSuccursale());
        //assertEquals("123 rue daniel ",employeHelperClass.getAdressSuccursale());


    }


    @Test
    public void testServiceHelperclass()
    {
        ServicesHelperClass c = new ServicesHelperClass("mi","carte santé","document","permit");

        assertEquals("mi",c.getServiceId());
        assertEquals("carte santé",c.getNom());
        assertEquals("document",c.getDocuments());
        assertEquals("permit",c.getFormulaireRequis());

    }

    @Test
    public void testService()
    {
        Service c = new Service("mi","carte santé","document","permit");

        assertEquals("mi",c.getServiceId());
        assertEquals("carte santé",c.getServiceName());
        assertEquals("document",c.getDocuments());
        assertEquals("permit",c.getFormulaireRequis());

    }


}