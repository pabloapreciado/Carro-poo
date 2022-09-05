package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Carro;
import vista.VentanaPrincipal;

public class Controlador implements ActionListener
{
    //----------------------------
    //Atributos
    //----------------------------
    private VentanaPrincipal venPrin;
    private Carro model;
    
    //----------------------------
    //Metodos
    //----------------------------
    
    //Constructor
    public Controlador(VentanaPrincipal pVenPrin, Carro pModel)
    {
        this.venPrin = pVenPrin;
        this.model = pModel;
        this.venPrin.miPanelOperaciones.agregarOyentesBotones(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        //Identificar el comendo generado (calcular, borrar, salir)
        String comando = ae.getActionCommand();
        
        if(comando.equals("crear"))
        {   
            //Validar datos entrada
            try
            {
                String color = venPrin.miPanelEntradaDatos.getColor();
                int posX = Integer.parseInt(venPrin.miPanelEntradaDatos.getPosX());
                int poxY = Integer.parseInt(venPrin.miPanelEntradaDatos.getPosY());
            
                //Creación del objeto tipo Carro
                model = new Carro(color, posX, poxY);
                
                venPrin.miPanelResultado.mostrarResultado("Se ha creado un objeto Carro");
                venPrin.miPanelResultado.mostrarResultado("Su número de ruedas son " + model.getNumRuedas());
                venPrin.miPanelResultado.mostrarResultado("Su color actual es " + model.getColor());
                venPrin.miPanelResultado.mostrarResultado("Su posición actual es (" + model.getPosX()+"," + model.getPosY()+")" );
                venPrin.miPanelResultado.mostrarResultado("Su velocidad actual es " + model.getVelocidad());
                
                //Desactivar boton crear
                venPrin.miPanelOperaciones.desactivarBotonCrear();
                //Activar botones
                venPrin.miPanelOperaciones.activarBotones();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error en datos de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        }
        
        if(comando.equals("mover"))
        {   
            venPrin.crearDialogoMover();
            this.venPrin.miDialogoMoverCarro.agregarOyentesBotones(this);  
        }
        
        if(comando.equals("aceptar"))
        {
            int x = Integer.parseInt(venPrin.miDialogoMoverCarro.getPosX());
            int y = Integer.parseInt(venPrin.miDialogoMoverCarro.getPosY());
            model.mover(x, y);
            venPrin.miPanelResultado.mostrarResultado("\nTras mover el carro, su posición actual es (" + model.getPosX()+"," + model.getPosY()+")");
            venPrin.miDialogoMoverCarro.cerrarDialogo();
        }
        
        if(comando.equals("acelerar"))
        {
            model.acelerar();
            venPrin.miPanelResultado.mostrarResultado("\nTras acelerar, su velocidad actual es " + model.getVelocidad());
        }
        
        if(comando.equals("frenar"))
        {
            model.frenar();
            venPrin.miPanelResultado.mostrarResultado("\nTras frenar, su velocidad actual es " + model.getVelocidad());
        }
    }    
}