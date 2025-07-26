package savepay.savepay.domain.telecom.converter;

import savepay.savepay.domain.telecom.entity.TelecomName;

public class TelecomConverter {

    public static TelecomName nameToEnum(String telecomName) {
        return TelecomName.valueOf(telecomName);
    }
}
