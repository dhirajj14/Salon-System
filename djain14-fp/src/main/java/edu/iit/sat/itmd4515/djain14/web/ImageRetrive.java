/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.djain14.web;

import edu.iit.sat.itmd4515.djain14.domain.Products;
import edu.iit.sat.itmd4515.djain14.ejbService.ProductsService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author dhira
 */
@RequestScoped
@Named
public class ImageRetrive {
    
    private Products product;
    
    @EJB
    private ProductsService productsSVC;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            System.out.print("id:"+Long.valueOf(id));
            product = productsSVC.findImageById(Long.valueOf(id));
            System.out.println("Product id"+product);
            System.out.println("Product id"+product.getProductImage());
            byte[] image = product.getProductImage();
            return new DefaultStreamedContent(new ByteArrayInputStream(image));
        }
    }

}
