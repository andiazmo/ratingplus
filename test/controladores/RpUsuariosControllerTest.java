///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controladores;
//
//import entidades.RpUsuarios;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.CroppedImage;
//import session.UsuarioSeccion;
//
///**
// *
// * @author User
// */
//public class RpUsuariosControllerTest {
//    
//    public RpUsuariosControllerTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of init method, of class RpUsuariosController.
//     */
//    @Test
//    public void testInit() {
//        System.out.println("init");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.init();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCroppeFoto method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetCroppeFoto() {
//        System.out.println("getCroppeFoto");
//        RpUsuariosController instance = new RpUsuariosController();
//        CroppedImage expResult = null;
//        CroppedImage result = instance.getCroppeFoto();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCroppeFoto method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetCroppeFoto() {
//        System.out.println("setCroppeFoto");
//        CroppedImage croppeFoto = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setCroppeFoto(croppeFoto);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getImageTemp method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetImageTemp() {
//        System.out.println("getImageTemp");
//        RpUsuariosController instance = new RpUsuariosController();
//        String expResult = "";
//        String result = instance.getImageTemp();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setImageTemp method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetImageTemp() {
//        System.out.println("setImageTemp");
//        String imageTemp = "";
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setImageTemp(imageTemp);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsuarios method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetUsuarios() {
//        System.out.println("getUsuarios");
//        RpUsuariosController instance = new RpUsuariosController();
//        List<RpUsuarios> expResult = null;
//        List<RpUsuarios> result = instance.getUsuarios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUsuarios method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetUsuarios() {
//        System.out.println("setUsuarios");
//        List<RpUsuarios> usuarios = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setUsuarios(usuarios);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loguear method, of class RpUsuariosController.
//     */
//    @Test
//    public void testLoguear() {
//        System.out.println("loguear");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.loguear();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cerrar method, of class RpUsuariosController.
//     */
//    @Test
//    public void testCerrar() {
//        System.out.println("cerrar");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.cerrar();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEstado method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetEstado() {
//        System.out.println("getEstado");
//        RpUsuariosController instance = new RpUsuariosController();
//        String expResult = "";
//        String result = instance.getEstado();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEstado method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetEstado() {
//        System.out.println("setEstado");
//        String estado = "";
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setEstado(estado);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMisdatos method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetMisdatos() {
//        System.out.println("getMisdatos");
//        RpUsuariosController instance = new RpUsuariosController();
//        UsuarioSeccion expResult = null;
//        UsuarioSeccion result = instance.getMisdatos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of guardarusuario method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGuardarusuario() {
//        System.out.println("guardarusuario");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.guardarusuario();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actionFoto method, of class RpUsuariosController.
//     */
//    @Test
//    public void testActionFoto() {
//        System.out.println("actionFoto");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.actionFoto();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actionGuardarFoto method, of class RpUsuariosController.
//     */
//    @Test
//    public void testActionGuardarFoto() {
//        System.out.println("actionGuardarFoto");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.actionGuardarFoto();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of uploadFile method, of class RpUsuariosController.
//     */
//    @Test
//    public void testUploadFile() {
//        System.out.println("uploadFile");
//        FileUploadEvent event = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.uploadFile(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cambioClave method, of class RpUsuariosController.
//     */
//    @Test
//    public void testCambioClave() {
//        System.out.println("cambioClave");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.cambioClave();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of resetear method, of class RpUsuariosController.
//     */
//    @Test
//    public void testResetear() {
//        System.out.println("resetear");
//        RpUsuarios usuario = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.resetear(usuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generarClaveGenerica method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGenerarClaveGenerica() {
//        System.out.println("generarClaveGenerica");
//        String nombres = "Abc Operaciones";
//        RpUsuariosController instance = new RpUsuariosController();
//        String expResult = "AOperaciones";
//        String result = instance.generarClaveGenerica(nombres);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getNom method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetNom() {
//        System.out.println("getNom");
//        RpUsuariosController instance = new RpUsuariosController();
//        String expResult = "";
//        String result = instance.getNom();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNom method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetNom() {
//        System.out.println("setNom");
//        String nom = "";
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setNom(nom);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUser method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGetUser() {
//        System.out.println("getUser");
//        RpUsuariosController instance = new RpUsuariosController();
//        String expResult = "";
//        String result = instance.getUser();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUser method, of class RpUsuariosController.
//     */
//    @Test
//    public void testSetUser() {
//        System.out.println("setUser");
//        String user = "";
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.setUser(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of guardar method, of class RpUsuariosController.
//     */
//    @Test
//    public void testGuardar() {
//        System.out.println("guardar");
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.guardar();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of activacionUsuario method, of class RpUsuariosController.
//     */
//    @Test
//    public void testActivacionUsuario() {
//        System.out.println("activacionUsuario");
//        RpUsuarios usuario = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.activacionUsuario(usuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of bloqueacionUsuario method, of class RpUsuariosController.
//     */
//    @Test
//    public void testBloqueacionUsuario() {
//        System.out.println("bloqueacionUsuario");
//        RpUsuarios usuario = null;
//        RpUsuariosController instance = new RpUsuariosController();
//        instance.bloqueacionUsuario(usuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
