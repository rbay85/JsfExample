package main.com.jsfExample.Service;

import com.sun.jersey.api.client.Client;
import main.com.jsfExample.DTOs.MyClient;
import main.com.jsfExample.DTOs.Tariff;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean( name="RestClientService" )
@SessionScoped
public class RestClientService implements Serializable{

    private static final long serialVersionUID = 7L;

    List<Tariff> tariffList = getTariffListFromMap( getTariffMapList());

    private List<Tariff> getTariffListFromMap ( List<HashMap> tariffMapList ){
        List<Tariff> tariffList = new ArrayList<Tariff>();
        for ( HashMap hashMap : tariffMapList ){
            Tariff tariff = new Tariff();
            tariff.setId( String.valueOf( hashMap.get( "id" )));
            tariff.setName( String.valueOf( hashMap.get( "name" )));
            tariffList.add( tariff );
        }
        return tariffList;
    }

    public List<HashMap> getTariffMapList (){
        return Client.create()
                .resource( "http://localhost:8080/json/tariffs" )
                .accept( MediaType.APPLICATION_JSON )
                .get( List.class );
    }

    public List<HashMap> getClientMapList ( String tariffId ){
        return Client.create()
                .resource( "http://localhost:8080/json/tariff/" + tariffId )
                .accept( MediaType.APPLICATION_JSON )
                .get( List.class );
    }

    // геттер и сеттер, toString
    public List<Tariff> getTariffList() {
        return tariffList;
    }
    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }
    @Override
    public String toString() {
        return "RestClientService{" +
                "tariffList=" + tariffList +
                '}';
    }
}
