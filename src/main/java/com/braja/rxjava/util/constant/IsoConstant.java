package com.braja.rxjava.util.constant;

import java.util.List;

public class IsoConstant {

    public static final String MTI_DEFAULT_NOT_FOUND = "0000";
    public static final String MTI_AUTHORIZATION_REQ = "0100";
    public static final String MTI_AUTHORIZATION_REQ_ERROR = "9100";
    public static final String MTI_AUTHORIZATION_RES = "0110";
    public static final String MTI_FINANCIAL_REQ = "0200";
    public static final String MTI_FINANCIAL_REQ_ERROR = "9200";
    public static final String MTI_FINANCIAL_RES = "0210";
    public static final String MTI_REVERSAL_REQ = "0420";
    public static final String MTI_REVERSAL_REQ_ERROR = "9420";
    public static final String MTI_REVERSAL_REPEAT_REQ = "0421";
    public static final String MTI_REVERSAL_REPEAT_REQ_ERROR = "9421";
    public static final String MTI_REVERSAL_RES = "0430";
    public static final String MTI_ECHO_LOGON_LOGOOFF_REQ = "0800";
    public static final String MTI_ECHO_LOGON_LOGOOFF_REQ_ERROR = "9800";
    public static final String MTI_ECHO_LOGON_LOGOOFF_RES = "0810";
    public static final String MTI_REQUEST_PREFIX = "0";
    public static final String MTI_RESPONSE_PREFIX_ERROR_MANDATORY = "9";
    public static final String MTI_REQUEST_MESSAGE = "00";
    public static final String MTI_RESPONSE_MESSAGE = "10";
    public static final String MTI_REQUEST_REVS_MESSAGE = "20";
    public static final String MTI_REQUEST_REVS_REAPEAT_MESSAGE = "21";
    public static final String MTI_RESPONSE_REVS_MESSAGE = "30";
    public static final String NETWORK_MANAGEMENT_INFORMATION_CODE_ECHO = "301";
    public static final String NETWORK_MANAGEMENT_INFORMATION_CODE_SIGN_ON = "001";
    public static final String NETWORK_MANAGEMENT_INFORMATION_CODE_SIGN_OFF = "002";
    public static final String NETWORK_MANAGEMENT_INFORMATION_CODE_CUT_OVER = "201";
    public static final String DATA_ELEMENT_43_PATTERN = "%-25.25s%-13.13s%-2.2s";
    public static final String DATA_ELEMENT_90_PATTERN = "%4s%6s%10s%011d%011d";
    public static final String ISO_DATA_MANAGER_KEY_PATTERN = "%s-%s-%s-%s";
    public static final String TRANSACTION_FEE_AMOUNT_PATTERN = "%s%08d";
    public static final Integer MTI_START_INDEX_OF_MESSAGE = 0;
    public static final Integer MTI_INDEX_OF_REQ_RES_MESSAGE = 2;
    public static final Integer ISO_DATA_MANAGER_INDEX = 0;
    public static final Integer ISO_LENGTH_FIELD_OFFSET = 0;
    public static final Integer ISO_LENGTH_FIELD_HEADER = 2;
    public static final Integer ISO_LENGTH_FIELD_ADJUSTMENT = 0;
    public static final Integer ISO_INITIAL_BYTE_STRIP = 2;
    public static final Integer CARD_ACCEPTOR_NAME_LOCATION_LENGTH = 40;
    public static final Integer CARD_ACCEPTOR_NAME_LOCATION_START = 0;
    public static final Integer CARD_ACCEPTOR_NAME_LOCATION_NAME = 25;
    public static final Integer CARD_ACCEPTOR_NAME_LOCATION_CITY = 38;
    public static final Integer CARD_ACCEPTOR_NAME_LOCATION_COUNTRY = 40;
    public static final Integer ORIGINAL_DATA_ELEMENTS_LENGTH = 42;
    public static final Integer ORIGINAL_DATA_ELEMENTS_STAN = 4;
    public static final Integer ORIGINAL_DATA_ELEMENTS_STAN_LENGTH = 6;
    public static final Integer ORIGINAL_DATA_ELEMENTS_TRANSMISSION_DATETIME = 10;
    public static final Integer ORIGINAL_DATA_ELEMENTS_TRANSMISSION_DATETIME_LENGTH = 10;

    public static final List<String> MTI_REQUEST = List.of(new String[]{MTI_AUTHORIZATION_REQ, MTI_FINANCIAL_REQ, MTI_REVERSAL_REQ, MTI_REVERSAL_REPEAT_REQ, MTI_ECHO_LOGON_LOGOOFF_REQ}) ;
    public static final List<String> MTI_RESPONSE = List.of(new String[]{MTI_AUTHORIZATION_RES, MTI_FINANCIAL_RES, MTI_REVERSAL_RES, MTI_ECHO_LOGON_LOGOOFF_RES, MTI_AUTHORIZATION_REQ_ERROR, MTI_FINANCIAL_REQ_ERROR, MTI_REVERSAL_REQ_ERROR, MTI_REVERSAL_REPEAT_REQ_ERROR, MTI_ECHO_LOGON_LOGOOFF_REQ_ERROR});
}
