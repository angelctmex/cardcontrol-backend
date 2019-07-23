package com.aiti.preauthorizer.services.postgres;

import java.util.Map;

public interface CardControlService {
    Map configureCardAccessChannels(String pan,String userId,String traceid,Map channels);
    Map configureNumberTransactionsDay(String userId, String panCard, String traceid, Map<String, Object> movimientes);
    Map getCurrentStatusAccessChannels(String pan,String traceid,String userId);
    Map getLimitAmountsOperation(String userId, String panCard, String traceid);
    Map getLimitAmountsDay(String userId, String panCard, String traceid);
    Map getNumberTransactionsDay(String userId, String panCard, String traceid);
    Map setMaximAmountsDay(String userId, String panCard, Map<String, Object> amounts, String traceid);
    Map setMaximAmountsOperation(String userId, String panCard, Map<String, Object> amounts, String traceId);
}
