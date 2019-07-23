package com.aiti.preauthorizer.services.postgres;

import java.util.List;
import java.util.Map;

public interface CardService {
    public Map blockCard(String pan,String idClient, String traceid,  int  status);
    public Map blockUser(String idclient, String traceid, String status);
    public Map enrollCard( String idClient, String traceid, Map dataEnroll);
    public Map getInformationUser(String idclient, String traceid);
    public Map enrollUser(String idClient, String traceid, Map dataEnroll);
    public Map getInformationAllCards(String idclient, String traceid);
}
