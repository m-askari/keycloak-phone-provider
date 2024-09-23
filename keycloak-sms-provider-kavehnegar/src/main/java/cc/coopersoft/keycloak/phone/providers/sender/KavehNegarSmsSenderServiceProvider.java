package cc.coopersoft.keycloak.phone.providers.sender;

import cc.coopersoft.keycloak.phone.providers.exception.MessageSendException;
import cc.coopersoft.keycloak.phone.providers.spi.FullSmsSenderAbstractService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;

import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.ApiException;
import com.kavenegar.sdk.models.SendResult;

import javax.annotation.PostConstruct;

public class KavehNegarSmsSenderServiceProvider extends FullSmsSenderAbstractService {

    private static final Logger logger = Logger.getLogger(KavehNegarSmsSenderServiceProvider.class);
    private String kavehnegarApiKey; 
    private OkHttpClient client;

    
    KavehNegarSmsSenderServiceProvider(Scope config, String realmDisplay) {
        super(realmDisplay);
        this.kavehnegarApiKey = config.get("kavehnegarApiKey");

    }

    @Override
    public void sendMessage(String phoneNumber, String message) throws MessageSendException {
        try {
            KavenegarApi  api= new KavenegarApi(this.kavehnegarApiKey);
             SendResult Result = api.send("1000500030002",phoneNumber, message);
       }
        
       catch (ApiException ex)
       { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
          System.out.print("ApiException : " + ex.getMessage());
       }
        
    }

    @Override
    public void close() {
    }
}
