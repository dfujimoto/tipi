package com.petiteestudio.controllers.entregas;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.petiteestudio.Beans.DetalleVentaBean;
import com.petiteestudio.Beans.EntregaBean;
import com.petiteestudio.Beans.VentaEntregarBean;
import com.petiteestudio.entities.DetalleVenta;
import com.petiteestudio.entities.Diseño;
import com.petiteestudio.entities.Entrega;
import com.petiteestudio.entities.Venta;
import com.petiteestudio.services.envio.EntregaService;
import com.petiteestudio.services.uber.UberService;
import com.petiteestudio.services.venta.VentaService;

import com.petiteestudio.utilitarios.Util;
import com.uber.sdk.rides.client.model.PriceEstimate;
import com.uber.sdk.rides.client.model.Ride;
import com.uber.sdk.rides.client.model.TimeEstimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 21/07/2017.
 */

@Controller
public class EntregasController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private UberService uberService;

    @RequestMapping("/entregas")
    public String listaVentas(Model model) throws Exception{

        List array = new ArrayList();

        List<Venta> ventas= ventaService.ventasPorEntregar();
        String tiempoEstimado = obtenerTiempoEstimado();
        for (Venta venta:ventas) {
            VentaEntregarBean ventaEntregarBean = new VentaEntregarBean();

            ventaEntregarBean.setId(venta.getId());
            ventaEntregarBean.setIdCliente(venta.getCliente().getId());
            ventaEntregarBean.setNombre(venta.getCliente().getPersona().getNombreCompleto());
            ventaEntregarBean.setFechaPactada(Util.toDDMMYYYY(venta.getFechaEntregaPactada()));
            ventaEntregarBean.setDireccion(venta.getCliente().getPersona().getDireccion());
            ventaEntregarBean.setDistrito(venta.getCliente().getPersona().getDistrito());
            ventaEntregarBean.setDetalle(detalleVentas(venta.getDetalleVenta()));

            Float longitud = venta.getCliente().getPersona().getLatitud();
            Float latitud = venta.getCliente().getPersona().getLongitud();
            ventaEntregarBean.setPrecioEstimado(obtenerPrecioEstimado(longitud, latitud));
            ventaEntregarBean.setTiempoEstimado(tiempoEstimado+ " recojo de producto");
            array.add(ventaEntregarBean);
        }

        model.addAttribute("ventas", array);

        return "entregas/ventasEntregar";
    }

    @RequestMapping("/envios")
    public String listaEnvios(Model model){

        List<Entrega> entregas = entregaService.entregas();
        List<EntregaBean> lista = new ArrayList<>();
        for (Entrega entrega:entregas) {
            if(entrega.getEstado().equals("R")){
                continue;
            }
            EntregaBean entregaBean =  new EntregaBean();
            entregaBean.setId(entrega.getId());
            entregaBean.setNombreCliente(entrega.getVenta().getCliente().getPersona().getNombreCompleto());
            entregaBean.setDetalleVenta(detalleVentas(entrega.getVenta().getDetalleVenta()));
            entregaBean.setEstado(entrega.getEstado());
            entregaBean.setCostoEnvio(entrega.getCostoEnvio());
            entregaBean.setDireccionEntrega(entrega.getDireccionEntrega());
            entregaBean.setFechaEntrega(Util.toDDMMYYYY(entrega.getFechaEntrega()));
            entregaBean.setObservacion(entrega.getObservacion());

            lista.add(entregaBean);
        }

        model.addAttribute("entregas", lista);

        return "entregas/envios";
    }

    @RequestMapping("{id}/solicitarEnvio")
    public ModelAndView solicitarEnvio(@PathVariable Long id, Model model) {
        Venta venta = ventaService.obtenerVenta(id);
        Float longitud = venta.getCliente().getPersona().getLongitud();
        Float latitud = venta.getCliente().getPersona().getLatitud();

        try {
            Ride ride = uberService.solicitarServicioPrueba(longitud, latitud);

            Entrega entrega = new Entrega();
            entrega.setCabifyID(ride.getRideId());
            entrega.setEstado("P");
            entrega.setObservacion("Conductor: "+ride.getDriver().getName() + " | Telefono:" + ride.getDriver().getPhoneNumber() + " | Auto:" + ride.getVehicle().getModel() + ride.getVehicle().getLicensePlate());
            entrega.setVenta(venta);

            entregaService.registraEntrega(entrega);
            return new ModelAndView("redirect:/entregas/envios");
        }catch (Exception e){
            return new ModelAndView("redirect:/entregas");
        }

    }


    @RequestMapping("{id}/registrarEnvio")
    public String registrarEnvio(@PathVariable Long id, Model model) {
        Venta venta = ventaService.obtenerVenta(id);

        Entrega entrega = new Entrega();
        entrega.setDireccionEntrega(venta.getCliente().getPersona().getDireccion()+venta.getCliente().getPersona().getDistrito());
        entrega.setFechaEntrega(Util.getDate());
        model.addAttribute("entrega", entrega);
        model.addAttribute("venta", venta);
        model.addAttribute("cliente", venta.getCliente().getPersona());
        model.addAttribute("listaDetalle", listaDetalleVentaBean(venta) );

        return "entregas/registrarEnvio";
    }

    @RequestMapping(value = "guardarEnvio", method = RequestMethod.POST)
    public ModelAndView guardarEnvio(Entrega entrega, @RequestParam("idVenta") Long idVenta){

        Venta venta =ventaService.obtenerVenta(idVenta);
        venta.setEstado("F");

        entrega.setVenta(ventaService.obtenerVenta(idVenta));
        entrega.setEstado("C");
        entrega.setFechaRecojo(Util.getDate());

        entregaService.registraEntrega(entrega);
        ventaService.actualizarVenta(venta);
        return new ModelAndView("redirect:/envios");
    }

    @RequestMapping("{id}/confirmarEntrega")
    public String confirmarEntrega(@PathVariable Long id){
        Entrega entrega = entregaService.obtenetEntrega(id);
        entrega.setEstado("C");
        entregaService.registraEntrega(entrega);

        return "redirect:/envios";
    }

    @Transactional(readOnly = false)
    @RequestMapping("{id}/rechazarEntrega")
    public String rechazarEntrega(@PathVariable Long id){
        Entrega entrega = entregaService.obtenetEntrega(id);
        entrega.setEstado("R");

        Long idVenta = entrega.getVenta().getId();
        Venta venta = ventaService.obtenerVenta(idVenta);
        venta.setEstado("E");
        ventaService.actualizarVenta(venta);
        entregaService.registraEntrega(entrega);

        return "redirect:/envios";
    }

    @RequestMapping("{id}/editarEntrega")
    public String editarEntrega(Model model, @PathVariable Long id){
        Entrega entrega = entregaService.obtenetEntrega(id);
        entrega.setEstado("C");
        entregaService.registraEntrega(entrega);

        Venta venta = entrega.getVenta();
        model.addAttribute("entrega", entrega);
        model.addAttribute("venta", venta);
        model.addAttribute("cliente", venta.getCliente().getPersona());
        model.addAttribute("listaDetalle",listaDetalleVentaBean(venta) );

        return "entregas/editarEnvio";
    }

    @RequestMapping(value = "modificarEnvio", method = RequestMethod.POST)
    public ModelAndView modificarEnvio(Entrega entrega, @RequestParam("idVenta") Long idVenta){

        Venta venta =ventaService.obtenerVenta(idVenta);
        entrega.setVenta(venta);

        entregaService.registraEntrega(entrega);
        return new ModelAndView("redirect:/envios");
    }

    public String detalleVentas(List<DetalleVenta> lista){
        String detalleVentas = "";
        for (DetalleVenta elemento:lista) {
            detalleVentas = detalleVentas + elemento.getProducto().getDescripcion() +" "+ elemento.getCantidad() + " und | ";
        }

        return detalleVentas;
    }

    private String obtenerPrecioEstimado(Float longitud, Float latitud){
        String estimado;
        try {
            PriceEstimate precioEstimado = uberService.precioEstimado(longitud, latitud );
            estimado = precioEstimado.getHighEstimate() +" PEN";
        } catch (Exception e) {
            estimado = "DIRECCION";
        }
        return estimado;
    }

    private String obtenerTiempoEstimado(){
        String estimado;
        try {
            TimeEstimate timeEstimate = uberService.tiempoEstimado();
            estimado = timeEstimate.getEstimate()/60 +" min ";
        } catch (Exception e) {
            estimado = "No Disponible";
        }
        return estimado;
    }

    private List<DetalleVentaBean> listaDetalleVentaBean (Venta venta){
        List<DetalleVenta> listaDetalle = venta.getDetalleVenta();
        List<DetalleVentaBean> listaTablaDetalleVenta = new ArrayList<>();
        for (DetalleVenta detalleVenta : listaDetalle) {
            DetalleVentaBean bean = new DetalleVentaBean();
            bean.setDescripcion(detalleVenta.getProducto().getDescripcion());
            bean.setPrecio(detalleVenta.getPrecioUnitario());
            bean.setCantidad(detalleVenta.getCantidad());
            Diseño diseño = detalleVenta.getProducto().getDiseño();
            bean.setDimensiones(diseño.getAltura() + " x " + diseño.getLargo() + " x " + diseño.getAncho());
            listaTablaDetalleVenta.add(bean);
        }
        return listaTablaDetalleVenta;
    }
}
