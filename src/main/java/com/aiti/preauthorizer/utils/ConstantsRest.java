package com.aiti.preauthorizer.utils;

public class ConstantsRest {

    /** Constants Users **/
    public static final String GET_USER_NOT_FOUND = "CC:USER:GET:412:001";
    public static final String GET_USER_BAD_REQUEST = "CC:USER:GET:400:002";
    public static final String GET_USER_INTERNAL_SERVER_ERROR = "CC:USER:GET:500:003";

    /** Constants Seeds **/
    public static final String GET_SEED_NOT_FOUND = "CC:SEED:GET:412:004";
    public static final String GET_SEED_BAD_REQUEST = "CC:SEED:GET:400:005";
    public static final String GET_SEED_INTERNAL_SERVER_ERROR = "CC:SEED:GET:500:006";

    /** Constants Card **/
    public static final String GET_CARD_NOT_FOUND = "CC:CARDCH:GET:412:007";
    public static final String GET_CARD_BAD_REQUEST = "CC:CARDCH:GET:400:008";
    public static final String GET_CARD_INTERNAL_SERVER_ERROR = "CC:CARDCH:GET:500:009";

    public static final String POST_CARD_CONFLICT = "CC:CARDCH:POST:409:010";
    public static final String POST_CARD_BAD_REQUEST = "CC:CARDCH:POST:400:011";
    public static final String POST_CARD_INTERNAL_SERVER_ERROR = "CC:CARDCH:POST:500:012";

    public static final String PUT_CARD_NOT_FOUND = "CC:CARDCH:PUT:412:013";
    public static final String PUT_CARD_BAD_REQUEST = "CC:CARDCH:PUT:400:014";
    public static final String PUT_CARD_INTERNAL_SERVER_ERROR = "CC:CARDCH:PUT:500:015";

    /** Constants Channels **/
    public static final String GET_CHNN_NOT_FOUND = "CC:LAMOUNT:GET:412:016";
    public static final String GET_CHNN_BAD_REQUEST = "CC:LAMOUNT:GET:400:017";
    public static final String GET_CHNN_INTERNAL_SERVER_ERROR = "CC:LAMOUNT:GET:500:018";

    public static final String POST_CHNN_CONFLICT = "CC:LAMOUNT:POST:409:019";
    public static final String POST_CHNN_BAD_REQUEST = "CC:LAMOUNT:POST:400:020";
    public static final String POST_CHNN_INTERNAL_SERVER_ERROR = "CC:LAMOUNT:POST:500:021";

    public static final String PUT_CHNN_NOT_FOUND = "CC:LAMOUNT:PUT:412:022";
    public static final String PUT_CHNN_BAD_REQUEST = "CC:LAMOUNT:PUT:400:023";
    public static final String PUT_CHNN_INTERNAL_SERVER_ERROR = "CC:LAMOUNT:PUT:500:024";

    /** Constants Movement **/
    public static final String GET_MOVE_NOT_FOUND = "CC:LMOVNUM:GET:412:025";
    public static final String GET_MOVE_BAD_REQUEST = "CC:LMOVNUM:GET:400:026";
    public static final String GET_MOVE_INTERNAL_SERVER_ERROR = "CC:LMOVNUM:GET:500:027";

    public static final String POST_MOVE_CONFLICT = "CC:LMOVNUM:POST:409:028";
    public static final String POST_MOVE_BAD_REQUEST = "CC:LMOVNUM:POST:400:029";
    public static final String POST_MOVE_INTERNAL_SERVER_ERROR = "CC:LMOVNUM:POST:500:030";

    public static final String PUT_MOVE_NOT_FOUND = "CC:LMOVNUM:PUT:412:031";
    public static final String PUT_MOVE_BAD_REQUEST = "CC:LMOVNUM:PUT:400:032";
    public static final String PUT_MOVE_INTERNAL_SERVER_ERROR = "CC:LMOVNUM:PUT:500:033";

    /** Constants Enrollment **/
    public static final String GET_ENROLL_NOT_FOUND = "CC:USRENROLL:GET:412:034";
    public static final String GET_ENROLL_BAD_REQUEST = "CC:USRENROLL:GET:400:035";
    public static final String GET_ENROLL_INTERNAL_SERVER_ERROR = "CC:USRENROLL:GET:500:036";

    public static final String POST_ENROLL_CONFLICT = "CC:USRENROLL:POST:409:037";
    public static final String POST_ENROLL_BAD_REQUEST = "CC:USRENROLL:POST:400:038";
    public static final String POST_ENROLL_INTERNAL_SERVER_ERROR = "CC:USRENROLL:POST:500:039";

    public static final String PUT_ENROLL_NOT_FOUND = "CC:USRENROLL:PUT:412:040";
    public static final String PUT_ENROLL_BAD_REQUEST = "CC:USRENROLL:PUT:400:041";
    public static final String PUT_ENROLL_INTERNAL_SERVER_ERROR = "CC:USRENROLL:PUT:500:042";

    /** Constants User Status **/
    public static final String PUT_STATUS_NOT_FOUND = "CC:USRSTATUS:PUT:412:043";
    public static final String PUT_STATUS_BAD_REQUEST = "CC:USRSTATUS:PUT:400:043";
    public static final String PUT_STATUS_INTERNAL_SERVER_ERROR = "CC:USRSTATUS:PUT:500:044";

    /** Constants Movements Limit **/
    public static final Integer LIMIT_AMOUNTS_OPERATION = 0;
    public static final Integer LIMIT_AMOUNTS_DAY = 1;
    public static final Integer NUMBER_TRANSACTION_DAY = 2;

}

