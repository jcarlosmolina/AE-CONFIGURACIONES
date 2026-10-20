package com.keralty.aeconfiguraciones.api.formapago;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;
import com.keralty.aeconfiguraciones.service.FormaPagoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.formapago.piu.PIUFormaPagoDetDominioResponse;
import com.keralty.aeconfiguraciones.viewmodel.formapago.piu.PIUFormaPagoResponse;
import com.keralty.aeconfiguraciones.viewmodel.formapago.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.formapago.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.formapago.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/FormaPagoApi")
@RequestScoped
public class FormaPagoApi {

    FormaPagoService service;

    public FormaPagoApi(FormaPagoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{formaPagoCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public FormaPago getInstance(Long formaPagoCodigo) {
    	return service.getById(formaPagoCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<FormaPago> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getFormaPagoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public FormaPago getByOid(FormaPagoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public FormaPago getByOIDWithHV(FormaPagoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetFormaPagoDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getFormaPagoDynamic(QueryRequest request) {
        FormaPagoOid oid = new FormaPagoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getFormaPagoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getFormaPagoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getFormaPagoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse formaPagoCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse formaPagoEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse formaPagoModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_FormaPago")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUFormaPagoResponse piuPIUFormaPago(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUFormaPagoResponse response;
        try
        {
            response = new PIUFormaPagoResponse(service.piuPIUFormaPago(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUFormaPago(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUFormaPagoResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_FormaPagoDetDominio")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUFormaPagoDetDominioResponse piuPIUFormaPagoDetDominio(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUFormaPagoDetDominioResponse response;
        try
        {
            response = new PIUFormaPagoDetDominioResponse(service.piuPIUFormaPagoDetDominio(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUFormaPagoDetDominio(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUFormaPagoDetDominioResponse();
            response.setException(e);
        }
        return response;
    }


    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
