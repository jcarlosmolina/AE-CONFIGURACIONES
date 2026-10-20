package com.keralty.aeconfiguraciones.api.pregunta;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.service.PreguntaService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.ds.DSPreguntaIIU;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.piu.PIUPreguntaResponse;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest;
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

@Path("/api/PreguntaApi")
@RequestScoped
public class PreguntaApi {

    PreguntaService service;

    public PreguntaApi(PreguntaService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{preguntaNumero}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public Pregunta getInstance(Long preguntaNumero) {
    	return service.getById(preguntaNumero);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<Pregunta> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public Pregunta getByOid(PreguntaOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public Pregunta getByOIDWithHV(PreguntaOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetPreguntaDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getPreguntaDynamic(QueryRequest request) {
        PreguntaOid oid = new PreguntaOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getPreguntaDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getPreguntaPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/InsValorPregunta")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaInsValorPregunta(InsValorPreguntaRequest request) {
        return service.insValorPregunta(request);
    }
    

    @POST
    @Path(value = "/DelValorPregunta")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaDelValorPregunta(DelValorPreguntaRequest request) {
        return service.delValorPregunta(request);
    }
    

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TCREARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaTCREARCheckPreconditions(TCREARRequest request) {

        return service.tCREARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    

    @POST
    @Path(value = "/TMODIFICARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaTMODIFICARCheckPreconditions(TMODIFICARRequest request) {

        return service.tMODIFICARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse preguntaTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    

    @POST
    @Path(value = "/IIU_Pregunta")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public DSPreguntaIIU iiuIIUPregunta(PreguntaOid oid) {
        return service.iIUPregunta(oid);
    }

    @POST
    @Path(value = "/PIU_Pregunta")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUPreguntaResponse piuPIUPregunta(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUPreguntaResponse response;
        try
        {
            response = new PIUPreguntaResponse(service.piuPIUPregunta(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUPregunta(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUPreguntaResponse();
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
